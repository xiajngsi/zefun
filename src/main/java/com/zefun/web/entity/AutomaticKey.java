package com.zefun.web.entity;


/**
 * 菜单回复信息
* @author 高国藩
* @date 2015年8月31日 下午4:52:56
 */
public class AutomaticKey {
	/** 主键ID */
	private Integer automaticId;

	/** 菜单key值 */
	private String automaticKey;

	/** 回复类型(1:文 2:图文) */
	private Integer automaticType;

	/** 文字内容 */
	private String automaticText;

	/** 图文素材ID */
	private String mediaId;

	/** 门店标识 */
	private Integer storeId;

	/** @param automaticId	主键ID */
	public void setAutomaticId(Integer automaticId){
		this.automaticId = automaticId;
	}

	/** @return	主键ID */
	public Integer getAutomaticId(){
		return automaticId;
	}

	/** @param automaticKey	菜单key值 */
	public void setAutomaticKey(String automaticKey){
		this.automaticKey = automaticKey;
	}

	/** @return	菜单key值 */
	public String getAutomaticKey(){
		return automaticKey;
	}

	/** @param automaticType	回复类型(1:文 2:图文) */
	public void setAutomaticType(Integer automaticType){
		this.automaticType = automaticType;
	}

	/** @return	回复类型(1:文 2:图文) */
	public Integer getAutomaticType(){
		return automaticType;
	}

	/** @param automaticText	文字内容 */
	public void setAutomaticText(String automaticText){
		this.automaticText = automaticText;
	}

	/** @return	文字内容 */
	public String getAutomaticText(){
		return automaticText;
	}

	/** @param mediaId	图文素材ID */
	public void setMediaId(String mediaId){
		this.mediaId = mediaId;
	}

	/** @return	图文素材ID */
	public String getMediaId(){
		return mediaId;
	}

	/** @param storeId 门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}
	
	/** @return 门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

}