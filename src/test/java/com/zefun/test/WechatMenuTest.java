package com.zefun.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.WechatMenuDto;
import com.zefun.web.entity.MemberMenu;
import com.zefun.web.entity.Menu;
import com.zefun.web.mapper.MemberMenuMapper;
import com.zefun.web.mapper.RoleInfoMapper;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;
import com.zefun.wechat.controller.WechatController;
import com.zefun.wechat.dto.ItmesDto;
import com.zefun.wechat.dto.TextMessageDto;
import com.zefun.wechat.service.WeixinConfigService;
import com.zefun.wechat.service.WeixinMessageService;
import com.zefun.wechat.service.WeixinUploadService;

/**
 * 微信单元测试
* @author 高国藩
* @date 2015年8月11日 上午12:30:24
 */
public class WechatMenuTest extends BaseTest {
    /** 微信请求类 */
    @Autowired
    private WechatController wechatController;
    /** 微信服务类 */
    @Autowired
    private WeixinMessageService weixinMessageService;
    /** 微信工具service */
    @Autowired
    private WeixinConfigService weixinConfigService;
    /** 队列 */
    @Autowired
    private RabbitService rabbitService;
    /** redis */
    @Autowired
    private RedisService redisService;
    /**角色表*/
    @Autowired
    private RoleInfoMapper infoMapper;
    /**菜单权限表*/
    @Autowired
    private MemberMenuMapper memberMenuMapper;
    /** 日志 */
    private Logger logger = Logger.getLogger(WechatMenuTest.class);

    /**
     * 新增菜单
    * @author 高国藩
    * @date 2015年8月11日 下午2:41:09
     */
    @Test
    public void add() {
        Menu menu = new Menu("我的信息", "click", null, 0, 1);
        wechatController.addMenu(menu);
    }

    /**
     * 微信上传菜单
    * @author 高国藩
    * @date 2015年8月11日 下午2:41:09
     */
    @Test
    public void updateWechatMenu() {
        /** 调用微信接口,从库中获取村好的菜单,更新微信菜单 */
        wechatController.createMenu(null);
    }

    /**
     * 获得菜单信息
    * @author 高国藩
    * @date 2015年8月11日 下午2:41:09
     */
    @Test
    public void getMenus() {
        Integer storeId = 1;
        /** 调用微信接口,从库中获取村好的菜单,更新微信菜单 */
        List<WechatMenuDto> menus = weixinMessageService.getAllMenu(storeId);
        Logger.getLogger(WechatMenuTest.class).info(
                "菜单:" + JSONArray.fromObject(menus).toString());
    }

    /**
     * 群发发送图文消息
    * @author 高国藩
    * @date 2015年8月13日 上午10:59:33
     */
    @Test
    public void menuRole() {
        MemberMenu memberMenu = memberMenuMapper.selectMenuByRoleId(1);
        logger.info(memberMenu.getFirstMenu());
        logger.info(memberMenu.getSecontMenu());
    }
    
    /**
     * 群发发送图文消息
    * @author 高国藩
    * @date 2015年8月13日 上午10:59:33
     */
    @Test
    public void sendMessageText() {
        List<String> touser = new ArrayList<String>();
        touser.add("oHZz8tpK3YYo9r0X4kGi7wzW3DIs");
        touser.add("oHZz8toqcjOZHyEBvJdbwv2nxkEw");
        touser.add("oHZz8tovOZ8GbzzFp93m2WXsFH1E");
        touser.add("oHZz8tvgyNY-Fvnp9xiSGH54_c0o");
        String text = "大家好，我是波多野结衣，喜欢我就点我吧~~~";
        BaseDto baseDto = wechatController.sendTextByOpenId(touser, text, null);
        logger.info("状态:"+baseDto.getMsg().toString());
    }
    
    /**
     * 群发发送图文消息
    * @author 高国藩
    * @date 2015年8月13日 上午10:59:33
     */
    @Test
    public void updateItems() {
//        wechatController.updateItmes("SIUN9ihLt74UItSxUBL4KzkhdxkxLptmdbqC5ypzXvU");
    }
    
    /**
     * 进入页面测试
    * @author 高国藩
     * @throws Exception 异常处理
    * @date 2015年8月12日 下午3:09:10
     */
    @Test
    public void testView() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/wechat/menu"))
                .andExpect(MockMvcResultMatchers.view().name("wechat/menu"))
                .andExpect(
                        MockMvcResultMatchers.model().attributeExists("menus"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result.getModelAndView().getModel().get("menus"));
    }

    /**
     * 发送requert，获取request中数据
    * @author 高国藩
    * @date 2015年8月12日 下午8:01:26
    * @throws Exception 抛出异常
     */
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

    /**
     * 发送json数据测试
    * @author 高国藩
    * @date 2015年8月12日 下午8:01:26
    * @throws Exception 抛出异常
     */
    @Test
    public void jsonTest() throws Exception {
        String jsonMessage = "{\"name\":\"xiaopan\"}";
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/wechat/getJson")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonMessage)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.view().name("wechat/menu"))
                .andExpect(
                        MockMvcResultMatchers.model().attributeExists("menus"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result.getModelAndView().getModel().get("menus"));
    }
    
    /**
     * 发送模板消息
    * @author 高国藩
    * @date 2015年9月15日 下午3:36:55
     */
    @Test
    public void sendTempleMsg(){
        String url = Url.Wechat.SEND_COUPON;
        String accessToken = weixinConfigService.getAccessToken(
                "wx57423593c068308c", "e18bd8ce8c3996374e697d3771c51493");
        url = url.replace("ACCESS_TOKEN", accessToken);
        Map<String, Object> map = new HashMap<String, Object>();
        //data 数据
        Map<String, Object> data = new HashMap<String, Object>();
        
        //first 数据
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", "恭喜你获得一张优惠券");
        first.put("color", "#173177");
        
        //couponName 优惠券名称
        Map<String, String> couponName = new HashMap<String, String>();
        couponName.put("value", "按摩嫖娼一卡通");
        couponName.put("color", "#173177");
        
        //couponName 优惠券名称
        Map<String, String> couponPrice = new HashMap<String, String>();
        couponPrice.put("value", "39.9元");
        couponPrice.put("color", "#173177");
        
        //couponUse 优惠券适用
        Map<String, String> couponUse = new HashMap<String, String>();
        couponUse.put("value", "洗剪吹");
        couponUse.put("color", "#173177");
        
        //couponStopTime 截止日期
        Map<String, String> couponStopTime = new HashMap<String, String>();
        couponStopTime.put("value", "2015-01-23");
        couponStopTime.put("color", "#173177");
        
        data.put("first", first);
        data.put("couponName", couponName);
        data.put("couponPrice", couponPrice);
        data.put("couponUse", couponUse);
        data.put("couponStopTime", couponStopTime);
        
        map.put("data", data);
        map.put("touser", "oHZz8tpK3YYo9r0X4kGi7wzW3DIs");
        map.put("template_id", "gxma6Fq8BUyF6AH47dy0jRnkLGmX2daUHReeShdqlAc");
        map.put("url", "http://www.baidu.com");
        map.put("topcolor", "#FF0000");
        WeixinUploadService.httpRequest(url, "POST", JSONObject.fromObject(map).toString());
    }
    
    /**
     * 队列测试
    * @author 高国藩
    * @date 2015年9月16日 下午12:12:17
     */
    @Test
    public void sendMessageCopyItems(){
        String url = Url.Wechat.SEND_COUPON;
        String accessToken = weixinConfigService.getAccessToken(
                "wx57423593c068308c", "e18bd8ce8c3996374e697d3771c51493");
        url = url.replace("ACCESS_TOKEN", accessToken);
        
        Map<String, Object> map = new HashMap<String, Object>();
        //data 数据
        Map<String, Object> data = new HashMap<String, Object>();
        
        //first 数据
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", "恭喜你获得一张优惠券");
        first.put("color", "#173177");
        
        //couponName 优惠券名称
        Map<String, String> couponName = new HashMap<String, String>();
        couponName.put("value", "按摩嫖娼一卡通");
        couponName.put("color", "#173177");
        
        //couponName 优惠券名称
        Map<String, String> couponPrice = new HashMap<String, String>();
        couponPrice.put("value", "39.9元");
        couponPrice.put("color", "#173177");
        
        //couponUse 优惠券适用
        Map<String, String> couponUse = new HashMap<String, String>();
        couponUse.put("value", "洗剪吹");
        couponUse.put("color", "#173177");
        
        //couponStopTime 截止日期
        Map<String, String> couponStopTime = new HashMap<String, String>();
        couponStopTime.put("value", "2015-01-23");
        couponStopTime.put("color", "#173177");
        
        data.put("first", first);
        data.put("couponName", couponName);
        data.put("couponPrice", couponPrice);
        data.put("couponUse", couponUse);
        data.put("couponStopTime", couponStopTime);
        
        map.put("data", data);
        map.put("touser", "oHZz8tpK3YYo9r0X4kGi7wzW3DIs");
        map.put("template_id", "gxma6Fq8BUyF6AH47dy0jRnkLGmX2daUHReeShdqlAc");
        map.put("url", "http://www.baidu.com");
        map.put("topcolor", "#FF0000");
        
        map.put("storeId", 1);
        
    }
    
    /**
     * 当前门店复制图文消息
    * @author 高国藩
    * @date 2015年9月17日 下午2:53:39
     */
    @Test
    public void copyItems(){
/*        String fatherMediaId = "sdf";
        @SuppressWarnings("unused")
        Long count = redisService.zcount(fatherMediaId, 0l, 1l);*/
        String[] member = new String[]{"1", "2"};
        redisService.sadd("ceshi", member);
    }
    
    /**
     * 观看图文消息
    * @author 高国藩
    * @date 2015年9月29日 下午5:01:10
     */
    @Test
    public void showItems(){
        Integer storeId = 1004;
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", accessToken);
        Map<String, String> map = new HashMap<String, String>();
        map.put("media_id", "En7C-sAE0HVJlBH1AvmAnn3DKK-THLynpIUxE6Ar20A");
        JSONObject json = WeixinUploadService.httpRequest(url, "POST", JSONObject.fromObject(map)
                .toString());
        ItmesDto itmes = (ItmesDto) JSONObject.toBean(json, ItmesDto.class);
        logger.info(JSONObject.fromObject(itmes).toString());
    }
    
}
