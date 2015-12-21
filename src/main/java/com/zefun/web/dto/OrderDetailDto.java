package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务明细
* @author 张进军
* @date 2015年9月18日 上午10:33:53
 */
public class OrderDetailDto {

    /** 明细标识 */
    private Integer detailId;
    
    /** 明细编号*/
    private String detailCode;

    /** 订单标识 */
    private Integer orderId;

    /** 订单类型(1:项目,2:商品,3:套餐) */
    private Integer orderType;

    /** 是否预约(1:是,2:否) */
    private Integer isAppoint;

    /** 是否指定(1:是,2:否) */
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

    /** 项目实际金额 */
    private BigDecimal realPrice;

    /** 折扣价格 */
    private BigDecimal discountAmount;
    
    /** 折扣类型(1:套餐，2:优惠券，3:礼金)*/
    private Integer offType;
    
    /** 礼金抵扣*/
    private BigDecimal giftAmount;
    
    /** 签单金额*/
    private String freeAmount;

    /** 套餐标识 */
    private Integer comboId;

    /** 优惠券标识 */
    private Integer couponId;

    /** 服务时长 */
    private Integer serviceLength;

    /** 订单状态(1：等待中、2：服务中、3：已结束) */
    private Integer orderStatus;
    
    /** 签单备注*/
    private String orderRemark;

    /** 修改时间 */
    private String updateTime;
    
    /** 优惠信息*/
    private String privilegeInfo;
    
    /** 优惠金额*/
    private BigDecimal privilegeMoney;
    
    /** 步骤集合 */
    private List<OrderDetailStepDto> stepList;


    
    
    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public BigDecimal getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
    }

    

    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getOffType() {
        return offType;
    }

    public void setOffType(Integer offType) {
        this.offType = offType;
    }

    public String getPrivilegeInfo() {
        return privilegeInfo;
    }

    public void setPrivilegeInfo(String privilegeInfo) {
        this.privilegeInfo = privilegeInfo;
    }

    public BigDecimal getPrivilegeMoney() {
        return privilegeMoney;
    }

    public void setPrivilegeMoney(BigDecimal privilegeMoney) {
        this.privilegeMoney = privilegeMoney;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    /** @param detailId 明细标识 */
    public void setDetailId(Integer detailId){
        this.detailId = detailId;
    }

    /** @return 明细标识 */
    public Integer getDetailId(){
        return detailId;
    }

    /** @param orderId  订单标识 */
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /** @return 订单标识 */
    public Integer getOrderId(){
        return orderId;
    }

    /** @param orderType    订单类型(1:项目,2:商品,3:套餐) */
    public void setOrderType(Integer orderType){
        this.orderType = orderType;
    }

    /** @return 订单类型(1:项目,2:商品,3:套餐) */
    public Integer getOrderType(){
        return orderType;
    }

    /** @param isAppoint    是否预约(1:是,2:否) */
    public void setIsAppoint(Integer isAppoint){
        this.isAppoint = isAppoint;
    }

    /** @return 是否预约(1:是,2:否) */
    public Integer getIsAppoint(){
        return isAppoint;
    }

    /** @param isAssign 是否指定(1:是,2:否) */
    public void setIsAssign(Integer isAssign){
        this.isAssign = isAssign;
    }

    /** @return 是否指定(1:是,2:否) */
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

    /** @param realPrice    项目实际金额 */
    public void setRealPrice(BigDecimal realPrice){
        this.realPrice = realPrice;
    }

    /** @return 项目实际金额 */
    public BigDecimal getRealPrice(){
        return realPrice;
    }

    /** @param discountAmount   折扣价格 */
    public void setDiscountAmount(BigDecimal discountAmount){
        this.discountAmount = discountAmount;
    }

    /** @return 折扣价格 */
    public BigDecimal getDiscountAmount(){
        return discountAmount;
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

    

    public Integer getServiceLength() {
        return serviceLength;
    }

    public void setServiceLength(Integer serviceLength) {
        this.serviceLength = serviceLength;
    }

    /** @param orderStatus  订单状态(1：未评价、2：已评价) */
    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }

    /** @return 订单状态(1：未评价、2：已评价) */
    public Integer getOrderStatus(){
        return orderStatus;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    public List<OrderDetailStepDto> getStepList() {
        return stepList;
    }

    public void setStepList(List<OrderDetailStepDto> stepList) {
        this.stepList = stepList;
    }
}
