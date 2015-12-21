package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 项目信息基础传输对象
* @author 张进军
* @date Oct 14, 2015 11:36:52 PM 
*/
public class ProjectBaseDto {
    /** 项目标识 */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目图片 */
    private String projectImage;

    /** 项目描述 */
    private String projectDesc;

    /** 项目价格 */
    private BigDecimal projectPrice;
    
    /** 预约价格 */
    private BigDecimal appointmentPrice;

    /** 销售次数 */
    private Integer salesCount;

    /** 销售人数 */
    private Integer salesPeople;
    
    /** 是否接受预约(0:否,1:是) */
    private Integer isAppointment;

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

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    public BigDecimal getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(BigDecimal appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getSalesPeople() {
        return salesPeople;
    }

    public void setSalesPeople(Integer salesPeople) {
        this.salesPeople = salesPeople;
    }

    public Integer getIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }
    
}
