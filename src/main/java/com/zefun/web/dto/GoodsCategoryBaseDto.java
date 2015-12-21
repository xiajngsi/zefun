package com.zefun.web.dto;

import java.util.List;

/**
 * 商品类别数据传输对象
* @author 高国藩
* @date 2015年10月16日 上午10:52:40
 */
public class GoodsCategoryBaseDto {

    /** 类别标识 */
    private Integer categoryId;

    /** 类别名称 */
    private String categoryName;
    
    /** 商品数据*/
    private List<GoodsBaseDto> goodsBaseDtos;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<GoodsBaseDto> getGoodsBaseDtos() {
        return goodsBaseDtos;
    }

    public void setGoodsBaseDtos(List<GoodsBaseDto> goodsBaseDtos) {
        this.goodsBaseDtos = goodsBaseDtos;
    }
    
}
