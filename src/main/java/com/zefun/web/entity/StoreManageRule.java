package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年12月05日 PM 18:57:32
 */
public class StoreManageRule {
	/** 规则标识 */
	private Integer ruleId;

	/** 门店标识 */
	private Integer storeId;

	/** 规则类型(1:考勤，2:行为，3:服务) */
	private Integer ruleType;

	/** 规则名称 */
	private String ruleName;

	/** 规则描述 */
	private String ruleDesc;

	/** 指标类型(1:分钟，2:小时，3:分数) */
	private Integer targetType;

	/** 指标值 */
	private Integer targetValue;

	/** 处理方式(1:奖励，2:惩罚) */
	private Integer processType;

	/** 奖惩金额 */
	private BigDecimal processMoney;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** 最后操作时间 */
	private String lastOperatorTime;

	/** @param ruleId	规则标识 */
	public void setRuleId(Integer ruleId){
		this.ruleId = ruleId;
	}

	/** @return	规则标识 */
	public Integer getRuleId(){
		return ruleId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param ruleType	规则类型(1:考勤，2:行为，3:服务) */
	public void setRuleType(Integer ruleType){
		this.ruleType = ruleType;
	}

	/** @return	规则类型(1:考勤，2:行为，3:服务) */
	public Integer getRuleType(){
		return ruleType;
	}

	/** @param ruleName	规则名称 */
	public void setRuleName(String ruleName){
		this.ruleName = ruleName;
	}

	/** @return	规则名称 */
	public String getRuleName(){
		return ruleName;
	}

	/** @param ruleDesc	规则描述 */
	public void setRuleDesc(String ruleDesc){
		this.ruleDesc = ruleDesc;
	}

	/** @return	规则描述 */
	public String getRuleDesc(){
		return ruleDesc;
	}

	/** @param targetType	指标类型(1:分钟，2:小时，3:分数) */
	public void setTargetType(Integer targetType){
		this.targetType = targetType;
	}

	/** @return	指标类型(1:分钟，2:小时，3:分数) */
	public Integer getTargetType(){
		return targetType;
	}

	/** @param targetValue	指标值 */
	public void setTargetValue(Integer targetValue){
		this.targetValue = targetValue;
	}

	/** @return	指标值 */
	public Integer getTargetValue(){
		return targetValue;
	}

	/** @param processType	处理方式(1:奖励，2:惩罚) */
	public void setProcessType(Integer processType){
		this.processType = processType;
	}

	/** @return	处理方式(1:奖励，2:惩罚) */
	public Integer getProcessType(){
		return processType;
	}

	/** @param processMoney	奖惩金额 */
	public void setProcessMoney(BigDecimal processMoney){
		this.processMoney = processMoney;
	}

	/** @return	奖惩金额 */
	public BigDecimal getProcessMoney(){
		return processMoney;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

	/** @param lastOperatorTime	最后操作时间 */
	public void setLastOperatorTime(String lastOperatorTime){
		this.lastOperatorTime = lastOperatorTime;
	}

	/** @return	最后操作时间 */
	public String getLastOperatorTime(){
		return lastOperatorTime;
	}

}