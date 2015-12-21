package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.WechatEmployee;
import com.zefun.web.entity.WechatMember;

/**
 * 微信与会员关联数据操作类
* @author 张进军
* @date Aug 19, 2015 8:24:46 PM
 */
public interface WechatMemberMapper {
    /**
     * 根据微信id删除关联记录
    * @author 张进军
    * @date Aug 19, 2015 8:21:59 PM
    * @param openId 微信id
    * @return       0:失败，1:成功
     */
    int deleteByPrimaryKey(String openId);

    /**
     * 插入微信id与会员关联记录
    * @author 张进军
    * @date Aug 19, 2015 8:22:33 PM
    * @param record 关联记录
    * @return       0:失败，1:成功
     */
    int insert(WechatMember record);

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
    int updateByPrimaryKey(WechatMember record);
    
    /**
     * 给一个会员id的list,查询出所有openid
    * @author 高国藩
    * @date 2015年9月18日 上午10:07:17
    * @param memberIds 会员id集合
    * @return openId集合
     */
    List<String> selectOpenIdsByMemberIdList(List<Integer> memberIds);

    /**
     * 如果取消了关注,将is_subscribe值改为0
    * @author 高国藩
    * @date 2015年9月18日 上午10:31:17
    * @param member 关联实体
     */
    void updateByOpenId(WechatMember member);

    /**
     * 通过openId查询memberId
    * @author 高国藩
    * @date 2015年9月18日 下午5:51:42
    * @param fromUserName 微信openId
    * @return memberId 
     */
    Integer selectMemberIdByOpenId(String fromUserName);
}