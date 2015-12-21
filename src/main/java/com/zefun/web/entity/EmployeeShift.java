package com.zefun.web.entity;

/**
 * 员工班次关联表
 * @author lzc
 *
 */
public class EmployeeShift {
	/** 主标识(id) */
    private Integer dataId;

    /** 员工标识 */
    private Integer employeeId;

    /** 周一班次 */
    private Integer shifIda;

    /** 周二班次 */
    private Integer shifIdb;

    /** 周三班次 */
    private Integer shifIdc;

    /** 周四班次 */
    private Integer shifIdd;

    /** 周五班次 */
    private Integer shifIde;

    /** 周六班次 */
    private Integer shifIdf;

    /** 周日班次 */
    private Integer shifIdg;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getShifIda() {
        return shifIda;
    }

    public void setShifIda(Integer shifIda) {
        this.shifIda = shifIda;
    }

    public Integer getShifIdb() {
        return shifIdb;
    }

    public void setShifIdb(Integer shifIdb) {
        this.shifIdb = shifIdb;
    }

    public Integer getShifIdc() {
        return shifIdc;
    }

    public void setShifIdc(Integer shifIdc) {
        this.shifIdc = shifIdc;
    }

    public Integer getShifIdd() {
        return shifIdd;
    }

    public void setShifIdd(Integer shifIdd) {
        this.shifIdd = shifIdd;
    }

    public Integer getShifIde() {
        return shifIde;
    }

    public void setShifIde(Integer shifIde) {
        this.shifIde = shifIde;
    }

    public Integer getShifIdf() {
        return shifIdf;
    }

    public void setShifIdf(Integer shifIdf) {
        this.shifIdf = shifIdf;
    }

    public Integer getShifIdg() {
        return shifIdg;
    }

    public void setShifIdg(Integer shifIdg) {
        this.shifIdg = shifIdg;
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
}