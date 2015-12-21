package com.zefun.web.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.StoreIncomeDto;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.WechatStore;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.StoreAccountMapper;
import com.zefun.web.mapper.StoreFlowMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatStoreMapper;

/**
 * 门店信息服务类
* @author 张进军
* @date Nov 9, 2015 11:21:30 AM
 */
@Service
public class StoreInfoService {

    /**
     * 剩余使用天数少于该值的正常使用的门店需要续费提醒
     */
    private static final int STORE_RENEW_DAYS = 7;

    /**员工信息操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    /**用户账户信息操作对象*/
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**店铺信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /**店铺基础设置操作对象*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;

    /**会员等级操作对象*/
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 门店与微信openid关连信息操作
     */
    @Autowired
    private WechatStoreMapper wechatStoreMapper;

    /**
     * 门店账号操作
     */
    @Autowired
    private StoreAccountMapper storeAccountMapper;

    /**
     * 渠道操作
     */
    @Autowired
    private AgentInfoService agentService;

    /**
     * 门店流水操作
     */
    @Autowired
    private StoreFlowMapper storeFlowMapper;

    /** 门店管理制度操作对象 */
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;

    /**
     * 进行店铺设置操作
    * @author 张进军
    * @date Nov 9, 2015 11:19:28 AM
    * @param storeId    店铺标识
    * @param storeInfo  店铺信息
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto storeSettingAction(Integer storeId, StoreInfo storeInfo) {
        storeInfoMapper.updateByPrimaryKey(storeInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }


    /**
     * 进入店铺设置页面
    * @author 张进军
    * @date Nov 9, 2015 11:19:03 AM
    * @param storeId    店铺标识
    * @return   店铺设置页面
     */
    public ModelAndView storeSettingView(Integer storeId) {
        ModelAndView view = new ModelAndView(View.Setting.STORE_SETTING);
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        view.addObject("storeInfo", storeInfo);

        List<EmployeeBaseDto> employeeList = employeeInfoMapper.selectEmployeeListByStoreId(storeId);
        view.addObject("storeEmployeeList", employeeList);

        if (StringUtils.isNotBlank(storeInfo.getTeacherIntroduction())) {
            List<String> list = Arrays.asList(storeInfo.getTeacherIntroduction().split(","));
            List<EmployeeBaseDto> showEmployeeList = employeeInfoMapper.selectEmployeeListByList(list);
            view.addObject("showEmployeeList", showEmployeeList);
        }

        if (StringUtils.isNotBlank(storeInfo.getCarouselPicture())) {
            List<String> ps = Arrays.asList(storeInfo.getCarouselPicture().split(","));
            view.addObject("ps", ps);
        }
        return view;
    }


    /**
     * 新增门店
    * @author 张进军
    * @date Oct 29, 2015 11:45:28 AM
    * @param name       用户姓名
    * @param phone      门店电话
    * @param storeName  门店名称
    * @param storeType  门店类型(1:单店，2:连锁店总店，3:连锁店分店)
    * @param parentId   父级门店标识
    * @return   成功返回码为0，失败为其它返回码
     */
    @Transactional
    public BaseDto addStoreInfo(String name, String phone, String storeName, Integer storeType, Integer parentId) {
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setStoreName(storeName);
        storeInfo.setStoreType(storeType);
        if (parentId != null && parentId != 0) {
            storeInfo.setHqStoreId(parentId);
        }
        storeInfo.setStoreLinkname(name);
        storeInfo.setStoreLinkphone(phone);
        storeInfo.setCreateTime(DateUtil.getCurTime());
        storeInfoMapper.insert(storeInfo);

        int storeId = storeInfo.getStoreId();

        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setStoreId(storeId);
        employeeDto.setDeptId(0);
        employeeDto.setName(name);
        employeeDto.setPhone(phone);
        employeeDto.setSex("男");
        employeeDto.setHeadImage("pc/defaulf_male.png");
        employeeInfoMapper.insert(employeeDto);

        int roleId = App.System.SYSTEM_ROLE_STORE_BRANCH_OWNER;
        if (storeType == 1) {
            roleId = App.System.SYSTEM_ROLE_STORE_SINGLE_OWNER;
        }
        else if (storeType == 2) {
            roleId = App.System.SYSTEM_ROLE_STORE_MAIN_OWNER;
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(employeeDto.getEmployeeId());
        userAccount.setUserName(phone);
        String password=StringUtil.mD5(StringUtil.mD5("123456"));
        String hash = StringUtil.encryptPwd(password);
        password = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(password);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setRoleId(roleId);
        userAccount.setStoreId(storeId);
        userAccountMapper.insert(userAccount);

        //如果是总店，添加默认等级
        if (storeType != 3) {
            //添加一个默认会员等级
            MemberLevel memberLevel = new MemberLevel();
            memberLevel.setStoreId(storeId);
            memberLevel.setChargeAmount(0);
            memberLevel.setChargeBelongType(1);
            memberLevel.setCreateTime(DateUtil.getCurTime());
            memberLevel.setGoodsDiscount(100);
            memberLevel.setIntegralNumber(1);
            memberLevel.setIntegralUnit(0);
            memberLevel.setIsDefault(1);
            memberLevel.setIsDeleted(0);
            memberLevel.setLevelName("默认会员");
            memberLevel.setProjectDiscount(100);
            memberLevel.setSellAmount(0);
            memberLevelMapper.insert(memberLevel);
        }

        //创建默认设置
        StoreSetting setting = new StoreSetting();
        setting.setStoreId(storeId);
        storeSettingMapper.insert(setting);

        //初始化门店管理制度
        storeManageRuleMapper.initStoreRuleByStoreId(storeId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 添加门店申请信息, 如果是申请连锁分店, 总店账号必须存在(不为空且存能根据总店账号查到记录)
     * @author gebing
     * @date 2015年12月4日
     * @param code 推荐人id
     * @param name 申请人姓名
     * @param phone 申请人手机号
     * @param storeType 申请的门店类型
     * @param hqUserName 申请连锁分店时的总店账号
     * @param storeName 门店名称
     * @param province 省份
     * @param city 城市
     * @param openId 申请人的openid
     * @return 申请结果
     */
    @Transactional
    public BaseDto addStoreApplyInfo(String code, String name, String phone, Integer storeType,
            String hqUserName, String storeName, String province, String city, String openId) {
        if (storeType == 3 && StringUtils.isBlank(hqUserName)) {
            return new BaseDto(3, "总店账号不能为空");
        }
        Integer hqStoreId = null;
        if (storeType == 3) {
            UserAccount hqAccount = userAccountMapper.selectByUserName(hqUserName);
            if (hqAccount == null) {
                return new BaseDto(4, "总店账号不可用");
            }
            StoreInfo hqStoreInfo = storeInfoMapper.selectByPrimaryKey(hqAccount.getStoreId());
            if (hqStoreInfo == null || hqStoreInfo.getStoreType() != 2) {
                return new BaseDto(4, "总店账号不可用");
            }
            hqStoreId = hqStoreInfo.getStoreId();
        }

        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setStoreLinkname(name);
        storeInfo.setStoreLinkphone(phone);
        storeInfo.setStoreType(storeType);
        storeInfo.setHqStoreId(hqStoreId);
        storeInfo.setStoreName(storeName);
        storeInfo.setStoreProvince(province);
        storeInfo.setStoreCity(city);
        storeInfo.setCreateTime(DateUtil.getCurTime());
        storeInfoMapper.insert(storeInfo);

        Integer storeId = storeInfo.getStoreId();
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreId(storeId);
        storeAccountMapper.insert(storeAccount);

        Integer recomendId = null;
        if (StringUtils.isNotBlank(code)) { // 推荐, 根据openid查询渠道商, 有则归到该渠道商
            AgentInfo recommendAgentInfo = agentService.getByOpenId(code);
            if (recommendAgentInfo != null) {
                recomendId = recommendAgentInfo.getAgentId();
            }
        }

        //查询所选城市是否存在渠道商，如果存在，归当地渠道商所有，否则归智放旗下
        AgentInfo regionAgentInfo = agentService.getByRegion(province, city);
        int agentId = App.System.DEFAULT_RECOMMEND_AGENT_ID;
        if (regionAgentInfo != null) {
            agentId = regionAgentInfo.getAgentId();
        }
        agentService.recommend(agentId, storeId, recomendId, 1);

        WechatStore wechatStore = new WechatStore();
        wechatStore.setOpenId(openId);
        wechatStore.setStoreId(storeId);
        wechatStoreMapper.insert(wechatStore);

        return new BaseDto(0, "申请成功");
    }


    /**
     * 根据openid查询门店信息
     * @author gebing
     * @date 2015年12月4日
     * @param openId 微信openid
     * @return 门店信息
     */
    public StoreInfo getStoreByOpenId(String openId) {

        WechatStore wechatStore = wechatStoreMapper.selectByPrimaryKey(openId);
        if (wechatStore == null || wechatStore.getStoreId() == null) {
            return null;
        }
        return storeInfoMapper.selectByPrimaryKey(wechatStore.getStoreId());
    }

    /**
     * 根据门店id查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 门店账号
     */
    public StoreAccount getAccountByStoreId(Integer storeId) {
        return storeAccountMapper.selectByPrimaryKey(storeId);
    }

    /**
     * 根据门店id 查询门店信息
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 门店信息
     */
    public StoreInfo getByStoreId(Integer storeId) {
        return storeInfoMapper.selectByPrimaryKey(storeId);
    }

    /**
     * 根据总店id查询旗下的分店数量
     * @param storeId 总店id
     * @return 分店数量
     */
    public int countChainStores(Integer storeId) {
        return storeInfoMapper.countByHQStoreId(storeId);
    }

    /**
     * 根据总店id查询旗下所有分店
     * @param hqStoreId 总店id
     * @return 总店id查询旗下所有分店
     */
    public List<StoreInfo> getChainsByHQStoreId(Integer hqStoreId) {
        return storeInfoMapper.selectChainsByHQStoreId(hqStoreId);
    }

    /**
     * 根据多个门店id查询门店信息
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return 多个门店信息
     */
    public List<StoreInfo> getStoreByStoreIds(List<Integer> storeIds) {
        return storeInfoMapper.selectByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return 多个门店账号
     */
    public List<StoreAccount> getAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id和门店账号状态查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @param status 门店状态
     * @return 多个门店账号
     */
    public List<StoreAccount> getAccountByStoreIds(List<Integer> storeIds, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", status);
//        params.put("storeIds", StringUtils.join(storeIds, ','));
        params.put("storeIds", storeIds);
        return storeAccountMapper.selectByStoreIdsAndStatus(params);
    }

    /**
     * 根据多个门店id查询正常使用的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中正常使用门店账号
     */
    public List<StoreAccount> getNormalAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectNormalAccountByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id查询需要续费的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中需要续费的门店账号
     */
    public List<StoreAccount> getRenewAccountByStoreIds(List<Integer> storeIds) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("balanceDays", STORE_RENEW_DAYS);
        params.put("storeIds", storeIds);
        return storeAccountMapper.selectRenewAccountByStoreIds(params);
    }

    /**
     * 根据多个门店id查询已停用(剩余使用时间为0)的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中已停用的门店账号
     */
    public List<StoreAccount> getOverAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectOverAccountByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id和时间查询各个门店收益
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @param begin 开始
     * @param end 结束
     * @return 各个门店收益
     */
    public List<StoreIncomeDto> getMotnIncomesByTime(List<Integer> storeIds,
            String begin, String end) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin", begin);
        params.put("end", end);
        params.put("storeIds", storeIds);
        return storeFlowMapper.selectMonthIncomesByTime(params);
    }

}
