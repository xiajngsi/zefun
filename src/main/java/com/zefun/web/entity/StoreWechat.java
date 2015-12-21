package com.zefun.web.entity;

/**
 * 门店与微信数据关联
* @author 高国藩
* @date 2015年10月10日 上午10:08:56
 */
public class StoreWechat {
    
    /**主键ID*/
    private Integer relatedId;
    /**门店ID*/
    private Integer storeId;
    /**公众号唯一标示ID*/
    private String wechatId;
    /**公众号appID*/
    private String wechatAppId;
    /**公众号appsecret*/
    private String wechatAppsecret;
    /**优惠券模板*/
    private String couponsTm;
    /**提醒通知模板*/
    private String remindTm;

    public Integer getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId == null ? null : wechatAppId.trim();
    }

    public String getWechatAppsecret() {
        return wechatAppsecret;
    }

    public void setWechatAppsecret(String wechatAppsecret) {
        this.wechatAppsecret = wechatAppsecret == null ? null : wechatAppsecret.trim();
    }

    public String getCouponsTm() {
        return couponsTm;
    }

    public void setCouponsTm(String couponsTm) {
        this.couponsTm = couponsTm == null ? null : couponsTm.trim();
    }

    public String getRemindTm() {
        return remindTm;
    }

    public void setRemindTm(String remindTm) {
        this.remindTm = remindTm == null ? null : remindTm.trim();
    }
}