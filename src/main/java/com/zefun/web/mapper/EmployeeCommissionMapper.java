package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.EmployeeCommission;

/**
 * 员工提成操作对象
* @author 张进军
* @date Oct 28, 2015 4:32:57 PM
 */
public interface EmployeeCommissionMapper {
    /**
     * 根据提成标识删除提成信息
    * @author 张进军
    * @date Oct 28, 2015 4:33:36 PM
    * @param commissionId   提成标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer commissionId);

    /**
     * 插入新的提成信息
    * @author 张进军
    * @date Oct 28, 2015 4:34:03 PM
    * @param record 提成记录
    * @return   0:失败，1:成功
     */
    int insert(EmployeeCommission record);

    /**
     * 根据提成标识查询提成信息
    * @author 张进军
    * @date Oct 28, 2015 4:34:11 PM
    * @param commissionId   提成标识
    * @return   提成记录
     */
    EmployeeCommission selectByPrimaryKey(Integer commissionId);

    /**
     * 根据提成标识修改提成记录
    * @author 张进军
    * @date Oct 28, 2015 4:36:27 PM
    * @param record 提成记录
    * @return    0:失败，1:成功
     */
    int updateByPrimaryKey(EmployeeCommission record);
    
    /**
     * 根据员工标识查询当日业绩金额
    * @author 张进军
    * @date Oct 28, 2015 5:33:00 PM
    * @param employeeId 员工标识
    * @return   当日业绩金额
     */
    BigDecimal selectServiceDayMoneyByEmployeeId(int employeeId);
    
    /**
     * 根据员工标识,日期区间,查询提成金额
    * @author 王大爷
    * @date 2015年11月17日 下午2:32:12
    * @param map 参数
    * @return 当日提成金额
     */
    BigDecimal selectBySectionDayCommission(Map<String, Object> map);
    
    /**
     * 计算时间区间，类型的业绩 
    * @author 王大爷
    * @date 2015年11月17日 下午3:53:02
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectBySectionDayCalculate(Map<String, Object> map);
    
    /**
     * 计算时间区间，指定项目的业绩
    * @author 王大爷
    * @date 2015年11月17日 下午4:40:42
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectBySectionAssignProjectCalculate(Map<String, Object> map);
    
    /**
     * 根据员工集合排序 
    * @author 王大爷
    * @date 2015年11月19日 下午3:02:58
    * @param map 参数
    * @return BigDecimal
     */
    List<Map<String, Object>> selectByEmployeeIdList(Map<String, Object> map);
    
    /**
     * 查询区域时间内，岗位对应的总业绩
    * @author 王大爷
    * @date 2015年11月19日 下午6:14:16
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectByTotalEmployeeIdList(Map<String, Object> map);
    
    /**
     * 查询区域时间内，岗位对应的指定总业绩
    * @author 王大爷
    * @date 2015年11月19日 下午6:27:54
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectByTotalSectionAssignProjectCalculate(Map<String, Object> map);
    
    /**
     * 根据明细标识查询员工服务提成
    * @author 王大爷
    * @date 2015年12月2日 下午2:07:46
    * @param detailId 明细标识
    * @return List<EmployeeCommission>
     */
    List<EmployeeCommission> selectByDetailId(Integer detailId);
    
    /**
     * 根据轮牌步骤标识
    * @author 王大爷
    * @date 2015年12月2日 下午8:36:15
    * @param shiftMahjongStepId 参数
    * @return EmployeeCommission
     */
    EmployeeCommission selectByEmployeeIdShift(Integer shiftMahjongStepId);
    
}