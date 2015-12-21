package com.zefun.wechat.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EntityJsonConverter;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.entity.ShiftMahjong;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.WechatEmployee;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsCategoryMapper;
import com.zefun.web.mapper.GoodsDiscountMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;
import com.zefun.web.mapper.ProjectCommissionMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatEmployeeMapper;
import com.zefun.web.service.EmployeeService;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.ProjectService;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.ShiftMahjongService;

/**
 * 员工端service
* @author 王大爷
* @date 2015年8月20日 下午5:37:24
 */
@Service
public class StaffService {
    
    /** 项目类别*/
    @Autowired private ProjectCategoryMapper projectCategoryMapper;
    
    /** 商品类别*/
    @Autowired private GoodsCategoryMapper goodsCategoryMapper;
    
    /** 项目信息*/
    @Autowired private ProjectInfoMapper projectInfoMapper;
    
    /** 商品信息*/
    @Autowired private GoodsInfoMapper goodsInfoMapper;
    
    /** 员工信息*/
    @Autowired private EmployeeInfoMapper employeeInfoMapper;
    
    /** 套餐信息*/
    @Autowired private ComboInfoMapper comboInfoMapper;

    /** 订单信息*/
    @Autowired private OrderInfoMapper orderInfoMapper;
    
    /** 订单明细*/
    @Autowired private OrderDetailMapper orderDetailMapper;
    
    /** 项目步骤*/
    @Autowired private ProjectStepMapper projectStepMapper;
    
    /** 轮牌项目步骤关系*/
    @Autowired private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
    
    /** 轮牌员工*/
    @Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
    
    /** 轮牌信息*/
    @Autowired private ShiftMahjongMapper shiftMahjongMapper;
    
    /** 项目会员折扣*/
    @Autowired private ProjectDiscountMapper projectDiscountMapper;
    
    /**项目服务操作对象*/
    @Autowired private ProjectService projectService;
    
    /**商品服务操作对象*/
    @Autowired private GoodsInfoService goodsInfoService;
    
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    
    /** rabbitService*/
    @Autowired
    private RabbitService rabbitService;
    
    /** 登录*/
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    /** 微信*/
    @Autowired
    private WechatEmployeeMapper wechatEmployeeMapper;
    
    /** 商品*/
    @Autowired private GoodsDiscountMapper goodsDiscountMapper;
    
    /** 轮牌*/
    @Autowired private ShiftMahjongService shiftMahjongService;
    
    /** 会员等级*/
    @Autowired private MemberLevelMapper memberLevelMapper;
    /** 会员*/
    @Autowired private MemberInfoMapper memberInfoMapper;
    
    /** 会员信息业务逻辑类*/
    @Autowired private MemberInfoService memberInfoService;
    
    /** 项目员工提成*/
    @Autowired private ProjectCommissionMapper projectCommissionMapper;
    /**    */
    @Autowired private StaffOrderService staffOrderService;
    /**  */
    @Autowired private DeptInfoMapper deptInfoMapper;
    
    /**员工信息服务对象*/
    @Autowired private EmployeeService employeeService;
    
    /**
     * 查看员工主页
    * @author 王潇
    * @date Aug 19, 2015 4:21:25 PM
    * @param employeeId       员工标识
    * @return           员工主页面
     */
    public ModelAndView homeView(Integer employeeId){
        Map<String, Object> employeeMap = employeeInfoMapper.getDetail(employeeId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("employeeDto", employeeMap);
        return mav;
    }
    
    /**
     * 员工登录
    * @author 张进军
    * @date Aug 19, 2015 7:43:51 PM
    * @param phone          手机号
    * @param password       密码
    * @param openId         微信id
    * @return               成功返回码0，返回值为跳转地址；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto login(String phone, String password, String openId){
        UserAccount userAccount = userAccountMapper.selectByUserName(phone);
        if (userAccount == null) {
            return new BaseDto(9001, "账号不存在或已被删除");
        }
        
        //检查用户密码
        if (!StringUtil.mD5(password + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        BaseDto baseDto = new BaseDto();
        baseDto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
        
        Integer employeeId = userAccount.getUserId();
        //检查是否已经绑定员工，如果已绑定，直接提示错误
        if (StringUtils.isNotBlank(redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId))) {
            return new BaseDto(9003, "该员工已经绑定了其他微信号");
        }
        
//        Map<String, Object> employeeMap = employeeInfoMapper.getDetail(employeeId);
        //建立微信的关联关系
        wechatEmployeeMapper.deleteByPrimaryKey(openId);
        WechatEmployee wechatEmployee = new WechatEmployee();
        wechatEmployee.setOpenId(openId);
        wechatEmployee.setEmployeeId(employeeId);
        wechatEmployee.setIsSubscribe(isSubscribe(openId));
        String curTime = DateUtil.getCurTime();
        wechatEmployee.setCreateTime(curTime);
        wechatEmployee.setUpdateTime(curTime);
        wechatEmployeeMapper.insert(wechatEmployee);
        
        redisService.hset(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId, employeeId);
        redisService.hset(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId, openId);
        redisService.hset(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId, 2);
        
        return baseDto;
    }
    
    
    /**
     * 员工注销登录
    * @author 张进军
    * @date Dec 11, 2015 11:22:04 PM
    * @param employeeId 员工标识
    * @param request    请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto logout(int employeeId, HttpServletRequest request){
        employeeService.wipeCache(employeeId);
        request.getSession().invalidate();
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 查询会员信息、项目类别、商品类别
    * @author 王大爷
    * @date 2015年8月20日 下午5:40:29
    * @param storeId 门店标识
    * @param employeeId 员工标识
    * @param memberBaseDto 会员信息
    * @param orderId 订单标识
    * @return ModelAndView
     */
    public ModelAndView selectBaseInfo(Integer storeId, Integer employeeId, MemberBaseDto memberBaseDto, Integer orderId){
        
        ModelAndView mav = new ModelAndView();
        Integer employeeDeptId = employeeService.getDeptIdByEmployeeId(employeeId);
        mav.addObject("memberBaseDto", memberBaseDto);
        mav.addObject("memberBaseDtoStr", JSONObject.fromObject(memberBaseDto).toString());
        List<DeptInfo> deptList = deptInfoMapper.getDeptIdAndNameByStoreId(storeId);
        
        List<Map<String, Object>> dtoList = new ArrayList<Map<String, Object>>();
        
        for (DeptInfo dept : deptList) {
            Integer deptId = dept.getDeptId();
            
            if (employeeDeptId.intValue() == deptId.intValue()) {
                mav.addObject("employeeDeptName", dept.getDeptName());
            }
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            
            DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptId);
            map.put("project", deptProjectBaseDto.getProjectCategoryList());
            DeptGoodsBaseDto deptGoodsBaseDto = goodsInfoService.getDeptGoodsByDeptId(deptId);
            map.put("goods", deptGoodsBaseDto.getGoodsCategoryBaseDto());
            List<ComboInfo> comboInfoList = comboInfoMapper.getComboInfo(deptId);
            map.put("combo", comboInfoList);
            dtoList.add(map);
        }
        //常用项目
//        List<ProjectInfo> projectInfoListOrder = projectInfoMapper.selectDeptIdOrder(deptId);
//        mav.addObject("projectInfoListOrder", projectInfoListOrder);
        
        /*ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setStoreId(storeId);
        //固定值
        projectCategory.setDeptId(deptId);*/
        
//        List<ProjectCategoryDto> projectCategoryDtoList = projectCategoryMapper.selectByProjectCategory(projectCategory);
        
/*        mav.addObject("projectCategoryDtoList", deptProjectBaseDto.getProjectCategoryList());*/
        /*GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setStoreId(storeId);
        goodsCategory.setDeptId(deptId);*/
        
//        List<GoodsCategoryDto> goodsCategoryDtoList = goodsCategoryMapper.selectByGoodsCategory(goodsCategory);
        
/*        mav.addObject("goodsCategoryDtoList", deptGoodsBaseDto.getGoodsCategoryBaseDto());

        
        mav.addObject("comboInfoList", comboInfoList);*/
        mav.addObject("dtoList", dtoList);
        mav.addObject("deptList", deptList);
        mav.addObject("employeeDeptId", employeeDeptId);
        mav.addObject("orderId", orderId);
        
        mav.setViewName(View.StaffPage.PROJECT_CATEGORY);
        return mav;
    }
    
    
    /**
     * 开单
    * @author 王大爷
    * @date 2015年10月27日 上午11:06:17
    * @param objString 订单明细obj
    * @param employeeObj 指定人员
    * @param memberBaseDtoStr 会员信息
    * @param storeId 门店标识
    * @param lastOperatorId 最后操作者
    * @param isAppointment 是否预约
    * @param orderId 订单标识
    * @return ModelAndView
     */
    @Transactional
    public BaseDto addOrder(String objString, String employeeObj, String memberBaseDtoStr, Integer storeId, 
            Integer lastOperatorId, Integer isAppointment, Integer orderId) {
        MemberBaseDto memberBaseDto = EntityJsonConverter.json2Entity(memberBaseDtoStr, MemberBaseDto.class);

        Integer memberId = null;
        Integer levelId = null;
        String sex = memberBaseDto.getSex();
        if (memberBaseDto.getMemberId() != null && memberBaseDto.getMemberId() != 0) {
            memberId = memberBaseDto.getMemberId();
            
            levelId = memberInfoMapper.selectByPrimaryKey(memberId).getLevelId();
        }
        if (orderId == null) {
            String orderCode = getOrderCode("order_info", storeId);
            //保存订单信息
            orderId = addOrderInfo(orderCode, memberId, storeId, sex, lastOperatorId);
        }
        
        Integer markTypeInteger = addDetail(orderId, employeeObj, objString, storeId, memberId, levelId, sex, 
                isAppointment, lastOperatorId, memberBaseDto);
        
        orderInfoMapper.updateTotalPrice(orderId);
        
        //查询该明细对应的订单中存在的未完成的项目明细
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByNotOverByOrderId(orderId);
        if (orderDetailList.size() == 0) {
            //修改订单信息状态
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(orderId);
            orderInfo.setOrderStatus(2);
            orderInfoMapper.updateByPrimaryKey(orderInfo);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("markTypeInteger", markTypeInteger);
        map.put("storeId", storeId);
        map.put("orderId", orderId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
    
    /**
     * 添加明细，步骤，及修改轮牌信息
    * @author 王大爷
    * @date 2015年11月5日 上午10:41:08
    * @param orderId 订单标识
    * @param employeeObj 指定员工
    * @param objString 选择的项目
    * @param storeId 门店标识
    * @param memberId 会员标识
    * @param levelId 会员级别
    * @param sex 性别
    * @param isAppointment 是否预约
    * @param lastOperatorId 操作人
    * @param memberBaseDto 会员基本信息
    * @return Integer
     */
    public Integer addDetail(Integer orderId, String employeeObj, String objString, Integer storeId, Integer memberId, Integer levelId, String sex, 
            Integer isAppointment, Integer lastOperatorId, MemberBaseDto memberBaseDto) {

        Integer markTypeInteger = 0;
        //获取项目指定轮牌员工
        JSONArray empjsonArray =JSONArray.fromObject(employeeObj);
        List<Map<String, Integer>> assignList = new ArrayList<Map<String, Integer>>(); 
        for (int j = 0; j < empjsonArray.size(); j++) {
            JSONObject jsonEmployee = empjsonArray.getJSONObject(j);
            //对应订单编号
            Integer assignProjectNum = jsonEmployee.getInt("projectNum");
            //项目步骤
            Integer assignOrderNum = jsonEmployee.getInt("orderNum");
            //指定员工标识
            Integer assignProjectId = jsonEmployee.getInt("projectId");
            //指定员工对应轮牌员工标识
            Integer assignShiftMahjongEmployeeId = jsonEmployee.getInt("shiftMahjongEmployeeId");
            //指定员工标识
            Integer assignEmployeeId = jsonEmployee.getInt("employeeId");
            //类型
            Integer isType = jsonEmployee.getInt("isType");
            
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("assignProjectNum", assignProjectNum);
            map.put("assignOrderNum", assignOrderNum + 1);
            map.put("assignProjectId", assignProjectId);
            map.put("assignShiftMahjongEmployeeId", assignShiftMahjongEmployeeId);
            map.put("assignEmployeeId", assignEmployeeId);
            map.put("isType", isType);
            assignList.add(map);
        }
        
        JSONArray objjsonArray =JSONArray.fromObject(objString);
        
        for (int i = 0; i < objjsonArray.size(); i++) {
            JSONObject jsonObject = objjsonArray.getJSONObject(i);
            //订单明细类型
            Integer orderType = jsonObject.getInt("orderType");
            //项目标识
            Integer projectId = jsonObject.getInt("projectId");
            //项目名称
            String projectName = jsonObject.getString("projectName");
            //项目价格
            String  projectPriceStr = jsonObject.getString("projectPriceStr");
            BigDecimal  projectPrice = new BigDecimal(projectPriceStr);
            
            //项目数量
            Integer projectCount = (Integer) jsonObject.getInt("projectCount");
            //项目头像
            String projectImage = (String) jsonObject.getString("projectImage");
            
            //保存订单明细
            String detailCode = null;
            
            if (orderType == 1) {
                //选多个项目时，保存多条订单明细
                for (int k = 0; k < projectCount; k++) {
                    detailCode = getOrderCode("order_detail", storeId);
                    Integer detailId = addOrderDetail(detailCode, orderId, memberId, levelId, orderType, projectId, 
                            projectName, projectPrice, projectCount, projectImage, isAppointment, storeId, lastOperatorId);
                    
                    addShiftMahjongProjectStep(projectId, detailId, lastOperatorId);
                    //移除指定信息的记录
                    int a = -1;
                    //判断是否指定第一步
                    int b = 0;
                    //获取项目指定轮牌员工
                    for (int j = 0; j < assignList.size(); j++) {
                        
                        Map<String, Integer> assignmap = assignList.get(j);
                        
                        if (String.valueOf(assignmap.get("assignProjectId")).equals(String.valueOf(projectId))) {
                            if (a == -1) {
                                a = assignmap.get("assignProjectNum");
                                Integer type = invokingShift(assignmap, memberBaseDto, storeId, detailId, isAppointment, lastOperatorId);
                                if (type == 1) {
                                    markTypeInteger = 1;
                                }
                                if (assignmap.get("assignOrderNum") == 1) {
                                    b = 1;
                                }
                                assignList.remove(j);
                                j--;
                            }
                            else {
                                if (a == assignmap.get("assignProjectNum")) {
                                    Integer type = invokingShift(assignmap, memberBaseDto, storeId, detailId, isAppointment, lastOperatorId);
                                    if (type == 1) {
                                        markTypeInteger = 1;
                                    }
                                    if (assignmap.get("assignOrderNum") == 1) {
                                        b = 1;
                                    }
                                    assignList.remove(j);
                                    j--;
                                }
                            }
                            
                        }
                    }
                    //如果第一步没有指定，就从轮牌中取
                    if (b == 0) {
                        //根据项目步骤标识、订单明细查询轮牌项目步骤关系（第一步）
                        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(projectId, detailId, 1);
                        ShiftMahjongEmployee shiftMahjongEmployee 
                                = selectShiftMahjongOneEmployee(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                        if (shiftMahjongEmployee == null) {
                            //转到等待中心
                            redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                                     Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                        }
                        else {
                            //调整员工在轮牌的位置及修改状态
                            moveEmployee(shiftMahjongEmployee.getShiftMahjongEmployeeId(), false);
                            //轮牌项目步骤关系指定人员
                            updateShiftMahjongProjectStep(shiftMahjongEmployee.getEmployeesId(), 
                                    shiftMahjongProjectStepDto.getShiftMahjongStepId(), 0, 0, detailId, isAppointment);
                            if (shiftMahjongEmployee.getEmployeesId().intValue() == lastOperatorId.intValue()) {
                                markTypeInteger = 1;
                            }
                            else {
                                //发送模版消息
                                staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null,
                                        shiftMahjongProjectStepDto.getShiftMahjongStepId(), storeId, 2, lastOperatorId);
                            }
                        }
                    }
                }
            }
            else {
                for (int k = 0; k < projectCount; k++) {
                    addOrderDetail(detailCode, orderId, memberId, levelId, orderType, projectId, projectName, 
                            projectPrice, 1, projectImage, isAppointment, storeId, lastOperatorId);
                }
            }
            
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        //状态进行中
        orderInfo.setOrderStatus(1);
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        
        return markTypeInteger;
    }
    
    /**
     * 保存订单信息
    * @author 王大爷
    * @date 2015年9月19日 下午2:01:33
    * @param orderCode 订单号
    * @param memberId 会员信息标识
    * @param storeId 门店标识
    * @param sex 性别
    * @param employeeId 操作员工
    * @return 订单标识
     */
    @Transactional
    public Integer addOrderInfo(String orderCode, Integer memberId, Integer storeId, String sex, Integer employeeId){
        //保存订单信息
        int deptId = employeeService.getDeptIdByEmployeeId(employeeId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderCode(orderCode);
        if (memberId != null) {
            orderInfo.setMemberId(memberId);
        }
        orderInfo.setSex(sex);
        orderInfo.setDeptId(deptId);
        orderInfo.setStoreId(storeId);
        orderInfo.setCreateTime(DateUtil.getCurTime());
        orderInfo.setLastOperatorId(employeeId);
        
        orderInfoMapper.insert(orderInfo);
        return orderInfo.getOrderId();
    }
    
    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年9月19日 下午3:50:07
    * @param detailCode 订单明细编号
    * @param orderId 订单标识
    * @param memberId 会员信息标识
    * @param levelId 会员等级标识
    * @param orderType 订单类型(1:项目,2:商品,3:套餐)
    * @param projectId 项目标识
    * @param projectName 项目名称
    * @param projectPrice 项目价格
    * @param projectCount 项目数量
    * @param projectImage 项目图片
    * @param isAppointment 是否预约
    * @param storeId 门店标识
    * @param lastOperatorId 操作员工
    * @return 订单明细标识
     */
    public Integer addOrderDetail(String detailCode, Integer orderId, Integer memberId, Integer levelId, 
            Integer orderType, Integer projectId, String projectName, 
            BigDecimal projectPrice, Integer projectCount, String projectImage, Integer isAppointment, 
            Integer storeId, Integer lastOperatorId) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        
        //项目
        if (orderType == 1) {
            ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
            
            BigDecimal discountAmount = projectInfo.getProjectPrice();
            BigDecimal rate = new BigDecimal(100);
            
            //计算折扣价
            if (memberId != null) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("memberId", memberId);
                map.put("projectId", projectId);
                ProjectDiscount obj = projectDiscountMapper.selectByDiscount(map);
                
                //该项目对应该会员的会员等级不存在特定价格
                if (obj == null) {
                    //计算会员折扣价
                    MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(levelId);
                    discountAmount = projectInfo.getProjectPrice().multiply(new BigDecimal(memberLevel.getProjectDiscount()).divide(rate));
                }
                //该项目对应该会员的会员等级存在特定价格
                else {
                    discountAmount = obj.getDiscountAmount();
                }
                
                //记录预约优惠金额
                if (isAppointment == 1) {
                    discountAmount = discountAmount.subtract(projectInfo.getAppointmentPrice());
                    orderDetail.setAppointOff(projectInfo.getAppointmentPrice());
                    orderDetail.setIsAppoint(isAppointment);
                }
            }
            
            orderDetail.setDiscountAmount(discountAmount);
            orderDetail.setOrderStatus(1);
        }
        
        //商品
        else if (orderType == 2) {
            GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(projectId);
            BigDecimal discountAmount = goodsInfo.getGoodsPrice();
            BigDecimal rate = new BigDecimal(100);
            
            //计算折扣价
            if (memberId != null) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("memberId", memberId);
                map.put("goodsId", projectId);
                GoodsDiscount obj = goodsDiscountMapper.selectByDiscount(map);
                
                //该商品对应该会员的会员等级不存在特定价格
                if (obj == null) {
                    //计算会员折扣价
                    MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(levelId);
                    discountAmount = goodsInfo.getGoodsPrice().multiply(new BigDecimal(memberLevel.getProjectDiscount()).divide(rate));
                }
                //该商品对应该会员的会员等级存在特定价格
                else {
                    discountAmount = obj.getDiscountAmount();
                }
            } 
            
            orderDetail.setDiscountAmount(discountAmount);
            orderDetail.setOrderStatus(3);
        } 
        else {
            orderDetail.setDiscountAmount(projectPrice);
            orderDetail.setOrderStatus(3);
        }
        
        orderDetail.setDetailCode(detailCode);
        orderDetail.setOrderType(orderType);
        orderDetail.setIsAssign(0);
        orderDetail.setProjectId(projectId);
        orderDetail.setProjectName(projectName);
        orderDetail.setProjectPrice(projectPrice);
        orderDetail.setProjectCount(1);
        orderDetail.setProjectImage(projectImage);
        orderDetail.setStoreId(storeId);
        orderDetail.setCreateTime(DateUtil.getCurTime());
        orderDetail.setLastOperatorId(lastOperatorId);
        
        orderDetailMapper.insert(orderDetail);
        
        return orderDetail.getDetailId();
    }
    
    /**
     * 根据项目步骤标识、订单明细查询轮牌项目步骤关系(第一步)
    * @author 王大爷
    * @date 2015年9月28日 下午2:13:24
    * @param projectId 项目标识
    * @param detailId 订单明细标识
    * @param projectStepOrder 项目顺序
    * @return List<ShiftMahjongProjectStepDto>
     */
    public ShiftMahjongProjectStepDto selectShiftMahjongStep(Integer projectId, Integer detailId , Integer projectStepOrder){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("detailId", detailId);
        map.put("projectId", projectId);
        map.put("projectStepOrder", projectStepOrder);
        ShiftMahjongProjectStepDto objDto = shiftMahjongProjectStepMapper.selectShiftMahjongStep(map);
        return objDto;
    }
    
    /**
     * 保存轮牌步骤
    * @author 王大爷
    * @date 2015年9月19日 下午4:40:41
    * @param projectId 项目标识
    * @param detailId 订单明细
    * @param lastOperatorId 操作人员
     */
    public void addShiftMahjongProjectStep(Integer projectId, Integer detailId, Integer lastOperatorId){
        List<ProjectStep> list = projectStepMapper.queryProjectStepByPIdList(projectId);
        /*//修改项目前的没有删除的步骤
        List<ShiftMahjongProjectStep> objList = shiftMahjongProjectStepMapper.selectStepByDetailId(detailId);*/
        for (int i = 0; i < list.size(); i++) {
            /*Integer projectStepId = list.get(i).getProjectStepId();
            for (ShiftMahjongProjectStep obj : objList) {
                if (obj.getProjectStepId().intValue() == projectStepId.intValue()) {
                    
                }
            }*/
            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
            shiftMahjongProjectStep.setProjectStepId(list.get(i).getProjectStepId());
            shiftMahjongProjectStep.setDetailId(detailId);
            shiftMahjongProjectStep.setIsOver(2);
            shiftMahjongProjectStep.setCreateTime(DateUtil.getCurTime());
            shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
            
            shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep);
        }
    }
    
    /**
     * 查询项目对应轮牌员工
    * @author 王大爷
    * @date 2015年9月19日 下午5:19:42
    * @param projectId 项目标识
    * @return List<ShiftMahjongEmployee>
     */
    public List<ShiftMahjongDto> selectShiftProjectEmployee(Integer projectId){
                
        List<ShiftMahjong> list = shiftMahjongMapper.selectByProjectId(projectId);
                
        ShiftMahjongDto shiftMahjongDto = null;
        
        List<ShiftMahjongDto> shiftMahjongDtoList= new ArrayList<ShiftMahjongDto>();
        
        for (int i = 0; i < list.size(); i++) {
            ShiftMahjong shiftMahjong = list.get(i);
            shiftMahjongDto = new ShiftMahjongDto();
            shiftMahjongDto.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
            shiftMahjongDto.setShiftMahjongName(shiftMahjong.getShiftMahjongName());
            Integer num = i + 1;
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("projectId", projectId);
            map.put("num", num);
            
            List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftEmployeeList(map);
            shiftMahjongDto.setShiftMahjongEmployeeList(shiftMahjongEmployeeList);
            
            shiftMahjongDtoList.add(shiftMahjongDto);
        }
        return shiftMahjongDtoList;
    }
    
    /**
     * 根据项目标识和步骤查询对应轮牌信息
    * @author 王大爷
    * @date 2015年9月21日 下午5:45:04
    * @param projectId 项目标识
    * @param num 步骤
    * @return List<ShiftMahjongEmployee>
     */
    public List<ShiftMahjongEmployee> selectShiftEmployeeList(Integer projectId, Integer num){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("projectId", projectId);
        map.put("num", num);
        List<ShiftMahjongEmployee> list = shiftMahjongEmployeeMapper.selectShiftEmployeeList(map);
        return list;
    }
    
    /**
     * 根据轮牌项目步骤关系标识查询轮牌员工（满足项目中设定级别的排到第一个）
    * @author 王大爷
    * @date 2015年9月28日 下午4:45:53
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @return 满足项目中设定级别的排到第一个
     */
    public ShiftMahjongEmployee selectShiftMahjongOneEmployee(Integer shiftMahjongStepId){
        return shiftMahjongEmployeeMapper.selectShiftMahjongOneEmployee(shiftMahjongStepId);
    }
    
    /**
     * 指定时员工
    * @author 王大爷
    * @date 2015年9月21日 下午5:59:12
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @param employeeId 员工标识
    * @param shiftMahjongEmployeeId 轮牌员工标识
     */
    public void updateShiftMahjongProjectStepInfo(Integer shiftMahjongStepId, Integer employeeId, Integer shiftMahjongEmployeeId){
        
        //修改轮牌项目步骤关系
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
        shiftMahjongProjectStep.setEmployeeId(employeeId);
        shiftMahjongProjectStep.setIsAppoint(1);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        //修改轮牌员工状态为：指定服务
        ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
        shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
        shiftMahjongEmployee.setState(4);
        shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
    }
    
    /**
     * 指定人员
    * @author 王大爷
    * @date 2015年9月28日 上午11:19:38
    * @param employeeId 员工id
    * @param shiftMahjongStepId 轮牌项目步骤关系标识
    * @param isAssign 是否指定
    * @param detailId 订单明细标识
    * @param isAppoint 是否预约
    * @param isDesignate 是否指派
     */
    public void updateShiftMahjongProjectStep(Integer employeeId, Integer shiftMahjongStepId, Integer isAssign, Integer isDesignate, 
            Integer detailId, Integer isAppoint){
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setEmployeeId(employeeId);
        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
        shiftMahjongProjectStep.setBeginTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setIsOver(1);
        shiftMahjongProjectStep.setIsAssign(isAssign);
        shiftMahjongProjectStep.setIsDesignate(isDesignate);
        shiftMahjongProjectStep.setIsAppoint(isAppoint);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(detailId);
        orderDetail.setOrderStatus(2);
        orderDetailMapper.updateByPrimaryKey(orderDetail);
    }
    
    /**
     * 员工接单后，是否做出位置调整
    * @author 王大爷
    * @date 2015年9月22日 上午11:28:25
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @param type 是否指定
     */
    public void moveEmployee(Integer shiftMahjongEmployeeId, boolean type){
        ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByShiftMahjongEmployeeId(shiftMahjongEmployeeId);
        if (type) {
            if (shiftMahjong.getShiftMahjongRule() == 2) {
                //更新轮牌位置
                ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
                shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
                shiftMahjongEmployee.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
                shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
                shiftMahjongEmployeeMapper.updateByUpwardAllCount(shiftMahjongEmployee);
            }
            shiftMahjongService.updateState(shiftMahjongEmployeeId, 4, null);
        }
        else {
            //更新轮牌位置
            ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
            shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
            shiftMahjongEmployee.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
            shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
            shiftMahjongEmployeeMapper.updateByUpwardAllCount(shiftMahjongEmployee);
            shiftMahjongService.updateState(shiftMahjongEmployeeId, 0, null);
        }
    }
    
    /**
     * 修改上个轮牌员工状态
    * @author 王大爷
    * @date 2015年10月24日 下午2:48:47
    * @param shiftMahjongStepId 步骤标识
    * @param detailId 明细标识
    * @param storeId 门店标识
     */
    public void updateShiftLastEmployeeState(Integer shiftMahjongStepId, Integer detailId, Integer storeId){
        
        //查询上个步骤对应轮牌员工标识
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectShiftMahjongLastEmployee(shiftMahjongStepId);
        
        updateShiftEmployeeState(shiftMahjongEmployee.getShiftMahjongEmployeeId(), detailId, storeId);
            
        ShiftMahjongProjectStep obj = shiftMahjongProjectStepMapper.selectShiftMahjongLastStep(shiftMahjongStepId);
        
        //修改上个步骤
        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
        shiftMahjongProjectStep.setShiftMahjongStepId(obj.getShiftMahjongStepId());
        shiftMahjongProjectStep.setFinishTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setIsOver(3);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
        
        //修改服务时长
        orderDetailMapper.updateServiceLength(shiftMahjongStepId);
    }
    
    /**
     * 根据步骤修改轮牌员工状态
    * @author 王大爷
    * @date 2015年10月24日 下午2:48:47
    * @param shiftMahjongEmployeeId 轮牌员工标识标识
    * @param detailId 明细标识
    * @param storeId 门店标识
     */
    public void updateShiftEmployeeState(Integer shiftMahjongEmployeeId, Integer detailId, Integer storeId){
        
        OrderDetail detailObj  = orderDetailMapper.selectByPrimaryKey(detailId);
        
        if (detailObj.getOrderStatus() != 4) {
            ShiftMahjongEmployee record = new ShiftMahjongEmployee();
            record.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
            record.setState(1);
            //修改员工轮牌状态
            shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(record);
            
            //自动获取项目
            selfMotionExecute(shiftMahjongEmployeeId, storeId);
        }    
    }
    
    /**
     * 通过订单明细标识、步骤顺序、项目标识查询轮牌项目步骤关系标识
    * @author 王大爷
    * @date 2015年9月25日 下午4:17:05
    * @param detailId 订单明细标识
    * @param projectStepOrder 步骤顺序
    * @param projectId 项目标识
    * @return 轮牌项目步骤关系标识
     */
    public Integer selectShiftMahjongStepId(Integer detailId, Integer projectStepOrder, Integer projectId){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("detailId", detailId);
        map.put("projectStepOrder", projectStepOrder);
        map.put("projectId", projectId);
        return shiftMahjongProjectStepMapper.selectShiftMahjongStepId(map);
    }
    
    /**
     * 查询员工当前状态
    * @author 王大爷
    * @date 2015年9月25日 下午4:27:55
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @return 员工状态（0：工作中、1：空闲中、2：暂时离开、3：离开、4、指定服务）
     */
    public Integer selectState(Integer shiftMahjongEmployeeId) {
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
        return shiftMahjongEmployee.getState();
    }
    
    /**
     * 查询订单明细
    * @author 王大爷
    * @date 2015年9月22日 下午2:07:34
    * @param employeesId 员工标识
    * @return List<ShiftMahjongProjectStepDto>
     */
    public List<ShiftMahjongProjectStepDto> serveOrder(Integer employeesId){
        return shiftMahjongProjectStepMapper.selectByEmployeeId(employeesId);
    }
    
    /**
     * 新增项目是操作
    * @author 王大爷
    * @date 2015年10月17日 下午3:06:24
    * @param assignmap assignmap
    * @param memberDto 会员信息
    * @param storeId 门店标识
    * @param detailId 订单明细标识
    * @param lastOperatorId 操作人
    * @param isAppoint 是否预约
    * @return Integer
     */
    public Integer invokingShift(Map<String, Integer> assignmap, MemberBaseDto memberDto, Integer storeId, Integer detailId, 
            Integer isAppoint, Integer lastOperatorId) {
        
        Integer assignOrderNum =  assignmap.get("assignOrderNum");
        
        Integer assignShiftMahjongEmployeeId =  assignmap.get("assignShiftMahjongEmployeeId");
        
        Integer assignProjectId =  assignmap.get("assignProjectId");
        
        Integer assignEmployeeId = assignmap.get("assignEmployeeId");
        
        Integer isType = assignmap.get("isType");
        
        Integer markTypeInteger = 0;
        
        //根据项目步骤标识、订单明细查询轮牌项目步骤关系（第一步）
        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(assignProjectId, detailId, assignOrderNum);
        //当为步骤一时执行
        if (assignOrderNum == 1) {
            //判断当前员工状态
            Integer state = selectState(assignShiftMahjongEmployeeId);

            //如果该员工忙，则将订单步骤转等待
            if (state != 1) {
                
                //转等待中心
                redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                        Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));
                //向队列中插入一条指定消息
                /*rabbitService.sendAppointEmployeeMessage(shiftMahjongProjectStepDto.getProjectStep().getProjectStepName(),
                        name, phone, assignEmployeeId);*/
                //增加指定人
                shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
                
                ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
                
                if (isType == 1) {
                    shiftMahjongProjectStep.setIsAssign(1);
                    shiftMahjongProjectStep.setIsDesignate(0);
                }
                else if (isType == 2){
                    shiftMahjongProjectStep.setIsAssign(0);
                    shiftMahjongProjectStep.setIsDesignate(1);
                }
                
                shiftMahjongProjectStep.setIsAppoint(isAppoint);
                shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
                shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                
                if (assignEmployeeId.intValue() == lastOperatorId.intValue()) {
                    markTypeInteger = 1;
                }
                else {
                    //发送模版消息
                    staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                            storeId, 7, lastOperatorId);
                }
            }
            else {
                if (isType == 1) {
                    //调整员工在轮牌走哦那个的位置
                    moveEmployee(assignShiftMahjongEmployeeId, true);
                    //轮牌项目步骤关系指定人员 ,修改订单明细状态
                    updateShiftMahjongProjectStep(assignEmployeeId, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 1, 0, detailId, isAppoint);
                }
                else if (isType == 2){
                    //调整员工在轮牌走哦那个的位置
                    moveEmployee(assignShiftMahjongEmployeeId, false);
                    //轮牌项目步骤关系指定人员 ,修改订单明细状态
                    updateShiftMahjongProjectStep(assignEmployeeId, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 0, 1, detailId, isAppoint);
                }
                
                if (assignEmployeeId.intValue() == lastOperatorId.intValue()) {
                    markTypeInteger = 1;
                }
                else {
                    staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                            storeId, 1, lastOperatorId);
                }
            }
        }
        else {
            //将员工与订单明细关系保存到数据库
            /*redisService.zadd(App.Queue.APPOINT_EMPLOYEE + String.valueOf(assignEmployeeId), 
                    Double.valueOf(System.currentTimeMillis()), String.valueOf(shiftMahjongProjectStepDto.getShiftMahjongStepId()));*/
            ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
            shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
            
            if (isType == 1) {
                shiftMahjongProjectStep.setIsAssign(1);
                shiftMahjongProjectStep.setIsDesignate(0);
            }
            else if (isType == 2){
                shiftMahjongProjectStep.setIsAssign(0);
                shiftMahjongProjectStep.setIsDesignate(1);
            }

            shiftMahjongProjectStep.setIsAppoint(isAppoint);
            shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
            shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
            shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
            //向队列中插入一条指定消息
            /*rabbitService.sendAppointEmployeeMessage(projectStepName, name, phone, assignEmployeeId);*/
            //增加指定人
            shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
            
            //发送模版消息
            staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), storeId, 7, lastOperatorId);
        }
        return markTypeInteger;
    }
    
    /**
     * 修改项目
    * @author 王大爷
    * @date 2015年10月22日 下午5:51:54
    * @param storeId 门店标识
    * @param employeeId 员工标识
    * @param detailId 明细标识
    * @return ModelAndView
     */
    public ModelAndView updateProjectList(Integer storeId, Integer employeeId, Integer detailId) {
        ModelAndView mav = new ModelAndView(View.StaffPage.CHANGE_PROJECT);
        int deptId = employeeService.getDeptIdByEmployeeId(employeeId);
        //常用项目
        List<ProjectInfo> projectInfoListOrder = projectInfoMapper.selectDeptIdOrder(deptId);
        mav.addObject("projectInfoListOrder", projectInfoListOrder);
        
        DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptId);
        mav.addObject("projectCategoryDtoList", deptProjectBaseDto.getProjectCategoryList());
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        
        mav.addObject("orderDetailDto", orderDetailDto);
        
        mav.addObject("detailId", detailId);
        
        mav.addObject("projectId", orderDetailDto.getProjectId());
        return mav;
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年10月23日 下午4:49:52
    * @param detailId 明细标识
    * @param projectInfo 项目信息
    * @return ModelAndView
     */
    public ModelAndView confirmUpdateProject(Integer detailId, String projectInfo){
        
        ModelAndView mav = new ModelAndView(View.StaffPage.UPDATE_SHIFTMAHJONG_SERVE);
        ProjectInfo project = EntityJsonConverter.json2Entity(projectInfo, ProjectInfo.class);
        
        List<ShiftMahjongDto> shiftMahjongDtoList = selectShiftProjectEmployee(project.getProjectId());
        
        OrderDetailDto orderDetailDto = orderDetailMapper.selectByDetailBaseDto(detailId);
        
        mav.addObject("shiftMahjongDtoList", shiftMahjongDtoList);
        mav.addObject("orderDetailDto", orderDetailDto);
        mav.addObject("project", project);
        mav.addObject("projectInfo", projectInfo);
        mav.addObject("detailId", detailId);
        
        return mav;
    }
    
    /**
     * 修改项目保存
    * @author 王大爷
    * @date 2015年10月23日 下午7:43:02
    * @param detailId 明细标识
    * @param projectInfo 项目信息
    * @param employeeObj 指定员工
    * @param lastOperatorId 修改员工标识
    * @param storeId 门店标识
    * @return ModelAndView
     */
    @Transactional
    public BaseDto updateSaveDetail (Integer detailId, String projectInfo, String employeeObj, Integer lastOperatorId, Integer storeId) {
        
        ProjectInfo project = EntityJsonConverter.json2Entity(projectInfo, ProjectInfo.class);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(detailId);
        orderDetail.setProjectId(project.getProjectId());
        orderDetail.setProjectName(project.getProjectName());
        orderDetail.setProjectPrice(project.getProjectPrice());
        orderDetail.setProjectCount(1);
        orderDetail.setProjectImage(project.getProjectImage());
        orderDetail.setIsAppoint(0);
        
        OrderInfo orderInfoOld = orderInfoMapper.selectByDetailId(detailId);
        
        OrderDetail orderDetailOld = orderDetailMapper.selectByPrimaryKey(detailId);
        
        MemberBaseDto memberDto = new MemberBaseDto();
        
        if (orderInfoOld.getMemberId() != null) {
            MemberInfo memberInfo =  memberInfoMapper.selectByPrimaryKey(orderInfoOld.getMemberId());
            memberDto.setPhone(memberInfo.getPhone());
            memberDto.setName(memberInfo.getName());
            //计算会员折扣价
            MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(memberInfo.getLevelId());
            //会员折扣 * 项目价格
            Float discountAmount =  Float.parseFloat(memberLevel.getProjectDiscount().toString())
                    * Float.parseFloat(project.getProjectPrice().toString()) / 100; 
            orderDetail.setDiscountAmount(new BigDecimal(discountAmount));
        }
        else {
            orderDetail.setDiscountAmount(project.getProjectPrice());
            memberDto.setPhone("未知");
            memberDto.setName("散客");
        }
        
        orderDetail.setUpdateTime(DateUtil.getCurTime());
        orderDetail.setLastOperatorId(lastOperatorId);
        orderDetailMapper.updateByPrimaryKey(orderDetail);
        
        orderInfoMapper.updateTotalPrice(orderInfoOld.getOrderId());
        
        //删除该订单下没有完成的步骤
        List<ShiftMahjongProjectStep> shiftMahjongProjectStepList = shiftMahjongProjectStepMapper.selectByIsNotOver(detailId);
        //删除的步骤中如过存在指定员工
        for (int i = 0; i < shiftMahjongProjectStepList.size(); i++) {
            if (shiftMahjongProjectStepList.get(i).getEmployeeId() != null) {
                ShiftMahjongEmployee shiftMahjongEmployee 
                         = shiftMahjongEmployeeMapper.selectShiftMahjongEmployee(shiftMahjongProjectStepList.get(i).getShiftMahjongStepId());
                
                shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployee.getShiftMahjongEmployeeId());
                //发送消息
                staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongProjectStepList.get(i).getShiftMahjongStepId(), 
                        storeId, 5, lastOperatorId);
            }
            shiftMahjongProjectStepMapper.deleteByPrimaryKey(shiftMahjongProjectStepList.get(i).getShiftMahjongStepId());
        }
        
        addShiftMahjongProjectStep(project.getProjectId(), detailId, lastOperatorId);
        
        if (orderDetailOld.getOrderStatus() == 2) {
            JSONArray empjsonArray =JSONArray.fromObject(employeeObj);
            for (int j = 0; j < empjsonArray.size(); j++) {
                JSONObject jsonEmployee = empjsonArray.getJSONObject(j);
                //项目步骤
                Integer assignOrderNum = jsonEmployee.getInt("orderNum");
                //指定项目
                Integer assignProjectId = jsonEmployee.getInt("projectId");
                //指定员工标识
                Integer assignEmployeeId = jsonEmployee.getInt("employeeId");
                
                Integer assignShiftMahjongEmployeeId = jsonEmployee.getInt("shiftMahjongEmployeeId");
                assignOrderNum = assignOrderNum + 1;
                ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(assignProjectId, detailId, assignOrderNum);
                
                ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
                shiftMahjongProjectStep.setIsAssign(1);
                shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
                shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);

                
                //增加指定人
                shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
                
                //发送模版消息
                staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), storeId, 7, lastOperatorId);
            }
        }
        else {
            int b = 0;     
            JSONArray empjsonArray =JSONArray.fromObject(employeeObj);
            for (int j = 0; j < empjsonArray.size(); j++) {
                JSONObject jsonEmployee = empjsonArray.getJSONObject(j);
                //项目步骤
                Integer assignOrderNum = jsonEmployee.getInt("orderNum");
                //指定项目()
                Integer assignProjectId = jsonEmployee.getInt("projectId");
                //指定员工标识
                Integer assignEmployeeId = jsonEmployee.getInt("employeeId");
                
                Integer assignShiftMahjongEmployeeId = jsonEmployee.getInt("shiftMahjongEmployeeId");
                
                Integer isType = jsonEmployee.getInt("isType");
                
                assignOrderNum = assignOrderNum + 1;
                //根据项目步骤标识、订单明细查询轮牌项目步骤关系（第一步）
                ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(assignProjectId, detailId, assignOrderNum);
                //当为步骤一时执行
                if (assignOrderNum == 1) {
                    //判断当前员工状态
                    Integer state = selectState(assignShiftMahjongEmployeeId);
                    b = 1;
                    //如果该员工忙，则将订单步骤转等待
                    if (state != 1) {
                        
                        //转等待中心
                        /*redisService.zadd(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 
                                Double.valueOf(System.currentTimeMillis()), String.valueOf(detailId));*/

                        //增加指定人
                        shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
                        
                        ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                        shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
                        shiftMahjongProjectStep.setIsAssign(1);
                        shiftMahjongProjectStep.setIsAppoint(0);
                        shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
                        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                        
                        //发送模版消息
                        staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                                storeId, 7, lastOperatorId);
                    }
                    else {
                        
                        if (isType == 1) {
                            //调整员工在轮牌走哦那个的位置
                            moveEmployee(assignShiftMahjongEmployeeId, true);
                            //轮牌项目步骤关系指定人员 ,修改订单明细状态
                            updateShiftMahjongProjectStep(assignEmployeeId, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 1, 0, detailId, 0);
                        }
                        else if (isType == 2){
                            //调整员工在轮牌走哦那个的位置
                            moveEmployee(assignShiftMahjongEmployeeId, false);
                            //轮牌项目步骤关系指定人员 ,修改订单明细状态
                            updateShiftMahjongProjectStep(assignEmployeeId, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 0, 1, detailId, 0);
                        }
                        
                        staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                                storeId, 1, lastOperatorId);
                        
                        //删除订单明细
                        redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailId);
                    }
                }
                else {
                    //将员工与订单明细关系保存到数据库
                    ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep.setEmployeeId(assignEmployeeId);
                    shiftMahjongProjectStep.setIsAssign(1);
                    shiftMahjongProjectStep.setIsAppoint(0);
                    shiftMahjongProjectStep.setUpdateTime(DateUtil.getCurTime());
                    shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                    shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                    //向队列中插入一条指定消息
                    /*rabbitService.sendAppointEmployeeMessage(projectStepName, name, phone, assignEmployeeId);*/
                    //增加指定人
                    shiftMahjongEmployeeMapper.updateAddAppointNumber(assignShiftMahjongEmployeeId);
                    
                    //发送模版消息
                    staffOrderService.senMessage(assignEmployeeId, null, shiftMahjongProjectStepDto.getShiftMahjongStepId(), 
                            storeId, 7, lastOperatorId);
                }
            }
            
            //如果第一步没有指定，就从轮牌中取
            if (b == 0) {
                //根据项目步骤标识、订单明细查询轮牌项目步骤关系（第一步）
                ShiftMahjongProjectStepDto shiftMahjongProjectStepDto = selectShiftMahjongStep(project.getProjectId(), detailId, 1);
                ShiftMahjongEmployee shiftMahjongEmployee 
                        = selectShiftMahjongOneEmployee(shiftMahjongProjectStepDto.getShiftMahjongStepId());
                if (shiftMahjongEmployee != null) {
                    //调整员工在轮牌的位置及修改状态
                    moveEmployee(shiftMahjongEmployee.getShiftMahjongEmployeeId(), false);
                    //轮牌项目步骤关系指定人员
                    updateShiftMahjongProjectStep(shiftMahjongEmployee.getEmployeesId(), 
                            shiftMahjongProjectStepDto.getShiftMahjongStepId(), 0, 0, detailId, 0);
                    //发送模版消息
                    staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null,
                            shiftMahjongProjectStepDto.getShiftMahjongStepId(), storeId, 2, lastOperatorId);
                    //删除订单明细
                    redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), detailId);
                }
            }
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 附加项目（或商品、套餐）
    * @author 王大爷
    * @date 2015年11月5日 上午11:25:27
    * @param orderId 订单标识
    * @param storeId 门店标识
    * @param lastOperatorId 操作人
    * @return ModelAndView
     */
    public ModelAndView appendDetail(Integer orderId, Integer storeId, Integer lastOperatorId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        MemberBaseDto memberBaseDto = null;
        if (orderInfo.getMemberId() != null) {
            memberBaseDto = memberInfoMapper.selectMemberBaseInfo(orderInfo.getMemberId());
        }
        else {
            memberBaseDto = new MemberBaseDto();
            memberBaseDto.setName("散客");
            memberBaseDto.setSex(orderInfo.getSex());
        }
        return selectBaseInfo(storeId, lastOperatorId, memberBaseDto, orderId);
    }
    
    /**
     * 查询全部轮牌
    * @author 王大爷
    * @date 2015年11月12日 下午7:44:11
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView selectAllShiftMahjong(Integer storeId){
        ModelAndView mav =  new ModelAndView(View.StaffPage.ALL_SHIFTMAHJONG);
        List<DeptInfoDto> deptList = deptInfoMapper.selectByshiftMahjong(storeId);
        mav.addObject("deptList", deptList);
        mav.addObject("deptListJson", JSONArray.fromObject(deptList).toString());
        if (deptList.size() > 0) {
            mav.addObject("deptId", deptList.get(0).getDeptId());
            mav.addObject("typeNumber", deptList.size());
        }
        return mav;
    }
    
    /**
     * 查询订单详情
    * @author 王大爷
    * @date 2015年11月13日 下午6:23:27
    * @param orderId 订单标识
    * @return BaseDto
     */
    public BaseDto selectOrderInfo(Integer orderId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderInfo);
    }
    
    /**
     * 查询明细详情
    * @author 王大爷
    * @date 2015年12月3日 下午2:27:08
    * @param detailId 明细标识
    * @return BaseDto
     */
    public BaseDto selectOrderDetail (Integer detailId) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderDetail);
    }
    
    /**
     * 保存挂账
    * @author 王大爷
    * @date 2015年11月14日 上午11:33:06
    * @param orderId 订单标识
    * @param amount 金额
    * @param remark 备注
    * @param type 类型（1、签单，2、挂账）
    * @return BaseDto
     */
    public BaseDto saveDebtInfo(Integer orderId, BigDecimal amount, String remark, Integer type) {
        
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        BigDecimal discountAmount = null;
        OrderInfo record = new OrderInfo();
        record.setOrderId(orderId);
        if (type == 1) {
            /*record.setFreeAmount(amount);
            record.setOrderRemark(remark);
            discountAmount = orderInfo.getDiscountAmount().subtract((amount.subtract(orderInfo.getFreeAmount())));*/
        }
        else {
            record.setDebtAmount(amount);
            record.setOrderEvaluate(remark);
            discountAmount = orderInfo.getDiscountAmount().subtract((amount.subtract(orderInfo.getDebtAmount())));
        }
        
        BigDecimal zore = new BigDecimal(0);
        if (discountAmount.compareTo(zore) == -1) {
            discountAmount = zore;
        }
        
        record.setDiscountAmount(discountAmount);
        
        orderInfoMapper.updateByPrimaryKey(record);
        
        OrderInfo newOrderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, newOrderInfo.getDiscountAmount().toString());
    }
    
    /**
     * 保存签单
    * @author 王大爷
    * @date 2015年11月14日 上午11:33:06
    * @param detailId 订单标识
    * @param discountAmount 折扣金额
    * @param amount 金额
    * @param remark 备注
    * @return BaseDto
     */
    public BaseDto saveFreeInfo(Integer detailId, BigDecimal discountAmount, String amount, String remark) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        OrderDetail record = new OrderDetail();
        record.setDetailId(detailId);
        record.setFreeAmount(amount);
        record.setOrderRemark(remark);

        
        BigDecimal zore = new BigDecimal(0);
        if (discountAmount.compareTo(zore) == -1) {
            discountAmount = zore;
        }
        
        record.setDiscountAmount(discountAmount);
        
        orderDetailMapper.updateByPrimaryKey(record);
        
        //汇总签单总金额
        orderInfoMapper.updateFreeAmount(detailId);
        
        //汇总变更后总价
        orderInfoMapper.updateTotalPrice(orderDetail.getOrderId());
        
        OrderInfo newOrderInfo = orderInfoMapper.selectByPrimaryKey(orderDetail.getOrderId());
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, newOrderInfo.getDiscountAmount().toString());
    }
    
    /**
     * 当员工做完一个项目、更换了项目、更换了人员时员工状态为闲时
    * @author 王大爷
    * @date 2015年10月27日 下午5:51:44
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @param storeId 门店标识
     */
    public void selfMotionExecute(Integer shiftMahjongEmployeeId, Integer storeId){
        List<ShiftMahjongProjectStep> shiftMahjongProjectStepList = shiftMahjongProjectStepMapper.selectAppoint(shiftMahjongEmployeeId);
        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
        Set<String> tup = redisService.zrange(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), 0, -1);
        ShiftMahjongProjectStep shiftMahjongProjectStep = null;
        Integer isDetailId = null;
        for (Iterator<String> iterator = tup.iterator(); iterator.hasNext();) {
            String waitDetailId = iterator.next();
            for (int i = 0; i < shiftMahjongProjectStepList.size(); i++) {
                //如果为指定
                if (waitDetailId.equals(shiftMahjongProjectStepList.get(i).getDetailId().toString())) {
                    shiftMahjongProjectStep = shiftMahjongProjectStepList.get(i);
                    break;
                }
            }
            if (shiftMahjongProjectStep != null) {
                break;
            }
        }
        
        if (shiftMahjongProjectStep == null) {
            for (Iterator<String> iterator = tup.iterator(); iterator.hasNext();) {
                String waitDetailId = iterator.next();
                List<OrderDetailStepDto> orderDetailStepDtoList 
                                         = shiftMahjongProjectStepMapper.selectOrderStepListByDetailId(Integer.valueOf(waitDetailId));
                if (orderDetailStepDtoList.size() == 0) {
                    redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), Integer.valueOf(waitDetailId));
                    continue;
                }
                List<Integer> levelIdList = projectCommissionMapper.selectLevelIdList(orderDetailStepDtoList.get(0).getProjectStepId());
                //判断明细是否被指定，指定的明细，不能被接受
                if (orderDetailStepDtoList.get(0).getEmployeeInfo() == null  
                        && orderDetailStepDtoList.get(0).getShiftMahjongId().intValue() == shiftMahjongEmployee.getShiftMahjongId().intValue()
                        && levelIdList.contains(shiftMahjongEmployee.getLevelId())) {
                    isDetailId = Integer.valueOf(waitDetailId);
                    break;
                }
            }
        }
        else {
            redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), shiftMahjongProjectStep.getDetailId());
            
            if (shiftMahjongProjectStep.getIsDesignate() == 1) {
                //调整员工在轮牌走哦那个的位置
                moveEmployee(shiftMahjongEmployeeId, false);
                //轮牌项目步骤关系指定人员 ,修改订单明细状态
                updateShiftMahjongProjectStep(shiftMahjongProjectStep.getEmployeeId(), 
                        shiftMahjongProjectStep.getShiftMahjongStepId(), 0, 1, shiftMahjongProjectStep.getDetailId(), 0);
            }
            else {
                //调整员工在轮牌走哦那个的位置
                moveEmployee(shiftMahjongEmployeeId, true);
                //轮牌项目步骤关系指定人员 ,修改订单明细状态
                updateShiftMahjongProjectStep(shiftMahjongProjectStep.getEmployeeId(), 
                        shiftMahjongProjectStep.getShiftMahjongStepId(), 1, 0, shiftMahjongProjectStep.getDetailId(), 0);
            }
            
            //减小指定人数
            shiftMahjongEmployeeMapper.updateDecreaseAppointNumber(shiftMahjongEmployeeId);
            
            staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null, shiftMahjongProjectStep.getShiftMahjongStepId(), 
                    storeId, 1, null);
        }
        
        if (isDetailId != null) {
            redisService.zrem(App.Queue.WAIT_ORDER_EMPLOYEE + String.valueOf(storeId), isDetailId);
            List<OrderDetailStepDto> list = shiftMahjongProjectStepMapper.selectOrderStepListByDetailId(isDetailId);
            //调整员工在轮牌走哦那个的位置
            moveEmployee(shiftMahjongEmployeeId, false);
            //轮牌项目步骤关系指定人员 ,修改订单明细状态
            updateShiftMahjongProjectStep(shiftMahjongEmployee.getEmployeesId(), list.get(0).getShiftMahjongStepId(), 0, 0, isDetailId, 0);
            
            staffOrderService.senMessage(shiftMahjongEmployee.getEmployeesId(), null, list.get(0).getShiftMahjongStepId(), storeId, 2, null);
        }
    }
    
    /**
     * 检查微信id是否关注了公众号
    * @author 张进军
    * @date Aug 19, 2015 9:14:43 PM
    * @param openId     微信id
    * @return           1：关注，0：未关注
     */
    private int isSubscribe(String openId){
        String subscribe = redisService.hget(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, openId);
        if ("1".equals(subscribe)) {
            return 1;
        }
        return 0;
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月18日 下午8:19:46
    * @param field field
    * @param storeId storeId
    * @return String
     */
    public String getOrderCode(String field, Integer storeId){
        String numString = redisService.hget(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), field);
        for (int i = 0 ; i < 1 ;) {
            if (numString == null) {
                i = 1;
                String val = DateUtil.getCurDateString() + "0001";
                redisService.hset(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), "order_info", val);
                redisService.hset(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), "order_detail", "0");
                return val;
            } 
            else {
                String val = redisService.watchSet(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), storeId, field);
                if (val != null) {
                    i = 1;
                    return val;
                }
            }
        }
        return null;
    }
}
