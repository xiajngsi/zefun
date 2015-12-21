package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.AgentAccount;

/**
 * 代理账户操作类
* @author 张进军
* @date Nov 25, 2015 5:52:55 PM
 */
public interface AgentAccountMapper {


    /**
     * 新增代理账户
    * @author 张进军
    * @date Nov 25, 2015 5:53:22 PM
    * @param record 代理账户信息
    * @return   0:失败，1:成功
     */
    int insert(AgentAccount record);


    /**
     * 根据代理标识查询代理账户信息
    * @author 张进军
    * @date Nov 25, 2015 5:53:43 PM
    * @param agentId    代理标识
    * @return   代理账户信息
     */
    AgentAccount selectByPrimaryKey(Integer agentId);


    /**
     * 修改代理账户信息
    * @author 张进军
    * @date Nov 25, 2015 5:54:06 PM
    * @param record 代理账户信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(AgentAccount record);

    /**
     * 根据多个渠道商的id查询渠道商账号
     * @author gebing
     * @date 2015年12月4日
     * @param agentIds 多个渠道商id
     * @return 多个渠道商账号
     */
    List<AgentAccount> selectAccountByAgentIds(List<Integer> agentIds);
}
