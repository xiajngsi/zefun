package com.zefun.wechat.dto;

/**
 * 群发消息内容，单个图文消息
* @author 高国藩
* @date 2015年8月11日 上午12:08:17
 */
public class ThumbDto {
	/** 用于七牛图片路径 */
	private String imgUrl;
	/** 用于对应微信的图片 */
	private String PicUrl;
	/** 上传得到的永久素材ID */
	private String thumb_media_id;
	/** 作者 */
	private String author;
	/** 标题 */
	private String title;
	/** 原文链接 */
	private String content_source_url;
	/** 内容 */
	private String content;
	/** 摘要 */
	private String digest;
	/** 描述，对应了数据库中，用于自动回复 */
	private String Description;
	/** 是否显示封面，0为false，即不显示，1为true，即显示 */
	private String show_cover_pic;

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}

	public ThumbDto() {
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public ThumbDto(String thumb_media_id, String author, String title,
			String content_source_url, String content, String digest,
			String show_cover_pic) {
		super();
		this.thumb_media_id = thumb_media_id;
		this.author = author;
		this.title = title;
		this.content_source_url = content_source_url;
		this.content = content;
		this.digest = digest;
		this.show_cover_pic = show_cover_pic;
	}
}
