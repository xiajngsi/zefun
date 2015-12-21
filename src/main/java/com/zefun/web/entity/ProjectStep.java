package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ProjectStep {
	/** 项目步骤标识 */
	private Integer projectStepId;

	/** 项目标识 */
	private Integer projectId;

	/** 轮牌信息标识 */
	private Integer shiftMahjongId;

	/** 步骤顺序 */
	private Integer projectStepOrder;
	
	/** 步骤名称 */
	private String projectStepName;
	
	/**步骤业绩计算*/
	private BigDecimal stepPerformance;
	/**步骤业绩计算方式(2:固定,1:比例) */
	private Integer stepPerformanceType;

	/***/
    private String projectStepDesc;

    /***/
    private Integer isDeleted;
    
    /***/
    private Integer isPermissionsUpdate;
    
    /***/
    private Integer isHangUp;

    /**是否为预约步骤*/
    private Integer isDisable;
    
    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/**是否可以预约*/
	private Integer isOrder;

	
	public Integer getStepPerformanceType() {
        return stepPerformanceType;
    }

    public void setStepPerformanceType(Integer stepPerformanceType) {
        this.stepPerformanceType = stepPerformanceType;
    }

    public BigDecimal getStepPerformance() {
        return stepPerformance;
    }

    public void setStepPerformance(BigDecimal stepPerformance) {
        this.stepPerformance = stepPerformance;
    }

    public Integer getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(Integer isOrder) {
        this.isOrder = isOrder;
    }

    /** @param projectStepId	项目步骤标识 */
	public void setProjectStepId(Integer projectStepId){
		this.projectStepId = projectStepId;
	}

	/** @return	项目步骤标识 */
	public Integer getProjectStepId(){
		return projectStepId;
	}

	/** @param projectId	项目标识 */
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}

	/** @return	项目标识 */
	public Integer getProjectId(){
		return projectId;
	}

	/** @param shiftMahjongId	轮牌信息标识 */
	public void setShiftMahjongId(Integer shiftMahjongId){
		this.shiftMahjongId = shiftMahjongId;
	}

	/** @return	轮牌信息标识 */
	public Integer getShiftMahjongId(){
		return shiftMahjongId;
	}

	/** @param projectStepOrder	步骤顺序 */
	public void setProjectStepOrder(Integer projectStepOrder){
		this.projectStepOrder = projectStepOrder;
	}

	/** @return	步骤顺序 */
	public Integer getProjectStepOrder(){
		return projectStepOrder;
	}

	public String getProjectStepName() {
        return projectStepName;
    }

    public void setProjectStepName(String projectStepName) {
        this.projectStepName = projectStepName;
    }
    
    public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
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

    public String getProjectStepDesc() {
        return projectStepDesc;
    }

    public void setProjectStepDesc(String projectStepDesc) {
        this.projectStepDesc = projectStepDesc;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsPermissionsUpdate() {
        return isPermissionsUpdate;
    }

    public void setIsPermissionsUpdate(Integer isPermissionsUpdate) {
        this.isPermissionsUpdate = isPermissionsUpdate;
    }

    public Integer getIsHangUp() {
        return isHangUp;
    }

    public void setIsHangUp(Integer isHangUp) {
        this.isHangUp = isHangUp;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

}