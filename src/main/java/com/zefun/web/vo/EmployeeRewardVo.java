package com.zefun.web.vo;

/**
 * 员工奖惩vo
 * 
 * @author lzc
 *
 */
public class EmployeeRewardVo {
	
	/** 页码 */
	private Integer pageNo;

	/** 页距 */
	private Integer pageSize;

	/** 门店标识 */
	private Integer storeId;

	/** 时间 */
	private String time;
	
	/** 员工id */
	private Integer employeeId;

	/** 员工姓名 */
	private String employeeName;
	
	/** 奖惩类型(1.迟到,2.早退,3.请假,4.旷工,5.全勤,6.小过,7.大过,8.警告,9.好评,10.差评,11.投诉) */
	private String type;

	/** 员工规则制度(也对应com.zefun.common.enums.EmployeeRuleEnum) */
	private String rule;
	
	/** 原因(手工添加奖惩的原因) */
	private String reasons;
	
	/**
	 * 无参构造
	 */
	public EmployeeRewardVo() {
		super();
	}

	/**
	 * 员工奖惩vo有参构造
	 * @param pageNo  页码
	 * @param pageSize  页距
	 * @param storeId  店铺id
	 * @param time  查询时间
	 * @param employeeId  员工id
	 * @param employeeName  员工姓名
	 * @param type 奖惩类型(1.迟到,2.早退,3.请假,4.旷工,5.全勤,6.小过,7.大过,8.警告,9.好评,10.差评,11.投诉)
	 * @param rule  规则制度(考勤规则:ATTENDANCE, 行为规范:BEHAVIOUR, 服务表现:SERVICE)
	 * @param reasons 原因(手工添加奖惩的原因)
	 */
	public EmployeeRewardVo(Integer pageNo, Integer pageSize, Integer storeId, String time, Integer employeeId, 
			  String employeeName, String type, String rule, String reasons) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.storeId = storeId;
		this.time = time;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.type = type;
		this.rule = rule;
		this.reasons = reasons;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
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

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	
}
