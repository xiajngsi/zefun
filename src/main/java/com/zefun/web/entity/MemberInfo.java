package com.zefun.web.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MemberInfo {
	/** 会员标识 */
	private Integer memberId;

	/** 门店标识 */
	private Integer storeId;

	/** 级别标识 */
	private Integer levelId;

	/** 介绍人标识 */
	private Integer recommendId;

	/** 姓名 */
	private String name;
	
	/** 头像 */
	private String headUrl;

	/** 性别 */
	private String sex;

	/** 生日 */
	private String birthday;

	/** 手机号码 */
	private String phone;

	/** 所住小区 */
	private String community;

	/** 通知方式*/
	private Integer messageType;
	
	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	
	public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param levelId	级别标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	级别标识 */
	public Integer getLevelId(){
		return levelId;
	}

	/** @param recommendId	介绍人标识 */
	public void setRecommendId(Integer recommendId){
		this.recommendId = recommendId;
	}

	/** @return	介绍人标识 */
	public Integer getRecommendId(){
		return recommendId;
	}

	/** @param name	姓名 */
	public void setName(String name){
		this.name = name;
	}

	/** @return	姓名 */
	public String getName(){
		return name;
	}
	
	/** @param headUrl	头像 */
	public void setHeadUrl(String headUrl){
		this.headUrl = headUrl;
	}

	/** @return	头像 */
	public String getHeadUrl(){
		return headUrl;
	}

	/** @param sex	性别 */
	public void setSex(String sex){
		this.sex = sex;
	}

	/** @return	性别 */
	public String getSex(){
		return sex;
	}

	/** @param birthday	生日 */
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	/** @return	生日 */
	public String getBirthday(){
		return birthday;
	}

	/** @param phone	手机号码 */
	public void setPhone(String phone){
		this.phone = phone;
	}

	/** @return	手机号码 */
	public String getPhone(){
		return phone;
	}

	/** @param community	所住小区 */
	public void setCommunity(String community){
		this.community = community;
	}

	/** @return	所住小区 */
	public String getCommunity(){
		return community;
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

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}
	
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }


    @Override
    public String toString() {
        return "MemberInfo [storeId=" + storeId + ", levelId=" + levelId
                + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", messageType=" + messageType
                + ", createTime=" + createTime + ", isDeleted=" + isDeleted
                + ", lastOperatorId=" + lastOperatorId + "]";
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}