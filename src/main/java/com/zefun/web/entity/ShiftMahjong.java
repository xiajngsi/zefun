package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ShiftMahjong {
	/** 轮牌信息标识 */
	private Integer shiftMahjongId;

	/** 轮牌名称 */
	private String shiftMahjongName;

	/** 店铺标识 */
	private Integer storeId;

	/** 部门标识*/
	private Integer deptId;
	
	/** 上牌规则（1：考勤轮牌、2：持续轮牌） */
	private Integer shiftMahjongUp;

	/** 轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
	private Integer shiftMahjongRule;
	
	/** 轮牌性质(1：助理轮牌、2：技师轮牌)*/
	private Integer nature;

	/** 轮牌指定人数 */
	private Integer appointNumber;

	/** 创建时间 */
	private String createTime;

	/** 操作人标识 */
	private Integer operatorId;

	
	
	public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    /** @param shiftMahjongId	轮牌信息标识 */
	public void setShiftMahjongId(Integer shiftMahjongId){
		this.shiftMahjongId = shiftMahjongId;
	}

	/** @return	轮牌信息标识 */
	public Integer getShiftMahjongId(){
		return shiftMahjongId;
	}

	/** @param shiftMahjongName	轮牌名称 */
	public void setShiftMahjongName(String shiftMahjongName){
		this.shiftMahjongName = shiftMahjongName;
	}

	/** @return	轮牌名称 */
	public String getShiftMahjongName(){
		return shiftMahjongName;
	}

	/** @param storeId	店铺标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	店铺标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param shiftMahjongUp	上牌规则（1：考勤轮牌、2：持续轮牌） */
	public void setShiftMahjongUp(Integer shiftMahjongUp){
		this.shiftMahjongUp = shiftMahjongUp;
	}

	/** @return	上牌规则（1：考勤轮牌、2：持续轮牌） */
	public Integer getShiftMahjongUp(){
		return shiftMahjongUp;
	}

	/** @param shiftMahjongRule	轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
	public void setShiftMahjongRule(Integer shiftMahjongRule){
		this.shiftMahjongRule = shiftMahjongRule;
	}

	/** @return	轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
	public Integer getShiftMahjongRule(){
		return shiftMahjongRule;
	}

	/** @param appointNumber	轮牌指定人数 */
	public void setAppointNumber(Integer appointNumber){
		this.appointNumber = appointNumber;
	}

	/** @return	轮牌指定人数 */
	public Integer getAppointNumber(){
		return appointNumber;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** @param operatorId	操作人标识 */
	public void setOperatorId(Integer operatorId){
		this.operatorId = operatorId;
	}

	/** @return	操作人标识 */
	public Integer getOperatorId(){
		return operatorId;
	}

}