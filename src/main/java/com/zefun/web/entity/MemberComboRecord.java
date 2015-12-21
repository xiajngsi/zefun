package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MemberComboRecord {
	/** 记录标识 */
	private Integer recordId;

	/** 会员标识 */
	private Integer memberId;

	/** 套餐标识 */
	private Integer comboId;

	/** 门店标识 */
	private Integer storeId;
	
	/** 订单明细标识*/
	private Integer detailId;

	/** 套餐名称 */
	private String comboName;

	/** 套餐价格 */
	private BigDecimal comboPrice;

	/** 套餐图片 */
	private String comboImage;

	/** 项目总价 */
	private BigDecimal projectAmount;

	/** 项目数量 */
	private Integer projectCount;

	/** 剩余数量 */
	private Integer remainingCount;
	
    /** 过期时间*/
	private String overdueTime;
	
	/** 创建时间 */
	private String createTime;

	/** 是否删除(0:未删除,1:已删除)*/
	private Integer isDeleted;
	
	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	
	
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

    public String getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(String overdueTime) {
        this.overdueTime = overdueTime;
    }

    /** @param recordId	记录标识 */
	public void setRecordId(Integer recordId){
		this.recordId = recordId;
	}

	/** @return	记录标识 */
	public Integer getRecordId(){
		return recordId;
	}

	/** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param comboId	套餐标识 */
	public void setComboId(Integer comboId){
		this.comboId = comboId;
	}

	/** @return	套餐标识 */
	public Integer getComboId(){
		return comboId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param comboName	套餐名称 */
	public void setComboName(String comboName){
		this.comboName = comboName;
	}

	/** @return	套餐名称 */
	public String getComboName(){
		return comboName;
	}

	/** @param comboPrice	套餐价格 */
	public void setComboPrice(BigDecimal comboPrice){
		this.comboPrice = comboPrice;
	}

	/** @return	套餐价格 */
	public BigDecimal getComboPrice(){
		return comboPrice;
	}

	/** @param comboImage	套餐图片 */
	public void setComboImage(String comboImage){
		this.comboImage = comboImage;
	}

	/** @return	套餐图片 */
	public String getComboImage(){
		return comboImage;
	}

	/** @param projectAmount	项目总价 */
	public void setProjectAmount(BigDecimal projectAmount){
		this.projectAmount = projectAmount;
	}

	/** @return	项目总价 */
	public BigDecimal getProjectAmount(){
		return projectAmount;
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