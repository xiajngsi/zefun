package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.DeptMahjongDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.ProjectAppointDto;
import com.zefun.web.dto.ProjectInfoDto;
import com.zefun.web.dto.ProjectMahjongProjectStepDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ProjectInfo;

/**
 * 项目信息
* @author 洪秋霞
* @date 2015年8月11日 上午11:06:04
 */
public interface ProjectInfoMapper {
    
    /**
     * 根据项目标识查询项目信息，包括可预约的员工
    * @author 张进军
    * @date Oct 18, 2015 7:11:52 PM
    * @param projectId  项目标识
    * @return   项目详情
     */
    ProjectAppointDto selectProjectAppointByProjectId(int projectId);
    
    /**
     * 根据部门标识查询轮牌信息
    * @author 张进军
    * @date Oct 15, 2015 1:15:08 AM
    * @param deptId 部门标识
    * @return   部门下的轮牌信息
     */
    DeptMahjongDto selectDeptMahjongByDeptId(int deptId);
    
    /**
     * 根据部门标识查询项目信息
    * @author 张进军
    * @date Oct 15, 2015 12:02:09 AM
    * @param deptId    部门标识
    * @return   部门下的项目信息
     */
    DeptProjectBaseDto selectDeptProjectByDeptId(int deptId);
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:06:34
    * @param projectId 项目id
    * @return int
     */
    int deleteByPrimaryKey(Integer projectId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:09:35
    * @param projectInfo 项目信息
    * @return int
     */
    int insertSelective(ProjectInfo projectInfo);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:13:45
    * @param projectId 项目id
    * @return ProjectInfo
     */
    ProjectInfo selectByPrimaryKey(Integer projectId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:13:59
    * @param projectInfo 项目信息
    * @return int
     */
    int updateByPrimaryKeySelective(ProjectInfo projectInfo);

    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:14:23
    * @param projectInfo 项目信息
    * @return List<ProjectInfo>
     */
    List<ProjectInfo> selectByProperty(ProjectInfo projectInfo);
    
    /**
     * 查询分页
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:14:37
    * @param page 分页对象
    * @return List<ProjectInfoDto>
     */
    List<ProjectInfoDto> selectProjectInfoDtoPage(Page<ProjectInfoDto> page);
    
    /**
     * 动态查询dto
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:14:52
    * @param projectInfoDto 项目信息dto
    * @return List<ProjectInfoDto>
     */
    List<ProjectInfoDto> selectProjectInfoDto(ProjectInfoDto projectInfoDto);
    
    /**
     * 根据部门标识查询项目且按销售次数排序
    * @author 王大爷
    * @date 2015年9月23日 下午2:32:18
    * @param deptId 部门标识
    * @return List<ProjectInfo>
     */
    List<ProjectInfo> selectDeptIdOrder(Integer deptId);
    
    /**
     * 根据门店id查询项目列表
    * @author 洪秋霞
    * @date 2015年9月29日 下午5:23:36
    * @param storeId 门店id
    * @return List<ProjectInfo>
     */
    List<ProjectInfo> selectByStoreId(Integer storeId);

    /**
     * 删除类别下的所有项目
    * @author 高国藩
    * @date 2015年10月27日 下午6:35:08
    * @param categoryId 累呗ID
    * @return 影响行数
     */
    int deleteByCategory(Integer categoryId);
    
    /**
     * 根据项目查询步骤及轮牌员工
    * @author 王大爷
    * @date 2015年11月24日 下午12:05:06
    * @param projectId 项目标识
    * @return ProjectMahjongProjectStepDto
     */
    ProjectMahjongProjectStepDto selectByProjectId(Integer projectId);
}