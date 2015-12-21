package com.zefun.web.entity;


/**
 * 部门信息
* @author chendb
* @date 2015年9月8日 上午10:08:53
 */
public class DeptInfo {
    /**部门标识*/
    private Integer deptId;
    /**门店标识*/
    private Integer storeId;
    /**部门编码*/
    private Integer deptCode;
    /**部门名称*/
    private String deptName;
    /**是否产生业绩*/
    private Integer isResults;
    /**操作时间*/
    private String operateTime;
    /**操作人员*/
    private Integer operateId;

    public Integer getIsResults() {
        return isResults;
    }

    public void setIsResults(Integer isResults) {
        this.isResults = isResults;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime == null ? null : operateTime.trim();
    }

    public Integer getOperateId() {
        return operateId;
    }

    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }


}