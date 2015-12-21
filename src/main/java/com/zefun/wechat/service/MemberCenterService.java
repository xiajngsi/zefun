package com.zefun.wechat.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.AppointDateDto;
import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CouponBaseDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.dto.MemberOrderDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderEvaluateDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.OrderPaymentDto;
import com.zefun.web.dto.ProjectAppointDto;
import com.zefun.web.dto.ProjectEvaluateDto;
import com.zefun.web.dto.SelfCashierDetailDto;
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EmployeeEvaluate;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectEvaluate;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectShare;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.WechatMember;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.EmployeeEvaluateMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectEvaluateMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectShareMapper;
import com.zefun.web.mapper.ShiftMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.ProjectService;
import com.zefun.web.service.QiniuService;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.SelfCashierService;

/**
 * 会员中心业务逻辑类
* @author 张进军
* @date Aug 19, 2015 11:01:22 AM 
*/
@Service
public class MemberCenterService {
    /** 会员信息业务逻辑对象 */
    @Autowired
    private MemberInfoService memberInfoService;
    
    /** 会员信息数据操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    
    /** 队列操作类 */
    @Autowired
    private RabbitService rabbitService;
    
    /** 微信与会员关联数据操作对象 */
    @Autowired
    private WechatMemberMapper wechatMemberMapper;
    
    /** 会员等级数据操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    
    /** 会员账户数据操作对象 */
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    
    /** 会员礼金明细操作对象 */
    @Autowired
    private GiftmoneyDetailMapper giftmoneyDetailMapper;
    
    /** 会员预约操作对象 */
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    /**员工班次操作对象*/
    @Autowired
    private ShiftMapper shiftMapper;
    
    /**积分流水操作对象*/
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    
    /**优惠券操作对象*/
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /**会员优惠券操作对象*/
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    
    /**会员卡金流水操作对象*/
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    
    /**会员礼金流水操作对象*/
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    
    /**会员套餐记录操作对象*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    
    /**订单信息操作对象*/
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    /**店铺信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**项目评价信息操作对象*/
    @Autowired 
    private ProjectEvaluateMapper projectEvaluateMapper;
    
    /** 七牛api操作服务类 */
    @Autowired
    private QiniuService qiniuService;
    
    /** 项目服务操作对象 */
    @Autowired
    private ProjectService projectService;
    
    /** 自助收银操作对象 */
    @Autowired
    private SelfCashierService selfCashierService;
    
    /** 订单详情操作对象 */
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	/**员工评价操作对象*/
	@Autowired
	private EmployeeEvaluateMapper employeeEvaluateMapper;
	
	/**员工信息操作对象*/
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	/**项目分享信息操作对象*/
	@Autowired
	private ProjectShareMapper projectShareMapper;
	
	/**项目信息操作对象*/
	@Autowired
	private ProjectInfoMapper projectInfoMapper;
	
	/**门店设置操作对象*/
	@Autowired
	private StoreSettingMapper storeSettingMapper;
	
	/**项目特定折扣操作对象*/
	@Autowired
	private ProjectDiscountMapper projectDiscountMapper;
	
	/**日志记录对象*/
	private final Logger logger = Logger.getLogger(MemberCenterService.class);
	
    /**
     * 查看会员主页
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @return           会员主页面
     */
    public ModelAndView homeView(Integer memberId){
        MemberBaseDto memberBaseInfo = memberInfoService.getMemberBaseInfo(memberId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.HOME);
        mav.addObject("memberBaseInfo", memberBaseInfo);
        return mav;
    }
    
    
    /**
     * 查看个人资料
    * @author 张进军
    * @date Oct 22, 2015 8:37:07 PM
    * @param memberId   会员标识
    * @return   个人资料页面
     */
    public ModelAndView infoView(Integer memberId) {
        MemberBaseDto memberBaseInfo = memberInfoService.getMemberBaseInfo(memberId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.INFO);
        mav.addObject("memberBaseInfo", memberBaseInfo);
        
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        //检查是否有设置支付密码
        if (StringUtils.isBlank(memberAccount.getPayPassword())) {
            mav.addObject("isPayCode", 0);
        }
        else {
            mav.addObject("isPayCode", 1);
        }
        
        return mav;
    }
    
    
    /**
     * 修改个人资料
    * @author 张进军
    * @date Oct 23, 2015 10:53:17 AM
    * @param memberId   会员标识    
    * @param name       昵称
    * @param headUrl    头像 
    * @param sex        性别
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto updateInfoAction(Integer memberId, String name, String headUrl, String sex) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        memberInfo.setName(name);
        memberInfo.setSex(sex);
        memberInfo.setHeadUrl(headUrl);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        
        memberInfoService.wipeCache(memberId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberInfo);
    }
    
    
    /**
     * 设置支付密码
    * @author 张进军
    * @date Nov 28, 2015 4:09:08 PM
    * @param memberId   会员标准
    * @param pwd        支付密码
    * @return           baseDto
     */
    public BaseDto setPwdAction(Integer memberId, String pwd) { 
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        
        //检查用户是否有设置密码
        if (StringUtils.isNotBlank(memberAccount.getPayPassword())) {
            return new BaseDto(9003, "您已设置过密码");
        }
        
        setPwd(pwd, memberAccount);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 修改支付密码
    * @author 张进军
    * @date Oct 23, 2015 10:56:04 AM
    * @param memberId   会员标识
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto updatePaycodeAction(Integer memberId, String oldPwd, String newPwd) {
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        
        //检查用户密码
        if (!StringUtil.mD5(oldPwd + memberAccount.getPasswordSalt()).equals(memberAccount.getPayPassword())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        setPwd(newPwd, memberAccount);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 修改支付密码
    * @author 张进军
    * @date Nov 28, 2015 4:12:25 PM
    * @param newPwd         新密码
    * @param memberAccount  需修改的账户
    * @return   0:失败，1:成功
     */
    private int setPwd(String newPwd, MemberAccount memberAccount){
        String hash = StringUtil.encryptPwd(newPwd);
        newPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        memberAccount.setPayPassword(newPwd);
        memberAccount.setPasswordSalt(passwordSalt);
        memberAccount.setUpdateTime(DateUtil.getCurTime());
        return memberAccountMapper.updateByPrimaryKey(memberAccount);
    }
    
    
    /**
     * 查看会员预约列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @return           会员预约列表页面
     */
    public ModelAndView appointmentListView(Integer memberId){
        List<AppointmentBaseDto> appointmentList = memberAppointmentMapper.selectAppointmentByMemberId(memberId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.APPOINTMENT_LIST);
        mav.addObject("appointmentList", appointmentList);
        return mav;
    }
    
    
    /**
     * 查看会员订单列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @return           会员预约列表页面
     */
    public ModelAndView orderListView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_LIST);
        List<MemberOrderDto> orderList = orderInfoMapper.selectOrderListByMemberId(memberId);
        
        //检查订单的评价状态，商品/套餐订单暂不能评价
        for (MemberOrderDto order : orderList) {
            if (order.getOrderStatus() != 3) {
                continue;
            }
            boolean isProject = false;
            for (OrderDetailDto detail : order.getDetailList()) {
                if (detail.getOrderType() == 1) {
                    isProject = true;
                    break;
                }
            }
            if (isProject) {
                order.setOrderStatus(0);
            }
        }
        mav.addObject("orderList", orderList);
        return mav;
    }
    
    
    /**
     * 查看会员订单确认页面
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId        门店标识
    * @param orderId        订单编号
    * @return           会员订单确认页面
     */
    public ModelAndView orderPayView(int storeId, Integer orderId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_PAY);
        SelfCashierOrderDto orderDto = selfCashierService.queryOrderDetailAction(orderId);
        //检查订单付款状态，如果已付款，直接跳转到小票页面
        if (orderDto.getOrderStatus() == 3 || orderDto.getOrderStatus() == 4) {
            return new ModelAndView("redirect:" + Url.MemberCenter.VIEW_PAYMENT_DETAIL
                    .replace("{storeId}", storeId + "").replace("{businessType}", "1") + "?orderId=" + orderId);
        }
        
        List<SelfCashierDetailDto> detialList = orderDto.getOrderDetails();
        int appointOff = 0;
        for (SelfCashierDetailDto detail : detialList) {
            appointOff += detail.getAppointOff();
        }
        mav.addObject("appointOff", appointOff);
        mav.addObject("orderDto", orderDto);
        mav.addObject("detailStr", JSONArray.fromObject(orderDto.getOrderDetails()).toString());
        mav.addObject("allOffStr", JSONObject.fromObject(orderDto.getAllOffMap()).toString());
        return mav;
    }
    
    
    /**
     * 查看订单支付明细信息
    * @author 张进军
    * @date Nov 9, 2015 11:23:08 PM
    * @param orderId        订单编号
    * @return   订单支付明细页面
     */
    public ModelAndView paymentDetailView(int orderId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.PAYMENT_DETAIL);
        OrderPaymentDto orderPayment = orderInfoMapper.selectOrderPaymentByOrderId(orderId);
        mav.addObject("orderPayment", orderPayment);
        
        int integralAmount = integralFlowMapper.selectIntegralAmountByOrderId(orderId);
        mav.addObject("integralAmount", integralAmount);
        
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(orderPayment.getStoreId());
        mav.addObject("storeInfo", storeInfo);
        return mav;
    }
    
    
    /**
     * 根据订单标识查询订单详情
    * @author 张进军
    * @date Nov 7, 2015 4:01:57 PM
    * @param orderId    订单标识
    * @return   订单评价页面
     */
    public ModelAndView orderEvaluateView(int orderId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_EVALUATE);
        OrderInfoBaseDto orderInfo = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        mav.addObject("orderInfo", orderInfo);
        return mav;
    }
    
    
    /**
     * 根据订单标识查询订单详情
    * @author 张进军
    * @date Nov 7, 2015 4:01:57 PM
    * @param orderEvaluate  评价对象
    * @param memberId       会员标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto orderEvaluateAction(OrderEvaluateDto orderEvaluate, int memberId){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderEvaluate.getOrderId());
        orderInfo.setOrderStatus(4);
        orderInfo.setUpdateTime(DateUtil.getCurTime());
        int result = orderInfoMapper.updateEvaluateByOrderId(orderInfo);
        
        if (result == 1) {
            List<EmployeeEvaluate> employeeList = orderEvaluate.getEmloyeeList();
            List<ProjectEvaluate> projectList = orderEvaluate.getProjectList();
            
            String curTime = DateUtil.getCurTime();
            for (ProjectEvaluate projectEvaluate : projectList) {
                projectEvaluate.setTime(curTime);
                projectEvaluate.setMemberId(memberId);
            }
            for (EmployeeEvaluate employeeEvaluate : employeeList) {
                employeeEvaluate.setCreateTime(curTime);
                employeeEvaluate.setMemberId(memberId);
            }
            
            employeeEvaluateMapper.insertBatch(employeeList);
            projectEvaluateMapper.insertBatch(projectList);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 查看会员套餐列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @return           会员套餐列表页面
     */
    public ModelAndView comboListView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.COMBO_LIST);
        List<MemberComboDto> comboList = memberComboRecordMapper.selectComboListByMemberId(memberId);
        mav.addObject("comboList", comboList);
        return mav;
    }
    
    
    
    /**
     * 会员注册
    * @author 张进军
    * @date Aug 19, 2015 7:43:51 PM
    * @param storeId        注册门店
    * @param mainStoreId    总店标识
    * @param phone          手机号
    * @param verifyCode     验证码
    * @param openId         微信id
    * @param accessToken    微信接口口令
    * @param request        请求对象
    * @return               成功返回码0，返回值为跳转地址；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto registerAction(int mainStoreId, int storeId, String phone, String verifyCode, String openId,
            String accessToken, HttpServletRequest request){
        //校验验证码是否正确
//        String code = redisService.get(App.Redis.PHONE_VERIFY_CODE_KEY_PRE + phone);
//        if (!verifyCode.equals(code)) {
//            return new BaseDto(10001, "验证码错误");
//        }
        
        
        Integer memberId = memberInfoService.selectMemberIdByPhone(phone, storeId);
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME.replace("{storeId}", String.valueOf(mainStoreId)).
                replace("{businessType}", "1"));
        
        int subscribe = 0;
        //如果不存在，则需要新增会员数据、会员账户，跳转到完善会员信息页面
        if (memberId == null) {
            String name = "";
            String sex = "女";
            String headUrl = "";
            
            String userInfoRes = HttpClientUtil.sendGetReq(String.format(App.Wechat.GET_USER_INFO_URL, 
                    new Object[] { accessToken, openId }), "utf-8");
            JSONObject userInfoJson = JSONObject.fromObject(userInfoRes);
            //如果用户未关注
            if (!userInfoJson.containsKey("errcode") && userInfoJson.getInt("subscribe") == 0) {
                String subscribeUrl = redisService.hget(App.Redis.STORE_WECHAT_SUBSCRIBE_URL_KEY_HASH, mainStoreId);
                baseDto.setMsg(subscribeUrl);
            } 
            else if (!userInfoJson.containsKey("errcode") && userInfoJson.getInt("subscribe") == 1) {
                subscribe = 1;
                if (userInfoJson.getInt("sex") == 1) {
                    sex = "男";
                }
                name = userInfoJson.getString("nickname");
                String key = "userhead/wechat/" + new Date().getTime();
                qiniuService.fetch(userInfoJson.getString("headimgurl"), key);
                headUrl = key;
            }
            
            //查看店铺的默认会员等级
            int memberLevel = memberLevelMapper.selectDefaultLevelIdByStoreId(mainStoreId);
            
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(name);
            memberInfo.setSex(sex);
            memberInfo.setHeadUrl(headUrl);
            memberInfo.setStoreId(storeId);
            memberInfo.setPhone(phone);
            memberInfo.setLevelId(memberLevel);
            memberInfo.setLastOperatorId(0);
            memberInfo.setCreateTime(DateUtil.getCurTime());
            memberInfoMapper.insert(memberInfo);
            memberId = memberInfo.getMemberId();
            
            MemberAccount memberAccount = new MemberAccount();
            memberAccount.setAccountId(memberId);
            memberAccount.setBalanceIntegral(0);
            memberAccount.setTotalIntegral(0);
            memberAccount.setBalanceAmount(new BigDecimal(0));
            memberAccount.setTotalAmount(new BigDecimal(0));
            memberAccount.setLastOperatorId(0);
            memberAccount.setCreateTime(DateUtil.getCurTime());
            memberAccountMapper.insert(memberAccount);
        }
        else {
            String ownerOpenId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
            if (StringUtils.isNotBlank(ownerOpenId)) {
                return new BaseDto(10002, "对不起，该会员已经被其他微信绑定");
            }
        }
        
        wechatMemberMapper.deleteByPrimaryKey(openId);
        //建立微信的关联关系
        WechatMember wechatMember = new WechatMember();
        wechatMember.setOpenId(openId);
        wechatMember.setMemberId(memberId);
        wechatMember.setIsSubscribe(subscribe);
        String curTime = DateUtil.getCurTime();
        wechatMember.setCreateTime(curTime);
        wechatMember.setUpdateTime(curTime);
        wechatMemberMapper.insert(wechatMember);
        
        //检查该会员是否有未赠送的奖励
        if (redisService.sismember(App.Redis.WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET, openId)) {
            StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
            //检查是否有赠送的优惠券
            String coupon = storeSetting.getFirstFollowCoupon();
            if (StringUtils.isNotBlank(coupon)) {
                String[] couponList = coupon.split(",");
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
            
            //检查是否有赠送的礼金
            Integer gift = storeSetting.getFirstFollowGift();
            if (gift > 0) {
                BigDecimal money = new BigDecimal(gift);
                if (money.intValue() > 0) {
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
                }
            }
            redisService.srem(App.Redis.WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET, memberId);
        }
        
        redisService.hset(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, openId, subscribe);
        redisService.hset(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId, memberId);
        redisService.hset(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId, openId);
        redisService.hset(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId, 1);
        request.getSession().setAttribute(App.Session.USER_ID, memberId);
        
        return baseDto;
    }
    
    
    /**
     * 解除微信关联
    * @author 张进军
    * @date Dec 12, 2015 12:10:13 AM
    * @param memberId   会员标识
    * @param openId     微信标识
    * @param request    请求对象
    * @return   成功返回码为0，失败为其他错误码
     */
    public BaseDto logout(int memberId, String openId, HttpServletRequest request) {
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openId);
        wechatMemberMapper.deleteByPrimaryKey(openId);
        request.getSession().invalidate();
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 获取短信验证码
    * @author 张进军
    * @date Sep 17, 2015 11:57:08 AM
    * @param storeId    店铺标识
    * @param phone      手机号
    * @return           验证码
     */
    public BaseDto getVerifyCodeAction(int storeId, String phone){
        rabbitService.sendVerifyCode(storeId, phone, "注册会员!");
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 访问完善会员信息页面
    * @author 张进军
    * @date Aug 19, 2015 8:37:05 PM
    * @param accessToken    微信api口令
    * @param openId         微信id
    * @return               完善会员信息页面             
     */
    public ModelAndView registerInfoView(String accessToken, String openId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.REGISTER_INFO);
        
        //如果是关注的会员，提取微信资料填充的页面
//        if (isSubscribe(openId) == 1) {
        String userInfoRes = HttpClientUtil.sendGetReq(String.format(App.Wechat.GET_USER_INFO_URL, 
                new Object[] { accessToken, openId }), "utf-8");
        JSONObject userInfoJson = JSONObject.fromObject(userInfoRes);
        if (!userInfoJson.containsKey("errcode")) {
            mav.addObject("nickname", userInfoJson.getString("nickname"));
            mav.addObject("sex", userInfoJson.getInt("sex"));
            mav.addObject("headimgurl", userInfoJson.getString("headimgurl"));
        }
//        }
        
        return mav;
    }
    
    
    /**
     * 访问完善会员信息页面
     * @author 张进军
     * @date Aug 19, 2015 7:08:55 PM
     * @param userId        用户标识
     * @param nickname      昵称
     * @param headUrl       头像
     * @param sex           性别
     * @param paycode       支付密码
     * @return              成功返回码0；失败返回其他错误码，返回值为提示语                 
     */
    @Transactional
    public BaseDto registerInfoAction(Integer userId, String nickname, 
    		    String headUrl, String sex, String paycode){
    	//如果为其他域名的路径，需上传到七牛统一存储
    	if (headUrl.startsWith("http://") || headUrl.startsWith("https://")) {
    		String key = "userhead/" + userId + "/" + new Date().getTime();
    		qiniuService.fetch(headUrl, key);
    		headUrl = key;
    	}
    	
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(userId);
        memberInfo.setName(nickname);
        memberInfo.setHeadUrl(headUrl);
        memberInfo.setSex(sex);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        memberInfoService.wipeCache(userId);
        
        //更新用户支付密码
        MemberAccount memberAccount = new MemberAccount();
        String hash = StringUtil.encryptPwd(paycode);
        paycode = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        memberAccount.setPayPassword(paycode);
        memberAccount.setPasswordSalt(passwordSalt);
        memberAccount.setAccountId(userId);
        memberAccount.setBalanceIntegral(10000);
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        
        IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(userId);
        integralFlow.setBalanceAmount(0);
        integralFlow.setBusinessType("注册赠送");
        integralFlow.setBusinessDesc("新用户积分赠送");
        integralFlow.setFlowAmount(10000);
        integralFlow.setFlowType(2);
        integralFlow.setFlowTime(DateUtil.getCurTime());
        integralFlow.setIsDeleted(0);
        integralFlowMapper.insert(integralFlow);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 访问预约页面
    * @author 张进军
    * @date Sep 2, 2015 11:00:28 AM
    * @param storeId        门店标识
    * @param selectStoreId  所选门店
    * @return               预约页面
     */
    public ModelAndView orderAppointmentView(int storeId, Integer selectStoreId){
        //检查是否有选择门店，如未选择跳转到门店列表
        if (selectStoreId == null) {
            return storeListView(storeId, Url.MemberCenter.VIEW_ORDER_APPOINTMENT.replace("{storeId}", String.valueOf(storeId))
                    .replace("{businessType}", "1") + "?selectStoreId={storeId}");
        }
        
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_APPOINTMENT);
        List<DeptProjectBaseDto> serviceList = projectService.getDeptProjectByStoreId(selectStoreId);
        mav.addObject("serviceList", serviceList);
        mav.addObject("typeNumber", serviceList == null ? 0 : serviceList.size());
        return mav;
    }
    
    
    /**
     * 访问项目详情页面
    * @author 张进军
    * @date Oct 18, 2015 7:21:19 PM
    * @param projectId  项目编号
    * @param memberId   会员标识
    * @return   项目详情页面
     */
    public ModelAndView projectDetailView(int projectId, int memberId) {
        ModelAndView mav = new ModelAndView(View.MemberCenter.PROJECT_DETAIL);
        ProjectAppointDto projectDetail = projectService.selectProjectAppointByProjectId(projectId);
        mav.addObject("project", projectDetail);

        //组装轮播图片
        List<String> imgList = new ArrayList<String>();
        imgList.add(projectDetail.getProjectImage());
        if (StringUtils.isNotBlank(projectDetail.getAffiliatedImage())) {
            String[] tmp = projectDetail.getAffiliatedImage().split(",");
            for (String s : tmp) {
                //过滤默认图片
                if (!"zefun/images/pic_none.gif".equals(s) && !"zefun/idCard/1445060767155".equals(s)) {
                    imgList.add(s);
                }
            }
        }
        mav.addObject("imgList", imgList);
        
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
        mav.addObject("levelName", memberInfo.getLevelName());
        mav.addObject("discountAmount", getProjectPriceByMember(memberId, projectId, projectDetail.getProjectPrice()));
        
        List<ProjectEvaluateDto> evaluateList = projectEvaluateMapper.selectListByProjectId(projectId);
        for (ProjectEvaluateDto projectEvaluateDto : evaluateList) {
            projectEvaluateDto.setMemberInfo(memberInfoService.getMemberBaseInfo(projectEvaluateDto.getMemberId()));
        }
        mav.addObject("evaluateList", evaluateList);
        return mav;
    }
    
    
    /**
     * 访问时间预约页面
    * @author 张进军
    * @date Oct 19, 2015 3:43:41 PM
    * @param projectId      项目标识
    * @param projectName    项目名称
    * @param projectStepOrder 服务步骤序号
    * @param shiftMahjongId 服务步骤轮牌标识
    * @param employeeId     员工标识
    * @param employeeName   员工名称
    * @param levelName      员工级别
    * @param request        请求对象
    * @param response       响应对象
    * @throws IOException 页面跳转异常
    * @throws ServletException 页面跳转异常
    * @return   时间预约页面
     */
    public ModelAndView dateAppointmentView(int projectId, String projectName, int projectStepOrder, int shiftMahjongId,
            int employeeId, String employeeName, String levelName, 
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ModelAndView mav = new ModelAndView(View.MemberCenter.DATE_APPOINTMENT);
        mav.addObject("projectId", projectId);
        mav.addObject("projectName", projectName);
        mav.addObject("projectStepOrder", projectStepOrder);
        mav.addObject("shiftMahjongId", shiftMahjongId);
        mav.addObject("employeeId", employeeId);
        mav.addObject("employeeName", employeeName);
        mav.addObject("levelName", levelName);
        
        //查询员工的班次
        Map<Integer, String> map = shiftMapper.selectShiftByEmployeeId(employeeId);
        //检查员工是否有设置班次
        if (map == null || map.isEmpty()) {
            request.setAttribute("tip", "对不起，系统找不到TA的可服务时间！");
            request.getRequestDispatcher("/404.jsp").forward(request, response);
            return null;
        }
        //TODO 查询员工当前的考勤情况
        
        //查询员工的预约情况
        List<String> appointList = memberAppointmentMapper.selectAppointDateByEmployee(employeeId);
        
        List<AppointDateDto> dateList = new ArrayList<AppointDateDto>();
        
        Calendar calendar = Calendar.getInstance();
        Integer week = 0;
        String workTime = "";
        String appointDate = "";
        String appointTime = "";
        String[] workTimeArr = null;
        int workStartHour = 0; 
        int workEndHour = 0;
        String day = "0";
        
        for (int i = 0; i < 8; i++) {
            week = calendar.get(Calendar.DAY_OF_WEEK);
            day = "" + calendar.get(Calendar.DAY_OF_MONTH);
            if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
                day = "0" + day;
            }
            
            appointDate = (calendar.get(Calendar.MONTH) + 1) + "/" + day;
            AppointDateDto appointDateDto = new AppointDateDto();
            appointDateDto.setDate(appointDate);
            appointDateDto.setWeekNumber(week);
            appointDateDto.setWeekName(DateUtil.getWeekName(week));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            
            //获取当天的班次
            workTime = map.get(week + ""); 
            if (StringUtils.isBlank(workTime)) {
                continue;
            }
            workTimeArr = workTime.split(",");
            workStartHour = Integer.parseInt(workTimeArr[0].substring(0, 2)) + 1;
            workEndHour = Integer.parseInt(workTimeArr[1].substring(0, 2));
            
            if (workStartHour >= workEndHour) {
                continue;
            }
            
            appointDate = calendar.get(Calendar.YEAR) + "-" + appointDate.replace('/', '-') + " ";
            List<Map<String, Integer>> timeList = new ArrayList<Map<String, Integer>>();
            while (workStartHour != workEndHour) {
                appointTime = workStartHour + ":00";
                if (workStartHour < 10) {
                    appointTime = "0" + appointTime;
                }
                Map<String, Integer> timeMap = new HashMap<String, Integer>();
                timeMap.put(appointTime, 1);
                
                //如果是当天的话，检查是否超过当前时间
                if (i == 0 && workStartHour <= calendar.get(Calendar.HOUR_OF_DAY) + 1) {
                    timeMap.put(appointTime, 2);
                }
                //检查该时间是否已被预约
                else if (appointList.contains(appointDate + appointTime)) {
                    timeMap.put(appointTime, 2);
                }
                
                timeList.add(timeMap);
                workStartHour++;
            }
            appointDateDto.setTimeList(timeList);
            
            dateList.add(appointDateDto);
        }
        
        mav.addObject("dateList", dateList);
        
        //查询员工请假情况leave_info
        return mav;
    }
    
    
    /**
     * 预约确认操作
    * @author 张进军
    * @date Oct 19, 2015 3:43:41 PM
    * @param memberId       预约的会员标识
    * @param mainStoreId    总店标识
    * @param appointDate    预约日期
    * @param appointTime    预约时间
    * @param projectId      项目标识
    * @param projectName    项目名称
    * @param projectStepOrder 服务步骤序号
    * @param shiftMahjongId 服务步骤轮牌标识
    * @param employeeId     员工标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto orderAppointmentAction(int memberId, int mainStoreId, String appointDate, String appointTime, int projectId, 
            String projectName, int projectStepOrder, int shiftMahjongId, int employeeId){
        //获取预约员工的门店标识
        int storeId = employeeInfoMapper.selectByPrimaryKey(employeeId).getStoreId();
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
        
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setMemberId(memberId);
        memberAppointment.setName(memberInfo.getName());
        memberAppointment.setPhone(memberInfo.getPhone());
        memberAppointment.setStoreId(storeId);
        memberAppointment.setEmployeeId(employeeId);
        memberAppointment.setProjectId(projectId);
        memberAppointment.setProjectStepOrder(projectStepOrder);
        memberAppointment.setShiftMahjongId(shiftMahjongId);
        memberAppointment.setAppointmentDate(appointDate);
        memberAppointment.setAppointmentTime(appointTime);
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
        memberAppointment.setAppointmentPrice(getProjectPriceByMember(memberId, projectId, projectInfo.getProjectPrice()));
        String curTime = DateUtil.getCurTime();
        memberAppointment.setCreateTime(curTime);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(1);
        memberAppointment.setIsDeleted(0);
        memberAppointment.setLastOperatorId(0);
        memberAppointmentMapper.insert(memberAppointment);
        
        //发送预约通知给员工
        String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
        if (StringUtils.isNotBlank(openId)) {
            rabbitService.sendAppointmentApplyNotice(storeId, mainStoreId, App.System.SERVER_BASE_URL 
                    + Url.Staff.VIEW_STAFF_APPOINT.replace("{storeId}", mainStoreId + "").replace("{businessType}", "2").replace("{type}", "1"), 
                    employeeId, openId, memberInfo.getName(), memberInfo.getLevelName(), projectName, appointDate + " " + appointTime);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 取消预约
    * @author 张进军
    * @date Nov 4, 2015 10:49:11 AM
    * @param memberId       会员标识
    * @param storeId        门店标识
    * @param appointmentId  预约标识
    * @param employeeId     员工标识
    * @param projectName    预约项目    
    * @param appointmentTime    预约时间
    * @param reason         取消原因
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto cancelAppoinmentAction(int memberId, int storeId, int appointmentId, int employeeId, 
            String projectName, String appointmentTime, String reason){
        String curTime = DateUtil.getCurTime();
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setAppointmentId(appointmentId);
        memberAppointment.setCancelReason(reason);
        memberAppointment.setCancelTime(curTime);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(4);
        memberAppointmentMapper.updateByPrimaryKey(memberAppointment);
        
        //发送预约取消通知给员工
        String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
        if (StringUtils.isNotBlank(openId)) {
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
            String url = App.System.SERVER_BASE_URL 
                    + Url.Staff.VIEW_STAFF_APPOINT.replace("{storeId}", storeId + "").replace("{businessType}", "2").replace("{type}", "3");
            rabbitService.sendAppointmentResultNotice(3, storeId, url, openId, 
                    memberInfo.getName(), memberInfo.getLevelName(), projectName, appointmentTime, reason);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 查看会员卡金流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   卡金流水页面
     */
    public ModelAndView cardmoneyFlowView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.CARD_MONEY_FLOW);
        List<MoneyFlow> flowList = moneyFlowMapper.selectFlowListByMemberId(memberId);
        mav.addObject("flowList", flowList);
        return mav;
    }
    
    
    /**
     * 查看会员礼金流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   礼金流水页面
     */
    public ModelAndView giftmoneyFlowView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.GIFT_MONEY_FLOW);
        List<GiftmoneyFlow> flowList = giftmoneyFlowMapper.selectFlowlistByAccountId(memberId);
        mav.addObject("flowList", flowList);
        return mav;
    }
    
    
    /**
     * 查看会员积分流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   积分流水页面
     */
    public ModelAndView integralFlowView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.INTEGRAL_FLOW);
        List<IntegralFlow> integralFlowList = integralFlowMapper.selectFlowListByAccountId(memberId);
        mav.addObject("memberInfo", memberInfoService.getMemberBaseInfo(memberId));
        mav.addObject("integralFlowList", integralFlowList);
        return mav;
    }
    
    
    /**
     * 查看会员优惠券
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   会员优惠券页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_INTEGRAL_FLOW_LIST)
    public ModelAndView memberCouponView(int memberId){
        List<CouponBaseDto> couponList = couponInfoMapper.selectBaseByMemberId(memberId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.MEMBER_COUPON);
        mav.addObject("couponList", couponList);
        return mav;
    }
    
    
    /**
     * 查询门店下的优惠券
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId    门店标识
    * @return   积分商城页面
     */
    public ModelAndView shopCenterView(int storeId){
        Page<CouponBaseDto> page = new Page<CouponBaseDto>();
        page.setPageNo(1);
        page.setPageSize(10);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<CouponBaseDto> couponList = couponInfoMapper.selectBaseByStoreId(page);
        page.setResults(couponList);
        
        ModelAndView mav = new ModelAndView(View.MemberCenter.SHOP_CENTER);
        mav.addObject("page", page);
        return mav;
    }
    
    
    /**
     * 兑换优惠券
     * @author 张进军
     * @date Oct 22, 2015 2:58:22 PM
     * @param memberId  会员标识
     * @param couponId  优惠券标识
     * @return  成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto exchangeCouponAction(int memberId, Integer couponId) {
        BaseDto dto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
        CouponInfo couponInfo = couponInfoMapper.selectNormalByCouponId(couponId);
        if (couponInfo.getCouponVantages() > memberInfo.getBalanceIntegral()) {
            dto.setCode(1001);
            dto.setMsg("对不起，您的积分余额不够！");
            return dto;
        }
        
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.setMemberInfoId(memberId);
        memberCoupon.setCouponId(couponId);
        memberCoupon.setIsUsed(0);
        memberCoupon.setGrantTime(DateUtil.getCurTime());
        memberCouponMapper.insert(memberCoupon);
        
        int balanceAmount = memberInfo.getBalanceIntegral() - couponInfo.getCouponVantages();
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAccountId(memberId);
        memberAccount.setBalanceIntegral(balanceAmount);
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        
        String couponType = "";
        switch (couponInfo.getCouponType()) {
            case 1:
                couponType = "商品";
                break;
            case 2:
                couponType = "项目";
                break;
            case 3:
                couponType = "套餐";
                break;
    
            default:
                couponType = "通用";
                break;
        }
        
        IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(memberId);
        integralFlow.setBalanceAmount(balanceAmount);
        integralFlow.setBusinessType("兑换优惠券");
        integralFlow.setBusinessDesc("兑换" + couponInfo.getCouponPrice() + "元" + couponType + "优惠券");
        integralFlow.setFlowAmount(couponInfo.getCouponVantages());
        integralFlow.setFlowType(1);
        integralFlow.setFlowTime(DateUtil.getCurTime());
        integralFlowMapper.insert(integralFlow);
        
        memberInfoService.wipeCache(memberId);
        
        return dto;
    }
    
    
    /**
     * 查询门店信息
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId    门店标识
    * @return   积分商城页面
     */
    public ModelAndView storeInfoView(int storeId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_INFO);
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
        if (storeInfo == null) {
            return mav;
        }
        String imgStr = storeInfo.getCarouselPicture();
        if (StringUtils.isNotBlank(imgStr)) {
            String[] imgArray = imgStr.split(",");
            mav.addObject("imgArray", imgArray);
        }
        mav.addObject("storeInfo", storeInfo);
        return mav;
    }
    
    
    /**
     * 店铺展示页面
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId    门店标识
    * @param type           展示类型(1：门店介绍，2：技术展示，3：特色服务)
    * @return   店铺展示页面
     */
    public ModelAndView storeShowView(int storeId, int type){
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_SHOW);
        String title = "";
        String content = "";
        switch (type) {
            case 1:
                content = storeInfoMapper.selectDescByStoreId(storeId);
                title = "门店介绍";
                break;
            case 2:
                content = storeInfoMapper.selectTechnicalByStoreId(storeId);
                title = "技术展示";
                break;
            default:
                content = storeInfoMapper.selectCharacteristicByStoreId(storeId);
                title = "特色服务";
                break;
        }
        mav.addObject("title", title);
        mav.addObject("content", content);
        return mav;
    }
    
    
    /**
     * 分享发型操作
    * @author 张进军
    * @date Nov 14, 2015 12:45:46 PM
    * @param projectShare    分享信息
    * @return   成功返回获赠积分数量
     */
    @Transactional
    public BaseDto shareAction(ProjectShare projectShare){
        int orderId = projectShare.getOrderId();
        //检查是否有分享过
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        if (orderInfo.getIsShare() == 1) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您已经分享过此订单");
        }
        orderInfoMapper.updateOrderShare(orderId);
        
        String time = DateUtil.getCurTime();
        int memberId = orderInfo.getMemberId();
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
        
        projectShare.setMemberId(memberId);
        projectShare.setTime(time);
        projectShareMapper.insert(projectShare);
        
        //增加积分流水
        int integral = 10;
        IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(memberId);
        integralFlow.setBusinessType("分享赠送");
        integralFlow.setFlowAmount(integral);
        integralFlow.setFlowType(2);
        integralFlow.setFlowTime(time);
        integralFlow.setOrderId(orderId);
        integralFlow.setBalanceAmount(memberInfo.getBalanceIntegral());
        integralFlowMapper.insert(integralFlow);
        
        //更新会员账户信息
        int balanceAmount = memberInfo.getBalanceIntegral() + integral;
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAccountId(memberId);
        memberAccount.setBalanceIntegral(balanceAmount);
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        
        memberInfoService.wipeCache(memberId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, integral);
    }


    /**
     * 查看门店列表
    * @author 张进军
    * @date Nov 19, 2015 5:38:33 PM
    * @param storeId    总店标识
    * @param url        跳转地址
    * @return   门店列表页面
    */
    public ModelAndView storeListView(int storeId, String url) {
        logger.debug("storeId : " + storeId + ", url : " + url);
        if (url.startsWith("//")) {
            url = url.replaceFirst("/", "");
        }
        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        //如果只有一个分店，那么直接跳转到对应的地址，即分店
        if (storeList.size() == 1) {
            return new ModelAndView("redirect:" + url.replace("{storeId}", String.valueOf(storeList.get(0).getStoreId())));
        }
        //保证页面拼接时的地址正确
        if (url.startsWith("/")) {
            url = url.replaceFirst("/", "");
        }
        
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_LIST);
        mav.addObject("url", url);
        mav.addObject("storeList", storeList);
        return mav;
    }
    
    
    /**
     * 查询会员对于项目的具体价格
     * 首先检查是否有设置该会员的特定价格，再通过会员等级的折扣去计算
    * @author 张进军
    * @date Nov 28, 2015 8:37:15 PM
    * @param memberId       会员标识
    * @param projectId      项目标识
    * @param projectPrice   项目原价
    * @return   会员实际价格
     */
    private BigDecimal getProjectPriceByMember(int memberId, int projectId, BigDecimal projectPrice){
        BigDecimal discountAmount = projectPrice;
        //计算会员折扣价
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("memberId", memberId);
        map.put("projectId", projectId);
        ProjectDiscount discount = projectDiscountMapper.selectByDiscount(map);
        //如果没有特定会员价，那么计算查找该会员的折扣去计算
        if (discount == null) {
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
            MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(memberInfo.getLevelId());
            discountAmount = discountAmount.multiply(new BigDecimal(memberLevel.getProjectDiscount())).divide(new BigDecimal(100), 2);
        }
        else {
            discountAmount = discount.getDiscountAmount();
        }
        return discountAmount;
    }
}