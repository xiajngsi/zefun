package com.zefun.web.mapper;

import com.zefun.web.entity.EmployeeShift;

/**
 * 员工班次关联映射
 * @author lzc
 *
 */
public interface EmployeeShiftMapper {
	/**
	 * 根据主键删除员工班次关联
	 * @param dataId 主键
	 * @return 成功数目
	 */
    int deleteByPrimaryKey(Integer dataId);

    /**
     * 全插
     * @param record 员工班次关联记录
     * @return 成功数目
     */
    int insert(EmployeeShift record);

    /**
     * 选择性插入
     * @param record 员工班次关联记录
     * @return 成功数目
     */
    int insertSelective(EmployeeShift record);

    /**
     * 根据主键查询员工关联记录
     * @param dataId 主键
     * @return 员工班次关联记录
     */
    EmployeeShift selectByPrimaryKey(Integer dataId);

    /**
     * 选择性更新员工关联记录
     * @param record 员工关联记录
     * @return 成功数目
     */
    int updateByPrimaryKeySelective(EmployeeShift record);

    /**
     * 根据主键更新员工关联记录
     * @param record 员工关联记录
     * @return 成功数目
     */
    int updateByPrimaryKey(EmployeeShift record);
}