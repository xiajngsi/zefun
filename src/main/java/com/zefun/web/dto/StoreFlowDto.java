package com.zefun.web.dto;

/**
 * 开支记账DTO
* @author 王大爷
* @date 2015年8月11日 上午11:20:27
 */
public class StoreFlowDto {
    
    /** 门店标识ID*/
	private Integer storeId;
	
	/** 开始时间*/
	private Integer beginTime;
	
	/** 结束时间*/
	private Integer endTime;
	
	/** 流水类型*/
	private Integer flowType;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}
	
	
	
}
