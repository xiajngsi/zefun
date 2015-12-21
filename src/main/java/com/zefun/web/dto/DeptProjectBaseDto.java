package com.zefun.web.dto;

import java.util.List;

/**
 * 部门下项目基础传输对象
* @author 张进军
* @date Oct 14, 2015 11:28:44 PM 
*/
public class DeptProjectBaseDto {
    /**部门标识*/
    private Integer deptId;
    
    /**部门名称*/
    private String deptName;
    
    /** 项目类别列表 */
    private List<ProjectCategoryBaseDto> projectCategoryList;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<ProjectCategoryBaseDto> getProjectCategoryList() {
        return projectCategoryList;
    }

    public void setProjectCategoryList(List<ProjectCategoryBaseDto> projectCategoryList) {
        this.projectCategoryList = projectCategoryList;
    }
}
