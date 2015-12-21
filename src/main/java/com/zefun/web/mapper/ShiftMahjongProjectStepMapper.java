package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.ShiftMahjongProjectStep;

/**
 * 轮牌项目步骤关系
* @author 王大爷
* @date 2015年9月19日 下午4:26:29
 */
public interface ShiftMahjongProjectStepMapper {
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月19日 下午4:25:56
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer shiftMahjongStepId);

    /**
     * 添加
    * @author 王大爷
    * @date 2015年9月19日 下午4:25:56
    * @param record 轮牌项目步骤关系
    * @return 是否成功
     */
    int insert(ShiftMahjongProjectStep record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月19日 下午4:25:56
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return 是否成功
     */
    ShiftMahjongProjectStepDto selectByPrimaryKey(Integer shiftMahjongStepId);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月19日 下午4:25:56
    * @param record 轮牌项目步骤关系
    * @return 是否成功
     */
    int updateByPrimaryKey(ShiftMahjongProjectStep record);
    
    /**
     * 通过订单明细标识、步骤顺序、项目标识查询轮牌项目步骤关系标识
    * @author 王大爷
    * @date 2015年9月25日 下午4:12:29
    * @param map 参数
    * @return Integer
     */
    Integer selectShiftMahjongStepId(Map<String, Integer> map);
    
    /**
     * 根据项目步骤标识、订单明细查询轮牌项目步骤关系(第一步)
    * @author 王大爷
    * @date 2015年9月28日 下午2:05:08
    * @param map 参数
    * @return ShiftMahjongProjectStepDto
     */
    ShiftMahjongProjectStepDto selectShiftMahjongStep(Map<String, Integer> map);
    
    /**
     * 根据员工标识查询服务中步骤、指定中服务
    * @author 王大爷
    * @date 2015年9月29日 下午7:13:45
    * @param employeeId 员工标识
    * @return ShiftMahjongProjectStepDto
     */
    List<ShiftMahjongProjectStepDto> selectByEmployeeId(Integer employeeId);
    
    /**
     * 根据步骤标识、员工
    * @author 王大爷
    * @date 2015年9月29日 下午7:27:03
    * @param map 参数
    * @return ShiftMahjongProjectStepDto
     *//*
    ShiftMahjongProjectStepDto serveDetail(Map<String, Integer> map);*/
    
    /**
     * 修改上个步骤信息
    * @author 王大爷
    * @date 2015年10月20日 下午3:57:25
    * @param record 轮牌步骤信息
    * @return 是否成功
     */
    Integer updateLastValue(ShiftMahjongProjectStep record);
    
    /**
     * 查询没有做完的项目
    * @author 王大爷
    * @date 2015年10月24日 上午10:30:26
    * @param detailId 明细标识
    * @return 是否成功
     */
    List<ShiftMahjongProjectStep> selectByIsNotOver(Integer detailId);
    
    /**
     * 根据步骤标识查询该步骤前的没完成的步骤（isover 1、进行中 2、等待中）
    * @author 王大爷
    * @date 2015年10月24日 下午4:47:31
    * @param map 步骤标识、状态
    * @return List<ShiftMahjongProjectStep>
     */
    List<ShiftMahjongProjectStep> selectByNotOver(Map<String, Integer> map);
    
    /**
     * 根据轮牌项目步骤标识，查出上一个轮牌步骤 
    * @author 王大爷
    * @date 2015年11月3日 下午8:35:14
    * @param shiftMahjongStepId 步骤标识
    * @return ShiftMahjongProjectStep
     */
    ShiftMahjongProjectStep selectShiftMahjongLastStep(Integer shiftMahjongStepId);
    
    /**
     * 
    * @author 王大爷
    * @date 2015年10月24日 下午7:27:34
    * @param detailId 明细标识
    * @return OrderDetailStepDto
     */
    List<OrderDetailStepDto> selectOrderStepListByDetailId(Integer detailId);
    
    /**
     * 查询当前员工指定订单
    * @author 王大爷
    * @date 2015年10月27日 下午6:19:09
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @return List<ShiftMahjongProjectStep>
     */
    List<ShiftMahjongProjectStep> selectAppoint(Integer shiftMahjongEmployeeId);
    
    /**
     * 修改特定字段
    * @author 王大爷
    * @date 2015年11月5日 下午5:21:20
    * @param shiftMahjongProjectStep 步骤
    * @return 是否成功
     */
    Integer updateBySpecial(ShiftMahjongProjectStep shiftMahjongProjectStep);
    
    /**
     * 根据明细标识查询步骤
    * @author 王大爷
    * @date 2015年12月6日 下午4:02:22
    * @param detailId 明细标识
    * @return List<ShiftMahjongProjectStep>
     */
    List<ShiftMahjongProjectStep> selectStepByDetailId(Integer detailId);
}