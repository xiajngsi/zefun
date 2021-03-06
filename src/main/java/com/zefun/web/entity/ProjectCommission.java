package com.zefun.web.entity;


/**
 * 项目职位提成
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ProjectCommission {
    /** 提成标识 */
    private Integer commissionId;
    
    /** 轮牌标识*/
    private Integer shiftMahjongId;

    /** 项目标识 */
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

    
    
    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    /** @param commissionId	提成标识 */
    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    /** @return	提成标识 */
    public Integer getCommissionId() {
        return commissionId;
    }

    /** @param projectId	项目标识 */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /** @return	项目标识 */
    public Integer getProjectId() {
        return projectId;
    }

    /** @param levelId	职位标识 */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /** @return	职位标识 */
    public Integer getLevelId() {
        return levelId;
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
    
    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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