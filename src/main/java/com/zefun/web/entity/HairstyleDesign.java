package com.zefun.web.entity;
/**
 * 发型设计表
* @author 洪秋霞
* @date 2015年9月29日 下午4:51:35
 */
public class HairstyleDesign {
    /** 发型设置标识 */
    private Integer hairstyleId;

    /** 发型类别标识 */
    private Integer hairstyleCategoryId;

    /** 门店标识 */
    private Integer storeId;

    /** 封面 */
    private String hairstyleCover;

    /** 名称 */
    private String hairstyleTitle;

    /** 描述 */
    private String hairstyleContent;

    /** 头发 */
    private String hair;
    
    /** 发质 */
    private String hairQuality;

    /** 样式 */
    private String style;

    /** 场景 */
    private String scenario;

    /** 附属图片 */
    private String affiliatedImg;

    /** 发型相关的产品 */
    private String relatedProduct;

    /** 发型相关的项目 */
    private String relatedProject;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    public Integer getHairstyleId() {
        return hairstyleId;
    }

    public void setHairstyleId(Integer hairstyleId) {
        this.hairstyleId = hairstyleId;
    }

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

    public String getHairstyleCover() {
        return hairstyleCover;
    }

    public void setHairstyleCover(String hairstyleCover) {
        this.hairstyleCover = hairstyleCover == null ? null : hairstyleCover.trim();
    }

    public String getHairstyleTitle() {
        return hairstyleTitle;
    }

    public void setHairstyleTitle(String hairstyleTitle) {
        this.hairstyleTitle = hairstyleTitle == null ? null : hairstyleTitle.trim();
    }

    public String getHairstyleContent() {
        return hairstyleContent;
    }

    public void setHairstyleContent(String hairstyleContent) {
        this.hairstyleContent = hairstyleContent == null ? null : hairstyleContent.trim();
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair == null ? null : hair.trim();
    }

    public String getHairQuality() {
        return hairQuality;
    }

    public void setHairQuality(String hairQuality) {
        this.hairQuality = hairQuality == null ? null : hairQuality.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario == null ? null : scenario.trim();
    }

    public String getAffiliatedImg() {
        return affiliatedImg;
    }

    public void setAffiliatedImg(String affiliatedImg) {
        this.affiliatedImg = affiliatedImg == null ? null : affiliatedImg.trim();
    }

    public String getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(String relatedProduct) {
        this.relatedProduct = relatedProduct == null ? null : relatedProduct.trim();
    }

    public String getRelatedProject() {
        return relatedProject;
    }

    public void setRelatedProject(String relatedProject) {
        this.relatedProject = relatedProject == null ? null : relatedProject.trim();
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