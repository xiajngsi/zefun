package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ComboInfo {
	/** 套餐标识 */
	private Integer comboId;
	
	/** 上级套餐id */
	private Integer comboParentId;

	/** 门店标识 */
	private Integer storeId;
	
	/**部门标识*/
    private Integer deptId;

	/** 套餐名称 */
	private String comboName;

	/** 套餐图片 */
	private String comboImage;

	/** 套餐描述 */
	private String comboDesc;
	
	/** 项目数量 */
    private Integer projectCount;
    
	/** 项目总价 */
	private BigDecimal projectAmount;

	/** 套餐销售价 */
	private BigDecimal comboSalePrice;

	/** 提成方式(1:按业绩比例,2:按固定金额) */
    private Integer commissionType;

    /** 现金提成 */
    private BigDecimal cashCommission;

    /** 卡金提成 */
    private BigDecimal cardCommission;
	
	/** 销售次数 */
	private Integer salesCount;

	/** 销售人数 */
	private Integer salesPeople;

	/** 开始日期 */
	private String startDate;

	/** 结束日期 */
	private String endDate;
	
	/** 有效期 */
	private Integer validDate;
	
	/** 提成是否包含成本(0:否,1:是) */
    private Integer isIncludeCost;

	/** 是否微信销售(0:否,1:是) */
	private Integer isWechatSell;

	/** 是否禁用(0:未禁用,1:已禁用)2 */
	private Integer isDisable;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/**套餐规格设置*/
	private Integer standard;
	
	/**是否进行身份认证*/
	private Integer isAttestation;
	
	/**套餐业绩计算*/
	private BigDecimal comboPerformance;
	
	
	public BigDecimal getComboPerformance() {
        return comboPerformance;
    }

    public void setComboPerformance(BigDecimal comboPerformance) {
        this.comboPerformance = comboPerformance;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getIsAttestation() {
        return isAttestation;
    }

    public void setIsAttestation(Integer isAttestation) {
        this.isAttestation = isAttestation;
    }

    /** @param comboId	套餐标识 */
	public void setComboId(Integer comboId){
		this.comboId = comboId;
	}

	/** @return	套餐标识 */
	public Integer getComboId(){
		return comboId;
	}
	
	/** @return    上级套餐标识 */
	public Integer getComboParentId() {
        return comboParentId;
    }

	/** @param comboParentId 上级套餐标识 */
    public void setComboParentId(Integer comboParentId) {
        this.comboParentId = comboParentId;
    }

    /** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}
	
	/**@return 部门标识*/
    public Integer getDeptId() {
        return deptId;
    }

    /**@param deptId 部门标识*/
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

	/** @param comboName	套餐名称 */
	public void setComboName(String comboName){
		this.comboName = comboName;
	}

	/** @return	套餐名称 */
	public String getComboName(){
		return comboName;
	}

	
	/** @param comboImage	套餐图片 */
	public void setComboImage(String comboImage){
		this.comboImage = comboImage;
	}

	/** @return	套餐图片 */
	public String getComboImage(){
		return comboImage;
	}

	/** @param comboDesc	套餐描述 */
	public void setComboDesc(String comboDesc){
		this.comboDesc = comboDesc;
	}

	/** @return	套餐描述 */
	public String getComboDesc(){
		return comboDesc;
	}

	/** @param projectAmount	项目总价 */
	public void setProjectAmount(BigDecimal projectAmount){
		this.projectAmount = projectAmount;
	}

	/** @return	项目总价 */
	public BigDecimal getProjectAmount(){
		return projectAmount;
	}
	
	/** @return 套餐销售价 */
	public BigDecimal getComboSalePrice() {
        return comboSalePrice;
    }

	/** @param comboSalePrice 套餐销售价 */
    public void setComboSalePrice(BigDecimal comboSalePrice) {
        this.comboSalePrice = comboSalePrice;
    }

    public Integer getCommissionType() {
        return commissionType;
    }

    /** @param commissionType 提成方式 */
    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }

    /** @return 现金提成 */
    public BigDecimal getCashCommission() {
        return cashCommission;
    }

    /** @param cashCommission 现金提成 */
    public void setCashCommission(BigDecimal cashCommission) {
        this.cashCommission = cashCommission;
    }

    /** @return 卡金提成 */
    public BigDecimal getCardCommission() {
        return cardCommission;
    }

    /** @param cardCommission 卡金提成 */
    public void setCardCommission(BigDecimal cardCommission) {
        this.cardCommission = cardCommission;
    }

    /** @param projectCount	项目数量 */
	public void setProjectCount(Integer projectCount){
		this.projectCount = projectCount;
	}

	/** @return	项目数量 */
	public Integer getProjectCount(){
		return projectCount;
	}

	/** @param salesCount	销售次数 */
	public void setSalesCount(Integer salesCount){
		this.salesCount = salesCount;
	}

	/** @return	销售次数 */
	public Integer getSalesCount(){
		return salesCount;
	}

	/** @param salesPeople	销售人数 */
	public void setSalesPeople(Integer salesPeople){
		this.salesPeople = salesPeople;
	}

	/** @return	销售人数 */
	public Integer getSalesPeople(){
		return salesPeople;
	}

	/** @param startDate	开始日期 */
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	/** @return	开始日期 */
	public String getStartDate(){
		return startDate;
	}

	/** @param endDate	结束日期 */
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	/** @return	结束日期 */
	public String getEndDate(){
		return endDate;
	}
	
	/**@return 有效期*/
    public Integer getValidDate() {
        return validDate;
    }

    /**@param validDate 有效期*/
    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
    }

    /** @param isIncludeCost   提成是否包含成本(0:否,1:是) */
    public void setIsIncludeCost(Integer isIncludeCost){
        this.isIncludeCost = isIncludeCost;
    }

    /** @return 提成是否包含成本(0:否,1:是) */
    public Integer getIsIncludeCost(){
        return isIncludeCost;
    }
    
	/** @param isWechatSell	是否微信销售(0:否,1:是) */
	public void setIsWechatSell(Integer isWechatSell){
		this.isWechatSell = isWechatSell;
	}

	/** @return	是否微信销售(0:否,1:是) */
	public Integer getIsWechatSell(){
		return isWechatSell;
	}

	/** @param isDisable	是否禁用(0:未禁用,1:已禁用)2 */
	public void setIsDisable(Integer isDisable){
		this.isDisable = isDisable;
	}

	/** @return	是否禁用(0:未禁用,1:已禁用)2 */
	public Integer getIsDisable(){
		return isDisable;
	}

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
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