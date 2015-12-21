package com.zefun.web.dto;

/**
 * 套餐项目提成Dto
* @author 洪秋霞
* @date 2015年9月25日 下午2:04:14
 */
public class ComboProjectCommissionDto {
    /** 标识 */
    private Integer commissionId;

    /** 套餐Id */
    private Integer comboId;

    /** 项目Id */
    private Integer projectId;
    
    /** 职位标识 */
    private Integer levelId;

    /** 指定客现金方式(1:按业绩比例,2:按固定金额) */
    private Integer assignCashType;

    /** 指定客现金 */
    private Integer assignCash;

    /** 指定客刷卡方式(1:按业绩比例,2:按固定金额) */
    private Integer assignCardType;

    /** 指定客刷卡 */
    private Integer assignCard;
    
    /** 指定客现金方式(1:按业绩比例,2:按固定金额) */
    private Integer nonAssignCashType;

    /** 指定客现金 */
    private Integer nonAssignCash;

    /** 指定客刷卡方式(1:按业绩比例,2:按固定金额) */
    private Integer nonAssignCardType;

    /** 指定客刷卡 */
    private Integer nonAssignCard;
    
    /** 预约奖励方式(1：折扣，2：金额) */
    private Integer appointmentRewardType;
    
    /** 奖励金额 */
    private Integer appointmentReward;
    
    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    
    /** 级别名称 */
    private String levelName;

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getAssignCashType() {
        return assignCashType;
    }

    public void setAssignCashType(Integer assignCashType) {
        this.assignCashType = assignCashType;
    }


    public Integer getAssignCardType() {
        return assignCardType;
    }

    public void setAssignCardType(Integer assignCardType) {
        this.assignCardType = assignCardType;
    }

    public Integer getNonAssignCashType() {
        return nonAssignCashType;
    }

    public void setNonAssignCashType(Integer nonAssignCashType) {
        this.nonAssignCashType = nonAssignCashType;
    }

    public Integer getNonAssignCardType() {
        return nonAssignCardType;
    }

    public void setNonAssignCardType(Integer nonAssignCardType) {
        this.nonAssignCardType = nonAssignCardType;
    }

    public Integer getAppointmentRewardType() {
        return appointmentRewardType;
    }

    public void setAppointmentRewardType(Integer appointmentRewardType) {
        this.appointmentRewardType = appointmentRewardType;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
    
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getAssignCash() {
        return assignCash;
    }

    public void setAssignCash(Integer assignCash) {
        this.assignCash = assignCash;
    }

    public Integer getAssignCard() {
        return assignCard;
    }

    public void setAssignCard(Integer assignCard) {
        this.assignCard = assignCard;
    }

    public Integer getNonAssignCash() {
        return nonAssignCash;
    }

    public void setNonAssignCash(Integer nonAssignCash) {
        this.nonAssignCash = nonAssignCash;
    }

    public Integer getNonAssignCard() {
        return nonAssignCard;
    }

    public void setNonAssignCard(Integer nonAssignCard) {
        this.nonAssignCard = nonAssignCard;
    }

    public Integer getAppointmentReward() {
        return appointmentReward;
    }

    public void setAppointmentReward(Integer appointmentReward) {
        this.appointmentReward = appointmentReward;
    }
    
    
}
