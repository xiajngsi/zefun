package com.zefun.web.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.OpenCardService;


/**
 * 收银记账
 * @author laowang
 * @date Jun 30, 2015 4:42:19 PM 
 */
@Controller
public class OpenCardController extends BaseController{
    
    /** 开卡充值*/
    @Autowired
    private OpenCardService openCardService;
     
    /** 会员*/
    @Autowired
    private MemberInfoService memberInfoService;
    /**
     * 初始化开卡充值页面
    * @author 王大爷
    * @date 2015年9月8日 下午8:31:36
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.KeepAccounts.INITIALIZE_OPENCARD, method = RequestMethod.GET)
    public ModelAndView initializeOpenCard(HttpServletRequest request, HttpServletResponse response){
        return openCardService.initializeOpenCard(getStoreId(request));
    }
	
    /**
     * 开卡
    * @author 王大爷
    * @date 2015年9月9日 下午3:53:08
    * @param request 返回
    * @param response 请求
    * @param phone 手机号
    * @param name 姓名
    * @param sex 性别 
    * @param levelId 会员级别
    * @param openType 开卡方式(1：充值开卡、2：现金购卡)
    * @param recommend 员工提成
    * @param giftmoneyAmount 礼金金额
    * @param pastDate 过期天数
    * @param partType 分期方式
    * @param balanceAmount 储值金额
    * @param rewardAmount 卡金
    * @param messageType 是否接收短信（0：否、1、是）
    * @param cashAmount 现金支付
    * @param unionpayAmount 银联支付
    * @param payPassword 支付密码
    * @return BaseDto
     * @throws ParseException 
     */
    @RequestMapping(value = Url.KeepAccounts.ADD_MEMBERINFO, method = RequestMethod.POST)
    @ResponseBody
	public BaseDto addMemberInfo(HttpServletRequest request, HttpServletResponse response, 
	        String phone, String name, String sex, Integer levelId, Integer openType, String recommend, BigDecimal giftmoneyAmount, 
	        Integer pastDate, Integer partType, BigDecimal balanceAmount, BigDecimal rewardAmount, Integer messageType, 
	        BigDecimal cashAmount, BigDecimal unionpayAmount, String payPassword) throws ParseException{
        //提成员工
        List<Integer> recommendId = new ArrayList<Integer>();
        //提成金额
        List<BigDecimal> commissionAmount = new ArrayList<BigDecimal>();
        //业绩金额
        List<BigDecimal> calculateAmount = new ArrayList<BigDecimal>();
        if (recommend != "") {
            String[] recommends = recommend.split(",");
            for (int i = 0; i < recommends.length; i++) {
                String recommendStr = recommends[i];
                recommendId.add(Integer.parseInt(recommendStr.split(":")[0]));
                commissionAmount.add(new BigDecimal(recommendStr.split(":")[1]));
                calculateAmount.add(new BigDecimal(recommendStr.split(":")[2]));
            }
        }
        
	    return openCardService.addMemberInfo(phone, name, sex, levelId, openType, recommendId, commissionAmount, calculateAmount, 
	            giftmoneyAmount, pastDate, partType, balanceAmount, rewardAmount, messageType, cashAmount, unionpayAmount, payPassword, 
	            getStoreId(request), getUserId(request));
	}
    
    /**
     * 充值
    * @author 王大爷
    * @date 2015年9月10日 下午7:12:25
    * @param request 返回
    * @param response 请求
    * @param memberId 会员标识
    * @param chargeAmount 充值金额
    * @param cashAmount 现金支付
    * @param unionpayAmount 银联支付
    * @param recommend 提成员工标识
    * @param giftmoneyAmount 礼金
    * @param pastDate 过期天数
    * @param partType 分期方式
    * @param rewardAmount 卡金
    * @param type 确认情况
    * @return BaseDto
     * @throws ParseException 
     */
    @RequestMapping(value = Url.KeepAccounts.RECHARGE_MEMBERINFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto rechargeMemberInfo(HttpServletRequest request, HttpServletResponse response, Integer memberId, 
            BigDecimal chargeAmount, BigDecimal cashAmount, BigDecimal  unionpayAmount, String recommend, BigDecimal giftmoneyAmount, 
            Integer pastDate, Integer partType, BigDecimal rewardAmount, Integer type) throws ParseException{
        List<Integer> recommendId = new ArrayList<Integer>();
        List<BigDecimal> commissionAmount = new ArrayList<BigDecimal>();
        //业绩金额
        List<BigDecimal> calculateAmount = new ArrayList<BigDecimal>();
        if (recommend != "") {
            String[] recommends = recommend.split(",");
            for (int i = 0; i < recommends.length; i++) {
                String recommendStr = recommends[i];
                recommendId.add(Integer.parseInt(recommendStr.split(":")[0]));
                commissionAmount.add(new BigDecimal(recommendStr.split(":")[1]));
                calculateAmount.add(new BigDecimal(recommendStr.split(":")[2]));
            }
        }
        
        return openCardService.rechargeMemberInfo(memberId, chargeAmount, cashAmount, unionpayAmount, recommendId,
                commissionAmount, calculateAmount, giftmoneyAmount, pastDate, partType, rewardAmount, type, getStoreId(request), getUserId(request));
    }
    
    /**
     * 会员升级
    * @author 王大爷
    * @date 2015年9月9日 下午3:53:08
    * @param request 返回
    * @param response 请求
    * @param memberId 会员标识
    * @param levelId 会员级别
    * @param openType 开卡方式(1：充值开卡、2：现金购卡)
    * @param recommend 开卡员工
    * @param giftmoneyAmount 礼金
    * @param pastDate 过期天数
    * @param partType 分期方式
    * @param cashAmount 现金支付
    * @param unionpayAmount 银联支付
    * @param rewardAmount 卡金
    * @return BaseDto
     * @throws ParseException 
     */
    @RequestMapping(value = Url.KeepAccounts.UPGRADE_MEMBERINFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto upgradeMemberInfo(HttpServletRequest request, HttpServletResponse response, 
            Integer memberId, Integer levelId, Integer openType, String recommend, 
            BigDecimal giftmoneyAmount, Integer pastDate, Integer partType, BigDecimal cashAmount, 
            BigDecimal unionpayAmount, BigDecimal rewardAmount) throws ParseException{
        //提成员工
        List<Integer> recommendId = new ArrayList<Integer>();
        //提成金额
        List<BigDecimal> commissionAmount = new ArrayList<BigDecimal>();
        //业绩金额
        List<BigDecimal> calculateAmount = new ArrayList<BigDecimal>();
        if (recommend != "") {
            String[] recommends = recommend.split(",");
            for (int i = 0; i < recommends.length; i++) {
                String recommendStr = recommends[i];
                recommendId.add(Integer.parseInt(recommendStr.split(":")[0]));
                commissionAmount.add(new BigDecimal(recommendStr.split(":")[1]));
                calculateAmount.add(new BigDecimal(recommendStr.split(":")[2]));
            }
        }
        
        return openCardService.upgradeMemberInfo(memberId, levelId, openType, recommendId, 
                commissionAmount, calculateAmount, giftmoneyAmount, pastDate, partType, 
                cashAmount, unionpayAmount, rewardAmount, getStoreId(request), getUserId(request));
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Dec 10, 2015 9:28:02 PM
    * @param memberId           会员标识
    * @param giftmoneyAmount    礼金赠送金额
    * @param part               礼金分期期数
    * @param overdueMonth       过期月份数
    * @param integralAmount     积分赠送数量
    * @param coupon             优惠券标识集合，逗号分割
    * @param comment            备注信息
    * @param request            请求对象
    * @param response           响应对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.KeepAccounts.PRESENT_GIFT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto presentGift(Integer memberId, Integer giftmoneyAmount, Integer part, 
            Integer overdueMonth, Integer integralAmount, String coupon, String comment,
            HttpServletRequest request, HttpServletResponse response){
        int employeeId = getUserId(request);
        return openCardService.presentGift(memberId, giftmoneyAmount, part, overdueMonth, integralAmount, coupon, comment, employeeId);
    }
    
    
    /**
     * 校验账户密码及转账
    * @author 王大爷
    * @date 2015年9月11日 上午11:39:53
    * @param request 返回
    * @param response 请求
    * @param outMemberId 转出会员标识
    * @param inMemberId 转入会员标识
    * @param chargeAmount 金额
    * @param password 密码
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.CHECKOUT_ACCOUNT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto checkoutAccount(HttpServletRequest request, HttpServletResponse response, Integer outMemberId, 
            Integer inMemberId, BigDecimal chargeAmount, String password){
        return openCardService.checkoutAccount(outMemberId, inMemberId, chargeAmount, password, getStoreId(request));
    }
    
    /**
     * 根据会员标识查询转出记录
    * @author 王大爷
    * @date 2015年10月10日 下午2:55:10
    * @param memberId 会员标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.SELECT_ZCDETAIL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectZcDetail(Integer memberId){
        return openCardService.selectZcDetail(memberId);
    }
    
    /**
     * 根据会员标识查询转出记录
    * @author 王大爷
    * @date 2015年10月10日 下午2:55:10
    * @param memberId 会员标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.SELECT_CZDETAIL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectCzDetail(Integer memberId){
        return openCardService.selectCzDetail(memberId);
    }
    
    /**
     * 校验是否已存在该手机号码的会员
    * @author 王大爷
    * @date 2015年10月15日 下午4:36:58
    * @param request 返回
    * @param response 请求
    * @param phone 手机号码
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.IS_CHECK_ACCOUNT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto isCheckAccount(HttpServletRequest request, HttpServletResponse response, String phone){
        if (memberInfoService.isExists(phone, getStoreId(request))) {
            return new BaseDto(41007, "该手机号码已存在，请重新输入！");
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
