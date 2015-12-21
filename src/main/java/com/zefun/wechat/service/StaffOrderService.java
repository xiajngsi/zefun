package com.zefun.wechat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
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
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ShiftMahjong;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.service.OrderInfoService;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.ShiftMahjongService;

/**
 * 移动端员工的订单服务逻辑类
* @author 张进军
* @date Oct 13, 2015 9:29:53 PM 
*/
@Service
public class StaffOrderService {
    
    /**  logger*/
    private static final Logger LOGGERS = Logger.getLogger(StaffOrderService.class);
    
    /** 订单基础数据服务对象 */
    @Autowired
    private OrderInfoService orderInfoService;
    
    /** 订单数据操作对象 */
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    /** 订单明细*/
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /** 轮牌*/
    @Autowired private ShiftMahjongMapper shiftMahjongMapper;
    /** 轮牌步骤*/
    @Autowired
    private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
    /** 轮牌员工*/
    @Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
    /** 员工*/
    @Autowired private StaffService staffService;
    /** redis*/
    @Autowired private RedisService redisService;
    /** 会员*/
    @Autowired private MemberInfoMapper memberInfoMapper;
    
    /** 轮牌*/
    @Autowired private ShiftMahjongService shiftMahjongService;
    
    /** rabbitService*/
    @Autowired
    private RabbitService rabbitService;
    /** 项目步骤*/
    @Autowired private ProjectStepMapper projectStepMapper;
    /** 项目*/
    @Autowired private ProjectInfoMapper projectInfoMapper;
    /** 员工*/
    @Autowired private EmployeeInfoMapper employeeInfoMapper;
    /**门店信息操作对象*/
    @Autowired private StoreInfoMapper storeInfoMapper;
    
    
    
    /**
     * 查询店内所有订单
    * @author 张进军
    * @date Oct 13, 2015 9:43:06 PM
    * @param storeId 门店标识
    * @param type 订单状态类型（1、进行中，2、已完成，3、全部）
    * @return   全部订单页面
     */
    public ModelAndView allOrderView(int storeId, Integer type){
        ModelAndView mav = new ModelAndView(View.StaffPage.ORDER_LIST);
        List<OrderInfoBaseDto> orderList = orderInfoService.getOrderInfoBaseDtoByStoreId(storeId, type);
        mav.addObject("orderList", orderList);
        mav.addObject("orderListStr", JSONArray.fromObject(orderList).toString());
        mav.addObject("type", type);
        
        mav.addObject("orderType", 1);
        
        mav.addObject("storeId", storeId);
        Map<String, Object> map = orderInfoMapper.selectOrderStatisticsByStoreId(storeId);
        mav.addObject("statisticsCount", map.get("count"));
        mav.addObject("statisticsMoney", map.get("money"));
        return mav;
    }
    
    /**
     * 我的订单
    * @author 王大爷
    * @date 2015年10月18日 下午2:18:30
    * @param employeeId 员工标识
    * @param type 订单状态类型（1、进行中，2、已完成，3、全部）
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView employeeOrderView(Integer employeeId, Integer type, Integer storeId){
        
        List<OrderInfoBaseDto> orderList = orderInfoService.getOrderInfoBaseDtoByEmployeeId(employeeId, type);
        Map<String, Object> map = orderInfoMapper.selectOrderStatisticsByEmployeeId(employeeId);
                
        ModelAndView mav = new ModelAndView(View.StaffPage.ORDER_LIST);
        mav.addObject("orderList", orderList);
        mav.addObject("type", type);
        
        mav.addObject("orderType", 2);
        
        mav.addObject("storeId", storeId);
        mav.addObject("orderListStr", JSONArray.fromObject(orderList).toString());
        mav.addObject("statisticsCount", map.get("count"));
        mav.addObject("statisticsMoney", map.get("money"));
        return mav;
    }
    
    /**
     * 订单明细查询
    * @author 王大爷
    * @date 2015年11月10日 上午10:34:21
    * @param orderId 订单标识
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView selectOrderDetail(Integer orderId, Integer storeId) {
        OrderInfoBaseDto orderInfo = orderInfoService.getOrderInfoBaseDto(orderId);  
        ModelAndView mav = new ModelAndView(View.StaffPage.ORDER_DETAIL);
        mav.addObject("orderInfo", orderInfo);
        mav.addObject("orderId", orderInfo.getOrderId());
        mav.addObject("storeId", storeId);
        mav.addObject("orderDetailListStr", JSONArray.fromObject(orderInfo.getOrderDetailList()).toString());
        mav.addObject("memberInfoStr", JSONArray.fromObject(orderInfo.getMemberInfo()).toString());
        return mav;
    }
    
    /**
     * 挂起
    * @author 王大爷
    * @date 2015年12月10日 下午7:33:47
    * @param detailId 明细标识
    * @param storeId 门店标识
    * @param shiftMahjongStepId 步骤标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    @Transactional
    public BaseDto hangUpOrder(Integer detailId, Integer storeId, Integer shiftMahjongStepId, Integer lastOperatorId) {
        //修改订单状态
        OrderDetail record = new OrderDetail();
        record.setDetailId(detailId);
        record.setOrderStatus(4);
        record.setLastOperatorId(lastOperatorId);
        record.setUpdateTime(DateUtil.getCurDateTrimMinutes());
        orderDetailMapper.updateByPrimaryKey(record);
        
        ShiftMahjongEmployee employeeobj = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
        
        ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
        shiftMahjongEmployee.setShiftMahjongEmployeeId(employeeobj.getShiftMahjongEmployeeId());
        shiftMahjongEmployee.setState(1);
        //修改员工轮牌状态
        shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
        
        //自动获取项目
        staffService.selfMotionExecute(shiftMahjongEmployee.getShiftMahjongEmployeeId(), storeId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 取消挂起
    * @author 王大爷
    * @date 2015年12月10日 下午7:33:47
    * @param detailId 明细标识
    * @param storeId 门店标识
    * @param shiftMahjongStepId 步骤标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    @Transactional
    public BaseDto cancelHangUpOrder(Integer detailId, Integer storeId, Integer shiftMahjongStepId, Integer lastOperatorId) {
        
        ShiftMahjongEmployee employeeobj = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
        
        if (employeeobj.getState() != 1) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您的当前状态不为空闲，无法取消挂起。");
        }
        
        //修改订单状态
        OrderDetail record = new OrderDetail();
        record.setDetailId(detailId);
        record.setOrderStatus(2);
        record.setLastOperatorId(lastOperatorId);
        record.setUpdateTime(DateUtil.getCurDateTrimMinutes());
        orderDetailMapper.updateByPrimaryKey(record);
        
        ShiftMahjongProjectStepDto dto = shiftMahjongProjectStepMapper.selectByPrimaryKey(shiftMahjongStepId);
        
        ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
        shiftMahjongEmployee.setShiftMahjongEmployeeId(employeeobj.getShiftMahjongEmployeeId());
        if (dto.getIsAssign() == 1) {
            shiftMahjongEmployee.setState(4);
        }
        else {
            shiftMahjongEmployee.setState(0);
        }
        //修改员工轮牌状态
        shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 删除明细
    * @author 王大爷
    * @date 2015年11月10日 下午6:48:58
    * @param detailId 明细标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return ModelAndView
     */
    @Transactional
    public BaseDto deleteOrderDetail(Integer detailId, Integer storeId, Integer lastOperatorId) {
        OrderInfo orderInfo = orderInfoMapper.selectByDetailId(detailId);
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        //redis操作用（不回滚）
        List<Integer> detailIdList = new ArrayList<Integer>();
        Integer selfMotionId = deletes(orderDetail, storeId, lastOperatorId);

        detailIdList.add(detailId);

        operationRedisZrem(storeId, detailIdList);
        
        staffService.selfMotionExecute(selfMotionId, storeId);
        
        List<OrderDetail> list = orderDetailMapper.selectOrderDetail(orderInfo.getOrderId());
        
        if (list.size() > 0) {
            Integer a = 0;
            for (OrderDetail orderDetail1 : list) {
                if (orderDetail1.getOrderStatus() != 3) {
                    a = 1;
                }
            }
          
            OrderInfo record = new  OrderInfo();
            record.setOrderId(orderInfo.getOrderId());
            if (a == 0) {
                //修改订单状态为待结账
                record.setOrderStatus(2);
            }
            else {
                //修改订单状态为进行
                record.setOrderStatus(1);
            }
            orderInfoMapper.updateByPrimaryKey(record);
            orderInfoMapper.updateTotalPrice(orderInfo.getOrderId());
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, 1);
        }
        else {
            OrderInfo record = new  OrderInfo();
            record.setOrderId(orderInfo.getOrderId());
            record.setIsDeleted(1);
            orderInfoMapper.updateByPrimaryKey(record);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, 2);
        }
    }
    
    /**
     * 删除订单
    * @author 王大爷
    * @date 2015年11月11日 上午11:48:33
    * @param orderId 订单标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return ModelAndView
     */
    @Transactional
    public BaseDto deleteOrderInfo(Integer orderId, Integer storeId, Integer lastOperatorId) {
        OrderInfoBaseDto orderInfoBaseDto = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        List<OrderDetailDto> orderDetailList = orderInfoBaseDto.getOrderDetailList();
        //redis操作用（不回滚）
        List<Integer> detailIdList = new ArrayList<Integer>();
        //处理删除订单中多个相同明细时，第一个是指定，而会自动去那需要删除的明细，所以需要将项目全删除后，再执行自动去项目代码
        List<Integer> selfMotionIdList = new ArrayList<Integer>();
        
        for (int i = 0; i < orderDetailList.size(); i++) {
            OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderDetailList.get(i).getDetailId());
            Integer selfMotionId = deletes(orderDetail, storeId, lastOperatorId);
            detailIdList.add(orderDetailList.get(i).getDetailId());
            if (selfMotionId != null) {
                selfMotionIdList.add(selfMotionId);
            }
        }
        OrderInfo record = new  OrderInfo();
        record.setOrderId(orderId);
        record.setIsDeleted(1);
        orderInfoMapper.updateByPrimaryKey(record);
        
        operationRedisZrem(storeId, detailIdList);
        
        for (Integer selfMotionId : selfMotionIdList) {
            staffService.selfMotionExecute(selfMotionId, storeId);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 删除明细
    * @author 王大爷
    * @date 2015年11月11日 上午11:37:54
    * @param orderDetail 明细信息
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return List<Integer>
     */
    public Integer deletes(OrderDetail orderDetail, Integer storeId, Integer lastOperatorId) {
        Integer selfMotionId = null;
        if (orderDetail.getOrderType() != 1) {
            OrderDetail record = new OrderDetail();
            record.setDetailId(orderDetail.getDetailId());
            record.setIsDeleted(1);
            orderDetailMapper.updateByPrimaryKey(record);
        }
        else {
            //项目
            List<ShiftMahjongProjectStep> orderDetailStepList = shiftMahjongProjectStepMapper.selectStepByDetailId(orderDetail.getDetailId());
            for (ShiftMahjongProjectStep shiftMahjongProjectStep : orderDetailStepList) {
                //轮牌步骤标识
                Integer shiftMahjongStepId = shiftMahjongProjectStep.getShiftMahjongStepId();
                //获取轮牌员工
                ShiftMahjongEmployee shiftMahjongEmployee 
                       = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
                
                //服务中步骤
                if (shiftMahjongProjectStep.getIsOver() == 1) {
                    if (orderDetail.getOrderStatus() != 4) {
                        
                        Integer shiftMahjongEmployeeId = shiftMahjongEmployee.getShiftMahjongEmployeeId();
                        ShiftMahjongEmployee record = new ShiftMahjongEmployee();
                        record.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
                        record.setState(1);
                        //修改员工轮牌状态
                        shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(record);
                        //自动获取项目
                        selfMotionId = shiftMahjongEmployeeId;
                    }
                    //发送服务移交模版
                    senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongStepId, 
                            storeId, 5, lastOperatorId);
                    
                    /*
                    staffService.selfMotionExecute(shiftMahjongEmployeeId, storeId);*/
                    
                }
                //等待中状态
                else if (shiftMahjongProjectStep.getIsOver() == 2 && shiftMahjongProjectStep.getEmployeeId() != null) {
                    Integer shiftMahjongEmployeeId = shiftMahjongEmployee.getShiftMahjongEmployeeId();
                                        
                    shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeId);
                    //发送消息
                    senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongStepId, 
                            storeId, 5, lastOperatorId);
                }
                //已完成状态
                else if (shiftMahjongProjectStep.getIsOver() == 3 && shiftMahjongProjectStep.getEmployeeId() != null) {
                    //发送消息
                    senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongStepId, 
                            storeId, 9, lastOperatorId);
                }
                
                ShiftMahjongProjectStep record = new ShiftMahjongProjectStep();
                record.setShiftMahjongStepId(shiftMahjongStepId);
                record.setIsDeleted(1);
                shiftMahjongProjectStepMapper.updateByPrimaryKey(record);
            }
            
            OrderDetail record = new OrderDetail();
            record.setDetailId(orderDetail.getDetailId());
            record.setIsDeleted(1);
            orderDetailMapper.updateByPrimaryKey(record);
            
        }
        return selfMotionId;
    }
    
    /**
     * 服务移交
    * @author 王大爷
    * @date 2015年10月20日 下午2:58:46
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param type 交接类型
    * @param detailId 订单明细标识
    * @param shiftMahjongId 轮牌标识
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView serverAssociate(Integer shiftMahjongStepId, Integer type, Integer detailId, Integer shiftMahjongId, Integer storeId){
        ModelAndView mav = new ModelAndView();
        ShiftMahjongProjectStepDto obj = shiftMahjongProjectStepMapper.selectByPrimaryKey(shiftMahjongStepId);
        Integer projectId = obj.getProjectStep().getProjectId();
        Integer num = obj.getProjectStep().getProjectStepOrder();
        
        ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByPrimaryKey(shiftMahjongId);
        mav.addObject("shiftMahjong", shiftMahjong);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("projectId", projectId);
        map.put("num", num);
        //查询出满足项目对应级别的员工
        List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftEmployeeList(map);
        
        //查询会员信息
        MemberBaseDto memberBaseDto = memberInfoMapper.selectByDetailId(detailId);
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        
        if (memberBaseDto == null) {
            memberBaseDto = new MemberBaseDto();
            memberBaseDto.setName("散客");
        }
        
        Integer shiftMahjongEmployeeId = null;
        //当存在指定员工时，查询指定员工轮牌
        if (obj.getEmployeeId() != null) {
            ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
            shiftMahjongEmployeeId = shiftMahjongEmployee.getShiftMahjongEmployeeId();
        }
        
        Integer isType = 0;
        
        int a = 0;
        
        if (obj.getIsAssign() == 1) {
            isType = 1;
            a = 1;
        }
        
        if (obj.getIsDesignate() == 1){
            isType = 2;
            a = 1;
        }

        if (type == 1 && a == 0) {
            isType = 3;
        }
        mav.addObject("isType", isType); 
        mav.addObject("shiftMahjongEmployeeId", shiftMahjongEmployeeId);
        mav.addObject("shiftMahjongEmployeeList", shiftMahjongEmployeeList);
        mav.addObject("shiftMahjongStepId", shiftMahjongStepId);
        mav.addObject("type", type);
        mav.addObject("detailId", detailId);
        mav.addObject("shiftMahjongId", shiftMahjongId);
        
        mav.addObject("memberBaseDto", memberBaseDto);
        
        mav.addObject("memberBaseDtoStr", JSONObject.fromObject(memberBaseDto).toString());
        
        mav.addObject("orderDetailDto", orderDetailDto);
        mav.setViewName(View.StaffPage.TURN_SHIFTMAHJONG_SERVE);

        return mav;
    }
    
    /**
     * 确定完成订单
    * @author 王大爷
    * @date 2015年11月14日 下午4:41:54
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param detailId 订单明细标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    public BaseDto finishOrderDetail(Integer shiftMahjongStepId, Integer detailId, Integer storeId, Integer lastOperatorId) {
        //修改最后步骤
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
        shiftMahjongProjectStep.setFinishTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setIsOver(3);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        //删除该订单下没有完成的步骤
        List<ShiftMahjongProjectStep> shiftMahjongProjectStepList = shiftMahjongProjectStepMapper.selectByIsNotOver(detailId);
        //删除的步骤中如过存在指定员工
        for (int i = 0; i < shiftMahjongProjectStepList.size(); i++) {
            if (shiftMahjongProjectStepList.get(i).getEmployeeId() != null) {
                ShiftMahjongEmployee shiftMahjongEmployee 
                         = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongProjectStepList.get(i).getShiftMahjongStepId());
                
                shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployee.getShiftMahjongEmployeeId());
                //发送消息
                senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongProjectStepList.get(i).getShiftMahjongStepId(), 
                        storeId, 5, lastOperatorId);
            }
            ShiftMahjongProjectStep record = new ShiftMahjongProjectStep();
            record.setShiftMahjongStepId(shiftMahjongProjectStepList.get(i).getShiftMahjongStepId());
            record.setIsDeleted(1);
            shiftMahjongProjectStepMapper.updateByPrimaryKey(record);
        }
        
        //修改服务时长
        orderDetailMapper.updateServiceLength(shiftMahjongStepId);
        
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
        
        OrderDetail obj  = orderDetailMapper.selectByPrimaryKey(detailId);
        
        //修改员工的轮牌状态
        staffService.updateShiftEmployeeState(shiftMahjongEmployee.getShiftMahjongEmployeeId(), detailId, storeId);
        
        //修改订单明细状态
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(detailId);
        orderDetail.setOrderStatus(3);
        orderDetailMapper.updateByPrimaryKey(orderDetail);
        
        //查询该明细对应的订单中存在的未完成的项目明细
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByNotOverOrderDetail(detailId);
        if (orderDetailList.size() == 0) {
            //修改订单信息状态
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(obj.getOrderId());
            orderInfo.setOrderStatus(2);
            orderInfoMapper.updateByPrimaryKey(orderInfo);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 确认服务移交
    * @author 王大爷
    * @date 2015年10月21日 上午11:02:44
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param type 移交类型
    * @param detailId 明细标识
    * @param shiftMahjongId 轮牌标识
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @param storeId 门店标识
    * @param shiftMahjongEmployeeIdOld 该步骤原本指定的人员
    * @param isType 类型
    * @param lastOperatorId 操作人标识
    * @return ModelAndView
     */
    @Transactional
    public BaseDto serverAssociateShiftMahjong(Integer shiftMahjongStepId, Integer type, Integer detailId, Integer shiftMahjongId, 
            Integer shiftMahjongEmployeeId, Integer storeId, Integer shiftMahjongEmployeeIdOld, Integer isType, Integer lastOperatorId){
        
        LOGGERS.info("shiftMahjongStepId : " + shiftMahjongStepId + "type : " + type + "detailId : " + detailId 
                + "shiftMahjongId : " + shiftMahjongId + "shiftMahjongEmployeeId : " + shiftMahjongEmployeeId 
                + "storeId : " + storeId + "shiftMahjongEmployeeIdOld : " + shiftMahjongEmployeeIdOld + "lastOperatorId : " + lastOperatorId);
        //向下一个步骤移交
        if (type == 2) {
            //修改当前员工状态
            staffService.updateShiftLastEmployeeState(shiftMahjongStepId, detailId, storeId);
            
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("shiftMahjongStepId", shiftMahjongStepId);
            map.put("isOver", 2);
            
            //将跳过的步骤状态改为完成
            List<ShiftMahjongProjectStep> list = shiftMahjongProjectStepMapper.selectByNotOver(map);
            
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEmployeeId() != null) {
                    ShiftMahjongEmployee shiftMahjongEmployee 
                             = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(list.get(i).getShiftMahjongStepId());
                    
                    shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployee.getShiftMahjongEmployeeId());
                    //发送消息
                    senMessage(shiftMahjongEmployee.getEmployeesId(), null, list.get(i).getShiftMahjongStepId(), 
                            storeId, 5, lastOperatorId);
                }
                ShiftMahjongProjectStep record = new ShiftMahjongProjectStep();
                record.setShiftMahjongStepId(list.get(i).getShiftMahjongStepId());
                record.setIsDeleted(1);
                shiftMahjongProjectStepMapper.updateByPrimaryKey(record);
            }
            
            //修改订单明细状态
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(detailId);
            orderDetail.setOrderStatus(1);
            orderDetailMapper.updateByPrimaryKey(orderDetail);
            
            //如果没有指定人员
            if (shiftMahjongEmployeeId == null) {
                //根据轮牌项目步骤关系标识查询轮牌员工（满足项目中设定级别的排到第一个）
                ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectShiftMahjongOneEmployee(shiftMahjongStepId);
                if (obj == null) {
                    //转等待中心
                    redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                             Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                    ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
                    shiftMahjongProjectStep.setBeginTime(null);
                    shiftMahjongProjectStep.setIsAssign(0);
                    shiftMahjongProjectStep.setIsDesignate(0);
                    shiftMahjongProjectStep.setIsAppoint(0);
                    shiftMahjongProjectStep.setIsOver(2);
                    shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
                    shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
                    shiftMahjongProjectStepMapper.updateBySpecial(shiftMahjongProjectStep);
                    
                }
                else {
                    //调整员工在轮牌的位置及修改状态
                    staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                    
                    //轮牌项目步骤关系指定人员
                    staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                            shiftMahjongStepId, 0, 0, detailId, 0);
                    
                    //发送服务移交模版
                    senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 2, lastOperatorId);
                }
                //当原来该步骤有指定
                if (shiftMahjongEmployeeIdOld != null) {
                    
                    //指定人员减一
                    shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeIdOld);
                    
                    //发送取消指定模版消息
                    senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 5, lastOperatorId);
                }
            }
            //有指定的人
            else {
                //如果有指定的人，而开单时未指定
                if (shiftMahjongEmployeeIdOld == null) {
                    ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
                    /*ShiftMahjongEmployee objOld = shiftMahjongEmployeeMapper.selectShiftMahjongOneEmployee(shiftMahjongStepId);
                    if (objOld == null || obj.getShiftMahjongEmployeeId() != objOld.getShiftMahjongEmployeeId()) {
                        senMessage(objOld.getEmployeesId(), null, shiftMahjongStepId, storeId, 6, lastOperatorId);
                    }*/
                    if (obj.getState() == 1) {
                        
                        if (isType == 1) {
                            //调整员工在轮牌的位置及修改状态
                            staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), true);
                            //轮牌项目步骤关系指定人员
                            staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                                    shiftMahjongStepId, 1, 0, detailId, 0);
                        }
                        else if (isType == 2){
                            //调整员工在轮牌的位置及修改状态
                            staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                            //轮牌项目步骤关系指定人员
                            staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                                    shiftMahjongStepId, 0, 1, detailId, 0);
                        }
                        
                        //发送指定模版消息
                        senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 4, lastOperatorId);
                    }
                    else {
                        //转等待中心
                        redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                                Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                        
                        //发送模版消息
                        senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 8, lastOperatorId);
                        
                        //增加指定人
                        shiftMahjongEmployeeMapper.updateAddAppointNumber(shiftMahjongEmployeeId);
                        
                        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
                        shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                        if (isType == 1) {
                            shiftMahjongProjectStep.setIsAssign(1);
                            shiftMahjongProjectStep.setIsDesignate(0);
                        }
                        else if (isType == 2){
                            shiftMahjongProjectStep.setIsAssign(0);
                            shiftMahjongProjectStep.setIsDesignate(1);
                        }
                        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                    }
                }
                else {
                    //与开单时指定的人员比对，一致
                    if (shiftMahjongEmployeeId.intValue() == shiftMahjongEmployeeIdOld.intValue()) {
                        ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
                        if (obj.getState() == 1) {
                            
                            if (isType == 1) {
                                //调整员工在轮牌的位置及修改状态
                                staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), true);
                                //轮牌项目步骤关系指定人员
                                staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                                        shiftMahjongStepId, 1, 0, detailId, 0);
                            }
                            else {
                                //调整员工在轮牌的位置及修改状态
                                staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                                //轮牌项目步骤关系指定人员
                                staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                                        shiftMahjongStepId, 0, 1, detailId, 0);
                            }
                            
                            //指定人员减一
                            shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeIdOld);
                            
                            senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 1, lastOperatorId);
                        }
                        else {
                            //转等待中心
                            redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                                    Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                            //发送消息
                            
                            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                            shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
                            if (isType == 1) {
                                shiftMahjongProjectStep.setIsAssign(1);
                                shiftMahjongProjectStep.setIsDesignate(0);
                            }
                            else if (isType == 2){
                                shiftMahjongProjectStep.setIsAssign(0);
                                shiftMahjongProjectStep.setIsDesignate(1);
                            }
                            shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                        }
                    }
                    //与开单时指定的人员比对,不一致
                    else {
                        ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
                        ShiftMahjongEmployee oldObj = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeIdOld);
                        
                        //指定人员减一
                        shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeIdOld);
                        //发送取消订单模版消息
                        senMessage(oldObj.getEmployeesId(), null, shiftMahjongStepId, storeId, 5, lastOperatorId);
                        if (obj.getState() == 1) {
                            
                            if (isType == 1) {
                                //调整员工在轮牌的位置及修改状态
                                staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), true);
                                //轮牌项目步骤关系指定人员
                                staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                                        shiftMahjongStepId, 1, 0, detailId, 0);
                            }
                            else if (isType == 2){
                                //调整员工在轮牌的位置及修改状态
                                staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                                //轮牌项目步骤关系指定人员
                                staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                                        shiftMahjongStepId, 0, 1, detailId, 0);
                            }
                            //向服务人员发送模版消息
                            senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 4, lastOperatorId);
                            
                        }
                        else {
                            //转等待中心
                            redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                                    Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                            
                            //发送模版消息
                            senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 8, lastOperatorId);
                            
                            //增加指定人
                            shiftMahjongEmployeeMapper.updateAddAppointNumber(shiftMahjongEmployeeId);
                            
                            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                            shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
                            shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                            if (isType == 1) {
                                shiftMahjongProjectStep.setIsAssign(1);
                                shiftMahjongProjectStep.setIsDesignate(0);
                            }
                            else if (isType == 2){
                                shiftMahjongProjectStep.setIsAssign(0);
                                shiftMahjongProjectStep.setIsDesignate(1);
                            }
                            shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                        }
                    }
                }
                
            }
        }
        //同轮牌的移交
        else {
            
            //查询步骤对应轮牌员工标识
            ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
            
            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
            shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
            
            //修改明细状态
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(detailId);
            
            //如果没有指定人员
            if (shiftMahjongEmployeeId == null) {
                ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectShiftMahjongOneEmployee(shiftMahjongStepId);
                if (obj == null) {
                    //转等待中心
                    redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                            Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                    
                    shiftMahjongProjectStep.setIsOver(2);
                    shiftMahjongProjectStep.setEmployeeId(null);
                    shiftMahjongProjectStep.setBeginTime(null);
                    orderDetail.setOrderStatus(1);
                    
                }
                else {
                    orderDetail.setOrderStatus(2);
                    
                    shiftMahjongProjectStep.setIsOver(1);
                    shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                    shiftMahjongProjectStep.setBeginTime(DateUtil.getCurTime());
                    
                    //调整员工在轮牌的位置及修改状态
                    staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                    
                    senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 2, lastOperatorId);
                }
            }
            else {
                
                ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
                if (obj.getState() == 1) {
                    //调整员工在轮牌的位置及修改状态
                    if (isType == 1) {
                        staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), true);
                    }
                    else {
                        staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                    }
                    
                    orderDetail.setOrderStatus(2);
                    
                    shiftMahjongProjectStep.setIsOver(1);
                    shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                    shiftMahjongProjectStep.setBeginTime(DateUtil.getCurTime());
                    
                    senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 4, lastOperatorId);
                }
                else {
                    //转等待中心
                    redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                            Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                    
                    //发送模版消息
                    senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 8, lastOperatorId);
                    
                    orderDetail.setOrderStatus(1);
                    
                    shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                    shiftMahjongProjectStep.setIsOver(2);
                    shiftMahjongProjectStep.setBeginTime("");
                    
                    //增加指定人
                    shiftMahjongEmployeeMapper.updateAddAppointNumber(shiftMahjongEmployeeId);
                }
            }
            
            if (isType == 1) {
                shiftMahjongProjectStep.setIsAssign(1);
                shiftMahjongProjectStep.setIsDesignate(0);
            }
            else if (isType == 2) {
                shiftMahjongProjectStep.setIsAssign(0);
                shiftMahjongProjectStep.setIsDesignate(1);
            }
            else {
                shiftMahjongProjectStep.setIsAssign(0);
                shiftMahjongProjectStep.setIsDesignate(0);
            }
            shiftMahjongProjectStep.setIsAppoint(0);
            //修改该步骤员工
            shiftMahjongProjectStepMapper.updateBySpecial(shiftMahjongProjectStep);
            
            orderDetailMapper.updateByPrimaryKey(orderDetail);
            
            //修改被更换员工的轮牌状态（必须在步骤修改完以后才能修改被更换员工的轮牌状态）
            staffService.updateShiftEmployeeState(shiftMahjongEmployee.getShiftMahjongEmployeeId(), detailId, storeId);
            
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 等待中心
    * @author 王大爷
    * @date 2015年10月24日 下午3:21:05
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView waitingCentre(Integer storeId){
        ModelAndView mav = new ModelAndView(View.StaffPage.WAITING_ORDER);
        Set<String> tup = redisService.zrange(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 0, -1);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Iterator<String> iterator = tup.iterator(); iterator.hasNext();) {
            String detailId = iterator.next();
            Double dates = redisService.zscore(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailId);
            Long dateNow = System.currentTimeMillis();
            Long differ = (dateNow - dates.longValue())/60000;
            OrderDetail orderDetail  = orderDetailMapper.selectByPrimaryKey(Integer.valueOf(detailId));
            List<OrderDetailStepDto> orderDetailStepDtoList = shiftMahjongProjectStepMapper.selectOrderStepListByDetailId(Integer.valueOf(detailId));
            //删除redis中还有，数据库没有的
            if (orderDetailStepDtoList.size() == 0) {
                redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), Integer.valueOf(detailId));
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("detailId", orderDetail.getDetailId());
            map.put("detailCode", orderDetail.getDetailCode());
            map.put("projectId", orderDetail.getProjectId());
            map.put("projectName", orderDetail.getProjectName());
            map.put("discountAmount", orderDetail.getDiscountAmount());
            map.put("projectImage", orderDetail.getProjectImage());
            map.put("differ", differ);
            map.put("shiftMahjongStepId", orderDetailStepDtoList.get(0).getShiftMahjongStepId());
            map.put("shiftMahjongName", orderDetailStepDtoList.get(0).getShiftMahjongName());
            if (orderDetailStepDtoList.get(0).getEmployeeInfo() == null) {
                map.put("name", "未指定");
            }
            else {
                map.put("name", orderDetailStepDtoList.get(0).getEmployeeInfo().getEmployeeCode() 
                        + " " + orderDetailStepDtoList.get(0).getEmployeeInfo().getName());
            }
            list.add(map);
        }
        mav.addObject("list", list);
        return mav;
    }
    
    /**
     * 等待中心轮牌界面
    * @author 王大爷
    * @date 2015年10月24日 下午8:20:17
    * @param detailId 明细标识
    * @param shiftMahjongStepId 步骤标识
    * @return ModelAndView
     */
    public ModelAndView waitingCentreShiftMahjong(Integer detailId, Integer shiftMahjongStepId){
        ModelAndView mav = new ModelAndView(View.StaffPage.WAITING_SHIFTMAHJONG_SERVE);
        
        ShiftMahjongProjectStepDto obj = shiftMahjongProjectStepMapper.selectByPrimaryKey(shiftMahjongStepId);
        
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongStepId);
        
        Integer projectId = obj.getProjectStep().getProjectId();
        Integer num = obj.getProjectStep().getProjectStepOrder();
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("projectId", projectId);
        map.put("num", num);
        //查询出满足项目对应级别的员工
        List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftEmployeeList(map);
        
        //查询会员信息
        MemberBaseDto memberBaseDto = memberInfoMapper.selectByDetailId(detailId);
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        
        if (memberBaseDto == null) {
            memberBaseDto = new MemberBaseDto();
            memberBaseDto.setName("散客");
        }
        
        Integer isType = 0;
        
        if (obj.getIsAssign() == 1) {
            isType = 1;
        }
        if (obj.getIsDesignate() == 1){
            isType = 2;
        }
        mav.addObject("isType", isType);
        
        mav.addObject("shiftMahjongEmployeeIdOld", shiftMahjongEmployee.getShiftMahjongEmployeeId());
        mav.addObject("shiftMahjongEmployeeList", shiftMahjongEmployeeList);
        mav.addObject("shiftMahjongStepId", shiftMahjongStepId);
        mav.addObject("detailId", detailId);
        mav.addObject("memberBaseDto", memberBaseDto);
        mav.addObject("orderDetailDto", orderDetailDto);
        
        return mav;
    }
    
    /**
     * 确定等待轮牌更换的员工
    * @author 王大爷
    * @date 2015年10月24日 下午8:39:36
    * @param detailId 明细标识
    * @param shiftMahjongStepId 步骤标识
    * @param shiftMahjongEmployeeId 指定员工标识
    * @param storeId 门店标识
    * @param shiftMahjongEmployeeIdOld 被更换员工轮牌标识
    * @param lastOperatorId 操作人
    * @param isType 类别
    * @return ModelAndView
     */
    @Transactional
    public BaseDto waitingAssociateShiftMahjong (Integer detailId, Integer shiftMahjongStepId, Integer shiftMahjongEmployeeId, 
            Integer storeId, Integer shiftMahjongEmployeeIdOld, Integer isType, Integer lastOperatorId) {
        
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
        shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
        shiftMahjongProjectStep.setIsAppoint(0);
        
        
        if (shiftMahjongEmployeeId == null) {
            ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectShiftMahjongOneEmployee(shiftMahjongStepId);
            if (obj == null) {
                //只要是更换人员，所有指定及预约都失效
                shiftMahjongProjectStep.setIsDesignate(0);
                shiftMahjongProjectStep.setIsAssign(0);
                shiftMahjongProjectStep.setIsOver(2);
                
                //修改该步骤员工
                shiftMahjongProjectStepMapper.updateBySpecial(shiftMahjongProjectStep);
            }
            else {
                redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailId);
                //调整员工在轮牌的位置及修改状态
                staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                
                //轮牌项目步骤关系指定人员
                staffService.updateShiftMahjongProjectStep(obj.getEmployeesId(), 
                        shiftMahjongStepId, 0, 0, detailId, 0);
                
                //发送服务移交模版
                senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 2, lastOperatorId);
            }
        }
        else {
            ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
            if (obj.getState() == 1) {
                
                if (isType == 1) {
                    //调整员工在轮牌的位置及修改状态
                    staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), true);
                    
                    shiftMahjongProjectStep.setIsAssign(1);
                    shiftMahjongProjectStep.setIsDesignate(0);
                }
                else {
                    //调整员工在轮牌的位置及修改状态
                    staffService.moveEmployee(obj.getShiftMahjongEmployeeId(), false);
                    
                    shiftMahjongProjectStep.setIsAssign(0);
                    shiftMahjongProjectStep.setIsDesignate(1);
                }
                //只要是更换人员，所有预约都失效
                shiftMahjongProjectStep.setIsOver(1);
                shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                shiftMahjongProjectStep.setBeginTime(DateUtil.getCurTime());
                redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailId);
                
                //修改该步骤员工
                shiftMahjongProjectStepMapper.updateBySpecial(shiftMahjongProjectStep);
                
                
                //发送服务移交模版
                senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 1, lastOperatorId);
            }
            else {
                shiftMahjongProjectStep.setEmployeeId(obj.getEmployeesId());
                
                //增加指定人
                shiftMahjongEmployeeMapper.updateAddAppointNumber(shiftMahjongEmployeeId);
                //只要是更换人员，所有指定及预约都失效
                if (isType == 1) {
                    shiftMahjongProjectStep.setIsAssign(1);
                    shiftMahjongProjectStep.setIsDesignate(0);
                }
                else {
                    shiftMahjongProjectStep.setIsAssign(0);
                    shiftMahjongProjectStep.setIsDesignate(1);
                }
                shiftMahjongProjectStep.setIsOver(2);
                //修改该步骤员工
                shiftMahjongProjectStepMapper.updateBySpecial(shiftMahjongProjectStep);
                
                //发送服务移交模版
                senMessage(obj.getEmployeesId(), null, shiftMahjongStepId, storeId, 7, lastOperatorId);
            }

        }
        
        //取消原人员指定
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeIdOld);
        shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeIdOld);
        //当该步骤有指定时，发消息通知订单已更换
        senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongStepId, storeId, 5, lastOperatorId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 通知会员买单
    * @author 张进军
    * @date Nov 5, 2015 9:07:05 PM
    * @param storeId    门店标识
    * @param orderId    订单标识
    * @param employeeId 员工标识
    * @return   成功返回码为0，失败为其它返回码
     */
    public BaseDto notifyPaymentAction(int storeId, int orderId, int employeeId){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setOrderStatus(5);
        orderInfo.setUpdateTime(DateUtil.getCurTime());
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        
        OrderInfoBaseDto orderDto = orderInfoService.getOrderInfoBaseDto(orderId);
        String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, orderDto.getMemberId());
        if (StringUtils.isNotBlank(openId)) {
            List<OrderDetailDto> detailList = orderDto.getOrderDetailList();
            Set<String> projectSet = new HashSet<String>();
            for (OrderDetailDto orderDetail : detailList) {
                projectSet.add(orderDetail.getProjectName());
            }
            String projectList = projectSet.toString();
            projectList = projectList.substring(1, projectList.length() - 1);
            String url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_ORDER_PAY
                    .replace("{storeId}", storeId + "").replace("{businessType}", "1") + "?orderId=" + orderId;
            rabbitService.sendPaymentNotice(1, storeId, url, openId, orderDto.getMemberInfo().getStoreName(), 
                    orderDto.getMemberInfo().getPhone(), projectList, 
                    orderDto.getReceivableAmount().toString(), orderDto.getDiscountAmount().toString());
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年12月6日 下午4:58:53
    * @param storeId 门店标识
    * @param detailIdList 明细标识集合
     */
    public void operationRedisZrem(Integer storeId, List<Integer> detailIdList) {
        //删除等待中心
        redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailIdList);
    }

    /**
     * 
    * @author 王大爷
    * @date 2015年11月2日 下午6:21:43
    * @param employeesId 员工标识
    * @param oldEmployeeInfo 原服务人员
    * @param shiftMahjongStepId 步骤标识 
    * @param storeId 门店标识
    * @param type 1：指定服务、2：轮牌服务、3：指定服务被更换、4：中途指定服务、5：轮牌服务调整
    * @param lastOperatorId 操作人
     */
    public void senMessage(Integer employeesId, EmployeeInfo oldEmployeeInfo, Integer shiftMahjongStepId, 
            Integer storeId, Integer type, Integer lastOperatorId) {
        
        String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeesId);
        if (StringUtils.isBlank(openId)) {
            return;
        }
        
        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto 
                                   = shiftMahjongProjectStepMapper.selectByPrimaryKey(shiftMahjongStepId);
        
        OrderInfo orderInfo = orderInfoMapper.selectByDetailId(shiftMahjongProjectStepDto.getDetailId());
        MemberBaseDto memberInfo = null;
        if (orderInfo.getMemberId() != null) {
            memberInfo = memberInfoMapper.selectMemberBaseInfo(orderInfo.getMemberId());
        }
        else {
            memberInfo = new MemberBaseDto();
            memberInfo.setName("散客");
        }
        
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(shiftMahjongProjectStepDto.getProjectStep().getProjectId());
        
        EmployeeInfo lastOperator = null;
        if (lastOperatorId == null) {
            lastOperator = new EmployeeInfo();
            lastOperator.setEmployeeCode(0000);
            lastOperator.setName("系统");
        }
        else {
            lastOperator = employeeInfoMapper.selectByPrimaryKey(lastOperatorId);
        }
        
        //获取员工的总店标识
        int mainStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        String url = App.System.SERVER_BASE_URL + Url.Staff.VIEW_SELECT_ORDER_DETAIL.replace("{storeId}", mainStoreId + "")
                .replace("{businessType}", "2") + "?orderId=" + orderInfo.getOrderId();
        String turnType = null;
        String title = null;
        String serviceNmae = projectInfo.getProjectName();
        String remark = "您可以在\"我的－我的订单\"中随时查看订单详细信息";
        
        int isSpeech = 0;
        //指定通知功能
        if (type == 1 || type == 4) {
            title = "有顾客需要您服务\r\n";
            turnType = "指定服务";
            isSpeech = 1;
        }
        else if (type == 2){
            title = "有顾客需要您服务\r\n";
            turnType = "轮牌服务";
            isSpeech = 1;
        }
        else if (type == 3){
            title = "有同事的顾客需要您协助服务\r\n";
            turnType = "更换人员";
            isSpeech = 1;
        }
        else if (type == 5 || type == 6){
            title = "顾客取消了您这次的服务指定\r\n";
            turnType = "取消指定";
        }
//        else if (type == 6){
//            title = "服务变更通知:轮牌服务调整\r\n";
//            turnType = "轮牌服务调整"+ "\r\n\r\n明细编号：" + shiftMahjongProjectStepDto.getOrderDetail().getDetailCode();
//        }
        //指定了服务人员，但不是马上进行服务
        else if (type == 7 || type == 8) {
            title = "有顾客指定您服务\r\n";
            turnType = "服务指定";
        }
        else if (type == 9) {
            title = "顾客取消已服务的项目\r\n";
            turnType = "取消服务";
        }
//        else if (type == 8) {
//            title = "指定通知:中途别更指定通知\r\n";
//            
//            turnType = "被指定通知"+ "\r\n\r\n明细编号：" + shiftMahjongProjectStepDto.getOrderDetail().getDetailCode() 
//                    + "\r\n项目名称:" + projectInfo.getProjectName()
//                    + "\r\n服务客:" + memberInfo.getName() + "(" + memberInfo.getLevelName() + ")"
//                    + "\r\n服务步骤:" + shiftMahjongProjectStepDto.getProjectStep().getProjectStepName() 
//                    + "\r\n操作人：" + lastOperator.getEmployeeCode() + " " + lastOperator.getName()
//                    + "\r\n操作时间：" + DateUtil.getCurTime()
//                    + "\r\n操作备注：" + "中途发生变更，指定服务员工";
//        }
        
        turnType += "\r\n\r\n订单编号：" + orderInfo.getOrderCode() + "\r\n服务顾客：" + memberInfo.getName();
        if (StringUtils.isNotBlank(memberInfo.getLevelName())) {
            turnType += "\r\n顾客等级：" + memberInfo.getLevelName();
        }
        turnType = turnType  + "\r\n服务步骤：" + shiftMahjongProjectStepDto.getProjectStep().getProjectStepName() 
            + "\r\n操作人员：" + lastOperator.getEmployeeCode() + " " + lastOperator.getName()
            + "\r\n操作时间：" + DateUtil.getCurTime();
        
        rabbitService.sendServiceNotice(title, remark, storeId, url, openId, serviceNmae, turnType, oldEmployeeInfo, employeesId, isSpeech);
    }

}
