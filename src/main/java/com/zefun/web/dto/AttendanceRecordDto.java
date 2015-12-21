package com.zefun.web.dto;

import com.zefun.web.entity.EmployeeInfo;

/**
 * 员工设置：考勤记录DTO
 * 
 * @author lzc
 *
 */
public class AttendanceRecordDto {

	/** 考勤记录id */
	private Integer recordId;

	/** 员工id */
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

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	public Integer getSignInOffset() {
		return signInOffset;
	}

	public void setSignInOffset(Integer signInOffset) {
		this.signInOffset = signInOffset;
	}

	public String getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}

	public Integer getSignOutOffset() {
		return signOutOffset;
	}

	public void setSignOutOffset(Integer signOutOffset) {
		this.signOutOffset = signOutOffset;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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
