package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class StoreFlow {
	/** 流水标识 */
	private Integer flowId;

	/** 店铺标识 */
	private Integer storeId;
	
	/** 部门标识*/
	private Integer deptId;

	/** 分期月数*/
	private Integer flowNum;
	
	/** 流水类型(1:支出,2:收入) */
	private Integer flowType;

	/** 流水金额 */
	private BigDecimal flowAmount;

	/** 当前余额 */
	private BigDecimal balanceAmount;

	/** 业务类别 */
	private String businessType;

	/** 业务项目 */
	private String businessProject;

	/** 业务内容 */
	private String businessDesc;

	/** 资金来源 */
	private String flowSource;

	/** 流水时间 */
	private String flowTime;

	/** 负责人标识 */
	private Integer principalId;

	/** 操作人标识 */
	private Integer operatorId;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	
	
	public Integer getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Integer flowNum) {
        this.flowNum = flowNum;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /** @param flowId	流水标识 */
	public void setFlowId(Integer flowId){
		this.flowId = flowId;
	}

	/** @return	流水标识 */
	public Integer getFlowId(){
		return flowId;
	}

	/** @param storeId	店铺标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	店铺标识 */
	public Integer getStoreId(){
		return storeId;
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
	public void setFlowAmount(BigDecimal flowAmount){
		this.flowAmount = flowAmount;
	}

	/** @return	流水金额 */
	public BigDecimal getFlowAmount(){
		return flowAmount;
	}

	/** @param balanceAmount	当前余额 */
	public void setBalanceAmount(BigDecimal balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	当前余额 */
	public BigDecimal getBalanceAmount(){
		return balanceAmount;
	}

	/** @param businessType	业务类别 */
	public void setBusinessType(String businessType){
		this.businessType = businessType;
	}

	/** @return	业务类别 */
	public String getBusinessType(){
		return businessType;
	}

	/** @param businessProject	业务项目 */
	public void setBusinessProject(String businessProject){
		this.businessProject = businessProject;
	}

	/** @return	业务项目 */
	public String getBusinessProject(){
		return businessProject;
	}

	/** @param businessDesc	业务内容 */
	public void setBusinessDesc(String businessDesc){
		this.businessDesc = businessDesc;
	}

	/** @return	业务内容 */
	public String getBusinessDesc(){
		return businessDesc;
	}

	/** @param flowSource	资金来源 */
	public void setFlowSource(String flowSource){
		this.flowSource = flowSource;
	}

	/** @return	资金来源 */
	public String getFlowSource(){
		return flowSource;
	}

	/** @param flowTime	流水时间 */
	public void setFlowTime(String flowTime){
		this.flowTime = flowTime;
	}

	/** @return	流水时间 */
	public String getFlowTime(){
		return flowTime;
	}

	/** @param principalId	负责人标识 */
	public void setPrincipalId(Integer principalId){
		this.principalId = principalId;
	}

	/** @return	负责人标识 */
	public Integer getPrincipalId(){
		return principalId;
	}

	/** @param operatorId	操作人标识 */
	public void setOperatorId(Integer operatorId){
		this.operatorId = operatorId;
	}

	/** @return	操作人标识 */
	public Integer getOperatorId(){
		return operatorId;
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