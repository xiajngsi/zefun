package com.zefun.web.dto;

import java.math.BigDecimal;


/**
* @author laowang
* @date Nov 7, 2015 10:01:02 PM 
*/
public class OrderProjectPageDto {
    /** 明细标识*/
    private Integer detailId;
    /** 订单编号*/
    private String orderCode;
    /** 项目名称*/
    private String projectName;
    /** 应付金额*/
    private BigDecimal discountAmount;
    /** 实际价格*/
    private BigDecimal realPrice;
    /** 创建时间*/
    private String createTime;
    /** 门店名称*/
    private String storeName;
    /** 优惠信息*/
    private String privilegeInfo;
    /** 优惠金额*/
    private BigDecimal privilegeMoney;
    /** 折扣类型(1:套餐，2:优惠券，3:礼金)*/
    private Integer offType;
    /** 销售人员*/
    private String lastOperatorName;
    
    
    
    public String getLastOperatorName() {
        return lastOperatorName;
    }
    public void setLastOperatorName(String lastOperatorName) {
        this.lastOperatorName = lastOperatorName;
    }
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    public BigDecimal getPrivilegeMoney() {
        return privilegeMoney;
    }
    public void setPrivilegeMoney(BigDecimal privilegeMoney) {
        this.privilegeMoney = privilegeMoney;
    }
    public Integer getOffType() {
        return offType;
    }
    public void setOffType(Integer offType) {
        this.offType = offType;
    }
    public String getPrivilegeInfo() {
        return privilegeInfo;
    }
    public void setPrivilegeInfo(String privilegeInfo) {
        this.privilegeInfo = privilegeInfo;
    }
    public Integer getDetailId() {
        return detailId;
    }
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }
    public String getOrderCode() {
        return orderCode;
    }
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public BigDecimal getRealPrice() {
        return realPrice;
    }
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
}
