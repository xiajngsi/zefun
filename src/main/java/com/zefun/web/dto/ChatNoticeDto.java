package com.zefun.web.dto;

/**
 * 消息通知传输对象
* @author 张进军
* @date Nov 26, 2015 2:41:45 PM 
*/
public class ChatNoticeDto {

    /**
     * 功能ID：1、聊天，2、PC通知，3、APP通知
     */
    private Integer fid;
    
    /**消息接受者标识*/
    private String toUser;
    
    /**消息发送者标识*/
    private String fromUser;
    
    /**消息体*/
    private ChatDataDto data;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public ChatDataDto getData() {
        return data;
    }

    public void setData(ChatDataDto data) {
        this.data = data;
    }
    
}
