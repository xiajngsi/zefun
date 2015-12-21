package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.StoreFlowBaseDto;
import com.zefun.web.dto.StoreFlowDto;
import com.zefun.web.dto.StoreIncomeDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreFlow;

/**
 * 开支记账Mapper
* @author 王大爷
* @date 2015年8月11日 下午2:11:37
 */
public interface StoreFlowMapper {

    /**
     * 删除开支记账
    * @author 王大爷
    * @date 2015年8月11日 上午10:43:48
    * @param flowId 开支记账ID
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer flowId);

    /**
     * 插入开支记账
    * @author 王大爷
    * @date 2015年8月11日 上午10:43:04
    * @param record 开支记账
    * @return 是否成功
     */
    int insert(StoreFlow record);

    /**
     * 插入存在属性开支记账
    * @author 王大爷
    * @date 2015年8月11日 上午10:42:25
    * @param record 开支记账
    * @return 是否成功
     */
    int insertSelective(StoreFlow record);

    /**
     * 通过开支记账ID查询开支记账
    * @author 王大爷
    * @date 2015年8月11日 上午10:41:42
    * @param flowId 开支记账ID
    * @return 轮牌信息
     */
    StoreFlow selectByPrimaryKey(Integer flowId);

    /**
     * 修改已存在的开支记账
    * @author 王大爷
    * @date 2015年8月11日 上午10:40:56
    * @param record 开支记账
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(StoreFlow record);

    /**
     * 整体修改开支记账
    * @author 王大爷
    * @date 2015年8月11日 上午10:40:16
    * @param record 开支记账
    * @return 是否成功
     */
    int updateByPrimaryKey(StoreFlow record);

    /**
     * 批量插入开支记账
    * @author 王大爷
    * @date 2015年8月11日 下午2:07:52
    * @param list 开支记账
    * @return 是否成功
     */
    int insertStoreFlowList(List<StoreFlow> list);

    /**
     * 分页查询
    * @author 王大爷
    * @date 2015年8月11日 下午2:07:13
    * @param page page对象
    * @return List<StoreFlow>
     */
    List<StoreFlowBaseDto> selectByPage(Page<StoreFlowBaseDto> page);

    /**
     * 开支记账动态查询
    * @author 王大爷
    * @date 2015年8月11日 下午2:05:41
    * @param storeFlow 开支记账
    * @return 开支记账集合
     */
    List<StoreFlow> selectByStoreFlow(StoreFlow storeFlow);

    /**
     * 查询汇总信息
    * @author 王大爷
    * @date 2015年8月11日 下午2:03:52
    * @param storeFlowDto 开支记账DTO
    * @return 汇总信息
     */
    Integer selectByCome(StoreFlowDto storeFlowDto);

    /**
     * 根据多个门店id和时间查询各个门店收益
     * @author gebing
     * @date 2015年12月4日
     * @param params storeIds 多个门店id, begin 开始, end 结束
     * @return 各个门店收益
     */
    List<StoreIncomeDto> selectMonthIncomesByTime(Map<String, Object> params);
}
