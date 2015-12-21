package com.zefun.wechat.dto;

/**
 * 图文消息medaiId特殊处理
* @author 高国藩
* @date 2015年8月11日 上午12:19:43
 */
public class MediaIdDto {
    /** 图文消息 ID*/
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public MediaIdDto() {
		// TODO Auto-generated constructor stub
	}

	public MediaIdDto(String media_id) {
		super();
		this.media_id = media_id;
	}

}