package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.ProjectMahjongProjectStepDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.wechat.service.StaffService;

/**
 * 轮牌信息Service
* @author 王大爷
* @date 2015年8月11日 上午11:50:57
 */
@Service
public class ManuallyOpenOrderService {
	/** 员工信息*/
	@Autowired private EmployeeInfoMapper employeeInfoMapper;
	
	/** 轮牌员工信息Mapper*/
	@Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
	
	/** redis Service*/
	@Autowired private RedisService redisService;
	
	/** 部门*/
	@Autowired private DeptInfoMapper deptInfoMapper;
	
	/** 岗位*/
	@Autowired private PositioninfoMapper positioninfoMapper;
	
	/** 轮牌*/
	@Autowired private ShiftMahjongMapper shiftMahjongMapper;
	
	/** 项目步骤*/
	@Autowired private ProjectStepMapper projectStepMapper;
	/** */
	@Autowired private StaffService staffService;
    /** 项目*/
	@Autowired private ProjectService projectService;
	/** 商品*/
	@Autowired private GoodsInfoService goodsInfoService;
	/** 套餐*/
	@Autowired private ComboInfoMapper comboInfoMapper;
	/** 项目*/
	@Autowired private ProjectInfoMapper projectInfoMapper;
	/** 商品*/
	@Autowired private GoodsInfoMapper goodsInfoMapper;
	/** 会员*/
	@Autowired private MemberInfoService memberInfoService;
	/** 步骤*/
	@Autowired private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
	/** 订单*/
	@Autowired private OrderInfoMapper orderInfoMapper;
	/** 订单明细*/
	@Autowired private OrderDetailMapper orderDetailMapper;
	
	/**
	 * 初始化轮职排班界面
	* @author laowang
	* @date 2015年8月8日 上午11:17:40
	* @param storeId 门店标识
	* @param employeeId 员工标识
	* @return ModelAndView
	 */
	public ModelAndView initializeManuallyOpenOrder(Integer storeId, Integer employeeId){
		ModelAndView mav = new ModelAndView();
        
		List<Integer> deptIdList = deptInfoMapper.selectDeptIdByStoreIdIsResults(storeId);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < deptIdList.size(); i++) {
		    
		    DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptIdList.get(i));
		    
		    DeptGoodsBaseDto deptGoodsBaseDto = goodsInfoService.getDeptGoodsByDeptId(deptIdList.get(i));
		    
	        List<ComboInfo> comboInfoList = comboInfoMapper.getComboInfo(deptIdList.get(i));
	        
	        //按部门组装数据
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("deptId", deptIdList.get(i));
	        map.put("deptName", deptProjectBaseDto.getDeptName());
	        map.put("projectCategoryList", deptProjectBaseDto.getProjectCategoryList());
	        map.put("goodsCategoryList", deptGoodsBaseDto.getGoodsCategoryBaseDto());
	        map.put("comboInfoList", comboInfoList);
		    list.add(map);
        }
		mav.addObject("list", list);
		//查询该门店所有员工
		List<EmployeeInfo> employeeInfoList = employeeInfoMapper.getRecommendlist(storeId);
		mav.addObject("employeeInfoList", JSONArray.fromObject(employeeInfoList));
		mav.setViewName(View.KeepAccounts.MANUALLY_OPEN_ORDER);
		return mav;
	}
	
	/**
     * 根据项目标识查询想轮牌信息及步骤对应员工
    * @author 王大爷
    * @date 2015年11月24日 下午12:08:34
    * @param projectId 项目标识
    * @return BaseDto
     */
	public BaseDto selectProjectShiftStep(Integer projectId) {
	    ProjectMahjongProjectStepDto projectMahjongProjectStepDto = projectInfoMapper.selectByProjectId(projectId);
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, projectMahjongProjectStepDto);
	}
	
	/**
	 * 手动开单
	* @author 王大爷
	* @date 2015年11月25日 上午10:23:45
	* @param memberId 会员标识
	* @param sex 散客性别
	* @param arrayObjStr 开单项目
	* @param storeId 门店标识
	* @param lastOperatorId 操作人
	* @return BaseDto
	 */
	@Transactional
    public BaseDto manuallyOpenOrderSave(Integer memberId, String sex, String arrayObjStr, Integer storeId, Integer lastOperatorId){
        //查询会员基本信息
        MemberBaseDto memberBaseDto = new MemberBaseDto();
        if (memberId != null) {
            memberBaseDto = memberInfoService.getMemberBaseInfo(memberId);
        }
        else {
            memberBaseDto.setSex(sex);
        }
        
        String orderCode = staffService.getOrderCode("order_info", storeId);
        //保存订单信息
        Integer orderId = staffService.addOrderInfo(orderCode, memberId, storeId, sex, lastOperatorId);
        
        JSONArray arrayObj =JSONArray.fromObject(arrayObjStr);
        
        for (int j = 0; j < arrayObj.size(); j++) {
            JSONObject jsonObj = arrayObj.getJSONObject(j);
            
            Integer orderType = jsonObj.getInt("type");
            Integer projectId = jsonObj.getInt("projectId");
            if (orderType == 1) {
                /*Integer assign = jsonObj.getInt("assign");*/
                Integer appoint = jsonObj.getInt("appoint");
                ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
                String detailCode = staffService.getOrderCode("order_detail", storeId);
                Integer detailId = staffService.addOrderDetail(detailCode, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, 
                        projectInfo.getProjectName(), projectInfo.getProjectPrice(), 1, projectInfo.getProjectImage(),
                        appoint, storeId, lastOperatorId);
                
                String projectStepArrayObjStr = jsonObj.getString("projectStepArrayObjStr");
                
                JSONArray stepObj =JSONArray.fromObject(projectStepArrayObjStr);
                for (int i = 0; i < stepObj.size(); i++) {
                    JSONObject jsonStep = stepObj.getJSONObject(i);
                    Integer projectStepId = jsonStep.getInt("projectStepId");
                    String employeeIds = jsonStep.getString("employeeId");
                    Integer employeeId = null;
                    if (!StringUtil.isEmpty(employeeIds)) {
                        employeeId = Integer.valueOf(employeeIds);
                    }
                    Integer isAssign = jsonStep.getInt("isAssign");
                    Integer isAppoint = jsonStep.getInt("isAppoint");
                    
                    
                    ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep.setProjectStepId(projectStepId);
                    shiftMahjongProjectStep.setEmployeeId(employeeId);
                    shiftMahjongProjectStep.setDetailId(detailId);
                    shiftMahjongProjectStep.setIsAssign(isAssign);
                    shiftMahjongProjectStep.setIsAppoint(isAppoint);
                    shiftMahjongProjectStep.setIsOver(3);
                    shiftMahjongProjectStep.setCreateTime(DateUtil.getCurTime());
                    shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep);
                }
                OrderDetail orderDetail = new OrderDetail(); 
                orderDetail.setDetailId(detailId);
                orderDetail.setOrderStatus(3);
                orderDetailMapper.updateByPrimaryKey(orderDetail);
            }
            else if (orderType == 2) {
                
                GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(projectId);
                String employeeIds = jsonObj.getString("projectStepArrayObjStr");
                Integer employeeId = null;
                if (!StringUtil.isEmpty(employeeIds)) {
                    employeeId = Integer.valueOf(employeeIds);
                }
                staffService.addOrderDetail(null, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, goodsInfo.getGoodsName(), 
                        goodsInfo.getGoodsPrice(), 1, goodsInfo.getGoodsImage(), 0, storeId, employeeId);
            }
            else {
                ComboInfo comboInfo = comboInfoMapper.selectByPrimaryKey(projectId);
                String employeeIds = jsonObj.getString("projectStepArrayObjStr");
                Integer employeeId = null;
                if (!StringUtil.isEmpty(employeeIds)) {
                    employeeId = Integer.valueOf(employeeIds);
                }
                staffService.addOrderDetail(null, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, comboInfo.getComboName(), 
                        comboInfo.getComboSalePrice(), 1, comboInfo.getComboImage(), 0, storeId, employeeId);
            }
        }
        
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        //状态进行中
        orderInfo.setOrderStatus(2);
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //修改订单价
        orderInfoMapper.updateTotalPrice(orderId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
