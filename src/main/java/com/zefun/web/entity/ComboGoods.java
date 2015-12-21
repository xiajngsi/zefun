package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 套餐商品关联
* @author 高国藩
* @date 2015年11月10日 下午3:54:16
 */
public class ComboGoods {
    
    /**套餐标识*/
    private Integer comboId;
    /**商品标识*/
    private Integer goodsId;
    /**商品名称*/
    private String goodsName;
    /**商品价格*/
    private BigDecimal goodsPrice;
    /**商品图片*/
    private String goodsImage;
    /**商品数量*/
    private Integer goodsCounts;
    /**套餐内业绩计算*/
    private BigDecimal comboPerformanceCal;
    /**提成方式(1:按业绩比例,2:按固定金额)*/
    private Integer commissionType;
    /**提成金额*/
    private Integer commissionAmount;
    /**创建时间*/
    private String createTime;
    /**修改时间*/
    private String updateTime;
    /**最后操作人标识*/
    private Integer lastOperatorId;
    
    public Integer getGoodsCounts() {
        return goodsCounts;
    }

    public void setGoodsCounts(Integer goodsCounts) {
        this.goodsCounts = goodsCounts;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
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
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    public BigDecimal getComboPerformanceCal() {
        return comboPerformanceCal;
    }

    public void setComboPerformanceCal(BigDecimal comboPerformanceCal) {
        this.comboPerformanceCal = comboPerformanceCal;
    }

    public Integer getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }

    public Integer getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Integer commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}