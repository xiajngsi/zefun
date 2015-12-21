package com.zefun.web.dto;

import java.math.BigDecimal;

import com.zefun.web.entity.EmployeeInfo;

/**
 * 开支记账DTO
* @author 王大爷
* @date 2015年8月11日 上午11:20:27
 */
public class StoreFlowBaseDto {
    
    /** 流水标识 */
    private Integer flowId;

    /** 店铺标识 */
    private Integer storeId;
    
    /** 部门标识*/
    private Integer deptId;

    /** 分期月数*/
    private Integer flowNum;
    
    /** 流水类型(1:支出,2:收入) */
    private Integer flowType;

    /** 流水金额 */
    private BigDecimal flowAmount;

    /** 当前余额 */
    private BigDecimal balanceAmount;

    /** 业务类别 */
    private String businessType;

    /** 业务项目 */
    private String businessProject;

    /** 业务内容 */
    private String businessDesc;

    /** 资金来源 */
    private String flowSource;

    /** 流水时间 */
    private String flowTime;

    /** 负责人标识 */
    private Integer principalId;
    
    /** 负责人*/
    private EmployeeInfo principal;

    /** 操作人标识 */
    private Integer operatorId;

    /** 操作人*/
    private EmployeeInfo operator;
    
    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Integer flowNum) {
        this.flowNum = flowNum;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(BigDecimal flowAmount) {
        this.flowAmount = flowAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessProject() {
        return businessProject;
    }

    public void setBusinessProject(String businessProject) {
        this.businessProject = businessProject;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getFlowSource() {
        return flowSource;
    }

    public void setFlowSource(String flowSource) {
        this.flowSource = flowSource;
    }

    public String getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(String flowTime) {
        this.flowTime = flowTime;
    }

    public Integer getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Integer principalId) {
        this.principalId = principalId;
    }

    public EmployeeInfo getPrincipal() {
        return principal;
    }

    public void setPrincipal(EmployeeInfo principal) {
        this.principal = principal;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public EmployeeInfo getOperator() {
        return operator;
    }

    public void setOperator(EmployeeInfo operator) {
        this.operator = operator;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
