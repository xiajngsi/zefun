package com.zefun.web.mapper;

import com.zefun.web.entity.WechatAgent;


/**
 * 微信代理映射操作类
* @author 张进军
* @date Nov 25, 2015 6:03:02 PM
 */
public interface WechatAgentMapper {
    
    
    /**
     * 新增微信与代理的映射
    * @author 张进军
    * @date Nov 25, 2015 6:03:19 PM
    * @param record 映射信息
    * @return   0:失败，1:成功
     */
    int insert(WechatAgent record);

    
    /**
     * 根据微信标识查询映射信息
    * @author 张进军
    * @date Nov 25, 2015 6:03:43 PM
    * @param openId 微信标识
    * @return   映射信息
     */
    WechatAgent selectByPrimaryKey(String openId);
}