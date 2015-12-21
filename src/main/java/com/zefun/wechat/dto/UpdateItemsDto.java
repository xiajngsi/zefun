package com.zefun.wechat.dto;

/**
 * 修改图文消息DTO
* @author 高国藩
* @date 2015年8月31日 下午3:15:26
 */
public class UpdateItemsDto {
    /**图文消息主键*/
    private String replyId;
    private String media_id;
    private String index;
    private UpdateArticleDto articles;
    
    public UpdateArticleDto getArticles() {
        return articles;
    }
    public void setArticles(UpdateArticleDto articles) {
        this.articles = articles;
    }
    public String getMedia_id() {
        return media_id;
    }
    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

    public String getReplyId() {
        return replyId;
    }
    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }
    /**
     * 午餐
    * @author 高国藩
    * @date 2015年8月31日 下午7:39:16
     */
    public UpdateItemsDto() {
        // TODO Auto-generated constructor stub
    }
    /**
     * 修改图文消息构造
    * @author 高国藩
    * @date 2015年8月31日 下午7:51:44
    * @param media_id a
    * @param index a
    * @param articles a
     */
    public UpdateItemsDto(String media_id, String index,
            UpdateArticleDto articles) {
        super();
        this.media_id = media_id;
        this.index = index;
        this.articles = articles;
    }
    

}
