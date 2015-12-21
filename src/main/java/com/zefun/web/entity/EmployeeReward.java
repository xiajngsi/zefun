package com.zefun.web.entity;

/**
 * 员工奖惩记录entity
 * 
 * @author lzc
 *
 */
public class EmployeeReward {
	/** id */
	private Integer rewardId;

	/** 员工id */
	private Integer employeeId;

	/** 类型：1，考勤；2，服务； */
	private String type;

	/** 是否奖励：0，否；1，是； */
	private String isReward;

	/** 金额 */
	private Double number;

	/** 修改人 */
	private Integer modifyer;

	/** 修改时间 */
	private String modifytime;

	/** 原因 */
	private String reasons;

	/** 总数 */
	private Integer count;

	public Integer getRewardId() {
		return rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

	public String getIsReward() {
		return isReward;
	}

	public void setIsReward(String isReward) {
		this.isReward = isReward == null ? null : isReward.trim();
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime == null ? null : modifytime.trim();
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}