package com.zefun.web.entity;
/**
 * 发型类别
* @author 洪秋霞
* @date 2015年9月8日 上午11:49:37
 */
public class HairstyleCategory {
    
    /** 发型类别标识 */
    private Integer hairstyleCategoryId;
    
    /** 门店标识 */
    private Integer storeId;

    /** 发型类别名称 */
    private String hairstyleCategoryName;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    public Integer getHairstyleCategoryId() {
        return hairstyleCategoryId;
    }

    public void setHairstyleCategoryId(Integer hairstyleCategoryId) {
        this.hairstyleCategoryId = hairstyleCategoryId;
    }
    
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getHairstyleCategoryName() {
        return hairstyleCategoryName;
    }

    public void setHairstyleCategoryName(String hairstyleCategoryName) {
        this.hairstyleCategoryName = hairstyleCategoryName == null ? null : hairstyleCategoryName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}