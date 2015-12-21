package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.EmployeeEvaluate;


/**
 * 员工评价记录操作对象
* @author 张进军
* @date Nov 7, 2015 7:49:56 PM
 */
public interface EmployeeEvaluateMapper {
    /**
     * 根据记录标识删除记录
    * @author 张进军
    * @date Nov 7, 2015 7:50:15 PM
    * @param id 记录标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer id);

    
    /**
     * 新增评价记录
    * @author 张进军
    * @date Nov 7, 2015 7:51:38 PM
    * @param record     评价记录
    * @return   0:失败，1:成功
     */
    int insert(EmployeeEvaluate record);

    
    /**
     * 根据记录标识查询记录信息
    * @author 张进军
    * @date Nov 7, 2015 7:52:33 PM
    * @param id     记录标识
    * @return   记录信息
     */
    EmployeeEvaluate selectByPrimaryKey(Integer id);

    
    /**
     * 根据记录标识修改评价记录
    * @author 张进军
    * @date Nov 7, 2015 7:51:47 PM
    * @param record     评价记录
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(EmployeeEvaluate record);
    
    
    /**
     * 批量新增评价记录
    * @author 张进军
    * @date Nov 7, 2015 11:02:07 PM
    * @param list   评价列表
    * @return   0:失败，成功条数
     */
    int insertBatch(List<EmployeeEvaluate> list);
}