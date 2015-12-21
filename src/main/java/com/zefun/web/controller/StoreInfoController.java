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
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.service.StoreInfoService;

/**
 * 门店信息服务
* @author 张进军
* @date Nov 9, 2015 11:20:50 AM
 */
@Controller
public class StoreInfoController extends BaseController{
    
    /**门店信息服务对象*/
    @Autowired
    private StoreInfoService storeInfoService;
    
    
    /**
     * 进入店铺设置页面
    * @author 张进军
    * @date Nov 9, 2015 11:19:03 AM
    * @param request    请求对象
    * @return   店铺设置页面
     */
    @RequestMapping(value=Url.StoreInfo.VIEW_STORE_SETTING, method = RequestMethod.GET)
    public ModelAndView viewStoreSetting(HttpServletRequest request){
        return storeInfoService.storeSettingView(getStoreId(request));
    }
    
    
    /**
     * 进行店铺设置操作
    * @author 张进军
    * @date Nov 9, 2015 11:19:28 AM
    * @param storeInfo  店铺信息    
    * @param request    请求对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value=Url.StoreInfo.ACTION_STORE_SETTING, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto storeSettingAction(StoreInfo storeInfo, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        storeInfo.setStoreId(storeId);
        return storeInfoService.storeSettingAction(storeId, storeInfo);
    }
    
    
    /**
     * 新增门店
    * @author 张进军
    * @date Oct 29, 2015 11:45:28 AM
    * @param name       用户姓名
    * @param phone      门店电话
    * @param storeName  门店名称
    * @param storeType  门店类型(1:单店，2:连锁店总店，3:连锁店分店)
    * @param parentId   父级门店标识   
    * @return   成功返回码为0，失败为其它返回码
     */
    @RequestMapping(value = Url.StoreInfo.ACTION_ADD_STORE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addStoreInfo(String name, String phone, String storeName, Integer storeType, Integer parentId) {
        return storeInfoService.addStoreInfo(name, phone, storeName, storeType, parentId);
    }
}
