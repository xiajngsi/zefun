package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.ComboProject;
/**
 * 套餐项目关联Mapper
* @author 洪秋霞
* @date 2015年8月11日 上午10:49:17
 */
public interface ComboProjectMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:49:33
    * @param comboProject 套餐项目关联
    * @return int
     */
    int deleteByPrimaryKey(ComboProject comboProject);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:50:09
    * @param comboProject 套餐项目关联
    * @return int
     */
    int insertSelective(ComboProject comboProject);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:50:30
    * @param comboProject 套餐项目关联
    * @return ComboProject
     */
    ComboProject selectByPrimaryKey(ComboProject comboProject);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:50:52
    * @param comboProject 套餐项目关联
    * @return int
     */
    int updateByPrimaryKeySelective(ComboProject comboProject);
    
    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:51:11
    * @param comboProject 套餐项目关联
    * @return List<ComboProject>
     */
    List<ComboProject> selectByProperty(ComboProject comboProject);

    /**
     * 批量插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:51:34
    * @param comboProjectList 套餐项目关联列表
    * @return int
     */
    int insertComboProject(List<ComboProject> comboProjectList);
}