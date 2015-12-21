package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 会员预约基础传输对象
* @author 张进军
* @date Oct 21, 2015 10:19:04 PM 
*/
public class AppointmentBaseDto {
    /** 预约标识 */
    private Integer appointmentId;

    /** 会员标识 */
    private Integer memberId;
    
    /**预约姓名*/
    private String name;
    
    /**预约电话*/
    private String phone;

    /** 预约员工标识 */
    private Integer employeeId;

    /** 预约项目标识 */
    private Integer projectId;
    
    /** 服务步骤序号 */
    private Integer projectStepOrder;
    
    /** 服务轮牌标识 */
    private Integer shiftMahjongId;

    /**服务轮牌名称*/
    private String shiftMahjongName;

    /**预约日期*/
    private String appointmentDate;
    
    /** 预约时间 */
    private String appointmentTime;

    /** 预约价格 */
    private BigDecimal appointmentPrice;
    
    /** 服务时间 */
    private String serviceTime;

    /** 预约状态(1:预约中,2:已确认,3:已服务,4:已取消,5:已拒绝) */
    private Integer appointmentStatus;

    /** 取消时间 */
    private String cancelTime;

    /** 取消原因 */
    private String cancelReason;
    
    /** 下单时间 */
    private String createTime;
    
    /** 会员基本信息 */
    private MemberBaseDto memberInfo;
    
    /** 预约员工信息 */
    private EmployeeBaseDto employeeInfo;
    
    /** 预约项目信息 */
    private ProjectBaseDto projectInfo;

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectStepOrder() {
        return projectStepOrder;
    }

    public void setProjectStepOrder(Integer projectStepOrder) {
        this.projectStepOrder = projectStepOrder;
    }

    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    public String getShiftMahjongName() {
        return shiftMahjongName;
    }

    public void setShiftMahjongName(String shiftMahjongName) {
        this.shiftMahjongName = shiftMahjongName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public BigDecimal getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(BigDecimal appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public MemberBaseDto getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberBaseDto memberInfo) {
        this.memberInfo = memberInfo;
    }

    public EmployeeBaseDto getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeBaseDto employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public ProjectBaseDto getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectBaseDto projectInfo) {
        this.projectInfo = projectInfo;
    }
    
    
}
