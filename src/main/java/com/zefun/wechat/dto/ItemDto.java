package com.zefun.wechat.dto;

import java.util.List;

/**
 * 拉取永久图文素材,对应字段值
* @author 高国藩
* @date 2015年8月11日 上午12:07:19
 */
public class ItemDto {
    
    /** 图文素材总个数*/
    private String total_count;
    /** 子图文素材个数*/
    private String item_count;
    /** 单个图文素材*/
    private List<NewsItemDto> item;

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}

	public String getItem_count() {
		return item_count;
	}

	public void setItem_count(String item_count) {
		this.item_count = item_count;
	}

	public List<NewsItemDto> getItem() {
		return item;
	}

	public void setItem(List<NewsItemDto> item) {
		this.item = item;
	}

	public ItemDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Item [total_count=" + total_count + ", item_count="
				+ item_count + ", item=" + item + "]";
	}

}
