package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月19日 PM 15:52:59
 */
public class WechatEmployee {
	/** 微信标识 */
	private String openId;

	/** 员工标识 */
	private Integer employeeId;

	/** 是否关注(0:未关注,1:已关注) */
	private Integer isSubscribe;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** @param openId	微信标识 */
	public void setOpenId(String openId){
		this.openId = openId;
	}

	/** @return	微信标识 */
	public String getOpenId(){
		return openId;
	}

	/** @param employeeId	员工标识 */
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}

	/** @return	员工标识 */
	public Integer getEmployeeId(){
		return employeeId;
	}

	/** @param isSubscribe	是否关注(0:未关注,1:已关注) */
	public void setIsSubscribe(Integer isSubscribe){
		this.isSubscribe = isSubscribe;
	}

	/** @return	是否关注(0:未关注,1:已关注) */
	public Integer getIsSubscribe(){
		return isSubscribe;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
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
}