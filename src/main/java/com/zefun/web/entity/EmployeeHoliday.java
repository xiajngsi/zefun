package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeHoliday {
	/** 假期标识 */
	private Integer holidayId;

	/** 人员标识 */
	private Integer employeeId;

	/** 每周休假日(支持多天,逗号分割) */
	private String weekHoliday;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param holidayId	假期标识 */
	public void setHolidayId(Integer holidayId){
		this.holidayId = holidayId;
	}

	/** @return	假期标识 */
	public Integer getHolidayId(){
		return holidayId;
	}

	/** @param employeeId	人员标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	人员标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param weekHoliday	每周休假日(支持多天,逗号分割) */
	public void setWeekHoliday(String weekHoliday){
		this.weekHoliday = weekHoliday;
	}

	/** @return	每周休假日(支持多天,逗号分割) */
	public String getWeekHoliday(){
		return weekHoliday;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

}