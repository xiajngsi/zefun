package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class AccountCharge {
	/** 充值标识 */
	private Integer chargeId;

	/** 账户标识 */
	private Integer accountId;

	/** 充值金额 */
	private BigDecimal chargeAmount;

	/** 充值时间 */
	private String chargeTime;
	
	/** */
    private Integer storeId;
	
	/** 操作员工标识 */
	private Integer operatorId;

	
	
	public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /** @param chargeId	充值标识 */
	public void setChargeId(Integer chargeId){
		this.chargeId = chargeId;
	}

	/** @return	充值标识 */
	public Integer getChargeId(){
		return chargeId;
	}

	/** @param accountId	账户标识 */
	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}

	/** @return	账户标识 */
	public Integer getAccountId(){
		return accountId;
	}

	/** @param chargeAmount	充值金额 */
	public void setChargeAmount(BigDecimal chargeAmount){
		this.chargeAmount = chargeAmount;
	}

	/** @return	充值金额 */
	public BigDecimal getChargeAmount(){
		return chargeAmount;
	}

	/** @param chargeTime	充值时间 */
	public void setChargeTime(String chargeTime){
		this.chargeTime = chargeTime;
	}

	/** @return	充值时间 */
	public String getChargeTime(){
		return chargeTime;
	}


}