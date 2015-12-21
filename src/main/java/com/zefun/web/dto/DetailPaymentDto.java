package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 单个订单明细支付明细传输对象
* @author 张进军
* @date Nov 9, 2015 9:46:49 PM 
*/
public class DetailPaymentDto {
    /** 明细标识 */
    private Integer detailId;

    /** 订单类型(1:项目,2:商品,3:套餐) */
    private Integer orderType;

    /** 是否预约(1:是,2:否) */
    private Integer isAppoint;
    
    /** 预约优惠金额 */
    private BigDecimal appointOff;

    /** 项目标识 */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目价格 */
    private BigDecimal projectPrice;

    /** 项目数量 */
    private Integer projectCount;

    /** 项目实际金额 */
    private BigDecimal realPrice;

    /** 折扣价格 */
    private BigDecimal discountAmount;

    /** 优惠类型(具体套餐抵扣，优惠券抵扣，礼金抵扣) */
    private String offType;
    
    /** 优惠金额 */
    private BigDecimal offAmount;

    /** 服务时长 */
    private Integer serviceLength;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(Integer isAppoint) {
        this.isAppoint = isAppoint;
    }


    public BigDecimal getAppointOff() {
        return appointOff;
    }

    public void setAppointOff(BigDecimal appointOff) {
        this.appointOff = appointOff;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getOffType() {
        return offType;
    }

    public void setOffType(String offType) {
        this.offType = offType;
    }

    public BigDecimal getOffAmount() {
        return offAmount;
    }

    public void setOffAmount(BigDecimal offAmount) {
        this.offAmount = offAmount;
    }

    public Integer getServiceLength() {
        return serviceLength;
    }

    public void setServiceLength(Integer serviceLength) {
        this.serviceLength = serviceLength;
    }
    
}
