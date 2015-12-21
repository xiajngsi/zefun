package com.zefun.web.dto;

import java.util.List;

/**
 * 项目类别基础传输对象
* @author 张进军
* @date Oct 14, 2015 11:30:51 PM 
*/
public class ProjectCategoryBaseDto {
    /** 类别标识 */
    private Integer categoryId;

    /** 类别名称 */
    private String categoryName;
    
    /** 项目列表 */
    private List<ProjectBaseDto> projectList;

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

    public List<ProjectBaseDto> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectBaseDto> projectList) {
        this.projectList = projectList;
    }
    
}