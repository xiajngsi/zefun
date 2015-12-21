package com.zefun.web.dto;

import java.util.List;

/**
 * 部门下轮牌信息，包括牌位下的职位
* @author 张进军
* @date Oct 15, 2015 12:56:03 AM 
*/
public class DeptMahjongDto {
    /**部门标识*/
    private Integer deptId;
    
    /**部门名称*/
    private String deptName;
    
    /** 轮牌列表 */
    private List<MahjongLevelDto> mahjongLevelList;

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

    public List<MahjongLevelDto> getMahjongLevelList() {
        return mahjongLevelList;
    }

    public void setMahjongLevelList(List<MahjongLevelDto> mahjongLevelList) {
        this.mahjongLevelList = mahjongLevelList;
    }

}
