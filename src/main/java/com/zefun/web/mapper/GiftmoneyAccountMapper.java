package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.GiftmoneyAccount;

/**
 * 礼金账户
* @author 王大爷
* @date 2015年9月10日 下午4:47:39
 */
public interface GiftmoneyAccountMapper {
    
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月10日 下午4:47:54
    * @param accountId 礼金账户标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer accountId);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:47:54
    * @param record 礼金账户
    * @return 是否成功
     */
    int insert(GiftmoneyAccount record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月10日 下午4:47:54
    * @param accountId 礼金账户标识
    * @return 是否成功
     */
    GiftmoneyAccount selectByPrimaryKey(Integer accountId);
    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:47:54
    * @param record 礼金账户
    * @return 是否成功
     */
    int updateByPrimaryKey(GiftmoneyAccount record);
    
    /**
     * 查询礼金账户是否存在
    * @author 王大爷
    * @date 2015年9月11日 上午10:12:19
    * @param hashMap hashMap
    * @return List<GiftmoneyAccount>
     */
    List<GiftmoneyAccount> selectIsExist(Map<String, Integer> hashMap);
    
    /**
     * 修改礼金总金额及余额
    * @author 王大爷
    * @date 2015年9月11日 上午10:24:13
    * @param hashMap hashMap
    * @return 是否成功
     */
    int updateGiftmoney(Map<String, Object> hashMap);
    
    /**
     * 根据会员标识查询会员所有部门下的礼金余额
    * @author 张进军
    * @date Oct 12, 2015 4:27:51 PM
    * @param memberId   会员标识
    * @return   礼金余额
     */
    BigDecimal selectGiftmoneyByMemberId(int memberId);
    
    /**
     * 根据会员标识与部门查询会员对应部门下的礼金余额
    * @author 张进军
    * @date Oct 12, 2015 4:28:50 PM
    * @param map        包含会员标识与部门标识
    * @return   礼金余额
     */
    BigDecimal selectGiftmoneyByMemberIdAndDeptId(Map<String, Object> map);
    
    /**
     * 根据会员标识、部门标识、原用户余额更新用户余额
     * @param map map
     * @return 更新数量
     */
    int updateMemberGiftBalance(Map<String, Object> map);
    
    /**
     * 查询自助收银会员关联的礼金账户
     * @param map 会员标识、部门标识
     * @return 礼金数据
     */
    GiftmoneyAccount selectSelfCashierGiftAccount(Map<String, Integer> map);
    
    /**
     * 根据会员标识查询礼金账户列表
    * @author 张进军
    * @date Nov 10, 2015 7:36:08 PM
    * @param memberId   会员标识
    * @return   礼金账户列表
     */
    List<GiftmoneyAccount> selectGiftmoneyAccountListByMemberId(int memberId);
}