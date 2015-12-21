package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.CardCommission;

/**
 * 员工开卡充值提成
* @author 王大爷
* @date 2015年9月10日 下午4:26:15
 */
public interface CardCommissionMapper {
    
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月10日 下午4:26:23
    * @param commissionId 提成标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer commissionId);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:26:23
    * @param record 员工开卡充值提成
    * @return 是否成功
     */
    int insert(CardCommission record);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:26:23
    * @param record 员工开卡充值提成
    * @return 是否成功
     */
    int insertSelective(CardCommission record);

    /**
     * 根据id查询
    * @author 王大爷
    * @date 2015年9月10日 下午4:26:23
    * @param commissionId 提成标识
    * @return 是否成功
     */
    CardCommission selectByPrimaryKey(Integer commissionId);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:26:23
    * @param record 员工开卡充值提成
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(CardCommission record);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:26:23
    * @param record 员工开卡充值提成
    * @return 是否成功
     */
    int updateByPrimaryKey(CardCommission record);
    
    /**
     * 批量插入员工开卡充值提成
    * @author 王大爷
    * @date 2015年9月10日 下午4:43:47
    * @param list 员工开卡充值提成集合
    * @return 是否成功
     */
    int insertCardCommissionList(List<CardCommission> list);
}