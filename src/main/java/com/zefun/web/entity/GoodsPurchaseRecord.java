package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class GoodsPurchaseRecord {
	/** 记录标识 */
	private Integer purchaseId;

	/** 店铺标识 */
	private Integer storeId;

	/** 商品标识 */
	private Integer goodsId;

	/** 供应商标识 */
	private Integer supplierId;

	/** 进货价格 */
	private BigDecimal purchasePrice;

	/** 进货数量 */
	private Integer purchaseCount;

	/** 进货时间 */
	private String purchaseTime;

	/** 操作人标识 */
	private Integer operatorId;

	/** @param purchaseId	记录标识 */
	public void setPurchaseId(Integer purchaseId){
		this.purchaseId = purchaseId;
	}

	/** @return	记录标识 */
	public Integer getPurchaseId(){
		return purchaseId;
	}

	/** @param storeId	店铺标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	店铺标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param goodsId	商品标识 */
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}

	/** @return	商品标识 */
	public Integer getGoodsId(){
		return goodsId;
	}

	/** @param supplierId	供应商标识 */
	public void setSupplierId(Integer supplierId){
		this.supplierId = supplierId;
	}

	/** @return	供应商标识 */
	public Integer getSupplierId(){
		return supplierId;
	}

	/** @param purchasePrice	进货价格 */
	public void setPurchasePrice(BigDecimal purchasePrice){
		this.purchasePrice = purchasePrice;
	}

	/** @return	进货价格 */
	public BigDecimal getPurchasePrice(){
		return purchasePrice;
	}

	/** @param purchaseCount	进货数量 */
	public void setPurchaseCount(Integer purchaseCount){
		this.purchaseCount = purchaseCount;
	}

	/** @return	进货数量 */
	public Integer getPurchaseCount(){
		return purchaseCount;
	}

	/** @param purchaseTime	进货时间 */
	public void setPurchaseTime(String purchaseTime){
		this.purchaseTime = purchaseTime;
	}

	/** @return	进货时间 */
	public String getPurchaseTime(){
		return purchaseTime;
	}

	/** @param operatorId	操作人标识 */
	public void setOperatorId(Integer operatorId){
		this.operatorId = operatorId;
	}

	/** @return	操作人标识 */
	public Integer getOperatorId(){
		return operatorId;
	}

}