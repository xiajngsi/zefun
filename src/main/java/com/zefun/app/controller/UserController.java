package com.zefun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.app.service.UserService;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;

/**
 * 用户基本模块控制类
* @author 张进军
* @date Sep 18, 2015 4:48:32 PM 
*/
@Controller
public class UserController {
    /** 用户基本模块服务对象 */
    @Autowired
    private UserService userService;
    
    
    /**
     * 用户登录
    * @author 张进军
    * @date Sep 18, 2015 4:50:00 PM
    * @param username       账号
    * @param password       密码
    * @param deviceType     设备类型(android、ios、other)
    * @param deviceToken    设备推送标识
    * @return           成功返回码0，返回值为用户角色、token、userid；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.App.LOGIN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto login(String username, String password, String deviceType, String deviceToken){
        return userService.login(username, password, deviceType, deviceToken);
    }
}
