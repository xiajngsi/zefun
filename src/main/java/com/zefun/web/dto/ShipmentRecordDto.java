package com.zefun.web.dto;

/**
 * 出货记录数据传输dto
* @author 高国藩
* @date 2015年11月14日 上午11:10:19
 */
public class ShipmentRecordDto {

    /**标识*/
    private Integer recordId;
    /**商品ID*/
    private Integer goodsId;
    /**商品Name*/
    private String goodsName;
    /**提取货物人*/
    private Integer employeeId;
    /**提取货物人*/
    private String employeeName;
    /**提取货数量*/
    private Integer shipmentCount;
    /**提取时间*/
    private String shipmentTime;
    /**门店Id*/
    private Integer storeId;
    /**最后操作人员*/
    private Integer operatorId;
    /**最后操作人员姓名*/
    private String operatorName;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(Integer shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    public String getShipmentTime() {
        return shipmentTime;
    }

    public void setShipmentTime(String shipmentTime) {
        this.shipmentTime = shipmentTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
