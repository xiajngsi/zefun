package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.ProjectInfo;

/**
 * 项目类别Dto
* @author 洪秋霞
* @date 2015年8月27日 下午3:51:02
 */
public class ProjectCategoryDto {

    /** 类别标识 */
    private Integer categoryId;
    
    /** 类别名称 */
    private String categoryName;
    
    /** 门店id */
    private Integer storeId;
    
    /** 项目列表 */
    private List<ProjectInfo> projectInfo;

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

    public List<ProjectInfo> getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(List<ProjectInfo> projectInfo) {
        this.projectInfo = projectInfo;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    
}
