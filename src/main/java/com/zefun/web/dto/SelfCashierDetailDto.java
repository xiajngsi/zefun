package com.zefun.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 自助收银订单详情相关
* @author luhw
* @date 2015年10月21日 下午4:20:00
 */
public class SelfCashierDetailDto implements Serializable {
	
    /** @Fields serialVersionUID : */
    private static final long serialVersionUID = -8551386369449236216L;

    /** 订单详情ID */
    private Integer detailId;
    
    /** 订单号 */
    private String detailCode;
    
    /** 订单类型(1：项目，2:商品，3:套餐) */
    private Integer orderType;
    
    /** 是否预约 */
    private Integer isAppoint;
    
    /** 预约优惠金额 */
    private BigDecimal appointOff;
    
    /** 是否指定 */
    private Integer isAssign;
    
    /** 项目ID */
    private Integer projectId;
    
    /** 项目名称 */
    private String projectName;
    
    /** 项目价格 */
    private BigDecimal projectPrice;
    
    /** 项目图片 */
    private String projectImage;
    
    /** 项目数量 */
    private Integer projectCount;

	/** 订单状态 */
    private Integer orderStatus;
    
    /** 实际价格 */
    private BigDecimal realPrice;
    
    /** 单个商品的折扣价格 */
    private BigDecimal discountAmount;
    
    /** 服务时长 */
    private String createTime;
    
    /** 优惠类型(1:套餐，2:优惠券，3:礼金) */
    private Integer offType;
    
    /** 优惠名称 */
    private Integer offName;
    
    /** 优惠金额 */
    private BigDecimal offAmount;
    
    /** 是否可用礼金抵扣 */
    private Integer isGiftCash;
    
    /**最大礼金抵扣*/
    private BigDecimal highestDiscount;
    
    /** 项目所属部门(用户计算礼金抵扣) */
    private Integer deptId;
    
    /** 可用优惠列表 */
    private List<PaymentOffDto> paymentOffList;
    
    public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    
    public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsAppoint() {
		return isAppoint;
	}

	public void setIsAppoint(Integer isAppoint) {
		this.isAppoint = isAppoint;
	}



    public BigDecimal getAppointOff() {
        return appointOff;
    }

    public void setAppointOff(BigDecimal appointOff) {
        this.appointOff = appointOff;
    }

    public Integer getIsAssign() {
		return isAssign;
	}

	public void setIsAssign(Integer isAssign) {
		this.isAssign = isAssign;
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
    
    public BigDecimal getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(BigDecimal projectPrice) {
		this.projectPrice = projectPrice;
	}
	
    public String getProjectImage() {
		return projectImage;
	}

	public void setProjectImage(String projectImage) {
		this.projectImage = projectImage;
	}

	public Integer getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(Integer projectCount) {
		this.projectCount = projectCount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public Integer getIsGiftCash() {
        return isGiftCash;
    }

    public void setIsGiftCash(Integer isGiftCash) {
        this.isGiftCash = isGiftCash;
    }

    public BigDecimal getHighestDiscount() {
        return highestDiscount;
    }

    public void setHighestDiscount(BigDecimal highestDiscount) {
        this.highestDiscount = highestDiscount;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getOffType() {
        return offType;
    }

    public void setOffType(Integer offType) {
        this.offType = offType;
    }

    public Integer getOffName() {
        return offName;
    }

    public void setOffName(Integer offName) {
        this.offName = offName;
    }

    public BigDecimal getOffAmount() {
        return offAmount;
    }

    public void setOffAmount(BigDecimal offAmount) {
        this.offAmount = offAmount;
    }

    public List<PaymentOffDto> getPaymentOffList() {
        return paymentOffList;
    }

    public void setPaymentOffList(List<PaymentOffDto> paymentOffList) {
        this.paymentOffList = paymentOffList;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
}
