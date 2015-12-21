package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月26日 AM 00:09:41
 */
public class StoreSetting {
	/** 门店标识 */
	private Integer storeId;

	/** 礼金清理天数 */
	private Integer giftCleanDays;

	/** 积分清理天数 */
	private Integer integralCleanDays;

	/** 提成是否扣除成本(0:不扣除，1:扣除) */
	private Integer costCommissionType;

	/** 礼金减扣比例 */
	private Integer giftCommissionRate;

	/** 优惠券减扣比例 */
	private Integer couponCommissionRate;

	/** 预约到时提醒(小时) */
	private Integer appointRemindHour;

	/** 播音类型(1:男，0:女) */
	private Integer speechType;

	/** 服务交接是否播音(1:是，0:否) */
	private Integer speechTurnUse;

	/** 初次关注赠送券 */
	private String firstFollowCoupon;

	/** 初次关注赠送礼金 */
	private Integer firstFollowGift;

	/** 会员短信服务费 */
	private Integer smsFee;
	
	/**原服务商*/
	private String lastFacilitator;
	
	/** @return 原服务商 */
	public String getLastFacilitator() {
        return lastFacilitator;
    }
    public void setLastFacilitator(String lastFacilitator) {
        this.lastFacilitator = lastFacilitator;
    }

    /** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param giftCleanDays	礼金清理天数 */
	public void setGiftCleanDays(Integer giftCleanDays){
		this.giftCleanDays = giftCleanDays;
	}

	/** @return	礼金清理天数 */
	public Integer getGiftCleanDays(){
		return giftCleanDays;
	}

	/** @param integralCleanDays	积分清理天数 */
	public void setIntegralCleanDays(Integer integralCleanDays){
		this.integralCleanDays = integralCleanDays;
	}

	/** @return	积分清理天数 */
	public Integer getIntegralCleanDays(){
		return integralCleanDays;
	}

	/** @param costCommissionType	提成是否扣除成本(0:不扣除，1:扣除) */
	public void setCostCommissionType(Integer costCommissionType){
		this.costCommissionType = costCommissionType;
	}

	/** @return	提成是否扣除成本(0:不扣除，1:扣除) */
	public Integer getCostCommissionType(){
		return costCommissionType;
	}

	/** @param giftCommissionRate	礼金减扣比例 */
	public void setGiftCommissionRate(Integer giftCommissionRate){
		this.giftCommissionRate = giftCommissionRate;
	}

	/** @return	礼金减扣比例 */
	public Integer getGiftCommissionRate(){
		return giftCommissionRate;
	}

	/** @param couponCommissionRate	优惠券减扣比例 */
	public void setCouponCommissionRate(Integer couponCommissionRate){
		this.couponCommissionRate = couponCommissionRate;
	}

	/** @return	优惠券减扣比例 */
	public Integer getCouponCommissionRate(){
		return couponCommissionRate;
	}

	/** @param appointRemindHour	预约到时提醒(小时) */
	public void setAppointRemindHour(Integer appointRemindHour){
		this.appointRemindHour = appointRemindHour;
	}

	/** @return	预约到时提醒(小时) */
	public Integer getAppointRemindHour(){
		return appointRemindHour;
	}

	/** @param speechType	播音类型(1:男，0:女) */
	public void setSpeechType(Integer speechType){
		this.speechType = speechType;
	}

	/** @return	播音类型(1:男，0:女) */
	public Integer getSpeechType(){
		return speechType;
	}

	/** @param speechTurnUse	服务交接是否播音(1:是，0:否) */
	public void setSpeechTurnUse(Integer speechTurnUse){
		this.speechTurnUse = speechTurnUse;
	}

	/** @return	服务交接是否播音(1:是，0:否) */
	public Integer getSpeechTurnUse(){
		return speechTurnUse;
	}

	/** @param firstFollowCoupon	初次关注赠送券 */
	public void setFirstFollowCoupon(String firstFollowCoupon){
		this.firstFollowCoupon = firstFollowCoupon;
	}

	/** @return	初次关注赠送券 */
	public String getFirstFollowCoupon(){
		return firstFollowCoupon;
	}

	/** @param firstFollowGift	初次关注赠送礼金 */
	public void setFirstFollowGift(Integer firstFollowGift){
		this.firstFollowGift = firstFollowGift;
	}

	/** @return	初次关注赠送礼金 */
	public Integer getFirstFollowGift(){
		return firstFollowGift;
	}

	/** @param smsFee	会员短信服务费 */
	public void setSmsFee(Integer smsFee){
		this.smsFee = smsFee;
	}

	/** @return	会员短信服务费 */
	public Integer getSmsFee(){
		return smsFee;
	}

}