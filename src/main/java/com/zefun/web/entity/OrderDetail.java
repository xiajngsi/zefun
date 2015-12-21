package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年11月09日 PM 22:54:42
 */
public class OrderDetail {
    /** 明细标识 */
    private Integer detailId;

    /** 明细编号 */
    private String detailCode;

    /** 订单标识 */
    private Integer orderId;

    /** 订单类型(1:项目,2:商品,3:套餐,4、开卡充值升级) */
    private Integer orderType;

    /** 是否预约(0：否，1：是) */
    private Integer isAppoint;

    /** 预约优惠金额 */
    private BigDecimal appointOff;

    /** 是否指定(0：否，1：是) */
    private Integer isAssign;

    /** 项目标识 */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目价格 */
    private BigDecimal projectPrice;

    /** 项目数量 */
    private Integer projectCount;

    /** 项目图片 */
    private String projectImage;

    /** 折扣价格 */
    private BigDecimal discountAmount;

    /** 项目实际金额 */
    private BigDecimal realPrice;
    
    /** 签单金额*/
    private String freeAmount;

    /** 折扣类型(1:套餐，2:优惠券，3:礼金) */
    private Integer offType;

    /** 套餐标识 */
    private Integer comboId;

    /** 优惠券标识 */
    private Integer couponId;

    /** 礼金抵扣 */
    private BigDecimal giftAmount;

    /** 服务时长 */
    private Integer serviceLength;

    /** 门店标识 */
    private Integer storeId;

    /** 订单状态(1：等待中、2：服务中、3：已结束) */
    private Integer orderStatus;
    
    /** 签单备注*/
    private String orderRemark;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    
    
    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    

    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    /** @param detailId 明细标识 */
    public void setDetailId(Integer detailId){
        this.detailId = detailId;
    }

    /** @return 明细标识 */
    public Integer getDetailId(){
        return detailId;
    }

    /** @param detailCode   明细编号 */
    public void setDetailCode(String detailCode){
        this.detailCode = detailCode;
    }

    /** @return 明细编号 */
    public String getDetailCode(){
        return detailCode;
    }

    /** @param orderId  订单标识 */
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /** @return 订单标识 */
    public Integer getOrderId(){
        return orderId;
    }

    /** @param orderType    订单类型(1:项目,2:商品,3:套餐,4、开卡充值升级) */
    public void setOrderType(Integer orderType){
        this.orderType = orderType;
    }

    /** @return 订单类型(1:项目,2:商品,3:套餐,4、开卡充值升级) */
    public Integer getOrderType(){
        return orderType;
    }

    /** @param isAppoint    是否预约(0：否，1：是) */
    public void setIsAppoint(Integer isAppoint){
        this.isAppoint = isAppoint;
    }

    /** @return 是否预约(0：否，1：是) */
    public Integer getIsAppoint(){
        return isAppoint;
    }

    

    public BigDecimal getAppointOff() {
        return appointOff;
    }

    public void setAppointOff(BigDecimal appointOff) {
        this.appointOff = appointOff;
    }

    /** @param isAssign 是否指定(0：否，1：是) */
    public void setIsAssign(Integer isAssign){
        this.isAssign = isAssign;
    }

    /** @return 是否指定(0：否，1：是) */
    public Integer getIsAssign(){
        return isAssign;
    }

    /** @param projectId    项目标识 */
    public void setProjectId(Integer projectId){
        this.projectId = projectId;
    }

    /** @return 项目标识 */
    public Integer getProjectId(){
        return projectId;
    }

    /** @param projectName  项目名称 */
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    /** @return 项目名称 */
    public String getProjectName(){
        return projectName;
    }

    /** @param projectPrice 项目价格 */
    public void setProjectPrice(BigDecimal projectPrice){
        this.projectPrice = projectPrice;
    }

    /** @return 项目价格 */
    public BigDecimal getProjectPrice(){
        return projectPrice;
    }

    /** @param projectCount 项目数量 */
    public void setProjectCount(Integer projectCount){
        this.projectCount = projectCount;
    }

    /** @return 项目数量 */
    public Integer getProjectCount(){
        return projectCount;
    }

    /** @param projectImage 项目图片 */
    public void setProjectImage(String projectImage){
        this.projectImage = projectImage;
    }

    /** @return 项目图片 */
    public String getProjectImage(){
        return projectImage;
    }

    /** @param discountAmount   折扣价格 */
    public void setDiscountAmount(BigDecimal discountAmount){
        this.discountAmount = discountAmount;
    }

    /** @return 折扣价格 */
    public BigDecimal getDiscountAmount(){
        return discountAmount;
    }

    /** @param realPrice    项目实际金额 */
    public void setRealPrice(BigDecimal realPrice){
        this.realPrice = realPrice;
    }

    /** @return 项目实际金额 */
    public BigDecimal getRealPrice(){
        return realPrice;
    }

    /** @param offType  折扣类型(1:套餐，2:优惠券，3:礼金) */
    public void setOffType(Integer offType){
        this.offType = offType;
    }

    /** @return 折扣类型(1:套餐，2:优惠券，3:礼金) */
    public Integer getOffType(){
        return offType;
    }

    /** @param comboId  套餐标识 */
    public void setComboId(Integer comboId){
        this.comboId = comboId;
    }

    /** @return 套餐标识 */
    public Integer getComboId(){
        return comboId;
    }

    /** @param couponId 优惠券标识 */
    public void setCouponId(Integer couponId){
        this.couponId = couponId;
    }

    /** @return 优惠券标识 */
    public Integer getCouponId(){
        return couponId;
    }

    /** @param giftAmount   礼金抵扣 */
    public void setGiftAmount(BigDecimal giftAmount){
        this.giftAmount = giftAmount;
    }

    /** @return 礼金抵扣 */
    public BigDecimal getGiftAmount(){
        return giftAmount;
    }

    /** @param serviceLength    服务时长 */
    public void setServiceLength(Integer serviceLength){
        this.serviceLength = serviceLength;
    }

    /** @return 服务时长 */
    public Integer getServiceLength(){
        return serviceLength;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param orderStatus  订单状态(1：等待中、2：服务中、3：已结束) */
    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }

    /** @return 订单状态(1：等待中、2：服务中、3：已结束) */
    public Integer getOrderStatus(){
        return orderStatus;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted(){
        return isDeleted;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }

}