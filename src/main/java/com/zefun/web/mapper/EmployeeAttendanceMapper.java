package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.AttendanceRecordDto;
import com.zefun.web.entity.EmployeeAttendance;
import com.zefun.web.entity.Page;

/**
 * 员工考勤操作对象
* @author 张进军
* @date Dec 6, 2015 12:07:41 PM
 */
public interface EmployeeAttendanceMapper {
    
    /**
     * 根据考勤标识删除考勤记录
    * @author 张进军
    * @date Dec 6, 2015 12:07:53 PM
    * @param recordId   考勤标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer recordId);

    
    /**
     * 新增考勤
    * @author 张进军
    * @date Dec 6, 2015 12:08:26 PM
    * @param record 考勤信息
    * @return   0:失败，1:成功
     */
    int insert(EmployeeAttendance record);

    /**
     * 根据考勤标识查询考勤信息
    * @author 张进军
    * @date Dec 6, 2015 12:08:41 PM
    * @param recordId   考勤标识
    * @return   考勤信息
     */
    EmployeeAttendance selectByPrimaryKey(Integer recordId);

    
    /**
     * 修改考勤信息
    * @author 张进军
    * @date Dec 6, 2015 12:09:01 PM
    * @param record 考勤信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(EmployeeAttendance record);
    
    /**
     * 员工签退
    * @author 张进军
    * @date Dec 6, 2015 2:21:57 PM
    * @param record 签退信息
    * @return   0:失败，1:成功
     */
    int updateSignoutByEmployeeId(EmployeeAttendance record);
    
    /**
     * 根据员工标识查询当日考勤情况
    * @author 张进军
    * @date Dec 6, 2015 1:04:56 PM
    * @param employeeId 员工标识
    * @return   考勤情况
     */
    EmployeeAttendance selectByEmployeeId(int employeeId);
    
    /**
     * 根据条件查询员工出勤记录
     * @author lzc
     * @param page 页查询条件
     * @return 员工考勤集
     */
    List<AttendanceRecordDto> selectAttendanceRecordSelective(Page<AttendanceRecordDto> page);
    
    
}