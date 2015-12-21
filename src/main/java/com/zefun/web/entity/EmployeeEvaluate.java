package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月28日 AM 11:08:01
 */
public class EmployeeEvaluate {
	/** 评价标识 */
	private Integer id;

	/** 评价人标识 */
	private Integer memberId;

	/** 订单明细标识 */
	private Integer detailId;

	/** 员工标识 */
	private Integer employeeId;

	/** 评价分数 */
	private Integer evaluateRate;

	/** 评价时间 */
	private String createTime;

	/** @param id	评价标识 */
	public void setId(Integer id){
		this.id = id;
	}

	/** @return	评价标识 */
	public Integer getId(){
		return id;
	}

	/** @param memberId	评价人标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	评价人标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param detailId	订单明细标识 */
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}

	/** @return	订单明细标识 */
	public Integer getDetailId(){
		return detailId;
	}

	/** @param employeeId	员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param evaluateRate	评价分数 */
	public void setEvaluateRate(Integer evaluateRate){
		this.evaluateRate = evaluateRate;
	}

	/** @return	评价分数 */
	public Integer getEvaluateRate(){
		return evaluateRate;
	}

	/** @param createTime	评价时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	评价时间 */
	public String getCreateTime(){
		return createTime;
	}

}