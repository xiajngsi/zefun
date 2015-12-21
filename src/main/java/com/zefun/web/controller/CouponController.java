package com.zefun.web.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CouponInfoDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.service.CouponService;

/**
 * 优惠券请求类
* @author 高国藩
* @date 2015年9月14日 下午2:10:07
 */
@Controller
public class CouponController extends BaseController{
    
    /**
     * 
     */
    @Autowired
    private CouponService couponService;
   
    /**
     * 进入优惠券管理页面
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param request 请求封装
    * @return 跳转页面
     */
	@RequestMapping(value = Url.Coupon.VIEW_COUPONS, method = RequestMethod.GET)
	public ModelAndView viewCoupons(HttpServletRequest request){
	    Integer storeId = getStoreId(request);
		return couponService.viewCoupons(storeId);
	}
	
	/**
	 * 分页动态刷新table
	* @author 高国藩
	* @date 2015年10月11日 上午11:53:55
	* @param page 分页元素
	* @param request 请求封装
	* @return 数据返回
	 */
	@RequestMapping(value = Url.Coupon.VIEW_COUPONS_BY_PAGE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto viewCouponsByPage(Page<CouponInfoDto> page, HttpServletRequest request){
	    Integer storeId = getStoreId(request);
	    Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        page.setParams(params);
	    return couponService.viewCouponsByPage(page);
	}
	
    /**
     * 查询项目商品套餐信息
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param type 要查询的类型 1:项目 2:商品 3:套餐
    * @param request 请求封装
    * @return 信息封装
     */
    @RequestMapping(value = Url.Coupon.LIST_USE_COUPONS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listCouponsUse(Integer type, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return couponService.listCouponsUse(type, storeId);
    }
	
    /**
     * 新增优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponInfo 优惠券
    * @param request 请求封装
    * @return 跳转页面
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Coupon.ADD_COUPONS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionAddCoupons(CouponInfo couponInfo, HttpServletRequest request) throws ParseException{
        Integer storeId = getStoreId(request);
        return couponService.addCoupons(couponInfo, storeId);
    }
    
    /**
     * 发布优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponId 优惠券主键
    * @return 跳转页面
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Coupon.UPDATE_COUPON_USE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionUpdateCoupons(Integer couponId) throws ParseException{
        return couponService.updateCoupons(couponId);
    }
    
    /**
     * 删除优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponId 优惠券主键
    * @return 跳转页面
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Coupon.DELETE_COUPONS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionDeleteCoupons(Integer couponId) throws ParseException{
        return couponService.actionDeleteCoupons(couponId);
    }
    
    /**
     * 下架优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponId 优惠券主键
    * @return 跳转页面
     */
    @RequestMapping(value = Url.Coupon.UPDATE_COUPON_NO_USE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionUpdateNoUseCoupons(Integer couponId){
        return couponService.updateNoUseCoupons(couponId);
    }
    
    /**
     * 发送优惠券
    * @author 高国藩
    * @date 2015年9月15日 上午11:49:39
    * @param couponId 优惠券
    * @param memberLevelId 会员卡
    * @param memberScreening 筛选器
    * @param request 请求封装
    * @return 发送状态
     */
    @RequestMapping(value = Url.Coupon.SEND_COUPONS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionSendCoupons(Integer couponId, String memberLevelId, String memberScreening, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return couponService.sendCoupons(couponId, memberLevelId, memberScreening, storeId);
    }
}
