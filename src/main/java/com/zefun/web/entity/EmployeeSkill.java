package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeSkill {
	/** 技能标识 */
	private Integer skillId;

	/** 员工标识 */
	private Integer employeeId;

	/** 类别名称 */
	private String categoryName;

	/** 技能名称 */
	private String skillName;

	/** 技能描述 */
	private String skillDesc;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param skillId	技能标识 */
	public void setSkillId(Integer skillId){
		this.skillId = skillId;
	}

	/** @return	技能标识 */
	public Integer getSkillId(){
		return skillId;
	}

	/** @param employeeId	员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param categoryName	类别名称 */
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	/** @return	类别名称 */
	public String getCategoryName(){
		return categoryName;
	}

	/** @param skillName	技能名称 */
	public void setSkillName(String skillName){
		this.skillName = skillName;
	}

	/** @return	技能名称 */
	public String getSkillName(){
		return skillName;
	}

	/** @param skillDesc	技能描述 */
	public void setSkillDesc(String skillDesc){
		this.skillDesc = skillDesc;
	}

	/** @return	技能描述 */
	public String getSkillDesc(){
		return skillDesc;
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