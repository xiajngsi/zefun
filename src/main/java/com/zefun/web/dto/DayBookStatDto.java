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
public class DayBookStatDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 6089121680002948183L;

	/** 项目消费 */
	private BigDecimal projectAmount;
	
	/** 商品消费 */
	private BigDecimal goodsAmount;
	
	/** 套餐消费 */
	private BigDecimal comboAmount;
	
	/** 开卡充值消费 */
	private BigDecimal cardAmount;
	
	public BigDecimal getTotalAmount() {
		return projectAmount.add(goodsAmount).add(comboAmount).add(cardAmount);
	}
	
	public BigDecimal getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(BigDecimal projectAmount) {
		this.projectAmount = projectAmount;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public BigDecimal getComboAmount() {
		return comboAmount;
	}

	public void setComboAmount(BigDecimal comboAmount) {
		this.comboAmount = comboAmount;
	}

	public BigDecimal getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(BigDecimal cardAmount) {
		this.cardAmount = cardAmount;
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
