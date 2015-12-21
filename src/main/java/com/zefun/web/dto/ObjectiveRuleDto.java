package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 关于目标考勤
* @author chendb
* @date 2015年9月2日 下午4:38:34
 */
public class ObjectiveRuleDto {

    /**目标奖惩规则标识*/
    private Integer ruleId;
    /**目标项目*/
    private Integer objectiveProject;
    /**目标项目名称*/
    private String projectName;
    /**按金额按比例*/
    private Integer choiceType;
    /**按金额按比例 1按金额  2 按比例*/
    private String choiceTypeName;
    /**对比大小  大于  小于  等于*/
    private Integer contrastType;
    /**对比大小  大于  小于  等于*/
    private String contrastTypeName;
    /**区间*/
    private BigDecimal section;
    /**奖惩类型*/
    private Integer rewards;
    /**奖惩类型名称*/
    private String rewardsName;
    /**奖惩方式*/
    private Integer type;
    /**奖惩方式*/
    private String typeName;
    /**金额*/
    private BigDecimal money;
    
   
    public Integer getChoiceType() {
        return choiceType;
    }
    public void setChoiceType(Integer choiceType) {
        this.choiceType = choiceType;
    }
   
    public String getChoiceTypeName() {
        return choiceTypeName;
    }
    public void setChoiceTypeName(String choiceTypeName) {
        this.choiceTypeName = choiceTypeName;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getContrastTypeName() {
        return contrastTypeName;
    }
    public void setContrastTypeName(String contrastTypeName) {
        this.contrastTypeName = contrastTypeName;
    }
    public String getRewardsName() {
        return rewardsName;
    }
    public void setRewardsName(String rewardsName) {
        this.rewardsName = rewardsName;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
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
