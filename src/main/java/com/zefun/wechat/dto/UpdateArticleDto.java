package com.zefun.wechat.dto;

/**
 * 修改图文消息详情
* @author 高国藩
* @date 2015年8月31日 下午7:37:45
 */
public class UpdateArticleDto {
    private String title;
    private String thumb_media_id;
    private String author;
    private String digest;
    @SuppressWarnings("unused")
    private String show_cover_pic;
    private String content;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDigest() {
        return digest;
    }
    public void setDigest(String digest) {
        this.digest = digest;
    }
    public String getShow_cover_pic() {
        return "0";
    }
    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = "0";
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
   
    public String getContent_source_url() {
        return content_source_url;
    }
    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }
    public UpdateArticleDto() {
        // TODO Auto-generated constructor stub
    }
    public UpdateArticleDto(String title, String thumb_media_id, String author,
            String digest,  String content, String content_source_url) {
        super();
        this.title = title;
        this.thumb_media_id = thumb_media_id;
        this.author = author;
        this.digest = digest;
        this.content = content;
        this.content_source_url = content_source_url;
    }
    
    
}
