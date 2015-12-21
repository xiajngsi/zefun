package com.zefun.test;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.controller.ShiftMahjongController;
import com.zefun.web.entity.ShiftMahjong;


/**
 * 开支记账Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
public class ShiftMahjongTest extends BaseTest{
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(ShiftMahjongTest.class);
    
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
	private ShiftMahjongController shiftMahjongController;
	
	/**
     * 新增开支记账
    * @author 王大爷
    * @date Jul 2, 2015 2:58:04 PM
     */
	@Test
	public void initializeShiftMahjong(){
	    
		ModelAndView dto =shiftMahjongController.initializeShiftMahjong(request, response);
		logger.info("addStoreFlow接口  ：   "+JSONObject.fromObject(dto).toString());
		
	}
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月8日 下午3:56:48
	 */
	@Test
	public void addUpdateShiftMahjong(){
	    ShiftMahjong shiftMahjong = new ShiftMahjong();
	    shiftMahjong.setShiftMahjongName("剪发技师牌");
	    shiftMahjong.setDeptId(1);
	    shiftMahjong.setNature(2);
	    shiftMahjong.setShiftMahjongRule(1);
	    shiftMahjong.setShiftMahjongUp(1);
	    shiftMahjong.setCreateTime(DateUtil.getCurDate());
	    String positionIdList = "4,10";
	    shiftMahjongController.addUpdateShiftMahjong(request, response, shiftMahjong, positionIdList);
	}
	
	/**
	 * 
	* @author 王大爷
	* @date 2015年9月8日 下午4:26:46
	 */
	@Test
	public void deleteShiftMahjong(){
	    shiftMahjongController.deleteShiftMahjong(5);
	}
	
}
