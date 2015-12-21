package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.MoneyFlowDto;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.Page;

/**
 * 资金流水
* @author 王大爷
* @date 2015年9月14日 上午10:31:49
 */
public interface MoneyFlowMapper {
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月14日 上午10:31:57
    * @param flowId 流水标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer flowId);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月14日 上午10:31:57
    * @param record 资金流水
    * @return 是否成功
     */
    int insert(MoneyFlow record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月14日 上午10:31:57
    * @param flowId 流水标识
    * @return 是否成功
     */
    MoneyFlow selectByPrimaryKey(Integer flowId);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月14日 上午10:31:57
    * @param record 资金流水
    * @return 是否成功
     */
    int updateByPrimaryKey(MoneyFlow record);
    
    /**
     * 根据会员标识分页查询资金流水
    * @author 王大爷
    * @date 2015年9月14日 下午4:52:14
    * @param page 会员标识
    * @return List<Page<MoneyFlow>>
     */
    List<MoneyFlowDto> selectByPage(Page<MoneyFlowDto> page);
    
    /**
     * 根据会员标识查询转出记录
    * @author 王大爷
    * @date 2015年10月10日 下午2:52:09
    * @param map 会员标识
    * @return List<MoneyFlowDto>
     */
    List<MoneyFlowDto> selectDetail(Map<String, Integer> map);
    
    /**
     * 根据会员标识查询流水记录
    * @author 张进军
    * @date Oct 23, 2015 10:58:17 PM
    * @param memberId   会员标识
    * @return   流水记录
     */
    List<MoneyFlow> selectFlowListByMemberId(int memberId);
    
    /**
     * 根据订单标识查询流水
    * @author 王大爷
    * @date 2015年12月12日 上午11:50:24
    * @param orderId 订单标识
    * @return List<MoneyFlow>
     */
    List<MoneyFlow> selectByOrderId(Integer orderId);
    
}