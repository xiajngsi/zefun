package com.zefun.web.entity;


/**
 * 关注回复
* @author 高国藩
* @date 2015年8月31日 下午4:55:13
 */
public class MsgReply {
	/** 消息ID */
	private Integer msgId;

	/** 回复类型(text news) */
	private String msgType;

	/** 回复文本 */
	private String msgText;

	/** 回复图文mediaId */
	private String mediaId;

	/** 1:关注回复 2：消息回复 */
	private Integer msgStatus;

	/** 门店ID */
	private Integer storeId;
	
	/** 存放图文 */
	private AutomaticReply automaticReply;

	
	public AutomaticReply getAutomaticReply() {
        return automaticReply;
    }

    public void setAutomaticReply(AutomaticReply automaticReply) {
        this.automaticReply = automaticReply;
    }

    /** @param msgId	消息ID */
	public void setMsgId(Integer msgId){
		this.msgId = msgId;
	}

	/** @return	消息ID */
	public Integer getMsgId(){
		return msgId;
	}

	/** @param msgType	回复类型(text picWord) */
	public void setMsgType(String msgType){
		this.msgType = msgType;
	}

	/** @return	回复类型(text picWord) */
	public String getMsgType(){
		return msgType;
	}

	/** @param msgText	回复文本 */
	public void setMsgText(String msgText){
		this.msgText = msgText;
	}

	/** @return	回复文本 */
	public String getMsgText(){
		return msgText;
	}

	/** @param mediaId	回复图文mediaId */
	public void setMediaId(String mediaId){
		this.mediaId = mediaId;
	}

	/** @return	回复图文mediaId */
	public String getMediaId(){
		return mediaId;
	}

	/** @param msgStatus	1:关注回复 2：消息回复 */
	public void setMsgStatus(Integer msgStatus){
		this.msgStatus = msgStatus;
	}

	/** @return	1:关注回复 2：消息回复 */
	public Integer getMsgStatus(){
		return msgStatus;
	}

	/** @param storeId	门店ID */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店ID */
	public Integer getStoreId(){
		return storeId;
	}

}