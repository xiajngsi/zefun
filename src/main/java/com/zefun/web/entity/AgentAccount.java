package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月25日 PM 17:47:43
 */
public class AgentAccount {
	/** 代理标识 */
	private Integer agentId;

	/** 账户累计金额 */
	private Integer totalAmount;

	/** 账户余额 */
	private Integer balanceAmount;

	/** 累计账号数量 */
	private Integer totalAccount;

	/** 剩余账号数量 */
	private Integer balanceAccount;

	/** 代理状态(1:申请中，2:正常，3:停用) */
	private Integer agentStatus;

	/** @param agentId	代理标识 */
	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	/** @return	代理标识 */
	public Integer getAgentId(){
		return agentId;
	}

	/** @param totalAmount	账户累计金额 */
	public void setTotalAmount(Integer totalAmount){
		this.totalAmount = totalAmount;
	}

	/** @return	账户累计金额 */
	public Integer getTotalAmount(){
		return totalAmount;
	}

	/** @param balanceAmount	账户余额 */
	public void setBalanceAmount(Integer balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	账户余额 */
	public Integer getBalanceAmount(){
		return balanceAmount;
	}

	/** @param totalAccount	累计账号数量 */
	public void setTotalAccount(Integer totalAccount){
		this.totalAccount = totalAccount;
	}

	/** @return	累计账号数量 */
	public Integer getTotalAccount(){
		return totalAccount;
	}

	/** @param balanceAccount	剩余账号数量 */
	public void setBalanceAccount(Integer balanceAccount){
		this.balanceAccount = balanceAccount;
	}

	/** @return	剩余账号数量 */
	public Integer getBalanceAccount(){
		return balanceAccount;
	}

	/** @param agentStatus	代理状态(1:申请中，2:正常，3:停用) */
	public void setAgentStatus(Integer agentStatus){
		this.agentStatus = agentStatus;
	}

	/** @return	代理状态(1:申请中，2:正常，3:停用) */
	public Integer getAgentStatus(){
		return agentStatus;
	}

}