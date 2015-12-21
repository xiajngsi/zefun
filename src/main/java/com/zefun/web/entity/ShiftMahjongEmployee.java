package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ShiftMahjongEmployee {
	/** 轮牌员工标识 */
	private Integer shiftMahjongEmployeeId;

	/** 轮牌信息标识 */
	private Integer shiftMahjongId;

	/** 级别标识 */
	private Integer levelId;
	
	/** 是否打卡（0：否、1：是）*/
	private Integer isPunchCard;

	/** 员工标识 */
	private Integer employeesId;

	/** 员工编码 */
	private Integer employeeCode;

	/** 姓名 */
	private String name;

	/** 头像 */
	private String headImage;
	
	/** 员工状态（0：闲、1：忙、2：离开） */
	private Integer state;

	/** 是否指定（0：否、1：是） */
	private Integer isAppoint;

	/** 指定人数 */
	private Integer appointNumber;

	/** 轮牌顺序 */
	private Integer shiftMahjongOrder;

	/** 创建时间 */
	private String createTime;

	/** 操作人标识 */
	private Integer operatorId;

	

    public Integer getIsPunchCard() {
        return isPunchCard;
    }

    public void setIsPunchCard(Integer isPunchCard) {
        this.isPunchCard = isPunchCard;
    }

    /** @param shiftMahjongEmployeeId	轮牌员工标识 */
	public void setShiftMahjongEmployeeId(Integer shiftMahjongEmployeeId){
		this.shiftMahjongEmployeeId = shiftMahjongEmployeeId;
	}

	/** @return	轮牌员工标识 */
	public Integer getShiftMahjongEmployeeId(){
		return shiftMahjongEmployeeId;
	}

	/** @param shiftMahjongId	轮牌信息标识 */
	public void setShiftMahjongId(Integer shiftMahjongId){
		this.shiftMahjongId = shiftMahjongId;
	}

	/** @return	轮牌信息标识 */
	public Integer getShiftMahjongId(){
		return shiftMahjongId;
	}

	/** @param levelId	级别标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	级别标识 */
	public Integer getLevelId(){
		return levelId;
	}

	/** @param employeesId	员工标识 */
	public void setEmployeesId(Integer employeesId){
		this.employeesId = employeesId;
	}

	/** @return	员工标识 */
	public Integer getEmployeesId(){
		return employeesId;
	}

	/** @param employeeCode	员工编码 */
	public void setEmployeeCode(Integer employeeCode){
		this.employeeCode = employeeCode;
	}

	/** @return	员工编码 */
	public Integer getEmployeeCode(){
		return employeeCode;
	}

	/** @param name	姓名 */
	public void setName(String name){
		this.name = name;
	}

	/** @return	姓名 */
	public String getName(){
		return name;
	}

	/** @param headImage	头像 */
	public void setHeadImage(String headImage){
		this.headImage = headImage;
	}

	/** @return	头像 */
	public String getHeadImage(){
		return headImage;
	}

	/** @param state	员工状态（0：工作中、1：空闲中、2：暂时离开、3：离开、4、指定服务） */
	public void setState(Integer state){
		this.state = state;
	}

	/** @return	员工状态（0：闲、1：忙、2：离开） */
	public Integer getState(){
		return state;
	}

	/** @param isAppoint	是否指定（0：否、1：是） */
	public void setIsAppoint(Integer isAppoint){
		this.isAppoint = isAppoint;
	}

	/** @return	是否指定（0：否、1：是） */
	public Integer getIsAppoint(){
		return isAppoint;
	}

	/** @param appointNumber	指定人数 */
	public void setAppointNumber(Integer appointNumber){
		this.appointNumber = appointNumber;
	}

	/** @return	指定人数 */
	public Integer getAppointNumber(){
		return appointNumber;
	}

	/** @param shiftMahjongOrder	轮牌顺序 */
	public void setShiftMahjongOrder(Integer shiftMahjongOrder){
		this.shiftMahjongOrder = shiftMahjongOrder;
	}

	/** @return	轮牌顺序 */
	public Integer getShiftMahjongOrder(){
		return shiftMahjongOrder;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
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