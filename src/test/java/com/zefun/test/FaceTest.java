package com.zefun.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.zefun.web.controller.FaceController;

/**
 * @author 张进军
 * @date Aug 5, 2015 4:31:18 PM 
 */
public class FaceTest extends BaseTest{
    /**
     * 
     */
	@Autowired
    private FaceController faceController;
	
	/**
	 * 
	* @author 洪秋霞
	* @date 2015年8月11日 下午2:48:52
	* @throws Exception e
	 */
	@Test
	public void test() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		faceController.addFace("abcd", request);
	}
}
