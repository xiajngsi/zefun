package com.zefun.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.log.AccessLog;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.RedisService;

/**
 * 拦截需要登录的操作请求，检查Token
 * 自动延长Token时长
 * @author sfit0254
 * @date 2014-4-26
 */
public class SessionInterceptor implements HandlerInterceptor {

    /**
     * 日志对象
     */
    private Logger logger = Logger.getLogger(SessionInterceptor.class);

    /**
     * 过滤的url表达式
     */
    private String[] allowUrlPatterns;
    
    /** redis操作服务类 */
    @Autowired
    private RedisService redisService;

    /**
     * @param request request
     * @param response response
     * @param arg2 arg2
     * @throws Exception e
     * @return boolean
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String token = request.getHeader("token");
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

        JSONObject json = new JSONObject();
        json.put("api_name", requestUrl);
        json.put("api_params", getRequstParams(request)); // 浏览器信息(APP客户端忽略)
        json.put("token", token);
        
        json.put("client_version", request.getHeader("client_version")); // 客户端版本号
        json.put("client_ip", StringUtil.getIpAddr(request)); //客户端ip
        
        json.put("os_name", request.getHeader("os_name")); // 系统名称（0：android，1：IOS，2：其他）
        json.put("os_version", request.getHeader("os_version")); // 系统版本
        json.put("device_vendor", request.getHeader("device_vendor")); // 设备生产商
        json.put("device_name", request.getHeader("device_name")); // 设备型号
        json.put("device_imei", request.getHeader("device_imei")); // 设备唯一标识号
        json.put("device_language", request.getHeader("device_language")); // 设备语言
        
        json.put("screen_size", request.getHeader("screen_size")); // 硬件设备分辨率
        json.put("network_info", request.getHeader("network_info")); // 硬件设备网络状态
        
        json.put("user_agent", request.getHeader("user-agent")); // 浏览器信息(APP客户端忽略)
        json.put("receive_time", DateUtil.getCurTime()); // 浏览器信息(APP客户端忽略)

        AccessLog.log(logger, "request info : " + json.toString());

        for (String url : allowUrlPatterns) {
            if (Pattern.matches("^" + url + "$", requestUrl)) {
                return true;
            }
        }
        
        if (Url.App.LOGIN.equals(requestUrl)) {
            return true;
        }
        
        //如果为APP接口，校验其token
        if (requestUrl.startsWith("/app/") && !redisService.exists(token)) {
            printErr(response, 9001, "请重新登录...");
            return false;
        }
        
        return true;
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:59:09
    * @param request r
    * @return String
     */
    private String getRequstParams(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer("");
        @SuppressWarnings("unchecked")
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                sb.append(key + "=" + value + "&");
            }
        }
        if (sb.length() > 3) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    
    
    /**
     * 输出异常信息
    * @author 张进军
    * @date Sep 18, 2015 3:53:01 PM
    * @param response       输出对象
    * @param code           错误编码
    * @param msg            错误信息
    * @throws IOException   输出产生的异常
     */
    private void printErr(HttpServletResponse response, int code, String msg) throws IOException{
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        BaseDto dto = new BaseDto();
        dto.setCode(code);
        dto.setMsg(msg);
        out.print(JSONObject.fromObject(dto).toString());
        out.flush();
        out.close();
    }
    

    /**
     * @param arg0 arg0
     * @param arg1 arg1
     * @param arg2 arg2
     * @param arg3 arg3
     * @throws Exception 异常
     */
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    /**
     * @param arg0 arg0
     * @param arg1 arg1
     * @param arg2 arg2
     * @param arg3 arg3
     * @throws Exception 异常
     */
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    public void setAllowUrlPatterns(String[] allowUrlPatterns) {
        this.allowUrlPatterns = allowUrlPatterns;
    }
    
}