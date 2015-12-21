package com.zefun.test;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.wechat.controller.StaffCentreController;
import com.zefun.wechat.controller.StaffController;
import com.zefun.wechat.controller.StaffOrderController;

/**
 * 开支记账Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
public class StaffTest extends BaseTest{
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(StaffTest.class);
    
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
	private StaffController staffController;
	
	/**	 */
	@Autowired
	private StaffOrderController staffOrderController;
	/**
     * 移动端员工中心逻辑类Controller
     */
    @Autowired
    private StaffCentreController staffCentreController;
	
	/**
     * 新增开支记账
    * @author 王大爷
    * @date Jul 2, 2015 2:58:04 PM
     */
	@Test
	public void homeView(){
	    /*shiftMahjongStepId=3029&type=2&detailId=1881&shiftMahjongEmployeeId=1224&shiftMahjongEmployeeIdOld=&shiftMahjongId=128*/
	    staffController.selfMotionExecute(1259, 1005);
	    /*HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongStepId, 
        Integer type, Integer shiftMahjongEmployeeId, Integer shiftMahjongEmployeeIdOld, Integer detailId, Integer shiftMahjongId*/
		/*logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());*/
		
	}
	
	/**
	 * 测试签到签退功能
	* @author 高国藩
	* @date 2015年12月3日 下午3:27:20
	 *//*
	@Test
    public void signOperate(){
	    request.getSession().setAttribute(App.Session.STORE_ID, "1024");
	    request.getSession().setAttribute(App.Session.WECHAT_BUSINESS_TYPE, "2");
	    request.getSession().setAttribute(App.Session.WECHAT_OPEN_ID, "JKDKON_DSUJLJKLSJDFDFSKSDKSLJFK_klsdfkljh");
	    BaseDto baseDto = staffCentreController.signOperate(request, response);
	    logger.info(JSONObject.fromObject(baseDto).toString());
    }*/
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月2日 上午11:55:55
	 */
	@Test
    public void selectBaseInfo(){
        
	    /*BaseDto dto =staffController.selectBaseInfo(0, 0, "18554756544");
        logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());*/
        
    }
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月18日 上午11:38:54
	 */
	/*	@Test
	public void selectCategory(){
	    MemberBaseDto memberBaseDto = new MemberBaseDto();
	    ModelAndView dto = null;
        try {
            dto = staffController.selectCategory(memberBaseDto, request, response);
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("selectCategory result : " + dto.toString());
	}
	*/
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月19日 下午2:38:45
	 */
/*	@Test
	public void addOrderInfo(){
	    List<Object> listObj = new ArrayList<Object>();
	    Object obj = new Object();
	    obj.
	    BaseDto dto =staffController.addOrder(request, response, listObj, 30);
	}
	*/
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月24日 下午4:30:47
	 */
	/*@Test
	public void getOrderCode() {
	    staffController.getOrderCode("order_detail");
	    BaseDto dto = staffController.addOrder(request, response);
	    logger.info("addOrderInfo result : " + dto.toString());
	}*/
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月21日 下午4:42:49
	 */
/*	@Test
	public void selectShiftProjectEmployee(){
	    staffController.selectShiftProjectEmployee(105);
	}*/
}
