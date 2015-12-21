package com.zefun.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.json.JSONObject;

/**
 * 流水查询列表信息对象
* @author luhw
*/
public class DayBookDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -1749678604530169826L;

	/** 订单标识 */
	private Integer orderId;
	
	/** 订单号 */
	private String orderCode;
	
	/** 会员标识 */
	private Integer memberId;
	
	/** 会员名称 */
	private String memberName;
	
	/** 性别 */
	private String sex;
	
	/** 创建时间 */
	private String createTime;
	
	/** 项目名称 */
	private String projectName;
	
	/** 应收 */
	private BigDecimal receivableAmount;
	
	/** 实收 */
	private BigDecimal realPrice;
	
	/** 礼金抵扣 */
	private BigDecimal giftAmount;
	
	/** 现金支付 */
	private BigDecimal cashAmount;
	
	/** 卡金支付 */
	private BigDecimal cardAmount;
	
	/** 支付宝支付 */
	private BigDecimal alipayAmount;
	
	/** 微信支付 */
	private BigDecimal wechatAmount;	
	/** 银联支付 */
	private BigDecimal unionpayAmount;
	
	/** 优惠券抵扣 */
	private BigDecimal couponAmount;
	
	/** 套餐抵扣 */
	private BigDecimal comboAmount;
	
	/** 签单 */
	private String freeAmount;
	
	/** 挂账 */
	private BigDecimal debtAmount;
	
	/** 统计数量 */
	private Integer count;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCreateTime() {
		return createTime;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}

	public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(BigDecimal cardAmount) {
		this.cardAmount = cardAmount;
	}

	public BigDecimal getAlipayAmount() {
		return alipayAmount;
	}

	public void setAlipayAmount(BigDecimal alipayAmount) {
		this.alipayAmount = alipayAmount;
	}

	public BigDecimal getWechatAmount() {
		return wechatAmount;
	}

	public void setWechatAmount(BigDecimal wechatAmount) {
		this.wechatAmount = wechatAmount;
	}

	public BigDecimal getUnionpayAmount() {
		return unionpayAmount;
	}

	public void setUnionpayAmount(BigDecimal unionpayAmount) {
		this.unionpayAmount = unionpayAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getComboAmount() {
		return comboAmount;
	}

	public void setComboAmount(BigDecimal comboAmount) {
		this.comboAmount = comboAmount;
	}

	
	public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    public BigDecimal getDebtAmount() {
		return debtAmount;
	}

	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}
	
	public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
	 * @return BigDecimal
	 */
	public BigDecimal getRealPay() {
		BigDecimal realPay = giftAmount.add(cashAmount).add(cardAmount).add(alipayAmount).add(wechatAmount)
				  .add(unionpayAmount).subtract(couponAmount).subtract(comboAmount)
				  .subtract(debtAmount).subtract(debtAmount);
		BigDecimal zero = new BigDecimal(0);
		if (realPay.compareTo(zero) < 0) {
			realPay = zero;
		}
		return realPay;
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
