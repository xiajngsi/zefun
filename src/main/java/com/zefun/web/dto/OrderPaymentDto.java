package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 整笔订单信息支付明细传输对象
* @author 张进军
* @date Nov 9, 2015 5:40:49 PM 
*/
public class OrderPaymentDto {
    /** 订单标识 */
    private Integer orderId;
    
    /** 门店标识 */
    private Integer storeId;
    
    /** 订单类型(1:服务类，2:充值类) */
    private Integer orderType;

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

    /** 创建时间 */
    private String createTime;
    
    /** 明细列表 */
    private List<DetailPaymentDto> detailList;
    
    /** @param orderId  订单标识 */
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /** @return 订单标识 */
    public Integer getOrderId(){
        return orderId;
    }
    
    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param orderType    订单类型(1:服务类，2:充值类) */
    public void setOrderType(Integer orderType){
        this.orderType = orderType;
    }

    /** @return 订单类型(1:服务类，2:充值类) */
    public Integer getOrderType(){
        return orderType;
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

    /** @param realAmount 实际收款 */
    public void setRealAmount(BigDecimal realAmount){
        this.realAmount = realAmount;
    }

    /** @return 实际收款 */
    public BigDecimal getRealAmount(){
        return realAmount;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    public List<DetailPaymentDto> getDetailList() {
        return detailList;
    }

    public void setDeailList(List<DetailPaymentDto> detailList) {
        this.detailList = detailList;
    }
}
