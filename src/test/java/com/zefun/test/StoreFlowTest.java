package com.zefun.test;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.controller.StoreFlowController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.StoreFlow;
import com.zefun.wechat.controller.StaffCentreController;

/**
 * 开支记账Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
public class StoreFlowTest extends BaseTest{
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(StoreFlowTest.class);
    
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();
    
    /**
     * 开支记账Controller
     */
	@Autowired
	private StaffCentreController staffCentreController;
	
	
	
	/**
     * 删除开支记账
    * @author laowang
    * @date 2015年8月6日 下午8:45:22
     */
	/*@Test
	public void deleteStoreFlow(){
	    ModelAndView dto = staffCentreController.myPerformance(request, response, 279);
	    logger.info("deleteStoreFlow接口      ：  "+JSONObject.fromObject(dto).toString());
	}*/
}
