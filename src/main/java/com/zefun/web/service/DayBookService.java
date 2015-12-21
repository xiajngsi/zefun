package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DayBookDto;
import com.zefun.web.dto.DayBookQueryDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.dto.MemberDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberComboProject;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;

/**
 * 流水查询
 * @author luhw
 * @since 2015-11-05 15:39:11
 * 
 */
@Service
public class DayBookService {
	
	/** deptInfoMapper */
	@Autowired
	private DeptInfoMapper deptInfoMapper;
	
	/** orderInfoMapper */
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	/** orderDetailMapper*/
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	/** shiftMahjongEmployeeMapper*/
	@Autowired
	private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
	/** employeeCommissionMapper*/
	@Autowired
	private EmployeeCommissionMapper employeeCommissionMapper;
	/** employeeObjectiveMapper*/
	@Autowired
	private EmployeeObjectiveMapper employeeObjectiveMapper;
	
	/** 商品*/
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;
	
	/** 员工*/
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	/** 会员信息*/
	@Autowired
	private MemberInfoService memberInfoService;
	
	/** redisService*/
	@Autowired
	private RedisService redisService;
	
	/** rabbitService*/
	@Autowired
	private RabbitService rabbitService;
	
	/** 会员Mapper*/
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	/** 流水mapper*/
	@Autowired 
	private MoneyFlowMapper moneyFlowMapper;
	
	/** 会员账户*/
	@Autowired 
	private MemberAccountMapper memberAccountMapper;
	
	/** 礼金明细*/
	@Autowired 
	private GiftmoneyDetailMapper giftmoneyDetailMapper;
	
	/** 礼金流水*/
	@Autowired
	private GiftmoneyFlowMapper giftmoneyFlowMapper;
	
	/** 会员等级*/
	@Autowired
	private MemberLevelMapper memberLevelMapper;
	
	/** 会员套餐项目明细*/
	@Autowired
	private MemberComboProjectMapper memberComboProjectMapper;
	
	/** 会员套餐记录*/
	@Autowired
	private MemberComboRecordMapper memberComboRecordMapper;
	
	/** 用户优惠券*/
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	
	/** 积分*/
	@Autowired
	private IntegralFlowMapper integralFlowMapper;
	
	
	/**
	 * 根据查询条件查询流水
	 * @param storeId 门店标识
	 * @param beginTime 开始时间
	 * @param endTime   结束时间
	 * @return 流水信息
	 */
	public Map<String, Object> querydaybookInfo(Integer storeId, String beginTime, String endTime) {
		Map<String, Object> map = querydaybookInfo(storeId, new DayBookQueryDto(storeId, beginTime, endTime));
		return map;
	}
    
	/**
	 * 根据查询条件查询流水
	 * @param storeId 门店标识
	 * @param queryParams 查询条件
	 * @return 流水信息
	 */
	public Map<String, Object> querydaybookInfo(Integer storeId, DayBookQueryDto queryParams) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<DayBookDto> page = new Page<DayBookDto>();
		page.setPageNo(queryParams.getPageNo());
		page.setPageSize(queryParams.getPageSize());
		List<DayBookDto> dayBookInfos = null;
		queryParams.setDeptIds(null);
		queryParams.setStoreId(storeId);
		queryParams.setQueryCode(StringUtils.trim(queryParams.getQueryCode()));
		int index = (queryParams.getPageNo() - 1) * queryParams.getPageSize() - 1;
		if (index < 0) {
			index = 0;
		}
		queryParams.setPageNo(index);
		DayBookDto countBook = orderInfoMapper.selectDayBookInfoCount(queryParams);
		map.put("countBook", countBook);
		
		//拼装查询时间
		if (StringUtils.isNotBlank(queryParams.getBeginTime())) {
		    if (queryParams.getBeginTime().equals(queryParams.getEndTime())) {
	            map.put("searchDate", queryParams.getBeginTime());
	        }
	        else {
	            map.put("searchDate", queryParams.getBeginTime() + " 至 " + queryParams.getEndTime());
	        }
		}
		
		//统计各类型收入
		List<Map<String, Object>> detailCount = orderInfoMapper.selectDetailCountForType(queryParams);
		Map<String, Object> detailMap = new HashMap<String, Object>();
		for (Map<String, Object> m : detailCount) {
		    detailMap.put("moneyType_" + m.get("orderType").toString(), m.get("realPrice"));
		    detailMap.put("countType_" + m.get("orderType").toString(), m.get("count"));
        }
		//重新组装统计数据
		
		//1.项目
		Object obj = detailMap.get("moneyType_1");
		if (obj == null) {
		    map.put("projectSalesAmount", 0);
		}
		else {
		    map.put("projectSalesAmount", obj.toString() + "元(" + detailMap.get("countType_1") + "单)");
		}
		
		//2.商品
        Object obj2 = detailMap.get("moneyType_2");
        if (obj2 == null) {
            map.put("goodsSalesAmount", 0);
        }
        else {
            map.put("goodsSalesAmount", obj2.toString() + "元(" + detailMap.get("countType_2") + "单)");
        }
        
        //3.套餐
        Object obj3 = detailMap.get("moneyType_3");
        if (obj3 == null) {
            map.put("comboSalesAmount", 0);
        }
        else {
            map.put("comboSalesAmount", obj3.toString() + "元(" + detailMap.get("countType_3") + "单)");
        }
        
        //4/5/6充值开卡
        Object obj4 = detailMap.get("moneyType_4");
        Object obj5 = detailMap.get("moneyType_5");
        Object obj6 = detailMap.get("moneyType_6");
        int chargeCount = 0;
        BigDecimal chargeMoney = new BigDecimal(0);
        if (obj4 != null) {
            chargeCount += Integer.parseInt(detailMap.get("countType_4").toString());
            chargeMoney = chargeMoney.add(new BigDecimal(obj4.toString()));
        }
        if (obj5 != null) {
            chargeCount += Integer.parseInt(detailMap.get("countType_5").toString());
            chargeMoney = chargeMoney.add(new BigDecimal(obj5.toString()));
        }
        if (obj6 != null) {
            chargeCount += Integer.parseInt(detailMap.get("countType_6").toString());
            chargeMoney = chargeMoney.add(new BigDecimal(obj6.toString()));
        }
        
        if (chargeCount == 0) {
            map.put("cardSalesAmount", 0);
        }
        else {
            map.put("cardSalesAmount", chargeMoney + "元(" + chargeCount + "单)");
        }
		
		dayBookInfos = orderInfoMapper.selectDayBookInfoList(queryParams);
		page.setResults(dayBookInfos);
		page.setTotalRecord(countBook.getCount());
		map.put("pageInfo", page);
//		DayBookStatDto statDto = orderInfoMapper.selectDayBookStat(queryParams);
//		map.put("statInfo", statDto);
		return map;
	}
	
	/**
	 * 查询订单及明细
	* @author 王大爷
	* @date 2015年12月1日 下午7:48:17
	* @param orderId 订单标识
	* @return BaseDto
	 */
    public BaseDto selectOrderByUpdate(Integer orderId) {
        OrderInfoBaseDto orderInfoBaseDto = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        orderInfoBaseDto = processOrderInfoBaseDto(orderInfoBaseDto);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderInfoBaseDto);
    }

    /**
     * 组装OrderProjectPageDto
    * @author 王大爷
    * @date 2015年11月30日 下午4:32:39
    * @param orderInfoBaseDto orderInfoBaseDto
    * @return List<OrderProjectPageDto>
     */
    private OrderInfoBaseDto processOrderInfoBaseDto(OrderInfoBaseDto orderInfoBaseDto) {
        List<OrderDetailDto> orderDetailList = orderInfoBaseDto.getOrderDetailList();
        for (OrderDetailDto orderDetailDto : orderDetailList) {
            if (orderDetailDto.getOffType() == 1) {
                String comboName = orderDetailMapper.selectComboNameByDetailId(orderDetailDto.getDetailId());
                orderDetailDto.setPrivilegeInfo("套餐：" + comboName);
            }
            else if (orderDetailDto.getOffType() == 2) {
                Map<String, Object> map = orderDetailMapper.selectCouponNameByDetailId(orderDetailDto.getDetailId());
                orderDetailDto.setPrivilegeInfo("优惠券：" + map.get("couponName").toString());
            }
            else if (orderDetailDto.getOffType() == 3) {
                orderDetailDto.setPrivilegeInfo("礼金：");
            }
            else {
                orderDetailDto.setPrivilegeInfo("未使用优惠");
            }
            orderDetailDto.setPrivilegeMoney(orderDetailDto.getGiftAmount());
            
            if (orderDetailDto.getOrderType() == 1) {
                List<OrderDetailStepDto> stepList = orderDetailDto.getStepList();
                for (OrderDetailStepDto orderDetailStepDto : stepList) {
                    EmployeeCommission employeeCommission 
                                            = employeeCommissionMapper.selectByEmployeeIdShift(orderDetailStepDto.getShiftMahjongStepId());
                    if (employeeCommission == null) {
                        orderDetailStepDto.setCommissionCalculate(new BigDecimal(0));
                        orderDetailStepDto.setCommissionAmount(new BigDecimal(0));
                        orderDetailStepDto.setCommissionId(null);
                    }
                    else {
                        orderDetailStepDto.setCommissionCalculate(employeeCommission.getCommissionCalculate());
                        orderDetailStepDto.setCommissionAmount(employeeCommission.getCommissionAmount());
                        orderDetailStepDto.setCommissionId(employeeCommission.getCommissionId());
                    }
                }
            }
            else {
                
                List<EmployeeCommission> list 
                           = employeeCommissionMapper.selectByDetailId(orderDetailDto.getDetailId());
                if (list.size() > 0) {
                    EmployeeCommission employeeCommission = list.get(0);
                    List<OrderDetailStepDto> stepList = new ArrayList<OrderDetailStepDto>();
                    
                    EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeCommission.getEmployeeId());
                    
                    OrderDetailStepDto orderDetailStepDto = new OrderDetailStepDto();
                    orderDetailStepDto.setEmployeeInfo(employeeInfo);
                    orderDetailStepDto.setCommissionCalculate(employeeCommission.getCommissionCalculate());
                    orderDetailStepDto.setCommissionAmount(employeeCommission.getCommissionAmount());
                    orderDetailStepDto.setCommissionId(employeeCommission.getCommissionId());
                    orderDetailStepDto.setIsAppoint(2);
                    orderDetailStepDto.setIsAssign(2);
                    orderDetailStepDto.setShiftMahjongName("");
                    orderDetailStepDto.setProjectStepName("");
                    stepList.add(orderDetailStepDto);
                    orderDetailDto.setStepList(stepList);
                }
            }
            
            //解决签单备注为null
            if (StringUtil.isEmpty(orderDetailDto.getOrderRemark())) {
                orderDetailDto.setOrderRemark("");
            }
        }
        return orderInfoBaseDto;
    }
    
    /**
     * 保存修改订单
    * @author 王大爷
    * @date 2015年12月3日 下午7:43:58
    * @param orderInfo 参数
    * @param commissionArrayStr 修改的提成业绩
    * @return BaseDto
     */
    @Transactional
    public BaseDto orderByUpdate(OrderInfo orderInfo, String commissionArrayStr) {
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        
        JSONArray commissionArray = JSONArray.fromObject(commissionArrayStr);
        for (int i = 0; i < commissionArray.size(); i++) {
            JSONObject commissionObject = commissionArray.getJSONObject(i);
            Integer commissionId = commissionObject.getInt("commissionId");
            String commissionAmountStr = commissionObject.getString("commissionAmount");
            String commissionCalculateStr = commissionObject.getString("commissionCalculate");
            
            EmployeeCommission employeeCommission = employeeCommissionMapper.selectByPrimaryKey(commissionId);
            
            String month = getMonthDate(employeeCommission.getChargeTime());
            
            //修改提成
            EmployeeCommission record = new EmployeeCommission();
            record.setCommissionId(commissionId);
            record.setCommissionAmount(new BigDecimal(commissionAmountStr));
            record.setCommissionCalculate(new BigDecimal(commissionCalculateStr));
            employeeCommissionMapper.updateByPrimaryKey(record);
            
            //修改业绩
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("employeeId", employeeCommission.getEmployeeId());
            map.put("month", month);
            EmployeeObjective objectiveDto = employeeObjectiveMapper.selectObjectiveByMonth(map);
            
            Integer type = employeeCommission.getOrderType();
            
            EmployeeObjective employeeObjectiveRecord = new EmployeeObjective();
            
            employeeObjectiveRecord.setObjectiveId(objectiveDto.getObjectiveId());
            
            //计算修改后的值差距
            BigDecimal commissionCalculate = employeeCommission.getCommissionCalculate().subtract(new BigDecimal(commissionCalculateStr));
            
            if (type == 1) {
                BigDecimal actualTotalProjectSales =objectiveDto.getActualTotalProjectSales().add(commissionCalculate);
                
                employeeObjectiveRecord.setActualTotalProjectSales(actualTotalProjectSales);
                
                ShiftMahjongProjectStepDto isAssignObj = shiftMahjongProjectStepMapper.selectByPrimaryKey(employeeCommission.getShiftMahjongStepId());
                if (isAssignObj.getIsAssign() == 1) {
                    BigDecimal actualAssignProjectSales = objectiveDto.getActualAssignProjectSales().add(commissionCalculate);
                    employeeObjectiveRecord.setActualAssignProjectSales(actualAssignProjectSales);
                }
            }
            else if (type == 2) {
                BigDecimal actualGoodsSales = objectiveDto.getActualGoodsSales().add(commissionCalculate);
                
                employeeObjectiveRecord.setActualGoodsSales(actualGoodsSales);
            }
            else if (type == 3) {
                BigDecimal actualComboSales = objectiveDto.getActualComboSales().add(commissionCalculate);
                employeeObjectiveRecord.setActualComboSales(actualComboSales);
            }
            else {
                BigDecimal actualChargeSales = objectiveDto.getActualChargeSales().add(commissionCalculate);
                
                employeeObjectiveRecord.setActualChargeSales(actualChargeSales);
            }
            
            employeeObjectiveMapper.updateByPrimaryKeySelective(employeeObjectiveRecord);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 整笔订单删除
    * @author 王大爷
    * @date 2015年12月2日 上午10:42:27
    * @param orderId 订单标识
    * @param storeId 门店标识
    * @return BaseDto
     */
    @Transactional
    public BaseDto elementDeleteOrderId(Integer orderId, Integer storeId) {
        OrderInfoBaseDto orderInfoBaseDto = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        List<OrderDetailDto> orderDetailList = orderInfoBaseDto.getOrderDetailList();
        Integer memberId = orderInfoBaseDto.getMemberId();
        Integer type = 0;
        for (int i = 0; i < orderDetailList.size(); i++) {
            Integer detailId = orderDetailList.get(i).getDetailId();
            type = elementDeleteDetailId(orderInfoBaseDto, detailId, memberId);
        }
        if (type == 1) {
            
            OrderInfo record = new  OrderInfo();
            record.setOrderId(orderId);
            record.setIsDeleted(1);
            orderInfoMapper.updateByPrimaryKey(record);
            
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
            List<MemberLevel> memberLevelList = memberLevelMapper.selectByStoreId(storeId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("memberId", memberId);
            map.put("levelId", memberInfo.getLevelId());
            map.put("memberLevelList", memberLevelList);
            return new BaseDto(888, map);
        }
        else if (type == 0){
            if (memberId != null) {
                MemberDto memberDto = memberInfoMapper.selectByMemberResultMap(memberId);
                
                List<MoneyFlow> moneyFlowList = moneyFlowMapper.selectByOrderId(orderId);
                for (MoneyFlow moneyFlow : moneyFlowList) {
                    
                    MoneyFlow record = new MoneyFlow();
                    record.setFlowId(moneyFlow.getFlowId());
                    record.setIsDeleted(1);
                    moneyFlowMapper.updateByPrimaryKey(record);
                }
                
                MemberAccount memberAccount = new MemberAccount();
                memberAccount.setAccountId(memberId);
                if (orderInfoBaseDto.getCardAmount().compareTo(new BigDecimal(0)) == 1) {
                    memberAccount.setBalanceAmount(memberDto.getMemberAccount().getBalanceAmount().add(orderInfoBaseDto.getCardAmount()));
                }
                IntegralFlow integralFlow = integralFlowMapper.selectByOrderId(orderId);
                if (integralFlow != null) {
                    memberAccount.setTotalIntegral(memberDto.getMemberAccount().getTotalIntegral() - integralFlow.getFlowAmount());
                    Integer valueInteger = memberDto.getMemberAccount().getBalanceIntegral() - integralFlow.getFlowAmount();
                    if (valueInteger >= 0) {
                        memberAccount.setBalanceIntegral(valueInteger); 
                    }
                    else {
                        memberAccount.setBalanceIntegral(0); 
                    }
                    memberAccountMapper.updateByPrimaryKey(memberAccount);
                    IntegralFlow record = new IntegralFlow();
                    record.setFlowId(integralFlow.getFlowId());
                    record.setIsDeleted(1);
                    integralFlowMapper.updateByPrimaryKey(record);
                }
            }
        }
        
        if (memberId  != null) {
           //更新缓存中的会员数据
            memberInfoService.wipeCache(memberId);
        }
        
        OrderInfo record = new  OrderInfo();
        record.setOrderId(orderId);
        record.setIsDeleted(1);
        orderInfoMapper.updateByPrimaryKey(record);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    
    /**
     * 删除明细
    * @author 王大爷
    * @date 2015年11月11日 上午11:37:54
    * @param orderInfoBaseDto 订单基础
    * @param detailId 明细信息
    * @param memberId 会员标识
    * @return Integer
     */
    @Transactional
    public Integer elementDeleteDetailId(OrderInfoBaseDto orderInfoBaseDto, Integer detailId, Integer memberId) {
        Integer type = 0;
        
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        
        Integer orderId = orderInfoBaseDto.getOrderId();
        
        MemberDto memberDto = new MemberDto();
        
        if (memberId != null){
            memberDto = memberInfoMapper.selectByMemberResultMap(memberId);
        }
        
        /*Integer storeId = orderInfoBaseDto.getStoreId();*/
        //删除员工业绩，提成
        deleteObjective(detailId, orderDetail.getOrderType());
        
        //项目
        if (orderDetail.getOrderType() == 1) {
            List<OrderDetailStepDto> orderDetailStepDtos = shiftMahjongProjectStepMapper.selectOrderStepListByDetailId(orderDetail.getDetailId());
            for (int i = 0; i < orderDetailStepDtos.size(); i++) {
                Integer shiftMahjongStepId = orderDetailStepDtos.get(i).getShiftMahjongStepId();
                deleteShiftStep(shiftMahjongStepId);
            }
        }
        //商品
        else if (orderDetail.getOrderType() == 2){
            //增加删除商品库存
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGoodsId(orderDetail.getProjectId());
            goodsInfo.setGoodsStock(orderDetail.getProjectCount());
            goodsInfoMapper.updateGoodsStock(goodsInfo);
        }
        else if (orderDetail.getOrderType() == 3) {
            //清除员工套餐
            MemberComboDto dto = memberComboRecordMapper.selectComboListByDetailId(detailId);
            if (dto != null) {
                MemberComboRecord record = new MemberComboRecord();
                record.setRecordId(dto.getRecordId());
                record.setIsDeleted(1);
                memberComboRecordMapper.updateByPrimaryKey(record);
                
                List<MemberComboProject> plist = dto.getProjectList();
                
                for (MemberComboProject memberComboProject : plist) {
                    MemberComboProject precord = new MemberComboProject();
                    /*precord.setDetailId(memberComboProject.getDetailId());*/
                    precord.setComboId(memberComboProject.getComboId());
                    precord.setRecordId(memberComboProject.getRecordId());
                    precord.setIsDeleted(1);
                    memberComboProjectMapper.updateByPrimaryKey(precord);
                }
            }
        }
        //开卡
        else if (orderDetail.getOrderType() == 4) {
            changeAccount(memberDto, orderId, detailId);
            type = 1;
        }
        //充值
        else if (orderDetail.getOrderType() == 5) {
            changeAccount(memberDto, orderId, detailId);
            type = 2;
        }
        //升级
        else if (orderDetail.getOrderType() == 6) {
            changeAccount(memberDto, orderId, detailId);
            type = 1;
        }
        
        //回滚抵扣
        if (orderDetail.getOffType() == 1) {
            //回滚会员对应套餐
            Integer detailComboId = orderDetail.getComboId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("detailId", detailComboId);
            map.put("comboCount", 1);
            map.put("updateTime", DateUtil.getCurTime());
            memberComboProjectMapper.updateAddComboNum(map);
        }
        else if (orderDetail.getOffType() == 2){
            //回滚优惠卷
            MemberCoupon record = new MemberCoupon();
            record.setRelevanceId(orderDetail.getCouponId());
            record.setIsUsed(0);
            memberCouponMapper.updateByPrimaryKey(record);
        }
        else if (orderDetail.getOffType() == 3){
            //回滚礼金(出现两个记录的话 ，我会骂街)
            //删除流水
            GiftmoneyFlow giftmoneyFlow = giftmoneyFlowMapper.selectByDetailId(detailId);
            GiftmoneyFlow record = new GiftmoneyFlow();
            record.setFlowId(giftmoneyFlow.getFlowId());
            record.setIsDeleted(1);
            giftmoneyFlowMapper.updateByPrimaryKeySelective(record);
            
            //回滚礼金明细
            String residueMoneyInfo = giftmoneyFlow.getResidueMoneyInfo();
            
            //礼金储值余额
            BigDecimal balanceGiftmoneyAmount = new BigDecimal(0);
            
            //礼金过期金额
            BigDecimal pastdataMoney = new BigDecimal(0);
            
            //礼金使用金额
            BigDecimal useMoney = new BigDecimal(0);
            
            useMoney = giftmoneyFlow.getFlowAmount();
            
            if (residueMoneyInfo != "" && residueMoneyInfo != null) {
                String[] residueMoneys = residueMoneyInfo.split(",");
                List<Integer> detailList = new ArrayList<Integer>(residueMoneys.length);
                List<BigDecimal> operationValueList = new ArrayList<BigDecimal>(residueMoneys.length);
                for (int i = 0; i < residueMoneys.length; i++) {
                    String residueMoneyStr = residueMoneys[i];
                    detailList.add(Integer.parseInt(residueMoneyStr.split(":")[0]));
                    operationValueList.add(new BigDecimal(residueMoneyStr.split(":")[1]));
                }
                
                Integer index = 0;
                for (Integer detail : detailList) {
                    GiftmoneyDetail giftmoneyDetail = giftmoneyDetailMapper.selectByPrimaryKey(detail);
                    
                    Date endDate = DateUtil.tranStrToDateDD(giftmoneyDetail.getEndDate());
                    
                    BigDecimal operationValue = operationValueList.get(index);
                    
                    
                    
                    if (!"永久".equals(giftmoneyDetail.getEndDate())) {
                        if (giftmoneyDetail.getIsDeleted() != 1) {
                            if (endDate.before(new Date())) {
                                pastdataMoney = pastdataMoney.add(operationValue);
                            }
                            else {
                                balanceGiftmoneyAmount = balanceGiftmoneyAmount.add(operationValue);
                            }
                        }
                    }
                    else {
                        balanceGiftmoneyAmount = balanceGiftmoneyAmount.add(operationValue);
                    }
                    GiftmoneyDetail grecord = new GiftmoneyDetail();
                    grecord.setDetail(detail);
                    grecord.setResidueNowMoney(giftmoneyDetail.getResidueNowMoney().add(operationValue));
                    giftmoneyDetailMapper.updateByPrimaryKeySelective(grecord);
                    
                    index++ ;
                }
                MemberAccount objAccount = memberDto.getMemberAccount();
                MemberAccount memberAccount = new MemberAccount();
                memberAccount.setAccountId(memberDto.getMemberId());
                memberAccount.setBalanceGiftmoneyAmount(objAccount.getBalanceGiftmoneyAmount().add(balanceGiftmoneyAmount));
                memberAccount.setPastdataMoney(objAccount.getPastdataMoney().add(pastdataMoney));
                memberAccount.setUseMoney(objAccount.getUseMoney().subtract(useMoney));
                memberAccountMapper.updateByPrimaryKey(memberAccount);
            }
            
        }
        
        OrderDetail record = new OrderDetail();
        record.setDetailId(detailId);
        record.setIsDeleted(1);
        orderDetailMapper.updateByPrimaryKey(record);
        
        return type;
    }
    
    /**
     * 修改账户信息(开卡充值升级)
    * @author 王大爷
    * @date 2015年12月14日 下午7:47:58
    * @param memberDto 会员Dto
    * @param orderId 订单标识
    * @param detailId 明细标识
     */
    public void changeAccount(MemberDto memberDto, Integer orderId, Integer detailId) {
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAccountId(memberDto.getMemberId());
        
        List<MoneyFlow> moneyFlowList = moneyFlowMapper.selectByOrderId(orderId);
        //充值金额
        BigDecimal balanceAmount = new BigDecimal(0);
        for (MoneyFlow moneyFlow : moneyFlowList) {
            balanceAmount = balanceAmount.add(moneyFlow.getFlowAmount());
            
            MoneyFlow record = new MoneyFlow();
            record.setFlowId(moneyFlow.getFlowId());
            record.setIsDeleted(1);
            moneyFlowMapper.updateByPrimaryKey(record);
        }
        //修改储值总额
        memberAccount.setTotalAmount(memberDto.getMemberAccount().getTotalAmount().subtract(balanceAmount));
        
        //储值余额
        if (memberDto.getMemberAccount().getBalanceAmount().compareTo(balanceAmount) == -1) {
            memberAccount.setBalanceAmount(new BigDecimal(0));
        }
        else {
            memberAccount.setBalanceAmount(memberDto.getMemberAccount().getBalanceAmount().subtract(balanceAmount));
        }
        
        List<GiftmoneyDetail> giftmoneyDetailList = giftmoneyDetailMapper.selectByDetailId(detailId);
        
        BigDecimal isGiftmoney = new BigDecimal(0);
        //删除礼金明细表
        for (GiftmoneyDetail giftmoneyDetail : giftmoneyDetailList) {
            if (giftmoneyDetail.getIsPresent() == 1) {
                isGiftmoney = isGiftmoney.add(giftmoneyDetail.getResidueNowMoney());
            }
            //删除礼金明细
            GiftmoneyDetail record = new GiftmoneyDetail();
            record.setDetail(giftmoneyDetail.getDetail());
            record.setIsDeleted(1);
            giftmoneyDetailMapper.updateByPrimaryKeySelective(record);
        }
        
        //回滚礼金(出现两个记录的话 ，我会骂街)
        //删除流水
        GiftmoneyFlow giftmoneyFlow = giftmoneyFlowMapper.selectByDetailId(detailId);
        GiftmoneyFlow record = new GiftmoneyFlow();
        record.setFlowId(giftmoneyFlow.getFlowId());
        record.setIsDeleted(1);
        giftmoneyFlowMapper.updateByPrimaryKeySelective(record);
        
        memberAccount.setTotalGiftmoneyAmount(memberDto.getMemberAccount().getTotalGiftmoneyAmount().subtract(isGiftmoney));
        
        memberAccount.setBalanceGiftmoneyAmount(memberDto.getMemberAccount().getBalanceGiftmoneyAmount().subtract(isGiftmoney));
        
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        
        //更新缓存中的会员数据
        memberInfoService.wipeCache(memberDto.getMemberId());
    }
    
    /**
     * 修改业绩提成
    * @author 王大爷
    * @date 2015年12月2日 下午2:43:48
    * @param detailId 明细标识
    * @param type 类型
     */
    public void deleteObjective(Integer detailId, Integer type) {
      //根据明细标识查询员工提成
        List<EmployeeCommission> employeeCommissionList = employeeCommissionMapper.selectByDetailId(detailId);
        
        for (EmployeeCommission employeeCommission : employeeCommissionList) {
            //打删除标识
            EmployeeCommission record = new EmployeeCommission();
            record.setCommissionId(employeeCommission.getCommissionId());
            record.setIsDeleted(1);
            employeeCommissionMapper.updateByPrimaryKey(record);
            
            String objectiveMonth = getMonthDate(employeeCommission.getChargeTime());
            
            BigDecimal commissionCalculate = employeeCommission.getCommissionCalculate();
            
            if (commissionCalculate != null) {
                //减实际业绩
                EmployeeObjective employeeObjective = new EmployeeObjective();
                employeeObjective.setEmployeeId(employeeCommission.getEmployeeId());
                employeeObjective.setObjectiveMonth(objectiveMonth);
                if (type == 1) {
                    employeeObjective.setActualTotalProjectSales(commissionCalculate);
                    if (employeeCommission.getShiftMahjongStepId() != null) {
                        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto 
                            = shiftMahjongProjectStepMapper.selectByPrimaryKey(employeeCommission.getShiftMahjongStepId());
                        if (shiftMahjongProjectStepDto.getIsAssign() == 1) {
                            employeeObjective.setActualAssignProjectSales(commissionCalculate);
                        }
                    }
                    
                }
                else if (type == 2) {
                    employeeObjective.setActualGoodsSales(commissionCalculate);
                }
                else if (type == 3){
                    employeeObjective.setActualComboSales(commissionCalculate);
                }
                else {
                    employeeObjective.setActualChargeSales(commissionCalculate);
                }

                employeeObjectiveMapper.updateDecreaseActual(employeeObjective);
            }
        }
    }
    
    
    /**
     * 删除轮牌项目步骤
    * @author 王大爷
    * @date 2015年12月2日 上午11:04:10
    * @param shiftMahjongStepId 轮牌项目步骤标识
     */
    @Transactional
    public void deleteShiftStep(Integer shiftMahjongStepId) {
        ShiftMahjongProjectStep record = new ShiftMahjongProjectStep();
        record.setShiftMahjongStepId(shiftMahjongStepId);
        record.setIsDeleted(1);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(record);
    }
    
    /**
     * 根据传人参数时间返回对应年月  yyyy-mm
    * @author 王大爷
    * @date 2015年12月12日 下午4:43:00
    * @param parameterDate 参数时间
    * @return String
     */
    public String getMonthDate(String parameterDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(parameterDate);
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        String month = dateFormat.format(date);
        
        return month;
    }
    
    /**
     * 修改会员等级
    * @author 王大爷
    * @date 2015年12月14日 下午9:54:57
    * @param memberId 会员标识
    * @param levelId 级别标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    public BaseDto changeLevelId(Integer memberId, Integer levelId, Integer lastOperatorId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setLevelId(levelId);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        memberInfo.setMemberId(memberId);
        memberInfo.setLastOperatorId(lastOperatorId);
        
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
