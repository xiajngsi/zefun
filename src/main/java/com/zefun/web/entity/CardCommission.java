package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class CardCommission {
	/** 提成标识 */
	private Integer commissionId;

	/** 充值标识 */
	private Integer chargeId;

	/** 提成员工标识 */
	private Integer employeeId;

	/** 提成类型(1:开卡提成,2:充值提成) */
	private Integer commissionType;

	/** 会员卡名称 */
	private String cardName;

	/** 充值金额 */
	private BigDecimal chargeAmount;

	/** 提成金额 */
	private BigDecimal commissionAmount;

	/** 发生时间 */
	private String chargeTime;

	
	
	public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /** @param commissionId	提成标识 */
	public void setCommissionId(Integer commissionId){
		this.commissionId = commissionId;
	}

	/** @return	提成标识 */
	public Integer getCommissionId(){
		return commissionId;
	}

	/** @param chargeId	充值标识 */
	public void setChargeId(Integer chargeId){
		this.chargeId = chargeId;
	}

	/** @return	充值标识 */
	public Integer getChargeId(){
		return chargeId;
	}

	/** @param commissionType	提成类型(1:开卡提成,2:充值提成) */
	public void setCommissionType(Integer commissionType){
		this.commissionType = commissionType;
	}

	/** @return	提成类型(1:开卡提成,2:充值提成) */
	public Integer getCommissionType(){
		return commissionType;
	}

	/** @param cardName	会员卡名称 */
	public void setCardName(String cardName){
		this.cardName = cardName;
	}

	/** @return	会员卡名称 */
	public String getCardName(){
		return cardName;
	}

	/** @param chargeAmount	充值金额 */
	public void setChargeAmount(BigDecimal chargeAmount){
		this.chargeAmount = chargeAmount;
	}

	/** @return	充值金额 */
	public BigDecimal getChargeAmount(){
		return chargeAmount;
	}

	/** @param commissionAmount	提成金额 */
	public void setCommissionAmount(BigDecimal commissionAmount){
		this.commissionAmount = commissionAmount;
	}

	/** @return	提成金额 */
	public BigDecimal getCommissionAmount(){
		return commissionAmount;
	}

	/** @param chargeTime	发生时间 */
	public void setChargeTime(String chargeTime){
		this.chargeTime = chargeTime;
	}

	/** @return	发生时间 */
	public String getChargeTime(){
		return chargeTime;
	}

}