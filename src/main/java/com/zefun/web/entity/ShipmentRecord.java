package com.zefun.web.entity;

/**
 * @author 张进军
 * @date 2015年11月14日 PM 13:42:32
 */
public class ShipmentRecord {
    /** 记录标识 */
    private Integer recordId;

    /** 商品标识 */
    private Integer goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 出货人员标示 */
    private Integer employeeId;

    /** 出货人姓名 */
    private String employeeName;

    /** 出货数量 */
    private Integer shipmentCount;

    /** 出货时间 */
    private String shipmentTime;

    /** 店铺标识 */
    private Integer storeId;

    /** 操作人标识 */
    private Integer operatorId;

    /** @param recordId 记录标识 */
    public void setRecordId(Integer recordId){
        this.recordId = recordId;
    }

    /** @return 记录标识 */
    public Integer getRecordId(){
        return recordId;
    }

    /** @param goodsId  商品标识 */
    public void setGoodsId(Integer goodsId){
        this.goodsId = goodsId;
    }

    /** @return 商品标识 */
    public Integer getGoodsId(){
        return goodsId;
    }

    /** @param goodsName    商品名称 */
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    /** @return 商品名称 */
    public String getGoodsName(){
        return goodsName;
    }

    /** @param employeeId   出货人员标示 */
    public void setEmployeeId(Integer employeeId){
        this.employeeId = employeeId;
    }

    /** @return 出货人员标示 */
    public Integer getEmployeeId(){
        return employeeId;
    }

    /** @param employeeName 出货人姓名 */
    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }

    /** @return 出货人姓名 */
    public String getEmployeeName(){
        return employeeName;
    }

    /** @param shipmentCount    出货数量 */
    public void setShipmentCount(Integer shipmentCount){
        this.shipmentCount = shipmentCount;
    }

    /** @return 出货数量 */
    public Integer getShipmentCount(){
        return shipmentCount;
    }

    /** @param shipmentTime 出货时间 */
    public void setShipmentTime(String shipmentTime){
        this.shipmentTime = shipmentTime;
    }

    /** @return 出货时间 */
    public String getShipmentTime(){
        return shipmentTime;
    }

    /** @param storeId  店铺标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 店铺标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param operatorId   操作人标识 */
    public void setOperatorId(Integer operatorId){
        this.operatorId = operatorId;
    }

    /** @return 操作人标识 */
    public Integer getOperatorId(){
        return operatorId;
    }
}