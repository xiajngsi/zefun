package com.zefun.app.service;

import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.common.consts.App;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.service.RedisService;

/**
 * 用户账户基本模块服务类
* @author 张进军
* @date Sep 18, 2015 4:54:45 PM 
*/
@Service
public class UserService {
    /** 用户账户表操作类 */
    @Autowired
    private UserAccountMapper userAccountMapper;

    /** redis操作服务类 */
    @Autowired
    private RedisService redisService;

    /**
     * 用户登录
    * @author 张进军
    * @date Sep 18, 2015 4:50:00 PM
    * @param username       账号
    * @param password       密码
    * @param deviceType     设备类型(android、ios、other)
    * @param deviceToken    设备推送标识
    * @return           成功返回码0，返回值为用户角色、token、userid；失败返回其他错误码，返回值为提示语
     */
    public BaseDto login(String username, String password, String deviceType,
            String deviceToken) {
        UserAccount userAccount = userAccountMapper.selectByUserName(username);
        if (userAccount == null) {
            return new BaseDto(9001, "亲，确定没记错账号？");
        }

        // 检查用户密码
        if (!StringUtil.mD5(password + userAccount.getPwdSalt())
                .equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }

        int userId = userAccount.getUserId();
        String token = StringUtil.mD5(userId + UUID.randomUUID().toString());
        redisService.setMultiToken(String.valueOf(userId), token, deviceType,
                deviceToken);

        JSONObject userinfo = new JSONObject();
        userinfo.put("token", token);
        userinfo.put("userid", userId);
        userinfo.put("role", App.System.APP_USER_ROLE_EMPLOYEE);
        if (userAccount.getRoleId().toString().startsWith("1")) {
            userinfo.put("role", App.System.APP_USER_ROLE_BOSS);
        }

        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, userinfo);
    }
}
