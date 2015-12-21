package com.zefun.common.enums;

/**
 * 员工奖惩类型枚举
 * 
 * @author Administrator
 *
 */
public enum EmployeeRewardTypeEnum {
	/** 迟到 */
	LATE(1), //
	/** 早退 */
	EARLY_LEAVE(2), //
	/** 请假 */
	HOLIDAY(3), //
	/** 旷工 */
	ABSENTEEISM(4),  //
	/** 全勤 */
	ATTENDANCE(5),  //
	/** 小过 */
	SMALL_MISTAKE(6),  //
	/** 大过 */
	SERIOUS_MISTAKE(7),  //
	/** 警告 */
	WARNING(8),  //
	/** 好评 */
	FAVOURABLE(9),  //
	/** 差评 */
	BADPOST(10),  //
	/** 投诉 */
	COMPLAINT(11);  //

	/***/
	private int employeeRewardType;

	/**
	 * 构造
	 * 
	 * @param employeeRewardType
	 *            员工奖惩类型
	 */
	EmployeeRewardTypeEnum(int employeeRewardType) {
		this.employeeRewardType = employeeRewardType;
	}

	public int getEmployeeRewardType() {
		return employeeRewardType;
	}

	public void setEmployeeRewardType(int employeeRewardType) {
		this.employeeRewardType = employeeRewardType;
	}

}
