package com.zefun.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
/**
 * 工资单
* @author chendb
* @date 2015年10月24日 下午2:56:28
 */
@Controller
public class PayrollController extends BaseController{
    /**
     * 
    * @author chendb
    * @date 2015年10月24日 上午11:27:50
    * @param request request
    * @param response response
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Businessanalysis.PAYROLL)
    public ModelAndView attendanceView(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        ModelAndView mav = new ModelAndView("businessanalysis/payroll/payroll");
        return mav;
    }

}
