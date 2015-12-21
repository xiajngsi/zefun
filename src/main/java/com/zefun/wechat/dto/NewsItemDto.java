package com.zefun.wechat.dto;

/**
 * 单个图文消息
* @author 高国藩
* @date 2015年8月11日 下午2:21:49
 */
public class NewsItemDto {
    /** 素材ID*/
	private String media_id;
	/** 文本描述*/
	private ItmesDto content;
	/** 更新时间*/
	private String update_time;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public ItmesDto getContent() {
		return content;
	}

	public void setContent(ItmesDto content) {
		this.content = content;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public NewsItemDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NewsItem [media_id=" + media_id + ", content=" + content
				+ ", update_time=" + update_time + "]";
	}

}
