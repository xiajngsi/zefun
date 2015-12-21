package com.zefun.web.entity;

/**
 * 图片库
* @author 高国藩
* @date 2015年9月2日 上午10:50:30
 */
public class PictureLibrary {
    /**图片信息标示*/
    private Integer pictureId;
    /**图片地址-七牛*/
    private String pictureQiniu;
    /**图片地址-微信*/
    private String pictureWechat;
    /**图片微信thumb_media_id*/
    private String thumbMediaId;
    /**门店信息*/
    private Integer storeId;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureQiniu() {
        return pictureQiniu;
    }

    public void setPictureQiniu(String pictureQiniu) {
        this.pictureQiniu = pictureQiniu == null ? null : pictureQiniu.trim();
    }

    public String getPictureWechat() {
        return pictureWechat;
    }

    public void setPictureWechat(String pictureWechat) {
        this.pictureWechat = pictureWechat == null ? null : pictureWechat.trim();
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    
    /**
     * 无参构造
    * @author 高国藩
    * @date 2015年9月2日 下午3:15:22
     */
    public PictureLibrary() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 有参构造
    * @author 高国藩
    * @date 2015年9月2日 下午3:15:32
    * @param pictureQiniu 见上
    * @param pictureWechat 见上
    * @param thumbMediaId 见上
    * @param storeId 见上
     */
    public PictureLibrary(String pictureQiniu, String pictureWechat,
            String thumbMediaId, Integer storeId) {
        super();
        this.pictureQiniu = pictureQiniu;
        this.pictureWechat = pictureWechat;
        this.thumbMediaId = thumbMediaId;
        this.storeId = storeId;
    }
    
}