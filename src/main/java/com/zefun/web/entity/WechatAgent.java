package com.zefun.web.entity;

import java.sql.Timestamp;

/**
 * @author 张进军
 * @date 2015年11月25日 PM 17:47:43
 */
public class WechatAgent {
	/** 微信标识 */
	private String openId;

	/** 代理标识 */
	private Integer agentId;

	/** 创建时间 */
	private Timestamp createTime;

	/** @param openId	微信标识 */
	public void setOpenId(String openId){
		this.openId = openId;
	}

	/** @return	微信标识 */
	public String getOpenId(){
		return openId;
	}

	/** @param agentId	代理标识 */
	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	/** @return	代理标识 */
	public Integer getAgentId(){
		return agentId;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public Timestamp getCreateTime(){
		return createTime;
	}

}