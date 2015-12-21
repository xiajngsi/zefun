package com.zefun.web.mapper;

import com.zefun.web.entity.WechatSubscribe;


/**
 * 微信关注信息操作类
* @author 张进军
* @date Nov 25, 2015 9:01:53 PM
 */
public interface WechatSubscribeMapper {
    
    
    /**
     * 新增微信关注者
    * @author 张进军
    * @date Nov 25, 2015 9:02:04 PM
    * @param record 关注信息
    * @return   0:失败，1:成功
     */
    int insert(WechatSubscribe record);

    
    /**
     * 根据微信标识查询关注信息
    * @author 张进军
    * @date Nov 25, 2015 9:02:26 PM
    * @param openId 微信标识
    * @return   关注信息
     */
    WechatSubscribe selectByPrimaryKey(String openId);

    
    /**
     * 取消关注/再次关注
    * @author 张进军
    * @date Nov 25, 2015 9:02:46 PM
    * @param record 关注信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(WechatSubscribe record);
}