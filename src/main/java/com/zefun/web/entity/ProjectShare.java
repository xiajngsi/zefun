package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月14日 PM 13:42:32
 */
public class ProjectShare {
	/** 分享标识 */
	private Integer id;

	/** 订单标识 */
	private Integer orderId;

	/** 会员标识 */
	private Integer memberId;

	/** 项目标识 */
	private Integer projectId;

	/** 分享内容 */
	private String content;

	/** 分享照片 */
	private String imgUrl;

	/** 分享时间 */
	private String time;

	/** @param id	分享标识 */
	public void setId(Integer id){
		this.id = id;
	}

	/** @return	分享标识 */
	public Integer getId(){
		return id;
	}

	/** @param orderId	订单标识 */
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}

	/** @return	订单标识 */
	public Integer getOrderId(){
		return orderId;
	}

	/** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param projectId	项目标识 */
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}

	/** @return	项目标识 */
	public Integer getProjectId(){
		return projectId;
	}

	/** @param content	分享内容 */
	public void setContent(String content){
		this.content = content;
	}

	/** @return	分享内容 */
	public String getContent(){
		return content;
	}

	/** @param imgUrl	分享照片 */
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}

	/** @return	分享照片 */
	public String getImgUrl(){
		return imgUrl;
	}

	/** @param time	分享时间 */
	public void setTime(String time){
		this.time = time;
	}

	/** @return	分享时间 */
	public String getTime(){
		return time;
	}

}