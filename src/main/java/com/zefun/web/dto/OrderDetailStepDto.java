package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 项目服务步骤轮牌记录信息
* @author 张进军
* @date Oct 13, 2015 2:54:20 PM 
*/
public class OrderDetailStepDto {
    /** 轮牌步骤标识*/
    private Integer shiftMahjongStepId;
    /** 步骤标识 */
    private Integer projectStepId;
    /** 步骤名称*/
    private String projectStepName;
    /** 轮牌标识*/
    private Integer shiftMahjongId;
    /** 轮牌名称 */
    private String shiftMahjongName;
    
    /** 服务开始时间 */
    private String beginTime;

    /** 服务结束时间 */
    private String finishTime;
    
    /** 是否指定 */
    private Integer isAssign;
    
    /** 是否指派(0：否  1：是)*/
    private Integer isDesignate;
    
    /** 是否预约 */
    private Integer isAppoint;

    /** 是否结束(0：否  1：是) */
    private Integer isOver;
    
    /** 是否删除*/
    private Integer isDeleted; 
    
    /** 服务员工信息 */
    private EmployeeBaseDto employeeInfo;

    /** 业绩计算*/
    private BigDecimal commissionCalculate;
    
    /** 提成金额*/
    private BigDecimal commissionAmount;
    
    /** 提成标识*/
    private Integer commissionId;
    
    
    
    public Integer getIsDesignate() {
        return isDesignate;
    }

    public void setIsDesignate(Integer isDesignate) {
        this.isDesignate = isDesignate;
    }

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public BigDecimal getCommissionCalculate() {
        return commissionCalculate;
    }

    public void setCommissionCalculate(BigDecimal commissionCalculate) {
        this.commissionCalculate = commissionCalculate;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getProjectStepName() {
        return projectStepName;
    }

    public void setProjectStepName(String projectStepName) {
        this.projectStepName = projectStepName;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    public Integer getShiftMahjongStepId() {
        return shiftMahjongStepId;
    }

    public void setShiftMahjongStepId(Integer shiftMahjongStepId) {
        this.shiftMahjongStepId = shiftMahjongStepId;
    }

    public Integer getProjectStepId() {
        return projectStepId;
    }

    public void setProjectStepId(Integer projectStepId) {
        this.projectStepId = projectStepId;
    }

    public String getShiftMahjongName() {
        return shiftMahjongName;
    }

    public void setShiftMahjongName(String shiftMahjongName) {
        this.shiftMahjongName = shiftMahjongName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Integer isAssign) {
        this.isAssign = isAssign;
    }

    public Integer getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(Integer isAppoint) {
        this.isAppoint = isAppoint;
    }

    public Integer getIsOver() {
        return isOver;
    }

    public void setIsOver(Integer isOver) {
        this.isOver = isOver;
    }

    public EmployeeBaseDto getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeBaseDto employeeInfo) {
        this.employeeInfo = employeeInfo;
    }
    
}
