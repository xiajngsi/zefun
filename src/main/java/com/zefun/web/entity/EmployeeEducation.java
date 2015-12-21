package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeEducation {
	/** 培训标识 */
	private Integer educationId;

	/** 员工标识 */
	private Integer employeeId;

	/** 开始日期 */
	private String startDate;

	/** 结束日期 */
	private String endDate;

	/** 培训机构 */
	private String educationInstitution;

	/** 培训内容 */
	private String educationContent;

	/** 培训成绩 */
	private String educationPerformance;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param educationId	培训标识 */
	public void setEducationId(Integer educationId){
		this.educationId = educationId;
	}

	/** @return	培训标识 */
	public Integer getEducationId(){
		return educationId;
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

	/** @param educationInstitution	培训机构 */
	public void setEducationInstitution(String educationInstitution){
		this.educationInstitution = educationInstitution;
	}

	/** @return	培训机构 */
	public String getEducationInstitution(){
		return educationInstitution;
	}

	/** @param educationContent	培训内容 */
	public void setEducationContent(String educationContent){
		this.educationContent = educationContent;
	}

	/** @return	培训内容 */
	public String getEducationContent(){
		return educationContent;
	}

	/** @param educationPerformance	培训成绩 */
	public void setEducationPerformance(String educationPerformance){
		this.educationPerformance = educationPerformance;
	}

	/** @return	培训成绩 */
	public String getEducationPerformance(){
		return educationPerformance;
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