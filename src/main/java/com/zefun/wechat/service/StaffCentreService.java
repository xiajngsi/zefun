package com.zefun.wechat.service;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;

/**
 * 移动端员工的订单服务逻辑类
* @author 王大爷
* @date Oct 13, 2015 9:29:53 PM 
*/
@Service
public class StaffCentreService {
    
    /** 员工*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /**员工提成业绩操作对象*/
    @Autowired
    private EmployeeCommissionMapper employeeCommissionMapper;
    
    /**redis操作对象*/
    @Autowired
    private RedisService redisService;
    
    /**会员信息服务对象*/
    @Autowired
    private MemberInfoService memberInfoService;
    
    /**消费队列服务对象*/
    @Autowired
    private RabbitService rabbitService;
    
    /** 会员基本信息*/
    @Autowired MemberInfoMapper memberInfoMapper;
    
    /**会员预约操作对象*/
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    /** */
    @Autowired private StaffService staffService;
    
    /** 轮派员工信息*/
    @Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
    
    /** 项目*/
    @Autowired private ProjectInfoMapper projectInfoMapper;
    
    /** 订单明细*/
    @Autowired private OrderDetailMapper orderDetailMapper;
    
    /** 员工目标*/
    @Autowired private EmployeeObjectiveMapper employeeObjectiveMapper;
    
    /** 轮牌信息*/
    @Autowired private ShiftMahjongMapper shiftMahjongMapper;
    
    /** 员工账号操作对象 */
    @Autowired private UserAccountMapper userAccountMapper;
    
    /**日志记录对象*/
//    private Logger logger = Logger.getLogger(StaffCentreService.class);
    
    /**
     * 个人中心页面
    * @author 王大爷
    * @date 2015年10月18日 下午2:18:30
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView staffCenter(Integer employeeId){
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_CENTER);
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("employeeInfo", employeeInfo);
        mav.addObject("signStatus", redisService.hget(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId));
        return mav;
    }
    
    
    /**
     * 员工个人信息页面
    * @author 张进军
    * @date Dec 11, 2015 9:42:54 PM
    * @param employeeId 员工标识
    * @return   员工个人信息页面
     */
    public ModelAndView staffInfo(int employeeId){
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_INFO);
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("employeeInfo", employeeInfo);
        return mav;
    }
    
    
    /**
     * 修改员工密码
    * @author 张进军
    * @date Dec 11, 2015 9:55:10 PM
    * @param employeeId 员工标识
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @return   成功返回码为0，失败为其他错误码
     */
    public BaseDto updatePwd(Integer employeeId, String oldPwd, String newPwd){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(employeeId);
        
        //检查用户密码
        if (!StringUtil.mD5(oldPwd + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        String hash = StringUtil.encryptPwd(newPwd);
        newPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(newPwd);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setUpdateTime(DateUtil.getCurTime());
        userAccountMapper.updateByPrimaryKey(userAccount);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 查询员工个人预约列表
    * @author 张进军
    * @date Oct 28, 2015 9:11:09 PM
    * @param employeeId 员工标识
    * @param type       预约类型(1:预约中，2:已确认，3:已取消)
    * @return   员工预约列表页面
     */
    public ModelAndView staffAppoint(int employeeId, int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeId", employeeId);
        map.put("status", "1");
        if (type == 2) {
            map.put("status", "2");
        } 
        else if (type == 3) {
            map.put("status", "4, 5");
        }
        List<AppointmentBaseDto> appointList = memberAppointmentMapper.selectAppointmentByEmployeeId(map);
        if (!appointList.isEmpty()) {
            for (AppointmentBaseDto appointment : appointList) {
                appointment.setMemberInfo(memberInfoService.getMemberBaseInfo(appointment.getMemberId()));
            }
        }
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_APPOINT);
        mav.addObject("type", type);
        mav.addObject("appointmentList", appointList);
        return mav;
    }
    
    
    /**
     * 同意/拒绝预约
    * @author 张进军
    * @date Nov 4, 2015 10:38:27 AM
    * @param type           操作类型(1:同意，2:拒绝)
    * @param storeId        门店标识
    * @param employeeId     员工标识
    * @param appointmentId  预约标识
    * @param memberId       会员标识
    * @param projectName    项目名称
    * @param appointTime    预约时间
    * @param reason         取消原因
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto appointOperate(int type, int storeId, int employeeId, int appointmentId, int memberId, 
            String projectName, String appointTime, String reason){
        String curTime = DateUtil.getCurTime();
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setAppointmentId(appointmentId);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(2);
        if (type == 2) {
            memberAppointment.setCancelReason(reason);
            memberAppointment.setAppointmentStatus(5);
        }
        memberAppointmentMapper.updateByPrimaryKey(memberAppointment);
        
        //发送同意预约通知给会员
        String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
        if (StringUtils.isNotBlank(openId)) {
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
            String url = App.System.SERVER_BASE_URL 
                    + Url.MemberCenter.VIEW_APPOINTMENT_LIST.replace("{storeId}", storeId + "").replace("{businessType}", "1");
            if (type == 2) {
                url = App.System.SERVER_BASE_URL 
                        + Url.MemberCenter.VIEW_ORDER_APPOINTMENT.replace("{storeId}", storeId + "").replace("{businessType}", "1");
            }
            rabbitService.sendAppointmentResultNotice(type, storeId, url, openId, 
                    memberInfo.getName(), memberInfo.getLevelName(), projectName, appointTime, reason);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 启动预约
    * @author 王大爷
    * @date 2015年11月4日 上午11:30:59
    * @param appointmentId 预约标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    public BaseDto startAppoint(Integer appointmentId, Integer storeId, Integer lastOperatorId){
        
        MemberAppointment memberAppointment = memberAppointmentMapper.selectByPrimaryKey(appointmentId);
        //查询会员基本信息
        MemberBaseDto memberBaseDto = memberInfoService.getMemberBaseInfo(memberAppointment.getMemberId());
        
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(memberAppointment.getProjectId());
        
        //查询预约员工的轮牌员工标识
        Map<String, Integer> shiftMap = new HashMap<String, Integer>();
        shiftMap.put("projectId", memberAppointment.getProjectId());
        shiftMap.put("num", memberAppointment.getProjectStepOrder());
        shiftMap.put("employeesId", memberAppointment.getEmployeeId());
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftEmployeeListByEmployeeId(shiftMap);
        
        //组装预约员工
        List<Map<String, Object>> alist = new ArrayList<Map<String, Object>>();
        Map<String, Object> amap = new HashMap<String, Object>();
        amap.put("projectNum", 0);
        amap.put("orderNum", memberAppointment.getProjectStepOrder() - 1);
        amap.put("projectId", memberAppointment.getProjectId());
        amap.put("shiftMahjongEmployeeId", shiftMahjongEmployee.getShiftMahjongEmployeeId());
        amap.put("employeeId", memberAppointment.getEmployeeId());
        amap.put("isType", 1);
        alist.add(amap);
        
        //组装预约项目
        List<Map<String, Object>> blist = new ArrayList<Map<String, Object>>();
        Map<String, Object> bmap = new HashMap<String, Object>();
        bmap.put("orderType", 1);
        bmap.put("projectId", projectInfo.getProjectId());
        bmap.put("projectName", projectInfo.getProjectName());
        bmap.put("projectPriceStr", projectInfo.getProjectPrice());
        bmap.put("projectCount", 1);
        bmap.put("projectImage", projectInfo.getProjectImage());
        blist.add(bmap);
        
        //开单
        BaseDto dto = staffService.addOrder(JSONArray.fromObject(blist).toString(), JSONArray.fromObject(alist).toString(), 
                JSONObject.fromObject(memberBaseDto).toString(), storeId, lastOperatorId, 1, null);
        //修改预约单状态
        MemberAppointment record = new MemberAppointment();
        record.setAppointmentId(appointmentId);
        record.setServiceTime(DateUtil.getCurTime());
        record.setAppointmentStatus(3);
        memberAppointmentMapper.updateByPrimaryKey(record);
        
        return dto;
    }
    
    
    /**
     * 我的业绩
    * @author 王大爷
    * @date 2015年11月16日 下午7:49:25
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView staffEarning(Integer employeeId) {
        
        ModelAndView mav = new ModelAndView(View.StaffPage.STAFF_ERANING);
        
        String beginTime = null;
        String endTime = null;
        
        beginTime = DateUtil.getCurDate();
        endTime = DateUtil.getCurDate();

        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("employeeId", employeeId);
        commissionMap.put("beginTime", beginTime);
        commissionMap.put("endTime", endTime);
        
        //计算今日提成金额
        BigDecimal commissionValue = employeeCommissionMapper.selectBySectionDayCommission(commissionMap);
        
        mav.addObject("commissionValue", commissionValue);
        
        //今天
        List<Integer> projectOrderTypeList = new ArrayList<Integer>();
        projectOrderTypeList.add(1);
        Map<String, Object> projectCalculateMap = commissionMap;
        projectCalculateMap.put("orderTypeList", projectOrderTypeList);
        //劳动业绩
        BigDecimal toDayProjectCalculate = employeeCommissionMapper.selectBySectionDayCalculate(projectCalculateMap);
        
        mav.addObject("toDayProjectCalculate", toDayProjectCalculate);
        
        //指定业绩
        BigDecimal toDayAssignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(commissionMap);
        
        mav.addObject("toDayAssignProjectCalculate", toDayAssignProjectCalculate);
        
        List<Integer> comboOrderTypeList = new ArrayList<Integer>();
        comboOrderTypeList.add(3);
        Map<String, Object> comboCalculateMap = commissionMap;
        comboCalculateMap.put("orderTypeList", comboOrderTypeList);
        //套餐业绩
        BigDecimal toDayComboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(comboCalculateMap);
        
        mav.addObject("toDayComboCalculate", toDayComboCalculate);
        
        List<Integer> goodsOrderTypeList = new ArrayList<Integer>();
        goodsOrderTypeList.add(2);
        Map<String, Object> goodsCalculateMap = commissionMap;
        goodsCalculateMap.put("orderTypeList", goodsOrderTypeList);
        //商品业绩
        BigDecimal toDayGoodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(goodsCalculateMap);
        
        mav.addObject("toDayGoodsCalculate", toDayGoodsCalculate);
        
        List<Integer> chargeOrderTypeList = new ArrayList<Integer>();
        chargeOrderTypeList.add(4);
        chargeOrderTypeList.add(5);
        chargeOrderTypeList.add(6);
        Map<String, Object> chargeCalculateMap = commissionMap;
        chargeCalculateMap.put("orderTypeList", chargeOrderTypeList);
        //开卡充值业绩
        BigDecimal toDayChargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(chargeCalculateMap);
        
        mav.addObject("toDayChargeCalculate", toDayChargeCalculate);
        
        //昨天
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dates = df.format(DateUtil.getDateDaysBefore(new Date(), 1));
        beginTime = dates;
        endTime = dates;
        
        Map<String, Object> yesterdayCommissionMap = new HashMap<String, Object>();
        yesterdayCommissionMap.put("employeeId", employeeId);
        yesterdayCommissionMap.put("beginTime", beginTime);
        yesterdayCommissionMap.put("endTime", endTime);
        
        List<Integer> yesterdayProjectOrderTypeList = new ArrayList<Integer>();
        yesterdayProjectOrderTypeList.add(1);
        Map<String, Object> yesterdayProjectCalculateMap = yesterdayCommissionMap;
        yesterdayProjectCalculateMap.put("orderTypeList", yesterdayProjectOrderTypeList);
        //劳动业绩
        BigDecimal yesterdayProjectCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayProjectCalculateMap);
        
        mav.addObject("yesterdayProjectCalculate", yesterdayProjectCalculate);
        
        //指定业绩
        BigDecimal yesterdayAssignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(yesterdayCommissionMap);
        
        mav.addObject("yesterdayAssignProjectCalculate", yesterdayAssignProjectCalculate);
        
        List<Integer> yesterdayComboOrderTypeList = new ArrayList<Integer>();
        yesterdayComboOrderTypeList.add(3);
        Map<String, Object> yesterdayComboCalculateMap = yesterdayCommissionMap;
        yesterdayComboCalculateMap.put("orderTypeList", yesterdayComboOrderTypeList);
        //套餐业绩
        BigDecimal yesterdayComboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayComboCalculateMap);
        
        mav.addObject("yesterdayComboCalculate", yesterdayComboCalculate);
        
        List<Integer> yesterdayGoodsOrderTypeList = new ArrayList<Integer>();
        yesterdayGoodsOrderTypeList.add(2);
        Map<String, Object> yesterdayGoodsCalculateMap = yesterdayCommissionMap;
        yesterdayGoodsCalculateMap.put("orderTypeList", yesterdayGoodsOrderTypeList);
        //商品业绩
        BigDecimal yesterdayGoodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayGoodsCalculateMap);
        
        mav.addObject("yesterdayGoodsCalculate", yesterdayGoodsCalculate);
        
        List<Integer> yesterdayChargeOrderTypeList = new ArrayList<Integer>();
        yesterdayChargeOrderTypeList.add(4);
        yesterdayChargeOrderTypeList.add(5);
        yesterdayChargeOrderTypeList.add(6);
        Map<String, Object> yesterdayChargeCalculateMap = yesterdayCommissionMap;
        yesterdayChargeCalculateMap.put("orderTypeList", yesterdayChargeOrderTypeList);
        
        //开卡充值业绩
        BigDecimal yesterdayChargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(yesterdayChargeCalculateMap);
        
        mav.addObject("yesterdayChargeCalculate", yesterdayChargeCalculate);
        
        //本月
        beginTime = DateUtil.getMinMonthDateStr();
        endTime = DateUtil.getMaxMonthDateStr();
        
        Map<String, Object> monthCommissionMap = new HashMap<String, Object>();
        monthCommissionMap.put("employeeId", employeeId);
        monthCommissionMap.put("beginTime", beginTime);
        monthCommissionMap.put("endTime", endTime);
        
        //计算今日提成金额
        BigDecimal monthCommissionValue = employeeCommissionMapper.selectBySectionDayCommission(monthCommissionMap);
        
        mav.addObject("monthCommissionValue", monthCommissionValue);
        
        List<Integer> monthProjectOrderTypeList = new ArrayList<Integer>();
        monthProjectOrderTypeList.add(1);
        Map<String, Object> monthProjectCalculateMap = monthCommissionMap;
        monthProjectCalculateMap.put("orderTypeList", monthProjectOrderTypeList);
        //劳动业绩
        BigDecimal monthProjectCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthProjectCalculateMap);
        
        mav.addObject("monthProjectCalculate", monthProjectCalculate);
        
        //指定业绩
        BigDecimal monthAssignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(monthCommissionMap);
        
        mav.addObject("monthAssignProjectCalculate", monthAssignProjectCalculate);
        
        List<Integer> monthComboOrderTypeList = new ArrayList<Integer>();
        monthComboOrderTypeList.add(3);
        Map<String, Object> monthComboCalculateMap = monthCommissionMap;
        monthComboCalculateMap.put("orderTypeList", monthComboOrderTypeList);
        //套餐业绩
        BigDecimal monthComboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthComboCalculateMap);
        
        mav.addObject("monthComboCalculate", monthComboCalculate);
        
        List<Integer> monthGoodsOrderTypeList = new ArrayList<Integer>();
        monthGoodsOrderTypeList.add(2);
        Map<String, Object> monthGoodsCalculateMap = monthCommissionMap;
        monthGoodsCalculateMap.put("orderTypeList", monthGoodsOrderTypeList);
        //商品业绩
        BigDecimal monthGoodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthGoodsCalculateMap);
        
        mav.addObject("monthGoodsCalculate", monthGoodsCalculate);
        
        List<Integer> monthChargeOrderTypeList = new ArrayList<Integer>();
        monthChargeOrderTypeList.add(4);
        monthChargeOrderTypeList.add(5);
        monthChargeOrderTypeList.add(6);
        Map<String, Object> monthChargeCalculateMap = monthCommissionMap;
        monthChargeCalculateMap.put("orderTypeList", monthChargeOrderTypeList);
        
        //开卡充值业绩
        BigDecimal monthChargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(monthChargeCalculateMap);
        
        mav.addObject("monthChargeCalculate", monthChargeCalculate);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        EmployeeObjective selectObjective = new EmployeeObjective();
        selectObjective.setEmployeeId(employeeId);
        selectObjective.setObjectiveMonth(dateFormat.format(new Date()));
        //查询单月目标
        EmployeeObjective employeeObjective = employeeObjectiveMapper.getObjectiveInfo(selectObjective);
        if (employeeObjective != null) {
            BigDecimal totalProjectCalculate = employeeObjective.getTotalProjectSales();
            mav.addObject("totalProjectCalculate", totalProjectCalculate);
            BigDecimal totalAssignProjectCalculate = employeeObjective.getAssignProjectSales();
            mav.addObject("totalAssignProjectCalculate", totalAssignProjectCalculate);
            BigDecimal totalComboCalculate = employeeObjective.getComboSales();
            mav.addObject("totalComboCalculate", totalComboCalculate);
            BigDecimal totalGoodsCalculate = employeeObjective.getGoodsSales();
            mav.addObject("totalGoodsCalculate", totalGoodsCalculate);
            BigDecimal totalChargeCalculate = employeeObjective.getChargeSales();
            mav.addObject("totalChargeCalculate", totalChargeCalculate);
        }
        else {
            mav.addObject("totalProjectCalculate", 0);
            mav.addObject("totalAssignProjectCalculate", 0);
            mav.addObject("totalComboCalculate", 0);
            mav.addObject("totalGoodsCalculate", 0);
            mav.addObject("totalChargeCalculate", 0);
        }
        
        List<EmployeeObjective> employeeObjectiveList = employeeObjectiveMapper.selectObjectiveByEmployeeId(employeeId);
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        for (int i = 0; i < employeeObjectiveList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            EmployeeObjective obj = employeeObjectiveList.get(i);
            //劳动业绩
            map.put("totalProjectSales", obj.getTotalProjectSales());
            map.put("actualTotalProjectSales", obj.getActualTotalProjectSales());
            
            BigDecimal zore = new BigDecimal(0);
            
            if (obj.getTotalProjectSales().compareTo(zore) <= 0) {
                map.put("projectScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualTotalProjectSales().divide(obj.getTotalProjectSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //指定业绩
            map.put("assignProjectSales", obj.getAssignProjectSales());
            map.put("actualAssignProjectSales", obj.getActualAssignProjectSales());
            if (obj.getAssignProjectSales().compareTo(zore) <= 0)  {
                map.put("assignProjectScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualAssignProjectSales().divide(obj.getAssignProjectSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //套餐销售业绩
            map.put("comboSales", obj.getComboSales());
            map.put("actualComboSales", obj.getActualComboSales());
            if (obj.getComboSales().compareTo(zore) <= 0) {
                map.put("comboScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualComboSales().divide(obj.getComboSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //商品销售业绩
            map.put("goodsSales", obj.getGoodsSales());
            map.put("actualGoodsSales", obj.getActualGoodsSales());
            if (obj.getGoodsSales().compareTo(zore) <= 0) {
                map.put("goodsScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualGoodsSales().divide(obj.getGoodsSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            //卡项销售业绩
            map.put("chargeSales", obj.getChargeSales());
            map.put("actualChargeSales", obj.getActualChargeSales());
            if (obj.getChargeSales().compareTo(zore) <= 0) {
                map.put("chargeScale", "--");
            }
            else {
                map.put("projectScale", obj.getActualChargeSales().divide(obj.getChargeSales(), BigDecimal.ROUND_UP, 2)
                        .multiply(new BigDecimal(100)));
            }
            
            map.put("dates", obj.getObjectiveMonth());
            list.add(map);
        }
        
        mav.addObject("listStr", JSONArray.fromObject(list).toString());
        
        return mav;
    }
    
    /**
     * 业绩排行
    * @author 王大爷
    * @date 2015年8月19日 下午2:31:48
    * @param employeeId 员工id
    * @return ModelAndView
     */
    public ModelAndView allEarning(Integer employeeId){
        ModelAndView mav = new ModelAndView();
        
        List<Integer> idList = employeeInfoMapper.selectEmployeeInfoByEmployeeIdPositionId(employeeId);
        
        String toDayBeginTime = DateUtil.getCurDate();
        String toDayEndTime = DateUtil.getCurDate();
        
        List<Map<String, Object>> toDayList= selectListMap(idList, toDayBeginTime, toDayEndTime);
        
        mav.addObject("toDayList", toDayList);
        
        Map<String, Object> toDayMap = selectTotalValue(idList, toDayBeginTime, toDayEndTime);
        
        mav.addObject("toDayTotalProjectScale", toDayMap.get("totalProjectScale"));
        mav.addObject("toDayTotalAssignProjectScale", toDayMap.get("totalAssignProjectScale"));
        mav.addObject("toDayTotalComboScale", toDayMap.get("totalComboScale"));
        mav.addObject("toDayTotalGoodsScale", toDayMap.get("totalGoodsScale"));
        mav.addObject("toDayTotalChargeScale", toDayMap.get("totalChargeScale"));
        mav.addObject("toDayTotalScale", toDayMap.get("totalScale"));
        
        
        String weekBeginTime = DateUtil.getCurrentMondayStr();
        String weekEndTime = DateUtil.getPreviousSundayStr();
        
        List<Map<String, Object>> weekList= selectListMap(idList, weekBeginTime, weekEndTime);
        
        mav.addObject("weekList", weekList);
        
        Map<String, Object> weekMap = selectTotalValue(idList, weekBeginTime, weekEndTime);
        
        mav.addObject("weekTotalProjectScale", weekMap.get("totalProjectScale"));
        mav.addObject("weekTotalAssignProjectScale", weekMap.get("totalAssignProjectScale"));
        mav.addObject("weekTotalComboScale", weekMap.get("totalComboScale"));
        mav.addObject("weekTotalGoodsScale", weekMap.get("totalGoodsScale"));
        mav.addObject("weekTotalChargeScale", weekMap.get("totalChargeScale"));
        mav.addObject("weekTotalScale", weekMap.get("totalScale"));
        
        String monthBeginTime = DateUtil.getMinMonthDateStr();
        String monthEndTime = DateUtil.getMaxMonthDateStr();
        
        List<Map<String, Object>> monthList= selectListMap(idList, monthBeginTime, monthEndTime);
        
        mav.addObject("monthList", monthList);
        
        Map<String, Object> monthMap = selectTotalValue(idList, monthBeginTime, monthEndTime);
        
        mav.addObject("monthTotalProjectScale", monthMap.get("totalProjectScale"));
        mav.addObject("monthTotalAssignProjectScale", monthMap.get("totalAssignProjectScale"));
        mav.addObject("monthTotalComboScale", monthMap.get("totalComboScale"));
        mav.addObject("monthTotalGoodsScale", monthMap.get("totalGoodsScale"));
        mav.addObject("monthTotalChargeScale", monthMap.get("totalChargeScale"));
        mav.addObject("monthTotalScale", monthMap.get("totalScale"));
        
        mav.addObject("employeeId", employeeId);
        
        mav.setViewName(View.StaffPage.ALL_ERANING);
        return mav;
    }
    
    /**
     * 查询员工对应业绩集合
    * @author 王大爷
    * @date 2015年11月19日 下午5:11:59
    * @param idList 员工标识集合
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> selectListMap (List<Integer> idList, String beginTime, String endTime) {
        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("employeeIdList", idList);
        commissionMap.put("beginTime", beginTime);
        commissionMap.put("endTime", endTime);
        
        List<Integer> projectOrderTypeList = new ArrayList<Integer>();
        projectOrderTypeList.add(1);
        Map<String, Object> projectCalculateMap = commissionMap;
        projectCalculateMap.put("orderTypeList", projectOrderTypeList);
        
        List<Map<String, Object>> projectCommission = employeeCommissionMapper.selectByEmployeeIdList(projectCalculateMap);
        
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        List<Integer> projectList = new ArrayList<Integer>(idList);
        
        for (int i = 0; i < projectCommission.size(); i++) {
            Map<String, Object> objMap = projectCommission.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            String employeeId = objMap.get("employeeId").toString();
            String projectValue = objMap.get("commissionCalculate").toString();
            
            EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(Integer.parseInt(employeeId));
            map.put("employeeId", employeeId);
            map.put("name", employeeInfo.getName());
            map.put("employeeCode", employeeInfo.getEmployeeCode());
            map.put("headImage", employeeInfo.getHeadImage());
            map.put("projectValue", projectValue);
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("employeeId", employeeId);
            parameterMap.put("beginTime", beginTime);
            parameterMap.put("endTime", endTime);
            
            
            map = selectValue(parameterMap, map);
            
            projectList.remove(Integer.valueOf(employeeId));
            
            list.add(map);
        }
        
        //当没有查询出数据，赋值为0.00
        for (int i = 0; i < projectList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            
            EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(projectList.get(i));
            map.put("employeeId", projectList.get(i));
            map.put("name", employeeInfo.getName());
            map.put("employeeCode", employeeInfo.getEmployeeCode());
            map.put("headImage", employeeInfo.getHeadImage());
            map.put("projectValue", "0.00");
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("employeeId", projectList.get(i));
            parameterMap.put("beginTime", beginTime);
            parameterMap.put("endTime", endTime);
            
            
            map = selectValue(parameterMap, map);
            
            list.add(map);
        }
        return list;
    }
    
    /**
     * 查询员工对应业绩平均值
    * @author 王大爷
    * @date 2015年11月19日 下午5:11:59
    * @param idList 员工标识集合
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return List<Map<String, Object>>
     */
    public Map<String, Object> selectTotalValue(List<Integer> idList, String beginTime, String endTime) {
        Integer num = idList.size();
        BigDecimal numDecimal = new BigDecimal(num);
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        Map<String, Object> commissionMap = new HashMap<String, Object>();
        commissionMap.put("employeeIdList", idList);
        commissionMap.put("beginTime", beginTime);
        commissionMap.put("endTime", endTime);
        
        List<Integer> projectOrderTypeList = new ArrayList<Integer>();
        projectOrderTypeList.add(1);
        Map<String, Object> projectCalculateMap = commissionMap;
        projectCalculateMap.put("orderTypeList", projectOrderTypeList);
        
        BigDecimal totalProject = employeeCommissionMapper.selectByTotalEmployeeIdList(projectCalculateMap);
        
        BigDecimal totalProjectScale = totalProject.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalProjectScale", totalProjectScale);
        
        BigDecimal totalAssignProject = employeeCommissionMapper.selectByTotalSectionAssignProjectCalculate(commissionMap);
        
        BigDecimal totalAssignProjectScale = totalAssignProject.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalAssignProjectScale", totalAssignProjectScale);
        
        if (totalProjectScale.compareTo(BigDecimal.ZERO) <= 0) {
            map.put("totalScale", "0.00");
        }
        else {
            map.put("totalScale", totalAssignProjectScale.divide(totalProjectScale, BigDecimal.ROUND_UP, 2));
        }
        
        List<Integer> comboOrderTypeList = new ArrayList<Integer>();
        comboOrderTypeList.add(3);
        Map<String, Object> comboCalculateMap = commissionMap;
        comboCalculateMap.put("orderTypeList", comboOrderTypeList);
        
        BigDecimal totalCombo = employeeCommissionMapper.selectByTotalEmployeeIdList(comboCalculateMap);
        
        BigDecimal totalComboScale = totalCombo.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalComboScale", totalComboScale);
        
        List<Integer> goodsOrderTypeList = new ArrayList<Integer>();
        goodsOrderTypeList.add(2);
        Map<String, Object> goodsCalculateMap = commissionMap;
        goodsCalculateMap.put("orderTypeList", goodsOrderTypeList);
        
        BigDecimal totalGoods = employeeCommissionMapper.selectByTotalEmployeeIdList(goodsCalculateMap);
        
        BigDecimal totalGoodsScale = totalGoods.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalGoodsScale", totalGoodsScale);
        
        List<Integer> chargeOrderTypeList = new ArrayList<Integer>();
        chargeOrderTypeList.add(4);
        chargeOrderTypeList.add(5);
        chargeOrderTypeList.add(6);
        Map<String, Object> chargeCalculateMap = commissionMap;
        chargeCalculateMap.put("orderTypeList", chargeOrderTypeList);
        
        BigDecimal totalCharge = employeeCommissionMapper.selectByTotalEmployeeIdList(chargeCalculateMap);
        
        BigDecimal totalChargeScale = totalCharge.divide(numDecimal, BigDecimal.ROUND_UP, 2);
        
        map.put("totalChargeScale", totalChargeScale);
        
        return map;        
    }
    
    /**
     * 查询除劳动业绩外所有业绩
    * @author 王大爷
    * @date 2015年11月19日 下午4:43:08
    * @param commissionMap 时间人员标识
    * @param map map
    * @return Map<String, Object>
     */
    public Map<String, Object> selectValue(Map<String, Object> commissionMap, Map<String, Object> map) {
        //指定业绩
        BigDecimal assignProjectCalculate = employeeCommissionMapper.selectBySectionAssignProjectCalculate(commissionMap);
        
        map.put("assignProjectValue", assignProjectCalculate);
        
        BigDecimal projectValue = new BigDecimal(map.get("projectValue").toString());
        
        if (projectValue.compareTo(BigDecimal.ZERO) <= 0) {
            map.put("scale", "0.00");
        }
        else {
            map.put("scale", assignProjectCalculate.divide(projectValue, BigDecimal.ROUND_UP, 2));
        }
        
        List<Integer> comboOrderTypeList = new ArrayList<Integer>();
        comboOrderTypeList.add(3);
        Map<String, Object> comboCalculateMap = commissionMap;
        comboCalculateMap.put("orderTypeList", comboOrderTypeList);
        //套餐业绩
        BigDecimal comboCalculate = employeeCommissionMapper.selectBySectionDayCalculate(comboCalculateMap);
        
        map.put("comboValue", comboCalculate);
                
        List<Integer> goodsOrderTypeList = new ArrayList<Integer>();
        goodsOrderTypeList.add(2);
        Map<String, Object> goodsCalculateMap = commissionMap;
        goodsCalculateMap.put("orderTypeList", goodsOrderTypeList);
        //商品业绩
        BigDecimal goodsCalculate = employeeCommissionMapper.selectBySectionDayCalculate(goodsCalculateMap);
        
        map.put("goodsValue", goodsCalculate);
                
        List<Integer> chargeOrderTypeList = new ArrayList<Integer>();
        chargeOrderTypeList.add(4);
        chargeOrderTypeList.add(5);
        chargeOrderTypeList.add(6);
        Map<String, Object> chargeCalculateMap = commissionMap;
        chargeCalculateMap.put("orderTypeList", chargeOrderTypeList);
        //开卡充值业绩
        BigDecimal chargeCalculate = employeeCommissionMapper.selectBySectionDayCalculate(chargeCalculateMap);
        
        map.put("chargeValue", chargeCalculate);
        
        return map;
    }
    
    /**
     * 我的轮牌
    * @author 王大爷
    * @date 2015年11月23日 上午11:13:03
    * @param employeeId 员工标识
    * @return ModelAndView
     */
    public ModelAndView myShiftMahjong(Integer employeeId){
        ModelAndView mav =  new ModelAndView(View.StaffPage.MY_SHIFTMAHJONG);
        List<ShiftMahjongDto> shiftMahjongDtoList = shiftMahjongMapper.selectByEmployeeId(employeeId);
        mav.addObject("shiftMahjongDtoList", shiftMahjongDtoList);
        return mav;
    }
}
