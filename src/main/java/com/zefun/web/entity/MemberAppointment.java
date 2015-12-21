package com.zefun.web.entity;

import java.math.BigDecimal;


/**
 * @author 张进军
 * @date 2015年11月23日 PM 22:50:08
 */
public class MemberAppointment {
    /** 预约标识 */
    private Integer appointmentId;

    /** 门店标识 */
    private Integer storeId;

    /** 会员标识 */
    private Integer memberId;

    /** 预约姓名 */
    private String name;

    /** 预约电话 */
    private String phone;

    /** 预约员工标识 */
    private Integer employeeId;

    /** 预约项目标识 */
    private Integer projectId;

    /** 服务步骤序号 */
    private Integer projectStepOrder;

    /** 服务轮牌标识 */
    private Integer shiftMahjongId;

    /** 预约日期 */
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

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** @param appointmentId    预约标识 */
    public void setAppointmentId(Integer appointmentId){
        this.appointmentId = appointmentId;
    }

    /** @return 预约标识 */
    public Integer getAppointmentId(){
        return appointmentId;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param memberId 会员标识 */
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }

    /** @return 会员标识 */
    public Integer getMemberId(){
        return memberId;
    }

    /** @param name 预约姓名 */
    public void setName(String name){
        this.name = name;
    }

    /** @return 预约姓名 */
    public String getName(){
        return name;
    }

    /** @param phone    预约电话 */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /** @return 预约电话 */
    public String getPhone(){
        return phone;
    }

    /** @param employeeId   预约员工标识 */
    public void setEmployeeId(Integer employeeId){
        this.employeeId = employeeId;
    }

    /** @return 预约员工标识 */
    public Integer getEmployeeId(){
        return employeeId;
    }

    /** @param projectId    预约项目标识 */
    public void setProjectId(Integer projectId){
        this.projectId = projectId;
    }

    /** @return 预约项目标识 */
    public Integer getProjectId(){
        return projectId;
    }

    /** @param projectStepOrder 服务步骤序号 */
    public void setProjectStepOrder(Integer projectStepOrder){
        this.projectStepOrder = projectStepOrder;
    }

    /** @return 服务步骤序号 */
    public Integer getProjectStepOrder(){
        return projectStepOrder;
    }

    /** @param shiftMahjongId   服务轮牌标识 */
    public void setShiftMahjongId(Integer shiftMahjongId){
        this.shiftMahjongId = shiftMahjongId;
    }

    /** @return 服务轮牌标识 */
    public Integer getShiftMahjongId(){
        return shiftMahjongId;
    }

    /** @param appointmentDate  预约日期 */
    public void setAppointmentDate(String appointmentDate){
        this.appointmentDate = appointmentDate;
    }

    /** @return 预约日期 */
    public String getAppointmentDate(){
        return appointmentDate;
    }

    /** @param appointmentTime  预约时间 */
    public void setAppointmentTime(String appointmentTime){
        this.appointmentTime = appointmentTime;
    }

    /** @return 预约时间 */
    public String getAppointmentTime(){
        return appointmentTime;
    }

    public BigDecimal getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(BigDecimal appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    /** @param serviceTime  服务时间 */
    public void setServiceTime(String serviceTime){
        this.serviceTime = serviceTime;
    }

    /** @return 服务时间 */
    public String getServiceTime(){
        return serviceTime;
    }

    /** @param appointmentStatus    预约状态(1:预约中,2:已确认,3:已服务,4:已取消,5:已拒绝) */
    public void setAppointmentStatus(Integer appointmentStatus){
        this.appointmentStatus = appointmentStatus;
    }

    /** @return 预约状态(1:预约中,2:已确认,3:已服务,4:已取消,5:已拒绝) */
    public Integer getAppointmentStatus(){
        return appointmentStatus;
    }

    /** @param cancelTime   取消时间 */
    public void setCancelTime(String cancelTime){
        this.cancelTime = cancelTime;
    }

    /** @return 取消时间 */
    public String getCancelTime(){
        return cancelTime;
    }

    /** @param cancelReason 取消原因 */
    public void setCancelReason(String cancelReason){
        this.cancelReason = cancelReason;
    }

    /** @return 取消原因 */
    public String getCancelReason(){
        return cancelReason;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted(){
        return isDeleted;
    }

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }

}