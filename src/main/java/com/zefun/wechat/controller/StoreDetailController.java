package com.zefun.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.entity.RechargeSetting;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.service.AgentInfoService;
import com.zefun.web.service.AgentRecommendRecordService;
import com.zefun.web.service.RechargeSettingService;
import com.zefun.web.service.StoreInfoService;
import com.zefun.web.service.UserAccountService;
import com.zefun.wechat.service.WechatCallService;

/**
 * 门店信息控制器
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 */
@Controller
public class StoreDetailController extends BaseController {

    /**
     * 门店信息操作
     */
    @Autowired
    private StoreInfoService storeInfoService;

    /**
     * 用户账户操作
     */
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 推荐记录操作
     */
    @Autowired
    private AgentRecommendRecordService agentRecommendRecordService;

    /**
     * 渠道商操作
     */
    @Autowired
    private AgentInfoService agentInfoService;

    /**
     * 充值配置信息操作
     */
    @Autowired
    private RechargeSettingService rechargeSettingService;

    /**
     * 微信回调操作
     */
    @Autowired
    private WechatCallService wechatCallService;

    /**测试数据*/
//    private String openId = "oVeRPuAYeJBizrexSgVccJxd1tWg";

    /**
     * 门店首页
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 门店首页
     * @throws IOException  跳转异常
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_INDEX, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        if (storeInfo == null) {
            String contextPath = request.getContextPath();
            if (contextPath.endsWith("/")) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
            try {
                response.sendRedirect(contextPath + Url.StoreApply.VIEW_STORE_APPLY);
            }
            catch (IOException e) {
                throw e;
            }
            return null;
        }

        Integer storeType = storeInfo.getStoreType();
        switch (storeType) {
            case 1: { // 单店
                return singleStore(storeInfo, request, response);
            }
            case 2: { // 连锁总店
                return chainHQStore(storeInfo, request, response);
            }
            case 3: { // 连锁分店
                return chainStore(storeInfo, request, response);
            }
            default: {//
                return null;
            }
        }

    }

    /**
     * 连锁分店
     * @author gebing
     * @date 2015年12月4日
     * @param storeInfo 门店信息
     * @param request   请求对象
     * @param response  响应对象
     * @return 连锁分店首页
     */
    private ModelAndView chainStore(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeInfo.getStoreId());

        StoreInfo hqStoreInfo = storeInfoService.getByStoreId(storeInfo
                .getHqStoreId());
        ModelAndView mav = new ModelAndView(View.StoreDetail.CHAIN_STORE);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("hqStoreInfo", hqStoreInfo);
        return mav;
    }

    /**
     * 连锁总店
     * @author gebing
     * @date 2015年12月4日
     * @param storeInfo 门店信息
     * @param request   请求对象
     * @param response  响应对象
     * @return 连锁总店首页
     */
    private ModelAndView chainHQStore(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeInfo.getStoreId());
        ModelAndView mav = new ModelAndView(View.StoreDetail.CHAIN_HQ_STORE);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("chainsCnt",
                storeInfoService.countChainStores(storeInfo.getStoreId()));
        return mav;
    }

    /**
     * 单店
     * @author gebing
     * @date 2015年12月4日
     * @param storeInfo 门店信息
     * @param request   请求对象
     * @param response  响应对象
     * @return 单店首页
     */
    private ModelAndView singleStore(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeInfo.getStoreId());
        ModelAndView mav = new ModelAndView(View.StoreDetail.SINGLE_STORE);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("storeInfo", storeInfo);
        return mav;
    }

    /**
     * 门店资料
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 门店资料页
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_INFO, method = RequestMethod.GET)
    public ModelAndView info(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        Integer storeId = storeInfo.getStoreId();
        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("storeAccount", storeAccount);
        Integer storeType = storeInfo.getStoreType();
        String view = "";
        switch (storeType) {
            case 1: { // 单店
                view = View.StoreDetail.SINGLE_STORE_INFO;
                mav.addObject("userAccount",
                        userAccountService.getSingleStoreAccount(storeId));
                break;
            }
            case 2: { // 连锁总店
                mav.addObject("chainsCnt",
                        storeInfoService.countChainStores(storeId));
                mav.addObject("userAccount",
                        userAccountService.getChainHQStoreAccount(storeId));
                view = View.StoreDetail.CHAIN_HQ_STORE_INFO;
                break;
            }
            case 3: { // 连锁分店
                view = View.StoreDetail.CHAIN_STORE_INFO;
                mav.addObject("userAccount",
                        userAccountService.getChainStoreAccount(storeId));
                break;
            }
            default: {
                return null;
            }
        }
        mav.setViewName(view);
        return mav;

    }

    /**
     * 总店下连锁分店列表
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return  总店下连锁分店列表页
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_HQ_CHAINS, method = RequestMethod.GET)
    public ModelAndView chains(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        Integer storeId = storeInfo.getStoreId();
        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("userAccount",
                userAccountService.getChainHQStoreAccount(storeId));
        mav.addObject("chainsCnt", storeInfoService.countChainStores(storeId));
        List<StoreInfo> chains = storeInfoService.getChainsByHQStoreId(storeId);
        if (chains != null && !chains.isEmpty()) {
            List<Integer> storeIds = new ArrayList<Integer>();
            for (StoreInfo chain : chains) {
                storeIds.add(chain.getStoreId());
            }
            List<StoreAccount> chainStoreAccounts = storeInfoService
                    .getAccountByStoreIds(storeIds);
            Map<Integer, StoreAccount> mChainStoreAccounts = new HashMap<Integer, StoreAccount>();
            if (chainStoreAccounts != null && !chainStoreAccounts.isEmpty()) {
                for (StoreAccount account : chainStoreAccounts) {
                    mChainStoreAccounts.put(account.getStoreId(), account);
                }
            }
            mav.addObject("chainStoreAccounts", mChainStoreAccounts);

            Map<Integer, AgentInfo> mRecommendAgents = new HashMap<Integer, AgentInfo>();
            List<AgentRecommendRecord> recommendRecords = agentRecommendRecordService.getByRecommendedIds(storeIds);
            if (recommendRecords != null && !recommendRecords.isEmpty()) {
                for (AgentRecommendRecord recommendRecord : recommendRecords) {
                    AgentInfo agentInfo = agentInfoService
                            .getByAgentId(recommendRecord.getAgentId());
                    if (agentInfo != null) {
                        mRecommendAgents.put(recommendRecord.getRecommendedId(),
                                agentInfo);
                    }
                }
            }
            mav.addObject("recommendAgents", mRecommendAgents);
        }
        else {
            chains = new ArrayList<StoreInfo>();
        }
        mav.addObject("chains", chains);
        mav.setViewName(View.StoreDetail.CHAIN_STORE_CHAINS);
        return mav;

    }

    /**
     * 系统续费页面
     * @author gebing
     * @date 2015年12月4日
     * @param session 会话
     * @param request   请求对象
     * @param response  响应对象
     * @return 系统续费页面
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_RENEW_SYS, method = RequestMethod.GET)
    public ModelAndView viewRenewSys(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        List<RechargeSetting> rechargeSettings = rechargeSettingService.getByType(1);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(View.StoreDetail.STORE_RENEW_SYS);
        mav.addObject("rechargeSettings", rechargeSettings);
        return mav;
    }

    /**
     * 短信充值
     * @param session 会话
     * @param request   请求对象
     * @param response  响应对象
     * @return 短信充值页面
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_RENEW_SMS, method = RequestMethod.GET)
    public ModelAndView viewRenewSms(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        List<RechargeSetting> rechargeSettings = rechargeSettingService.getByType(2);
        ModelAndView mav = new ModelAndView();
        mav.addObject("rechargeSettings", rechargeSettings);
        mav.setViewName(View.StoreDetail.STORE_RENEW_SMS);
        return mav;
    }

    /**
     * 充值操作
     * @author gebing
     * @date 2015年12月4日
     * @param payType rechargeSettings的id
     * @param goodsName 商品名称
     * @param request 请求
     * @param response 响应
     * @return 微信调用结果
     */
    @RequestMapping(value = Url.StoreDetail.ACTION_DETAIL_RENEW, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto renew(Integer payType, String goodsName, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 4, request, response);
        if (openId == null) {
            return null;
        }

        if (payType == null) {
            return new BaseDto(1, "请选择一种支付类型");
        }
        RechargeSetting setting = rechargeSettingService.getById(payType);
        if (setting == null) {
            return new BaseDto(2, "错误的支付类型");
        }
        goodsName = goodsName + "支付" + setting.getAmount() + "元";
        String appId = getAppIdByStore(App.System.WECHAT_ZEFUN_STORE_ID);
        String mchId = App.Wechat.MCH_ID;
        String deviceInfo = "xxoo";
        String outTradeNo = StringUtil.getKey();
        return wechatCallService.pay(appId, mchId, deviceInfo, goodsName, setting.getAmount().toString(), openId, outTradeNo, request, response);
    }
}
