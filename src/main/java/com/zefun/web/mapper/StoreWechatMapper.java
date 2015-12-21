package com.zefun.web.mapper;

import com.zefun.web.entity.StoreWechat;

/**
 * 微信门店数据关联
* @author 高国藩
* @date 2015年10月10日 上午10:09:23
 */
public interface StoreWechatMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年10月10日 上午10:12:54
    * @param relatedId 主键
    * @return 影响行数
     */
    int deleteByPrimaryKey(Integer relatedId);

    /**
     * 新增
    * @author 高国藩
    * @date 2015年10月10日 上午10:13:37
    * @param record 实体
    * @return 影响行数
     */
    int insert(StoreWechat record);

    /**
     * 非空出入新增
    * @author 高国藩
    * @date 2015年10月10日 上午10:13:54
    * @param record 实体
    * @return 影响行数
     */
    int insertSelective(StoreWechat record);

    /**
     * 查询
    * @author 高国藩
    * @date 2015年10月10日 上午10:14:20
    * @param relatedId 主键
    * @return 返回实体
     */
    StoreWechat selectByPrimaryKey(Integer relatedId);

    /**
     * 更新
    * @author 高国藩
    * @date 2015年10月10日 上午10:14:42
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKeySelective(StoreWechat record);

    /**
     * 更新
    * @author 高国藩
    * @date 2015年10月10日 上午10:15:36
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKey(StoreWechat record);

    /**
     * 根据微信的唯一id进行查询
    * @author 高国藩
    * @date 2015年10月10日 上午10:26:41
    * @param toUserName wechatID
    * @return 返回实体
     */
    StoreWechat selectByWechatId(String toUserName);

    /**
     * 查询该门店是否注册公众号
    * @author 高国藩
    * @date 2015年10月13日 下午3:34:23
    * @param storeId 门店
    * @return 返回实体
     */
    StoreWechat selectByStoreId(int storeId);
}