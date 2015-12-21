package com.zefun.web.dto;
/**
 * 组织架构Dto
* @author chendb
* @date 2015年10月19日 下午2:40:03
 */
public class OrganizationDto {
    /** 门店标识 */
    private Integer storeId;
    /**部门标识*/
    private Integer deptId;
    /**部门编码*/
    private Integer deptCode;
    /**部门名称*/
    private String deptName;
    /**选择*/
    private Integer isResults;
    /** 岗位标识*/
    private Integer positionId;
    /** 岗位编码*/
    private Integer positionCode;
    /** 岗位名称*/
    private String positionName;
    /** 级别标识 */
    private Integer levelId;
    /** 级别名称 */
    private String levelName;
    /***导入时候行数*/
    private Integer num;
    
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Integer getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public Integer getIsResults() {
        return isResults;
    }
    public void setIsResults(Integer isResults) {
        this.isResults = isResults;
    }
    public Integer getPositionId() {
        return positionId;
    }
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
    
    public Integer getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(Integer positionCode) {
        this.positionCode = positionCode;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public Integer getLevelId() {
        return levelId;
    }
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
    public String getLevelName() {
        return levelName;
    }
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

}
