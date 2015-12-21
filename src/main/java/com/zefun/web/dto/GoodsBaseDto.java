package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 商品信息基础传输对象
* @author 高国藩
* @date 2015年10月16日 上午10:51:01
 */
public class GoodsBaseDto {

    /** 商品标识 */
    private Integer goodsId;
    
    /** 商品名称 */
    private String goodsName;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品图片 */
    private String goodsImage;

    /** 商品描述 */
    private String goodsDesc;

    /** 商品库存 */
    private Integer goodsStock;

    /** 销售次数 */
    private Integer salesCount;

    /** 销售人数 */
    private Integer salesPeople;
    
    /** 附属图片 */
    private String affiliatedImage;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getSalesPeople() {
        return salesPeople;
    }

    public void setSalesPeople(Integer salesPeople) {
        this.salesPeople = salesPeople;
    }

    public String getAffiliatedImage() {
        return affiliatedImage;
    }

    public void setAffiliatedImage(String affiliatedImage) {
        this.affiliatedImage = affiliatedImage;
    }
    
}
