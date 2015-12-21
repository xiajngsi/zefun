package com.zefun.web.vo;

/**
 * 员工设置：考勤记录VO
 * 
 * @author lzc
 *
 */
public class AttendanceRecordVo {

	/** 门店标识 */
	private Integer storeId;

	/** 员工编号 */
	private Integer employeeCode;

	/** 员工姓名 */
	private String employeeName;

	/** 考勤日期开始 */
	private String attendanceDateBegin;

	/** 考勤日期结束 */
	private String attendanceDateEnd;

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAttendanceDateBegin() {
		return attendanceDateBegin;
	}

	public void setAttendanceDateBegin(String attendanceDateBegin) {
		this.attendanceDateBegin = attendanceDateBegin;
	}

	public String getAttendanceDateEnd() {
		return attendanceDateEnd;
	}

	public void setAttendanceDateEnd(String attendanceDateEnd) {
		this.attendanceDateEnd = attendanceDateEnd;
	}

}
