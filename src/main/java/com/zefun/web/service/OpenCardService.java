package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberDto;
import com.zefun.web.dto.MoneyFlowDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberPresentRecord;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.GoodsDiscountMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberPresentRecordMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.wechat.service.StaffService;

/**
 * 开支记账Service
* @author 王大爷
* @date 2015年8月11日 上午11:24:41
 */
@Service
public class OpenCardService {
    
    /**会员信息服务对象*/
    @Autowired private MemberInfoService memberInfoService;
    
	/** 会员信息*/
    @Autowired private MemberInfoMapper memberInfoMapper;
    
    /** 会员等级*/
    @Autowired private MemberLevelMapper memberLevelMapper;
    
    /** 员工信息*/
    @Autowired private EmployeeInfoMapper employeeInfoMapper;
    
    /** 会员账户表*/
    @Autowired private MemberAccountMapper memberAccountMapper;
    
    /** 部门*/
    @Autowired private DeptInfoMapper deptInfoMapper;
    
    /** 员工开卡充值提成*/
    @Autowired private EmployeeCommissionMapper employeeCommissionMapper;
    
    /** 礼金明细*/
    @Autowired private GiftmoneyDetailMapper giftmoneyDetailMapper;
    
    /** 礼金流水*/
    @Autowired private GiftmoneyFlowMapper giftmoneyFlowMapper;
    
    /** 资金流水*/
    @Autowired private MoneyFlowMapper moneyFlowMapper;
    
    /** 订单信息*/
    @Autowired private OrderInfoMapper orderInfoMapper;
    
    /** 订单明细*/
    @Autowired private OrderDetailMapper orderDetailMapper;
    
    /**队列通知服务对象*/
    @Autowired private RabbitService rabbitService;
    
    /**redis缓存操作对象*/
    @Autowired private RedisService redisService;
    /** 项目信息*/
    @Autowired private ProjectInfoMapper projectInfoMapper;
    /** 项目特定会员*/
    @Autowired private ProjectDiscountMapper projectDiscountMapper;
    /** 商品特定会员*/
    @Autowired private GoodsDiscountMapper goodsDiscountMapper;
    /** 员工业绩目标表*/
    @Autowired private EmployeeObjectiveMapper employeeObjectiveMapper;
    
    /**优惠券信息操作对象*/
    @Autowired private CouponInfoMapper couponInfoMapper;
    
    /**会员优惠券操作对象*/
    @Autowired private MemberCouponMapper memberCouponMapper;
    
    /** 积分流水操作对象 */
    @Autowired private IntegralFlowMapper integralFlowMapper;
    
    /** 会员赠送记录操作对象 */
    @Autowired private MemberPresentRecordMapper memberPresentRecordMapper;
    
    /** staffService*/
    @Autowired private StaffService staffService;
    
    /**
     * 初始化开卡充值页面
    * @author 王大爷
    * @date 2015年9月8日 下午8:49:03
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView initializeOpenCard(Integer storeId){
        ModelAndView mav  = new ModelAndView(View.KeepAccounts.OPEN_CARD);
        List<MemberLevel> memberLevelList = memberLevelMapper.selectByStoreId(storeId);
        List<EmployeeInfo> employeeInfoList = employeeInfoMapper.selectEmployeeList(storeId);
        List<DeptInfoDto> deptInfoDtoList = deptInfoMapper.selectByshiftMahjong(storeId);
        mav.addObject("memberLevelList", memberLevelList);
        if (memberLevelList.size() > 0) {
            MemberLevel memberLevel = memberLevelList.get(0);
            mav.addObject("memberLevels", memberLevel);
        }
        mav.addObject("memberLevelListStr", JSONArray.fromObject(memberLevelList).toString());
        mav.addObject("employeeInfoList", employeeInfoList);
        mav.addObject("deptInfoDtoList", deptInfoDtoList);
        
        List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(storeId);
        mav.addObject("couponList", couponList);
        return mav;
    }
    
    /**
     * 开卡
    * @author 王大爷
    * @date 2015年9月9日 下午3:53:08
    * @param phone 手机号
    * @param name 姓名
    * @param sex 性别 
    * @param levelId 会员级别
    * @param openType 开卡方式(1：充值开卡、2：现金购卡)
    * @param recommendId 开卡员工
    * @param commissionAmount 员工提成金额
    * @param calculateAmount 员工业绩金额
    * @param giftmoneyAmount 礼金金额
    * @param pastDate 过期天数
    * @param partType 分期方式
    * @param balanceAmount 储值金额
    * @param rewardAmount 卡金
    * @param messageType 是否接收短信（0：否、1、是）
    * @param cashAmount 现金支付
    * @param unionpayAmount 银联支付
    * @param payPassword 支付密码
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     * @throws ParseException 
     */
    @Transactional
    public BaseDto addMemberInfo(String phone, String name, String sex, Integer levelId, Integer openType, List<Integer> recommendId, 
            List<BigDecimal> commissionAmount, List<BigDecimal> calculateAmount, BigDecimal giftmoneyAmount, Integer pastDate,
            Integer partType, BigDecimal balanceAmount, BigDecimal rewardAmount, 
            Integer messageType, BigDecimal cashAmount, BigDecimal unionpayAmount, String payPassword, Integer storeId, 
            Integer lastOperatorId) throws ParseException{
        //添加会员信息表
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setStoreId(storeId);
        memberInfo.setLevelId(levelId);
        memberInfo.setName(name);
        memberInfo.setSex(sex);
        memberInfo.setPhone(phone);
        memberInfo.setMessageType(messageType);
        memberInfo.setCreateTime(DateUtil.getCurTime());
        memberInfo.setIsDeleted(0);
        memberInfo.setLastOperatorId(lastOperatorId);
        
        memberInfoMapper.insert(memberInfo);
        
        //添加会员账户表
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAccountId(memberInfo.getMemberId());
        String hash = StringUtil.encryptPwd(payPassword);
        payPassword = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        memberAccount.setPayPassword(payPassword);
        memberAccount.setPasswordSalt(passwordSalt);
        //储值总金额
        BigDecimal totalBalanceAmount = balanceAmount.add(rewardAmount);
        
        if (openType == 1) {
            memberAccount.setTotalAmount(totalBalanceAmount);
            memberAccount.setBalanceAmount(totalBalanceAmount);
        }
        else {
            memberAccount.setTotalAmount(rewardAmount);
            memberAccount.setBalanceAmount(rewardAmount);
        }
        
        memberAccount.setCreateTime(DateUtil.getCurTime());
        memberAccount.setLastOperatorId(lastOperatorId);
        
        memberAccountMapper.insert(memberAccount);
        
        //新增资金流水
        /*if (openType == 1) {
            MoneyFlow moneyFlow = new MoneyFlow(); 
            moneyFlow.setAccountId(memberAccount.getAccountId());
            moneyFlow.setFlowType(2);
            moneyFlow.setBusinessType(4);
            moneyFlow.setFlowAmount(balanceAmount);
            moneyFlow.setBalanceAmount(totalBalanceAmount);            
            moneyFlow.setFlowTime(DateUtil.getCurTime());
            moneyFlow.setStoreId(storeId);
            moneyFlow.setIsDeleted(0);
            moneyFlow.setLastOperatorId(lastOperatorId);
            moneyFlowMapper.insert(moneyFlow);
        }*/
        
        
        commissionAndGift(memberInfo.getMemberId(), recommendId, commissionAmount, calculateAmount, giftmoneyAmount, balanceAmount, 
                cashAmount, openType, unionpayAmount, pastDate, partType, 4, storeId, rewardAmount, lastOperatorId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 充值
    * @author 王大爷
    * @date 2015年9月10日 下午7:12:25
    * @param memberId 会员标识
    * @param chargeAmount 充值金额
    * @param cashAmount 现金支付
    * @param unionpayAmount 银联支付
    * @param recommendId 提成员工标识
    * @param commissionAmount 提成金额
    * @param calculateAmount 业绩金额
    * @param giftmoneyAmount 礼金金额
    * @param pastDate 过期时间
    * @param partType 分期方式
    * @param rewardAmount 卡金
    * @param type 确认情况
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     * @throws ParseException 
     */
    @Transactional
    public BaseDto rechargeMemberInfo(Integer memberId, BigDecimal chargeAmount, BigDecimal cashAmount, 
            BigDecimal  unionpayAmount, List<Integer> recommendId, List<BigDecimal> commissionAmount, List<BigDecimal> calculateAmount, 
            BigDecimal giftmoneyAmount, Integer pastDate, Integer partType, 
            BigDecimal rewardAmount, Integer type, Integer storeId, Integer lastOperatorId) throws ParseException{
        MemberDto memberDto = memberInfoMapper.selectByMemberResultMap(memberId);
        
        if (type == 1) {
            if (Float.valueOf(memberDto.getMemberLevel().getChargeMinMoney().toString()) > Float.valueOf(chargeAmount.toString())) {
                return new BaseDto(41007, "充值金额小于最低充值金额！");
            }
        }
        
        //修改账户信息
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("chargeAmount", Float.valueOf(chargeAmount.toString()) + Float.valueOf(rewardAmount.toString()));
        hashMap.put("accountId", memberDto.getMemberAccount().getAccountId());
        memberAccountMapper.updateCharge(hashMap);

        
        commissionAndGift(memberId, recommendId, commissionAmount, calculateAmount, giftmoneyAmount, chargeAmount, 
                cashAmount, 1, unionpayAmount, pastDate, partType, 5, storeId, rewardAmount, lastOperatorId);
        
        //更新缓存中的会员数据
        memberInfoService.wipeCache(memberId);
        String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
        if (StringUtils.isNotBlank(openId)) {
            String url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME.replace("{storeId}", String.valueOf(storeId)).
                    replace("{businessType}", "1");
            rabbitService.sendMemberCharge(storeId, url, openId, memberDto.getStoreInfo().getStoreName(), memberDto.getMemberLevel().getLevelName(), 
                    chargeAmount.toString(), memberDto.getMemberAccount().getBalanceAmount().add(chargeAmount).toString(), DateUtil.getCurTime());
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 会员升级
    * @author 王大爷
    * @date 2015年10月31日 下午9:28:23
    * @param memberId 会员标识
    * @param levelId 升级等级
    * @param openType 升级方式（）
    * @param recommendId 提成员工
    * @param commissionAmount 提成金额
    * @param calculateAmount 业绩金额
    * @param giftmoneyAmount 礼金金额
    * @param pastDate 过期天数
    * @param partType 分期方式
    * @param cashAmount 现金支付金额
    * @param unionpayAmount 网银支付金额
    * @param rewardAmount 卡金
    * @param storeId 门店标识
    * @param lastOperatorId 操作人员
    * @return BaseDto
     * @throws ParseException 
     */
    @Transactional
    public BaseDto upgradeMemberInfo(Integer memberId, Integer levelId, Integer openType, List<Integer> recommendId, 
            List<BigDecimal> commissionAmount, List<BigDecimal> calculateAmount, BigDecimal giftmoneyAmount, Integer pastDate, Integer partType, 
            BigDecimal cashAmount, BigDecimal unionpayAmount, BigDecimal rewardAmount, Integer storeId, Integer lastOperatorId) throws ParseException{
        //添加会员信息表
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setLevelId(levelId);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        memberInfo.setMemberId(memberId);
        memberInfo.setLastOperatorId(lastOperatorId);
        
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        
        //添加会员账户表

        
        BigDecimal balanceAmount = null;
        
        balanceAmount = new BigDecimal(Float.parseFloat(unionpayAmount.toString()) + Float.parseFloat(cashAmount.toString()));
        
        //如果是充值升级
        Map<String, Object> hashMap = new HashMap<String, Object>();
        
        if (openType == 1) {
            hashMap.put("chargeAmount", Float.valueOf(balanceAmount.toString()) + Float.valueOf(rewardAmount.toString()));
        }
        else {
            hashMap.put("chargeAmount", rewardAmount);
        }
        hashMap.put("accountId", memberInfo.getMemberId());
        memberAccountMapper.updateCharge(hashMap);
                
        //更新缓存中的会员数据
        memberInfoService.wipeCache(memberId);
        
        commissionAndGift(memberId, recommendId, commissionAmount, calculateAmount, giftmoneyAmount, balanceAmount, 
                cashAmount, openType, unionpayAmount, pastDate, partType, 6, storeId, rewardAmount, lastOperatorId);
        changeMemberOrder(memberId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 会员升级或散客转会员时调用
    * @author 王大爷
    * @date 2015年11月27日 下午4:52:18
    * @param memberId 会员标识
     */
    public void changeMemberOrder(Integer memberId) {
        //如果有未结账的订单，将其金额修改
        List<OrderInfo> orderInfoList = orderInfoMapper.selectIsNotOver(memberId);
        
        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
        
        MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(memberInfo.getLevelId());
        for (int i = 0; i < orderInfoList.size(); i++) {
            List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderId(orderInfoList.get(i).getOrderId());
            for (int j = 0; j < orderDetailList.size(); j++) {
                OrderDetail orderDetail = orderDetailList.get(j);
                OrderDetail updayeDetail = new OrderDetail();
                updayeDetail.setDetailId(orderDetail.getDetailId());
                BigDecimal discountAmount = new BigDecimal(0);
                BigDecimal rate = new BigDecimal(100);
                
                if (orderDetail.getOrderType() == 1) {
                    ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(orderDetail.getProjectId());
                    
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("memberId", memberId);
                    map.put("projectId", projectInfo.getProjectId());
                    
                    ProjectDiscount obj = projectDiscountMapper.selectByDiscount(map);
                    
                    //该项目对应该会员的会员等级不存在特定价格
                    if (obj == null) {
                        discountAmount = projectInfo.getProjectPrice().multiply(new BigDecimal(memberLevel.getProjectDiscount()).divide(rate));
                    }
                    //该项目对应该会员的会员等级存在特定价格
                    else {
                        discountAmount = obj.getDiscountAmount();
                    }
                    
                    //扣除预约优惠
                    if (orderDetail.getIsAppoint() == 1) {
                        discountAmount.subtract(projectInfo.getAppointmentPrice());
                    }
                }
                else if (orderDetail.getOrderType() == 2) {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("memberId", memberId);
                    map.put("goodsId", orderDetail.getProjectId());
                    GoodsDiscount obj = goodsDiscountMapper.selectByDiscount(map);
                    
                    if (obj == null) {
                        discountAmount = orderDetail.getProjectPrice().multiply(new BigDecimal(memberLevel.getGoodsDiscount()).divide(rate));
                    }
                    else {
                        discountAmount = obj.getDiscountAmount();
                    }
                }
                
                BigDecimal zore = new BigDecimal(0);
                if (discountAmount.compareTo(zore) == -1) {
                    discountAmount = zore;
                }
                updayeDetail.setDiscountAmount(new BigDecimal(discountAmount.floatValue()));
                orderDetailMapper.updateByPrimaryKey(updayeDetail);
            }
            //汇总订单金额
            orderInfoMapper.updateTotalPrice(orderInfoList.get(i).getOrderId());
        }
    }
    
    /**
     * 新增员工提成及礼金
    * @author 王大爷
    * @date 2015年9月10日 下午7:32:42
    * @param memberId 会员标识
    * @param recommendId 提成员工标识
    * @param commissionAmount 提成金额
    * @param calculateAmount 员工业绩金额
    * @param giftmoneyAmount 礼金金额
    * @param receivableAmount 充值金额
    * @param cashAmount 现金支付
    * @param unionpayAmount 银联支付
    * @param pastDate 过期天数
    * @param partType 分期方式
    * @param type 类型（4:开卡提成,5:充值提成,6:升级提成）
    * @param storeId 门店标识
    * @param rewardAmount 流水标识
    * @param openType 开卡方式
    * @param lastOperatorId 操作人
     * @throws ParseException 
     */
    @Transactional
    private void commissionAndGift(Integer memberId, List<Integer> recommendId, List<BigDecimal> commissionAmount,
            List<BigDecimal> calculateAmount, BigDecimal giftmoneyAmount, 
            BigDecimal receivableAmount, BigDecimal cashAmount, Integer openType,
            BigDecimal unionpayAmount, Integer pastDate, Integer partType, Integer type, Integer storeId, 
            BigDecimal rewardAmount, Integer lastOperatorId) throws ParseException{
        
        //添加订单信息
        OrderInfo orderInfo =  new OrderInfo();
        String businessDesc = "";
        if (type == 4) {
            businessDesc = "开卡";
        }
        else if (type == 5) {
            businessDesc = "充值";
        }
        else {
            businessDesc = "升级";
        }
        String orderCode = staffService.getOrderCode("order_info", storeId);
        orderInfo.setOrderCode(orderCode);
        orderInfo.setMemberId(memberId);
        orderInfo.setReceivableAmount(receivableAmount);
        orderInfo.setDiscountAmount(receivableAmount);
        orderInfo.setCashAmount(cashAmount);
        orderInfo.setUnionpayAmount(unionpayAmount);
        orderInfo.setRealAmount(cashAmount.add(unionpayAmount));
        orderInfo.setOrderStatus(4);
        orderInfo.setStoreId(storeId);
        orderInfo.setIsDeleted(0);
        orderInfo.setCreateTime(DateUtil.getCurTime());
        orderInfo.setLastOperatorId(lastOperatorId);
        orderInfo.setOrderType(2);
        orderInfoMapper.insert(orderInfo);
        
        MemberDto memberDto = memberInfoMapper.selectByMemberResultMap(memberId);
        //添加订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderInfo.getOrderId());
        orderDetail.setProjectName(memberDto.getPhone());
        orderDetail.setOrderType(type);
        orderDetail.setProjectPrice(receivableAmount);
        orderDetail.setRealPrice(receivableAmount);
        orderDetail.setDiscountAmount(receivableAmount);
        orderDetail.setRealPrice(receivableAmount);
        orderDetail.setProjectCount(1);
        orderDetail.setStoreId(storeId);
        orderDetail.setCreateTime(DateUtil.getCurTime());
        orderDetail.setLastOperatorId(lastOperatorId);
        orderDetailMapper.insert(orderDetail);
        
        Integer detailId = orderDetail.getDetailId();
        if (openType == 1) {
            //新增资金流水
            MoneyFlow moneyFlow = new MoneyFlow(); 
            moneyFlow.setAccountId(memberId);
            moneyFlow.setFlowType(2);
            if (type == 4) {
                moneyFlow.setBusinessType(4);
            }
            else if (type == 5) {
                moneyFlow.setBusinessType(2);
            }
            else {
                moneyFlow.setBusinessType(5);
            }
            moneyFlow.setBusinessDesc(businessDesc);
            moneyFlow.setOrderId(orderInfo.getOrderId());
            moneyFlow.setFlowAmount(receivableAmount);
            moneyFlow.setFlowTime(DateUtil.getCurTime());
            moneyFlow.setStoreId(storeId);
            moneyFlow.setIsDeleted(0);
            moneyFlow.setLastOperatorId(lastOperatorId);
            moneyFlowMapper.insert(moneyFlow);
        }
        
        if (Float.valueOf(rewardAmount.toString()) > 0) {
            //新增资金流水
            MoneyFlow moneyFlow = new MoneyFlow(); 
            moneyFlow.setAccountId(memberId);
            moneyFlow.setFlowType(2);
            moneyFlow.setBusinessType(6);
            moneyFlow.setOrderId(orderInfo.getOrderId());
            moneyFlow.setFlowAmount(rewardAmount);
            moneyFlow.setFlowTime(DateUtil.getCurTime());
            moneyFlow.setStoreId(storeId);
            moneyFlow.setIsDeleted(0);
            moneyFlow.setLastOperatorId(lastOperatorId);
            moneyFlow.setBusinessDesc(businessDesc + "赠送");
            moneyFlowMapper.insert(moneyFlow);
        }
        
        //添加员工开卡充值提成表
        if (recommendId.size() > 0) {
            for (int i = 0; i < recommendId.size(); i++) {
                EmployeeCommission employeeCommission = new EmployeeCommission();
                employeeCommission.setDetailId(detailId);
                employeeCommission.setOrderType(type);
                employeeCommission.setEmployeeId(recommendId.get(i));
                employeeCommission.setCommissionAmount(commissionAmount.get(i));
                employeeCommission.setCommissionCalculate(calculateAmount.get(i));
                employeeCommission.setChargeTime(DateUtil.getCurTime());
                employeeCommissionMapper.insert(employeeCommission);
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                
                EmployeeObjective employeeObjective = new EmployeeObjective();
                employeeObjective.setActualChargeSales(calculateAmount.get(i));
                employeeObjective.setEmployeeId(recommendId.get(i));
                employeeObjective.setObjectiveMonth(dateFormat.format(new Date()));
                employeeObjectiveMapper.updateActual(employeeObjective);
            }
        }
        
        //添加礼金
        if (giftmoneyAmount.compareTo(BigDecimal.ZERO) == 1) {
            addGiftmoney(memberId, partType, giftmoneyAmount, pastDate, businessDesc + "赠送", businessDesc + "赠送", lastOperatorId, detailId);
        }
    }
    
    /**
     * 校验账户密码及转账
    * @author 王大爷
    * @date 2015年9月11日 下午2:23:46
    * @param outMemberId 转出会员标识
    * @param inMemberId 转入会员标识
    * @param chargeAmount 金额
    * @param password 密码
    * @param storeId 门店标识
    * @return BaseDto
     */
    @Transactional
    public BaseDto checkoutAccount(Integer outMemberId, Integer inMemberId, 
            BigDecimal chargeAmount, String password, Integer storeId){
        MemberDto outMemberDto = memberInfoMapper.selectByMemberResultMap(outMemberId);
        Integer outAccountId = outMemberDto.getMemberAccount().getAccountId();
        MemberDto inMemberDto = memberInfoMapper.selectByMemberResultMap(inMemberId);
        Integer inAccountId = inMemberDto.getMemberAccount().getAccountId();
        MemberAccount outMemberAccount = memberAccountMapper.selectByPrimaryKey(outAccountId);
        
        //检查用户密码
        if (!StringUtil.mD5(password + outMemberAccount.getPasswordSalt()).equals(outMemberAccount.getPayPassword())) {
            return new BaseDto(41007, "转出账户密码不正确！");
        }
        
        Map<String, Object> hashAdd = new HashMap<String, Object>();
        hashAdd.put("chargeAmount", chargeAmount);
        hashAdd.put("accountId", inAccountId);
        
        memberAccountMapper.updateAdd(hashAdd);
        
        Map<String, Object> hashDecrease = new HashMap<String, Object>();
        hashDecrease.put("chargeAmount", chargeAmount);
        hashDecrease.put("accountId", outAccountId);
        
        memberAccountMapper.updateDecrease(hashDecrease);
        
        //添加账户记录资金流水
        MoneyFlow outMoneyFlow = new MoneyFlow(); 
        outMoneyFlow.setAccountId(outAccountId);
        outMoneyFlow.setFlowType(1);
        outMoneyFlow.setBusinessType(3);
        outMoneyFlow.setFlowAmount(chargeAmount);
        outMoneyFlow.setBalanceAmount(new BigDecimal(Float.parseFloat(String.valueOf(outMemberAccount.getBalanceAmount())) - 
                Float.parseFloat(String.valueOf(chargeAmount))));
        outMoneyFlow.setOrderId(inAccountId);
        outMoneyFlow.setFlowTime(DateUtil.getCurTime());
        outMoneyFlow.setStoreId(storeId);
        outMoneyFlow.setIsDeleted(0);
        moneyFlowMapper.insert(outMoneyFlow);
        
        //更新缓存中的会员数据
        memberInfoService.wipeCache(inAccountId);
        memberInfoService.wipeCache(outAccountId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 根据会员标识查询转出记录（最近4条）
    * @author 王大爷
    * @date 2015年10月10日 下午2:54:04
    * @param memberId 会员标识
    * @return BaseDto
     */
    public BaseDto selectZcDetail(Integer memberId){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("memberId", memberId);
        map.put("businessType", 3);
        List<MoneyFlowDto> list = moneyFlowMapper.selectDetail(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
    }
    
    /**
     * 根据会员标识查询充值记录（最近4条）
    * @author 王大爷
    * @date 2015年10月10日 下午2:54:04
    * @param memberId 会员标识
    * @return BaseDto
     */
    public BaseDto selectCzDetail(Integer memberId){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("memberId", memberId);
        map.put("businessType", 2);
        List<MoneyFlowDto> list = moneyFlowMapper.selectDetail(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
    }

    
    /**
     * @author 张进军
     * @date Dec 10, 2015 8:39:00 PM
     * @param memberId          会员标识
     * @param giftmoneyAmount   礼金金额
     * @param part              礼金分期数
     * @param overdueMonth      礼金过期月份数
     * @param integralAmount    积分金额
     * @param coupon            优惠券标识集合，逗号分割
     * @param comment           备注信息
     * @param employeeId        操作员工标识
     * @return  成功返回码为0，失败为其他返回码
     */
    @Transactional
    public BaseDto presentGift(Integer memberId, Integer giftmoneyAmount, Integer part, Integer overdueMonth, Integer integralAmount, 
            String coupon, String comment, int employeeId) {
        String time = DateUtil.getCurTime();
        
        //检查是否赠送礼金
        if (giftmoneyAmount > 0) {
            BigDecimal money = new BigDecimal(giftmoneyAmount);
            addGiftmoney(memberId, part, money, overdueMonth, "门店赠送", comment, employeeId, null);
            
            MemberPresentRecord memberPresentRecord = new MemberPresentRecord();
            memberPresentRecord.setMemberId(memberId);
            memberPresentRecord.setEmployeeId(employeeId);
            memberPresentRecord.setType(2);
            memberPresentRecord.setGift(giftmoneyAmount);
            memberPresentRecord.setComment(comment);
            memberPresentRecord.setTime(time);
            memberPresentRecordMapper.insert(memberPresentRecord);
        }
        
        //检查是否赠送优惠券
        if (StringUtils.isNotBlank(coupon)) {
            String[] couponList = coupon.split(",");
            for (String c : couponList) {
                int couponId = Integer.parseInt(c);
                MemberCoupon memberCoupon = new MemberCoupon();
                memberCoupon.setMemberInfoId(memberId);
                memberCoupon.setCouponId(couponId);
                memberCoupon.setIsUsed(0);
                memberCoupon.setGrantTime(time);
                memberCouponMapper.insert(memberCoupon);
                
                MemberPresentRecord memberPresentRecord = new MemberPresentRecord();
                memberPresentRecord.setMemberId(memberId);
                memberPresentRecord.setEmployeeId(employeeId);
                memberPresentRecord.setType(3);
                memberPresentRecord.setGift(couponId);
                memberPresentRecord.setComment(comment);
                memberPresentRecord.setTime(time);
                memberPresentRecordMapper.insert(memberPresentRecord);
            }
        }
        
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId);
        //检查是否赠送积分
        if (integralAmount > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("accountId", memberId);
            map.put("usedBalanceAmount", 0);
            map.put("memberIntegral", integralAmount);
            map.put("consumeCount", 0);
            map.put("currDate", time);
            memberAccountMapper.updateMemberCashier(map);
            
            IntegralFlow integralFlow = new IntegralFlow();
            integralFlow.setAccountId(memberId);
            integralFlow.setOrderId(null);
            integralFlow.setFlowAmount(integralAmount);
            integralFlow.setBalanceAmount(memberInfo.getBalanceIntegral());
            integralFlow.setBusinessType("门店赠送");
            integralFlow.setBusinessDesc(comment);
            integralFlow.setFlowType(2);
            integralFlow.setFlowTime(time);
            integralFlowMapper.insert(integralFlow);
            
            MemberPresentRecord memberPresentRecord = new MemberPresentRecord();
            memberPresentRecord.setMemberId(memberId);
            memberPresentRecord.setEmployeeId(employeeId);
            memberPresentRecord.setType(1);
            memberPresentRecord.setGift(integralAmount);
            memberPresentRecord.setComment(comment);
            memberPresentRecord.setTime(time);
            memberPresentRecordMapper.insert(memberPresentRecord);
        }
        
        memberInfoService.wipeCache(memberId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 增加礼金到会员账户
    * @author 张进军
    * @date Dec 10, 2015 9:03:14 PM
    * @param memberId       会员标识
    * @param part           分期数
    * @param totalMoney     礼金赠送总金额
    * @param overdueMonth   过期月份数
    * @param businessType   业务类型    
    * @param businessDesc   业务描述
    * @param employeeId     员工标识
    * @param detailId       订单明细标识，可为空
     */
    @Transactional
    private void addGiftmoney(int memberId, int part, BigDecimal totalMoney, int overdueMonth, 
            String businessType, String businessDesc, int employeeId, Integer detailId) {
        BigDecimal money = totalMoney.divide(new BigDecimal(part), 2, BigDecimal.ROUND_HALF_DOWN);
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("totalGiftmoneyAmount", money);
        hash.put("balanceGiftmoneyAmount", money);
        hash.put("accountId", memberId);
        memberAccountMapper.updateGiftmoney(hash);
        
        GiftmoneyFlow giftmoneyFlow = new GiftmoneyFlow();
        giftmoneyFlow.setAccountId(memberId);
        giftmoneyFlow.setDetailId(detailId);
        giftmoneyFlow.setFlowType(2);
        giftmoneyFlow.setFlowAmount(money);
        giftmoneyFlow.setFlowTime(DateUtil.getCurTime());
        giftmoneyFlow.setBusinessType(businessType);
        giftmoneyFlow.setBusinessDesc(businessDesc);
        giftmoneyFlow.setIsDeleted(0);
        giftmoneyFlowMapper.insert(giftmoneyFlow);
        
        String date = DateUtil.getCurDate();
        String time = DateUtil.getCurTime();
        int num = part;
        for (int i = 0; i < num; i++) {
            GiftmoneyDetail giftmoneyDetail = new GiftmoneyDetail();
            giftmoneyDetail.setAccountId(memberId);
            giftmoneyDetail.setTotalAmount(totalMoney);
            giftmoneyDetail.setNowMoney(money);
            giftmoneyDetail.setResidueNowMoney(money);
            giftmoneyDetail.setDetailId(detailId);
            
            String beginDate = date;
            if (i == 0) {
                giftmoneyDetail.setIsPresent(1);
            }
            else {
                giftmoneyDetail.setIsPresent(0);
                try {
                	beginDate = DateUtil.getDateAfterMonthsStr(date, i);
    			} 
                catch (ParseException e1) {
    			}
            }
            giftmoneyDetail.setStartDate(beginDate);
            
            if (overdueMonth == 0) {
                giftmoneyDetail.setEndDate("永久");
            }
            else {
                try {
                	giftmoneyDetail.setEndDate(DateUtil.getDateAfterMonthsStr(beginDate, overdueMonth));
                }
                catch (ParseException e) {
                }
            }
            
            giftmoneyDetail.setPartType(part);
            giftmoneyDetail.setPartNumber(num - i - 1);
            giftmoneyDetail.setCreateTime(time);
            giftmoneyDetail.setIsDeleted(0);
            giftmoneyDetail.setLastOperatorId(employeeId);
            giftmoneyDetailMapper.insert(giftmoneyDetail);
        }
        memberInfoService.wipeCache(memberId);
    }
}
