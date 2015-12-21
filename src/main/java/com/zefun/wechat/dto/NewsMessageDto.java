package com.zefun.wechat.dto;

import java.util.List;

/**
 * 图文消息对应微信字段，用于json转换
* @author 高国藩
* @date 2015年8月11日 上午12:13:52
 */
public class NewsMessageDto extends BaseMessageDto {
    /** 图文消息个数，限制为8条以内 */
	private int ArticleCount;
	/** 多条图文消息信息，默认第一个item为大图 */
	private List<ArticleDto> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleDto> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleDto> articles) {
		Articles = articles;
	}
}