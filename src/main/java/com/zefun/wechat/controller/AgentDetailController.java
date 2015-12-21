package com.zefun.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.IncomeDto;
import com.zefun.web.dto.StoreIncomeDto;
import com.zefun.web.entity.AgentAccount;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.service.AgentInfoService;
import com.zefun.web.service.StoreInfoService;
import com.zefun.web.service.UserAccountService;

/**
 * 渠道信息控制器
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 */
@Controller
public class AgentDetailController extends BaseController {

    /** 日志记录对象 */
    private Logger logger = Logger.getLogger(StoreInfoService.class);

    /**
     * 渠道信息操作
     */
    @Autowired
    private AgentInfoService agentInfoService;

    /**
     * 用户账号操作
     */
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 门店信息操作
     */
    @Autowired
    private StoreInfoService storeInfoService;

    /**测试数据*/
//    private String openId = "oVeRPuAYeJBizrexSgVccJxd1tWg";

    /**
     * 渠道首页
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 渠道首页
     * @throws IOException  跳转异常
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_INDEX, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        if (agentInfo == null) {
            String contextPath = request.getContextPath();
            if (contextPath.endsWith("/")) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
            try {
                response.sendRedirect(contextPath + Url.AgentApply.VIEW_AGENT_APPLY);
            }
            catch (IOException e) {
                throw e;
            }
            return null;
        }
        AgentAccount agentAccount = agentInfoService.getAccountByAgentId(agentInfo.getAgentId());

        ModelAndView mav = new ModelAndView(View.AgentDetail.APPLY_INFO);
        mav.addObject("agentAccount", agentAccount);
        mav.addObject("agentInfo", agentInfo);

        //检查渠道是否通过申请
        if (agentAccount.getAgentStatus() == 2) {
            mav.setViewName(View.AgentDetail.INDEX);
            mav.addObject("code", openId);
            setJsapiSignData(App.System.WECHAT_ZEFUN_STORE_ID, request);
        }

        return mav;
    }

    /**
     * 渠道资料
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 渠道资料页
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_INFO, method = RequestMethod.GET)
    public ModelAndView info(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        AgentAccount agentAccount = agentInfoService
                .getAccountByAgentId(agentInfo.getAgentId());
        ModelAndView mav = new ModelAndView(View.AgentDetail.INFO);
        mav.addObject("agentAccount", agentAccount);
        mav.addObject("agentInfo", agentInfo);
        return mav;
    }

    /**
     * 渠道自己发展的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 渠道自己发展的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_NEW_STORE, method = RequestMethod.GET)
    public ModelAndView newStoreSelf(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        return newStore(request, response, 1);
    }

    /**
     * 别人推荐给渠道的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 别人推荐给渠道的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_NEW_STORE_OTHER, method = RequestMethod.GET)
    public ModelAndView newStoreOther(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        return newStore(request, response, 2);
    }

    /**
     * 根据type查询属于当前渠道发展或推荐的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @param type 1为自己发展, 2为别人推荐
     * @return 对应的响应页面
     */
    private ModelAndView newStore(HttpServletRequest request, HttpServletResponse response, int type) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        List<AgentRecommendRecord> storeRecommendRecords = null;
        String view = null;
        if (type == 1) {
            view = View.AgentDetail.NEW_STORE_SELF;
            storeRecommendRecords = agentInfoService.getSelfRecommendsByType(
                    agentInfo.getAgentId(), 1);
        }
        else if (type == 2) {
            view = View.AgentDetail.NEW_STORE_OTHER;
            storeRecommendRecords = agentInfoService.getOtherRecommendsByType(
                    agentInfo.getAgentId(), 1);
        }
        logger.info("got recommend record size: " + storeRecommendRecords.size());
        ModelAndView mav = new ModelAndView(view);
        List<StoreInfo> storeInfos = new ArrayList<StoreInfo>();
        Map<Integer, StoreAccount> mStoreAccounts = new HashMap<>();
        Map<Integer, Integer> recommedIds = new HashMap<Integer, Integer>();
        Map<Integer, AgentInfo> recommendAgents = new HashMap<Integer, AgentInfo>();
        if (storeRecommendRecords != null && !storeRecommendRecords.isEmpty()) {
            List<Integer> storeIds = new ArrayList<Integer>();
            for (AgentRecommendRecord agentRecommendRecord : storeRecommendRecords) {
                Integer storeId = agentRecommendRecord.getRecommendedId();
                storeIds.add(storeId);
                Integer recommendId = agentRecommendRecord.getRecommendId();
                if (recommendId != null) {
                    recommedIds.put(storeId, recommendId);
                }
            }
            List<StoreAccount> storeAccounts = storeInfoService
                    .getAccountByStoreIds(storeIds, 1);
            if (storeAccounts != null && !storeAccounts.isEmpty()) {
                storeIds = new ArrayList<Integer>();
                Map<Integer, Integer> nRecommedIds = new HashMap<Integer, Integer>();
                for (StoreAccount storeAccount : storeAccounts) {
                    Integer storeId = storeAccount.getStoreId();
                    storeIds.add(storeId);
                    mStoreAccounts.put(storeId, storeAccount);
                    if (recommedIds.containsKey(storeId)) {
                        nRecommedIds.put(storeId, recommedIds.get(storeId));
                    }
                    else {
                        recommendAgents.put(storeId, agentInfo);
                    }
                }
                storeInfos = storeInfoService.getStoreByStoreIds(storeIds);
                if (type == 2 && !recommedIds.isEmpty()) {
                    Set<Integer> rStoreIds = nRecommedIds.keySet();
                    for (Integer rStoreId : rStoreIds) {
                        AgentInfo recommendAgent = agentInfoService
                                .getByAgentId(nRecommedIds.get(rStoreId));
                        recommendAgents.put(rStoreId, recommendAgent);
                    }
                }
            }
        }
        mav.addObject("agentInfo", agentInfo);
        mav.addObject("storeInfos", storeInfos);
        mav.addObject("storeAccounts", mStoreAccounts);
        mav.addObject("recommendAgents", recommendAgents);
        return mav;
    }


    /**
     * 归于当前渠道下正常使用的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 归于当前渠道下正常使用的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_STORE_NORMAL, method = RequestMethod.GET)
    public ModelAndView storeNormal(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        return oldStore(request, response, 1); // 正常使用
    }

    /**
     * 归于当前渠道下需要续费了的门店, 即剩余使用时间少于7天的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 归于当前渠道下需要续费了的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_STORE_RENEW, method = RequestMethod.GET)
    public ModelAndView storeRenew(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        return oldStore(request, response, 2); // 正常使用
    }

    /**
     * 归于当前渠道下已过期的门店, 剩余使用时间为0
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 归于当前渠道下已过期的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_STORE_OVER, method = RequestMethod.GET)
    public ModelAndView storeOver(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        return oldStore(request, response, 3); // 已过期
    }

    /**
     * 根据type返回对应的归于当前渠道的门店的页面
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @param type 1为正常使用, 2为需要学费, 3为过期
     * @return type对应的页面
     */
    private ModelAndView oldStore(HttpServletRequest request, HttpServletResponse response, int type) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        String view = null;
        if (type == 1) { // 试运营, 正常运营
            view = View.AgentDetail.STORE_NORMAL;
        }
        else if (type == 2) { // 续费提醒
            view = View.AgentDetail.STORE_RENEW;
        }
        else if (type == 3) { // 已过期
            view = View.AgentDetail.STORE_OVER;
        }
        ModelAndView mav = new ModelAndView(view);
        AgentInfo agentInfo = agentInfoService.getByOpenId(getOpenId(3, request, response));
        List<AgentRecommendRecord> storeRecommendRecords = agentInfoService
                .getRecommendsByType(agentInfo.getAgentId(), 1);
        List<Integer> storeIds = new ArrayList<Integer>();
        Map<Integer, Integer> recommedIds = new HashMap<Integer, Integer>();
        Map<Integer, AgentInfo> recommendAgents = new HashMap<Integer, AgentInfo>();
        Map<Integer, StoreAccount> mStoreAccounts = new HashMap<>();
        List<StoreInfo> storeInfos = new ArrayList<StoreInfo>();
        if (storeRecommendRecords != null && !storeRecommendRecords.isEmpty()) {
            for (AgentRecommendRecord agentRecommendRecord : storeRecommendRecords) {
                Integer storeId = agentRecommendRecord.getRecommendedId();
                storeIds.add(storeId);
                Integer recommendId = agentRecommendRecord.getRecommendId();
                if (recommendId != null) {
                    recommedIds.put(storeId, recommendId);
                }
            }
            List<StoreAccount> storeAccounts = new ArrayList<StoreAccount>();
            switch (type) {
                case 1: {
                    storeAccounts = storeInfoService
                            .getNormalAccountByStoreIds(storeIds);
                    break;
                }
                case 2: {
                    storeAccounts = storeInfoService
                            .getRenewAccountByStoreIds(storeIds);
                    break;
                }
                case 3: {
                    storeAccounts = storeInfoService
                            .getOverAccountByStoreIds(storeIds);
                    break;
                }
                default:
                    break;
            }
            if (storeAccounts != null && !storeAccounts.isEmpty()) {
                storeIds = new ArrayList<Integer>();
                Map<Integer, Integer> nRecommedIds = new HashMap<Integer, Integer>();
                for (StoreAccount storeAccount : storeAccounts) {
                    Integer storeId = storeAccount.getStoreId();
                    storeIds.add(storeId);
                    mStoreAccounts.put(storeId, storeAccount);
                    if (recommedIds.containsKey(storeId)) {
                        nRecommedIds.put(storeId, recommedIds.get(storeId));
                    }
                    else {
                        recommendAgents.put(storeId, agentInfo);
                    }
                }
                storeInfos = storeInfoService.getStoreByStoreIds(storeIds);
                if (!recommedIds.isEmpty()) {
                    Set<Integer> rStoreIds = nRecommedIds.keySet();
                    for (Integer rStoreId : rStoreIds) {
                        AgentInfo recommendAgent = agentInfoService
                                .getByAgentId(nRecommedIds.get(rStoreId));
                        recommendAgents.put(rStoreId, recommendAgent);
                    }
                }
            }
        }
        mav.addObject("agentInfo", agentInfo);
        mav.addObject("storeInfos", storeInfos);
        mav.addObject("storeAccounts", mStoreAccounts);
        mav.addObject("recommendAgents", recommendAgents);

        return mav;
    }

    /**
     * 我推荐的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 我推荐的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_SHARE_STORE, method = RequestMethod.GET)
    public ModelAndView myRecommendStore(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        List<Integer> storeIds = new ArrayList<Integer>();

        Map<Integer, AgentInfo> agents = new HashMap<Integer, AgentInfo>();
        Map<Integer, StoreAccount> mStoreAccounts = new HashMap<>();
        List<StoreInfo> storeInfos = new ArrayList<StoreInfo>();
        Map<Integer, Integer> agentIds = new HashMap<Integer, Integer>();
        List<AgentRecommendRecord> storeRecommendRecords = agentInfoService
                .getMyRecommendsByType(agentInfo.getAgentId(), 1);
        if (storeRecommendRecords != null && !storeRecommendRecords.isEmpty()) {
            for (AgentRecommendRecord agentRecommendRecord : storeRecommendRecords) {
                Integer storeId = agentRecommendRecord.getRecommendedId();
                storeIds.add(storeId);
                Integer agentId = agentRecommendRecord.getAgentId();
                agentIds.put(storeId, agentId);
            }

            List<StoreAccount> storeAccounts = storeInfoService
                    .getAccountByStoreIds(storeIds);
            if (storeAccounts != null && !storeAccounts.isEmpty()) {
                Map<Integer, Integer> nAgentIds = new HashMap<Integer, Integer>();
                storeIds = new ArrayList<Integer>();
                for (StoreAccount storeAccount : storeAccounts) {
                    Integer storeId = storeAccount.getStoreId();
                    storeIds.add(storeId);
                    mStoreAccounts.put(storeId, storeAccount);
                    if (agentIds.containsKey(storeId)) {
                        nAgentIds.put(storeId, agentIds.get(storeId));
                    }
                }
                storeInfos = storeInfoService.getStoreByStoreIds(storeIds);
                if (!nAgentIds.isEmpty()) {
                    Set<Integer> rStoreIds = nAgentIds.keySet();
                    for (Integer rStoreId : rStoreIds) {
                        AgentInfo recommendAgent = agentInfoService
                                .getByAgentId(nAgentIds.get(rStoreId));
                        agents.put(rStoreId, recommendAgent);
                    }
                }
            }
        }
        ModelAndView mav = new ModelAndView(View.AgentDetail.STORE_MY_RECOMMEND);
        mav.addObject("agentInfo", agentInfo);
        mav.addObject("storeInfos", storeInfos);
        mav.addObject("storeAccounts", mStoreAccounts);
        mav.addObject("agents", agents);
        return mav;
    }

    /**
     * 我推荐的渠道
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 我推荐的渠道页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_SHARE_AGENT, method = RequestMethod.GET)
    public ModelAndView myRecommendAgent(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        List<AgentRecommendRecord> agentRecommendRecords = agentInfoService.getMyRecommendAgents(agentInfo.getAgentId());
        List<Integer> recommendedIds = new ArrayList<Integer>();
        // Map<Integer, Integer> localIds = new HashMap<Integer, Integer>();
        Map<Integer, AgentAccount> mRecommendedAgentAccounts = new HashMap<Integer, AgentAccount>();
        // Map<Integer, AgentInfo> loaclAgentInfos = new HashMap<Integer,
        // AgentInfo>();
        List<AgentInfo> recommededAgentInfos = new ArrayList<AgentInfo>();
        if (agentRecommendRecords != null && !agentRecommendRecords.isEmpty()) {
            for (AgentRecommendRecord agentRecommendRecord : agentRecommendRecords) {
                Integer recommendedId = agentRecommendRecord.getRecommendedId();
                recommendedIds.add(recommendedId);
                // localIds.put(recommendedId,
                // agentRecommendRecord.getAgentId());
            }
            List<AgentAccount> recommendedAgentAccounts = agentInfoService
                    .getAccountByAgentIds(recommendedIds);
            if (recommendedAgentAccounts != null
                    && !recommendedAgentAccounts.isEmpty()) {
                // Map<Integer, Integer> nLocalIds = new HashMap<Integer,
                // Integer>();
                recommendedIds = new ArrayList<Integer>();
                for (AgentAccount recommendedAgentAccount : recommendedAgentAccounts) {
                    Integer agentId = recommendedAgentAccount.getAgentId();
                    recommendedIds.add(agentId);
                    mRecommendedAgentAccounts.put(agentId,
                            recommendedAgentAccount);
                    // if (localIds.containsKey(agentId)) {
                    // nLocalIds.put(agentId, localIds.get(agentId));
                    // }
                }
                recommededAgentInfos = agentInfoService
                        .getByAgentIds(recommendedIds.toArray(new Integer[] {}));
                // if (!nLocalIds.isEmpty()) {
                // Set<Integer> rRecommendedIds = nLocalIds.keySet();
                // for (Integer recommendedId : rRecommendedIds) {
                // AgentInfo localAgentInfo =
                // agentInfoService.getByAgentId(nLocalIds.get(recommendedId));
                // loaclAgentInfos.put(recommendedId, localAgentInfo);
                // }
                // }
            }

        }
        ModelAndView mav = new ModelAndView(View.AgentDetail.AGENT_MY_RECOMMEND);
        mav.addObject("agentInfo", agentInfo);
        mav.addObject("recommendedAgentAccounts", mRecommendedAgentAccounts);
        // mav.addObject("loaclAgentInfos", loaclAgentInfos);
        mav.addObject("recommededAgentInfos", recommededAgentInfos);
        return mav;
    }

    /**
     * 推荐给我的门店
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 推荐给我的门店页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_SHARED_STORE, method = RequestMethod.GET)
    public ModelAndView recommendToMeStore(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        List<AgentRecommendRecord> agentRecommendRecords = agentInfoService
                .getRecommendsToMeByType(agentInfo.getAgentId(), 1);
        Map<Integer, Integer> localIds = new HashMap<Integer, Integer>();
        List<Integer> storeIds = new ArrayList<Integer>();
        Map<Integer, StoreAccount> mStoreAccounts = new HashMap<Integer, StoreAccount>();
        List<StoreInfo> storeInfos = new ArrayList<StoreInfo>();
        Map<Integer, AgentInfo> loaclAgentInfos = new HashMap<Integer, AgentInfo>();
        if (agentRecommendRecords != null && !agentRecommendRecords.isEmpty()) {
            for (AgentRecommendRecord agentRecommendRecord : agentRecommendRecords) {
                Integer storeId = agentRecommendRecord.getRecommendedId();
                storeIds.add(storeId);
                Integer agentId = agentRecommendRecord.getRecommendId();
                localIds.put(storeId, agentId);
            }

            List<StoreAccount> storeAccounts = storeInfoService
                    .getAccountByStoreIds(storeIds);
            if (storeAccounts != null && !storeAccounts.isEmpty()) {
                Map<Integer, Integer> nlocalIds = new HashMap<Integer, Integer>();
                storeIds = new ArrayList<Integer>();
                for (StoreAccount storeAccount : storeAccounts) {
                    Integer storeId = storeAccount.getStoreId();
                    storeIds.add(storeId);
                    mStoreAccounts.put(storeId, storeAccount);
                    if (localIds.containsKey(storeId)) {
                        nlocalIds.put(storeId, localIds.get(storeId));
                    }
                }
                storeInfos = storeInfoService.getStoreByStoreIds(storeIds);
                if (!nlocalIds.isEmpty()) {
                    Set<Integer> rStoreIds = nlocalIds.keySet();
                    for (Integer storeId : rStoreIds) {
                        AgentInfo localAgentInfo = agentInfoService
                                .getByAgentId(nlocalIds.get(storeId));
                        loaclAgentInfos.put(storeId, localAgentInfo);
                    }
                }
            }
        }
        ModelAndView mav = new ModelAndView(
                View.AgentDetail.STORE_RECOMMEMND_TO_ME);
        mav.addObject("agentInfo", agentInfo);
        mav.addObject("storeInfos", storeInfos);
        mav.addObject("storeAccounts", mStoreAccounts);
        mav.addObject("loaclAgentInfos", loaclAgentInfos);
        return mav;

    }

    /**
     * 业绩排行
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @return 业绩排行页面
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_STAT, method = RequestMethod.GET)
    public ModelAndView stat(HttpServletRequest request) {

        return null;
    }


    /**
     * 当前渠道及归于其下的所有门店的收益
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 当前渠道收益页
     */
    @RequestMapping(value = Url.AgentDetail.VIEW_DETAIL_INCOME, method = RequestMethod.GET)
    public ModelAndView income(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }

        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        Integer agentId = agentInfo.getAgentId();
        AgentAccount agentAccount = agentInfoService
                .getAccountByAgentId(agentId);
        ModelAndView mav = new ModelAndView(View.AgentDetail.INCOME);

        int incomeYear = Calendar.getInstance().get(Calendar.YEAR);
        String begin = incomeYear + "-01-01";
        String end = DateFormatUtils.format(DateUtils.addDays(new Date(), 1),
                "yyyy-MM-dd");
        List<IncomeDto> agentIncomes = agentInfoService.getMonthIncomeByTime(
                agentId, begin, end);
        Map<String, IncomeDto> mAgentIncomes = new HashMap<String, IncomeDto>();
        if (agentIncomes != null && !agentIncomes.isEmpty()) {
            for (IncomeDto incomeDto : agentIncomes) {
                mAgentIncomes.put(incomeDto.getTime(), incomeDto);
            }
        }
        mav.addObject("agentIncomes", mAgentIncomes);
        mav.addObject("incomeYear", incomeYear);

        List<AgentRecommendRecord> agentRecommendRecords = agentInfoService
                .getRecommendsByType(agentId, 1);
        StoresIncome agentStoresIncome = computeStoresIncomes(
                agentRecommendRecords, begin, end);
        Map<String, Double> singleMonthsIncome = agentStoresIncome
                .getSingleMonthsIncome();
        Map<String, Double> chainMonthsIncome = agentStoresIncome
                .getChainMonthsIncome();
        Double singleTotalIncome = agentStoresIncome.getSingleTotalIncome();
        Double chainTotalIncome = agentStoresIncome.getChainTotalIncome();
        Double totalIncome = agentStoresIncome.getTotalIncome();

        List<AgentRecommendRecord> myRecommendRecords = agentInfoService
                .getMyRecommendsByType(agentId, 1);
        StoresIncome myRecStoresIncome = computeStoresIncomes(
                myRecommendRecords, begin, end);
        Map<String, Double> myRecSingleMonthsIncome = myRecStoresIncome
                .getSingleMonthsIncome();
        Map<String, Double> myRecChainMonthsIncome = myRecStoresIncome
                .getChainMonthsIncome();
        Double myRecSingleTotalIncome = myRecStoresIncome
                .getSingleTotalIncome();
        Double myRecChainTotalIncome = myRecStoresIncome.getChainTotalIncome();

        List<AgentRecommendRecord> recMeRecommendRecords = agentInfoService
                .getRecommendsToMeByType(agentId, 1);
        StoresIncome recMeStoresIncome = computeStoresIncomes(
                recMeRecommendRecords, begin, end);
        Map<String, Double> recMeSingleMonthsIncome = recMeStoresIncome
                .getSingleMonthsIncome();
        Map<String, Double> recMeChainMonthsIncome = recMeStoresIncome
                .getChainMonthsIncome();
        Double recMeSingleTotalIncome = recMeStoresIncome
                .getSingleTotalIncome();
        Double recMeChainTotalIncome = recMeStoresIncome.getChainTotalIncome();

        Double recTotalIncome = myRecStoresIncome.getTotalIncome()
                + recMeStoresIncome.getTotalIncome();

        mav.addObject("singleMonthsIncome", singleMonthsIncome);
        mav.addObject("chainMonthsIncome", chainMonthsIncome);
        mav.addObject("singleTotalIncome", singleTotalIncome);
        mav.addObject("chainTotalIncome", chainTotalIncome);
        mav.addObject("totalIncome", totalIncome);

        mav.addObject("myRecSingleMonthsIncome", myRecSingleMonthsIncome);
        mav.addObject("myRecChainMonthsIncome", myRecChainMonthsIncome);
        mav.addObject("myRecSingleTotalIncome", myRecSingleTotalIncome);
        mav.addObject("myRecChainTotalIncome", myRecChainTotalIncome);

        mav.addObject("recMeSingleMonthsIncome", recMeSingleMonthsIncome);
        mav.addObject("recMeChainMonthsIncome", recMeChainMonthsIncome);
        mav.addObject("recMeSingleTotalIncome", recMeSingleTotalIncome);
        mav.addObject("recMeChainTotalIncome", recMeChainTotalIncome);

        mav.addObject("recTotalIncome", recTotalIncome);

        mav.addObject("agentAccount", agentAccount);
        mav.addObject("agentInfo", agentInfo);
        return mav;
    }

    /**
     * 计算当前渠道及归于其下的所有门店的收益
     * @author gebing
     * @date 2015年12月4日
     * @param recommendRecords 归于当前渠道下的所有门店的推荐记录
     * @param begin 开始时间
     * @param end 结束时间
     * @return 当前渠道及归于其下的所有门店的收益
     */
    private StoresIncome computeStoresIncomes(
            List<AgentRecommendRecord> recommendRecords, String begin,
            String end) {
        StoresIncome storesIncome = new StoresIncome(recommendRecords, begin,
                end);
        storesIncome.compulte();
        return storesIncome;
    }


    /**
     * 多个门店的收益的汇总
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
     * @date 2015年12月4日
     */
    public final class StoresIncome {

        /**
         * 归于某个渠道下的所有门店
         */
        private List<AgentRecommendRecord> recommendRecords;
        /**
         * 开始时间
         */
        private String begin;
        /**
         * 结束时间
         */
        private String end;

        /**
         * {@link #recommendRecords}里单店按月份的收益记录
         */
        private Map<String, Double> singleMonthsIncome = new HashMap<String, Double>();
        /**
         * {@link #recommendRecords}里连锁分店按月份的收益记录
         */
        private Map<String, Double> chainMonthsIncome = new HashMap<String, Double>();
        /**
         * {@link #recommendRecords}里单店总收益
         */
        private Double singleTotalIncome = new Double(0);
        /**
         * {@link #recommendRecords}里连锁分店总收益
         */
        private Double chainTotalIncome = new Double(0);

        /**
         * StoresIncome 构造函数
         * @param recommendRecords 归于某个渠道下的所有门店
         * @param begin 开始时间
         * @param end 结束时间
         */
        public StoresIncome(List<AgentRecommendRecord> recommendRecords,
                String begin, String end) {
            this.recommendRecords = recommendRecords;
            this.begin = begin;
            this.end = end;
        }

        /**
         * 对记录进行查询计算
         * @author gebing
         * @date 2015年12月4日
         */
        public void compulte() {
            if (recommendRecords != null && !recommendRecords.isEmpty()) {
                List<Integer> storeIds = new ArrayList<Integer>();
                for (AgentRecommendRecord record : recommendRecords) {
                    Integer storeId = record.getRecommendedId();
                    storeIds.add(storeId);
                }
                List<StoreIncomeDto> storeIncomes = storeInfoService
                        .getMotnIncomesByTime(storeIds, begin, end);
                List<StoreInfo> storeInfos = storeInfoService
                        .getStoreByStoreIds(storeIds);
                if (storeIncomes != null && !storeIncomes.isEmpty()
                        && storeInfos != null && !storeInfos.isEmpty()) {
                    Map<Integer, StoreInfo> sigleStores = new HashMap<Integer, StoreInfo>();
                    Map<Integer, StoreInfo> chainStores = new HashMap<Integer, StoreInfo>();
                    for (StoreInfo storeInfo : storeInfos) {
                        Integer storeType = storeInfo.getStoreType();
                        Integer storeId = storeInfo.getStoreId();
                        switch (storeType) {
                            case 1: {
                                sigleStores.put(storeId, storeInfo);
                                continue;
                            }
                            case 3: {
                                chainStores.put(storeId, storeInfo);
                                continue;
                            }
                            default:
                                continue;
                        }
                    }
                    for (StoreIncomeDto storeIncome : storeIncomes) {
                        Integer storeId = storeIncome.getStoreId();
                        String month = storeIncome.getTime();
                        double income = storeIncome.getIncome();
                        if (sigleStores.containsKey(storeId)) {
                            if (!singleMonthsIncome.containsKey(month)) {
                                singleMonthsIncome.put(month, new Double(0));
                            }
                            singleMonthsIncome.put(month,
                                    singleMonthsIncome.get(month) + income);
                            singleTotalIncome += income;
                            continue;
                        }
                        if (chainStores.containsKey(storeId)) {
                            if (!chainMonthsIncome.containsKey(month)) {
                                chainMonthsIncome.put(month, new Double(0));
                            }
                            chainMonthsIncome.put(month,
                                    chainMonthsIncome.get(month) + income);
                            chainTotalIncome += income;
                        }
                    }
                }
            }
        }

        /**
         * 单店每月收益
         * @author gebing
         * @date 2015年12月4日
         * @return 单店每月收益
         */
        public Map<String, Double> getSingleMonthsIncome() {
            return singleMonthsIncome;
        }

        /**
         * 连锁分店每月收益
         * @author gebing
         * @date 2015年12月4日
         * @return 连锁分店每月收益
         */
        public Map<String, Double> getChainMonthsIncome() {
            return chainMonthsIncome;
        }

        /**
         * 单店总收益
         * @author gebing
         * @date 2015年12月4日
         * @return 单店总收益
         */
        public Double getSingleTotalIncome() {
            return singleTotalIncome;
        }

        /**
         * 连锁分店总收益
         * @author gebing
         * @date 2015年12月4日
         * @return  连锁分店总收益
         */
        public Double getChainTotalIncome() {
            return chainTotalIncome;
        }

        /**
         * 单店和连锁分店总收益
         * @author gebing
         * @date 2015年12月4日
         * @return  单店和连锁分店总收益
         */
        public Double getTotalIncome() {
            return singleTotalIncome + chainTotalIncome;
        }

    }
}
