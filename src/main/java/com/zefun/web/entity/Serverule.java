package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class Serverule {
	/** 服务标识 */
	private Integer server;

	/** 奖惩项目 */
	private String serverName;

	/** 评分(1-5星) */
	private Integer grade;

	/** 奖惩类型（罚钱、奖励） */
	private String type;

	/** 奖惩金额 */
	private BigDecimal money;

	/** 操作时间 */
	private String operateTime;

	/** 最后操作人 */
	private Integer operateId;

	/** @param server	服务标识 */
	public void setServer(Integer server){
		this.server = server;
	}

	/** @return	服务标识 */
	public Integer getServer(){
		return server;
	}

	/** @param serverName	奖惩项目 */
	public void setServerName(String serverName){
		this.serverName = serverName;
	}

	/** @return	奖惩项目 */
	public String getServerName(){
		return serverName;
	}

	/** @param grade	评分(1-5星) */
	public void setGrade(Integer grade){
		this.grade = grade;
	}

	/** @return	评分(1-5星) */
	public Integer getGrade(){
		return grade;
	}

	/** @param type	奖惩类型（罚钱、奖励） */
	public void setType(String type){
		this.type = type;
	}

	/** @return	奖惩类型（罚钱、奖励） */
	public String getType(){
		return type;
	}

	/** @param money	奖惩金额 */
	public void setMoney(BigDecimal money){
		this.money = money;
	}

	/** @return	奖惩金额 */
	public BigDecimal getMoney(){
		return money;
	}

	/** @param operateTime	操作时间 */
	public void setOperateTime(String operateTime){
		this.operateTime = operateTime;
	}

	/** @return	操作时间 */
	public String getOperateTime(){
		return operateTime;
	}

	/** @param operateId	最后操作人 */
	public void setOperateId(Integer operateId){
		this.operateId = operateId;
	}

	/** @return	最后操作人 */
	public Integer getOperateId(){
		return operateId;
	}

}