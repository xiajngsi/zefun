package com.zefun.test;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.service.RabbitService;
import com.zefun.web.service.SelfCashierService;


/**
 * 自助收银签退
* @author luhw
* @date 2015年10月21日 下午16:19:19
 */
public class SelfCashierTest extends BaseTest{
    
	/**  */
    @Autowired
    private SelfCashierService selfCashierService;
    
    /**  */
    @Autowired
    private RabbitService rabbitService;

    /**
     * 日志
     */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(SelfCashierTest.class);
    
    /**
     * 
     */
    @Test
    public void testRabbitService(){
    	rabbitService.sendCashierOrderComission(1165);
    }
    
}
