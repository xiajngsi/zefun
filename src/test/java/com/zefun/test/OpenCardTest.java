package com.zefun.test;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.web.controller.OpenCardController;


/**
 * 开卡充值Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
public class OpenCardTest extends BaseTest{
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(OpenCardTest.class);
    
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();
    
    /**
     * 开卡充值Controller
     */
	@Autowired
	private OpenCardController openCardController;
	
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月8日 下午4:26:46
	 */
	@Test
	public void initializeOpenCard(){
	    
	    ModelAndView dto = openCardController.initializeOpenCard(request, response);
	    
	    logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());
	}
	
	/**
     * 
    * @author 王大爷
    * @date 2015年9月8日 下午4:26:46
     */
    @Test
    public void addMemberInfo(){
        
        /*BaseDto dto = openCardController.addMemberInfo(request, response, "188888888", "你大爷", "男", 28, "28,29", 
                "28.00,35.00", "5,7", "100,600", "1,6", new BigDecimal("5000"), "123456");
        
        logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());*/
    }
	
    /**
     * 
    * @author 王大爷
    * @date 2015年9月8日 下午4:26:46
     */
 /*   @Test
    public void rechargeMemberInfo(){
        
        BaseDto dto = openCardController.rechargeMemberInfo(request, response, 54, 7, new BigDecimal(1000), new BigDecimal(1000), null, "28,29", 
                "56.00,34.00", "5,7", "1200,200", "1,6");
        
        logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());
    }*/
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月8日 下午4:26:46
     */
/*    @Test
    public void checkoutAccount(){
        
        BaseDto dto = openCardController.checkoutAccount(request, response, 54, 7, 56, 9, new BigDecimal(1000), "123456");
        
        logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());
    }*/
    
    
    
}
