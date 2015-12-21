package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月25日 PM 17:47:43
 */
public class StoreAccount {
	/** 门店标识 */
	private Integer storeId;

	/** 累计获得天数 */
	private Integer totalDays;

	/** 剩余使用天数 */
	private Integer balanceDays;

	/** 累计短信数量 */
	private Integer totalSms;

	/** 剩余短信数量 */
	private Integer balanceSms;

	/** 门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
	private Integer storeStatus;

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param totalDays	累计获得天数 */
	public void setTotalDays(Integer totalDays){
		this.totalDays = totalDays;
	}

	/** @return	累计获得天数 */
	public Integer getTotalDays(){
		return totalDays;
	}

	/** @param balanceDays	剩余使用天数 */
	public void setBalanceDays(Integer balanceDays){
		this.balanceDays = balanceDays;
	}

	/** @return	剩余使用天数 */
	public Integer getBalanceDays(){
		return balanceDays;
	}

	/** @param totalSms	累计短信数量 */
	public void setTotalSms(Integer totalSms){
		this.totalSms = totalSms;
	}

	/** @return	累计短信数量 */
	public Integer getTotalSms(){
		return totalSms;
	}

	/** @param balanceSms	剩余短信数量 */
	public void setBalanceSms(Integer balanceSms){
		this.balanceSms = balanceSms;
	}

	/** @return	剩余短信数量 */
	public Integer getBalanceSms(){
		return balanceSms;
	}

	/** @param storeStatus	门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
	public void setStoreStatus(Integer storeStatus){
		this.storeStatus = storeStatus;
	}

	/** @return	门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
	public Integer getStoreStatus(){
		return storeStatus;
	}

}