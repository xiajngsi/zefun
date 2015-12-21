package com.zefun.wechat.dto;

/**
 * 文本消息对应微信字段，用于json映射
* @author 高国藩
* @date 2015年8月11日 上午12:14:26
 */
public class TextMessageDto extends BaseMessageDto {
    /** 回复的消息内容 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}