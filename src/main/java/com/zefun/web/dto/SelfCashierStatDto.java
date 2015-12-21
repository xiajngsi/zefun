package com.zefun.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.json.JSONObject;

/**
 * 自助收银 数据对象
* @author luhw
*/
public class SelfCashierStatDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -7656339663712892032L;

	/** 统计日期 */
	private String date;
	
	/** 已结账数量 */
	private Integer payCount;
	
	/** 已结账金额 */
	private BigDecimal payAmount;
	
	/** 未结账数量 */
	private Integer unpayCount;
	
	/** 未结账金额 */
	private BigDecimal unpayAmount;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getUnpayCount() {
		return unpayCount;
	}

	public void setUnpayCount(Integer unpayCount) {
		this.unpayCount = unpayCount;
	}

	public BigDecimal getUnpayAmount() {
		return unpayAmount;
	}

	public void setUnpayAmount(BigDecimal unpayAmount) {
		this.unpayAmount = unpayAmount;
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
