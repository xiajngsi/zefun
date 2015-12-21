package com.zefun.test;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.controller.EmployeeController;
import com.zefun.web.dto.BaseDto;
/**
* @author chendb
* @date 2015年8月11日 上午11:30:40
 */
public class EmployeeTest extends BaseTest{
    /**
     * 员工信息Controller
     */
	@Autowired
	private EmployeeController employeeController;
	
	/**
     * 日志
     */
    private Logger logger = Logger.getLogger(GoodsInfoTest.class);
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午11:30:49
	 */
	/*@Test
	public void add(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("positionId", 1);
		map.put("levelId", 1);
		map.put("recommendId", 1);
		map.put("employeeCode", "019");
		map.put("name", "hahah");
		map.put("headImage", "http://7xk8dt.com1.z0.glb.clouddn.com/lives/idCard/1437016097358");
		map.put("sex", "男");
		map.put("birthday", "2015-10-10");
		map.put("phone", "11111111111");
		map.put("identityCard", "2312312");
		map.put("healthCard", "123123");
		map.put("address", "d212e12edqw");
		map.put("emergencyPhone", "12312321");
		map.put("baseSalaries", "100");
		map.put("positionSalaries", "200");
		map.put("employeeStatus", "在职");
		map.put("entryDate", "2015-10-10");
		map.put("leaveDate", "2015-10-10");
		
		JSONObject jsonObj = JSONObject.fromObject(map);
		
		String b=jsonObj.toString();
		BaseDto dto =employeeController.addEmployee(request, response, b);
		logger.info("list result : " + JSONObject.fromObject(dto).toString());
		
	}*/
	
}
