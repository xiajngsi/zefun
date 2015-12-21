package com.zefun.web.entity;

/**
 * 优惠券
* @author 高国藩
* @date 2015年9月14日 下午2:21:14
 */
public class CouponInfo {
    /**主键*/
    private Integer couponId;
    /**名称*/
    private String couponName;
    /**抵扣金额*/
    private Integer couponPrice;
    /**兑换所需积分*/
    private Integer couponVantages;
    /**类型 0:通用 1:项目 2:商品 3:套餐*/
    private Integer couponType;
    /**使用与项目商品套餐的引用*/
    private Integer couponUseId;
    /**开始时间*/
    private String couponStartTime;
    /**结束时间*/
    private String couponStopTime;
    /**发布时间*/
    private String releaseTime;
    /**已经发送次数*/
    private Integer hasExchangeCount;
    /**已经使用次数*/
    private Integer hasUseCount;
    /**是否可用*/
    private Integer couponIsUse;
    /**门店信息*/
    private Integer storeId;
    /**是否删除*/
    private Integer isDelete;
    
    
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

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

    public Integer getCouponVantages() {
        return couponVantages;
    }

    public void setCouponVantages(Integer couponVantages) {
        this.couponVantages = couponVantages;
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
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Integer getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getCouponUseId() {
        return couponUseId;
    }

    public void setCouponUseId(Integer couponUseId) {
        this.couponUseId = couponUseId;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime == null ? null : couponStartTime.trim();
    }

    public String getCouponStopTime() {
        return couponStopTime;
    }

    public void setCouponStopTime(String couponStopTime) {
        this.couponStopTime = couponStopTime == null ? null : couponStopTime.trim();
    }

    public Integer getCouponIsUse() {
        return couponIsUse;
    }

    public void setCouponIsUse(Integer couponIsUse) {
        this.couponIsUse = couponIsUse;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}