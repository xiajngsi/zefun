package com.zefun.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zefun.common.utils.MessageUtil;
import com.zefun.wechat.dto.TextMessageDto;
import com.zefun.wechat.service.WeixinMessageService;

/**
 * 配置文件载入类，所有测试类须继承此类
 * @author 张进军
 * @date Aug 5, 2015 4:27:31 PM 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml", "classpath*:spring/spring-context.xml" })
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
// 不改变数据库数据
public class BaseTest {

    /** mockMvc 测试api*/
    public MockMvc mockMvc;
    /** request 作为参数传入*/
    public MockHttpServletRequest request;
    /** response 作为参数传入*/
    public MockHttpServletResponse response;
    /** 日志 */
    private Logger logger = Logger.getLogger(BaseTest.class);
    /** 上下文*/
    @Autowired protected WebApplicationContext webApplicationContext;
    /** 微信测试类*/
    @Autowired private WeixinMessageService weixinMessageService;

    /**
     * 
    * @author 高国藩
    * @date 2015年8月11日 下午2:49:09
     */
    /*
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    */

    /**
     * 发送json数据测试
    * @author 高国藩
    * @date 2015年8月12日 下午8:01:26
    * @throws Exception 抛出异常
     */
    /*
    @Test
    public void jsonTest() throws Exception {
        String jsonMessage = "{\"name\":\"xiaopan\"}";
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/wechat/getJson").contentType(MediaType.APPLICATION_JSON).content(jsonMessage)
                                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.view().name("wechat/menu"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("menus")).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result.getModelAndView().getModel().get("menus"));
    }
     */
    /**
     * 发送requert，获取request中数据，传递request参数单元测试
    * @author 高国藩
    * @date 2015年8月12日 下午8:01:26
    * @throws Exception 抛出异常
     */
    /*
    @Test
    public void requestTest() throws Exception {
        TextMessageDto textMessageDto = new TextMessageDto();
        textMessageDto.setContent("您好");
        textMessageDto.setCreateTime(new Date().getTime());
        textMessageDto.setFromUserName("发送者");
        textMessageDto.setToUserName("接受者");
        textMessageDto.setMsgType("text");
        String requestMessage = MessageUtil.textMessageToXml(textMessageDto);
        request.setContent(requestMessage.getBytes("utf-8"));
        request.setContentType("application/xml");
        String responeText = weixinMessageService.processRequest(request);
        logger.info("respMessage" + responeText);
    }
    */
    /**
     * 跳转页面及参数数据单元测试
    * @author 高国藩
    * @date 2015年8月12日 下午7:49:19
    * @throws Exception 抛出异常
     */
    /*
    @Test
    public void testView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/wechat/menu")) // 请求链接
                .andExpect(MockMvcResultMatchers.view().name("wechat/menu")) // 检验返回页面
                .andExpect(MockMvcResultMatchers.model().attributeExists("menus")) // 检验model中参数数据
                .andDo(MockMvcResultHandlers.print()) // 打印本次请求全部信息
                .andReturn(); // 返回
        Assert.assertNotNull(result.getModelAndView().getModel().get("menus")); // 校验model中数据，是否满足条件
    }
    */

}
