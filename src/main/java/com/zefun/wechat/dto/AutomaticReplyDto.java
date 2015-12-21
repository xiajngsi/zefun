package com.zefun.wechat.dto;

/**
 * 
* @author 高国藩
* @date 2015年11月16日 下午2:52:44
 */
public class AutomaticReplyDto {
    /** 消息ID */
    private Integer replyId;

    /** 图文消息MediaId */
    private String mediaId;

    /** 图文消息的标题 */
    private String title;
    
    /** 作者 */
    private String author;

    /** 图文页的URL */
    private String url;

    /** 图文消息的描述 */
    private String description;

    /** 图片链接 */
    private String picUrl;

    /** 七牛图片路径 */
    private String qiniuImg;
    
    /**永久图片媒体ID*/
    private String thumbMediaId;
    
    /**图文消息textarea*/
    private String content;
    
    /**原文地址*/
    private String contentSourceUrl;
    
    /** 创建时间 */
    private String createTime;

    /** 门店ID */
    private Integer storeId;
    
    /** 复制的图文媒体ID*/
    private String fatherMediaId;
    
    /**图文热度,门店使用*/
    private Long zcount;

    /**点赞人数,门店使用*/
    private Long praise;

    /**是否点赞,门店使用*/
    private Integer isPraise;
    
    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public Long getPraise() {
        return praise;
    }

    public void setPraise(Long praise) {
        this.praise = praise;
    }

    public Long getZcount() {
        return zcount;
    }

    public void setZcount(Long zcount) {
        this.zcount = zcount;
    }

    public String getFatherMediaId() {
        return fatherMediaId;
    }

    public void setFatherMediaId(String fatherMediaId) {
        this.fatherMediaId = fatherMediaId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    

    public String getQiniuImg() {
        return qiniuImg;
    }

    public void setQiniuImg(String qiniuImg) {
        this.qiniuImg = qiniuImg;
    }
    
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    /**
     * 
    * @author 高国藩
    * @date 2015年11月16日 下午3:00:07
     */
    public AutomaticReplyDto() {
        // TODO Auto-generated constructor stub
    }
}
