package com.zefun.web.entity;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class SupplierInfo {
	/** 供应商标识 */
	private Integer supplierId;
	
	/** 供应商名称 */
	private String supplierName;

	/** 门店标识 */
	private Integer storeId;

	/** 经营品牌 */
	private String supplyBrand;
	
	/** 经营品牌描述 */
    private String supplyBrandStr;

	/** 供货类别 */
	private String supplyCategory;
	
	/** 供货类别描述 */
    private String supplyCategoryStr;

	/** 联系人 */
	private String linkName;

	/** 手机号 */
	private String linkPhone;

	/** 30天销售量 */
	private Integer thirtySales;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @return   供应商标识 */
	public Integer getSupplierId() {
        return supplierId;
    }
	/** @param supplierId  供应商标识 */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

	public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    /** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param supplyBrand	经营品牌 */
	public void setSupplyBrand(String supplyBrand){
		this.supplyBrand = supplyBrand;
	}

	/** @return	经营品牌 */
	public String getSupplyBrand(){
		return supplyBrand;
	}
	
	/** @return 经营品牌 描述*/
	public String getSupplyBrandStr() {
        return supplyBrandStr;
    }
	
	/** @param supplyBrandStr 经营品牌描述 */
    public void setSupplyBrandStr(String supplyBrandStr) {
        this.supplyBrandStr = supplyBrandStr;
    }
    
    /** @param supplyCategory	供货类别 */
	public void setSupplyCategory(String supplyCategory){
		this.supplyCategory = supplyCategory;
	}

	/** @return	供货类别 */
	public String getSupplyCategory(){
		return supplyCategory;
	}
	
	/** @return 供货类别描述 */
	public String getSupplyCategoryStr() {
        return supplyCategoryStr;
    }
	
	/** @param supplyCategoryStr  供货类别描述 */
    public void setSupplyCategoryStr(String supplyCategoryStr) {
        this.supplyCategoryStr = supplyCategoryStr;
    }
    
    /** @param linkName	联系人 */
	public void setLinkName(String linkName){
		this.linkName = linkName;
	}

	/** @return	联系人 */
	public String getLinkName(){
		return linkName;
	}

	/** @param linkPhone	手机号 */
	public void setLinkPhone(String linkPhone){
		this.linkPhone = linkPhone;
	}

	/** @return	手机号 */
	public String getLinkPhone(){
		return linkPhone;
	}

	/** @return 30天销售量 */
	public Integer getThirtySales() {
        return thirtySales;
    }
	
	/** @param thirtySales   30天销售量 */
    public void setThirtySales(Integer thirtySales) {
        this.thirtySales = thirtySales;
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

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
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