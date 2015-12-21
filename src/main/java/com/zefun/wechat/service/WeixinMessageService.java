package com.zefun.wechat.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.dto.WechatMenuDto;
import com.zefun.web.entity.AutomaticKey;
import com.zefun.web.entity.AutomaticReply;
import com.zefun.web.entity.CopyMenu;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.ItemCensus;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.Menu;
import com.zefun.web.entity.MsgReply;
import com.zefun.web.entity.PictureLibrary;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.entity.WechatSubscribe;
import com.zefun.web.mapper.AutomaticKeyMapper;
import com.zefun.web.mapper.AutomaticReplyMapper;
import com.zefun.web.mapper.CopyMenuMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.ItemCensusMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.MenuMapper;
import com.zefun.web.mapper.MsgReplyMapper;
import com.zefun.web.mapper.PictureLibraryMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.web.mapper.WechatSubscribeMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.RedisService;
import com.zefun.wechat.dto.ArticleDto;
import com.zefun.wechat.dto.ButtonDto;
import com.zefun.wechat.dto.CommonButtonDto;
import com.zefun.wechat.dto.ComplexButtonDto;
import com.zefun.wechat.dto.MediaIdDto;
import com.zefun.wechat.dto.MenuDto;
import com.zefun.wechat.dto.NewsMessageDto;
import com.zefun.wechat.dto.SendMessagesDto;
import com.zefun.wechat.dto.TextMessageDto;
import com.zefun.wechat.dto.ViewButtonDto;

/**
 * 微信端消息service
* @author 高国藩
* @date 2015年8月11日 上午11:47:56
 */
@Service
public class WeixinMessageService {
    /**微信数据*/
	@Autowired
	private WeixinUploadService weixinUploadService;
    /** 图文消息*/
	@Autowired
	private AutomaticReplyMapper automaticReplyMapper;
	/** 图片库*/
	@Autowired
	private PictureLibraryMapper pictureLibraryMapper;
    /** 菜单点击匹配想*/
	@Autowired
	private AutomaticKeyMapper automaticKeyMapper;
    /** 菜单处理*/
	@Autowired
	private MenuMapper menuMapper;
    /** 自动回复信息*/
	@Autowired
	private MsgReplyMapper msgReplyMapper;
	/** 图文消息统计*/
	@Autowired
	private ItemCensusMapper censusMapper;
	/**会员等级*/
	@Autowired
	private MemberLevelMapper memberLevelMapper;
	/**会员信息*/
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    /** 会员账户数据操作对象 */
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    /** 会员礼金明细操作对象 */
    @Autowired
    private GiftmoneyDetailMapper giftmoneyDetailMapper;
    /**会员礼金流水操作对象*/
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
	/**筛选器*/
	@Autowired
	private MemberScreeningMapper memberScreeningMapper;
	/**redis*/
	@Autowired
	private RedisService redisService;
	/**微信关注操作对象*/
	@Autowired
	private WechatSubscribeMapper wechatSubscribeMapper;
	/**微信会员映射操作对象*/
	@Autowired
	private WechatMemberMapper wechatMemberMapper;
	/**会员优惠券操作对象*/
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	/**门店基础数据操作对象*/
	@Autowired
	private StoreSettingMapper storeSettingMapper;
	/**复制菜单信息表*/
    @Autowired
	private CopyMenuMapper copyMenuMapper;
    /**门店微信数据关联*/
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    /**获取accessToken*/
    @Autowired
    private WeixinConfigService weixinConfigService;
    /**会员信息服务对象*/
    @Autowired
    private MemberInfoService memberInfoService;
    /**查询门店信息*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
	/** 日志*/
	private Logger logger = Logger.getLogger(WeixinMessageService.class);
	
	/**
	 * 处理微信发来的请求
	* @author 高国藩
	* @date 2015年8月11日 上午11:29:11
	* @param request 请求封装
	* @return 返回展示信息
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = "";
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			logger.info("消息关注者 "+fromUserName);
			logger.info("开发者 "+toUserName);
			/**消息类型 */
			String msgType = requestMap.get("MsgType");
            /**事件推送*/
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				
				/**订阅*/
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				    /**根据微信开发者id进行查询门店设置内容*/
                    StoreWechat storeWechat = storeWechatMapper.selectByWechatId(toUserName);
                    
				    //如果用户是首次关注，需要查询门店是否有赠送内容
				    WechatSubscribe wechatSubscribe = wechatSubscribeMapper.selectByPrimaryKey(fromUserName);
				    
				    WechatSubscribe ws = new WechatSubscribe();
				    ws.setOpenId(fromUserName);
				    ws.setIsSubscribe(1);
				    String curTime = DateUtil.getCurTime();
				    ws.setUpdateTime(curTime);
				    //首次关注
				    if (wechatSubscribe == null) {
				        ws.setCreateTime(curTime);
				        wechatSubscribeMapper.insert(ws);
				        
				        //先检查该用户是否已注册为会员,如果已注册，查找会员所属门店的奖励，否则先记录奖励，后期注册时再进行赠送
				        String userId = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, fromUserName);
                        if (StringUtils.isNotBlank(userId)) {
                            int memberId = Integer.parseInt(userId);
                            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
                            StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(memberInfo.getStoreId());
                            //查看是否有优惠券奖励
                            String coupon = storeSetting.getFirstFollowCoupon();
                            String[] couponList = coupon.split(",");
                            if (StringUtils.isNotBlank(coupon)) {
                                for (String c : couponList) {
                                    int couponId = Integer.parseInt(c);
                                    MemberCoupon memberCoupon = new MemberCoupon();
                                    memberCoupon.setMemberInfoId(memberId);
                                    memberCoupon.setCouponId(couponId);
                                    memberCoupon.setIsUsed(0);
                                    memberCoupon.setGrantTime(curTime);
                                    memberCouponMapper.insert(memberCoupon);
                                }
                            }
                            
                            //查看是否有礼金奖励
                            int gift = storeSetting.getFirstFollowGift();
                            if (gift > 0) {
                                BigDecimal money = new BigDecimal(gift);
                                //增加礼金余额
                                Map<String, Object> giftParams = new HashMap<String, Object>(5);
                                giftParams.put("accountId", memberId);
                                giftParams.put("totalGiftmoneyAmount", money);
                                giftParams.put("balanceGiftmoneyAmount", money);
                                memberAccountMapper.updateGiftmoney(giftParams);
                                
                                //增加礼金明细
                                GiftmoneyDetail giftmoneyDetail = new GiftmoneyDetail();
                                giftmoneyDetail.setAccountId(memberId);
                                giftmoneyDetail.setTotalAmount(money);
                                giftmoneyDetail.setNowMoney(money);
                                giftmoneyDetail.setResidueNowMoney(money);
                                giftmoneyDetail.setPartNumber(0);
                                giftmoneyDetail.setPartType(1);
                                giftmoneyDetail.setIsPresent(1);
                                giftmoneyDetail.setStartDate(DateUtil.getCurDate());
                                giftmoneyDetail.setCreateTime(DateUtil.getCurTime());
                                giftmoneyDetail.setIsDeleted(0);
                                giftmoneyDetailMapper.insert(giftmoneyDetail);
                                
                                //增加礼金流水
                                GiftmoneyFlow giftmoneyFlow = new GiftmoneyFlow();
                                giftmoneyFlow.setAccountId(memberId);
                                giftmoneyFlow.setFlowType(2);
                                giftmoneyFlow.setFlowAmount(money);
                                giftmoneyFlow.setFlowTime(DateUtil.getCurTime());
                                giftmoneyFlow.setBusinessType("首次关注赠送");
                                giftmoneyFlow.setBusinessDesc("首次关注赠送");
                                giftmoneyFlow.setIsDeleted(0);
                                giftmoneyFlowMapper.insert(giftmoneyFlow);
                                memberInfoService.wipeCache(memberId);
                            }
                        }
                        else {
                            redisService.sadd(App.Redis.WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET, fromUserName);
                        }
				    } 
				    //再次关注
				    else {
				        wechatSubscribeMapper.updateByPrimaryKey(ws);
				    }
                    redisService.hset(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, fromUserName, "1");
                    
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("storeId", storeWechat.getStoreId());
					map.put("msgStatus", 1);
					MsgReply msgReply =  msgReplyMapper.selectReplyByParam(map);
					/**判断回复类型进行回复*/
					if (msgReply!=null&&msgReply.getMsgType().equals("text")) {
						return replyTextMessage(msgReply.getMsgText(), fromUserName, toUserName);
					} 
					else if (msgReply!=null&&msgReply.getMsgType().equals("news")) {
						/**回复图文消息*/
						return replyNewsMessage(msgReply.getMediaId(), fromUserName, toUserName);
					}
				}
				/** 取消订阅*/
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    WechatSubscribe ws = new WechatSubscribe();
                    ws.setOpenId(fromUserName);
                    ws.setIsSubscribe(0);
                    ws.setUpdateTime(DateUtil.getCurTime());
                    wechatSubscribeMapper.updateByPrimaryKey(ws);
                    redisService.hset(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, fromUserName, "0");
                }
				/**自定义菜单点击事件*/
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					/**事件KEY值，与创建自定义菜单时指定的KEY值对应*/
					String eventKey = requestMap.get("EventKey");
					/**此处用key去查找回复类型,如果是1说明文字回复,如果是2,查找图文消息素材*/
					AutomaticKey automaticKey = automaticKeyMapper.selectRespByKey(eventKey);
					if (automaticKey.getAutomaticType()==1){
						/**回复文本消息*/
						String respContent = automaticKey.getAutomaticText();
						return replyTextMessage(respContent, fromUserName, toUserName);
					}
					else {
						/**回复图文消息*/
						return replyNewsMessage(automaticKey.getMediaId(), fromUserName, toUserName);
					}
				}
				/**推送图文消息发送状态*/
				else if (eventType.equals(MessageUtil.EVENT_ITEMS_STATUS)){
				    String msgId = requestMap.get("MsgID");
				    ItemCensus itemCensus = censusMapper.selectByMsgId(msgId);
				    String status = requestMap.get("Status");
				    if (status.equals("send success")) {
				        status = "成功";
				    }
				    String sentCount = requestMap.get("SentCount");
				    String errorCount = requestMap.get("ErrorCount");
				    itemCensus.setSentCount(sentCount);
				    itemCensus.setErrorCount(errorCount);
				    itemCensus.setMsgStatus(status);
				    censusMapper.updateByPrimaryKey(itemCensus);
				    logger.info("发送的图文消息msgId为:"+msgId+",正在更新状态...");
				    /**在redis中注册数据,暂时只是统计了一个mediaId被家发送,但是没有更新门店中的分数*/
				    //redisSentinelService.zadd(itemCensus.getMediaId(), 1, itemCensus.getStoreId().toString());
				}
			}
			/**消息回复信息*/
			else if (msgType.equals("text")||msgType.equals("voice")||msgType.equals("image")){
				/**还没有成为会员,自动回复文字*/
                /**根据微信开发者id进行查询门店设置内容*/
                StoreWechat storeWechat = storeWechatMapper.selectByWechatId(toUserName);
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("storeId", storeWechat.getStoreId());
				map.put("msgStatus", 2);
				MsgReply msgReply =  msgReplyMapper.selectReplyByParam(map);
				/**判断回复类型进行回复*/
				if (msgReply!=null&&msgReply.getMsgType().equals("text")){
					return replyTextMessage(msgReply.getMsgText(), fromUserName, toUserName);
				}
				else if (msgReply!=null&&msgReply.getMsgType().equals("news")){
					/**回复图文消息*/
					return replyNewsMessage(msgReply.getMediaId(), fromUserName, toUserName);
				}
			}
			/**查看响应信息*/
			logger.info("respMessage" + respMessage);
	    } 
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 新增菜单-本地库存
	* @author 高国藩
	* @date 2015年8月11日 上午11:34:35
	* @param menu 菜单
	* @return 状态信息
	 */
	public BaseDto addMenu(Menu menu) {
		menu.setStoreId(1);
		int ok = menuMapper.insert(menu);
		if (ok>0){
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, menu);
		}
		else {
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
		}
	}

	/**
	 * 获得全部菜单-准备上传至微信,也可用于菜单信息的展示
	* @author 高国藩
	* @date 2015年8月11日 上午11:37:15
	* @param storeId 门店ID
	* @return 返回菜单信息
	 */
	public List<WechatMenuDto> getAllMenu(Integer storeId) {
		return menuMapper.selectAllMenu(storeId);
	}
	
	/**
	 * 1.微信上传菜单-调用微信接口将菜单信息上传
	* @author 高国藩
	* @date 2015年8月6日 下午3:59:54
	* @param menu 菜单
	* @param ACCESS_TOKEN 微信认证
	* @return 状态信息
	 */
	public BaseDto updateCreateMenu(MenuDto menu, String ACCESS_TOKEN) {
		String url = Url.Wechat.CREATE_MENU.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		String jsonMenu = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = WeixinUploadService.httpRequest(url, "POST", jsonMenu);
		int ok = jsonObject.getInt("errcode");
		if (ok==0){
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
		}
		else {
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
		}
	}
	
	/**
	 * 2.微信上传菜单-将从库中的菜单信息上传至微信
	* @author 高国藩
	* @date 2015年8月11日 上午11:38:50
	* @param storeId 門店信息
	* @param ACCESS_TOKEN 微信認證
	* @return 状态信息
	 */
	public BaseDto createMenu(Integer storeId, String ACCESS_TOKEN) {
		MenuDto menuDto = new MenuDto();
		List<ButtonDto> buttonDtos = new ArrayList<ButtonDto>();
		/**从数据库中获取菜单信息*/
		List<WechatMenuDto> ls = this.getAllMenu(0);
		for (int i = 0; i < ls.size(); i++) {
		    WechatMenuDto menu = ls.get(i);
			List<WechatMenuDto> menus = menu.getMenus();
			/**如果一级菜单没有二级菜单*/
			if (menus==null||menus.size()<=0){
				if (menu.getMenuType()==null||menu.getMenuType().equals("click")){
					/**保存一个点击按钮*/
					CommonButtonDto commonButtonDto = new CommonButtonDto();
					commonButtonDto.setName(menu.getMenuName());
					commonButtonDto.setType("click");
					commonButtonDto.setKey(menu.getMenuId().toString());
					buttonDtos.add(commonButtonDto);
				}
				else {
					/**保存一个链接按钮*/
					ViewButtonDto viewButtonDto = new ViewButtonDto();
					viewButtonDto.setName(menu.getMenuName());
					viewButtonDto.setType("view");
					String url = menu.getMenuUrl().replace("storeId", storeId.toString());
					viewButtonDto.setUrl(url);
					buttonDtos.add(viewButtonDto);
				}
			}
			else {
				/**一级菜单有二级菜单,新增一个复杂按钮,里面包裹普通按钮*/
				ComplexButtonDto complexButtonDto = new ComplexButtonDto();
				complexButtonDto.setName(menu.getMenuName());
				List<ButtonDto> button2 = new ArrayList<ButtonDto>();
				for (int j = 0; j < menus.size(); j++) {
				    WechatMenuDto menu2 = menus.get(j);
					if (menu2.getMenuType()==null||menu2.getMenuType().equals("click")){
						CommonButtonDto commonButtonDto = new CommonButtonDto();
						commonButtonDto.setName(menu2.getMenuName());
						commonButtonDto.setType("click");
						commonButtonDto.setKey(menu2.getMenuId().toString());
						button2.add(commonButtonDto);
					}
					else {
						ViewButtonDto viewButtonDto = new ViewButtonDto();
						viewButtonDto.setName(menu2.getMenuName());
						viewButtonDto.setType("view");
						String url = menu2.getMenuUrl().replace("storeId", storeId.toString());
						viewButtonDto.setUrl(url);
						button2.add(viewButtonDto);
					}
				}
				complexButtonDto.setSub_button(button2);
				buttonDtos.add(complexButtonDto);
			}
		}
		menuDto.setButton(buttonDtos);
		return this.updateCreateMenu(menuDto, ACCESS_TOKEN);
	}

	/**
	 * 菜单设置页面 
	* @author 高国藩
	* @date 2015年8月11日 上午11:40:21
	* @param request 请求封装
	* @return 返回页面
	 */
	public ModelAndView sendViewPage(HttpServletRequest request) {
		Integer storeId = 0;
		/**菜单信息*/
		List<WechatMenuDto> menus = this.getAllMenu(storeId);
		/**图文消息链接信息*/
		List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByStoreId(storeId);
		for (int i = 0; i < automaticReplies.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			String createTime = sdf.format(new Date(Long.parseLong(automaticReplies.get(i).getCreateTime())));
			automaticReplies.get(i).setCreateTime(createTime);
		}
		ModelAndView view = new ModelAndView(View.Wechat.MENU);
		view.addObject("menus", menus);
		view.addObject("automaticReplies", automaticReplies);
		return view;
	}

	/**
	 * 删除菜单
	* @author 高国藩
	* @date 2015年8月11日 上午11:40:43
	* @param menuId 菜单ID
	* @param storeId 门店ID
	* @return 状态信息
	 */
	public BaseDto deleteMenuAction(Integer menuId, Integer storeId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("storeId", storeId);
		map.put("menuId", menuId);
		int ok = menuMapper.deleteByParam(map);
		if (ok>0){
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
		}
		else {
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
		}
	}

	/**
	 * 修改菜单链接
	* @author 高国藩
	* @date 2015年8月11日 上午11:41:42
	* @param menu 菜单
	* @return 状态信息
	 */
	public BaseDto setMenuUrl(Menu menu) {
		menu.setMenuType("view");
		int ok = menuMapper.updateByPrimaryKeySelective(menu);
		if (ok>0){
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
		}
		else {
		    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
		}
	}

	/**
	 * 获取单一菜单元素
	* @author 高国藩
	* @date 2015年8月11日 上午11:42:39
	* @param menuId 菜单ID
	* @return 返回菜单
	 */
	public Menu getMenu(Integer menuId) {
		return menuMapper.selectByPrimaryKey(menuId);
	}
	
	/**
	 * 获得文本自动回复的信息
	* @author 高国藩
	* @date 2015年8月11日 上午11:42:57
	* @param context 文本内容
	* @param fromUserName 发送者
	* @param toUserName 接受者
	* @return 返回String
	 */
	public String replyTextMessage(String context, String fromUserName, String toUserName){
		/**回复文本消息*/
		TextMessageDto textMessage = new TextMessageDto();
		textMessage.setContent(context);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setMsgType("text");
		return MessageUtil.textMessageToXml(textMessage);
	}
	
	/**
	 * 回复图文消息
	* @author 高国藩
	* @date 2015年8月11日 上午11:47:10
	* @param mediaId 图文消息ID
	* @param fromUserName 发送者
	* @param toUserName 接受者
	* @return 返回String
	 */
	public String replyNewsMessage(String mediaId, String fromUserName, String toUserName){
		List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByMediaId(mediaId);
		NewsMessageDto newsMessage = new NewsMessageDto();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		List<ArticleDto> articleList = new ArrayList<ArticleDto>();
		for (int i = 0; i < automaticReplies.size(); i++) {
			AutomaticReply automaticReply = automaticReplies.get(i);
			ArticleDto articleDto = new ArticleDto();
			articleDto.setTitle(automaticReply.getTitle());
			articleDto.setDescription(automaticReply.getDescription());
			articleDto.setPicUrl(automaticReply.getPicUrl());
			articleDto.setUrl(automaticReply.getUrl());
			articleList.add(articleDto);
		}
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		newsMessage.setMsgType("news");
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	/**
	 * 群发图文消息
	* @author 高国藩
	* @date 2015年8月13日 上午10:52:45
    * @param level 会员卡等级
    * @param sceening 筛选器
    * @param mediaId 图文
    * @param accessToken 微信认证
    * @param storeId 门店
    * @param fatherMediaId 复制的图文消息
    * @return 接受信息
	 */
    public BaseDto sendMessagesItem(String level, String sceening, String mediaId,
            String accessToken, Integer storeId, String fatherMediaId) {
        String url = Url.Wechat.SEND_MESSAGEBYID;
        url = url.replace("ACCESS_TOKEN", accessToken);
        String[] sceenings = sceening.split(",");
        String[] levels = level.split(",");
        //查询出发送的会员名单
        List<Integer> ls = serchMemberIds(sceenings, levels);
        if (ls==null||ls.size()<=1){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "当前分组不满足发送条件");
        }
        //将会员名单中的发送次数为0的名单去除
        List<Integer> bs = memberScreeningMapper.selectSendItemsWechatCountNotZero(ls);
        //sql 记录本次发送的条件
        StringBuffer sql = new StringBuffer();
        if (sceening!=null&&!sceening.equals("")){
            for (int i = 0; i < sceenings.length; i++) {
                MemberScreening s = memberScreeningMapper.selectByPrimaryKey(Integer.valueOf(sceenings[i]));
                sql.append(s.toString());
            }
        }
        /**此处根据会员等级以及筛选器的名单查询会员信息,并获得openId*/
        List<String> touser = wechatMemberMapper.selectOpenIdsByMemberIdList(bs);
        MediaIdDto mpnews = new MediaIdDto(mediaId);
        SendMessagesDto message = new SendMessagesDto(touser, mpnews);
        JSONObject json = WeixinUploadService.httpRequest(url, "POST",
                  JSONObject.fromObject(message).toString());
        if (json.getString("errcode").equals("0")){
            String mediaIdMpnews = fatherMediaId;
            String msgId = json.getString("msg_id");
            ItemCensus census = new ItemCensus();
            census.setMediaId(mediaIdMpnews);
            census.setMsgId(msgId);
            census.setStoreId(storeId);
            census.setMsgTime(String.valueOf(new Date().getTime()));
            census.setHasGroup(sql.toString());
            censusMapper.insert(census);
            //将刚才发送的人员的微信次数-1
            memberScreeningMapper.updateWechatCountByMemberId(bs);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, WeixinConfigService.getErrorMessage().get(json.getString("errcode"))); 
        }
        if (fatherMediaId != null){
            weixinUploadService.mediaCountItems(storeId, fatherMediaId);
            //redisService.zadd(fatherMediaId, Double.valueOf("1"), storeId.toString());
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 群发文本消息
    * @author 高国藩
    * @date 2015年8月13日 上午11:13:06
    * @param touser 接受消息
    * @param text 文本信息
    * @param accessToken 微信认证
    * @return 状态码
     */
    public BaseDto sendMessagesText(List<String> touser, String text,
            String accessToken) {
        String url = Url.Wechat.SEND_MESSAGEBYID;
        url = url.replace("ACCESS_TOKEN", accessToken);
        SendMessagesDto message = new SendMessagesDto(touser, text);
        JSONObject json = WeixinUploadService.httpRequest(url, "POST",
                  JSONObject.fromObject(message).toString());
        return new BaseDto(0,  json.getString("errmsg"));
    }

    /**
     * 获取已经新增的图文消息
    * @author 高国藩
    * @date 2015年8月28日 下午4:17:07
    * @param storeId 门店信息
    * @param request 请求
    * @param response 返回结果
    * @return xinxi 
     * @throws IOException 
     */
    public ModelAndView getItems(Integer storeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**首先做一个校验,如果没有公众号测试,即不可点击*/
        Integer hqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        StoreWechat storeWechat = storeWechatMapper.selectByStoreId(hqStoreId);
        if (storeWechat == null){
            response.setContentType("text/html; charset=utf-8");
            PrintWriter pw=response.getWriter();
            pw.write("<script language='javascript'>"
                    + "alert('请设置公众号信息');history.back(-1);</script>");
            response.flushBuffer();
            return null;
        }
        /**智放图文库*/
        List<AutomaticReply> items = automaticReplyMapper.selectItemsByStoreId(App.Wechat.ZEFUN_STOREID);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setCreateTime(DateUtil.getDate(items.get(i).getCreateTime()));
            /*Long count = redisService.zcount(items.get(i).getMediaId(), 0l, 1l);
            items.get(i).setZcount(count);*/
        }
        /**我的图文库*/
        List<AutomaticReply> slefItems = automaticReplyMapper.selectItemsByStoreId(storeId); 
        for (int i = 0; i < slefItems.size(); i++) {
            slefItems.get(i).setCreateTime(DateUtil.getDate(slefItems.get(i).getCreateTime()));
        }
        /**图片库*/
        List<PictureLibrary> pictures = pictureLibraryMapper.selectPicturesByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.SHOW_ITEMS);
        view.addObject("items", items);
        view.addObject("slefItems", slefItems);
        view.addObject("pictures", pictures);
        return view;
    }
    

    /**
     * 根据mideaId查询图文消息
    * @author 高国藩
    * @date 2015年8月31日 上午11:25:57
    * @param mediaId 图文消息
    * @return 跳转附带图文消息信息
     */
    public ModelAndView sendUpdateItem(String mediaId) {
        Integer storeId = 1;
        List<AutomaticReply> ls = automaticReplyMapper.selectByMediaId(mediaId);
        /**图片库*/
        List<PictureLibrary> pictures = pictureLibraryMapper.selectPicturesByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.CHATE_ITME);
        view.addObject("pictureLibraries", pictures);
        view.addObject("items", ls);
        return view;
    }

    /**
     * 根据mideaId查询图文消息
    * @author 高国藩
    * @date 2015年8月31日 上午11:25:57
    * @param mediaId 图文消息
    * @return 跳转附带图文消息信息
     */
    public BaseDto getItemsByMediaId(String mediaId) {
        List<AutomaticReply> ls = automaticReplyMapper.selectByMediaId(mediaId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }

    /**
     * 图文消息状态信息
    * @author 高国藩
    * @date 2015年9月7日 下午4:47:43
    * @param storeId 门店
    * @return 封装数据
     */
    public ModelAndView listItemsStatus(Integer storeId) {
        List<AutomaticReply> ls = automaticReplyMapper.selectItemsByStoreId(storeId);
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setCreateTime(DateUtil.getDate(ls.get(i).getCreateTime()));;
        }
        List<MemberLevel> level = memberLevelMapper.selectByStoreId(storeId);
        List<MemberScreening> screen = memberScreeningMapper.selectByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.SEND_ITEMS);
        view.addObject("items", ls);
        view.addObject("level", level);
        view.addObject("screen", screen);
        return view;
    }

    /**
     * 自动回复页面数据加载
    * @author 高国藩
    * @date 2015年9月10日 上午10:23:27
    * @param storeId 根据门店信息查询已经设置的回复信息
    * @param response 返回数据
    * @return 跳转页面
     * @throws IOException 
     */
    public ModelAndView viewAutoReply(Integer storeId, HttpServletResponse response) throws IOException {
        /**首先做一个校验,如果没有公众号测试,即不可点击*/
        StoreWechat storeWechat = storeWechatMapper.selectByStoreId(storeId);
        if (storeWechat == null){
            response.setContentType("text/html; charset=utf-8");
            PrintWriter pw=response.getWriter();
            pw.write("<script language='javascript'>"
                    + "dialog('请先设置公众号');history.back(-1);</script>");
            return null;
        }
        //查询已经选择的回复信息
        Map<String, Integer> map= new HashMap<String, Integer>();
        map.put("storeId", storeId);
        map.put("msgStatus", 1);
        MsgReply autoReply = msgReplyMapper.selectReplyByParam(map);
        if (autoReply != null && autoReply.getMsgType().equals("news")){
            List<AutomaticReply> item = automaticReplyMapper.selectByMediaId(autoReply.getMediaId());
            autoReply.setAutomaticReply(item.get(0));
        }
        map.put("msgStatus", 2);
        MsgReply textReply = msgReplyMapper.selectReplyByParam(map);
        if (textReply != null && textReply.getMsgType().equals("news")){
            List<AutomaticReply> item = automaticReplyMapper.selectByMediaId(textReply.getMediaId());
            textReply.setAutomaticReply(item.get(0));
        }
        //图文消息封装
        List<AutomaticReply> ls = automaticReplyMapper.selectItemsByStoreId(storeId);
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setCreateTime(DateUtil.getDate(ls.get(i).getCreateTime()));
        }
        ModelAndView view = new ModelAndView(View.Wechat.VIEW_AUTO_REPLY);
        view.addObject("automaticReplys", ls);
        view.addObject("autoReply", autoReply);
        view.addObject("textReply", textReply);
        return view;
    }

    /**
     * 设置关注回复信息
    * @author 高国藩
    * @date 2015年9月10日 上午11:38:10
    * @param autoType 类型 text item
    * @param message 回复内容
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto actionFollowAutoReply(String autoType, String message, Integer storeId) {
        //回复图文消息
        if (autoType.equals("item")){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 1);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId(message);
                msgReply.setMsgText("");
                msgReply.setMsgType("news");
                msgReply.setMsgStatus(1);
                msgReply.setStoreId(storeId);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMediaId(message);
                reply.setMsgType("news");
                reply.setMsgStatus(1);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        //回复文本消息
        else {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 1);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId("");
                msgReply.setMsgText(message);
                msgReply.setMsgType("text");
                msgReply.setStoreId(storeId);
                msgReply.setMsgStatus(1);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMsgText(message);
                reply.setMsgType("text");
                reply.setMsgStatus(1);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 设置消息回复信息
    * @author 高国藩
    * @date 2015年9月10日 上午11:38:10
    * @param autoType 类型 text item
    * @param message 回复内容
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto actionTextAutoReply(String autoType, String message, Integer storeId) {
        //回复图文消息
        if (autoType.equals("item")){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 2);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId(message);
                msgReply.setMsgText("");
                msgReply.setMsgType("news");
                msgReply.setMsgStatus(2);
                msgReply.setStoreId(storeId);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMediaId(message);
                reply.setMsgType("news");
                reply.setMsgStatus(2);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        //回复文本消息
        else {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("storeId", storeId);
            map.put("msgStatus", 2);
            MsgReply reply = msgReplyMapper.selectReplyByParam(map);
            //如果不存在,就新增一个
            if (reply == null){
                MsgReply msgReply = new MsgReply();
                msgReply.setMediaId("");
                msgReply.setMsgText(message);
                msgReply.setMsgType("text");
                msgReply.setStoreId(storeId);
                msgReply.setMsgStatus(2);
                msgReplyMapper.insert(msgReply);
            }
            else {
                reply.setMsgText(message);
                reply.setMsgType("text");
                reply.setMsgStatus(2);
                msgReplyMapper.updateByPrimaryKey(reply);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 删除自动回复内容设置
    * @author 高国藩
    * @date 2015年9月10日 下午3:27:47
    * @param msgStatus msgStatus 删除的类型 1代表关注 2代表消息 
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto actionDeleteReply(Integer msgStatus, Integer storeId) {
        Map<String, Integer> map= new HashMap<String, Integer>();
        map.put("storeId", storeId);
        map.put("msgStatus", msgStatus);
        int ok = msgReplyMapper.deleteByParam(map);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 门店菜单展示页面
     * 1) 如果该门店没有开启,也就是库中没有改门店的菜单信息,那么只显示是否开启菜单功能
     * 2) 如果该门店已经开启了微信菜单,那么就将该菜单展示出来即可
    * @author 高国藩
    * @date 2015年9月20日 下午2:27:26
    * @param request 请求
    * @param storeId 门店ID
    * @return 跳转页面附带参数
     */
    public ModelAndView listStoreMenuAction(HttpServletRequest request,
            Integer storeId) {
        ModelAndView view = new ModelAndView(View.Wechat.STORE_MENU);
        Integer isHasCopy = copyMenuMapper.selectByStoreId(storeId);
        //如果已经存在不能再次开启
        if (isHasCopy!=null&&isHasCopy==1){
            /**菜单信息*/
            List<WechatMenuDto> menus = this.getAllMenu(0);
            view.addObject("menus", menus);
        }
        return view;
    }

    /**
     * 更新菜单
    * @author 高国藩
    * @date 2015年9月20日 下午4:02:42
    * @param storeId 门店
    * @param accessToken 微信认证
    * @return 状态
     */
    public BaseDto menuCopyAction(Integer storeId, String accessToken) {
        Integer isHasCopy = copyMenuMapper.selectByStoreId(storeId);
        //如果已经存在不能再次开启
        if (isHasCopy!=null&&isHasCopy==1){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您已经开启了菜单功能");
        }
        MenuDto menuDto = new MenuDto();
        List<ButtonDto> buttonDtos = new ArrayList<ButtonDto>();
        /**从数据库中获取菜单信息 0代表了开发商创建的菜单信息*/
        List<WechatMenuDto> ls = this.getAllMenu(0);
        for (int i = 0; i < ls.size(); i++) {
            WechatMenuDto menu = ls.get(i);
            List<WechatMenuDto> menus = menu.getMenus();
            /**如果一级菜单没有二级菜单*/
            if (menus==null||menus.size()<=0){
                if (menu.getMenuType()==null||menu.getMenuType().equals("click")){
                    /**保存一个点击按钮*/
                    CommonButtonDto commonButtonDto = new CommonButtonDto();
                    commonButtonDto.setName(menu.getMenuName());
                    commonButtonDto.setType("click");
                    commonButtonDto.setKey(menu.getMenuId().toString());
                    buttonDtos.add(commonButtonDto);
                }
                else {
                    /**保存一个链接按钮*/
                    ViewButtonDto viewButtonDto = new ViewButtonDto();
                    viewButtonDto.setName(menu.getMenuName());
                    viewButtonDto.setType("view");
                    viewButtonDto.setUrl(menu.getMenuUrl());
                    buttonDtos.add(viewButtonDto);
                }
            }
            else {
                /**一级菜单有二级菜单,新增一个复杂按钮,里面包裹普通按钮*/
                ComplexButtonDto complexButtonDto = new ComplexButtonDto();
                complexButtonDto.setName(menu.getMenuName());
                List<ButtonDto> button2 = new ArrayList<ButtonDto>();
                for (int j = 0; j < menus.size(); j++) {
                    WechatMenuDto menu2 = menus.get(j);
                    if (menu2.getMenuType()==null||menu2.getMenuType().equals("click")){
                        CommonButtonDto commonButtonDto = new CommonButtonDto();
                        commonButtonDto.setName(menu2.getMenuName());
                        commonButtonDto.setType("click");
                        commonButtonDto.setKey(menu2.getMenuId().toString());
                        button2.add(commonButtonDto);
                    }
                    else {
                        ViewButtonDto viewButtonDto = new ViewButtonDto();
                        viewButtonDto.setName(menu2.getMenuName());
                        viewButtonDto.setType("view");
                        viewButtonDto.setUrl(menu2.getMenuUrl());
                        button2.add(viewButtonDto);
                    }
                }
                complexButtonDto.setSub_button(button2);
                buttonDtos.add(complexButtonDto);
            }
        }
        menuDto.setButton(buttonDtos);
        BaseDto baseDto =  this.updateCreateMenu(menuDto, accessToken);
        if (baseDto.getCode() == App.System.API_RESULT_CODE_FOR_SUCCEES){
            //微信上传成功,新增入数据库中
            CopyMenu copyMenu = new CopyMenu();
            copyMenu.setCopyStatus(1);
            copyMenu.setStoreId(storeId);
            copyMenuMapper.insert(copyMenu);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 关闭微信菜单
    * @author 高国藩
    * @date 2015年9月20日 下午5:36:44
    * @param storeId 门店信息
    * @return 状态
     */
    public BaseDto closeMenusAction(Integer storeId) {
        int ok = copyMenuMapper.deleteByStoreId(storeId);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 查询出发送次数为0的人员名单作为提醒
    * @author 高国藩
    * @date 2015年10月7日 下午5:52:27
    * @param level 选择的会员等级
    * @param sceening 筛选器
    * @param storeId 门店信息
    * @return 返回有0的会员列表
     */
    public BaseDto checkWechatCount(String level, String sceening,
            Integer storeId) {
        String[] sceenings = sceening.split(",");
        String[] levels = level.split(",");
        List<Integer> ls = serchMemberIds(sceenings, levels);
        ls.add(0);
        List<MemberInfo> bs = memberInfoMapper.selectMemberInfoByWechatParams(ls);
        if (bs!=null&&bs.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, bs);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "全部满足条件");
    }

    /**
     * 根据关键字查询图文消息
    * @author 高国藩
    * @date 2015年10月8日 上午10:31:20
    * @param content 关键字搜索
    * @param storeId 门店,如果是0,查询智放,否则查询我的图文库
    * @return 将图文消息传出
     */
    public BaseDto actionSerchItems(String content, Integer storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        map.put("content", content);
        List<AutomaticReply> items = automaticReplyMapper.selectMediaIdByContent(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, items);
    }

    /**
     * 新增门店微信数据表
    * @author 高国藩
    * @date 2015年10月14日 下午6:25:26
    * @param storeWechat 数据
    * @return 返回信息
     */
    public BaseDto addStoreWechat(StoreWechat storeWechat) {
        //删除,再新增
        StoreWechat storeWechatDelete = storeWechatMapper.selectByStoreId(storeWechat.getStoreId());
        if (storeWechatDelete!=null){
            storeWechatMapper.deleteByPrimaryKey(storeWechatDelete.getRelatedId());
        }
        /*StoreWechat wechat = storeWechatMapper.selectByStoreId(storeWechat.getStoreId());
        if (wechat != null){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您已经绑定成功");
        }*/
        redisService.hset(App.Redis.STORE_WECHAT_APP_ID_KEY_HASH, storeWechat.getStoreId(), storeWechat.getWechatAppId());
        redisService.hset(App.Redis.STORE_WECHAT_APP_SECRET_KEY_HASH, storeWechat.getStoreId(), storeWechat.getWechatAppsecret());
        String accessToken = weixinConfigService.getAccessToken(storeWechat.getWechatAppId(), storeWechat.getWechatAppsecret());
        redisService.hset(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeWechat.getStoreId(), accessToken);
        BaseDto baseDto = createMenu(storeWechat.getStoreId(), accessToken);
        if (baseDto.getCode() == App.System.API_RESULT_CODE_FOR_SUCCEES){
            int ok = storeWechatMapper.insert(storeWechat);
            if (ok >= 0){
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "绑定成功");
            }
            else {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "绑定失败");
            }
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "绑定成功");
        }
    }

    /**
     * 我的公众号页面
    * @author 高国藩
    * @date 2015年9月30日 下午3:58:03
    * @param storeId 门店
    * @return 跳转页面
     */
    public ModelAndView viewOfficialAccounts(Integer storeId) {
        StoreWechat storeWechat = storeWechatMapper.selectByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.VIEW_OFFICAL);
        view.addObject("storeWechat", storeWechat);
        return view;
    }

    /**
     * 删除我的公众号数据绑定
    * @author 高国藩
    * @date 2015年10月14日 下午7:57:24
    * @param storeId 门店
    * @return 状态
     */
    public BaseDto deleteStoreWechat(Integer storeId) {
        StoreWechat storeWechat = storeWechatMapper.selectByStoreId(storeId);
        int ok = storeWechatMapper.deleteByPrimaryKey(storeWechat.getRelatedId());
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
    /**
     * 传入分组和会员等级,即可得到所有的会员ids
    * @author 高国藩
    * @date 2015年12月11日 下午2:48:57
    * @param groups    分组
    * @param level     会员等级
    * @return          会员ids
     */
    public List<Integer> serchMemberIds(String[] groups, String[] level){
        Set<Integer> sendsIds = new HashSet<>();
        List<ScreeningDto> dtos = memberScreeningMapper.selectByDtos(Arrays.asList(groups));
        for (int i = 0; i < dtos.size(); i++) {
            List<Integer> memberIds = memberInfoMapper.selectMemberIdsByDtos2(MessageUtil.transBean2Map(dtos.get(i)));
            sendsIds.addAll(memberIds);
        }
        List<String> levels = Arrays.asList(level);
        List<Integer> memberIds = memberInfoMapper.selectMemberIdsByLevelIds(levels);
        sendsIds.addAll(memberIds);
        //查询出发送的会员名单
        List<Integer> ls = new ArrayList<>();
        ls.addAll(sendsIds);
        return ls;
    }

}
