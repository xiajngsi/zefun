package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ServiceRecord {
	/** 服务标识 */
	private Integer recordId;

	/** 类型（评分、投诉） */
	private String type;

	/** 员工 */
	private Integer employeeId;

	/** 描述 */
	private String des;

	/** 奖惩钱 */
	private BigDecimal money;

	/** 操作时间 */
	private String operateTime;

	/** 操作人 */
	private Integer operateId;

	/** @param recordId	服务标识 */
	public void setRecordId(Integer recordId){
		this.recordId = recordId;
	}

	/** @return	服务标识 */
	public Integer getRecordId(){
		return recordId;
	}

	/** @param type	类型（评分、投诉） */
	public void setType(String type){
		this.type = type;
	}

	/** @return	类型（评分、投诉） */
	public String getType(){
		return type;
	}

	/** @param employeeId	员工 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param des	描述 */
	public void setDes(String des){
		this.des = des;
	}

	/** @return	描述 */
	public String getDes(){
		return des;
	}

	/** @param money	奖惩钱 */
	public void setMoney(BigDecimal money){
		this.money = money;
	}

	/** @return	奖惩钱 */
	public BigDecimal getMoney(){
		return money;
	}

	/** @param operateTime	操作时间 */
	public void setOperateTime(String operateTime){
		this.operateTime = operateTime;
	}

	/** @return	操作时间 */
	public String getOperateTime(){
		return operateTime;
	}

	/** @param operateId	操作人 */
	public void setOperateId(Integer operateId){
		this.operateId = operateId;
	}

	/** @return	操作人 */
	public Integer getOperateId(){
		return operateId;
	}

}