package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeRewardDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;
import com.zefun.web.vo.EmployeeRewardVo;

/**
 * 门店制度管理服务类
* @author 张进军
* @date Dec 5, 2015 6:26:08 PM 
*/
@Service
public class StoreManageRuleService {
	
	/** 员工奖惩记录service */
    @Autowired
    private EmployeeRewardService employeeRewardService;
    
    /**门店管理制度操作对象*/
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;
    
    /** 员工奖惩映射 */
    @Autowired
    private EmployeeRewardMapper employeeRewardMapper;
    
    
    /**
     * 查看管理制度主页
    * @author 张进军
    * @date Dec 5, 2015 7:06:51 PM
    * @param storeId    门店标识
    * @return   管理制度主页
     */
    public ModelAndView homeView(int storeId){
        ModelAndView mav = new ModelAndView(View.StoreManageRule.RULE);
        List<StoreManageRule> list = storeManageRuleMapper.selectRuleListByStoreId(storeId);
        mav.addObject("ruleList", list);
        //考勤异常记录
        /*Page<EmployeeRewardDto> attendancePage = new Page<EmployeeRewardDto>();
        Map<String, Object> attendanceParams = new HashMap<String, Object>();
        attendanceParams.put("storeId", storeId);
        attendanceParams.put("rewardType", "ATTENDANCE");
        attendancePage.setParams(attendanceParams);
        List<EmployeeRewardDto> attendanceList = employeeRewardService.classify(employeeRewardMapper.selectCountReward(attendancePage));
        mav.addObject("attendanceList", attendanceList);*/
        Page<EmployeeRewardDto> pageOfAttendance = employeeRewardService.findCountEmployeeRewardByPage(
        		  new EmployeeRewardVo(1, App.System.API_DEFAULT_PAGE_SIZE, storeId, null, null, null, null, "ATTENDANCE", null));
        mav.addObject("pageOfAttendance", pageOfAttendance);
        //行为
        Page<EmployeeRewardDto> pageOfBehaviour = employeeRewardService.findCountEmployeeRewardByPage(
        		  new EmployeeRewardVo(1, App.System.API_DEFAULT_PAGE_SIZE, storeId, null, null, null, null, "BEHAVIOUR", null));
        mav.addObject("pageOfBehaviour", pageOfBehaviour);
        //服务
        Page<EmployeeRewardDto> pageOfService = employeeRewardService.findCountEmployeeRewardByPage(
        		  new EmployeeRewardVo(1, App.System.API_DEFAULT_PAGE_SIZE, storeId, null, null, null, null, "SERVICE", null));
        mav.addObject("pageOfService", pageOfService);
        return mav;
    }
    
    
    /**
     * 修改规则信息
    * @author 张进军
    * @date Dec 5, 2015 11:56:19 PM
    * @param storeManageRule    规则信息
    * @return   成功返回码为0，失败为其他异常信息
     */
    public BaseDto updateAction(StoreManageRule storeManageRule){
        storeManageRuleMapper.updateByPrimaryKey(storeManageRule);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
