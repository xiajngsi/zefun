package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 礼金明细
* @author 王大爷
* @date 2015年9月10日 下午4:56:08
 */
public class GiftmoneyDetail {
    /** 礼金明细标识*/
    private Integer detail;

    /** 礼金账户标识*/
    private Integer accountId;
    
    /** 明细标识*/
    private Integer detailId;

    /** 礼金总金额*/
    private BigDecimal totalAmount;

    /** 当期礼金金额*/
    private BigDecimal nowMoney;
    
    /** 当期剩余礼金*/
    private BigDecimal residueNowMoney;

    /** 剩余分期数量*/
    private Integer partNumber;

    /** 分期类型*/
    private Integer partType;

    /** 当期赠送时间*/
    private String startDate;

    /** 当期过期时间*/
    private String endDate;

    /** 创建时间*/
    private String createTime;

    /** 是否删除(0:未删除,1:已删除)*/
    private Integer isDeleted;
    
    /** 是否赠送(0:未赠送,1:已赠送)*/
    private Integer isPresent;

    /** 最后操作人标识*/
    private Integer lastOperatorId;

    
    
    
    public Integer getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Integer isPresent) {
        this.isPresent = isPresent;
    }

    public BigDecimal getResidueNowMoney() {
        return residueNowMoney;
    }

    public void setResidueNowMoney(BigDecimal residueNowMoney) {
        this.residueNowMoney = residueNowMoney;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(BigDecimal nowMoney) {
        this.nowMoney = nowMoney;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getPartType() {
        return partType;
    }

    public void setPartType(Integer partType) {
        this.partType = partType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}