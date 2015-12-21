package com.zefun.web.entity;
/**
 * 用户优惠券关联关系
* @author 高国藩
* @date 2015年9月15日 上午11:16:38
 */
public class MemberCoupon {
    
    /**关联主键*/
    private Integer relevanceId;
    /**优惠券*/
    private Integer couponId;
    /**会员*/
    private Integer memberInfoId;
    /**是否使用 0:未使用 1:已使用*/
    private Integer isUsed;
    /**发放日期*/
    private String grantTime;

    public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getMemberInfoId() {
        return memberInfoId;
    }

    public void setMemberInfoId(Integer memberInfoId) {
        this.memberInfoId = memberInfoId;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public String getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(String grantTime) {
        this.grantTime = grantTime == null ? null : grantTime.trim();
    }
}