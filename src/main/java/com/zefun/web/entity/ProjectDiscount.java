package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 项目会员折扣表
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ProjectDiscount {
    /** 折扣标识 */
    private Integer discountId;

    /** 子项目标识 */
    private Integer projectId;

    /** 会员卡标识 */
    private Integer levelId;
    
    /** 折扣比例 */
    private BigDecimal discountProportion;

    /** 会员门店价*/
    private BigDecimal discountAmount;

    /** 在线预约价格 */
    private BigDecimal onlineAppointmentPrice;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** @param discountId	折扣标识 */
    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    /** @return	折扣标识 */
    public Integer getDiscountId() {
        return discountId;
    }

    /** @param projectId	子项目标识 */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /** @return	子项目标识 */
    public Integer getProjectId() {
        return projectId;
    }

    /** @param levelId	会员卡标识 */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /** @return	会员卡标识 */
    public Integer getLevelId() {
        return levelId;
    }
    
    /** @return 折扣比例 */
    public BigDecimal getDiscountProportion() {
        return discountProportion;
    }

    /** @param discountProportion 折扣比例 */
    public void setDiscountProportion(BigDecimal discountProportion) {
        this.discountProportion = discountProportion;
    }

    /**@return 会员门店价*/
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /** @param discountAmount 会员门店价*/
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    /** @return 在线预约价格 */
    public BigDecimal getOnlineAppointmentPrice() {
        return onlineAppointmentPrice;
    }

    /** @param onlineAppointmentPrice 在线预约价格 */
    public void setOnlineAppointmentPrice(BigDecimal onlineAppointmentPrice) {
        this.onlineAppointmentPrice = onlineAppointmentPrice;
    }

    /** @param isDeleted	是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /** @return	是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /** @param createTime	创建时间 */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** @return	创建时间 */
    public String getCreateTime() {
        return createTime;
    }

    /** @param updateTime	修改时间 */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /** @return	修改时间 */
    public String getUpdateTime() {
        return updateTime;
    }

    /** @param lastOperatorId	最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /** @return	最后操作人标识 */
    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

}