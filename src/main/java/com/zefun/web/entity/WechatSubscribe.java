package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月25日 PM 20:59:48
 */
public class WechatSubscribe {
	/** 微信标识 */
	private String openId;

	/** 是否关注(0:未关注，1:已关注) */
	private Integer isSubscribe;

	/** 关注时间 */
	private String createTime;

	/** 取消关注时间/再次关注时间 */
	private String updateTime;

	/** @param openId	微信标识 */
	public void setOpenId(String openId){
		this.openId = openId;
	}

	/** @return	微信标识 */
	public String getOpenId(){
		return openId;
	}

	/** @param isSubscribe	是否关注(0:未关注，1:已关注) */
	public void setIsSubscribe(Integer isSubscribe){
		this.isSubscribe = isSubscribe;
	}

	/** @return	是否关注(0:未关注，1:已关注) */
	public Integer getIsSubscribe(){
		return isSubscribe;
	}

	/** @param createTime	关注时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	关注时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	取消关注时间/再次关注时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	取消关注时间/再次关注时间 */
	public String getUpdateTime(){
		return updateTime;
	}

}