package com.zefun.web.mapper;

import com.zefun.web.entity.FacesetStore;

/**
 * face集合门店关联操作类
* @author 张进军
* @date Aug 11, 2015 3:39:06 PM
 */
public interface FacesetStoreMapper {
    
    /**
     * 根据face集合标识跟门店标识删除记录
    * @author 张进军
    * @date Aug 11, 2015 3:39:22 PM
    * @param key    face集合标识跟门店标识
    * @return   0:失败，1:成功   
     */
    int deleteByPrimaryKey(FacesetStore key);

    /**
     * 插入face集合标识跟门店标识关联信息
    * @author 张进军
    * @date Aug 11, 2015 3:40:01 PM
    * @param record face集合标识跟门店标识关联信息
    * @return   0:失败，1:成功
     */
    int insert(FacesetStore record);
    
    /**
     * 根据门店标识查询关联信息
    * @author 张进军
    * @date Aug 11, 2015 3:40:30 PM
    * @param storeId    门店标识
    * @return   门店对应的face集合
     */
    String selectSetIdByStoreId(int storeId);
}