package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.ObjectiveDto;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.Page;

/**
 * 人员目标
* @author chendb
* @date 2015年8月17日 下午3:35:58
 */
public interface EmployeeObjectiveMapper {
    /**
     * 批量新增员工目标
    * @author 张进军
    * @date Dec 15, 2015 12:15:16 AM
    * @param list   员工目标列表
    * @return   成功条数
     */
    int insertBatch(List<EmployeeObjective> list);
    
    /**
     * 删除
    * @author chendb
    * @date 2015年8月17日 下午3:36:15
    * @param objectiveId 目标标识
    * @return int
     */
    int deleteByPrimaryKey(Integer objectiveId);
    /**
     * 新增功能
    * @author chendb
    * @date 2015年8月17日 下午3:36:46
    * @param objectiveDto 参数
    * @return int
     */
    int insert(ObjectiveDto objectiveDto);
    /**
     * 新增功能
    * @author chendb
    * @date 2015年8月17日 下午3:37:05
    * @param record 参数
    * @return int
     */
    int insertSelective(EmployeeObjective record);
    
    /**
     * 修改功能
    * @author chendb
    * @date 2015年8月17日 下午3:38:25
    * @param record 参数
    * @return int
     */
    int updateByPrimaryKeySelective(EmployeeObjective record);
    /**
     * 人员目标修改
    * @author chendb
    * @date 2015年8月17日 下午3:38:42
    * @param objectiveDto 参数
    * @return int
     */
    int updateByPrimaryKey(ObjectiveDto objectiveDto);
    /**
     * 人员目标
    * @author chendb
    * @date 2015年8月17日 下午3:46:34
    * @param page 参数
    * @return List<Map<String,Object>>
     */
    List<ObjectiveDto>getObjective(Page<ObjectiveDto> page);
    /**
     * 判断要新增的人员月目标是否存在了
    * @author chendb
    * @date 2015年8月17日 下午4:59:03
    * @param map 参数
    * @return int
     */
    int isexistObjective (Map<String, Object> map);
    /**
     * 获取详情
    * @author chendb
    * @date 2015年8月17日 下午7:14:51
    * @param objectiveId 目标标识
    * @return ObjectiveDto
     */
    ObjectiveDto queryDetail (Integer objectiveId);
    /**
     * 获取每月人员目标的总统计
    * @author chendb
    * @date 2015年8月20日 上午10:38:54
    * @param map 参数
    * @return Map<String, Object>
     */
    Map<String, Object>getsum(Map<String, Object> map);
    /**
     * 根据员工标识和月份获取员工目标
    * @author chendb
    * @date 2015年8月20日 下午3:09:13
    * @param employeeObjective 参数bean
    * @return EmployeeObjective
     */
    EmployeeObjective getObjectiveInfo(EmployeeObjective employeeObjective);
    
    /**
     * 获取门店下所有人员的目标
    * @author chendb
    * @date 2015年9月25日 下午5:27:20
    * @param page 参数
    * @return List<EmployeeDto>
     */
    List<EmployeeDto> getEmployee(Page<EmployeeDto> page);
    
    /**
     * 导出功能
     * @author chendb
     * @date 2015年9月25日 下午5:27:20
     * @param map 参数
     * @return List<EmployeeDto>
     */
    List<EmployeeDto> downloadExcle(Map<String, Object> map);
    /**
     * 导入功能
    * @author chendb
    * @date 2015年10月12日 下午2:31:09
    * @param objectiveDto 参数
    * @return int
     */
    int importExcle(ObjectiveDto objectiveDto);
    
    /**
     * 修改员工业绩目标表
    * @author 王大爷
    * @date 2015年11月17日 下午8:20:04
    * @param employeeObjective 参数
    * @return 是否成功
     */
    Integer updateActual(EmployeeObjective employeeObjective);
    
    /**
     * 减少员工业绩目标
    * @author 王大爷
    * @date 2015年12月2日 上午11:40:13
    * @param employeeObjective 参数
    * @return 是否成功
     */
    Integer updateDecreaseActual(EmployeeObjective employeeObjective);
    
    /**
     * 根据员工标识查询目标记录（最多12条）
    * @author 王大爷
    * @date 2015年11月18日 下午7:49:27
    * @param employeeId 员工标识
    * @return List<EmployeeObjective>
     */
    List<EmployeeObjective> selectObjectiveByEmployeeId(Integer employeeId);
    
    /**
     * 根据员工标识、目标月份查询
    * @author 王大爷
    * @date 2015年12月3日 下午9:02:52
    * @param map 参数
    * @return ObjectiveDto
     */
    EmployeeObjective selectObjectiveByMonth(Map<String, Object> map);
}