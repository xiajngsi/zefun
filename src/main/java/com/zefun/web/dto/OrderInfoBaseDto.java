package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单列表中的订单传输对象
* @author 张进军
* @date Oct 13, 2015 10:53:31 AM 
*/
public class OrderInfoBaseDto {

    /** 门店标识 */
    private Integer storeId;
    
    /** 订单标识 */
    private Integer orderId;
    
    /** 会员标识 */
    private Integer memberId;
    
    /** 性别*/
    private String sex;
    
    /** 部门标识*/
    private Integer deptId;
    
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
    /** 微信支付金额*/
    private BigDecimal wechatAmount;
    
    /** 支付宝支付金额*/
    private BigDecimal alipayAmount;
    
    /** 礼金抵扣金额*/
    private BigDecimal giftAmount;
    
    /** 会员卡支付 */
    private BigDecimal cardAmount;
    
    /** 预约优惠金额*/
    private BigDecimal appointOff;

    /** 套餐抵扣 */
    private BigDecimal comboAmount;

    /** 优惠券抵扣 */
    private BigDecimal couponAmount;

    /** 签单金额 */
    private String freeAmount;

    /** 挂账金额 */
    private BigDecimal debtAmount;
    
    /** 实际收款*/
    private BigDecimal realAmount;
    
    /** 预约标识 */
    private Integer appointmentId;
    
    /** 评价信息 */
    private String orderEvaluate;

    /** 修改时间 */
    private String updateTime;
    
    /** 订单状态(1：进行中、2：待结账、3：待评价(已结账)、4：已评价、5：已通知买单)*/
    private Integer orderStatus;
    
    /** 会员信息 */
    private MemberBaseDto memberInfo;
    
    /** 订单明细 */
    private List<OrderDetailDto> orderDetailList;

    
    
    
    public BigDecimal getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
    }


    public BigDecimal getAppointOff() {
        return appointOff;
    }

    public void setAppointOff(BigDecimal appointOff) {
        this.appointOff = appointOff;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getWechatAmount() {
        return wechatAmount;
    }

    public void setWechatAmount(BigDecimal wechatAmount) {
        this.wechatAmount = wechatAmount;
    }

    public BigDecimal getAlipayAmount() {
        return alipayAmount;
    }

    public void setAlipayAmount(BigDecimal alipayAmount) {
        this.alipayAmount = alipayAmount;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public MemberBaseDto getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberBaseDto memberInfo) {
        this.memberInfo = memberInfo;
    }

    public List<OrderDetailDto> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailDto> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getOrderEvaluate() {
        return orderEvaluate;
    }

    public void setOrderEvaluate(String orderEvaluate) {
        this.orderEvaluate = orderEvaluate;
    }
    
}
