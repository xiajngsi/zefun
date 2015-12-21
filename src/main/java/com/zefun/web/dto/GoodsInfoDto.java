package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 商品信息Dto
* @author 洪秋霞
* @date 2015年8月11日 上午10:41:09
 */
public class GoodsInfoDto {
	
    /** 商品标识 */
    private Integer goodsId;

    /** 门店标识 */
    private Integer storeId;

    /** 类别标识 */
    private Integer categoryId;

    /** 品牌标识 */
    private String brandId;
    
    /** 供应商标识 */
    private Integer supplierId;
    
    /**部门标识*/
    private Integer deptId;

    /** 商品名称 */
    private String goodsName;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 成本价格 */
    private BigDecimal costPrice;
    
    /** 网购价 */
    private BigDecimal onlineShoppingPrice;

    /** 提成是否包含成本(0:否,1:是) */
    private Integer isIncludeCost;

    /** 商品图片 */
    private String goodsImage;

    /** 商品描述 */
    private String goodsDesc;

    /** 商品库存 */
    private Integer goodsStock;

    /** 告警库存 */
    private Integer warnStock;

    /** 提成方式(1:按业绩比例,2:按固定金额) */
    private Integer commissionType;

    /** 提成金额 */
    private Integer commissionAmount;
    
    /** 刷卡提成方式(1:按业绩比例,2:按固定金额) */
    private Integer commissionCardType;

    /** 刷卡提成金额 */
    private Integer cardAmount;
    
    /** 积分兑换 */
    private Integer integralExchange;

    /** 销售次数 */
    private Integer salesCount;

    /** 销售人数 */
    private Integer salesPeople;

    /** 是否微信销售(0:否,1:是) */
    private Integer isWechatSell;

    /** 是否禁用(0:未禁用,1:已禁用) */
    private Integer isDisable;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;
    
    /** 是否卖品(0:否,1:是) */
    private Integer isSellProduct;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    
    /** 类别名称 */
    private String categoryName;
    
    /** 品牌名称 */
    private String brandName;
    
    /** 供应商名称 */
    private String supplierName;
    
    /** 30天销售量 */
    private Integer thirtySales;
    
    /** 进货记录 */
    private GoodsPurchaseRecordDto goodsPurchaseRecordDto;
    
    /** 附属图片 */
    private String affiliatedImage;
    
    /** 部门名称*/
    private String deptName;
    
    /**是否礼金抵扣*/
    private Integer isCashDeduction;
    
    /**最大礼金抵扣*/
    private BigDecimal highestDiscount; 
    

    public BigDecimal getHighestDiscount() {
        return highestDiscount;
    }

    public void setHighestDiscount(BigDecimal highestDiscount) {
        this.highestDiscount = highestDiscount;
    }
    public Integer getIsCashDeduction() {
        return isCashDeduction;
    }

    public void setIsCashDeduction(Integer isCashDeduction) {
        this.isCashDeduction = isCashDeduction;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /** @param goodsId  商品标识 */
    public void setGoodsId(Integer goodsId){
        this.goodsId = goodsId;
    }

    /** @return 商品标识 */
    public Integer getGoodsId(){
        return goodsId;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param categoryId   类别标识 */
    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }

    /** @return 类别标识 */
    public Integer getCategoryId(){
        return categoryId;
    }

    /** @param brandId  品牌标识 */
    public void setBrandId(String brandId){
        this.brandId = brandId;
    }

    /** @return 品牌标识 */
    public String getBrandId(){
        return brandId;
    }
    
    /** @param supplierId  供应商标识 */
    public void setSupplierId(Integer supplierId){
        this.supplierId = supplierId;
    }

    /** @return 供应商标识 */
    public Integer getSupplierId(){
        return supplierId;
    }
    
    /**@return 部门标识*/
    public Integer getDeptId() {
        return deptId;
    }

    /**@param deptId 部门标识*/
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /** @param goodsName    商品名称 */
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    /** @return 商品名称 */
    public String getGoodsName(){
        return goodsName;
    }

    /** @param goodsPrice   商品价格 */
    public void setGoodsPrice(BigDecimal goodsPrice){
        this.goodsPrice = goodsPrice;
    }

    /** @return 商品价格 */
    public BigDecimal getGoodsPrice(){
        return goodsPrice;
    }

    /** @param costPrice    成本价格 */
    public void setCostPrice(BigDecimal costPrice){
        this.costPrice = costPrice;
    }

    /** @return 成本价格 */
    public BigDecimal getCostPrice(){
        return costPrice;
    }
    
    /** @return 网购价 */
    public BigDecimal getOnlineShoppingPrice() {
        return onlineShoppingPrice;
    }

    /** @param onlineShoppingPrice 网购价 */
    public void setOnlineShoppingPrice(BigDecimal onlineShoppingPrice) {
        this.onlineShoppingPrice = onlineShoppingPrice;
    }

    /** @param isIncludeCost    提成是否包含成本(0:否,1:是) */
    public void setIsIncludeCost(Integer isIncludeCost){
        this.isIncludeCost = isIncludeCost;
    }

    /** @return 提成是否包含成本(0:否,1:是) */
    public Integer getIsIncludeCost(){
        return isIncludeCost;
    }

    /** @param goodsImage   商品图片 */
    public void setGoodsImage(String goodsImage){
        this.goodsImage = goodsImage;
    }

    /** @return 商品图片 */
    public String getGoodsImage(){
        return goodsImage;
    }

    /** @param goodsDesc    商品描述 */
    public void setGoodsDesc(String goodsDesc){
        this.goodsDesc = goodsDesc;
    }

    /** @return 商品描述 */
    public String getGoodsDesc(){
        return goodsDesc;
    }

    /** @param goodsStock   商品库存 */
    public void setGoodsStock(Integer goodsStock){
        this.goodsStock = goodsStock;
    }

    /** @return 商品库存 */
    public Integer getGoodsStock(){
        return goodsStock;
    }

    /** @param warnStock    告警库存 */
    public void setWarnStock(Integer warnStock){
        this.warnStock = warnStock;
    }

    /** @return 告警库存 */
    public Integer getWarnStock(){
        return warnStock;
    }

    /** @param commissionType   提成方式(1:按业绩比例,2:按固定金额) */
    public void setCommissionType(Integer commissionType){
        this.commissionType = commissionType;
    }

    /** @return 提成方式(1:按业绩比例,2:按固定金额) */
    public Integer getCommissionType(){
        return commissionType;
    }

    /** @return 刷卡提成方式(1:按业绩比例,2:按固定金额) */
    public Integer getCommissionCardType() {
        return commissionCardType;
    }

    /** @param commissionCardType 刷卡提成方式(1:按业绩比例,2:按固定金额) */
    public void setCommissionCardType(Integer commissionCardType) {
        this.commissionCardType = commissionCardType;
    }

    /** @return 积分兑换 */
    public Integer getIntegralExchange() {
        return integralExchange;
    }

    /** @param integralExchange 积分兑换 */
    public void setIntegralExchange(Integer integralExchange) {
        this.integralExchange = integralExchange;
    }

    /** @param salesCount   销售次数 */
    public void setSalesCount(Integer salesCount){
        this.salesCount = salesCount;
    }

    /** @return 销售次数 */
    public Integer getSalesCount(){
        return salesCount;
    }

    /** @param salesPeople  销售人数 */
    public void setSalesPeople(Integer salesPeople){
        this.salesPeople = salesPeople;
    }

    /** @return 销售人数 */
    public Integer getSalesPeople(){
        return salesPeople;
    }

    /** @param isWechatSell 是否微信销售(0:否,1:是) */
    public void setIsWechatSell(Integer isWechatSell){
        this.isWechatSell = isWechatSell;
    }

    /** @return 是否微信销售(0:否,1:是) */
    public Integer getIsWechatSell(){
        return isWechatSell;
    }

    /** @param isDisable    是否禁用(0:未禁用,1:已禁用) */
    public void setIsDisable(Integer isDisable){
        this.isDisable = isDisable;
    }

    /** @return 是否禁用(0:未禁用,1:已禁用) */
    public Integer getIsDisable(){
        return isDisable;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted(){
        return isDeleted;
    }
    
    /** @return 是否卖品(0:否,1:是) */
    public Integer getIsSellProduct() {
        return isSellProduct;
    }

    /** @param isSellProduct 是否卖品(0:否,1:是) */
    public void setIsSellProduct(Integer isSellProduct) {
        this.isSellProduct = isSellProduct;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public GoodsPurchaseRecordDto getGoodsPurchaseRecordDto() {
        return goodsPurchaseRecordDto;
    }

    public void setGoodsPurchaseRecordDto(GoodsPurchaseRecordDto goodsPurchaseRecordDto) {
        this.goodsPurchaseRecordDto = goodsPurchaseRecordDto;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getThirtySales() {
        return thirtySales;
    }

    public void setThirtySales(Integer thirtySales) {
        this.thirtySales = thirtySales;
    }
    
    /** @return 附属图片 */
    public String getAffiliatedImage() {
        return affiliatedImage;
    }

    /** @param affiliatedImage 附属图片 */
    public void setAffiliatedImage(String affiliatedImage) {
        this.affiliatedImage = affiliatedImage;
    }

    public Integer getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Integer commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Integer getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Integer cardAmount) {
        this.cardAmount = cardAmount;
    }

}
