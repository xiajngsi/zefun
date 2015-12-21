package com.zefun.wechat.dto;

import java.util.List;

/**
 * 通过mediaId获得图文消息对应实体
* @author 高国藩
* @date 2015年8月11日 上午12:07:51
 */
public class ItmesDto {
    /** 子图文消息*/
	private List<NewsItemsDto> news_item;

	public List<NewsItemsDto> getNews_item() {
		return news_item;
	}

	public void setNews_item(List<NewsItemsDto> news_item) {
		this.news_item = news_item;
	}

	public ItmesDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Itmes [news_item=" + news_item + "]";
	}

}
