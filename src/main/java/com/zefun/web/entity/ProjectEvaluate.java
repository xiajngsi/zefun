package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月28日 AM 11:08:01
 */
public class ProjectEvaluate {
	/** 评价标识 */
	private Integer id;

	/** 评价人标识 */
	private Integer memberId;

	/** 关联订单标识 */
	private Integer orderId;

	/** 订单明细标识 */
	private Integer detailId;

	/** 项目标识 */
	private Integer projectId;

	/** 评价分数 */
	private Integer evaluateRate;

	/** 评价内容 */
	private String comment;

	/** 评价时间 */
	private String time;

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

	/** @param orderId	关联订单标识 */
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}

	/** @return	关联订单标识 */
	public Integer getOrderId(){
		return orderId;
	}

	/** @param detailId	订单明细标识 */
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}

	/** @return	订单明细标识 */
	public Integer getDetailId(){
		return detailId;
	}

	/** @param projectId	项目标识 */
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}

	/** @return	项目标识 */
	public Integer getProjectId(){
		return projectId;
	}

	/** @param evaluateRate	评价分数 */
	public void setEvaluateRate(Integer evaluateRate){
		this.evaluateRate = evaluateRate;
	}

	/** @return	评价分数 */
	public Integer getEvaluateRate(){
		return evaluateRate;
	}

	/** @param comment	评价内容 */
	public void setComment(String comment){
		this.comment = comment;
	}

	/** @return	评价内容 */
	public String getComment(){
		return comment;
	}

	/** @param time	评价时间 */
	public void setTime(String time){
		this.time = time;
	}

	/** @return	评价时间 */
	public String getTime(){
		return time;
	}

}