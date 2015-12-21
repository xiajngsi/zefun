package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ShiftInfo {
	/** 班次标识 */
	private Integer shifId;

	/** 门店标识 */
	private Integer storeId;
	
	/** 部门标识 */
    private Integer deptId;

	/** 班次名称 */
	private String shifName;

	/** 开始时间 */
	private String startTime;

	/** 结束时间 */
	private String endTime;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	
	public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /** @param shifId	班次标识 */
	public void setShifId(Integer shifId){
		this.shifId = shifId;
	}

	/** @return	班次标识 */
	public Integer getShifId(){
		return shifId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}



	public String getShifName() {
        return shifName;
    }

    public void setShifName(String shifName) {
        this.shifName = shifName;
    }

    /** @param startTime	开始时间 */
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	/** @return	开始时间 */
	public String getStartTime(){
		return startTime;
	}

	/** @param endTime	结束时间 */
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	/** @return	结束时间 */
	public String getEndTime(){
		return endTime;
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