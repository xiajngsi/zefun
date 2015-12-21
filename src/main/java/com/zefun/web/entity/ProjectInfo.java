package com.zefun.web.entity;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ProjectInfo {
	/** 项目标识 */
	private Integer projectId;
	
	/** 上级项目标识 */
    private Integer parentId;

	/** 门店标识 */
	private Integer storeId;
	
	/**部门标识*/
    private Integer deptId;

	/** 类别标识 */
	private Integer categoryId;
	
	/** 发型设置标识 */
	private Integer hairstyleId;

	/** 项目名称 */
	private String projectName;
	
	/** 项目类型(1：普通项目，2：团购项目) */
	private String projectType;

	/** 项目图片 */
	private String projectImage;

	/** 项目描述 */
	private String projectDesc;

	/** 项目价格 */
	private BigDecimal projectPrice;

	/** 成本价格 */
	private BigDecimal costPrice;
	
	/** 业绩计算 */
	private BigDecimal performanceCalculate;
	
	/** 预约价格 */
	private BigDecimal appointmentPrice;

	/** 销售次数 */
	private Integer salesCount;

	/** 销售人数 */
	private Integer salesPeople;
	
	/** 是否接受预约(0:否,1:是) */
	private Integer isAppointment;
	
	/** 提成是否包含成本(0:否,1:是) */
    private Integer isIncludeCost;

	/** 是否微信销售(0:否,1:是) */
	private Integer isWechatSell;

	/** 是否禁用(0:未禁用,1:已禁用) */
	private Integer isDisable;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 是否有权限修改(0：不可修改，1：可修改) */
	private Integer isUpdate;

	/** 是否可以用礼金抵扣(0：否，1：是) */
	private Integer isGiftCash;
	
	/**最大礼金抵扣*/
	private BigDecimal highestDiscount;
	
	/** 是否团购(0：否，1：是) */
	private Integer isGroupbuy;
	
	/** 团购价格 */
	private BigDecimal groupbuyPrice;
	
	/** 团购业绩 */
	private BigDecimal groupbuyResults;
	
	/** 团购平台 */
	private String groupbuyPlatform;
	
	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/** 附属图片*/
	private String affiliatedImage;
	
	
	public BigDecimal getHighestDiscount() {
        return highestDiscount;
    }

    public void setHighestDiscount(BigDecimal highestDiscount) {
        this.highestDiscount = highestDiscount;
    }

    public String getAffiliatedImage() {
        return affiliatedImage;
    }

    public void setAffiliatedImage(String affiliatedImage) {
        this.affiliatedImage = affiliatedImage;
    }

    /** @param projectId	项目标识 */
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}

	/** @return	项目标识 */
	public Integer getProjectId(){
		return projectId;
	}
	
	/** @return 上级项目标识 */
    public Integer getParentId() {
        return parentId;
    }

    /** @param parentId 上级项目标识 */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

	/** @param categoryId	类别标识 */
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	/** @return	类别标识 */
	public Integer getCategoryId(){
		return categoryId;
	}
	
    /**@return 发型设置标识*/
	public Integer getHairstyleId() {
        return hairstyleId;
    }

	/** @param hairstyleId 发型设置标识*/
    public void setHairstyleId(Integer hairstyleId) {
        this.hairstyleId = hairstyleId;
    }

    /** @param projectName	项目名称 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/** @return	项目名称 */
	public String getProjectName(){
		return projectName;
	}
	
	/** @return 项目类型(1：普通项目，2：团购项目) */
	public String getProjectType() {
        return projectType;
    }

	/** @param projectType 项目类型(1：普通项目，2：团购项目) */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /** @param projectImage	项目图片 */
	public void setProjectImage(String projectImage){
		this.projectImage = projectImage;
	}

	/** @return	项目图片 */
	public String getProjectImage(){
		return projectImage;
	}

	/** @param projectDesc	项目描述 */
	public void setProjectDesc(String projectDesc){
		this.projectDesc = projectDesc;
	}

	/** @return	项目描述 */
	public String getProjectDesc(){
		return projectDesc;
	}

	/** @param projectPrice	项目价格 */
	public void setProjectPrice(BigDecimal projectPrice){
		this.projectPrice = projectPrice;
	}

	/** @return	项目价格 */
	public BigDecimal getProjectPrice(){
		return projectPrice;
	}

	/** @param costPrice	成本价格 */
	public void setCostPrice(BigDecimal costPrice){
		this.costPrice = costPrice;
	}

	/** @return	成本价格 */
	public BigDecimal getCostPrice(){
		return costPrice;
	}
	
	/** @return 业绩计算 */
	public BigDecimal getPerformanceCalculate() {
        return performanceCalculate;
    }

	/** @param performanceCalculate 业绩计算 */
    public void setPerformanceCalculate(BigDecimal performanceCalculate) {
        this.performanceCalculate = performanceCalculate;
    }

    /** @return 预约价格 */
    public BigDecimal getAppointmentPrice() {
        return appointmentPrice;
    }
    
    /** @param appointmentPrice 预约价格  */
    public void setAppointmentPrice(BigDecimal appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
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
	
	/** @return 是否接受预约(0:否,1:是) */
    public Integer getIsAppointment() {
        return isAppointment;
    }

    /** @param isAppointment 是否接受预约(0:否,1:是) */
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
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

	/** @param isDisable	是否禁用(0:未禁用,1:已禁用) */
	public void setIsDisable(Integer isDisable){
		this.isDisable = isDisable;
	}

	/** @return	是否禁用(0:未禁用,1:已禁用) */
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

	/** @param isUpdate	是否有权限修改(0：不可修改，1：可修改) */
	public void setIsUpdate(Integer isUpdate){
		this.isUpdate = isUpdate;
	}

	/** @return	是否有权限修改(0：不可修改，1：可修改) */
	public Integer getIsUpdate(){
		return isUpdate;
	}
	
	/** @return 是否可以用礼金抵扣(0：否，1：是) */
	public Integer getIsGiftCash() {
        return isGiftCash;
    }

	/** @param isGiftCash 是否可以用礼金抵扣(0：否，1：是) */
    public void setIsGiftCash(Integer isGiftCash) {
        this.isGiftCash = isGiftCash;
    }
    
    /** @return 是否团购(0：否，1：是) */
    public Integer getIsGroupbuy() {
        return isGroupbuy;
    }

    /** @param isGroupbuy 是否团购(0：否，1：是) */
    public void setIsGroupbuy(Integer isGroupbuy) {
        this.isGroupbuy = isGroupbuy;
    }

    /** @return 团购价格 */
    public BigDecimal getGroupbuyPrice() {
        return groupbuyPrice;
    }

    /** @param groupbuyPrice 团购价格*/
    public void setGroupbuyPrice(BigDecimal groupbuyPrice) {
        this.groupbuyPrice = groupbuyPrice;
    }

    /** @return 团购业绩 */
    public BigDecimal getGroupbuyResults() {
        return groupbuyResults;
    }

    /** @param groupbuyResults 团购业绩 */
    public void setGroupbuyResults(BigDecimal groupbuyResults) {
        this.groupbuyResults = groupbuyResults;
    }

    /** @return 团购平台 */
    public String getGroupbuyPlatform() {
        return groupbuyPlatform;
    }

    /** @param groupbuyPlatform 团购平台 */
    public void setGroupbuyPlatform(String groupbuyPlatform) {
        this.groupbuyPlatform = groupbuyPlatform;
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
	
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}