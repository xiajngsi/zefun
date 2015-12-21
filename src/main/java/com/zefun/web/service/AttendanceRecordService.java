package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.AttendanceRecordDto;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.vo.AttendanceRecordVo;

/**
 * 员工设置：考勤记录服务
 * @author lzc
 *
 */
@Service
public class AttendanceRecordService {
	
	/** 员工考勤映射 */
	@Autowired
	private EmployeeAttendanceMapper employeeAttendanceMapper;
	
	/**
	 * 查询员工考勤记录
	 * @param request  请求
	 * @param response  响应
	 * @param vo  考勤查询条件
	 * @return  ModelAndView
	 */
	public ModelAndView findAttendanceRecordSelective(HttpServletRequest request, HttpServletResponse response,
					AttendanceRecordVo vo) {
		Page<AttendanceRecordDto> page = findByPage(1, App.System.API_DEFAULT_PAGE_SIZE, vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.setViewName(View.AttendanceRecord.HOME);
		return mav;
	}
	
	/**
	 * 员工考勤记录分页查询
	 * @param pageNo    页码
	 * @param pageSize  页容量
	 * @param vo  考勤查询条件
	 * @return Page<AttendanceRecordDto>
	 */
	private Page<AttendanceRecordDto> findByPage(int pageNo, int pageSize, AttendanceRecordVo vo) {
		Page<AttendanceRecordDto> page = new Page<AttendanceRecordDto>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", vo.getStoreId());
		params.put("employeeCode", vo.getEmployeeCode());
		params.put("employeeName", vo.getEmployeeName());
		params.put("attendanceDateBegin", vo.getAttendanceDateBegin());
		params.put("attendanceDateEnd", vo.getAttendanceDateEnd());
		page.setParams(params);
		List<AttendanceRecordDto> list = employeeAttendanceMapper.selectAttendanceRecordSelective(page);
		page.setResults(list);
		return page;
	}
	

}
