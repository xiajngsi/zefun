package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeRecommend {
	/** 推荐标识 */
	private Integer recommendId;

	/** 员工标识 */
	private Integer employeeId;

	/** 推荐人标识 */
	private Integer referrerId;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param recommendId	推荐标识 */
	public void setRecommendId(Integer recommendId){
		this.recommendId = recommendId;
	}

	/** @return	推荐标识 */
	public Integer getRecommendId(){
		return recommendId;
	}

	/** @param employeeId	员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param referrerId	推荐人标识 */
	public void setReferrerId(Integer referrerId){
		this.referrerId = referrerId;
	}

	/** @return	推荐人标识 */
	public Integer getReferrerId(){
		return referrerId;
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