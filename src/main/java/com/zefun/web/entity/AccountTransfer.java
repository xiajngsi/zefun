package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class AccountTransfer {
	/** 转账标识 */
	private Integer transferId;

	/** 出账账户标识 */
	private Integer outAccountId;

	/** 入账账户标识 */
	private Integer inAccountId;

	/** 转账金额 */
	private BigDecimal transferAmount;

	/** 转账时间 */
	private String transferTime;

	/** 操作员工标识 */
	private Integer operatorId;

	
	
	public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /** @param transferId	转账标识 */
	public void setTransferId(Integer transferId){
		this.transferId = transferId;
	}

	/** @return	转账标识 */
	public Integer getTransferId(){
		return transferId;
	}

	/** @param outAccountId	出账账户标识 */
	public void setOutAccountId(Integer outAccountId){
		this.outAccountId = outAccountId;
	}

	/** @return	出账账户标识 */
	public Integer getOutAccountId(){
		return outAccountId;
	}

	/** @param inAccountId	入账账户标识 */
	public void setInAccountId(Integer inAccountId){
		this.inAccountId = inAccountId;
	}

	/** @return	入账账户标识 */
	public Integer getInAccountId(){
		return inAccountId;
	}

	/** @param transferAmount	转账金额 */
	public void setTransferAmount(BigDecimal transferAmount){
		this.transferAmount = transferAmount;
	}

	/** @return	转账金额 */
	public BigDecimal getTransferAmount(){
		return transferAmount;
	}

	/** @param transferTime	转账时间 */
	public void setTransferTime(String transferTime){
		this.transferTime = transferTime;
	}

	/** @return	转账时间 */
	public String getTransferTime(){
		return transferTime;
	}

}