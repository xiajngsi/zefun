package com.zefun.web.dto;

import java.util.List;

/**
 * 商品类别Dto
* @author 洪秋霞
* @date 2015年9月16日 下午2:47:08
 */
public class GoodsCategoryDto {

    /** 类别标识 */
    private Integer categoryId;
    
    /** 类别名称 */
    private String categoryName;
    
    /** 商品信息 */
    private List<GoodsInfoDto> goodsInfoDtoList;

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

    public List<GoodsInfoDto> getGoodsInfoDtoList() {
        return goodsInfoDtoList;
    }

    public void setGoodsInfoDtoList(List<GoodsInfoDto> goodsInfoDtoList) {
        this.goodsInfoDtoList = goodsInfoDtoList;
    }
    
    
}
