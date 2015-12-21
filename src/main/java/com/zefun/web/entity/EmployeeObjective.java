package com.zefun.web.entity;

import java.math.BigDecimal;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeObjective {
	/** 目标标识 */
	private Integer objectiveId;

	/** 员工标识 */
	private Integer employeeId;

	/** 总体项目目标 */
	private BigDecimal totalProjectSales;
	
	/** 实际劳动业绩目标*/
	private BigDecimal actualTotalProjectSales;

	/** 指定项目目标 */
	private BigDecimal assignProjectSales;

	/** 实际指定项目目标*/
	private BigDecimal actualAssignProjectSales;
	
	/** 套餐目标 */
	private BigDecimal comboSales;

	/** 实际套餐销售目标*/
	private BigDecimal actualComboSales;
	
	/** 商品目标 */
	private BigDecimal goodsSales;

	/** 实际商品销售目标 */
    private BigDecimal actualGoodsSales;
	
	/** 充值目标 */
	private BigDecimal chargeSales;
	
	/** 实际开卡/充值目标 */
    private BigDecimal actualChargeSales;

	/** 目标月份 */
	private String objectiveMonth;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	
	
	public BigDecimal getActualTotalProjectSales() {
        return actualTotalProjectSales;
    }

    public void setActualTotalProjectSales(BigDecimal actualTotalProjectSales) {
        this.actualTotalProjectSales = actualTotalProjectSales;
    }

    public BigDecimal getActualAssignProjectSales() {
        return actualAssignProjectSales;
    }

    public void setActualAssignProjectSales(BigDecimal actualAssignProjectSales) {
        this.actualAssignProjectSales = actualAssignProjectSales;
    }

    public BigDecimal getActualComboSales() {
        return actualComboSales;
    }

    public void setActualComboSales(BigDecimal actualComboSales) {
        this.actualComboSales = actualComboSales;
    }

    public BigDecimal getActualGoodsSales() {
        return actualGoodsSales;
    }

    public void setActualGoodsSales(BigDecimal actualGoodsSales) {
        this.actualGoodsSales = actualGoodsSales;
    }

    public BigDecimal getActualChargeSales() {
        return actualChargeSales;
    }

    public void setActualChargeSales(BigDecimal actualChargeSales) {
        this.actualChargeSales = actualChargeSales;
    }

    /** @param objectiveId	目标标识 */
	public void setObjectiveId(Integer objectiveId){
		this.objectiveId = objectiveId;
	}

	/** @return	目标标识 */
	public Integer getObjectiveId(){
		return objectiveId;
	}

	/** @param employeeId	员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

    

	public BigDecimal getTotalProjectSales() {
        return totalProjectSales;
    }

    public void setTotalProjectSales(BigDecimal totalProjectSales) {
        this.totalProjectSales = totalProjectSales;
    }

    public BigDecimal getAssignProjectSales() {
        return assignProjectSales;
    }

    public void setAssignProjectSales(BigDecimal assignProjectSales) {
        this.assignProjectSales = assignProjectSales;
    }

    public BigDecimal getComboSales() {
        return comboSales;
    }

    public void setComboSales(BigDecimal comboSales) {
        this.comboSales = comboSales;
    }

    public BigDecimal getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(BigDecimal goodsSales) {
        this.goodsSales = goodsSales;
    }

    public BigDecimal getChargeSales() {
        return chargeSales;
    }

    public void setChargeSales(BigDecimal chargeSales) {
        this.chargeSales = chargeSales;
    }

    /** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getObjectiveMonth() {
        return objectiveMonth;
    }

    public void setObjectiveMonth(String objectiveMonth) {
        this.objectiveMonth = objectiveMonth;
    }

    /** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

}