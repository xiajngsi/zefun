package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.ProjectCommissionDto;
import com.zefun.web.entity.ProjectCommission;

/**
 * 项目职位提成
* @author 洪秋霞
* @date 2015年8月11日 上午11:19:51
 */
public interface ProjectCommissionMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:20:22
    * @param commissionId 提成id
    * @return int
     */
    int deleteByPrimaryKey(Integer commissionId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:20:26
    * @param projectCommission 
    * @return int
     */
    int insertSelective(ProjectCommission projectCommission);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:20:57
    * @param commissionId 提成id
    * @return ProjectCommission
     */
    ProjectCommission selectByPrimaryKey(Integer commissionId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:21:18
    * @param projectCommission 项目职位提成
    * @return int
     */
    int updateByPrimaryKeySelective(ProjectCommission projectCommission);
    
    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月13日 上午11:10:58
    * @param projectCommission 项目职位提成
    * @return List<ProjectCommission>
     */
    List<ProjectCommission> selectByProperty(ProjectCommission projectCommission);
    
    /**
     * 批量插入
    * @author 洪秋霞
    * @date 2015年8月13日 上午10:52:00
    * @param projectCommissionList 项目职位提成列表
    * @return int
     */
    int insertProjectCommissionList(List<ProjectCommission> projectCommissionList);
    
    /**
     * 根据项目id查询提成职位列表
    * @author 洪秋霞
    * @date 2015年9月23日 下午8:20:31
    * @param projectId 项目id
    * @return List<ProjectCommission>
     */
    List<ProjectCommissionDto> selectByProjectId(Integer projectId);
    
    /**
     * 根据步骤标识查询项目对应的职位级别
    * @author 王大爷
    * @date 2015年10月31日 下午8:33:02
    * @param projectStepId 步骤标识
    * @return list
     */
    List<Integer> selectLevelIdList(Integer projectStepId);

    /**
     * 逻辑删除提成方式
    * @author 高国藩
    * @date 2015年12月5日 下午12:22:05
    * @param projectId   项目ID
    * @return            影响行数
     */
    int deleteByProjectId(Integer projectId);

    /**
     * 根据轮牌标识，及岗位list  查询锁存在的级别提成
    * @author 王大爷
    * @date 2015年12月5日 下午5:15:00
    * @param map 参数
    * @return List<EmployeeCommission>
     */
    List<ProjectCommission> selectByLevelIsExist(Map<String, Object> map);
}