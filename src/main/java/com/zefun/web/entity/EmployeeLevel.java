package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeLevel {
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


    /** @param levelId	级别标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	级别标识 */
	public Integer getLevelId(){
		return levelId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param positionId	岗位标识 */
	public void setPositionId(Integer positionId){
		this.positionId = positionId;
	}

	/** @return	岗位标识 */
	public Integer getPositionId(){
		return positionId;
	}

	/** @param levelName	级别名称 */
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}

	/** @return	级别名称 */
	public String getLevelName(){
		return levelName;
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

    /** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

	/** @param isUpgrade	岗位开启升级(0没开启 1开启) */
	public void setIsUpgrade(Integer isUpgrade){
		this.isUpgrade = isUpgrade;
	}

	/** @return	岗位开启升级(0没开启 1开启) */
	public Integer getIsUpgrade(){
		return isUpgrade;
	}

	/** @param performancecount	业绩总额 */
	public void setPerformancecount(BigDecimal performancecount){
		this.performancecount = performancecount;
	}

	/** @return	业绩总额 */
	public BigDecimal getPerformancecount(){
		return performancecount;
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