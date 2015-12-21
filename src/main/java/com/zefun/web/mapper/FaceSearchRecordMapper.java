package com.zefun.web.mapper;

import com.zefun.web.entity.FaceSearchRecord;

/**
 * face搜索记录操作类
* @author 张进军
* @date Aug 11, 2015 3:36:23 PM
 */
public interface FaceSearchRecordMapper {
    
    /**
     * 根据face标识删除搜索记录
    * @author 张进军
    * @date Aug 11, 2015 3:36:49 PM
    * @param faceId face标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(String faceId);

    /**
     * 插入face搜索记录
    * @author 张进军
    * @date Aug 11, 2015 3:37:08 PM
    * @param record 记录信息
    * @return   0:失败，1:成功  
     */
    int insert(FaceSearchRecord record);

    /**
     * 根据face标识查询face搜索记录
    * @author 张进军
    * @date Aug 11, 2015 3:37:32 PM
    * @param faceId face标识
    * @return   face记录信息
     */
    FaceSearchRecord selectByPrimaryKey(String faceId);

    /**
     * 根据face标识修改face搜索记录
    * @author 张进军
    * @date Aug 11, 2015 3:38:14 PM
    * @param record face搜索记录信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(FaceSearchRecord record);
}