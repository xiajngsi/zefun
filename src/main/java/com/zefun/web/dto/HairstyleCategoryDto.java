package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.HairstyleDesign;

/**
 * 发型类别Dto
* @author 洪秋霞
* @date 2015年9月8日 下午3:09:43
 */
public class HairstyleCategoryDto {

    /** 发型类别标识 */
    private Integer hairstyleCategoryId;
    
    /** 发型类别名称 */
    private String hairstyleCategoryName;
    
    /** 发型设置 */
    private List<HairstyleDesign> hairstyleDesign;

    public Integer getHairstyleCategoryId() {
        return hairstyleCategoryId;
    }

    public void setHairstyleCategoryId(Integer hairstyleCategoryId) {
        this.hairstyleCategoryId = hairstyleCategoryId;
    }

    public String getHairstyleCategoryName() {
        return hairstyleCategoryName;
    }

    public void setHairstyleCategoryName(String hairstyleCategoryName) {
        this.hairstyleCategoryName = hairstyleCategoryName;
    }

    public List<HairstyleDesign> getHairstyleDesign() {
        return hairstyleDesign;
    }

    public void setHairstyleDesign(List<HairstyleDesign> hairstyleDesign) {
        this.hairstyleDesign = hairstyleDesign;
    }
    
    
}
