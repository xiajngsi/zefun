package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.AppointManageService;

/**
 * 预约管理控制类
* @author 张进军
* @date Nov 23, 2015 9:25:48 PM 
*/
@Controller
public class AppointManageController extends BaseController {

    /** 预约管理服务对象 */
    @Autowired
    private AppointManageService appointManageService;
    
    
    /**
     * 查看预约列表
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param request    请求对象
    * @return   预约列表
     */
    @RequestMapping(value = Url.AppointManage.VIEW_APPOINT_LIST, method = RequestMethod.GET)
    public ModelAndView appointListView(HttpServletRequest request){
        int storeId = getStoreId(request);
        return appointManageService.appointListView(storeId);
    }
    
    
    /**
     * 分页查询某个门店的预约信息
    * @author 张进军
    * @date Aug 5, 2015 7:58:53 PM
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @param request    请求对象
    * @return           预约列表
     */
    @RequestMapping(value = Url.AppointManage.ACTION_APPOINT_LIST, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listAction(int pageNo, int pageSize, HttpServletRequest request){
        int storeId = getStoreId(request);
        return appointManageService.listAction(storeId, pageNo, pageSize);
    }
    
    
    /**
     * 取消预约
    * @author 张进军
    * @date Aug 5, 2015 7:58:53 PM
    * @param appointmentId  预约标识
    * @return           预约列表
     */
    @RequestMapping(value = Url.AppointManage.ACTION_APPOINT_CANCEL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto cancelAction(int appointmentId){
        return appointManageService.cancelAction(appointmentId);
    }
    
}
