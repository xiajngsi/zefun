package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 云浩企汇通-会员导入错误数据
* @author 高国藩
* @date 2015年12月6日 下午7:26:27
 */
public class MemberErrorHt {
    
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
    /** 储值余额 */
    private BigDecimal balanceAmount;
    /** 礼金储值余额 */
    private BigDecimal balanceGiftmoneyAmount;
    /** 累计消费次数 */
    private Integer consumeCount;
    /** 积分余额 */
    private BigDecimal balanceIntegral;
    /** 最后消费日期*/
    private String lastConsumeTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getBalanceGiftmoneyAmount() {
        return balanceGiftmoneyAmount;
    }

    public void setBalanceGiftmoneyAmount(BigDecimal balanceGiftmoneyAmount) {
        this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
    }

    public Integer getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(Integer consumeCount) {
        this.consumeCount = consumeCount;
    }

    public BigDecimal getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(BigDecimal balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public String getLastConsumeTime() {
        return lastConsumeTime;
    }

    public void setLastConsumeTime(String lastConsumeTime) {
        this.lastConsumeTime = lastConsumeTime == null ? null : lastConsumeTime.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}