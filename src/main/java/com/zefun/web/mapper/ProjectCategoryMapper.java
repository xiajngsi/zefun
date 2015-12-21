package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.ProjectCategoryDto;
import com.zefun.web.entity.ProjectCategory;

/**
 * 项目类别
* @author 洪秋霞
* @date 2015年8月11日 上午11:02:31
 */
public interface ProjectCategoryMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:02:39
    * @param categoryId 类别id
    * @return int
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:02:53
    * @param projectCategory 项目类别
    * @return int
     */
    int insertSelective(ProjectCategory projectCategory);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:03:15
    * @param categoryId 类别id
    * @return ProjectCategory
     */
    ProjectCategory selectByPrimaryKey(Integer categoryId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:03:27
    * @param projectCategory 项目类别
    * @return int
     */
    int updateByPrimaryKeySelective(ProjectCategory projectCategory);

    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:03:40
    * @param projectCategory 项目类别
    * @return List<ProjectCategory>
     */
    List<ProjectCategory> selectByProperty(ProjectCategory projectCategory);
    
    /**
     * 获取部门，项目类别和项目列表
    * @author 洪秋霞
    * @date 2015年9月16日 上午10:35:18
    * @param storeId 门店Id
    * @return List<DeptInfoDto>
     */
    List<DeptInfoDto> getDetpInfoByProject(Integer storeId);
    
    /**
     * 根据门店标识、部门标识查询类别下存在项目的类别 
    * @author 王大爷
    * @date 2015年9月18日 上午11:17:59
    * @param projectCategory 项目类别
    * @return List<ProjectCategory>
     */
    List<ProjectCategoryDto> selectByProjectCategory(ProjectCategory projectCategory);
}