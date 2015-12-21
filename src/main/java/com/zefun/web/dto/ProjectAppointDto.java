package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目详情数据传输对象
* @author 张进军
* @date Oct 18, 2015 4:10:02 PM 
*/
public class ProjectAppointDto {
    /** 项目标识 */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目图片 */
    private String projectImage;
    
    /** 附属图片*/
    private String affiliatedImage;

    /** 项目描述 */
    private String projectDesc;

    /** 项目价格 */
    private BigDecimal projectPrice;
    
    /** 预约优惠金额 */
    private BigDecimal appointmentPrice;

    /** 销售次数 */
    private Integer salesCount;

    /** 销售人数 */
    private Integer salesPeople;
    
    /** 项目步骤标识 */
    private Integer projectStepId;

    /** 轮牌信息标识 */
    private Integer shiftMahjongId;

    /** 步骤顺序 */
    private Integer projectStepOrder;
    
    /**服务员工列表*/
    private List<EmployeeBaseDto> employeeList;

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

    public String getAffiliatedImage() {
        return affiliatedImage;
    }

    public void setAffiliatedImage(String affiliatedImage) {
        this.affiliatedImage = affiliatedImage;
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

    public Integer getProjectStepId() {
        return projectStepId;
    }

    public void setProjectStepId(Integer projectStepId) {
        this.projectStepId = projectStepId;
    }

    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    public Integer getProjectStepOrder() {
        return projectStepOrder;
    }

    public void setProjectStepOrder(Integer projectStepOrder) {
        this.projectStepOrder = projectStepOrder;
    }

    public List<EmployeeBaseDto> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeBaseDto> employeeList) {
        this.employeeList = employeeList;
    }
    
}
