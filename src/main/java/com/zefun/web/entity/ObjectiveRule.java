package com.zefun.web.entity;

import java.math.BigDecimal;
/**
 * 目标奖惩规则
* @author chendb
* @date 2015年9月2日 下午3:03:29
 */
public class ObjectiveRule {
    /**目标奖惩规则标识*/
    private Integer ruleId;
    /**目标项目*/
    private Integer objectiveProject;
    /**对比大小  大于  小于  等于*/
    private Integer contrastType;
    /**区间*/
    private BigDecimal section;
    /**奖惩类型*/
    private Integer rewards;
    /**奖惩方式*/
    private Integer type;
    /**金额*/
    private BigDecimal money;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getObjectiveProject() {
        return objectiveProject;
    }

    public void setObjectiveProject(Integer objectiveProject) {
        this.objectiveProject = objectiveProject;
    }

    public Integer getContrastType() {
        return contrastType;
    }

    public void setContrastType(Integer contrastType) {
        this.contrastType = contrastType;
    }

    public BigDecimal getSection() {
        return section;
    }

    public void setSection(BigDecimal section) {
        this.section = section;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}