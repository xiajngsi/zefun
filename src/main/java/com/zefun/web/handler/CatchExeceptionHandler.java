package com.zefun.web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.exception.ResourceNotFoundException;
import com.zefun.common.exception.ServiceException;

/**
 * 所有抛出的异常处理类
 */
@Component
public class CatchExeceptionHandler implements HandlerExceptionResolver {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(CatchExeceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /**
         * 如果为自定义异常类，单独进行处理
         */
        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        String msg = "";
        if (ex instanceof ServiceException) {
            ServiceException se = (ServiceException) ex;
            status = se.getCode();
            msg = se.getMsg();
            logger.error(se);
        }
        else {
            logger.error("system exception: ", ex);
            if (ex instanceof ResourceNotFoundException) {
                status = HttpServletResponse.SC_NOT_FOUND;
            }
        }

        String ajaxHeader = request.getHeader("isAjax");
        boolean isAjax = ajaxHeader != null && "1".equals(ajaxHeader);
        if (isAjax) {
            response.setStatus(status);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(msg);
                pw.flush();
                pw.close();
            }
            catch (IOException e) {
                
            }
            return new ModelAndView();
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("status", status);
        mav.addObject("tip", msg);
        mav.setViewName("error/500");
        return mav;
    }

}
