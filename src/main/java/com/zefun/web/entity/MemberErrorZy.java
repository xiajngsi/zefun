package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 左右导入会员数据异常
* @author 高国藩
* @date 2015年12月8日 上午11:28:05
 */
public class MemberErrorZy {
    
    /***/
    private Integer id;
    /***/
    private String phone;
    /***/
    private String name;
    /***/
    private String sex;
    /***/
    private String levelName;
    /***/
    private String levelNum;
    /**卡内总余额*/
    private BigDecimal balanceAmount;
    /***/
    private Integer storeId;
    /**最后操作人*/
    private Integer lastOperatorId;
    /**最后操作人*/
    private Integer isDeleted;
    /**修改时间*/
    private String updateTime;
    /**当前储值余额*/
    private BigDecimal balanceAmounts;
    
    public BigDecimal getBalanceAmounts() {
        return balanceAmounts;
    }

    public void setBalanceAmounts(BigDecimal balanceAmounts) {
        this.balanceAmounts = balanceAmounts;
    }

    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}