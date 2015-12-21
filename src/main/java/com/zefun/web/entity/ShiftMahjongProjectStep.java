package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ShiftMahjongProjectStep {
	/** 轮牌信息标识 */
	private Integer shiftMahjongStepId;

	/** 项目步骤标识 */
	private Integer projectStepId;

	/** 订单明细*/
	private Integer detailId;
	
	/** 服务开始时间 */
	private String beginTime;

	/** 服务结束时间 */
	private String finishTime;
	
	/** 员工标识*/
	private Integer employeeId;
	
	/** 是否指定*/
	private Integer isAssign;
	
	/** 是否指派(0：否  1：是)*/
	private Integer isDesignate;
	
	/** 是否预约*/
	private Integer isAppoint;

	/** 步骤状态(1：服务中、2：等待中、4：已结束) */
	private Integer isOver;
	
	/** 是否删除*/
	private Integer isDeleted; 

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	
	
	
	public Integer getIsDesignate() {
        return isDesignate;
    }

    public void setIsDesignate(Integer isDesignate) {
        this.isDesignate = isDesignate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Integer isAssign) {
        this.isAssign = isAssign;
    }

    public Integer getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(Integer isAppoint) {
        this.isAppoint = isAppoint;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    
    
	public Integer getShiftMahjongStepId() {
        return shiftMahjongStepId;
    }

    public void setShiftMahjongStepId(Integer shiftMahjongStepId) {
        this.shiftMahjongStepId = shiftMahjongStepId;
    }

    /** @param projectStepId	项目步骤标识 */
	public void setProjectStepId(Integer projectStepId){
		this.projectStepId = projectStepId;
	}

	/** @return	项目步骤标识 */
	public Integer getProjectStepId(){
		return projectStepId;
	}

	/** @param beginTime	服务开始时间 */
	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	/** @return	服务开始时间 */
	public String getBeginTime(){
		return beginTime;
	}

	/** @param finishTime	服务结束时间 */
	public void setFinishTime(String finishTime){
		this.finishTime = finishTime;
	}

	/** @return	服务结束时间 */
	public String getFinishTime(){
		return finishTime;
	}

	/** @param isOver	是否结束(0：否  1：是) */
	public void setIsOver(Integer isOver){
		this.isOver = isOver;
	}

	/** @return	是否结束(0：否  1：是) */
	public Integer getIsOver(){
		return isOver;
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

}