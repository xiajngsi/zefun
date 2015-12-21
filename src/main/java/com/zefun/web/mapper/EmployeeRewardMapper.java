package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.EmployeeRewardDto;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.Page;

/**
 * 员工奖惩映射
 * @author lzc
 *
 */
public interface EmployeeRewardMapper {
	/**
	 * 删除根据主键
	 * @param rewardId 主键
	 * @return 成功数目
	 */
    int deleteByPrimaryKey(Integer rewardId);

    /**
     * 插入
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int insert(EmployeeReward record);

    /**
     * 选择性插入
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int insertSelective(EmployeeReward record);

    /**
     * 根据主键查询
     * @param rewardId 主键
     * @return 员工奖惩实体
     */
    EmployeeReward selectByPrimaryKey(Integer rewardId);

    /**
     * 根据主键选择性更新
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int updateByPrimaryKeySelective(EmployeeReward record);

    /**
     * 根据主键更新
     * @param record 员工奖惩实体
     * @return 成功数目
     */
    int updateByPrimaryKey(EmployeeReward record);
    
    /**
     * 根据员工id和类型分组查询奖惩记录汇总
     * @param page 页查询条件
     * @return  奖惩集EmployeeRewardDto
     */
    List<EmployeeRewardDto> selectCountRewardByPage(Page<EmployeeRewardDto> page);
    
    /**
     * 分页查询奖惩详细
     * @param page  页查询条件
     * @return  奖惩集EmployeeReward
     */
    List<EmployeeReward> selectRewardByPage(Page<EmployeeReward> page);
}