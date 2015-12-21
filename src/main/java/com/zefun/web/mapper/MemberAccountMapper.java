package com.zefun.web.mapper;

import java.util.Map;

import com.zefun.web.entity.MemberAccount;

/**
 * 会员账户表
* @author 王大爷
* @date 2015年9月9日 下午4:48:16
 */
public interface MemberAccountMapper {
    /**
     * 删除会员账户表
    * @author 王大爷
    * @date 2015年9月9日 下午4:48:09
    * @param accountId 账户标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer accountId);

    /**
     * 新增会员账户
    * @author 王大爷
    * @date 2015年9月9日 下午4:49:08
    * @param record 会员账户表
    * @return 是否成功
     */
    int insert(MemberAccount record);

    /**
     * 根据会员账户标识
    * @author 王大爷
    * @date 2015年9月9日 下午4:49:40
    * @param accountId   账户标识
    * @return MemberAccount
     */
    MemberAccount selectByPrimaryKey(Integer accountId);

    /**
     * 修改会员账户
    * @author 王大爷
    * @date 2015年9月9日 下午4:49:43
    * @param record 会员账户表
    * @return 是否成功
     */
    int updateByPrimaryKey(MemberAccount record);
    
    /**
     * 充值后修改账户表
    * @author 王大爷
    * @date 2015年9月10日 下午8:03:40
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateCharge(Map<String, Object> hashMap);
    
    /**
     * 转账 +
    * @author 王大爷
    * @date 2015年9月11日 下午3:46:49
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateAdd(Map<String, Object> hashMap);
    
    /**
     * 转账 -
    * @author 王大爷
    * @date 2015年9月11日 下午3:46:49
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateDecrease(Map<String, Object> hashMap);
    
    /**
     * 自助收银
     * @param hashMap hashMap
     * @return 是否成功
     */
    int updateMemberCashier(Map<String, Object> hashMap);
    
    /**
     * 结账时更新礼金余额
    * @author 张进军
    * @date Nov 21, 2015 7:52:30 PM
    * @param hashMap    更新参数
    * @return   0:失败，1:成功
     */
    int updateMemberGiftMoney(Map<String, Object> hashMap);
    
    /**
     * 增加礼金账户储值金额
    * @author 王大爷
    * @date 2015年11月21日 下午1:57:09
    * @param hashMap 参数
    * @return 是否成功
     */
    int updateGiftmoney(Map<String, Object> hashMap);
    
}