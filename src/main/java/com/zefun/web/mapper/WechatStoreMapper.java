package com.zefun.web.mapper;

import com.zefun.web.entity.WechatStore;


/**
 * 微信门店映射操作类
* @author 张进军
* @date Nov 25, 2015 6:05:08 PM
 */
public interface WechatStoreMapper {
    
    
    /**
     * 新增微信门店映射信息
    * @author 张进军
    * @date Nov 25, 2015 6:05:23 PM
    * @param record 微信门店映射信息
    * @return   0:失败，1:成功
     */
    int insert(WechatStore record);

    
    /**
     * 根据微信标识查询微信门店映射信息
    * @author 张进军
    * @date Nov 25, 2015 6:05:42 PM
    * @param openId 微信标识
    * @return   微信门店映射信息
     */
    WechatStore selectByPrimaryKey(String openId);
}