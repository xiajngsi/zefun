package com.zefun.wechat.dto;

/**
 * 本地存储一个图文消息
* @author 高国藩
* @date 2015年8月11日 上午12:13:06
 */
public class NewsItemsDto {
    /** 标题 */
    private String title;
    /** 永久素材ID */
    private String thumb_media_id;
    /** 是否图片在内容中显示 */
    private String show_cover_pic;
    /** 作者 */
    private String author;
    /** 文本内容 */
    private String content;
    /** 地址 */
    private String url;
    /** 原文链接 */
    private String content_source_url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public NewsItemsDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NewsItems [title=" + title + ", thumb_media_id="
				+ thumb_media_id + ", show_cover_pic=" + show_cover_pic
				+ ", author=" + author + ", content=" + content + ", url="
				+ url + ", content_source_url=" + content_source_url + "]";
	}

}
