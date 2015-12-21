package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单提交数据对象
* @author 张进军
* @date Nov 11, 2015 8:21:22 PM 
*/
public class OrderInfoSubmitDto {
    /** 订单标识 */
    private Integer orderId;
    
    /** 现金支付 */
    private BigDecimal cashAmount;

    /** 银联支付 */
    private BigDecimal unionpayAmount;

    /** 会员卡支付 */
    private BigDecimal cardAmount;

    /** 微信支付金额 */
    private BigDecimal wechatAmount;

    /** 支付宝支付金额 */
    private BigDecimal alipayAmount;
    
    /** 明细支付信息 */
    private List<OrderDetaiSubmitDto> detailList;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public List<OrderDetaiSubmitDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OrderDetaiSubmitDto> detailList) {
        this.detailList = detailList;
    }
    
}
