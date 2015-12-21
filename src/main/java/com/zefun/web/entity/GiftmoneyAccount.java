package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 礼金账户
* @author 王大爷
* @date 2015年9月10日 下午4:56:21
 */
public class GiftmoneyAccount {
    
    /** 礼金账户标识*/
    private Integer accountId;

    /** 会员标识*/
    private Integer memberId;

    /** 礼金使用部门标识*/
    private Integer deptId;

    /** 礼金总额*/
    private BigDecimal totalAmount;

    /** 礼金余额*/
    private BigDecimal balanceAmount;

    /** 礼金使用金额*/
    private BigDecimal useMoney;

    /** 礼金过期金额*/
    private BigDecimal pastdataMoney;

    /** 创建时间*/
    private String createTime;

    /** 修改时间*/
    private String updateTime;

    /** 最后操作人员*/
    private Integer lastOperatorId;

    
    
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public BigDecimal getPastdataMoney() {
        return pastdataMoney;
    }

    public void setPastdataMoney(BigDecimal pastdataMoney) {
        this.pastdataMoney = pastdataMoney;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}