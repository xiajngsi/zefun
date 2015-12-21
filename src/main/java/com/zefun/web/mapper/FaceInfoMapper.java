package com.zefun.web.mapper;

import com.zefun.web.entity.FaceInfo;

/**
 * face基本信息操作类
* @author 张进军
* @date Aug 11, 2015 3:29:44 PM
 */
public interface FaceInfoMapper {
    
    /**
     * 根据主键删除face信息
    * @author 张进军
    * @date Aug 11, 2015 3:30:01 PM
    * @param faceId face标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(String faceId);

    /**
     * 插入face信息
    * @author 张进军
    * @date Aug 11, 2015 3:32:10 PM
    * @param record face信息
    * @return   0:失败，1:成功
     */
    int insert(FaceInfo record);

    /**
     * 根据主键查询face信息
    * @author 张进军
    * @date Aug 11, 2015 3:33:55 PM
    * @param faceId face标识
    * @return   0:失败，1:成功
     */
    FaceInfo selectByPrimaryKey(String faceId);

    /**
     * 根据主键修改face信息
    * @author 张进军
    * @date Aug 11, 2015 3:34:23 PM
    * @param record face信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(FaceInfo record);
    
    /**
     * 根据用户标识查询face数量
    * @author 张进军
    * @date Aug 11, 2015 3:34:44 PM
    * @param userId 用户标识
    * @return   用户拥有的face数量
     */
    int selectCountByUserId(Integer userId);
    
    /**
     * 根据face集合标识查询face数量
    * @author 张进军
    * @date Aug 11, 2015 3:34:44 PM
    * @param setId face集合标识
    * @return   face集合下的face数量
     */
    int selectCountBySetId(String setId);
}