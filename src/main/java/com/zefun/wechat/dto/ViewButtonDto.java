package com.zefun.wechat.dto;

/**
 * 子按钮(跳转url)
* @author 高国藩
* @date 2015年8月6日 下午8:36:53 
*
 */
public class ViewButtonDto extends ButtonDto {
    /** 按钮类型 click 和 view*/
	private String type;
	/** 按钮跳转地址*/
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}