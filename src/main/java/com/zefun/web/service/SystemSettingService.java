package com.zefun.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.UserAccountMapper;

/**
 * 系统设置服务类
* @author 张进军
* @date Nov 20, 2015 7:12:13 PM 
*/
@Service
public class SystemSettingService {
    /**员工操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /**用户账户操作对象*/
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    /**门店设置操作对象*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    
    /**优惠券信息操作对象*/
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /**门店信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /**
     * 个人设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param employeeInfo   员工资料
    * @param request        请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto personSettingAction(EmployeeInfo employeeInfo, HttpServletRequest request){
        employeeInfoMapper.updateByPrimaryKeySelective(employeeInfo);
        EmployeeBaseDto employeeBaseDto = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeInfo.getEmployeeId());
        request.getSession().setAttribute(App.Session.USER_INFO, employeeBaseDto);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 修改账户密码
    * @author 张进军
    * @date Nov 20, 2015 10:05:26 PM
    * @param userId     用户标识
    * @param oldPwd     原密码
    * @param newPwd     新密码
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto updatePwdAction(int userId, String oldPwd, String newPwd){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(userId);
        
        //检查用户密码
        if (!StringUtil.mD5(oldPwd + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        String hash = StringUtil.encryptPwd(newPwd);
        newPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(newPwd);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setUpdateTime(DateUtil.getCurTime());
        userAccountMapper.updateByPrimaryKey(userAccount);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 访问门店基础设置页面
    * @author 张进军
    * @date Nov 25, 2015 7:40:34 PM
    * @param storeId    门店标识
    * @return   门店基础设置页面
     */
    public ModelAndView baseSettingView(int storeId){
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(storeId);
        ModelAndView mav = new ModelAndView(View.Setting.BASE_SETTING);
        mav.addObject("storeSetting", storeSetting);
        mav.addObject("couponList", couponList);
        return mav;
    }
    
    
    /**
     * 门店基础数据操作
    * @author 张进军
    * @date Nov 25, 2015 8:49:52 PM
    * @param storeSetting   门店基础数据
    * @return   成功返回码为0，失败返回其他返回码
     */
    public BaseDto baseSettingAction(StoreSetting storeSetting){
        storeSettingMapper.updateByPrimaryKey(storeSetting);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 我的分店列表页面
    * @author 张进军
    * @date Dec 14, 2015 10:56:29 PM
    * @param storeId    总店标识
    * @return   分店列表页面
     */
    public ModelAndView branchList(int storeId){
        ModelAndView mav = new ModelAndView(View.Setting.BRANCH_LIST);
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
        mav.addObject("storeInfo", storeInfo);
        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        mav.addObject("storeList", storeList);
        return mav;
    }
}
