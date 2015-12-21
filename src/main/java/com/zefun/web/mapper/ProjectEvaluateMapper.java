package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ProjectEvaluateDto;
import com.zefun.web.entity.ProjectEvaluate;

/**
 * 项目评价信息操作类
* @author 张进军
* @date Nov 13, 2015 3:27:46 PM
 */
public interface ProjectEvaluateMapper {
    /**
     * 新增项目评价
    * @author 张进军
    * @date Nov 13, 2015 3:27:55 PM
    * @param record     项目评价信息
    * @return   0:失败，1:成功
     */
    int insert(ProjectEvaluate record);

    /**
     * 根据评价标识查询评价信息
    * @author 张进军
    * @date Nov 13, 2015 3:28:25 PM
    * @param id     评价标识
    * @return   评价信息
     */
    ProjectEvaluate selectByPrimaryKey(Integer id);

    /**
     * 根据评价标识修改评价信息
    * @author 张进军
    * @date Nov 13, 2015 3:28:53 PM
    * @param record     评价信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(ProjectEvaluate record);
    
    /**
     * 批量增加评价信息
    * @author 张进军
    * @date Nov 13, 2015 3:34:09 PM
    * @param list   评价信息列表
    * @return   0:失败，成功条数
     */
    int insertBatch(List<ProjectEvaluate> list);
    
    /**
     * 根据项目标识查询评价列表
    * @author 张进军
    * @date Nov 28, 2015 10:32:42 AM
    * @param projectId  项目标识
    * @return   评价列表
     */
    List<ProjectEvaluateDto> selectListByProjectId(int projectId);
}