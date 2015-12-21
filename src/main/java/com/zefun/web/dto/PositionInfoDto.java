package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.EmployeeLevel;
/**
 * 岗位相关信息
* @author chendb
* @date 2015年8月24日 下午3:31:05
 */
public class PositionInfoDto {
    /** 岗位标识*/
    private Integer positionId;
    /** 岗位编码*/
    private String positionCode;
    /** 岗位名称*/
    private String positionName;
    /** 是否可夸部门*/
    private Integer isDept;
   /**职位相关信息*/
    private List<EmployeeLevel> employeeLeve;
    
    
    public Integer getIsDept() {
        return isDept;
    }
    public void setIsDept(Integer isDept) {
        this.isDept = isDept;
    }
    public Integer getPositionId() {
        return positionId;
    }
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public List<EmployeeLevel> getEmployeeLeve() {
        return employeeLeve;
    }
    public void setEmployeeLeve(List<EmployeeLevel> employeeLeve) {
        this.employeeLeve = employeeLeve;
    }
    

}
