package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.ShiftMahjong;

/**
 * 关于部门、岗位 职位连级查询
* @author chendb
* @date 2015年9月7日 下午3:59:13
 */
public class DeptInfoDto {
    /**部门标识*/
    private Integer deptId;
    /**部门编码*/
    private Integer deptCode;
    /**部门名称*/
    private String deptName;
    /**选择*/
    private Integer isResults;
    /**岗位集合*/
    private List<PositionInfoDto> positionInfo;
    /** 轮牌信息*/
    private List<ShiftMahjong> shiftMahjongList;
    /**项目类别Dto*/
    private List<ProjectCategoryDto> projectCategoryDtoList;
    /**套餐信息*/
    private List<ComboInfo> comboInfoList;
    /**商品类别*/
    private List<GoodsCategoryDto> goodsCategoryDtoList;

    public Integer getIsResults() {
        return isResults;
    }

    public void setIsResults(Integer isResults) {
        this.isResults = isResults;
    }

    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    public List<ShiftMahjong> getShiftMahjongList() {
        return shiftMahjongList;
    }

    public void setShiftMahjongList(List<ShiftMahjong> shiftMahjongList) {
        this.shiftMahjongList = shiftMahjongList;
    }

    public List<PositionInfoDto> getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(List<PositionInfoDto> positionInfo) {
        this.positionInfo = positionInfo;
    }

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

    public List<ProjectCategoryDto> getProjectCategoryDtoList() {
        return projectCategoryDtoList;
    }

    public void setProjectCategoryDtoList(List<ProjectCategoryDto> projectCategoryDtoList) {
        this.projectCategoryDtoList = projectCategoryDtoList;
    }

    public List<ComboInfo> getComboInfoList() {
        return comboInfoList;
    }

    public void setComboInfoList(List<ComboInfo> comboInfoList) {
        this.comboInfoList = comboInfoList;
    }

    public List<GoodsCategoryDto> getGoodsCategoryDtoList() {
        return goodsCategoryDtoList;
    }

    public void setGoodsCategoryDtoList(List<GoodsCategoryDto> goodsCategoryDtoList) {
        this.goodsCategoryDtoList = goodsCategoryDtoList;
    }

}
