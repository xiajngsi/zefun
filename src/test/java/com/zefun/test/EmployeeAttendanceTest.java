package com.zefun.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.EmployeeAttendanceService;

/**
 * 
 * @author Administrator
 *
 */
public class EmployeeAttendanceTest extends BaseTest {
	
	/***/
	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	/**
	 * 签到
	 */
	@Test
	public void signIn() {
		BaseDto bd = employeeAttendanceService.signOperate(1004, 130);
	}

}
