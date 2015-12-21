package com.zefun.web.entity;

/**
 * @author 张进军
 * @date 2015年12月06日 PM 12:02:16
 */
public class EmployeeAttendance {
	/** 记录标识 */
	private Integer recordId;

	/** 员工标识 */
	private Integer employeeId;

	/** 考勤日期 */
	private String attendanceDate;

	/** 签到时间 */
	private String signInTime;

	/** 签到时间差(分钟) */
	private Integer signInOffset;

	/** 签退时间 */
	private String signOutTime;

	/** 签退时间差(分钟) */
	private Integer signOutOffset;

	/** 修改人 */
	private String modifyer;

	/** 备注 */
	private String comment;

	/** 员工基本信息 */
	private EmployeeInfo employeeInfo;

	/**
	 * @param recordId
	 *            记录标识
	 */
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	/** @return 记录标识 */
	public Integer getRecordId() {
		return recordId;
	}

	/**
	 * @param employeeId
	 *            员工标识
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/** @return 员工标识 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param attendanceDate
	 *            考勤日期
	 */
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	/** @return 考勤日期 */
	public String getAttendanceDate() {
		return attendanceDate;
	}

	/**
	 * @param signInTime
	 *            签到时间
	 */
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	/** @return 签到时间 */
	public String getSignInTime() {
		return signInTime;
	}

	/**
	 * @param signInOffset
	 *            签到时间差(分钟)
	 */
	public void setSignInOffset(Integer signInOffset) {
		this.signInOffset = signInOffset;
	}

	/** @return 签到时间差(分钟) */
	public Integer getSignInOffset() {
		return signInOffset;
	}

	/**
	 * @param signOutTime
	 *            签退时间
	 */
	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}

	/** @return 签退时间 */
	public String getSignOutTime() {
		return signOutTime;
	}

	/**
	 * @param signOutOffset
	 *            签退时间差(分钟)
	 */
	public void setSignOutOffset(Integer signOutOffset) {
		this.signOutOffset = signOutOffset;
	}

	/** @return 签退时间差(分钟) */
	public Integer getSignOutOffset() {
		return signOutOffset;
	}

	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public String getModifyer() {
		return modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}