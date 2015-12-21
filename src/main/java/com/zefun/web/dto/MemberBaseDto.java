package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 会员信息基础对象
* @author 张进军
* @date Aug 19, 2015 4:44:42 PM 
*/
public class MemberBaseDto {
    /** 会员标识 */
    private Integer memberId;
    
    /** 所属门店标识 */
    private Integer storeId;

    /** 门店名称 */
    private String storeName;

    /** 级别名称 */
    private String levelName;
    
    /** 级别标识*/
    private Integer levelId;

    /** 姓名 */
    private String name;
    
    /** 头像 */
    private String headUrl;

    /** 性别 */
    private String sex;

    /** 生日 */
    private String birthday;

    /** 手机号码 */
    private String phone;

    /** 所住小区 */
    private String community;
    
    /** 储值余额*/
    private BigDecimal balanceAmount;
    
    /** 礼金余额 */
    private BigDecimal giftmoneyAmount;
    
    /** 积分余额 */
    private Integer balanceIntegral;

    /** 单次消费均价 */
    private BigDecimal avgConsumeAmount;

    /** 最后消费时间 */
    private String lastConsumeTime;

    /** 平均消费频率(天) */
    private Integer avgConsumeDays;
    
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(Integer balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public BigDecimal getAvgConsumeAmount() {
        return avgConsumeAmount;
    }

    public void setAvgConsumeAmount(BigDecimal avgConsumeAmount) {
        this.avgConsumeAmount = avgConsumeAmount;
    }

    public String getLastConsumeTime() {
        return lastConsumeTime;
    }

    public void setLastConsumeTime(String lastConsumeTime) {
        this.lastConsumeTime = lastConsumeTime;
    }

    public Integer getAvgConsumeDays() {
        return avgConsumeDays;
    }

    public void setAvgConsumeDays(Integer avgConsumeDays) {
        this.avgConsumeDays = avgConsumeDays;
    }

    public BigDecimal getGiftmoneyAmount() {
        return giftmoneyAmount;
    }

    public void setGiftmoneyAmount(BigDecimal giftmoneyAmount) {
        this.giftmoneyAmount = giftmoneyAmount;
    }
    
}
