package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.ShiftMahjongEmployee;

/**
 * 轮牌mapper
* @author 王大爷
* @date 2015年8月11日 上午10:38:41
 */
public interface ShiftMahjongEmployeeMapper {
    
    /**
     * 通过轮牌员工信息ID删除轮牌员工信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:27:56
    * @param shiftMahjongEmployeeId 轮牌员工信息ID
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer shiftMahjongEmployeeId);

    /**
     * 插入单个轮牌员工信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:33:25
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int insert(ShiftMahjongEmployee record);

    /**
     * 只保存存在的属性
    * @author 王大爷
    * @date 2015年8月11日 上午10:36:14
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int insertSelective(ShiftMahjongEmployee record);

    /**
     * 通过轮牌员工信息ID查询轮牌员工信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:37:47
    * @param shiftMahjongEmployeeId 轮牌员工信息ID
    * @return 轮牌员工信息
     */
    ShiftMahjongEmployee selectByPrimaryKey(Integer shiftMahjongEmployeeId);

    /**
     * 只修改存在的属性
    * @author 王大爷
    * @date 2015年8月11日 上午10:25:09
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(ShiftMahjongEmployee record);

    /**
     * 整体修改轮牌员工信息
    * @author 王大爷
    * @date 2015年8月11日 上午10:24:12
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByPrimaryKey(ShiftMahjongEmployee record);
    
    /**
     * 修改是否打卡(打卡上牌)
    * @author 王大爷
    * @date 2015年11月9日 上午10:47:16
    * @param map 参数
    * @return 是否成功
     */
    Integer updateIsPunchCard(Map<String, Object> map);
    
    /**
     * 通过轮牌信息ID查询轮牌员工信息ID
    * @author 王大爷
    * @date 2015年8月11日 上午10:19:47
    * @param shiftMahjongId 轮牌信息ID
    * @return 轮牌员工信息结合
     */
    List<ShiftMahjongEmployee> selectByShiftMahjongId(Integer shiftMahjongId);
    
    /**
     * 批量插入轮牌员工信息表数据
    * @author 王大爷
    * @date 2015年8月11日 上午10:18:29
    * @param list ShiftMahjongEmployee的集合
    * @return 是否成功
     */
    int insertShiftMahjongEmployeeList(List<ShiftMahjongEmployee> list);
    
    /**
     * 查询该轮牌已实用的级别
    * @author 王大爷
    * @date 2015年8月11日 上午10:16:27
    * @param shiftMahjongId 轮牌信息ID
    * @return 轮牌中已经使用的级别ID
     */
    List<Integer> selectByPositionIdList(Integer shiftMahjongId);
    
    /**
     * 查询该轮牌已实用的级别,上牌方式
    * @author 王大爷
    * @date 2015年8月11日 上午10:16:27
    * @param shiftMahjongId 轮牌信息ID
    * @return 轮牌中已经使用的级别ID
     */
    List<Map<String, Integer>> selectByPositionIdMap(Integer shiftMahjongId);
    
    /**
     * 根据轮牌信息ID删除轮牌员工信息表
    * @author 王大爷
    * @date 2015年8月11日 上午10:13:28
    * @param shiftMahjongId 轮牌信息ID
    * @return 是否成功
     */
    int deleteByShiftMahjongId(Integer shiftMahjongId);
    
    /**
     * 点上移时修改排序本身
    * @author 王大爷
    * @date 2015年8月13日 上午11:20:28
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByUpward(ShiftMahjongEmployee record);
    
    /**
     * 点上移时修改排序
    * @author 王大爷
    * @date 2015年8月13日 上午11:20:28
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByUpwardOther(ShiftMahjongEmployee record);
    
    /**
     * 当为第一位时执行
    * @author 王大爷
    * @date 2015年8月13日 下午3:34:55
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByUpwardAll(ShiftMahjongEmployee record);
    
    /**
     * 当为第一位时执行，（当状态改为工作中、指定工作、）
    * @author 王大爷
    * @date 2015年8月13日 下午3:34:55
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByUpwardAllCount(ShiftMahjongEmployee record);
    
    /**
     * 根据接单轮牌员工标识将其后所有员工往前移一位
    * @author 王大爷
    * @date 2015年9月22日 上午11:18:20
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByToEnd(ShiftMahjongEmployee record);
    
    /**
     * 点下移时修改排序本身
    * @author 王大爷
    * @date 2015年8月13日 上午11:20:28
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByNext(ShiftMahjongEmployee record);
    
    /**
     * 点下移时修改排序
    * @author 王大爷
    * @date 2015年8月13日 上午11:20:28
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByNextOther(ShiftMahjongEmployee record);
    
    /**
     * 当在最后面时
    * @author 王大爷
    * @date 2015年8月13日 下午3:48:41
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByNextdAll(ShiftMahjongEmployee record);
    
    /**
     * 当在最后面时
    * @author 王大爷
    * @date 2015年8月13日 下午3:48:41
    * @param record 轮牌员工信息
    * @return 是否成功
     */
    int updateByNextAllCount(ShiftMahjongEmployee record);
    
    /**
     * 根据轮牌信息ID查询轮牌员工信息记录条数
    * @author 王大爷
    * @date 2015年8月13日 下午3:59:13
    * @param shiftMahjongId 轮牌信息ID
    * @return 是否成功
     */
    Integer selectByCount(Integer shiftMahjongId);
    
    /**
     * 员工轮牌对应轮牌所有员工
    * @author 王大爷
    * @date 2015年8月19日 下午2:29:38
    * @param employeesId 当前登录员工id
    * @return 员工轮牌对应轮牌所有员工
     */
    List<Integer> selectByEmployeesId(Integer employeesId);
    
    /**
     * 查询所须修改资料员工对应的轮牌员工表标识
    * @author 王大爷
    * @date 2015年9月8日 下午4:47:50
    * @param employeesId 员工标识
    * @return List<ShiftMahjongEmployee>
     */
    List<ShiftMahjongEmployee> selectShiftMahjongEmployeeList(Integer employeesId);
    
    /**
     * 查询该级别对应轮牌信息标识
    * @author 王大爷
    * @date 2015年9月8日 下午5:12:34
    * @param levelId 级别标识
    * @return List<Integer>
     */
    List<Integer> selectShiftMahjongIdList(Integer levelId);
    
    /**
     * 根据项目标识查询轮牌中对应员工
    * @author 王大爷
    * @date 2015年9月19日 下午5:37:11
    * @param projectId 项目标识
    * @return List<ShiftMahjongEmployee>
     */
    /*List<ShiftMahjongEmployee> selectShiftMahjongEmployeeList(Integer projectId);*/
    
    /**
     * 根据项目标识和步骤查询对应轮牌信息
    * @author 王大爷
    * @date 2015年9月21日 下午5:49:20
    * @param map map
    * @return List<ShiftMahjongEmployee>
     */
    List<ShiftMahjongEmployee> selectShiftEmployeeList(Map<String, Integer> map);
    
    /**
     * 根据项目标识和步骤、员工标识查询对应轮牌信息
    * @author 王大爷
    * @date 2015年11月4日 下午2:45:51
    * @param map map
    * @return ShiftMahjongEmployee
     */
    ShiftMahjongEmployee selectShiftEmployeeListByEmployeeId(Map<String, Integer> map);
    
    /**
     * 根据轮牌标识查询会员等级
    * @author 洪秋霞
    * @date 2015年9月21日 下午2:01:46
    * @param shiftMahjongIds 多个轮牌标识
    * @return List<Integer>
     */
    List<Integer> selectShiftMahjongByLevelId(List<Integer> shiftMahjongIds);
    
    /**
     * 根据轮牌项目步骤关系标识查询轮牌员工（满足项目中设定级别的排到第一个）
    * @author 王大爷
    * @date 2015年9月28日 下午4:37:01
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return ShiftMahjongEmployee
     */
    ShiftMahjongEmployee selectShiftMahjongOneEmployee(Integer shiftMahjongStepId);
    
    /**
     * 根据轮牌项目步骤标识，查出上一个轮牌员工
    * @author 王大爷
    * @date 2015年10月20日 下午6:55:47
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return 轮牌员工
     */
    ShiftMahjongEmployee selectShiftMahjongLastEmployee(Integer shiftMahjongStepId);
    
    /**
     * 根据轮牌项目步骤标识，查出该轮牌员工
    * @author 王大爷
    * @date 2015年10月20日 下午6:55:47
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return 轮牌员工
     */
    ShiftMahjongEmployee selectShiftMahjongEmployee(Integer shiftMahjongStepId);
    
    /**
     * 修改指定人数(加)
    * @author 王大爷
    * @date 2015年10月26日 下午5:54:11
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @return 是否成功
     */
    Integer updateAddAppointNumber(Integer shiftMahjongEmployeeId);
    
    /**
     * 修改指定人数（减）
    * @author 王大爷
    * @date 2015年10月26日 下午5:54:11
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @return 是否成功
     */
    Integer updateDecreaseAppointNumber(Integer shiftMahjongEmployeeId);
    
    /**
     * 根据岗位标识和轮牌标识，查出所属岗位下轮牌员工
    * @author 王大爷
    * @date 2015年10月30日 下午2:40:24
    * @param map 岗位标识和轮牌标识
    * @return List<ShiftMahjongEmployee>
     */
    List<ShiftMahjongEmployee> selectByPositionId(Map<String, Integer> map);
    
    /**
     * 根据岗位标识和轮牌标识，查出所属岗位下服务轮牌员工
    * @author 王大爷
    * @date 2015年10月30日 下午2:40:24
    * @param map 岗位标识和轮牌标识
    * @return List<ShiftMahjongEmployee>
     */
    List<ShiftMahjongEmployee> selectByPositionIdExistsWork(Map<String, Object> map);
    
    /**
     * 根据轮牌标识及项目标识，查处对应员工
    * @author 王大爷
    * @date 2015年11月24日 上午11:43:57
    * @param map 参数
    * @return List<ShiftMahjongEmployee>
     */
    List<ShiftMahjongEmployee> selectByShiftMahjongProjectId(Map<String, Integer> map);
}