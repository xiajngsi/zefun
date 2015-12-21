package com.zefun.web.dto;

import java.math.BigDecimal;

import com.zefun.web.entity.EmployeeInfo;


/**
 * 员工业绩DTO
* @author 王大爷
* @date 2015年8月11日 上午11:20:27
 */
public class SumPriceDto {
    
    /** 总业绩*/
	private BigDecimal sumprice;
	
	/** 员工id*/
	private Integer employeeId;

	/** 劳动业绩*/
	private BigDecimal pprice;
	
	/** 客单价*/
	private BigDecimal mean;
	
	/** 指定客比率*/
	private Integer ratio;
	
	/** 商品业绩*/
	private BigDecimal cprice;
	
	/** 平均劳动业绩*/
    private BigDecimal mpprice;
    
    /** 平均客单价*/
    private BigDecimal mmean;
    
    /** 平均指定客比率*/
    private Integer mratio;
    
    /** 平均商品业绩*/
    private BigDecimal mcprice;
	
    /** 员工信息*/
    private EmployeeInfo employeeInfo;
    
    
    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getSumprice() {
        return sumprice;
    }

    public void setSumprice(BigDecimal sumprice) {
        this.sumprice = sumprice;
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public BigDecimal getMean() {
        return mean;
    }

    public void setMean(BigDecimal mean) {
        this.mean = mean;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getCprice() {
        return cprice;
    }

    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    public BigDecimal getMpprice() {
        return mpprice;
    }

    public void setMpprice(BigDecimal mpprice) {
        this.mpprice = mpprice;
    }

    public BigDecimal getMmean() {
        return mmean;
    }

    public void setMmean(BigDecimal mmean) {
        this.mmean = mmean;
    }

    public Integer getMratio() {
        return mratio;
    }

    public void setMratio(Integer mratio) {
        this.mratio = mratio;
    }

    public BigDecimal getMcprice() {
        return mcprice;
    }

    public void setMcprice(BigDecimal mcprice) {
        this.mcprice = mcprice;
    }
	
    
}
