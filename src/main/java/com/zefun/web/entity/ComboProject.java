package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ComboProject {
	/** 套餐标识 */
	private Integer comboId;

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
	
	/** 套餐内单次服务业绩计算 */
    private BigDecimal comboPerformanceCal;
    
    /** 提成比例*/
    private BigDecimal royaltyRate;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

    public BigDecimal getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(BigDecimal royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    /** @param comboId	套餐标识 */
	public void setComboId(Integer comboId){
		this.comboId = comboId;
	}

	/** @return	套餐标识 */
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
	
	/** @return 套餐内业绩计算 */
    public BigDecimal getComboPerformanceCal() {
        return comboPerformanceCal;
    }

    /** @param comboPerformanceCal 套餐内业绩计算 */
    public void setComboPerformanceCal(BigDecimal comboPerformanceCal) {
        this.comboPerformanceCal = comboPerformanceCal;
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