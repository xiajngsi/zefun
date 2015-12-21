package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.HairstyleDesign;

/**
 * 发型设置
* @author 洪秋霞
* @date 2015年9月8日 上午11:08:54
 */
public interface HairstyleDesignMapper {
    /**
     * 删除发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:09:03
    * @param hairstyleId 发型设置id
    * @return int
     */
    int deleteByPrimaryKey(Integer hairstyleId);


    /**
     * 新增发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:09:12
    * @param hairstyleDesign 发型设置
    * @return int 
     */
    int insertSelective(HairstyleDesign hairstyleDesign);

    /**
     * 查询发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:09:20
    * @param hairstyleId 发型设置id
    * @return HairstyleDesign
     */
    HairstyleDesign selectByPrimaryKey(Integer hairstyleId);

    /**
     * 修改发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:09:27
    * @param hairstyleDesign 发型设置
    * @return int
     */
    int updateByPrimaryKeySelective(HairstyleDesign hairstyleDesign);
    
    /**
     * 根据门店id查询
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:19:59
    * @param storeId 门店id
    * @return List<HairstyleDesign>
     */
    List<HairstyleDesign> selectByStoreId(Integer storeId);

}