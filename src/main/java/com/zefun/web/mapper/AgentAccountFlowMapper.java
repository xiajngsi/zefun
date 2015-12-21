package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.IncomeDto;
import com.zefun.web.entity.AgentAccountFlow;

/**
 * 代理账户流水操作类
* @author 张进军
* @date Nov 25, 2015 5:51:38 PM
 */
public interface AgentAccountFlowMapper {

    /**
     * 新增代理账户流水
    * @author 张进军
    * @date Nov 25, 2015 5:51:51 PM
    * @param record 流水记录
    * @return   1:成功，0:失败
     */
    int insert(AgentAccountFlow record);

    /**
     *  查询多个月份的收益
    * @author 张进军
    * @date Nov 25, 2015 5:51:51 PM
    * @param params 参数 , agentId 渠道商id, begin 开始时间, end 结束时间
    * @return       集合, 多个月份的收益
     */
    List<IncomeDto> selectMonthIncomeByTime(Map<String, Object> params);
}
