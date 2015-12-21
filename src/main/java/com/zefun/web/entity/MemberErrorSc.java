package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 会员导入,盛传异常数据
* @author 高国藩
* @date 2015年12月4日 下午4:45:51
 */
public class MemberErrorSc {
    
    /***/
    private Integer id;
    /***/
    private String phone;
    /***/
    private String name;
    /***/
    private String sex;
    /***/
    private String createTime;
    /**卡号*/
    private String levelNum;
    /***/
    private String levelName;
    /**卡类型*/
    private String levelType;
    /**折扣*/
    private BigDecimal discount;
    /**储值总额*/
    private BigDecimal totalAmount;
    /**消费总额*/
    private BigDecimal totalConsumeAmount;
    /**卡内总余额*/
    private BigDecimal balanceAmount;
    /**赠送总余额*/
    private BigDecimal sendAmount;
    /**失效日期*/
    private String aeadTime;
    /**消费次数*/
    private Integer consumeAmount;
    /**当前积分*/
    private Integer balanceIntegral;
    /**最后消费日*/
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum == null ? null : levelNum.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType == null ? null : levelType.trim();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalConsumeAmount() {
        return totalConsumeAmount;
    }

    public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
        this.totalConsumeAmount = totalConsumeAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
    }

    public String getAeadTime() {
        return aeadTime;
    }

    public void setAeadTime(String aeadTime) {
        this.aeadTime = aeadTime == null ? null : aeadTime.trim();
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(Integer balanceIntegral) {
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