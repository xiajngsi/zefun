package com.zefun.web.dto;


import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.ProjectStep;
/**
 * 项目服务步骤轮牌记录信息
* @author 王大爷
* @date 2015年8月24日 下午3:31:05
 */
public class ShiftMahjongProjectStepDto {
    
    /** 轮牌信息标识 */
    private Integer shiftMahjongStepId;

    /** 项目步骤标识 */
    private Integer projectStepId;

    /** 订单明细*/
    private Integer detailId;
    
    /** 服务开始时间 */
    private String beginTime;

    /** 服务结束时间 */
    private String finishTime;
    
    /** 员工标识*/
    private Integer employeeId;
    
    /** 是否指定*/
    private Integer isAppoint;
    
    /** 是否指派(0：否  1：是)*/
    private Integer isDesignate;
    
    /** 是否指定 */
    private Integer isAssign;

    /** 步骤状态(1：服务中、2：等待中、4：已结束) */
    private Integer isOver;
    
    /** 是否删除*/
    private Integer isDeleted; 

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    
    /** 项目步骤*/
    private ProjectStep projectStep;
    
    /** 订单明细*/
    private OrderDetail orderDetail;

    
    
    
    public Integer getIsDesignate() {
        return isDesignate;
    }

    public void setIsDesignate(Integer isDesignate) {
        this.isDesignate = isDesignate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Integer isAssign) {
        this.isAssign = isAssign;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
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

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public ProjectStep getProjectStep() {
        return projectStep;
    }

    public void setProjectStep(ProjectStep projectStep) {
        this.projectStep = projectStep;
    }

}
