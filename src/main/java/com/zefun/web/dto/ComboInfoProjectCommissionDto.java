package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;


/**
 * 单个套餐查询数据
* @author 高国藩
* @date 2015年10月20日 下午7:45:54
 */
public class ComboInfoProjectCommissionDto {

    /**套餐Id */
    private Integer comboId;
    /**套餐名字*/
    private String comboName;
    /**项目Id */
    private Integer projectId;
    /**项目名称 */
    private String projectName;
    /**项目描述*/
    private String comboDesc;
    /**部门ID*/
    private Integer deptId;
    /**部门名称*/
    private String deptName;
    /**项目图片*/
    private String comboImage;
    /**开始日期*/
    private String startDate;
    /**结束日期*/
    private String endDate;
    /**有效天数*/
    private String validDate;
    /**套餐原价*/
    private BigDecimal projectAmount;
    /**套餐销售价格*/
    private BigDecimal comboSalePrice;
    /**提成方式*/
    private Integer commissionType;
    /**现金提成*/
    private Integer cashCommission;
    /**卡进提成*/
    private Integer cardCommission;
    /** 项目价格 */
    private BigDecimal projectPrice;
    /** 项目数量 */
    private Integer projectCount;
    /** 套餐内业绩计算 */
    private BigDecimal comboPerformanceCal;
    /** 提成比例 */
    private BigDecimal royaltyRate;
    /** 项目明细 */
    private List<ComboProjectCommissionDto> comboProjectCommissionDtos;
    
    public Integer getComboId() {
        return comboId;
    }
    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }
    
    public BigDecimal getRoyaltyRate() {
        return royaltyRate;
    }
    public void setRoyaltyRate(BigDecimal royaltyRate) {
        this.royaltyRate = royaltyRate;
    }
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
    public BigDecimal getProjectPrice() {
        return projectPrice;
    }
    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }
    public Integer getProjectCount() {
        return projectCount;
    }
    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }
    public BigDecimal getComboPerformanceCal() {
        return comboPerformanceCal;
    }
    public void setComboPerformanceCal(BigDecimal comboPerformanceCal) {
        this.comboPerformanceCal = comboPerformanceCal;
    }
    public List<ComboProjectCommissionDto> getComboProjectCommissionDtos() {
        return comboProjectCommissionDtos;
    }
    public void setComboProjectCommissionDtos(
            List<ComboProjectCommissionDto> comboProjectCommissionDtos) {
        this.comboProjectCommissionDtos = comboProjectCommissionDtos;
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getValidDate() {
        return validDate;
    }
    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
    public BigDecimal getProjectAmount() {
        return projectAmount;
    }
    public void setProjectAmount(BigDecimal projectAmount) {
        this.projectAmount = projectAmount;
    }
    
    public BigDecimal getComboSalePrice() {
        return comboSalePrice;
    }
    public void setComboSalePrice(BigDecimal comboSalePrice) {
        this.comboSalePrice = comboSalePrice;
    }
    public Integer getCommissionType() {
        return commissionType;
    }
    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }
    public String getComboName() {
        return comboName;
    }
    public void setComboName(String comboName) {
        this.comboName = comboName;
    }
    public String getComboDesc() {
        return comboDesc;
    }
    public void setComboDesc(String comboDesc) {
        this.comboDesc = comboDesc;
    }
    public String getComboImage() {
        return comboImage;
    }
    public void setComboImage(String comboImage) {
        this.comboImage = comboImage;
    }
    public Integer getCashCommission() {
        return cashCommission;
    }
    public void setCashCommission(Integer cashCommission) {
        this.cashCommission = cashCommission;
    }
    public Integer getCardCommission() {
        return cardCommission;
    }
    public void setCardCommission(Integer cardCommission) {
        this.cardCommission = cardCommission;
    }

}
