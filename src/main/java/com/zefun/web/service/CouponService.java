package com.zefun.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CouponInfoDto;
import com.zefun.web.dto.ProjectInfoDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.wechat.service.WeixinMessageService;

/**
 * 优惠券
* @author 高国藩
* @date 2015年9月14日 下午2:28:45
 */
@Service
public class CouponService {
    /**优惠券*/
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    /**项目*/
    @Autowired
    private ProjectService projectService;
    /**套餐*/
    @Autowired
    private ComboInfoService comboInfoService;
    /**商品*/
    @Autowired
    private GoodsInfoService goodsInfoService;
    /**会员卡*/
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /**筛选器*/
    @Autowired
    private MemberScreeningMapper memberScreeningMapper;
    /**日志*/
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(CouponService.class);
    /**会员优惠券存储*/
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    /**rabbite服务类*/
    @Autowired
    private RabbitService rabbitService;
    /**会员和openId关联关系*/
    @Autowired
    private WechatMemberMapper wechatMemberMapper;
    /**微信门店关联*/
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    /**微信发送*/
    @Autowired
    private WeixinMessageService weixinMessageService;

    /**
     * 进入优惠券管理页面
    * @author 高国藩
    * @date 2015年9月14日 下午2:24:54
    * @param storeId 门店
    * @return 跳转页面
     */
    public ModelAndView viewCoupons(Integer storeId) {
        Page<CouponInfoDto> page = new Page<CouponInfoDto>();
        page.setPageNo(1);
        page.setPageSize(50);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<CouponInfoDto> cs = couponInfoMapper.selectByPage(page);
        page.setResults(cs);
        //会员卡
        List<MemberLevel> memberLevels = memberLevelMapper.selectByStoreId(storeId);
        //筛选器
        List<MemberScreening> memberScreenings = memberScreeningMapper.selectByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Coupon.VIEW_COUPON);
        view.addObject("page", page);
        view.addObject("memberLevels", memberLevels);
        view.addObject("memberScreenings", memberScreenings);
        return view;
    }
    
    /**
     * 动态分页查询
    * @author 高国藩
    * @date 2015年10月11日 上午11:53:27
    * @param page 分页元素
    * @return 分页结果
     */
    public BaseDto viewCouponsByPage(Page<CouponInfoDto> page) {
        List<CouponInfoDto> cs = couponInfoMapper.selectByPage(page);
        page.setResults(cs);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 新增优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponInfo 优惠券
    * @param storeId 门店
    * @return 状态
     * @throws ParseException  异常
     */
    public BaseDto addCoupons(CouponInfo couponInfo, Integer storeId) throws ParseException {
        couponInfo.setStoreId(storeId);
        couponInfo.setHasExchangeCount(0);
        couponInfo.setHasUseCount(0);
        couponInfo.setIsDelete(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //如果为1,立刻发布,存入当下时间
        if (couponInfo.getCouponIsUse() == 1){
            couponInfo.setReleaseTime(sdf.format(new Date()));;
        }
        if (!sdf.parse(couponInfo.getCouponStopTime()).after(new Date())){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "过期时间不能小于当下时间");
        }
        
        int ok = couponInfoMapper.insert(couponInfo);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, couponInfoMapper.selectByPrimaryKey(couponInfo.getCouponId()));
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 查询项目商品套餐信息
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param type 要查询的类型 1:项目 2:商品 3:套餐
    * @param storeId 门店信息
    * @return 信息封装
     */
    public BaseDto listCouponsUse(Integer type, Integer storeId) {
        if (type==1){
            ProjectInfoDto projectInfoDto = new ProjectInfoDto();
            projectInfoDto.setStoreId(storeId);
            List<ProjectInfoDto> ls = projectService.queryProjectInfoList(projectInfoDto);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
        }
        if (type==2){
            List<GoodsInfo> ls = goodsInfoService.queryGoodsInfos(storeId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
        }
        if (type==3){
            ComboInfo comboInfo = new ComboInfo();
            comboInfo.setStoreId(storeId);
            List<ComboInfo> ls = comboInfoService.queryComboInfo(comboInfo); 
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "");
    }

    /**
     * 发布优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponId 优惠券主键
    * @return 跳转页面
     * @throws ParseException 
     */
    public BaseDto updateCoupons(Integer couponId) throws ParseException {
        CouponInfoDto couponInfoDto = couponInfoMapper.selectByPrimaryKey(couponId);
        String stopDate = couponInfoDto.getCouponStopTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (new Date().after(sdf.parse(stopDate))){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "当前优惠券已过期,不能发布");
        }
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponId(couponId);
        couponInfo.setCouponIsUse(1);
        couponInfo.setReleaseTime(sdf.format(new Date()));
        int ok = couponInfoMapper.updateByPrimaryKey(couponInfo);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 下架优惠券
    * @author 高国藩
    * @date 2015年9月14日 下午2:11:20
    * @param couponId 优惠券主键
    * @return 跳转页面
     */
    public BaseDto updateNoUseCoupons(Integer couponId) {
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponId(couponId);
        couponInfo.setCouponIsUse(0);
        couponInfo.setReleaseTime("");;
        int ok = couponInfoMapper.updateByPrimaryKey(couponInfo);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
    /**
     * 删除优惠券
    * @author 高国藩
    * @date 2015年11月4日 上午10:23:54
    * @param couponId 优惠券ID
    * @return 返回状态
     */
    public BaseDto actionDeleteCoupons(Integer couponId) {
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponId(couponId);
        couponInfo.setIsDelete(1);
        couponInfo.setCouponIsUse(0);
        int ok = couponInfoMapper.updateByPrimaryKey(couponInfo);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 发送优惠券
    * @author 高国藩
    * @date 2015年9月15日 上午11:49:39
    * @param couponId 优惠券
    * @param memberLevelId 会员卡
    * @param memberScreening 筛选器
    * @param storeId 门店信息
    * @return 发送状态
     */
    public BaseDto sendCoupons(Integer couponId, String memberLevelId,
            String memberScreening, Integer storeId) {
        /**首先做一个校验,如果没有公众号测试,即不可点击*/
        StoreWechat storeWechat = storeWechatMapper.selectByStoreId(storeId);
        if (storeWechat == null){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请先设置公众号");
        }
        //通过会员卡和筛选器获取会员信息,将其中id去重后,插入
        String[] strLevel = memberLevelId.split(",");
        String[] strSceening = memberScreening.split(",");
        List<Integer> memberIds = weixinMessageService.serchMemberIds(strSceening, strLevel); 
        List<MemberCoupon> coupons = new ArrayList<MemberCoupon>();
        for (int i = 0; i < memberIds.size(); i++) {
            MemberCoupon memberCoupon = new MemberCoupon();
            memberCoupon.setGrantTime(DateUtil.getCurDate());
            memberCoupon.setMemberInfoId(memberIds.get(i));
            memberCoupon.setCouponId(couponId);
            memberCoupon.setIsUsed(0);
            coupons.add(memberCoupon);
        }
        int ok = memberCouponMapper.insertList(coupons);
        //根据会员id获得会员对应的openIds
        List<String> touser = wechatMemberMapper.selectOpenIdsByMemberIdList(memberIds);
        //微信模板消息发送获得优惠券通知
        CouponInfoDto couponInfo = couponInfoMapper.selectByPrimaryKey(couponId);
        rabbitService.sendCoupons(storeId, couponInfo, touser);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
    }

}
