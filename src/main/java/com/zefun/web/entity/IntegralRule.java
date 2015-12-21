package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class IntegralRule {
	/** 规则ID */
	private Integer ruleId;

	/** 门店ID */
	private Integer storeId;

	/** 失效时间(自领取积分开始起*个月后失效) */
	private Integer stopTime;

	/** @param ruleId	规则ID */
	public void setRuleId(Integer ruleId){
		this.ruleId = ruleId;
	}

	/** @return	规则ID */
	public Integer getRuleId(){
		return ruleId;
	}

	/** @param storeId	门店ID */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店ID */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param stopTime	失效时间(自领取积分开始起*个月后失效) */
	public void setStopTime(Integer stopTime){
		this.stopTime = stopTime;
	}

	/** @return	失效时间(自领取积分开始起*个月后失效) */
	public Integer getStopTime(){
		return stopTime;
	}

}