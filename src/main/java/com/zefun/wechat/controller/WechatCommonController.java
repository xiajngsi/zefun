package com.zefun.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.RabbitService;

/**
 * 一些通用的接口
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 */
@Controller
public class WechatCommonController extends BaseController {
    /** 消费队列服务对象 */
    @Autowired
    private RabbitService rabbitService;

    /**
     * 发送验证码
     * @author gebing
     * @date 2015年12月4日
     * @param phone 手机号
     * @param business 业务
     * @return 发送结果
     */
    @RequestMapping(value=Url.WechatCommon.VC, method = RequestMethod.POST)
    @ResponseBody 
    public BaseDto verifyCode(String phone, String business) {
        rabbitService.sendVerifyCode(App.System.WECHAT_ZEFUN_STORE_ID, phone, business);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
}
