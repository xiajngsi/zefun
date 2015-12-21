package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class SalaryRecord {
	/** 考勤记录标识 */
	private Integer recordId;

	/** 门店标识 */
	private Integer storeId;

	/** 操作人员 */
	private Integer employeeId;

	/** 日期 */
	private String date;

	/** 类型 */
	private String type;

	/** 签到时间 */
	private String signinTime;

	/** 签到地点 */
	private String signinAdderss;

	/** 迟到时间（多久） */
	private Double lateTime;

	/** 签退时间 */
	private String signoutTime;

	/** 签退地址 */
	private String signoutAdderss;

	/** 早退时间（多久） */
	private Double earlyTime;

	/** 请假旷工时间 */
	private Double disappearTime;

	/** 扣钱 */
	private BigDecimal abstractMoney;

	/** @param recordId	考勤记录标识 */
	public void setRecordId(Integer recordId){
		this.recordId = recordId;
	}

	/** @return	考勤记录标识 */
	public Integer getRecordId(){
		return recordId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param employeeId	操作人员 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	操作人员 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param date	日期 */
	public void setDate(String date){
		this.date = date;
	}

	/** @return	日期 */
	public String getDate(){
		return date;
	}

	/** @param type	类型 */
	public void setType(String type){
		this.type = type;
	}

	/** @return	类型 */
	public String getType(){
		return type;
	}

	/** @param signinTime	签到时间 */
	public void setSigninTime(String signinTime){
		this.signinTime = signinTime;
	}

	/** @return	签到时间 */
	public String getSigninTime(){
		return signinTime;
	}

	/** @param signinAdderss	签到地点 */
	public void setSigninAdderss(String signinAdderss){
		this.signinAdderss = signinAdderss;
	}

	/** @return	签到地点 */
	public String getSigninAdderss(){
		return signinAdderss;
	}

	/** @param lateTime	迟到时间（多久） */
	public void setLateTime(Double lateTime){
		this.lateTime = lateTime;
	}

	/** @return	迟到时间（多久） */
	public Double getLateTime(){
		return lateTime;
	}

	/** @param signoutTime	签退时间 */
	public void setSignoutTime(String signoutTime){
		this.signoutTime = signoutTime;
	}

	/** @return	签退时间 */
	public String getSignoutTime(){
		return signoutTime;
	}

	/** @param signoutAdderss	签退地址 */
	public void setSignoutAdderss(String signoutAdderss){
		this.signoutAdderss = signoutAdderss;
	}

	/** @return	签退地址 */
	public String getSignoutAdderss(){
		return signoutAdderss;
	}

	/** @param earlyTime	早退时间（多久） */
	public void setEarlyTime(Double earlyTime){
		this.earlyTime = earlyTime;
	}

	/** @return	早退时间（多久） */
	public Double getEarlyTime(){
		return earlyTime;
	}

	/** @param disappearTime	请假旷工时间 */
	public void setDisappearTime(Double disappearTime){
		this.disappearTime = disappearTime;
	}

	/** @return	请假旷工时间 */
	public Double getDisappearTime(){
		return disappearTime;
	}

	/** @param abstractMoney	扣钱 */
	public void setAbstractMoney(BigDecimal abstractMoney){
		this.abstractMoney = abstractMoney;
	}

	/** @return	扣钱 */
	public BigDecimal getAbstractMoney(){
		return abstractMoney;
	}

}