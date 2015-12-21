package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MemberComboProject {
    
    /** 详情标识*/
    private Integer detailId;
	/** 套餐标识 */
	private Integer comboId;
	/** 记录标识*/
	private Integer recordId;
	/** 项目标识 */
	private Integer projectId;

	/** 项目名称 */
	private String projectName;

	/** 项目价格 */
	private BigDecimal projectPrice;

	/** 项目图片 */
	private String projectImage;

	/** 项目数量 */
	private Integer projectCount;

	/** 剩余数量 */
	private Integer remainingCount;

	/** 创建时间 */
	private String createTime;

	/** 是否删除(0:未删除,1:已删除)*/
    private Integer isDeleted;
	
	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /** @param comboId	记录标识 */
	public void setComboId(Integer comboId){
		this.comboId = comboId;
	}

	/** @return	记录标识 */
	public Integer getComboId(){
		return comboId;
	}

	/** @param projectId	项目标识 */
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}

	/** @return	项目标识 */
	public Integer getProjectId(){
		return projectId;
	}

	/** @param projectName	项目名称 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/** @return	项目名称 */
	public String getProjectName(){
		return projectName;
	}

	/** @param projectPrice	项目价格 */
	public void setProjectPrice(BigDecimal projectPrice){
		this.projectPrice = projectPrice;
	}

	/** @return	项目价格 */
	public BigDecimal getProjectPrice(){
		return projectPrice;
	}

	/** @param projectImage	项目图片 */
	public void setProjectImage(String projectImage){
		this.projectImage = projectImage;
	}

	/** @return	项目图片 */
	public String getProjectImage(){
		return projectImage;
	}

	/** @param projectCount	项目数量 */
	public void setProjectCount(Integer projectCount){
		this.projectCount = projectCount;
	}

	/** @return	项目数量 */
	public Integer getProjectCount(){
		return projectCount;
	}

	/** @param remainingCount	剩余数量 */
	public void setRemainingCount(Integer remainingCount){
		this.remainingCount = remainingCount;
	}

	/** @return	剩余数量 */
	public Integer getRemainingCount(){
		return remainingCount;
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