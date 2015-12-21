package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.ShiftMahjongEmployee;
/**
 * 步骤Dto
* @author 老王
* @date 2015年8月11日 上午10:43:57
 */
public class ShiftStepDto {
    /** 轮牌信息标识 */
    private Integer shiftMahjongId;
    /**轮牌名称*/
    private String shiftMahjongName;
    /** 项目步骤标识 */
    private Integer projectStepId;
    
    /** 项目类型(1：普通项目，2：团购项目) */
    private String projectStepName;

    /** 项目图片 */
    private String isDisable;
    /** 项目标识*/
    private String projectId;
    
    /** 轮牌员工集合*/
    private List<ShiftMahjongEmployee> shiftMahjongEmployeeList;

    
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    public String getShiftMahjongName() {
        return shiftMahjongName;
    }

    public void setShiftMahjongName(String shiftMahjongName) {
        this.shiftMahjongName = shiftMahjongName;
    }

    public Integer getProjectStepId() {
        return projectStepId;
    }

    public void setProjectStepId(Integer projectStepId) {
        this.projectStepId = projectStepId;
    }

    public String getProjectStepName() {
        return projectStepName;
    }

    public void setProjectStepName(String projectStepName) {
        this.projectStepName = projectStepName;
    }
    

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    public List<ShiftMahjongEmployee> getShiftMahjongEmployeeList() {
        return shiftMahjongEmployeeList;
    }

    public void setShiftMahjongEmployeeList(
            List<ShiftMahjongEmployee> shiftMahjongEmployeeList) {
        this.shiftMahjongEmployeeList = shiftMahjongEmployeeList;
    }
    
    
}
