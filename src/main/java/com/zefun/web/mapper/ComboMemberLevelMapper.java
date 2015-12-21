package com.zefun.web.mapper;

import com.zefun.web.entity.ComboMemberLevel;
/**
 * 套餐会员等级关联表
* @author 洪秋霞
* @date 2015年9月15日 上午10:39:43
 */
public interface ComboMemberLevelMapper {
    /**
     * 删除套餐会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:39:57
    * @param comboMemberLevelId 套餐会员等级关联id
    * @return int
     */
    int deleteByPrimaryKey(Integer comboMemberLevelId);

    /**
     * 添加套餐会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:40:12
    * @param comboMemberLevel 套餐会员等级关联
    * @return int
     */
    int insertSelective(ComboMemberLevel comboMemberLevel);

    /**
     * 根据主键id查询套餐会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:40:20
    * @param comboMemberLevel 套餐会员等级关联
    * @return ComboMemberLevel
     */
    ComboMemberLevel selectByPrimaryKey(ComboMemberLevel comboMemberLevel);

    /**
     * 更新套餐会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:40:29
    * @param comboMemberLevel 套餐会员等级关联
    * @return int
     */
    int updateByPrimaryKeySelective(ComboMemberLevel comboMemberLevel);
    
    /**
     * 根据套餐id查询
    * @author 洪秋霞
    * @date 2015年9月15日 上午11:44:02
    * @param comboId 套餐id
    * @return ComboMemberLevel
     */
    ComboMemberLevel selectByComboId(Integer comboId);
    

}