package com.zefun.web.entity;

/**
 * 图文消息状态统计
* @author 高国藩
* @date 2015年9月7日 下午4:02:59
 */
public class ItemCensus {
    /**统计ID*/
    private Integer censusId;
    /**图文消息ID*/
    private String mediaId;
    /**图文发送微信返回ID*/
    private String msgId;
    /**图文发送状态*/
    private String msgStatus;
    /**已发送对象群组*/
    private String hasGroup;
    /**发送成功人数*/
    private String sentCount;
    /**发送失败人数*/
    private String errorCount;
    /**图文被阅读次数*/
    private String hasRead;
    /**发送时间*/
    private String msgTime;
    /**门店信息*/
    private Integer storeId;
    
    

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public Integer getCensusId() {
        return censusId;
    }

    public void setCensusId(Integer censusId) {
        this.censusId = censusId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus == null ? null : msgStatus.trim();
    }

    public String getHasGroup() {
        return hasGroup;
    }

    public void setHasGroup(String hasGroup) {
        this.hasGroup = hasGroup == null ? null : hasGroup.trim();
    }

    public String getSentCount() {
        return sentCount;
    }

    public void setSentCount(String sentCount) {
        this.sentCount = sentCount == null ? null : sentCount.trim();
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount == null ? null : errorCount.trim();
    }

    public String getHasRead() {
        return hasRead;
    }

    public void setHasRead(String hasRead) {
        this.hasRead = hasRead == null ? null : hasRead.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    
    /**
     * 无惨
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:01
     */
    public ItemCensus() {
        // TODO Auto-generated constructor stub
    }
}