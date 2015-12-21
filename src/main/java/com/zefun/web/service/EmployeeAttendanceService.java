package com.zefun.web.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.common.enums.EmployeeRewardTypeEnum;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.EmployeeAttendance;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.ShiftMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;

/**
 * 员工考勤服务类
* @author 张进军
* @date Dec 6, 2015 11:34:43 AM 
*/
@Service
public class EmployeeAttendanceService {
    
    /**员工班次操作对象*/
    @Autowired
    private ShiftMapper shiftMapper;
    
    /**员工考勤操作对象*/
    @Autowired
    private EmployeeAttendanceMapper employeeAttendanceMapper;
    
    /** 门店管理制度映射 */
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;
    
    /** 员工奖惩映射 */
    @Autowired
    private EmployeeRewardMapper employeeRewardMapper;
    
    /**缓存服务对象*/
    @Autowired
    private RedisService redisService;

    /**员工轮牌操作对象*/
    @Autowired
    private ShiftMahjongService shiftMahjongService;
    
    /**
     * 签到/签退操作
    * @author 张进军
    * @date Dec 6, 2015 12:11:53 AM
    * @param storeId        门店标识
    * @param employeeId     员工标识
    * @return   成功返回码为0，失败为其他异常信息
     */
    @Transactional
    public BaseDto signOperate(int storeId, int employeeId) {
        //先检查员工班次
        Map<Integer, String> map = shiftMapper.selectShiftByEmployeeId(employeeId);
        Calendar calendar = Calendar.getInstance();
        String workTime = "";
        if (map == null || map.isEmpty()) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "对不起，您暂未设置班次信息！");
        } 
        
        //检查是否为休息日
        workTime = map.get("" + calendar.get(Calendar.DAY_OF_WEEK));
        if (StringUtils.isBlank(workTime)) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "今天是您的休息日，如需上牌请调整班次！");
        }
        /**
         * 拆分workTime(eg:08:45,12:00 或者 22:00,04:00),
         * 拼接日期
         */
        String[] workTimeArray = workTime.split(",");  //eg:workTimeArray[0] -> 08:45, workTimeArray[1] -> 12:00
        String[] beginTimeArray = workTimeArray[0].split(":"); //eg:beginTimeArray[0] -> 08, beginTimeArray[1] -> 45
        String[] endTimeArray = workTimeArray[1].split(":"); //eg:endTimeArray[0] -> 12, endTimeArray[1] -> 00
        //if跨日
        if (Integer.parseInt(beginTimeArray[0]) > Integer.parseInt(endTimeArray[0])) {
        	workTimeArray[1] = DateUtil.addOneDay() + " " + workTimeArray[1] + ":00";
        } 
        else {
        	workTimeArray[1] = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " " + workTimeArray[1] + ":00";
        }
        workTimeArray[0] = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +  " " + workTimeArray[0] + ":00";
        
        
        BaseDto dto = new BaseDto();
        dto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
        
        //查询员工当日考勤情况
        EmployeeAttendance employeeAttendance = employeeAttendanceMapper.selectByEmployeeId(employeeId);
        //查询该店铺所以规则
        List<StoreManageRule> srList = storeManageRuleMapper.selectRuleListByStoreId(storeId);
        //如果当日未签到
        String curTime = DateUtil.getCurTime();
        if (employeeAttendance == null) {
            employeeAttendance = new EmployeeAttendance();
            employeeAttendance.setEmployeeId(employeeId);
            employeeAttendance.setAttendanceDate(DateUtil.getCurDate());
            employeeAttendance.setSignInTime(curTime);
            //curTime:员工当前打卡时间，workTime:数据库中查询的工作时间(eg:08:35,20:00)
            int signInOffset = DateUtil.getTwoTimeBetween(curTime, workTimeArray[0]);
            /**
             * 如果签到时间差(signInOffset)为负数，代表迟到了，
             * 就查询该员工所在店铺定义的迟到规则，比对时间看是否满足迟到条件，
             * 如果满足，就往employee_reward()添加条数据。
             */
            if (signInOffset < 0) {
            	StoreManageRule rule = null ;
            	for (StoreManageRule s : srList) {
            		if (s.getRuleName().equals("迟到")) {
            			rule = s;
            		}
            	}
            	int storeRuleViolationMinute = storeRuleViolationMinute(rule.getTargetType(), rule.getTargetValue());
            	if (signInOffset + storeRuleViolationMinute < 0) {
            		EmployeeReward er = new EmployeeReward();
            		er.setEmployeeId(employeeId);
            		er.setType(String.valueOf(EmployeeRewardTypeEnum.LATE.getEmployeeRewardType()));
            		er.setIsReward("0");
            		er.setNumber(rule.getProcessMoney().doubleValue());
            		er.setModifyer(0);
            		er.setModifytime(curTime);
            		er.setReasons("迟到:" + -signInOffset + "分钟");
            		employeeRewardMapper.insertSelective(er);
            	}
            }
            employeeAttendance.setSignInOffset(signInOffset);
            employeeAttendanceMapper.insert(employeeAttendance);
            shiftMahjongService.upShiftMahjong(employeeId);
            redisService.hset(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId, "1");
            dto.setMsg("签到成功");
        }
        //已签到，未签退
        else if (StringUtils.isBlank(employeeAttendance.getSignOutTime())) {
            employeeAttendance.setSignOutTime(DateUtil.getCurTime());
            int signOutOffset = DateUtil.getTwoTimeBetween(workTimeArray[1], curTime);
            //早退
            if (signOutOffset < 0) {
            	StoreManageRule rule = null ;
            	for (StoreManageRule s : srList) {
            		if (s.getRuleName().equals("早退")) {
            			rule = s;
            		}
            	}
            	int storeRuleViolationMinute = storeRuleViolationMinute(rule.getTargetType(), rule.getTargetValue());
            	if (signOutOffset + storeRuleViolationMinute < 0) {
            		EmployeeReward er = new EmployeeReward();
            		er.setEmployeeId(employeeId);
            		er.setType(String.valueOf(EmployeeRewardTypeEnum.EARLY_LEAVE.getEmployeeRewardType()));
            		er.setIsReward("0");
            		er.setNumber(rule.getProcessMoney().doubleValue());
            		er.setModifyer(0);
            		er.setModifytime(curTime);
            		er.setReasons("早退:" + -signOutOffset + "分钟");
            		employeeRewardMapper.insertSelective(er);
            	}
            }
            employeeAttendance.setSignOutOffset(signOutOffset);
            employeeAttendanceMapper.updateSignoutByEmployeeId(employeeAttendance);
            shiftMahjongService.downShiftMahjong(employeeId);
            redisService.hset(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId, "2");
            dto.setMsg("签退成功");
        }
        //已签到，已签退
        else {
            dto.setMsg("您今日已完成签到、签退操作！");
        }
        return dto;
    }
    
    /**
     * 计算该店铺违规时间(分钟)
     * @param targetType  违规时间类型(1:分钟，2:小时)
     * @param targetValue  违规时间数值
     * @return 分钟
     */
    private int storeRuleViolationMinute(int targetType, int targetValue) {
    	if (targetType == 2) {
    		return targetType * 2 + targetValue;
    	}
    	return targetValue;
    }
}
