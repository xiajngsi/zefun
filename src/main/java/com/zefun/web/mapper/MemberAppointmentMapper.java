package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.Page;

/**
 * 会员预约对象
* @author 张进军
* @date Oct 19, 2015 4:39:28 PM
 */
public interface MemberAppointmentMapper {
    /**
     * 根据预约标识查询预约记录
    * @author 张进军
    * @date Oct 19, 2015 4:39:42 PM
    * @param appointmentId  预约标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer appointmentId);

    /**
     * 插入预约记录
    * @author 张进军
    * @date Oct 19, 2015 4:40:09 PM
    * @param record 预约记录
    * @return   0:失败，1:成功
     */
    int insert(MemberAppointment record);

    /**
     * 根据预约标识查询预约记录
    * @author 张进军
    * @date Oct 19, 2015 4:40:54 PM
    * @param appointmentId  预约标识
    * @return   预约记录
     */
    MemberAppointment selectByPrimaryKey(Integer appointmentId);

    /**
     * 根据预约标识修改预约记录
    * @author 张进军
    * @date Oct 19, 2015 4:40:40 PM
    * @param record 预约记录
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(MemberAppointment record);
    
    /**
     * 根据员工标识查询已预约的日期、时间
    * @author 张进军
    * @date Oct 19, 2015 5:12:29 PM
    * @param employeeId 员工标识
    * @return   日期、时间集合
     */
    List<String> selectAppointDateByEmployee(int employeeId);
    
    /**
     * 根据会员标识查询预约列表
    * @author 张进军
    * @date Oct 21, 2015 10:33:37 PM
    * @param memberId   会员标识
    * @return   预约列表
     */
    List<AppointmentBaseDto> selectAppointmentByMemberId(int memberId);
    
    /**
     * 查询员工的预约列表
    * @author 张进军
    * @date Oct 28, 2015 9:10:18 PM
    * @param map    员工标识、预约状态
    * @return   预约列表
     */
    List<AppointmentBaseDto> selectAppointmentByEmployeeId(Map<String, Object> map);
    
    /**
     * 根据预约标识查询预约信息
    * @author 张进军
    * @date Nov 4, 2015 10:12:54 AM
    * @param appointmentId  预约标识
    * @return   预约信息
     */
    AppointmentBaseDto selectAppointmentByAppointmentId(int appointmentId);

    /**
     * 分页查询预约信息
    * @author 张进军
    * @date Nov 23, 2015 10:40:45 PM
    * @param page   页码、查询条件
    * @return   具体页码的预约列表
    */
    List<AppointmentBaseDto> selectByPage(Page<AppointmentBaseDto> page);
}