package com.zefun.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.UserAccountMapper;

/**
 * 用户账号查询
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 */
@Service
public class UserAccountService {

    /**
     * 用户账号操作
     */
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 根据门店id查询单店的用户账号
     *
     * @author gebing
     * @date 2015年12月4日
     * @param storeId
     *            门店id
     * @return 用户账号
     */
    public UserAccount getSingleStoreAccount(Integer storeId) {
        return userAccountMapper.selectSingleStoreAccount(storeId);
    }

    /**
     * 根据门店id查询分店的用户账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 用户账号
     */
    public UserAccount getChainStoreAccount(Integer storeId) {
        return userAccountMapper.selectChainStoreAccount(storeId);
    }

    /**
     * 根据门店id查询总店的用户账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 用户账号
     */
    public UserAccount getChainHQStoreAccount(Integer storeId) {
        return userAccountMapper.selectChainHQStoreAccount(storeId);
    }

    /**
     * 根据门店id查询该门店下用户账号数
     * @param storeId 门店id
     * @return 门店下用户账号数
     */
    public int countByStoreId(Integer storeId) {
        return userAccountMapper.countByStoreId(storeId);
    }

}
