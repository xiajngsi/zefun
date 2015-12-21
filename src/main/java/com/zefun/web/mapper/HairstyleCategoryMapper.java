package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.HairstyleCategoryDto;
import com.zefun.web.entity.HairstyleCategory;
/**
 * 发型类别
* @author 洪秋霞
* @date 2015年9月8日 上午11:49:20
 */
public interface HairstyleCategoryMapper {
    
    /**
     * 删除发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:48:12
    * @param hairstyleCategoryId 发型类别id
    * @return int
     */
    int deleteByPrimaryKey(Integer hairstyleCategoryId);

    /**
     * 新增发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:48:22
    * @param hairstyleCategory 发型类别
    * @return int
     */
    int insertSelective(HairstyleCategory hairstyleCategory);

    /**
     * 查询发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:48:31
    * @param hairstyleCategoryId 发型类别id
    * @return HairstyleCategory
     */
    HairstyleCategory selectByPrimaryKey(Integer hairstyleCategoryId);

    /**
     * 修改发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:48:36
    * @param hairstyleCategory 发型类别
    * @return int
     */
    int updateByPrimaryKeySelective(HairstyleCategory hairstyleCategory);
    
    /**
     * 根据门店id查询
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:19:59
    * @param storeId 门店id
    * @return List<HairstyleCategory>
     */
    List<HairstyleCategory> selectByStoreId(Integer storeId);

    /**
     * 查找发型类别和发型列表
    * @author 洪秋霞
    * @date 2015年9月8日 下午3:12:42
    * @param storeId 门店id
    * @return List<HairstyleCategoryDto>
     */
    List<HairstyleCategoryDto> getHairstyleCategoryInfo(Integer storeId);
    
}