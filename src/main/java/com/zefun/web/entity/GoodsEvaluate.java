package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月13日 PM 15:22:13
 */
public class GoodsEvaluate {
	/** 评价标识 */
	private Integer id;

	/** 关联订单标识 */
	private Integer orderId;

	/** 订单明细标识 */
	private Integer detailId;

	/** 商品标识 */
	private Integer goodsId;

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

	/** @param goodsId	商品标识 */
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}

	/** @return	商品标识 */
	public Integer getGoodsId(){
		return goodsId;
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