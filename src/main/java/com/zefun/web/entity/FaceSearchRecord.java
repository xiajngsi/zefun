package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 23:12:56
 */
public class FaceSearchRecord {
	/** 人脸ID */
	private String faceId;

	/** 用户ID */
	private Integer userId;

	/** 相似度 */
	private BigDecimal similarity;

	/** 创建时间 */
	private String createTime;

	/** @param faceId	人脸ID */
	public void setFaceId(String faceId){
		this.faceId = faceId;
	}

	/** @return	人脸ID */
	public String getFaceId(){
		return faceId;
	}

	/** @param userId	用户ID */
	public void setUserId(Integer userId){
		this.userId = userId;
	}

	/** @return	用户ID */
	public Integer getUserId(){
		return userId;
	}

	/** @param similarity	相似度 */
	public void setSimilarity(BigDecimal similarity){
		this.similarity = similarity;
	}

	/** @return	相似度 */
	public BigDecimal getSimilarity(){
		return similarity;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

}