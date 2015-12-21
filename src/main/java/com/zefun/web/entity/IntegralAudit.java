package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class IntegralAudit {
	/** 审核ID */
	private Integer auditId;

	/** 会员ID */
	private Integer memberId;

	/** 积分来源 */
	private String integralSource;

	/** 积分金额 */
	private Integer integralAmount;

	/** 生成时间 */
	private String startTime;

	/** 过期时间 */
	private String stopTime;

	/** 积分状态 */
	private Integer integralStatus;

	/** 可用积分余额 */
	private Integer useIntegralAcount;

	/** 积分总额 */
	private Integer integralAcount;

	/** @param auditId	审核ID */
	public void setAuditId(Integer auditId){
		this.auditId = auditId;
	}

	/** @return	审核ID */
	public Integer getAuditId(){
		return auditId;
	}

	/** @param memberId	会员ID */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员ID */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param integralSource	积分来源 */
	public void setIntegralSource(String integralSource){
		this.integralSource = integralSource;
	}

	/** @return	积分来源 */
	public String getIntegralSource(){
		return integralSource;
	}

	/** @param integralAmount	积分金额 */
	public void setIntegralAmount(Integer integralAmount){
		this.integralAmount = integralAmount;
	}

	/** @return	积分金额 */
	public Integer getIntegralAmount(){
		return integralAmount;
	}

	/** @param startTime	生成时间 */
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	/** @return	生成时间 */
	public String getStartTime(){
		return startTime;
	}

	/** @param stopTime	过期时间 */
	public void setStopTime(String stopTime){
		this.stopTime = stopTime;
	}

	/** @return	过期时间 */
	public String getStopTime(){
		return stopTime;
	}

	/** @param integralStatus	积分状态 */
	public void setIntegralStatus(Integer integralStatus){
		this.integralStatus = integralStatus;
	}

	/** @return	积分状态 */
	public Integer getIntegralStatus(){
		return integralStatus;
	}

	/** @param useIntegralAcount	可用积分余额 */
	public void setUseIntegralAcount(Integer useIntegralAcount){
		this.useIntegralAcount = useIntegralAcount;
	}

	/** @return	可用积分余额 */
	public Integer getUseIntegralAcount(){
		return useIntegralAcount;
	}

	/** @param integralAcount	积分总额 */
	public void setIntegralAcount(Integer integralAcount){
		this.integralAcount = integralAcount;
	}

	/** @return	积分总额 */
	public Integer getIntegralAcount(){
		return integralAcount;
	}

}