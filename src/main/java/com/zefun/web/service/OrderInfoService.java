package com.zefun.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.mapper.OrderInfoMapper;

/**
 * 订单相关操作服务类
* @author 张进军
* @date Oct 13, 2015 7:43:34 PM 
*/
@Service
public class OrderInfoService {
    /** 会员接口操作服务类 */
    @Autowired
    private MemberInfoService memberInfoService;

    /** 订单数据操作对象 */
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    
    /**
     * 查询门店下所有的订单信息
    * @author 张进军
    * @date Oct 13, 2015 8:04:21 PM
    * @param storeId    门店标识
    * @param type 订单状态类型（1、进行中，2、已完成，3、全部）
    * @return   门店下订单信息集合
     */
    public List<OrderInfoBaseDto> getOrderInfoBaseDtoByStoreId(int storeId, Integer type){
        List<Integer> orderIdList = new ArrayList<Integer>();
        if (type == 1) {
            orderIdList = orderInfoMapper.selectOrderIdByStoreIdNotOver(storeId);
        }
        else if (type == 2) {
            orderIdList = orderInfoMapper.selectOrderIdByStoreIdIsOver(storeId);
        }
        else {
            orderIdList = orderInfoMapper.selectOrderIdByStoreIdHistory(storeId);
        }
        
        return getOrderInfoBaseDtoByOrderIdList(orderIdList);
    }
    
    
    /**
     * 查询某员工的订单信息
    * @author 张进军
    * @date Oct 13, 2015 8:04:21 PM
    * @param employeeId    员工标识
    * @param type 订单状态类型（1、进行中，2、已完成，3、全部）
    * @return   门店下订单信息集合
     */
    public List<OrderInfoBaseDto> getOrderInfoBaseDtoByEmployeeId(int employeeId, Integer type){
        List<Integer> orderIdList = new ArrayList<Integer>();
        if (type == 1) {
            orderIdList = orderInfoMapper.selectOrderIdByEmployeeIdNotOver(employeeId);
        }
        else if (type == 2) {
            orderIdList = orderInfoMapper.selectOrderIdByEmployeeIdIsOver(employeeId);
        }
        else {
            orderIdList = orderInfoMapper.selectOrderIdByEmployeeIdHistory(employeeId);
        }
        return getOrderInfoBaseDtoByOrderIdList(orderIdList);
    }
    
    
    /**
     * 根据订单编号集合查询订单信息
    * @author 张进军
    * @date Oct 13, 2015 8:07:29 PM
    * @param orderIdList    订单编号集合
    * @return   订单信息集合
     */
    private List<OrderInfoBaseDto> getOrderInfoBaseDtoByOrderIdList(List<Integer> orderIdList) {
        if (orderIdList.isEmpty()) {
            return null;
        }
        
        List<OrderInfoBaseDto> orderList = new ArrayList<OrderInfoBaseDto>(orderIdList.size());
        for (Integer orderId : orderIdList) {
            OrderInfoBaseDto orderInfo = getOrderInfoBaseDto(orderId);
            orderList.add(orderInfo);
        }
        return orderList;
    }
    
    /**
     * 根据订单编号获取订单基础传输对象
    * @author 张进军
    * @date Oct 13, 2015 8:01:38 PM
    * @param orderId    订单编号
    * @return   订单基础传输对象
     */
    public OrderInfoBaseDto getOrderInfoBaseDto(Integer orderId){
        if (orderId == null) {
            return null;
        }
        
        /*String orderInfoBaseInfoJson = redisService.hget(App.Redis.ORDER_BASE_INFO_KEY_HASH, orderId);*/
        OrderInfoBaseDto orderInfo = null;
        /*//首先从缓存中获取，如果缓存中不存在，则从数据库查出并缓存
        if (StringUtils.isBlank(orderInfoBaseInfoJson)) {*/
        orderInfo = orderInfoMapper.selectOrderBaseByOrderId(orderId);
            /*if (orderInfo == null) {
                return null;
            }
            redisService.hset(App.Redis.ORDER_BASE_INFO_KEY_HASH, orderId, EntityJsonConverter.entity2Json(orderInfo));
        }
        //缓存中存在则直接转换为对象
        else {
            orderInfo = EntityJsonConverter.json2Entity(orderInfoBaseInfoJson, OrderInfoBaseDto.class);
        }*/
        MemberBaseDto memberInfo = new MemberBaseDto();
        //但会员为空时，为散客
        if (orderInfo.getMemberId() == null) {
            memberInfo.setName("散客");
        }
        else {
            memberInfo = memberInfoService.getMemberBaseInfo(orderInfo.getMemberId());
        }
        
        orderInfo.setMemberInfo(memberInfo);
        return orderInfo;
    }
}
