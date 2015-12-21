package com.zefun.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.AgentInfoService;

/**
 *
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 * 渠道申请控制器
 */
@Controller
public class AgentApplyController extends BaseController {

    /** 日志记录对象 */
//    private Logger logger = Logger.getLogger(AgentApplyController.class);

    /**
     * 渠道操作service
     */
    @Autowired
    private AgentInfoService agentInfoService;

    /**测试数据*/
//    private String openId = "oVeRPuAYeJBizrexSgVccJxd1tWg";
    
    /**
     * 入口方法, 根据用户的openid判断有没有渠道, 如果有则跳转到他的渠道首页, 如果没有, 则跳转到渠道申请页, 
     * 如果openid下没有渠道而code不为空, 则说明该用户是code对应的渠道推荐的用户
     * @author gebing
     * @date 2015年12月4日
     * @param code 推荐人的openid
     * @param request 请求
     * @param response 响应
     * @return 返回渠道申请页
     * @throws IOException 跳转异常
     */
    @RequestMapping(value=Url.AgentApply.VIEW_AGENT_APPLY, method = RequestMethod.GET)
    public ModelAndView viewChannelApply(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        if (!StringUtils.isBlank(openId) && agentInfoService.getByOpenId(openId) != null) {
            String contextPath = request.getContextPath();
            if (contextPath.endsWith("/")) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
            try {
                response.sendRedirect(contextPath + Url.AgentDetail.VIEW_DETAIL_INDEX);
            }
            catch (IOException e) {
                throw e;
            }
            return null;
        }
        ModelAndView mav = new ModelAndView(View.Agent.AGENT_APPLY);
        mav.addObject("code", code);
        return mav;
    }
    

    /**
     * 进行渠道申请
     * @author gebing
     * @date 2015年12月4日
     * @param code 推荐人的openid
     * @param name 申请人姓名
     * @param phone 申请人电话
     * @param verifyCode 验证码
     * @param agentType 申请的渠道类型
     * @param company 公司/商号名称
     * @param province 省份
     * @param city 城市
     * @param request 请求
     * @param response 响应
     * @return 申请结果
     */
    @RequestMapping(value=Url.AgentApply.ACTION_AGENT_APPLY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionStoreApply(String code, String name, String phone, 
            String verifyCode, Integer agentType, String company, String province, String city, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        //校验验证码是否正确
//        String c = redisService.get(App.Redis.PHONE_VERIFY_CODE_KEY_PRE + phone);
//        if (!verifyCode.equals(c)) {
//            return new BaseDto(10001, "验证码错误");
//        }

        return agentInfoService.addAgentApplyInfo(code, name, phone, agentType, company, province, city, openId);
    }

}
