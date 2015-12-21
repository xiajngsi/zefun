package com.zefun.web.dto;

import java.util.List;

/**
 * 部门商品数据传输对象
* @author 高国藩
* @date 2015年10月16日 上午10:51:50
 */
public class DeptGoodsBaseDto {

    /**部门标识*/
    private Integer deptId;
    
    /**部门名称*/
    private String deptName;
    
    /** 商品类别列表 */
    private List<GoodsCategoryBaseDto> goodsCategoryBaseDto;

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

    public List<GoodsCategoryBaseDto> getGoodsCategoryBaseDto() {
        return goodsCategoryBaseDto;
    }

    public void setGoodsCategoryBaseDto(
            List<GoodsCategoryBaseDto> goodsCategoryBaseDto) {
        this.goodsCategoryBaseDto = goodsCategoryBaseDto;
    }
    
}
