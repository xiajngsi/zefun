package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 商品会员折扣表
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class GoodsDiscount {
	/** 折扣标识 */
	private Integer discountId;

	/** 商品标识 */
	private Integer goodsId;

	/** 会员卡标识 */
	private Integer levelId;
	
	/** 折扣比例 */
	private Integer discountProportion;

	/** 会员门店价*/
	private BigDecimal discountAmount;
	
	/** 在线预约价格 */
    private BigDecimal onlineAppointmentPrice;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param discountId	折扣标识 */
	public void setDiscountId(Integer discountId){
		this.discountId = discountId;
	}

	/** @return	折扣标识 */
	public Integer getDiscountId(){
		return discountId;
	}

	/** @param goodsId	商品标识 */
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}

	/** @return	商品标识 */
	public Integer getGoodsId(){
		return goodsId;
	}

	/** @param levelId	会员卡标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	会员卡标识 */
	public Integer getLevelId(){
		return levelId;
	}
	
    public Integer getDiscountProportion() {
        return discountProportion;
    }

    public void setDiscountProportion(Integer discountProportion) {
        this.discountProportion = discountProportion;
    }

    /** @param discountAmount 会员门店价 */
	public void setDiscountAmount(BigDecimal discountAmount){
		this.discountAmount = discountAmount;
	}

	/** @return	会员门店价 */
	public BigDecimal getDiscountAmount(){
		return discountAmount;
	}
	
	/** @return 在线预约价格 */
    public BigDecimal getOnlineAppointmentPrice() {
        return onlineAppointmentPrice;
    }

    /** @param onlineAppointmentPrice 在线预约价格 */
    public void setOnlineAppointmentPrice(BigDecimal onlineAppointmentPrice) {
        this.onlineAppointmentPrice = onlineAppointmentPrice;
    }

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

}