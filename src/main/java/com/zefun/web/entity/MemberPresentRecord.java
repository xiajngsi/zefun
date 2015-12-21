package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年12月10日 PM 19:55:17
 */
public class MemberPresentRecord {
	/** 赠送标识 */
	private Integer id;

	/** 会员标识 */
	private Integer memberId;

	/** 操作员工标识 */
	private Integer employeeId;

	/** 赠送类型(1:积分，2:礼金，3:优惠券) */
	private Integer type;

	/** 关联标识/积分数量/礼金金额 */
	private Integer gift;

	/** 备注 */
	private String comment;

	/** 赠送时间 */
	private String time;

	/** @param id	赠送标识 */
	public void setId(Integer id){
		this.id = id;
	}

	/** @return	赠送标识 */
	public Integer getId(){
		return id;
	}

	/** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param employeeId	操作员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	操作员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param type	赠送类型(1:积分，2:礼金，3:优惠券) */
	public void setType(Integer type){
		this.type = type;
	}

	/** @return	赠送类型(1:积分，2:礼金，3:优惠券) */
	public Integer getType(){
		return type;
	}

	/** @param gift	关联标识/积分数量/礼金金额 */
	public void setGift(Integer gift){
		this.gift = gift;
	}

	/** @return	关联标识/积分数量/礼金金额 */
	public Integer getGift(){
		return gift;
	}

	/** @param comment	备注 */
	public void setComment(String comment){
		this.comment = comment;
	}

	/** @return	备注 */
	public String getComment(){
		return comment;
	}

	/** @param time	赠送时间 */
	public void setTime(String time){
		this.time = time;
	}

	/** @return	赠送时间 */
	public String getTime(){
		return time;
	}

}