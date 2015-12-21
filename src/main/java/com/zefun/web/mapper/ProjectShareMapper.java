package com.zefun.web.mapper;

import com.zefun.web.entity.ProjectShare;

/**
 * 项目分享操作类
* @author 张进军
* @date Nov 14, 2015 1:46:00 PM
 */
public interface ProjectShareMapper {
    /**
     * 根据分享标识删除分享记录
    * @author 张进军
    * @date Nov 14, 2015 1:46:08 PM
    * @param id     分享标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增分享记录
    * @author 张进军
    * @date Nov 14, 2015 1:46:38 PM
    * @param record     分享信息
    * @return   0:失败，1:成功
     */
    int insert(ProjectShare record);

    /**
     * 根据分享标识查询分享信息
    * @author 张进军
    * @date Nov 14, 2015 1:46:55 PM
    * @param id     分享标识
    * @return   分享信息
     */
    ProjectShare selectByPrimaryKey(Integer id);

    /**
     * 根据分享标识修改分享信息
    * @author 张进军
    * @date Nov 14, 2015 1:47:13 PM
    * @param record     分享信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(ProjectShare record);
}