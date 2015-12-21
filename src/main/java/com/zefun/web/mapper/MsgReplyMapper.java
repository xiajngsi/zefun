package com.zefun.web.mapper;

import java.util.Map;

import com.zefun.web.entity.MsgReply;
/**
 * 自动回复消息(包括关注和发送消息自动回复)
* @author 高国藩
* @date 2015年8月11日 下午3:29:46
 */
public interface MsgReplyMapper {
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:27:04
    * @param msgId 主键
    * @return 状态
     */
    int deleteByPrimaryKey(Integer msgId);
    /**
     * 
    * @author 高国藩
    * @date 2015年8月11日 下午3:29:04
    * @param record 实体
    * @return 状态
     */
    int insert(MsgReply record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:27:04
    * @param record 实体
    * @return 状态
     */
    int insertSelective(MsgReply record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:27:04
    * @param msgId 主键
    * @return 状态
     */
    MsgReply selectByPrimaryKey(Integer msgId);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:27:04
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(MsgReply record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:27:04
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(MsgReply record);
    /**
     * 获得自动回复信息:包括关注回复和消息回复
    * @author 高国藩
    * @date 2015年8月11日 下午3:27:04
    * @param map 参数
    * @return 实体
     */
	MsgReply selectReplyByParam(Map<String, Integer> map);
	/**
	 * 根据参数删除实体
	* @author 高国藩
	* @date 2015年9月10日 下午3:30:57
	* @param map 参数
	* @return 行数
	 */
    int deleteByParam(Map<String, Integer> map);
    /**
     * 根据mediaId删除,该情况用于删除图文消息
    * @author 高国藩
    * @date 2015年9月30日 上午11:52:03
    * @param mediaId 媒体ID
     */
    void deleteByMediaId(String mediaId);
}