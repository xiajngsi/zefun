package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月25日 PM 17:47:43
 */
public class AgentAccountFlow {
	/** 流水标识 */
	private Integer flowId;

	/** 代理标识 */
	private Integer agentId;

	/** 流水类型(1:支出,2:收入) */
	private Integer flowType;

	/** 流水金额 */
	private Integer flowAmount;

	/** 流水说明 */
	private String flowDesc;

	/** 流水时间 */
	private String flowTime;

	/** @param flowId	流水标识 */
	public void setFlowId(Integer flowId){
		this.flowId = flowId;
	}

	/** @return	流水标识 */
	public Integer getFlowId(){
		return flowId;
	}

	/** @param agentId	代理标识 */
	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	/** @return	代理标识 */
	public Integer getAgentId(){
		return agentId;
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

	/** @param flowDesc	流水说明 */
	public void setFlowDesc(String flowDesc){
		this.flowDesc = flowDesc;
	}

	/** @return	流水说明 */
	public String getFlowDesc(){
		return flowDesc;
	}

	/** @param flowTime	流水时间 */
	public void setFlowTime(String flowTime){
		this.flowTime = flowTime;
	}

	/** @return	流水时间 */
	public String getFlowTime(){
		return flowTime;
	}

}