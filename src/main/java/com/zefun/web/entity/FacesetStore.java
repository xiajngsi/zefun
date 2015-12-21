package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 23:12:56
 */
public class FacesetStore {
	/** 人脸集合ID */
	private String setId;

	/** 店铺ID */
	private Integer storeId;

	/** @param setId	人脸集合ID */
	public void setSetId(String setId){
		this.setId = setId;
	}

	/** @return	人脸集合ID */
	public String getSetId(){
		return setId;
	}

	/** @param storeId	店铺ID */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	店铺ID */
	public Integer getStoreId(){
		return storeId;
	}

}