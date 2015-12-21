package com.zefun.web.entity;

/**
 * 图文消息
* @author 高国藩
* @date 2015年9月2日 上午11:23:32
 */
public class AutomaticReply {
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
	
	/**点赞总数*/
	private Integer praise;
	/**已点赞门店*/
	private String hasPraiseStore;
	/**图文热度*/
	private Integer mediaCount;
	/**已经发送的门店*/
	private String hasSendStore;
	/**下载量*/
	private Integer downloads;

	
	public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getHasSendStore() {
        return hasSendStore;
    }

    public void setHasSendStore(String hasSendStore) {
        this.hasSendStore = hasSendStore;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getHasPraiseStore() {
        return hasPraiseStore;
    }

    public void setHasPraiseStore(String hasPraiseStore) {
        this.hasPraiseStore = hasPraiseStore;
    }

    public Integer getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Integer mediaCount) {
        this.mediaCount = mediaCount;
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
	* @date 2015年8月11日 上午11:57:20
	* @param mediaId mediaId
	* @param title 标题
	* @param author 作者
	* @param url 路径
	* @param description 描述
	* @param picUrl 图片路径
	* @param qiniuImg 七牛图片路径
	* @param createTime 创建时间
	* @param content 图文消息textarea
	* @param thumbMediaId id
	* @param contentSourceUrl 阅读原文
	* @param storeId 门店id
	 */
    public AutomaticReply(String mediaId, String title, String author, 
            String url, String description, String picUrl, String qiniuImg, String thumbMediaId, String createTime, 
            String content, String contentSourceUrl, Integer storeId) {
        super();
        this.mediaId = mediaId;
        this.title = title;
        this.author = author;
        this.url = url;
        this.description = description;
        this.picUrl = picUrl;
        this.qiniuImg = qiniuImg;
        this.thumbMediaId = thumbMediaId;
        this.createTime = createTime;
        this.content = content;
        this.contentSourceUrl = contentSourceUrl;
        this.storeId = storeId;
    }

    /**
     * 
    * @author 原文链接
    * @date 2015年8月11日 上午11:57:20
    * @param replyId 主键
    * @param mediaId mediaId
    * @param title 标题
    * @param author 作者
    * @param description 描述
    * @param picUrl 图片路径
    * @param qiniuImg 七牛图片路径
    * @param content 图文消息textarea
    * @param thumbMediaId id
    * @param storeId 门店id
     */
    public AutomaticReply(Integer replyId, String mediaId, String title, String author, String description, String picUrl, String qiniuImg, 
            String thumbMediaId, String content, Integer storeId) {
        super();
        this.replyId = replyId;
        this.mediaId = mediaId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.picUrl = picUrl;
        this.qiniuImg = qiniuImg;
        this.thumbMediaId = thumbMediaId;
        this.content = content;
        this.storeId = storeId;
    }
    /**
     * 复制图文消息
    * @author 高国藩
    * @date 2015年8月11日 上午11:57:20
    * @param mediaId mediaId
    * @param title 标题
    * @param author 作者
    * @param url 路径
    * @param description 描述
    * @param picUrl 图片路径
    * @param qiniuImg 七牛图片路径
    * @param createTime 创建时间
    * @param content 图文消息textarea
    * @param thumbMediaId id
    * @param contentSourceUrl 阅读原文
    * @param storeId 门店id
    * @param fatherMediaId 复制的图文
     */
    public AutomaticReply(String mediaId, String title, String author, 
            String url, String description, String picUrl, String qiniuImg, String thumbMediaId, String createTime, 
            String content, String contentSourceUrl, Integer storeId, String fatherMediaId) {
        super();
        this.mediaId = mediaId;
        this.title = title;
        this.author = author;
        this.url = url;
        this.description = description;
        this.picUrl = picUrl;
        this.qiniuImg = qiniuImg;
        this.thumbMediaId = thumbMediaId;
        this.createTime = createTime;
        this.content = content;
        this.contentSourceUrl = contentSourceUrl;
        this.storeId = storeId;
        this.fatherMediaId = fatherMediaId;
    }
    /**
     * 无参构造
    * @author 高国藩
    * @date 2015年8月11日 下午6:00:08
     */
    public AutomaticReply() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AutomaticReply [replyId=" + replyId + ", mediaId=" + mediaId
                + ", title=" + title + ", author=" + author + ", description="
                + description + ", picUrl=" + picUrl + ", qiniuImg=" + qiniuImg
                + ", thumbMediaId=" + thumbMediaId + ", content=" + content
                + ", storeId=" + storeId + "]";
    }
	
}