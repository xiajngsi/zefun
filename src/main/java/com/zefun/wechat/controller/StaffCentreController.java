package com.zefun.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.EmployeeAttendanceService;
import com.zefun.wechat.service.StaffCentreService;

/**
 * 移动端员工中心逻辑类
* @author 王大爷
* @date Oct 13, 2015 9:29:53 PM 
*/
@Controller
public class StaffCentreController extends BaseController{
    
    /** 员工中心*/
    @Autowired
    private StaffCentreService staffCentreService;
    
    /** 员工考勤服务对象 */
    @Autowired 
    private EmployeeAttendanceService employeeAttendanceService;
    
//    /**测试门店*/
//    private int storeId = 121;
//    /**测试员工*/
//    private int employeeId = 525;
    
    /**
     * 个人中心
    * @author 王大爷
    * @date 2015年10月18日 下午2:18:55
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_CENTER)
    public ModelAndView staffCenter(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffCenter(employeeId);
    }
    
    
    /**
     * 员工个人信息页面
    * @author 张进军
    * @date Dec 11, 2015 9:40:36 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工个人信息页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_INFO)
    public ModelAndView staffInfo(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffInfo(employeeId);
    }
    
    
    /**
     * 进入修改密码页面
    * @author 张进军
    * @date Dec 11, 2015 10:01:09 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   修改密码页面
     */
    @RequestMapping(value = Url.Staff.VIEW_UPDATE_PWD)
    public ModelAndView updatePwd(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return new ModelAndView(View.StaffPage.UPDATE_PWD);
    }
    
    
    /**
     * 修改员工密码
    * @author 张进军
    * @date Dec 11, 2015 9:56:32 PM
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码为0，失败为其他错误码
     */
    @RequestMapping(value = Url.Staff.ACTION_UPDATE_PWD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updatePwd(String oldPwd, String newPwd,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.updatePwd(employeeId, oldPwd, newPwd);
    }
    
    
    /**
     * 员工业绩排行
    * @author 张进军
    * @date Oct 28, 2015 7:56:49 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工业绩排行页面
     */
    @RequestMapping(value = Url.Staff.VIEW_ALL_EARNING)
    public ModelAndView allEarning(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.allEarning(employeeId);
    }
    
    
    /**
     * 员工更多页面
    * @author 张进军
    * @date Oct 28, 2015 7:56:49 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工更多页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_MORE)
    public ModelAndView more(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return new ModelAndView(View.StaffPage.STAFF_MORE);
    }
    
    
    /**
     * 员工个人业绩
    * @author 王大爷
    * @date Oct 28, 2015 7:56:49 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工个人业绩页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_EARNING)
    public ModelAndView staffEarning(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffEarning(employeeId);
    }
    
    /**
     * 我的轮牌
    * @author 王大爷
    * @date 2015年11月23日 上午10:36:42
    * @param request 请求对象
    * @param response 响应对象
    * @return 我的轮牌
     */
    @RequestMapping(value = Url.Staff.VIEW_MY_SHIFTMAHJONG)
    public ModelAndView myShiftMahjong(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.myShiftMahjong(employeeId);
    }
    
    /**
     * 员工个人预约列表
    * @author 张进军
    * @date Oct 28, 2015 7:56:49 PM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param type       预约类型(1:预约中，2:已确认，3:已取消)
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工个人预约页面
     */
    @RequestMapping(value = Url.Staff.VIEW_STAFF_APPOINT)
    public ModelAndView staffAppoint(@PathVariable int storeId, @PathVariable int businessType, @PathVariable int type,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        return staffCentreService.staffAppoint(employeeId, type);
    }
    
    
    /**
     * 同意/拒绝预约
    * @author 张进军
    * @date Nov 4, 2015 10:38:27 AM
    * @param type           操作类型(1:同意，2:拒绝)
    * @param appointmentId  预约标识
    * @param memberId       会员标识
    * @param projectName    项目名称
    * @param appointTime    预约时间
    * @param reason         取消原因
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.Staff.ACTION_APPOINT_OPERATE)
    @ResponseBody
    public BaseDto appointOperate(int type, int appointmentId, int memberId, String projectName, String appointTime, String reason,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreId(request);
        return staffCentreService.appointOperate(type, storeId, employeeId, appointmentId, memberId, projectName, appointTime, reason);
    }
    
    /**
     * 启动预约
    * @author 王大爷
    * @date 2015年11月4日 上午11:30:59
    * @param request 请求对象
    * @param response 响应对象
    * @param appointmentId 预约标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_START_APPOINT)
    @ResponseBody
    public BaseDto startAppoint(HttpServletRequest request, HttpServletResponse response, Integer appointmentId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId, 2);
        Integer employeeId = getUserIdByOpenId(openId);
        return staffCentreService.startAppoint(appointmentId, storeId, employeeId);
    }
    
    /**
     * 打卡操作，包括签到、签退
    * @author 张进军
    * @date Oct 28, 2015 6:10:05 PM
    * @param request 返回
    * @param response 请求
    * @return   打卡成功返回0；失败返回其它错误码。
     */
    @RequestMapping(value = Url.Staff.ACTION_SIGN_OPERATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto signOperate(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int storeId = getStoreIdByOpenId(openId, 2);
        return employeeAttendanceService.signOperate(storeId, employeeId);
    }
}
