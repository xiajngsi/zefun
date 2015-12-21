package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.exception.ServiceException;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderDetaiSubmitDto;
import com.zefun.web.dto.OrderInfoSubmitDto;
import com.zefun.web.dto.PaymentOffDto;
import com.zefun.web.dto.SelfCashierDetailDto;
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.dto.SelfCashierStatDto;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;

/**
 * 
 * @author 张进军
 * @date Oct 22, 2015 10:27:15 AM
 */
@Service
public class SelfCashierService {

	/** 订单信息操作对象 */
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	/** 订单详情操作对象 */
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	/** 会员优惠券操作对象 */
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	
	/** 会员套餐项目明细操作对象 */
	@Autowired
	private MemberComboProjectMapper memberComboProjectMapper;
	
    /** 会员信息操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    /** 会员账户信息操作对象 */
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    
    /**会员等级操作对象*/
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    
    /** 积分流水操作对象 */
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    
    /** 资金流水操作对象 */
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    
    /** 礼金账户流水操作对象 */
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    
    /**redis操作对象*/
    @Autowired
    private RedisService redisService;
    
    /**队列操作对象*/
    @Autowired
    private RabbitService rabbitService;
    
    /**会员基础信息服务对象*/
    @Autowired
    private MemberInfoService memberInfoService;
    
    /**开卡充值服务类*/
    @Autowired
    private OpenCardService openCardService;
    
    /** 礼金明细*/
    @Autowired
    private GiftmoneyDetailMapper giftmoneyDetailMapper;
    
    /** 零 */
    private BigDecimal zero = new BigDecimal(0);
    
    /** 日志操作对象 */
    private Logger logger = Logger.getLogger(SelfCashierService.class);
    
	
	/**
	 * 自助收银页面
	 * @author luhw
	 * @date 2015年10月21日 下午15:25:03
	 * @param storeId 门店标识
	 * @return cashierDto
	 * @throws ParseException ParseException
	 */
	public ModelAndView queryOrderInfoList(Integer storeId) throws ParseException {
		// 查询当日订单金额
		Map<String, Object> params = new HashMap<String, Object>(); 
		String currDate = DateUtil.getCurDate();
		params.put("storeId", storeId);
		params.put("starTime", currDate);
		params.put("endTime", DateUtil.getDateAfterDaysStr(currDate, 1));
		SelfCashierStatDto statDto = orderInfoMapper.selectCashierStatInfo(params);
		
		//查询未结账订单
		List<SelfCashierOrderDto> cashierDtoList = orderInfoMapper.selectUnfinishedOrderInfo(storeId);
		
		ModelAndView mav = new ModelAndView(View.SelfCashier.VIEW_SELF_CASHIER);
		mav.addObject("statDto", statDto);
		mav.addObject("cashierDtoList", cashierDtoList);
		return mav;
	}

	/**
	 * 查询订单详情
	 * @param orderId 订单标识
	 * @return SelfCashierResultDto
	 */
	public SelfCashierOrderDto queryOrderDetailAction(Integer orderId) {
		SelfCashierOrderDto cashierDto = selectSelfCashierOrderDetail(orderId, true);
		if (cashierDto == null) {
			return cashierDto;
		}
		return cashierDto;
	}
	
	
	/**
	 * 
	* @author 张进军
	* @date Nov 11, 2015 8:27:23 PM
	* @param employeeId    操作人员标识
	* @param orderSubmit   订单支付信息
	* @return  成功返回码0；失败返回其他错误码，返回值为提示语
	 * @throws ServiceException 
	 */
	@Transactional
	public BaseDto cashierSubmit(int employeeId, OrderInfoSubmitDto orderSubmit) throws ServiceException {
	    logger.debug("cashierSubmit -- > employeeId : " + employeeId + ", orderSubmit : " + JSONObject.fromObject(orderSubmit).toString());
	    
	    int orderId = orderSubmit.getOrderId();
	    SelfCashierOrderDto cashierDto = selectSelfCashierOrderDetail(orderId, true);
	    List<SelfCashierDetailDto> ownerDetailList = cashierDto.getOrderDetails();
	    List<OrderDetaiSubmitDto> submitDetailList = orderSubmit.getDetailList();
	    
	    //检查是否为待结账状态
	    if (!(cashierDto.getOrderStatus() == 2 || cashierDto.getOrderStatus() == 5)) {
	        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "此订单已经结过帐啦");
	    }
	    
	    //检查提交订单是否与数据库中订单一致
	    if (ownerDetailList.size() != submitDetailList.size()) {
	        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "订单信息错误，请刷新重试！");
	    }
	    
	    Map<String, SelfCashierDetailDto> detailMap = new HashMap<String, SelfCashierDetailDto>(ownerDetailList.size());
	    List<Integer> detailIdList = new ArrayList<Integer>(ownerDetailList.size());
	    for (SelfCashierDetailDto detail : ownerDetailList) {
	        detailIdList.add(detail.getDetailId());
	        detailMap.put(String.valueOf(detail.getDetailId()), detail);
        }
	    for (OrderDetaiSubmitDto detail : orderSubmit.getDetailList()) {
            if (!detailIdList.contains(detail.getDetailId())) {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "订单信息错误，请刷新重试！");
            }
        }
	    
	    String curTime = DateUtil.getCurTime();
	    Integer memberId = cashierDto.getMemberId();
	    BigDecimal giftAmount = new BigDecimal(0);
	    BigDecimal comboAmount = new BigDecimal(0);
	    BigDecimal couponAmount = new BigDecimal(0);
	    BigDecimal appointOff = new BigDecimal(0);
	    BigDecimal realAmount = new BigDecimal(0);
	    //依次遍历所有支付的明细信息
	    for (OrderDetaiSubmitDto detail : orderSubmit.getDetailList()) {
	        SelfCashierDetailDto ownerDetail = detailMap.get(String.valueOf(detail.getDetailId()));
	        
	        OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setDetailId(detail.getDetailId());
	        orderDetail.setGiftAmount(zero);
            orderDetail.setRealPrice(ownerDetail.getDiscountAmount());
            orderDetail.setUpdateTime(curTime);
	        
            if (memberId != null) {
                //优惠类型为套餐
                if (detail.getOffType() == 1 || detail.getOffType() == 2) {
                    //减去套餐次数
                    updateMemberComboInfo(memberId, detail.getOffId(), curTime);
                    Integer comboId = memberComboProjectMapper.selectComboIdByDetailId(detail.getOffId());
                    if (comboId == null) {
                        throw new ServiceException(-1, "对不起，您不存在此套餐！");
                    }
                    comboAmount = comboAmount.add(ownerDetail.getProjectPrice());
                    orderDetail.setGiftAmount(ownerDetail.getDiscountAmount());
                    orderDetail.setRealPrice(zero);
                    orderDetail.setComboId(detail.getOffId());
                    orderDetail.setOffType(1);
                }
                //优惠类型为礼金
                else if (detail.getOffType() == 4) {
                    //更新礼金账户
                    updateMemberGiftMoney(memberId, detail.getOffAmount());
                    //增加礼金流水
                    insertGiftMoneyFlow(memberId, ownerDetail.getDetailId(), detail.getOffAmount(), curTime, ownerDetail.getProjectName());
                    
                    giftAmount = giftAmount.add(detail.getOffAmount());
                    orderDetail.setGiftAmount(detail.getOffAmount());
                    orderDetail.setRealPrice(ownerDetail.getProjectPrice().subtract(detail.getOffAmount()));
                    orderDetail.setOffType(3);
                }
                //优惠类型为优惠券
                else if (detail.getOffType() == 3) {
                    //将优惠券标识对应的最快过期时间的会员优惠券设为已使用
                    updateMemberCouponInfo(memberId, detail.getOffId());
                    
                    couponAmount = couponAmount.add(detail.getOffAmount());
                    orderDetail.setGiftAmount(detail.getOffAmount());
                    orderDetail.setRealPrice(ownerDetail.getDiscountAmount().subtract(detail.getOffAmount()));
                    orderDetail.setCouponId(detail.getOffId());
                    orderDetail.setOffType(2);
                } 
                appointOff = appointOff.add(ownerDetail.getAppointOff());
                BigDecimal rm = orderDetail.getRealPrice().subtract(ownerDetail.getAppointOff());
                if (rm.compareTo(zero) == -1) {
                    rm = zero;
                }
                orderDetail.setRealPrice(rm);
            }
            
            realAmount = realAmount.add(orderDetail.getRealPrice());
            if (realAmount.compareTo(zero) == -1) {
                realAmount = zero;
            }
	        orderDetailMapper.updateByPrimaryKey(orderDetail);
	    }
	    
	    //校验实收金额
	    BigDecimal tempAmount = cashierDto.getDebtAmount().add(orderSubmit.getAlipayAmount()).
	            add(orderSubmit.getCardAmount()).add(orderSubmit.getCashAmount()).add(giftAmount).
	            add(orderSubmit.getUnionpayAmount()).add(orderSubmit.getWechatAmount()).
	            add(appointOff).add(comboAmount).add(couponAmount);
	    if (tempAmount.compareTo(realAmount) == -1) {
	        throw new ServiceException(1, "您支付的金额与实收金额不一致");
	    }
	    
	    OrderInfo orderInfo = new OrderInfo();
	    orderInfo.setOrderId(orderId);
	    orderInfo.setCardAmount(orderSubmit.getCardAmount());
	    orderInfo.setCashAmount(orderSubmit.getCashAmount());
	    orderInfo.setUnionpayAmount(orderSubmit.getUnionpayAmount());
	    orderInfo.setAlipayAmount(orderSubmit.getAlipayAmount());
	    orderInfo.setWechatAmount(orderSubmit.getWechatAmount());
	    orderInfo.setAppointOff(appointOff);
	    orderInfo.setComboAmount(comboAmount);
	    orderInfo.setCouponAmount(couponAmount);
	    orderInfo.setGiftAmount(giftAmount);
	    orderInfo.setRealAmount(realAmount);
	    orderInfo.setOrderStatus(3);
	    orderInfo.setUpdateTime(curTime);
	    orderInfo.setLastOperatorId(employeeId);
	    orderInfoMapper.updateByPrimaryKey(orderInfo);
	    
	    //检查是否需要减扣卡金
	    if (orderSubmit.getCardAmount().compareTo(zero) == 1) {
	        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
	        
	        //根据会员等级获取不同积分规则以计算应得积分
	        MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(memberInfo.getLevelId());
	        int integralAmount = 0;
	        if (memberLevel.getIntegralNumber() > 0 && memberLevel.getIntegralUnit() > 0) {
	            //支付卡金金额  单位积分数量 * 单位获取积分, 每消费1元获取0.5分,消费100元 ＝ 100 / 1 * 0.5
	            integralAmount = orderSubmit.getCardAmount().intValue() / memberLevel.getIntegralUnit() * memberLevel.getIntegralNumber();
	        }
	        
	        //更新会员账户信息
	        updateMemberAccount(memberId, orderSubmit.getCardAmount(), integralAmount, curTime);
	        //新增会员账户流水
	        insertMoneyFlow(cashierDto.getMemberInfo().getStoreId(), memberId, orderId, orderSubmit.getCardAmount(), 
	                cashierDto.getMemberInfo().getBalanceAmount(), curTime, employeeId);
	        //新增积分流水
	        if (integralAmount > 0) {
	            insertIntegralFlow(memberId, orderId, cashierDto.getMemberInfo().getBalanceIntegral(), integralAmount, curTime);
	        }
	    }
	    
	    //刷新会员缓存
	    if (memberId != null) {
	        memberInfoService.wipeCache(memberId);
	    }
	    
	    rabbitService.sendCashierOrderComission(orderId);
	    
	    //当操作人为0，代表会员自助结账，否则为前台结账，前台结账，向会员发送结账提醒
	    if (employeeId != 0) {
	        String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, cashierDto.getMemberId());
	        if (StringUtils.isNotBlank(openId)) {
	            Set<String> projectSet = new HashSet<String>();
	            for (SelfCashierDetailDto orderDetail : ownerDetailList) {
	                projectSet.add(orderDetail.getProjectName());
	            }
	            String projectList = projectSet.toString();
	            projectList = projectList.substring(1, projectList.length() - 1);
	            String url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_PAYMENT_DETAIL
	                    .replace("{storeId}", cashierDto.getMemberInfo().getStoreId() + "").replace("{businessType}", "1") + "?orderId=" + orderId;
	            rabbitService.sendPaymentNotice(2, cashierDto.getMemberInfo().getStoreId(), url, openId, cashierDto.getMemberInfo().getStoreName(), 
	                    cashierDto.getMemberInfo().getPhone(), projectList, 
	                    cashierDto.getReceivableAmount().toString(), cashierDto.getDiscountAmount().toString());
	        }
	    }
	    
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
	
	
	/**
	 * 更新订单的会员信息
	 * @param storeId 门店标识
	 * @param orderId 订单标识
	 * @param memberId 会员标识
	 * @param passwd 会员密码
	 * @return BaseDto
	 */
	public BaseDto changeOrderMemberId(Integer storeId, Integer orderId, Integer memberId, String passwd) {
		Map<String, Object> param = memberInfoMapper.selectSelfCashierMemberPassById(memberId);
		if (param == null || param.get("memberId") == null) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "所选会员不存在");
		}
        //检查用户密码
        if (!StringUtil.mD5(passwd + param.get("passwordSalt")).equals(param.get("payPassword"))) {
            return new BaseDto(9002, "支付密码错误");
        }
        // 修改订单信息
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("memberId", memberId);
        map.put("orderId", orderId);
        int count = orderInfoMapper.updateOrderMemberInfo(map);
        if (count == 1) {
            openCardService.changeMemberOrder(memberId);
        	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "绑定会员成功");
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "绑定会员失败");
        
	}
	
	
	/**
	 * 根据门店标识、订单标识查询订单详情
	 * @param orderId 订单标识
	 * @param queryOff 是否查询优惠
	 * @return SelfCashierOrderDto
	 */
	public SelfCashierOrderDto selectSelfCashierOrderDetail(Integer orderId, boolean queryOff) {
		SelfCashierOrderDto orderInfo = orderInfoMapper.selectUnfinishedOrderDetail(orderId);
		if (orderInfo == null || orderInfo.getMemberId() == null) {
		    return orderInfo;
		}
		if (queryOff) {
		    orderInfo = calculatePaymentOff(orderInfo);;
		}
		return orderInfo;
	}
	
	
	/**
	 * 更新会员账户信息
	* @author 张进军
	* @date Nov 12, 2015 12:40:24 AM
	* @param memberId      会员标识
	* @param useAmount     使用金额
	* @param integralAmount    获赠积分
	* @param time          使用时间
	 * @throws ServiceException 如果储值余额小于使用卡金金额，抛出异常
	 */
	@Transactional
	protected void updateMemberAccount(int memberId, BigDecimal useAmount, int integralAmount, String time) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accountId", memberId);
        map.put("usedBalanceAmount", useAmount);
        map.put("memberIntegral", integralAmount);
        map.put("consumeCount", 1);
        map.put("currDate", time);
        int count = memberAccountMapper.updateMemberCashier(map);
        if (count == 0) {
            throw new ServiceException(-1, "对不起，储值账户余额不足");
        }
    }
	
	
	/**
	 * 更新用户的优惠券信息
	* @author 张进军
	* @date Nov 11, 2015 9:50:25 PM
	* @param memberId      会员标识
	* @param coupontId     优惠券标识
	* @throws ServiceException 如不存在可用优惠券，抛出异常
	 */
	@Transactional
	protected void updateMemberCouponInfo(int memberId, int coupontId) throws ServiceException {
		Map<String, Integer> map = new HashMap<String, Integer>(2);
        map.put("memberId", memberId);
        map.put("couponId", coupontId);
        int relevanceId = memberCouponMapper.selectLeftCouponByMemberIdAndCouponId(map);
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.setRelevanceId(relevanceId);
        memberCoupon.setIsUsed(1);
        int count = memberCouponMapper.updateByPrimaryKey(memberCoupon);
		if (count == 0) {
		    throw new ServiceException(-1, "对不起，暂没有可用的优惠券");
		}
	}
	
	
	/**
	 * 更新会员的套餐信息
	* @author 张进军
	* @date Nov 11, 2015 9:56:51 PM
	* @param memberId  会员标识
	* @param detailId  会员套餐明细标识
	* @param time      更新时间
	* @throws ServiceException 如不存在可用套餐，抛出异常
	 */
	@Transactional
	protected void updateMemberComboInfo(int memberId, int detailId, String time) throws ServiceException {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("detailId", detailId);
	    map.put("comboCount", 1);
	    map.put("updateTime", time);
	    memberComboProjectMapper.updateSelfCashierCombo(map);
    }
	
	
	/**
	 * 新增消费获取的积分
	 * @param memberId 会员标识
	 * @param orderId 订单标识
	 * @param integralBanlance 积分余额
	 * @param integralAmount 获赠积分数量
	 * @param time 流水时间
	 */
	@Transactional
	protected void insertIntegralFlow(Integer memberId, Integer orderId, Integer integralBanlance, Integer integralAmount, String time) {
		IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(memberId);
        integralFlow.setOrderId(orderId);
        integralFlow.setFlowAmount(integralAmount);
        integralFlow.setBalanceAmount(integralBanlance);
        integralFlow.setBusinessType("消费得积分");
        integralFlow.setBusinessDesc("");
        integralFlow.setFlowType(2);
        integralFlow.setFlowTime(time);
        integralFlowMapper.insert(integralFlow);
	}
	
	
	/**
	 * 新增礼金流水
	 * @param accountId 账户标识
	 * @param detailId 订单明细标识
	 * @param giftAmount 消耗的资金
	 * @param time         流水时间
	 * @param projectName  消费项目/商品
	 * @return 新增记录数
	 */
	@Transactional
	protected int insertGiftMoneyFlow(Integer accountId, Integer detailId, BigDecimal giftAmount, String time, String projectName) {
	    
	    List<GiftmoneyDetail> list = giftmoneyDetailMapper.selectByAccountId(accountId);
	    
	    BigDecimal operationValue = new BigDecimal(giftAmount.toString());
	    
	    //抵扣礼金情况
	    String residueMoneyInfo = null;
	    
	    for (GiftmoneyDetail giftmoneyDetail : list) {
	        if (giftmoneyDetail.getResidueNowMoney().compareTo(operationValue) == -1 
	                || giftmoneyDetail.getResidueNowMoney().compareTo(operationValue) == 0) {
	            //修改礼金明细，当期剩余礼金
	            GiftmoneyDetail record = new GiftmoneyDetail();
	            record.setDetail(giftmoneyDetail.getDetail());
	            record.setResidueNowMoney(zero);
	            giftmoneyDetailMapper.updateByPrimaryKeySelective(record);
	            operationValue = operationValue.subtract(giftmoneyDetail.getResidueNowMoney());
	            if (residueMoneyInfo == null) {
	                residueMoneyInfo = giftmoneyDetail.getDetail() + ":" + giftmoneyDetail.getResidueNowMoney();
	            }
	            else {
	                residueMoneyInfo = residueMoneyInfo + "," + giftmoneyDetail.getDetail() + ":" + giftmoneyDetail.getResidueNowMoney();
	            }
	            continue;
	        }
	        else {
	        	if (operationValue.compareTo(new BigDecimal(0)) != 0) {
	        		//修改礼金明细，当期剩余礼金
	                GiftmoneyDetail record = new GiftmoneyDetail();
	                record.setDetail(giftmoneyDetail.getDetail());
	                record.setResidueNowMoney(giftmoneyDetail.getResidueNowMoney().subtract(operationValue));
	                giftmoneyDetailMapper.updateByPrimaryKeySelective(record);
	                
	                if (residueMoneyInfo == null) {
	                    residueMoneyInfo = giftmoneyDetail.getDetail() + ":" + operationValue;
	                }
	                else {
	                    residueMoneyInfo = residueMoneyInfo + "," + giftmoneyDetail.getDetail() + ":" + operationValue;
	                }
	                break;
	        	}
	        }
        }
	    
		// 新增资金流水
	    GiftmoneyFlow moneyFlow = new GiftmoneyFlow(); 
        moneyFlow.setAccountId(accountId);
        moneyFlow.setDetailId(detailId);
        moneyFlow.setFlowType(1);
        moneyFlow.setBusinessType("消费抵扣");
        moneyFlow.setBusinessDesc(projectName + " 消费抵扣");
        moneyFlow.setResidueMoneyInfo(residueMoneyInfo);
        moneyFlow.setFlowAmount(giftAmount);
        moneyFlow.setFlowTime(time);
        moneyFlow.setIsDeleted(0);
        return giftmoneyFlowMapper.insert(moneyFlow);
	}
	
	/**
	 * 新增资金流水
	 * @param storeId 门店标识
	 * @param accountId 账户标识
	 * @param orderId 订单标识
	 * @param flowAmount 消费资金
	 * @param balance 资金余额
	 * @param time         流水时间
	 * @param operatorId     操作人标识
	 * @return 新增记录数
	 */
	@Transactional
	protected int insertMoneyFlow(Integer storeId, Integer accountId, Integer orderId, BigDecimal flowAmount, 
	        BigDecimal balance, String time, int operatorId) {
		//新增资金流水
        MoneyFlow moneyFlow = new MoneyFlow(); 
        moneyFlow.setAccountId(accountId);
        moneyFlow.setFlowType(1);
        moneyFlow.setBusinessType(1);
        moneyFlow.setFlowAmount(flowAmount);
        moneyFlow.setBalanceAmount(balance);
        moneyFlow.setFlowTime(time);
        moneyFlow.setStoreId(storeId);
        moneyFlow.setOrderId(orderId);
        moneyFlow.setIsDeleted(0);
        moneyFlow.setLastOperatorId(operatorId);
        return moneyFlowMapper.insert(moneyFlow);
	}
	
	
	/**
	 * 更新礼金账户
	* @author 张进军
	* @date Nov 11, 2015 10:05:16 PM
	* @param memberId      会员标识
	* @param usedGiftmoney 使用礼金金额
	* @throws ServiceException     如果礼金余额小于使用礼金金额，抛出异常
	 */
	@Transactional
	protected void updateMemberGiftMoney(Integer memberId, BigDecimal usedGiftmoney) throws ServiceException {
		Map<String, Object> giftParams = new HashMap<String, Object>(5);
		giftParams.put("accountId", memberId);
		giftParams.put("usedGiftmoney", usedGiftmoney);
		int count = memberAccountMapper.updateMemberGiftMoney(giftParams);
		if (count == 0) {
		    throw new ServiceException(-1, "对不起，礼金账户余额不足");
		}
	}
	
	
	/**
	 * 计算订单支付时的可用优惠(会员)
	* @author 张进军
	* @date Nov 10, 2015 3:04:29 PM
	* @param orderInfo 订单信息
	* @return  注入优惠的订单信息
	*/
	private SelfCashierOrderDto calculatePaymentOff(SelfCashierOrderDto orderInfo){
	    int memberId = orderInfo.getMemberId();
	    MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
	    orderInfo.setMemberInfo(memberInfo);
	    
	    BigDecimal giftmoney = memberInfo.getGiftmoneyAmount();
	    
	    //存储所有的优惠项
	    Set<PaymentOffDto> set = new HashSet<PaymentOffDto>();
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    map.put("memberId", memberId);
	    //遍历每个具体消费项目
	    for (SelfCashierDetailDto detail : orderInfo.getOrderDetails()) {
	        List<PaymentOffDto> paymentOffList = new ArrayList<PaymentOffDto>();
            //套餐无任何优惠，直接过滤
	        if (detail.getOrderType() == 3) {
	            detail.setRealPrice(detail.getProjectPrice());
	            continue;
	        }
	        //如果是项目，先查询套餐抵扣
	        else if (detail.getOrderType() == 1) {
	            map.put("projectId", detail.getProjectId());
                paymentOffList = memberComboProjectMapper.selectPaymentOffListByMemberIdAndProjectId(map);
	        }
	        
	        //检查是否可以使用礼金抵扣
	        if (detail.getIsGiftCash() == 1 && giftmoney.compareTo(zero) == 1 && detail.getHighestDiscount().compareTo(zero) == 1) {
	            //项目原价减掉礼金之后还需要支付超过会员折扣价格的卡金，则不优先使用礼金抵扣。
	            PaymentOffDto giftPaymentOff = new PaymentOffDto();
	            giftPaymentOff.setId(String.valueOf(memberId));
	            giftPaymentOff.setType(4);
	            giftPaymentOff.setName("礼金抵扣");
                BigDecimal amount = giftmoney;
                if (giftmoney.compareTo(detail.getHighestDiscount()) == 1) {
                    amount = detail.getHighestDiscount();
                }
                giftPaymentOff.setAmount(amount);
                giftPaymentOff.setBalance(giftmoney);
                paymentOffList.add(giftPaymentOff);
	        }
	        
	        //检查是否有优惠券可以抵扣
	        map.put("couponType", detail.getOrderType());
	        map.put("projectId", detail.getProjectId());
	        List<PaymentOffDto> couponList = memberCouponMapper.selectPaymentCouponListByMemberIdAndProjectId(map);
	        if (!couponList.isEmpty()) {
	            paymentOffList.addAll(couponList);
	        }
	        
	        //全部放入该项目优惠列表中
	        detail.setPaymentOffList(paymentOffList);
	        set.addAll(paymentOffList);
        }
	    
	    //组装所有优惠项的可用次数/余额
	    Map<String, Object> allOffMap = new HashMap<String, Object>();
	    for (PaymentOffDto off : set) {
            allOffMap.put(off.getType() + "_" + off.getId(), off.getBalance());
        }
	    orderInfo.setAllOffMap(allOffMap);
	    
	    return orderInfo;
	}
}
