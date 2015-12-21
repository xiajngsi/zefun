package com.zefun.web.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MemberLevel {
    /** 等级标识 */
    private Integer levelId;

    /** 门店标识 */
    private Integer storeId;

    /** 等级名称 */
    private String levelName;

    /** 项目折扣 */
    private Integer projectDiscount;

    /** 商品折扣 */
    private Integer goodsDiscount;

    /** 充值开卡金额 */
    private Integer chargeAmount;

    /** 售卡开卡金额 */
    private Integer sellAmount;

    /** 最低充值金额 */
    private Integer chargeMinMoney;

    /** 充值金额归属(1:开卡门店,2:充值门店) */
    private Integer chargeBelongType;

    /** 消费积分单位 */
    private Integer integralUnit;

    /** 单位积分数量 */
    private Integer integralNumber;

    /** 升级积分 */
    private Integer upgradeIntegral;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 是否默认等级(0:否,1:是) */
    private Integer isDefault;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** @param levelId  等级标识 */
    public void setLevelId(Integer levelId){
        this.levelId = levelId;
    }

    /** @return 等级标识 */
    public Integer getLevelId(){
        return levelId;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param levelName    等级名称 */
    public void setLevelName(String levelName){
        this.levelName = levelName;
    }

    /** @return 等级名称 */
    public String getLevelName(){
        return levelName;
    }



    public Integer getProjectDiscount() {
        return projectDiscount;
    }

    public void setProjectDiscount(Integer projectDiscount) {
        this.projectDiscount = projectDiscount;
    }

    public Integer getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Integer goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    /** @param chargeAmount 充值开卡金额 */
    public void setChargeAmount(Integer chargeAmount){
        this.chargeAmount = chargeAmount;
    }

    /** @return 充值开卡金额 */
    public Integer getChargeAmount(){
        return chargeAmount;
    }

    /** @param sellAmount   售卡开卡金额 */
    public void setSellAmount(Integer sellAmount){
        this.sellAmount = sellAmount;
    }

    /** @return 售卡开卡金额 */
    public Integer getSellAmount(){
        return sellAmount;
    }

    /** @param chargeMinMoney   最低充值金额 */
    public void setChargeMinMoney(Integer chargeMinMoney){
        this.chargeMinMoney = chargeMinMoney;
    }

    /** @return 最低充值金额 */
    public Integer getChargeMinMoney(){
        return chargeMinMoney;
    }

    /** @param chargeBelongType 充值金额归属(1:开卡门店,2:充值门店) */
    public void setChargeBelongType(Integer chargeBelongType){
        this.chargeBelongType = chargeBelongType;
    }

    /** @return 充值金额归属(1:开卡门店,2:充值门店) */
    public Integer getChargeBelongType(){
        return chargeBelongType;
    }

    /** @param integralUnit 消费积分单位 */
    public void setIntegralUnit(Integer integralUnit){
        this.integralUnit = integralUnit;
    }

    /** @return 消费积分单位 */
    public Integer getIntegralUnit(){
        return integralUnit;
    }

    /** @param integralNumber   单位积分数量 */
    public void setIntegralNumber(Integer integralNumber){
        this.integralNumber = integralNumber;
    }

    /** @return 单位积分数量 */
    public Integer getIntegralNumber(){
        return integralNumber;
    }

    /** @param upgradeIntegral  升级积分 */
    public void setUpgradeIntegral(Integer upgradeIntegral){
        this.upgradeIntegral = upgradeIntegral;
    }

    /** @return 升级积分 */
    public Integer getUpgradeIntegral(){
        return upgradeIntegral;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    /** @param isDefault    是否默认等级(0:否,1:是) */
    public void setIsDefault(Integer isDefault){
        this.isDefault = isDefault;
    }

    /** @return 是否默认等级(0:否,1:是) */
    public Integer getIsDefault(){
        return isDefault;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted(){
        return isDeleted;
    }

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }*/

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "MemberLevel [levelName=" + levelName + ", projectDiscount="
                + projectDiscount + ", goodsDiscount=" + goodsDiscount + "]";
    }

}