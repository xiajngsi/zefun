package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月25日 PM 17:47:43
 */
public class AgentInfo {
	/** 代理标识 */
	private Integer agentId;

	/** 联系人 */
	private String name;

	/** 联系电话 */
	private String phone;

	/** 公司类型(0:其他，1:连锁机构，2:发品商，3:培训机构) */
	private Integer companyType;

	/** 公司名称 */
	private String companyName;

	/** 所属省份 */
	private String province;

	/** 所属城市 */
	private String city;

	/** 申请时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** @param agentId	代理标识 */
	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	/** @return	代理标识 */
	public Integer getAgentId(){
		return agentId;
	}

	/** @param name	联系人 */
	public void setName(String name){
		this.name = name;
	}

	/** @return	联系人 */
	public String getName(){
		return name;
	}

	/** @param phone	联系电话 */
	public void setPhone(String phone){
		this.phone = phone;
	}

	/** @return	联系电话 */
	public String getPhone(){
		return phone;
	}

	/** @param companyType	公司类型(0:其他，1:连锁机构，2:发品商，3:培训机构) */
	public void setCompanyType(Integer companyType){
		this.companyType = companyType;
	}

	/** @return	公司类型(0:其他，1:连锁机构，2:发品商，3:培训机构) */
	public Integer getCompanyType(){
		return companyType;
	}

	/** @param companyName	公司名称 */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	/** @return	公司名称 */
	public String getCompanyName(){
		return companyName;
	}

	/** @param province	所属省份 */
	public void setProvince(String province){
		this.province = province;
	}

	/** @return	所属省份 */
	public String getProvince(){
		return province;
	}

	/** @param city	所属城市 */
	public void setCity(String city){
		this.city = city;
	}

	/** @return	所属城市 */
	public String getCity(){
		return city;
	}

	/** @param createTime	申请时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	申请时间 */
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

}