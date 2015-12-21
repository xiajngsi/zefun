package com.zefun.wechat.dto;

/**
 * 更新图文消息，防止字段冲突问题
* @author 高国藩
* @date 2015年9月1日 上午11:28:08
 */
public class UpdateChangeThumb {
    /** 图片链接 */
    private String picUrl;
    /** 七牛图片路径 */
    private String qiniuImg;
    
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getQiniuImg() {
        return qiniuImg;
    }
    public void setQiniuImg(String qiniuImg) {
        this.qiniuImg = qiniuImg;
    }
    
    /**
     * 无参构造
    * @author 高国藩
    * @date 2015年9月1日 上午11:29:47
     */
    public UpdateChangeThumb() {
        // TODO Auto-generated constructor stub
    }
}
