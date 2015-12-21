package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.Page;
/**
 * 
* @author chendb
* @date 2015年8月11日 上午10:49:47
 */
public interface EmployeeInfoMapper {
    /**
     * 新增员工资料
    * @author 张进军
    * @date Nov 20, 2015 7:49:06 PM
    * @param employeeInfo   员工资料
    * @return   0:失败，1:成功
     */
    int insertSelective(EmployeeInfo employeeInfo);
    
    /**
     * 修改员工资料
    * @author 张进军
    * @date Nov 20, 2015 7:51:50 PM
    * @param employeeInfo   员工资料
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKeySelective(EmployeeInfo employeeInfo);
    
    /**
     * 删除功能
    * @author chendb
    * @date 2015年8月11日 上午10:49:58
    * @param employeeId 员工标识
    * @return int
     */
    int deleteByPrimaryKey(Integer employeeId);
    /**
     * 
    * @author chendb
    * @date 2015年8月11日 上午10:50:20
    * @param map 参数
    * @return int
     */
    int insert(EmployeeDto map);
    /*int insert(Map<String, Object> map);*/
    /**
     * 
    * @author chendb
    * @date 2015年8月11日 上午10:50:33
    * @param employeeId 员工标识
    * @return EmployeeInfo
     */
    EmployeeInfo selectByPrimaryKey(Integer employeeId);
    /**
     * 
    * @author chendb
    * @date 2015年8月11日 上午10:50:38
    * @param employeeDto bean
    * @return int
     */
    int updateByPrimaryKey(EmployeeDto employeeDto);
    /**
     * 查询员工信息列表
    * @author chendb
    * @date 2015年8月11日 上午10:50:56
    * @param page 参数
    * @return List<EmployeeDto>
     */
    List<EmployeeDto> queryEmployeeInfo(Page<EmployeeDto> page);
    /**
     * 判断员工编码是否被引用了
    * @author 陈端斌
    * @date 2015年8月10日 下午5:01:33
    * @param map 参数
    * @return EmployeeInfo
     */ 
    EmployeeInfo queryEmployeeCode(Map<String, Object> map);
    /**
     * 根据职位id查询出员工信息
    * @author laowang
    * @date 2015年8月8日 下午5:44:27
    * @param map 参数
    * @return List<EmployeeInfo>
     */
    List<EmployeeInfo> selectByLevelId(Map<String, Object> map);
    
    /**
     * 获取推荐人
    * @author laowang
    * @date 2015年8月8日 下午5:44:27
    * @param storeId 参数
    * @return List<EmployeeInfo>
     */
    List<EmployeeInfo> getRecommendlist(Integer storeId);
    /**
     * 人员关系录入
    * @author chendb
    * @date 2015年8月12日 上午10:29:15
    * @param map 参数
    * @return int
     */
    int employeeRecommend(Map<String, Object> map);
    /**
     * 删除人员关系
    * @author chendb
    * @date 2015年8月14日 下午4:09:43
    * @param employeeId 员工标识
    * @return int
     */
    int deleteRecommend(Integer employeeId);
    /**
     * 新增培训信息
    * @author chendb
    * @date 2015年8月12日 下午6:30:16
    * @param map 参数
    * @return int
     */
    int addpx(Map<String, Object> map);
    /**
     * 获取员工培训信息
    * @author chendb
    * @date 2015年8月17日 上午10:18:06
    * @param employeeId 员工标识
    * @return List<Map<String, Object>>
     */
    List<Map<String, Object>>querypx(Integer employeeId);
    /**
     * 删除员工之前的培训信息
    * @author chendb
    * @date 2015年8月12日 下午6:36:15
    * @param employeeId 员工标识
    * @return int
     */
    int deletepx(Integer employeeId);
    /**
     * 工作信息查询
    * @author chendb
    * @date 2015年8月17日 上午11:48:44
    * @param employeeId 员工标识
    * @return List<Map<String, Object>>
     */
    List<Map<String, Object>>querygz(Integer employeeId);
    /**
     * 新增工作信息
    * @author chendb
    * @date 2015年8月13日 上午9:46:52
    * @param map 参数
    * @return int
     */
    int addgz(Map<String, Object> map);
    
    /**
     * 删除员工之前的工作信息
    * @author chendb
    * @date 2015年8月13日 上午9:47:30
    * @param employeeId 员工标识
    * @return int
     */
    int deletegz(Integer employeeId);
    /**
     * 擅长信息获取
    * @author chendb
    * @date 2015年8月17日 下午2:35:27
    * @param employeeId 员工信息
    * @return List<Map<String, Object>>
     */
    List<Map<String, Object>>querysc(Integer employeeId);
    /**
     * 新增擅长信息
    * @author chendb
    * @date 2015年8月13日 下午2:10:15
    * @param map 参数
    * @return int
     */
    int addsc(Map<String, Object> map);
    /**
     * 删除擅长信息
    * @author chendb
    * @date 2015年8月13日 下午2:10:41
    * @param employeeId 员工标识
    * @return int
     */
    int deletesc(Integer employeeId);
    /**
     * 根据员工标识获取员工详情信息
    * @author chendb
    * @date 2015年8月14日 上午11:02:24
    * @param employeeId 员工标识
    * @return List<Map<String, Object>>
     */
    Map<String, Object> getDetail(Integer employeeId);
    /**
     * 获取推荐人信息
    * @author chendb
    * @date 2015年8月19日 下午2:06:30
    * @param employeeId 员工标识
    * @return Map<String, Object>
     */
    Map<String, Object>getreferrer(Integer employeeId); 
    /**
     * 获取被推荐人信息
    * @author chendb
    * @date 2015年8月19日 下午2:06:30
    * @param employeeId 员工标识
    * @return Map<String, Object>
     */
    List<Map<String, Object>>getrecommended(Integer employeeId); 
    
    /**
     * 通过员工id获取员工基本信息
    * @author 王大爷
    * @date 2015年9月1日 下午8:20:55
    * @param employeeId 员工id
    * @return EmployeeDto
     */
    EmployeeDto selectEmployeeBaseInfo(Integer employeeId);
    /**
     * 根据部门标识获取
    * @author chendb
    * @date 2015年9月11日 下午3:16:16
    * @param deptId 部门标识
    * @return List<EmployeeDto>
     */
    List<EmployeeDto> getDeptEmployee(Integer deptId);
    
    /**
     * 根据门店标识查询门店下所有员工信息
    * @author 王大爷
    * @date 2015年9月8日 下午8:56:27
    * @param storeId 门店标识
    * @return List<EmployeeInfo>
     */
    List<EmployeeInfo> selectEmployeeList(Integer storeId);
    /**
     * 获取所有的人员（在职 未删除）
    * @author chendb
    * @date 2015年9月22日 上午9:10:56
    * @return List<Map<String, Object>>
     */
    List<Map<String, Object>>getAllemployee();
    /**
     * 新增派遣
    * @author chendb
    * @date 2015年9月24日 下午4:26:58
    * @param map 参数
    * @return int
     */
    int adddispatch(Map<String, Object>map);
    
    /**
     * 获取员工派遣信息
    * @author chendb
    * @date 2015年9月24日 下午5:29:02
    * @param employeeId 员工表示
    * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getpqlist(Integer employeeId);
    /**
     * 修改人员状态
    * @author chendb
    * @date 2015年9月25日 上午10:15:02
    * @param map 参数
    * @return int
     */
    int updatestatus(Map<String, Object>map);
    
    /**
     * 导出功能
    * @author chendb
    * @date 2015年10月8日 下午4:02:46
    * @param map 参数
    * @return List<EmployeeDto>
     */
    List<EmployeeDto>queryEmployeeList(Map<String, Object>map);
    /**
     * 删除员工数据  改状态
    * @author chendb
    * @date 2015年10月8日 下午5:38:28
    * @param employeeId 员工标识
    * @return int
     */
    int deleteEmployee(Integer employeeId);
    
    /**
     * 删除账户
    * @author chendb
    * @date 2015年10月15日 上午11:22:39
    * @param employeeId 员工标识
    * @return int
     */
    int deleteUser(Integer employeeId);
    
    /**
     * 批量新增
    * @author chendb
    * @date 2015年10月17日 上午11:05:58
    * @param Dto Dto
    * @return int
     */
    int insertList(EmployeeDto Dto);
    /**
     * 
    * @author chendb
    * @date 2015年10月21日 上午10:04:59
    * @param map 参数
    * @return EmployeeDto
     */
    EmployeeDto getEmployeeDetail(Map<String, Object> map);
    
    /**
     * 根据员工标识查询员工基本信息
    * @author 张进军
    * @date Oct 24, 2015 5:46:34 PM
    * @param employeeId     员工标识
    * @return   员工信息
     */
    EmployeeBaseDto selectBaseInfoByEmployeeId(int employeeId);
    
    /**
     * 根据门店查询员工列表
    * @author 张进军
    * @date Oct 31, 2015 2:36:20 PM
    * @param storeId    门店标识
    * @return   员工列表
     */
    List<EmployeeBaseDto> selectEmployeeListByStoreId(int storeId);
    
    /**
     * 根据员工ID列表查询员工信息
    * @author 张进军
    * @date Oct 31, 2015 2:44:59 PM
    * @param list   员工ID列表
    * @return   员工列表
     */
    List<EmployeeBaseDto> selectEmployeeListByList(List<String> list);
    
    /**
     * 根据员工标识查询该员工对应岗位下的所有员工
    * @author 王大爷
    * @date 2015年11月19日 下午12:00:25
    * @param employeeId 员工标识
    * @return List<EmployeeInfo>
     */
    List<Integer> selectEmployeeInfoByEmployeeIdPositionId(Integer employeeId);
}