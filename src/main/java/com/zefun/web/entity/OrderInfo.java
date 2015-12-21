package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年11月09日 PM 22:54:42
 */
public class OrderInfo {
    /** 订单标识 */
    private Integer orderId;

    /** 订单类型(1:服务类，2:充值类) */
    private Integer orderType;

    /** 会员标识(散客为空) */
    private Integer memberId;

    /** 消费项目性别 */
    private String sex;

    /** 部门标识 */
    private Integer deptId;

    /** 订单号 */
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

    /** 礼金抵扣金额 */
    private BigDecimal giftAmount;

    /** 微信支付金额 */
    private BigDecimal wechatAmount;

    /** 支付宝支付金额 */
    private BigDecimal alipayAmount;

    /** 套餐抵扣 */
    private BigDecimal comboAmount;

    /** 优惠券抵扣 */
    private BigDecimal couponAmount;

    /** 预约优惠金额 */
    private BigDecimal appointOff;

    /** 签单金额 */
    private String freeAmount;

    /** 挂账金额 */
    private BigDecimal debtAmount;

    /** 实际收款 */
    private BigDecimal realAmount;

    /** 订单状态(1：进行中、2：待结账、3：待评价(已结账)、4：已评价、5：已通知买单) */
    private Integer orderStatus;

    /** 门店标识 */
    private Integer storeId;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 预约标识(预约单才有) */
    private Integer appointmentId;

    /** 挂账备注 */
    private String orderEvaluate;
    
    /**是否分享(0:未分享，1:已分享)*/
    private Integer isShare;

    /** @param orderId  订单标识 */
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /** @return 订单标识 */
    public Integer getOrderId(){
        return orderId;
    }

    /** @param orderType    订单类型(1:服务类，2:充值类) */
    public void setOrderType(Integer orderType){
        this.orderType = orderType;
    }

    /** @return 订单类型(1:服务类，2:充值类) */
    public Integer getOrderType(){
        return orderType;
    }

    /** @param memberId 会员标识(散客为空) */
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }

    /** @return 会员标识(散客为空) */
    public Integer getMemberId(){
        return memberId;
    }

    /** @param sex  消费项目性别 */
    public void setSex(String sex){
        this.sex = sex;
    }

    /** @return 消费项目性别 */
    public String getSex(){
        return sex;
    }

    /** @param deptId   部门标识 */
    public void setDeptId(Integer deptId){
        this.deptId = deptId;
    }

    /** @return 部门标识 */
    public Integer getDeptId(){
        return deptId;
    }

    /** @param orderCode    订单号 */
    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }

    /** @return 订单号 */
    public String getOrderCode(){
        return orderCode;
    }

    /** @param receivableAmount 应收金额 */
    public void setReceivableAmount(BigDecimal receivableAmount){
        this.receivableAmount = receivableAmount;
    }

    /** @return 应收金额 */
    public BigDecimal getReceivableAmount(){
        return receivableAmount;
    }

    /** @param discountAmount   折扣金额 */
    public void setDiscountAmount(BigDecimal discountAmount){
        this.discountAmount = discountAmount;
    }

    /** @return 折扣金额 */
    public BigDecimal getDiscountAmount(){
        return discountAmount;
    }

    /** @param cashAmount   现金支付 */
    public void setCashAmount(BigDecimal cashAmount){
        this.cashAmount = cashAmount;
    }

    /** @return 现金支付 */
    public BigDecimal getCashAmount(){
        return cashAmount;
    }

    /** @param unionpayAmount   银联支付 */
    public void setUnionpayAmount(BigDecimal unionpayAmount){
        this.unionpayAmount = unionpayAmount;
    }

    /** @return 银联支付 */
    public BigDecimal getUnionpayAmount(){
        return unionpayAmount;
    }

    /** @param cardAmount   会员卡支付 */
    public void setCardAmount(BigDecimal cardAmount){
        this.cardAmount = cardAmount;
    }

    /** @return 会员卡支付 */
    public BigDecimal getCardAmount(){
        return cardAmount;
    }

    /** @param giftAmount   礼金抵扣金额 */
    public void setGiftAmount(BigDecimal giftAmount){
        this.giftAmount = giftAmount;
    }

    /** @return 礼金抵扣金额 */
    public BigDecimal getGiftAmount(){
        return giftAmount;
    }

    /** @param wechatAmount 微信支付金额 */
    public void setWechatAmount(BigDecimal wechatAmount){
        this.wechatAmount = wechatAmount;
    }

    /** @return 微信支付金额 */
    public BigDecimal getWechatAmount(){
        return wechatAmount;
    }

    /** @param alipayAmount 支付宝支付金额 */
    public void setAlipayAmount(BigDecimal alipayAmount){
        this.alipayAmount = alipayAmount;
    }

    /** @return 支付宝支付金额 */
    public BigDecimal getAlipayAmount(){
        return alipayAmount;
    }

    /** @param comboAmount  套餐抵扣 */
    public void setComboAmount(BigDecimal comboAmount){
        this.comboAmount = comboAmount;
    }

    /** @return 套餐抵扣 */
    public BigDecimal getComboAmount(){
        return comboAmount;
    }

    /** @param couponAmount 优惠券抵扣 */
    public void setCouponAmount(BigDecimal couponAmount){
        this.couponAmount = couponAmount;
    }

    /** @return 优惠券抵扣 */
    public BigDecimal getCouponAmount(){
        return couponAmount;
    }

    public BigDecimal getAppointOff() {
        return appointOff;
    }

    public void setAppointOff(BigDecimal appointOff) {
        this.appointOff = appointOff;
    }

    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    /** @param debtAmount   挂账金额 */
    public void setDebtAmount(BigDecimal debtAmount){
        this.debtAmount = debtAmount;
    }

    /** @return 挂账金额 */
    public BigDecimal getDebtAmount(){
        return debtAmount;
    }

    /**@param realAmount 实际收款 */
    public void setRealAmount(BigDecimal realAmount){
        this.realAmount = realAmount;
    }

    /** @return 实际收款 */
    public BigDecimal getRealAmount(){
        return realAmount;
    }

    /** @param orderStatus  订单状态(1：进行中、2：待结账、3：待评价、4：已完成、5：已通知买单) */
    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }

    /** @return 订单状态(1：进行中、2：待结账、3：待评价、4：已完成、5：已通知买单) */
    public Integer getOrderStatus(){
        return orderStatus;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted(){
        return isDeleted;
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

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }

    /** @param appointmentId    预约标识(预约单才有) */
    public void setAppointmentId(Integer appointmentId){
        this.appointmentId = appointmentId;
    }

    /** @return 预约标识(预约单才有) */
    public Integer getAppointmentId(){
        return appointmentId;
    }

    /** @param orderEvaluate    评价内容 */
    public void setOrderEvaluate(String orderEvaluate){
        this.orderEvaluate = orderEvaluate;
    }

    /** @return 评价内容 */
    public String getOrderEvaluate(){
        return orderEvaluate;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

}