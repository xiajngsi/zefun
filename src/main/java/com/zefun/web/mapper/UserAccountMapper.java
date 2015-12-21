package com.zefun.web.mapper;

import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.entity.UserAccount;

/**
 * 用户信息
 *
 * @author 洪秋霞
 * @date 2015年8月13日 下午6:45:25
 */
public interface UserAccountMapper {
    /**
     * 删除
     *
     * @author 洪秋霞
     * @date 2015年8月13日 下午6:45:34
     * @param userId
     *            用户id
     * @return int
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入
     *
     * @author 洪秋霞
     * @date 2015年8月13日 下午6:45:46
     * @param userAccount
     *            用户信息
     * @return int
     */
    int insert(UserAccount userAccount);

    /**
     * 查询
     *
     * @author 洪秋霞
     * @date 2015年8月13日 下午6:45:58
     * @param userId
     *            用户id
     * @return UserInfo
     */
    UserAccount selectByPrimaryKey(Integer userId);

    /**
     * 根据用户账号查询用户账户信息
     *
     * @author 张进军
     * @date Sep 18, 2015 7:35:11 PM
     * @param userName
     *            用户账号
     * @return 用户账户信息
     */
    UserAccount selectByUserName(String userName);

    /**
     * 更新
     *
     * @author 洪秋霞
     * @date 2015年8月13日 下午6:46:12
     * @param userInfo
     *            用户信息
     * @return int
     */
    int updateByPrimaryKey(UserAccount userInfo);

    /**
     * 判断账号是否已经被引用了
     *
     * @author chendb
     * @date 2015年10月9日 下午4:08:39
     * @param userInfo
     *            参数
     * @return int
     */
    int countUserName(UserAccount userInfo);

    /**
     * 修改账户角色
     *
     * @author chendb
     * @date 2015年10月9日 下午5:29:09
     * @param userInfo
     *            参数
     * @return int
     */
    int updateUserRole(UserAccount userInfo);

    /**
     * 获取员工角色id
     *
     * @author chendb
     * @date 2015年10月9日 下午5:41:47
     * @param userId
     *            标识
     * @return int
     */
    UserAccount queryRoleId(Integer userId);

    /**
     * 批量导入
     *
     * @author chendb
     * @date 2015年10月17日 下午1:45:34
     * @param list
     *            参数
     * @return int
     */
    int insertlist(EmployeeDto list);

    /**
     * 根据门店id查询单店的用户账号
     *
     * @author gebing
     * @date 2015年12月4日
     * @param storeId
     *            门店id
     * @return 用户账号
     */
    UserAccount selectSingleStoreAccount(Integer storeId);

    /**
     * 根据门店id查询分店的用户账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 用户账号
     */
    UserAccount selectChainStoreAccount(Integer storeId);

    /**
     * 根据门店id查询总店的用户账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 用户账号
     */
    UserAccount selectChainHQStoreAccount(Integer storeId);

    /**
     * 根据门店id查询该门店下用户账号数
     * @param storeId 门店id
     * @return 门店下用户账号数
     */
    int countByStoreId(Integer storeId);

}
