package com.zefun.web.dto;


/**
* @author 张进军
* @date Oct 14, 2015 9:03:50 PM 
*/
public class EmployeeBaseDto {
    /** 员工标识 */
    private Integer employeeId;
    
    /** 门店标识 */
    private String storeId;
    
    /** 门店类型(1:单店，2:连锁总店，3:连锁分店) */
    private Integer storeType;

    /** 门店名称 */
    private String storeName;

    /** 级别名称 */
    private String levelName;

    /** 员工编码 */
    private Integer employeeCode;

    /** 姓名 */
    private String name;

    /** 头像 */
    private String headImage;

    /** 性别 */
    private String sex;
    
    /** 服务人数 */
    private Integer servicePeoples;

    /** 服务次数 */
    private Integer serviceCount;

    /** 综合评分 */
    private Float serviceScore;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getServicePeoples() {
        return servicePeoples;
    }

    public void setServicePeoples(Integer servicePeoples) {
        this.servicePeoples = servicePeoples;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public Float getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Float serviceScore) {
        this.serviceScore = serviceScore;
    }

}
