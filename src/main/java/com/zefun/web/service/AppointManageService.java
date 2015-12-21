package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.MemberAppointmentMapper;

/**
 * 预约管理服务类
* @author 张进军
* @date Nov 23, 2015 10:11:22 PM 
*/
@Service
public class AppointManageService {
    
    /**预约数据操作对象*/
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    
    /**
     * 查看预约列表
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param storeId    门店标识
    * @return   预约列表
     */
    public ModelAndView appointListView(int storeId){
        Page<AppointmentBaseDto> page = selectPageForAppointList(storeId, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView(View.SelfCashier.APPOINT_LIST);
        mav.addObject("page", page);
        return mav;
    }
    
    
    /**
     * 分页查询某个门店的预约信息
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param storeId    门店标识
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @return           预约列表
     */
    public BaseDto listAction(Integer storeId, int pageNo, int pageSize) {
        Page<AppointmentBaseDto> page = selectPageForAppointList(storeId, pageNo,
                pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    
    /**
     * 分页查询某个门店的预约信息
    * @author 张进军
    * @date Nov 23, 2015 10:17:35 PM
    * @param storeId    店铺标识
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @return Page<MemberLevel>
     */
    private Page<AppointmentBaseDto> selectPageForAppointList(Integer storeId,
            int pageNo, int pageSize) {
        Page<AppointmentBaseDto> page = new Page<AppointmentBaseDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<AppointmentBaseDto> list = memberAppointmentMapper.selectByPage(page);
        page.setResults(list);
        return page;
    }


    /**
     * 取消预约
    * @author 张进军
    * @date Nov 24, 2015 12:30:03 AM
    * @param appointmentId  预约标识
    * @return   成功返回码为0，失败为其它返回码
    */
    public BaseDto cancelAction(int appointmentId) {
        String curTime = DateUtil.getCurTime();
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setAppointmentId(appointmentId);
        memberAppointment.setCancelReason("PC员工操作");
        memberAppointment.setCancelTime(curTime);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(4);
        memberAppointmentMapper.updateByPrimaryKey(memberAppointment);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        
//        AppointmentBaseDto appointmentBaseDto = memberAppointmentMapper.selectAppointmentByAppointmentId(appointmentId);
//        String appointmentTime = appointmentBaseDto.getAppointmentDate() + " " + appointmentBaseDto.getAppointmentTime();
//        int employeeId = appointmentBaseDto.getEmployeeId();
//        int storeId = 0;
//        String projectName = appointmentBaseDto.getProjectInfo().getProjectName();
//        String reason = "";
//        return memberCenterService.cancelAppoinmentAction(appointmentBaseDto.getMemberId(), storeId, 
//        appointmentId, employeeId, projectName, appointmentTime, reason);
    }
    
}
