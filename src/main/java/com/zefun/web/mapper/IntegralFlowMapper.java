package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.Page;

/**
 * 积分流水
* @author 张进军
* @date Oct 20, 2015 8:25:27 PM
 */
public interface IntegralFlowMapper {
    /**
     * 根据流水标识删除流水记录
    * @author 张进军
    * @date Oct 20, 2015 8:25:44 PM
    * @param flowId 流水标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer flowId);

    /**
     * 新增流水记录
    * @author 张进军
    * @date Oct 20, 2015 8:26:28 PM
    * @param record 流水记录
    * @return   0:失败，1:成功
     */
    int insert(IntegralFlow record);

    /**
     * 根据流水标识查询流水记录
    * @author 张进军
    * @date Oct 20, 2015 8:26:46 PM
    * @param flowId 流水标识
    * @return   流水记录
     */
    IntegralFlow selectByPrimaryKey(Integer flowId);

    /**
     * 根据流水标识修改流水记录
    * @author 张进军
    * @date Oct 20, 2015 8:27:46 PM
    * @param record 流水记录
    * @return   流水标识
     */
    int updateByPrimaryKey(IntegralFlow record);
    
    /**
     * 根据会员标识查询积分流水
    * @author 张进军
    * @date Oct 20, 2015 8:41:21 PM
    * @param accountId  会员标识
    * @return   流水记录
     */
    List<IntegralFlow> selectFlowListByAccountId(int accountId);
    
    /**
     * 根据订单标识查询获赠积分数量
    * @author 张进军
    * @date Nov 9, 2015 11:46:33 PM
    * @param orderId    订单标识
    * @return   获赠积分数量
     */
    IntegralFlow selectByOrderId(int orderId);
    
    /**
     * 根据订单标识查询获赠积分数量
    * @author 张进军
    * @date Nov 9, 2015 11:46:33 PM
    * @param orderId    订单标识
    * @return   获赠积分数量
     */
    int selectIntegralAmountByOrderId(int orderId);
    
    /**
     * 分页查询积分流水
    * @author 王大爷
    * @date 2015年12月1日 上午11:30:35
    * @param page 参数
    * @return List<IntegralFlow>
     */
    List<IntegralFlow> selectFlowListByAccountIdPage(Page<IntegralFlow> page);
}