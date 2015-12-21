package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

import com.zefun.web.entity.MemberComboProject;

/**
 * 会员套餐信息传输对象
* @author 张进军
* @date Oct 24, 2015 10:50:14 AM 
*/
public class MemberComboDto {
    /** 记录标识 */
    private Integer recordId;

    /** 套餐标识 */
    private Integer comboId;

    /** 套餐名称 */
    private String comboName;

    /** 套餐价格 */
    private BigDecimal comboPrice;

    /** 套餐图片 */
    private String comboImage;

    /** 项目总价 */
    private BigDecimal projectAmount;

    /** 项目数量 */
    private Integer projectCount;

    /** 剩余数量 */
    private Integer remainingCount;
    
    /** 消费门店*/
    private String storeName;
    
    /** 销售人员*/
    private String lastOperatorName;
    
    /** 过期时间*/
    private String overdueTime;
    /** 创建时间*/
    private String createTime;
    
    /**项目列表*/
    private List<MemberComboProject> projectList;

    
    
    
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(String overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getLastOperatorName() {
        return lastOperatorName;
    }

    public void setLastOperatorName(String lastOperatorName) {
        this.lastOperatorName = lastOperatorName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public BigDecimal getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(BigDecimal comboPrice) {
        this.comboPrice = comboPrice;
    }

    public String getComboImage() {
        return comboImage;
    }

    public void setComboImage(String comboImage) {
        this.comboImage = comboImage;
    }

    public BigDecimal getProjectAmount() {
        return projectAmount;
    }

    public void setProjectAmount(BigDecimal projectAmount) {
        this.projectAmount = projectAmount;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(Integer remainingCount) {
        this.remainingCount = remainingCount;
    }

    public List<MemberComboProject> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<MemberComboProject> projectList) {
        this.projectList = projectList;
    }
    
}
