package com.zefun.web.dto;

/**
 * 消息数据传输对象
* @author 张进军
* @date Nov 26, 2015 2:43:34 PM 
*/
public class ChatDataDto {

    /**
     * 数据类型(聊天)：1、文字，2、图片，3、语音
     * 数据类型(PC通知)：1、新订单，2、新预约，3、新会员，4、服务交接
     */
    private Integer type;

    /**消息内容*/
    private String msg;
    
    /**语音长度(聊天时type为2)*/
    private Integer length;

    /**业务数据包(json)*/
    private String pkg;
    
    /**消息创建时间*/
    private String createTime;
    
    /**消息接收时间*/
    private String receiveTime;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }
    
}
