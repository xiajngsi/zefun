package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员订单信息传输对象
* @author 张进军
* @date Oct 24, 2015 1:51:50 PM 
*/
public class MemberOrderDto {
    /** 订单标识 */
    private Integer orderId;
    
    /** 会员订单号*/
    private String orderCode;

    /** 应收金额 */
    private BigDecimal receivableAmount;

    /** 折扣金额 */
    private BigDecimal discountAmount;

    /** 现金支付 */
    private BigDecimal cashAmount;

    /** 银联支付 */
    private BigDecimal unionpayAmount;

    /** 会员卡支付 */
    private BigDecimal cardAmount;

    /** 套餐抵扣 */
    private BigDecimal comboAmount;

    /** 优惠券抵扣 */
    private BigDecimal couponAmount;

    /** 签单金额 */
    private String freeAmount;

    /** 挂账金额 */
    private BigDecimal debtAmount;
    
    /** 预约标识 */
    private Integer appointmentId;

    /** 修改时间 */
    private String updateTime;
    
    /** 订单状态(1：进行中、2：待结账、3：待评价、4：已完成)*/
    private Integer orderStatus;
    
    /**是否分享(0:未分享，1:已分享)*/
    private Integer isShare;
    
    /**实收金额*/
    private BigDecimal realAmount;
    
    /**明细列表*/
    private List<OrderDetailDto> detailList;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getUnionpayAmount() {
        return unionpayAmount;
    }

    public void setUnionpayAmount(BigDecimal unionpayAmount) {
        this.unionpayAmount = unionpayAmount;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public BigDecimal getComboAmount() {
        return comboAmount;
    }

    public void setComboAmount(BigDecimal comboAmount) {
        this.comboAmount = comboAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }



    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public List<OrderDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OrderDetailDto> detailList) {
        this.detailList = detailList;
    }
    
}
