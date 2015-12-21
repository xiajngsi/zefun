package com.zefun.test;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.zefun.web.controller.MemberLevelController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.MemberLevel;

/**
 * 会员等级测试类
 * @author 张进军
 * @date Aug 5, 2015 6:57:00 PM 
 */
public class MemberLevelTest extends BaseTest{
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(MemberLevelTest.class);
    
    /** 请求对象 */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    
    /**
     * 会员等级
     */
	@Autowired
	private MemberLevelController memberLevelController;
	
	/**
	 * 添加
	* @author 洪秋霞
	* @date 2015年8月11日 下午2:47:52
	 */
	@Test
	public void add(){
		MemberLevel memberLevel = new MemberLevel();
		memberLevel.setLevelName("黄金会员");
		memberLevel.setChargeAmount(1000);
		memberLevel.setSellAmount(100);
		memberLevel.setIntegralUnit(1);
		memberLevel.setIntegralNumber(1);
		memberLevel.setUpgradeIntegral(10000);
		BaseDto dto = memberLevelController.addAction(memberLevel, request);
		logger.info(JSONObject.fromObject(dto).toString());
	}
	
	/**
	 * 列表
	* @author 洪秋霞
	* @date 2015年8月11日 下午2:48:27
	 */
	@Test
	public void list(){
		int pageNo = 1;
		int pageSize = 10;
		BaseDto dto = memberLevelController.listAction(pageNo, pageSize, request);
		logger.info("list result : " + JSONObject.fromObject(dto).toString());
	}
	
	/**
	 * 
	* @author 洪秋霞
	* @date 2015年8月11日 下午2:48:32
	 */
	@Test
	public void info(){
		BaseDto dto = memberLevelController.infoAction(1);
		logger.info("info result : " + JSONObject.fromObject(dto).toString());
	}
	
	/**
	 * 删除
	* @author 洪秋霞
	* @date 2015年8月11日 下午2:48:37
	 */
	@Test
	public void delete(){
		BaseDto dto = memberLevelController.deleteAction(1);
		logger.info("delete result : " + JSONObject.fromObject(dto).toString());
	}
}
