package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 23:12:56
 */
public class FaceInfo {
	/** 人脸ID */
	private String faceId;

	/** 集合ID */
	private String setId;

	/** 用户ID */
	private Integer userId;

	/** @param faceId	人脸ID */
	public void setFaceId(String faceId){
		this.faceId = faceId;
	}

	/** @return	人脸ID */
	public String getFaceId(){
		return faceId;
	}

	/** @param setId	集合ID */
	public void setSetId(String setId){
		this.setId = setId;
	}

	/** @return	集合ID */
	public String getSetId(){
		return setId;
	}

	/** @param userId	用户ID */
	public void setUserId(Integer userId){
		this.userId = userId;
	}

	/** @return	用户ID */
	public Integer getUserId(){
		return userId;
	}

}