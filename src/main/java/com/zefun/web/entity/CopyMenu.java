package com.zefun.web.entity;

/**
 * 复制菜单信息
* @author 高国藩
* @date 2015年9月20日 下午5:13:38
 */
public class CopyMenu {
    
    /**主键ID*/
    private Integer copyId;
    /**门店ID*/
    private Integer storeId;
    /**是否复制 0:未复制 1:已复制*/
    private Integer copyStatus;

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(Integer copyStatus) {
        this.copyStatus = copyStatus;
    }
    
}