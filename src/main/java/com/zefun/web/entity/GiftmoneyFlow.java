package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 礼金流水
* @author 王大爷
* @date 2015年9月10日 下午4:55:06
 */
public class GiftmoneyFlow {
    /** 礼金流水标识*/
    private Integer flowId;

    /** 礼金账户标识*/
    private Integer accountId;

    /** 明细标识(资金变化的订单标识)*/
    private Integer detailId;

    /** 礼金类型(1:支出,2:收入)*/
    private Integer flowType;
    
    /** 抵扣礼金情况*/
    private String residueMoneyInfo;

    /** 流水金额*/
    private BigDecimal flowAmount;

    /** 业务类型*/
    private String businessType;

    /** 业务描述*/
    private String businessDesc;

    /** 流水时间*/
    private String flowTime;

    /** 是否删除*/
    private Integer isDeleted;

    
    public String getResidueMoneyInfo() {
        return residueMoneyInfo;
    }

    public void setResidueMoneyInfo(String residueMoneyInfo) {
        this.residueMoneyInfo = residueMoneyInfo;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(BigDecimal flowAmount) {
        this.flowAmount = flowAmount;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc == null ? null : businessDesc.trim();
    }

    public String getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(String flowTime) {
        this.flowTime = flowTime == null ? null : flowTime.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}