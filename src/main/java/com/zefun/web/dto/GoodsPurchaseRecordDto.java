package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 进货记录
* @author 洪秋霞
* @date 2015年9月6日 下午4:14:55
 */
public class GoodsPurchaseRecordDto {

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
    
    /** 供应商名称 */
    private String supplierName;
    
    /** 商品名称 */
    private String goodsName;
    
    /** 平均成本 */
    private BigDecimal avgCost;
    
    /** 上批成本 */
    private BigDecimal prevCost;
    
    /**员工名称*/
    private String employeeName;

    /** @param purchaseId   记录标识 */
    public void setPurchaseId(Integer purchaseId){
        this.purchaseId = purchaseId;
    }

    /** @return 记录标识 */
    public Integer getPurchaseId(){
        return purchaseId;
    }

    /** @param storeId  店铺标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 店铺标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param goodsId  商品标识 */
    public void setGoodsId(Integer goodsId){
        this.goodsId = goodsId;
    }

    /** @return 商品标识 */
    public Integer getGoodsId(){
        return goodsId;
    }

    /** @param supplierId   供应商标识 */
    public void setSupplierId(Integer supplierId){
        this.supplierId = supplierId;
    }

    /** @return 供应商标识 */
    public Integer getSupplierId(){
        return supplierId;
    }

    /** @param purchasePrice    进货价格 */
    public void setPurchasePrice(BigDecimal purchasePrice){
        this.purchasePrice = purchasePrice;
    }

    /** @return 进货价格 */
    public BigDecimal getPurchasePrice(){
        return purchasePrice;
    }

    /** @param purchaseCount    进货数量 */
    public void setPurchaseCount(Integer purchaseCount){
        this.purchaseCount = purchaseCount;
    }

    /** @return 进货数量 */
    public Integer getPurchaseCount(){
        return purchaseCount;
    }

    /** @param purchaseTime 进货时间 */
    public void setPurchaseTime(String purchaseTime){
        this.purchaseTime = purchaseTime;
    }

    /** @return 进货时间 */
    public String getPurchaseTime(){
        return purchaseTime;
    }

    /** @param operatorId   操作人标识 */
    public void setOperatorId(Integer operatorId){
        this.operatorId = operatorId;
    }

    /** @return 操作人标识 */
    public Integer getOperatorId(){
        return operatorId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public BigDecimal getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(BigDecimal avgCost) {
        this.avgCost = avgCost;
    }

    public BigDecimal getPrevCost() {
        return prevCost;
    }

    public void setPrevCost(BigDecimal prevCost) {
        this.prevCost = prevCost;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
