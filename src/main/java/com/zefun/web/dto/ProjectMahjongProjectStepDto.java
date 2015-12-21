package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;
/**
 * 项目信息Dto
* @author 洪秋霞
* @date 2015年8月11日 上午10:43:57
 */
public class ProjectMahjongProjectStepDto {
    /** 项目标识 */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目图片 */
    private String projectImage;
    
    /** 项目价格 */
    private BigDecimal projectPrice;
    
    /** */
    private List<ShiftStepDto> shiftStepDtoList;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    public List<ShiftStepDto> getShiftStepDtoList() {
        return shiftStepDtoList;
    }

    public void setShiftStepDtoList(List<ShiftStepDto> shiftStepDtoList) {
        this.shiftStepDtoList = shiftStepDtoList;
    }
    
}
