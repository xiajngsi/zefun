package com.zefun.web.mapper;

import com.zefun.web.entity.AccountCharge;

/**
 * 账户充值记录
* @author 王大爷
* @date 2015年9月10日 下午7:43:54
 */
public interface AccountChargeMapper {
    
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月10日 下午7:44:05
    * @param chargeId 充值标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer chargeId);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午7:44:05
    * @param record 账户充值记录
    * @return 是否成功
     */
    int insert(AccountCharge record);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午7:44:05
    * @param record 账户充值记录
    * @return 是否成功
     */
    int insertSelective(AccountCharge record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月10日 下午7:44:05
    * @param chargeId 充值标识
    * @return 是否成功
     */
    AccountCharge selectByPrimaryKey(Integer chargeId);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午7:44:05
    * @param record 账户充值记录
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(AccountCharge record);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午7:44:05
    * @param record 账户充值记录
    * @return 是否成功
     */
    int updateByPrimaryKey(AccountCharge record);
}