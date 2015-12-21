package com.zefun.web.dto;

import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 优惠券基础传输对象
* @author 张进军
* @date Oct 21, 2015 3:04:08 PM 
*/
public class CouponBaseDto {
	
	/** 关联标识 */
	private Integer relevanceId;
	
    /**优惠券标识*/
    private Integer couponId;
    
    /**优惠券名称*/
    private String couponName;
    
    /**抵扣金额*/
    private Integer couponPrice;
    
    /**兑换所需积分*/
    private Integer couponVantages;
    
    /**优惠劵类型(0:通用 1:项目 2:商品 3:套餐)*/
    private Integer couponType;
    
    /**过期时间*/
    private String overdueTime;
    
    /**项目/商品/套餐 名称*/
    private Integer projectId;
    
    /**项目/商品/套餐 名称*/
    private String projectName;
    
    /**项目/商品/套餐 图片*/
    private String projectImage;
    
    /** 使用次数 */
    private Integer hasUseCount;
    
    /**是否过期，0:未过期，1:过期*/
    private Integer isOverdue;

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

    public Integer getCouponType() {
        return couponType;
    }

    /**
     * 
     * @param couponType couponType
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(String overdueTime) {
        this.overdueTime = overdueTime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public Integer getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Integer isOverdue) {
        this.isOverdue = isOverdue;
    }

	public Integer getHasUseCount() {
		return hasUseCount;
	}

	public void setHasUseCount(Integer hasUseCount) {
		this.hasUseCount = hasUseCount;
	}

	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
    	return JSONObject.fromObject(this).toString();
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
}
