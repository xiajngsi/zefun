package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.service.AttendanceRecordService;
import com.zefun.web.vo.AttendanceRecordVo;

/**
 * 员工设置：考勤记录控制器
 * 
 * @author lzc
 *
 */
@Controller
public class AttendanceRecordController extends BaseController {
	/** 员工设置：考勤记录服务 */
	@Autowired
	private AttendanceRecordService attendanceRecordService;

	/**
	 * 查询员工考勤记录
	 * 
	 * @param request 请求
	 * @param response 返回
	 * @param vo 考勤记录vo
	 * @return ModelAndView
	 */
	@RequestMapping(value = Url.AttendanceRecord.VIEW_ATTENDANCE_RECORD_HOME)
	public ModelAndView searchAttendanceRecordHome(HttpServletRequest request, HttpServletResponse response,
					AttendanceRecordVo vo) {
		vo.setStoreId(getStoreId(request));
		return attendanceRecordService.findAttendanceRecordSelective(request, response, vo);
	}

}
