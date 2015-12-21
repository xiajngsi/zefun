package com.zefun.web.dto;

/**
 * 员工奖惩记录dto
 * 
 * @author lzc
 *
 */
public class EmployeeRewardDto {

	/** id */
	private Integer rewardId;

	/** 员工id */
	private Integer employeeId;

	/** 类型：1，考勤；2，服务； */
	private String type;

	/** 是否奖励：0，否；1，是； */
	private String isReward;

	/** 金额 */
	private Double number;

	/** 修改人 */
	private Integer modifyer;

	/** 修改时间 */
	private String modifytime;

	/** 原因 */
	private String reasons;

	/** 总数 */
	private Integer count;

	/** 汇总金额 */
	private Double money;

	/** 员工姓名 */
	private String employeeName;

	/** 员工编号 */
	private Integer employeeCode;

	/** 门店标识 */
	private Integer storeId;

	/*
	 * 以下是逻辑字段
	 */
	/** 迟到总数 */
	private int countOfLate;

	/** 早退总数 */
	private int countOfEarlyLeave;

	/** 请假总数 */
	private int countOfHoliday;

	/** 旷工总数 */
	private int countOfAbsenteeism;

	/** 全勤总数 */
	private int countOfAttendance;

	/** 小过总数 */
	private int countOfSmallMistake;

	/** 大过总数 */
	private int countOfSeriousMistake;

	/** 警告总数 */
	private int countOfWarning;

	/** 好评总数 */
	private int countOfFavourable;

	/** 差评总数 */
	private int countOfBadpost;

	/** 投诉总数 */
	private int countOfComplaint;

	/** 奖励金额 */
	private double moneyOfReward;

	/** 惩罚金额 */
	private double moneyOfPunishment;

	public Integer getRewardId() {
		return rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsReward() {
		return isReward;
	}

	public void setIsReward(String isReward) {
		this.isReward = isReward;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public int getCountOfLate() {
		return countOfLate;
	}

	public void setCountOfLate(int countOfLate) {
		this.countOfLate = countOfLate;
	}

	public int getCountOfEarlyLeave() {
		return countOfEarlyLeave;
	}

	public void setCountOfEarlyLeave(int countOfEarlyLeave) {
		this.countOfEarlyLeave = countOfEarlyLeave;
	}

	public int getCountOfHoliday() {
		return countOfHoliday;
	}

	public void setCountOfHoliday(int countOfHoliday) {
		this.countOfHoliday = countOfHoliday;
	}

	public int getCountOfAbsenteeism() {
		return countOfAbsenteeism;
	}

	public void setCountOfAbsenteeism(int countOfAbsenteeism) {
		this.countOfAbsenteeism = countOfAbsenteeism;
	}

	public int getCountOfAttendance() {
		return countOfAttendance;
	}

	public void setCountOfAttendance(int countOfAttendance) {
		this.countOfAttendance = countOfAttendance;
	}

	public int getCountOfSmallMistake() {
		return countOfSmallMistake;
	}

	public void setCountOfSmallMistake(int countOfSmallMistake) {
		this.countOfSmallMistake = countOfSmallMistake;
	}

	public int getCountOfSeriousMistake() {
		return countOfSeriousMistake;
	}

	public void setCountOfSeriousMistake(int countOfSeriousMistake) {
		this.countOfSeriousMistake = countOfSeriousMistake;
	}

	public int getCountOfWarning() {
		return countOfWarning;
	}

	public void setCountOfWarning(int countOfWarning) {
		this.countOfWarning = countOfWarning;
	}

	public int getCountOfFavourable() {
		return countOfFavourable;
	}

	public void setCountOfFavourable(int countOfFavourable) {
		this.countOfFavourable = countOfFavourable;
	}

	public int getCountOfBadpost() {
		return countOfBadpost;
	}

	public void setCountOfBadpost(int countOfBadpost) {
		this.countOfBadpost = countOfBadpost;
	}

	public int getCountOfComplaint() {
		return countOfComplaint;
	}

	public void setCountOfComplaint(int countOfComplaint) {
		this.countOfComplaint = countOfComplaint;
	}

	public double getMoneyOfReward() {
		return moneyOfReward;
	}

	public void setMoneyOfReward(double moneyOfReward) {
		this.moneyOfReward = moneyOfReward;
	}

	public double getMoneyOfPunishment() {
		return moneyOfPunishment;
	}

	public void setMoneyOfPunishment(double moneyOfPunishment) {
		this.moneyOfPunishment = moneyOfPunishment;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
