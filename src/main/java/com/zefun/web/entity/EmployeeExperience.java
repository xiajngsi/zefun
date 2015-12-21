package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeExperience {
	/** 履历标识 */
	private Integer experienceId;

	/** 员工标识 */
	private Integer employeeId;

	/** 开始日期 */
	private String startDate;

	/** 结束日期 */
	private String endDate;

	/** 公司名称 */
	private String companyName;

	/** 岗位名称 */
	private String positionName;

	/** 履历描述 */
	private String experienceDesc;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param experienceId	履历标识 */
	public void setExperienceId(Integer experienceId){
		this.experienceId = experienceId;
	}

	/** @return	履历标识 */
	public Integer getExperienceId(){
		return experienceId;
	}

	/** @param employeeId	员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param startDate	开始日期 */
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	/** @return	开始日期 */
	public String getStartDate(){
		return startDate;
	}

	/** @param endDate	结束日期 */
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	/** @return	结束日期 */
	public String getEndDate(){
		return endDate;
	}

	/** @param companyName	公司名称 */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	/** @return	公司名称 */
	public String getCompanyName(){
		return companyName;
	}

	/** @param positionName	岗位名称 */
	public void setPositionName(String positionName){
		this.positionName = positionName;
	}

	/** @return	岗位名称 */
	public String getPositionName(){
		return positionName;
	}

	/** @param experienceDesc	履历描述 */
	public void setExperienceDesc(String experienceDesc){
		this.experienceDesc = experienceDesc;
	}

	/** @return	履历描述 */
	public String getExperienceDesc(){
		return experienceDesc;
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