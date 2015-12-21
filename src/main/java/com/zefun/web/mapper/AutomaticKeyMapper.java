package com.zefun.web.mapper;

import com.zefun.web.entity.AutomaticKey;

/**
 * 菜单回复关联
* @author 高国藩
* @date 2015年8月11日 下午3:23:04
 */
public interface AutomaticKeyMapper {
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:24:06
    * @param automaticId 主键
    * @return 返回状态
     */
    int deleteByPrimaryKey(Integer automaticId);
    /**
     * 插入
    * @author 高国藩
    * @date 2015年8月11日 下午3:24:35
    * @param record 实体
    * @return 插入状态
     */
    int insert(AutomaticKey record);
    /**
     * 插入
    * @author 高国藩
    * @date 2015年8月11日 下午3:24:35
    * @param record 实体
    * @return 插入状态
     */
    int insertSelective(AutomaticKey record);
    /**
     * 查询
    * @author 高国藩
    * @date 2015年8月11日 下午3:25:23
    * @param automaticId 主键
    * @return 实体
     */
    AutomaticKey selectByPrimaryKey(Integer automaticId);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:25:38
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(AutomaticKey record);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:26:09
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(AutomaticKey record);
    /**
     * 查询
    * @author 高国藩
    * @date 2015年8月11日 下午3:26:29
    * @param eventKey 通过key查询
    * @return 实体
     */
	AutomaticKey selectRespByKey(String eventKey);
}