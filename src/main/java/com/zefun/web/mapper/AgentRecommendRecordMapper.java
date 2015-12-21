package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.AgentRecommendRecord;


/**
 * 代理门店推荐信息操作类
* @author 张进军
* @date Nov 25, 2015 5:57:44 PM
 */
public interface AgentRecommendRecordMapper {


    /**
     * 新增推荐
    * @author 张进军
    * @date Nov 25, 2015 5:58:06 PM
    * @param record 推荐信息
    * @return   0:失败，1:成功
     */
    int insert(AgentRecommendRecord record);


    /**
     * 根据代理标识、门店标识查询推荐信息
    * @author 张进军
    * @date Nov 25, 2015 5:58:39 PM
    * @param key    代理标识、门店标识
    * @return   推荐信息
     */
    AgentRecommendRecord selectByPrimaryKey(AgentRecommendRecord key);

    /**
     * 根据多个storeId查询推荐记录
     * @author gebing
     * @date 2015年12月4日
     * @param recommendedIds 多个被推荐者id
     * @return 多条推荐记录
     */
    List<AgentRecommendRecord> selectByRecommendedIds(List<Integer> recommendedIds);

    /**
     * 根据渠道商id获取他自己发展的门店或渠道
     * @author gebing
     * @date 2015年12月4日
     * @param params agentId 渠道商id, type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    List<AgentRecommendRecord> selectSelfRecommendsByType(
            Map<String, Object> params);


    /**
     * 根据渠道商id查询其他渠道推荐给自己的门店活渠道
     * @author gebing
     * @date 2015年12月4日
     * @param params agentId 渠道商id, type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    List<AgentRecommendRecord> selectOtherRecommendsByType(
            Map<String, Object> params);

    /**
     * 根据渠道商id获取属于他的推荐记录, 不论是自己发展的还是别人推荐的
     * @author gebing
     * @date 2015年12月4日
     * @param params agentId 渠道商id, type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    List<AgentRecommendRecord> selectRecommendsByType(Map<String, Object> params);

    /**
     * 根据渠道商id查询自己推荐给别人的推荐记录
     * @author gebing
     * @date 2015年12月4日
     * @param params agentId 渠道商id, type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    List<AgentRecommendRecord> selectMyRecommendsByType(Map<String, Object> params);

    /**
     * 根据渠道商id查询其他渠道推荐给自己的门店活渠道
     * @author gebing
     * @date 2015年12月4日
     * @param params agentId 渠道商id, type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    List<AgentRecommendRecord> selectRecommendsToMeByType(
            Map<String, Object> params);

    /**
     * 根据agentId查询出其推荐的渠道
     * @author gebing
     * @date 2015年12月15日
     * @param agentId 渠道推荐者的id
     * @return 推荐结果
     */
    List<AgentRecommendRecord> selectMyRecommendAgents(Integer agentId);
}
