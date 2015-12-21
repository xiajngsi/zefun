package com.zefun.web.mapper;

import com.zefun.web.entity.AccountTransfer;

/**
 * 账户转账记录
* @author 王大爷
* @date 2015年9月11日 上午11:18:32
 */
public interface AccountTransferMapper {
    
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月11日 上午11:18:42
    * @param transferId 转账标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer transferId);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月11日 上午11:18:42
    * @param record 账户转账记录
    * @return 是否成功
     */
    int insert(AccountTransfer record);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月11日 上午11:18:42
    * @param record 账户转账记录
    * @return 是否成功
     */
    int insertSelective(AccountTransfer record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月11日 上午11:18:42
    * @param transferId 转账标识
    * @return 是否成功
     */
    AccountTransfer selectByPrimaryKey(Integer transferId);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月11日 上午11:18:42
    * @param record 账户转账记录
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(AccountTransfer record);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月11日 上午11:18:42
    * @param record 账户转账记录
    * @return 是否成功
     */
    int updateByPrimaryKey(AccountTransfer record);
}