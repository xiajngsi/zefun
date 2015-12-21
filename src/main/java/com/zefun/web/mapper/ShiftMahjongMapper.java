package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.entity.ShiftMahjong;

/**
 * 轮牌信息mapper
* @author 王大爷
* @date 2015年8月11日 上午10:44:19
 */
public interface ShiftMahjongMapper {
    
    /**
     * 删除轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:43:48
    * @param shiftMahjongId 轮牌信息ID
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer shiftMahjongId);

    /**
     * 插入轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:43:04
    * @param record 轮牌信息
    * @return 是否成功
     */
    int insert(ShiftMahjong record);

    /**
     * 插入存在属性轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:42:25
    * @param record 轮牌信息
    * @return 是否成功
     */
    int insertSelective(ShiftMahjong record);

    /**
     * 通过轮牌信息ID查询轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:41:42
    * @param shiftMahjongId 轮牌信息ID
    * @return 轮牌信息
     */
    ShiftMahjong selectByPrimaryKey(Integer shiftMahjongId);
    
    /**
     * 通过轮牌信息ID查询轮牌信息Dto
    * @author 王大爷
    * @date 2015年8月11日 上午10:41:42
    * @param shiftMahjongId 轮牌信息ID
    * @return 轮牌信息
     */
    ShiftMahjongDto selectByShiftMahjongIdDto(Integer shiftMahjongId);

    /**
     * 修改已存在的轮牌属性
    * @author 王大爷
    * @date 2015年8月11日 上午10:40:56
    * @param record 轮牌信息
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(ShiftMahjong record);

    /**
     * 整体修改轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:40:16
    * @param record 轮牌信息
    * @return 是否成功
     */
    int updateByPrimaryKey(ShiftMahjong record);
    
    /**
     * 根据部门标识查询轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:39:25
    * @param deptId 部门标识
    * @return 轮牌信息集合
     */
    List<ShiftMahjongDto> selectByDeptId(Integer deptId);
    
    /**
     * 根据门店标识查询轮牌信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:39:25
    * @param storeId 门店标识
    * @return 轮牌信息集合
     */
    List<ShiftMahjongDto> selectByStoreId(Integer storeId);
    
    /**
     * 根据轮牌员工信息标识查询轮牌信息
    * @author 王大爷
    * @date 2015年9月22日 上午10:48:05
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @return ShiftMahjong
     */
    ShiftMahjong selectByShiftMahjongEmployeeId(Integer shiftMahjongEmployeeId);
    
    /**
     * 根据项目标识查询该项目对应排位，并将排位名称修改为步骤名称
    * @author 王大爷
    * @date 2015年10月19日 下午12:06:20
    * @param projectId 项目标识
    * @return List<ShiftMahjong>
     */
    List<ShiftMahjong> selectByProjectId(Integer projectId);
    
    /**
     * 根据员工标识查询轮牌牌DTO
    * @author 王大爷
    * @date 2015年11月23日 上午10:26:25
    * @param employeeId 员工标识
    * @return List<ShiftMahjongDto>
     */
    List<ShiftMahjongDto> selectByEmployeeId(Integer employeeId);
    
    /**
     * 根据岗位标识查询轮牌信息
    * @author 王大爷
    * @date 2015年11月27日 上午11:02:56
    * @param positionId 岗位标识
    * @return List<ShiftMahjong>
     */
    List<ShiftMahjong> selectByPositionId(Integer positionId);
}