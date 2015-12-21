package com.zefun.web.dto;

import java.io.Serializable;

/**
 * 优惠券页面展示
* @author 高国藩
* @date 2015年9月14日 下午5:52:00
 */
public class CouponInfoDto implements Serializable {
    /** @Fields serialVersionUID : */
    private static final long serialVersionUID = -3210258757586071584L;
    /**主键*/
    private Integer couponId;
    /**名称*/
    private String couponName;
    /**抵扣金额*/
    private Integer couponPrice;
    /**兑换所需积分*/
    private Integer couponVantages;
    /**优惠劵类型(0:通用 1:项目 2:商品 3:套餐)*/
    private String couponType;
    /**所对应的项目商品套餐名称*/
    private String couponUse;
    /**开始时间*/
    private String couponStartTime;
    /**结束时间*/
    private String couponStopTime;
    /**是否可用*/
    private String couponIsUse;
    /**发布时间*/
    private String releaseTime;
    /**已经发送次数*/
    private Integer hasExchangeCount;
    /**已经使用次数*/
    private Integer hasUseCount;
    
    
    public String getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
    public Integer getHasExchangeCount() {
        return hasExchangeCount;
    }
    public void setHasExchangeCount(Integer hasExchangeCount) {
        this.hasExchangeCount = hasExchangeCount;
    }
    public Integer getHasUseCount() {
        return hasUseCount;
    }
    public void setHasUseCount(Integer hasUseCount) {
        this.hasUseCount = hasUseCount;
    }
    public Integer getCouponId() {
        return couponId;
    }
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
    public String getCouponName() {
        return couponName;
    }
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    public Integer getCouponPrice() {
        return couponPrice;
    }
    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }
    public Integer getCouponVantages() {
        return couponVantages;
    }
    public void setCouponVantages(Integer couponVantages) {
        this.couponVantages = couponVantages;
    }
    public String getCouponType() {
        return couponType;
    }
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
    public String getCouponUse() {
        return couponUse;
    }
    public void setCouponUse(String couponUse) {
        this.couponUse = couponUse;
    }
    public String getCouponStartTime() {
        return couponStartTime;
    }
    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }
    public String getCouponStopTime() {
        return couponStopTime;
    }
    public void setCouponStopTime(String couponStopTime) {
        this.couponStopTime = couponStopTime;
    }
    public String getCouponIsUse() {
        return couponIsUse;
    }
    public void setCouponIsUse(String couponIsUse) {
        this.couponIsUse = couponIsUse;
    }
    
    
}
