package com.zefun.web.mapper;

import com.zefun.web.entity.TempTableSc;

/**
 * 盛传会员数据临时表
* @author 高国藩
* @date 2015年12月4日 下午3:27:23
 */
public interface TempTableScMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:27:39
    * @param id   主键
    * @return     行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:27:39
    * @param record   主键
    * @return     行数
     */
    int insert(TempTableSc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:27:39
    * @param record   主键
    * @return     行数
     */
    int insertSelective(TempTableSc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:27:39
    * @param id   主键
    * @return     行数
     */
    TempTableSc selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:27:39
    * @param record   主键
    * @return     行数
     */
    int updateByPrimaryKeySelective(TempTableSc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:27:39
    * @param record   主键
    * @return     行数
     */
    int updateByPrimaryKey(TempTableSc record);
}