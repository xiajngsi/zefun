package com.zefun.test;

import org.apache.log4j.Logger;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


/**
 * 开卡充值Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
public class MemberInfoTest extends BaseTest{
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    /**日志*/
    public static Logger log = Logger.getLogger(MemberInfoTest.class);
    
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();
}