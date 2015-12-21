package com.zefun.web.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.web.service.RedisService;

/**
 * 短信授权回调处理
* @author 张进军
* @date Sep 17, 2015 11:21:58 AM 
*/
@Controller
public class SmsAuthContoller {
    /** 日志处理 */
    private Logger logger = Logger.getLogger(SmsAuthContoller.class);
    
    /** redis操作对象 */
    @Autowired
    private RedisService redisService;
    
    
    /**
     * 短信授权回调方法
    * @author 张进军
    * @date Sep 23, 2015 4:56:54 PM
    * @param code           授权的临时代码
    * @param open_id        在api中的唯一标识
    * @param res_code       授权结果代码
    * @param res_message    授权结果内容
    * @param state          用于签名的随机字符
    * @return               授权处理成功返回
     */
    @RequestMapping(value = Url.Sms.AUTH_CALLBACK)
    @ResponseBody
    public String callback(String code, String open_id, String res_code, String res_message, String state){
        if (!res_code.equals("0")) {
            logger.error("189 login callback error: " + res_message);
            return "res_code: " + res_code + ", res_message: " + res_message;
        }
        logger.info("get 189 code: " + code);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("app_id", App.Sms.APP_ID);
        params.put("app_secret", App.Sms.APP_SECRET);
        params.put("redirect_uri", App.System.SERVER_BASE_URL + Url.Sms.AUTH_CALLBACK);
        params.put("state", "at");

        String res = HttpClientUtil.sendPostReq(App.Sms.ACCESS_TOKEN_URL, params, null);
        JSONObject resJson = JSONObject.fromObject(res);
        if (!resJson.get("res_code").toString().equals("0")) {
            return "get 189 access_token by code: " + code + " failed, res_code: " 
                    + resJson.get("res_code").toString() + ", res_message: " + resJson.getString("res_message");
        }
        String accessToken = resJson.getString("access_token");
        String refreshToken = resJson.getString("refresh_token");
        redisService.hset(App.Redis.SMS_SERVICE_KEY_HASH, App.Redis.SMS_ACCESS_TOKEN_KEY, accessToken);
        redisService.hset(App.Redis.SMS_SERVICE_KEY_HASH, App.Redis.SMS_REFRESH_TOKEN_KEY, refreshToken);
        
        return "success!";
    }
}
