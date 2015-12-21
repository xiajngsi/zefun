package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.AgentInfo;

/**
 * 代理信息操作类
* @author 张进军
* @date Nov 25, 2015 5:55:11 PM
 */
public interface AgentInfoMapper {


    /**
     * 新增代理
    * @author 张进军
    * @date Nov 25, 2015 5:55:27 PM
    * @param record 代理信息
    * @return   0:失败，1:成功
     */
    int insert(AgentInfo record);


    /**
     * 根据代理标识查询代理信息
    * @author 张进军
    * @date Nov 25, 2015 5:55:43 PM
    * @param agentId    代理标识
    * @return   代理信息
     */
    AgentInfo selectByPrimaryKey(Integer agentId);


    /**
     * 修改代理信息
    * @author 张进军
    * @date Nov 25, 2015 5:56:08 PM
    * @param record 代理信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(AgentInfo record);

    /**
     * 根据省份和城市查询渠道信息
     * @author gebing
     * @date 2015年12月4日
     * @param region 省份
     * @return 渠道信息
     */
    AgentInfo selectByRegion(Map<String, String> region);

    /**
     * 根据多个渠道商的id查询渠道信息
     * @author gebing
     * @date 2015年12月4日
     * @param agentIds 多个渠道商的id
     * @return 多个渠道商信息
     */
    List<AgentInfo> selectByAgentIds(List<Integer> agentIds);
}
