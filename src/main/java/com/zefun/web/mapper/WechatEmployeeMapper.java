package com.zefun.web.mapper;

import com.zefun.web.entity.WechatEmployee;

/**
 * 微信与员工关联数据操作类
* @author 张进军
* @date Aug 19, 2015 8:20:49 PM
 */
public interface WechatEmployeeMapper {
    /**
     * 根据微信id删除关联记录
    * @author 张进军
    * @date Aug 19, 2015 8:21:59 PM
    * @param openId 微信id
    * @return       0:失败，1:成功
     */
    int deleteByPrimaryKey(String openId);

    /**
     * 插入微信id与员工关联记录
    * @author 张进军
    * @date Aug 19, 2015 8:22:33 PM
    * @param record 关联记录
    * @return       0:失败，1:成功
     */
    int insert(WechatEmployee record);

    /**
     * 根据微信id查询关联记录
    * @author 张进军
    * @date Aug 19, 2015 8:22:46 PM
    * @param openId 微信id
    * @return       关联记录
     */
    WechatEmployee selectByPrimaryKey(String openId);

    /**
     * 根据微信id修改关联记录
    * @author 张进军
    * @date Aug 19, 2015 8:22:59 PM
    * @param record 关联记录
    * @return       0:失败，1:成功
     */
    int updateByPrimaryKey(WechatEmployee record);
}