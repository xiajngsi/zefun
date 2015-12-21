package com.zefun.web.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.service.RedisService;

/**
 * 权限拦截器
* @author 高国藩
* @date 2015年9月19日 上午11:36:48
 */
public class AuthorityInterceptor implements HandlerInterceptor{

    /**
     * 过滤的url表达式
     */
    private String[] allowUrlPatterns;

    /**
     * redis操作对象
     */
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getServletPath().toString();
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

        //过滤非拦截接口
        for (String url : allowUrlPatterns) {
            if (Pattern.matches("^" + url + "$", requestUrl)) {
                return true;
            }
        }

        Object userInfo = request.getSession().getAttribute(App.Session.USER_INFO);
        //未登陆或者登陆超时
        if (userInfo == null){
            writeNoLoginResult(request, response, App.System.ERROR_CODE_SESSION_INVALID);
            return false;
        }

        //检查权限
        EmployeeBaseDto employeeInfo = (EmployeeBaseDto) userInfo;
        String roleId = redisService.hget(App.Redis.PC_USER_ID_ROLE_HASH, employeeInfo.getEmployeeId());
        if ("100".equals(roleId)){
            return true;
        }
        
        Set<String> set = redisService.smembers(App.Redis.AUTHORITY_ACCESS_SET_ROLE_PREFIX + roleId);
        List<String> authorUrl = new ArrayList<String>(set);
        //如果不拥有该权限
        if (authorUrl.isEmpty() || !authorUrl.contains(requestPath)){
            writeNoLoginResult(request, response, App.System.ERROR_CODE_FORBIDDEN);
            return false;
        }
        return true;
    }


    /***
     * 未登录时的处理
    * @author 张进军
    * @date Nov 1, 2015 2:22:44 AM
    * @param request    请求对象
    * @param response   响应对象
    * @param code       错误码
    * @throws IOException   异常
     */
    private void writeNoLoginResult(HttpServletRequest request, HttpServletResponse response, int code)
            throws IOException {
        String ajaxHeader = request.getHeader("isAjax");
        if (ajaxHeader != null && "1".equals(ajaxHeader)) {
            response.setStatus(code);
            response.flushBuffer();
            return;
        }
        response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/");
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void setAllowUrlPatterns(String[] allowUrlPatterns) {
        this.allowUrlPatterns = allowUrlPatterns;
    }
}
