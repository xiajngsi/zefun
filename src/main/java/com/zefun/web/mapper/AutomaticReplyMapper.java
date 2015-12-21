package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.AutomaticReply;
import com.zefun.wechat.dto.AutomaticReplyDto;
import com.zefun.wechat.dto.ItemStatusDto;
/**
 * 回复消息(保留了上传至微信端的素材信息,方便自动回复的设置)
* @author 高国藩
* @date 2015年8月11日 下午3:34:40
 */
public interface AutomaticReplyMapper {
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:35:20
    * @param replyId 主键
    * @return 状态
     */
    int deleteByPrimaryKey(Integer replyId);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:35:46
    * @param record 实体
    * @return 状态
     */
    int insert(AutomaticReply record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年8月11日 下午3:36:05
    * @param record 实体
    * @return 状态
     */
    int insertSelective(AutomaticReply record);
    /**
     * 查询
    * @author 高国藩
    * @date 2015年8月11日 下午3:36:34
    * @param replyId 主键
    * @return 实体
     */
    AutomaticReply selectByPrimaryKey(Integer replyId);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:36:54
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(AutomaticReply record);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:08
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(AutomaticReply record);

    /**
     * 批量新增
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:31
    * @param automaticReplies 集合
    * @return 状态
     */
	int insertByList(List<AutomaticReply> automaticReplies);
    /**
     * 查询回复消息
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:36
    * @param mediaId 素材ID
    * @return 回复列表
     */
	List<AutomaticReply> selectByMediaId(String mediaId);
    /**
     * 查询回复消息
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:40
    * @param storeId 门店ID
    * @return 回复列表
     */
	List<AutomaticReply> selectByStoreId(Integer storeId);
    /**
     * 查询回复消息，用于展示图文消息，此处的图文消息只显示一个
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:40
    * @param storeId 门店ID
    * @return 回复列表
     */
	List<AutomaticReply> selectItemsByStoreId(Integer storeId);
	
	/**
     * 查询回复消息，用于展示图文消息，此处的图文消息只显示一个,dto数据传输
    * @author 高国藩
    * @date 2015年8月11日 下午3:37:40
    * @param storeId 门店ID
    * @return 回复列表
     */
    List<AutomaticReplyDto> selectItemsByStoreIdForDto(Integer storeId);
	
	/**
	 * 批量修改操作
	* @author 高国藩
	* @date 2015年9月1日 上午11:40:03
	* @param reply list
	* @return 影响行数
	 */
    int updateById(AutomaticReply reply);
    
    /**
     * 根据mediaId删除图文消息
    * @author 高国藩
    * @date 2015年9月6日 下午4:26:51
    * @param mediaId 图文消息
    * @return 影像函数
     */
    int deleteItemsByMedaidId(String mediaId);
    /**
     * 发送图文
    * @author 高国藩
    * @date 2015年9月7日 下午5:43:26
    * @param storeId 门店
    * @return 图文消息
     */
    List<ItemStatusDto> selectItemStatusByStoreId(Integer storeId);
    /**
     * 跟新图文消息,将开发商的图文消息都更新下来
    * @author 高国藩
    * @date 2015年9月17日 上午11:23:46
    * @param medias 该门店已经有的图文媒体id
    * @return 媒体Id的集合(未更新的)
     */
    List<String> selectItemsByStoreIdNotSelf(List<String> medias);
    /**
     * 查询该门店已有的图文媒体id
    * @author 高国藩
    * @date 2015年9月17日 上午11:45:05
    * @param storeId 门店信息
    * @return 媒体Id集合
     */
    List<String> selectItemsByStoreIdHasItems(Integer storeId);
    /**
     * 查询所有的开发商新增的图文消息
    * @author 高国藩
    * @date 2015年9月17日 下午2:10:48
    * @param storeId 门店为0
    * @return 媒体id
     */
    List<String> selectItmesByStoreIdIsZero(Integer storeId);
    /**
     * 根据关键字查询出图文消息的mediaId集合
    * @author 高国藩
    * @date 2015年10月8日 上午10:36:38
    * @param map 参数
    * @return 图文消息集合
     */
    List<AutomaticReply> selectMediaIdByContent(Map<String, Object> map);
    
    /**
     * 查询首个图文消息
    * @author 高国藩
    * @date 2015年11月16日 下午5:27:55
    * @param mediaId 图文ID
    * @return 单个图文
     */
    AutomaticReply selectByOneItem(String mediaId);
}