package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

import com.zefun.web.entity.EmployeeInfo;

/**
 * 职位信息
* @author 陈端斌
* @date 2015年8月6日 下午2:26:49 
*
 */
public class EmployeeLevelDto {
    
    /** 级别标识 */
    private Integer levelId;

    /** 门店标识 */
    private Integer storeId;

    /** 岗位标识 */
    private Integer positionId;

    /** 级别名称 */
    private String levelName;

    /** 指定提成类别（1固定金额、2业绩比例） */
    private Integer assignType;

    /** 指定提成比例 */
    private Integer assignCommission;

    /** 非指定提成类别（1固定金额、2业绩比例） */
    private Integer nonAssignType;

    /** 非指定提成比例 */
    private Integer nonAssignCommission;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 岗位开启升级(0没开启 1开启) */
    private Integer isUpgrade;

    /** 非指定客单价 */
    private Integer nonCustomercost;

    /** 指定客单价 */
    private Integer customercost;

    /** 业绩总额 */
    private BigDecimal performancecount;

    /** 岗位编码 */
    private Integer positionCode;
    
    /** 岗位名称*/
    private String positionName;
    
    /**
     * 员工信息
     */
    private List<EmployeeInfo>  employeeInfoList;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getAssignType() {
        return assignType;
    }

    public void setAssignType(Integer assignType) {
        this.assignType = assignType;
    }

    public Integer getNonAssignType() {
        return nonAssignType;
    }

    public void setNonAssignType(Integer nonAssignType) {
        this.nonAssignType = nonAssignType;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public Integer getIsUpgrade() {
        return isUpgrade;
    }

    public void setIsUpgrade(Integer isUpgrade) {
        this.isUpgrade = isUpgrade;
    }

    public BigDecimal getPerformancecount() {
        return performancecount;
    }

    public void setPerformancecount(BigDecimal performancecount) {
        this.performancecount = performancecount;
    }

    public Integer getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Integer positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public List<EmployeeInfo> getEmployeeInfoList() {
        return employeeInfoList;
    }

    public void setEmployeeInfoList(List<EmployeeInfo> employeeInfoList) {
        this.employeeInfoList = employeeInfoList;
    }

    public Integer getAssignCommission() {
        return assignCommission;
    }

    public void setAssignCommission(Integer assignCommission) {
        this.assignCommission = assignCommission;
    }

    public Integer getNonAssignCommission() {
        return nonAssignCommission;
    }

    public void setNonAssignCommission(Integer nonAssignCommission) {
        this.nonAssignCommission = nonAssignCommission;
    }

    public Integer getNonCustomercost() {
        return nonCustomercost;
    }

    public void setNonCustomercost(Integer nonCustomercost) {
        this.nonCustomercost = nonCustomercost;
    }

    public Integer getCustomercost() {
        return customercost;
    }

    public void setCustomercost(Integer customercost) {
        this.customercost = customercost;
    }
    
	
}
