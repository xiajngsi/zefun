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
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.service.StoreManageRuleService;

/**
 * 门店制度管理控制类
* @author 张进军
* @date Dec 5, 2015 6:24:57 PM 
*/
@Controller
public class StoreManageRuleController extends BaseController {

    /**门店制度管理对象*/
    @Autowired
    private StoreManageRuleService storeManageRuleService;
    
    
    /**
     * 查看管理制度主页
    * @author 张进军
    * @date Dec 5, 2015 7:15:47 PM
    * @param request    请求对象
    * @return   管理制度主页
     */
    @RequestMapping(value = Url.StoreManageRule.VIEW_HOME)
    public ModelAndView homeView(HttpServletRequest request){
        int storeId = getStoreId(request);
        return storeManageRuleService.homeView(storeId);
    }
    
    
    /**
     * 修改规则信息
    * @author 张进军
    * @date Dec 5, 2015 11:56:19 PM
    * @param storeManageRule    规则信息
    * @return   成功返回码为0，失败为其他异常信息
     */
    @RequestMapping(value = Url.StoreManageRule.ACTION_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateAction(StoreManageRule storeManageRule){
        return storeManageRuleService.updateAction(storeManageRule);
    }
}
