package com.zefun.wechat.dto;

/**
 * 发送图文消息,图文消息列表展示,图文消息发送状态统计
* @author 高国藩
* @date 2015年9月7日 下午5:09:15
 */
public class ItemStatusDto {

    /**图文消息ID*/
    private String mediaId;
    /**首个标题*/
    private String title;
    /**创建时间*/
    private String createTime;
    /**图文消息发送状态*/
    private String msgStatus;
    /**成功接收人数*/
    private String successCount;
    /**失败接收人数*/
    private String errorCount;
    /**已经发送对象*/
    private String hasAccess;
    /**已经阅读人数*/
    private String hasRead;
    /**图文热度*/
    private Integer hotCount;
    /**门店信息*/
    private String storeId;
    
    
    public Integer getHotCount() {
        return hotCount;
    }
    public void setHotCount(Integer hotCount) {
        this.hotCount = hotCount;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getMsgStatus() {
        return msgStatus;
    }
    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
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
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getSuccessCount() {
        return successCount;
    }
    public void setSuccessCount(String successCount) {
        this.successCount = successCount;
    }
    public String getErrorCount() {
        return errorCount;
    }
    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount;
    }
    public String getHasAccess() {
        return hasAccess;
    }
    public void setHasAccess(String hasAccess) {
        this.hasAccess = hasAccess;
    }
    public String getHasRead() {
        return hasRead;
    }
    public void setHasRead(String hasRead) {
        this.hasRead = hasRead;
    }
    
}
