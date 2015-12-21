package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.ItemCensus;

/**
 * 统计
* @author 高国藩
* @date 2015年9月7日 下午4:05:53
 */
public interface ItemCensusMapper {
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:29
    * @param censusId 主键
    * @return 音响行数
     */
    int deleteByPrimaryKey(Integer censusId);
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:29
    * @param record 类
    * @return 影响行数
     */
    int insert(ItemCensus record);
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:29
    * @param record 类
    * @return 影响行数
     */
    int insertSelective(ItemCensus record);
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:29
    * @param censusId 主键
    * @return 状态
     */
    ItemCensus selectByPrimaryKey(Integer censusId);
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:29
    * @param record 类
    * @return 影响行数
     */
    int updateByPrimaryKeySelective(ItemCensus record);
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月7日 下午4:05:29
    * @param record 类
    * @return 影响行数
     */
    int updateByPrimaryKey(ItemCensus record);
    /**
     * 根据msgId进行查询
    * @author 高国藩
    * @date 2015年9月7日 下午4:26:54
    * @param msgId 微信返回的ID
    * @return 实体
     */
    ItemCensus selectByMsgId(String msgId);
    
    /**
     * 复制的图文消息
    * @author 高国藩
    * @date 2015年9月9日 下午7:05:54
    * @param fatherMediaId 复制的图文消息
    * @return 集合
     */
    List<ItemCensus> selectHasSendObject(String fatherMediaId);
}