package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class IntegralFlow {
	/** 流水标识 */
	private Integer flowId;

	/** 账户标识 */
	private Integer accountId;

	/** 流水类型(1:支出,2:收入) */
	private Integer flowType;

	/** 流水金额 */
	private Integer flowAmount;

	/** 当前余额 */
	private Integer balanceAmount;

	/** 单号标识(积分变化的订单标识) */
	private Integer orderId;

	/** 业务类型 */
	private String businessType;

	/** 业务描述 */
	private String businessDesc;

	/** 流水时间 */
	private String flowTime;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** @param flowId	流水标识 */
	public void setFlowId(Integer flowId){
		this.flowId = flowId;
	}

	/** @return	流水标识 */
	public Integer getFlowId(){
		return flowId;
	}

	/** @param accountId	账户标识 */
	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}

	/** @return	账户标识 */
	public Integer getAccountId(){
		return accountId;
	}

	/** @param flowType	流水类型(1:支出,2:收入) */
	public void setFlowType(Integer flowType){
		this.flowType = flowType;
	}

	/** @return	流水类型(1:支出,2:收入) */
	public Integer getFlowType(){
		return flowType;
	}

	/** @param flowAmount	流水金额 */
	public void setFlowAmount(Integer flowAmount){
		this.flowAmount = flowAmount;
	}

	/** @return	流水金额 */
	public Integer getFlowAmount(){
		return flowAmount;
	}

	/** @param balanceAmount	当前余额 */
	public void setBalanceAmount(Integer balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	当前余额 */
	public Integer getBalanceAmount(){
		return balanceAmount;
	}

	/** @param orderId	单号标识(积分变化的订单标识) */
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}

	/** @return	单号标识(积分变化的订单标识) */
	public Integer getOrderId(){
		return orderId;
	}

	/** @param businessType	业务类型 */
	public void setBusinessType(String businessType){
		this.businessType = businessType;
	}

	/** @return	业务类型 */
	public String getBusinessType(){
		return businessType;
	}

	/** @param businessDesc	业务描述 */
	public void setBusinessDesc(String businessDesc){
		this.businessDesc = businessDesc;
	}

	/** @return	业务描述 */
	public String getBusinessDesc(){
		return businessDesc;
	}

	/** @param flowTime	流水时间 */
	public void setFlowTime(String flowTime){
		this.flowTime = flowTime;
	}

	/** @return	流水时间 */
	public String getFlowTime(){
		return flowTime;
	}

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
	}

}