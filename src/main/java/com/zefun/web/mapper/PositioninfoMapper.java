package com.zefun.web.mapper;

import java.util.List;



import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.PositionInfoDto;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.PositionInfo;

/**
 * 岗位信息mapper
* @author 陈端斌
* @date 2015年8月4日 下午5:13:49 
*
 */
public interface PositioninfoMapper {
	/**
	 * 新增岗位信息
	* @author 陈端斌
	* @date 2015年8月4日 下午8:12:27
	* @param record bean
	* @return int
	 */
    int insert(PositionInfo record);
	/**
	 * 修改岗位信息
	* @author 陈端斌
	* @date 2015年8月4日 下午8:12:27
	* @param record bean
	* @return int
	 */
    int updateByPrimaryKeySelective(PositionInfo record);
    
    /**
     * 
    * @author 王大爷
    * @date 2015年11月27日 上午10:28:34
    * @param positionId 岗位标识
    * @return PositionInfo
     */
    PositionInfo selectByPrimaryKey(Integer positionId);
    
    /**
     * 查询岗位列表信息
    * @author 陈端斌
    * @date 2015年8月4日 下午8:12:02
    * @param page 参数
    * @return List<PositionInfo>
     */
    List<PositionInfo>querypositioninfo(Page<PositionInfo> page);
    /**
     * 判断岗位或者编码是否已经存在
    * @author 陈端斌
    * @date 2015年8月5日 上午9:58:41
    * @param record bean
    * @return List<PositionInfo>
     */
    List<PositionInfo>isPositionName(PositionInfo record);
    /**
     * 判断岗位或者编码是否已经存在
    * @author 陈端斌
    * @date 2015年8月5日 上午9:58:41
    * @param record bean
    * @return List<PositionInfo>
     */
    List<PositionInfo>isPositionCode(PositionInfo record);

    /**
     * 下拉框获取岗位（公共接口）
    * @author 陈端斌
    * @date 2015年8月5日 上午10:30:46
    * @param record bean
    * @return List<PositionInfo>
     */
    List<PositionInfo>queryposition(PositionInfo record);
    
    /**
     * 删除岗位信息
    * @author 陈端斌
    * @date 2015年8月5日 上午10:50:00
    * @param record bean
    * @return int
     */
    int deleteposition(PositionInfo record);
    /**
     * 删除岗位信息之前先判断职位是否有引用该岗位
    * @author 陈端斌
    * @date 2015年8月5日 上午11:02:27
    * @param record bean
    * @return int
     */
    int isemployeelevel(PositionInfo record);
    
    /**
     * 根据id获取详情
    * @author 陈端斌
    * @date 2015年8月10日 下午2:12:37
    * @param record bean
    * @return PositionInfo
     */
    PositionInfo positiondetail(PositionInfo record);
    
    /**
     * 获取岗位和职位信息列表
    * @author chendb
    * @date 2015年8月24日 下午4:00:40
    * @param storeId 门店标识
    * @return List<PositionInfoDto>
     */
    List<DeptInfoDto>getDetpInfo(Integer storeId);
    
    /**
     * 
    * @author chendb
    * @date 2015年9月8日 下午1:40:29
    * @param deptId 部门标识
    * @return List<PositionInfoDto>
     */
    List<PositionInfoDto> getpositionInfo(Integer deptId);
    
    /**
     * 根据部门查询岗位信息 （跨部门）
    * @author 王大爷
    * @date 2015年11月6日 下午2:47:55
    * @param deptId 部门标识
    * @return List<PositionInfoDto>
     */
    List<PositionInfoDto> getStridePositionInfo(Integer deptId);
    /**
     * 根据岗位标识获取职位
    * @author chendb
    * @date 2015年9月8日 下午1:41:50
    * @param positionId 岗位标识
    * @return List<EmployeeLevel>
     */
    List<EmployeeLevel> positionInfoSon(Integer positionId);
    
    /**
     * 获取岗位下所有级别的标识
    * @author 王大爷
    * @date 2015年9月8日 下午3:27:46
    * @param positionId 岗位标识
    * @return List<Integer>
     */
    List<Integer> getlevelIdList(Integer positionId);
    /**
     * 根据部门标识和岗位名称判断是否存在
    * @author chendb
    * @date 2015年10月14日 上午10:14:39
    * @param positionInfo 参数
    * @return PositionInfo
     */
    PositionInfo queryPositiondetail(PositionInfo positionInfo);
}