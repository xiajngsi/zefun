package com.zefun.web.entity;


/**
 * 
* @author chendb
* @date 2015年9月21日 下午1:58:18
 */
public class LeaveInfo {
    /**请假标识*/
    private Integer leaveId;
    /**部门标识*/
    private Integer storeId;
    /**请假类型*/
    private String leaveType;
    /**员工标识*/
    private Integer employeeId;
    /**请假申请时间*/
    private String applytime;
    /**请假开始时间*/
    private String startTime;
    /**请假结束时间*/
    private String endTime;
    /**请假时长*/
    private String duration;
    /**请假审批状态*/
    private Integer leaveState;
    /**请假审批人*/
    private Integer approvalId;
    /**请假审批时间*/
    private String approvalTime;
    /**请假审批描述*/
    private String approvalDes;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType == null ? null : leaveType.trim();
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime == null ? null : applytime.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public Integer getLeaveState() {
        return leaveState;
    }

    public void setLeaveState(Integer leaveState) {
        this.leaveState = leaveState;
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime == null ? null : approvalTime.trim();
    }

    public String getApprovalDes() {
        return approvalDes;
    }

    public void setApprovalDes(String approvalDes) {
        this.approvalDes = approvalDes == null ? null : approvalDes.trim();
    }

}