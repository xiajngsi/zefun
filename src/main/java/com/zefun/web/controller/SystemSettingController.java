package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.service.SystemSettingService;

/**
 * 系统设置控制类
* @author 张进军
* @date Nov 20, 2015 7:05:45 PM 
*/
@Controller
public class SystemSettingController extends BaseController{
    
    /** 系统设置服务对象 */
    @Autowired
    private SystemSettingService systemSettingService;
    
    /**
     * 个人设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   个人设置页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_PERSON_SETTING)
    public ModelAndView personSettingView(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(View.Setting.PERSON_SETTING);
    }
    
    
    /**
     * 个人设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param employeeInfo   员工资料
    * @param request        请求对象
    * @param response       返回对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_PERSON_SETTING, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto personSettingAction(EmployeeInfo employeeInfo, 
            HttpServletRequest request, HttpServletResponse response){
        int userId = getUserId(request);
        employeeInfo.setEmployeeId(userId);
        return systemSettingService.personSettingAction(employeeInfo, request);
    }
    
    /**
     * 修改账户密码
    * @author 张进军
    * @date Nov 20, 2015 10:05:26 PM
    * @param oldPwd     原密码
    * @param newPwd     新密码
    * @param request        请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_UPDATE_PASSWORD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updatePwdAction(String oldPwd, String newPwd,
            HttpServletRequest request){
        int userId = getUserId(request);
        return systemSettingService.updatePwdAction(userId, oldPwd, newPwd);
    }
    
    
    /**
     * 访问门店基础设置页面
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_BASE_SETTING)
    public ModelAndView baseSettingView(HttpServletRequest request, HttpServletResponse response){
        int storeId = getStoreId(request);
        return systemSettingService.baseSettingView(storeId);
    }
    
    
    /**
     * 门店基础设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param request        请求对象
    * @param storeSetting   门店基础数据
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.SystemSetting.ACTION_BASE_SETTING, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto baseSettingAction(HttpServletRequest request, StoreSetting storeSetting){
        int storeId = getStoreId(request);
        storeSetting.setStoreId(storeId);
        return systemSettingService.baseSettingAction(storeSetting);
    }
    
    
    /**
     * 我的分店列表页面
    * @author 张进军
    * @date Dec 14, 2015 10:56:29 PM
    * @param request    请求对象
    * @return   分店列表页面
     */
    @RequestMapping(value = Url.SystemSetting.VIEW_BRANCH_LIST)
    public ModelAndView branchList(HttpServletRequest request){
        int storeId = getStoreId(request);
        return systemSettingService.branchList(storeId);
    }
}
