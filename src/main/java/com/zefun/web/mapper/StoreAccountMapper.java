package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.StoreAccount;


/**
 * 门店账户信息操作类
* @author 张进军
* @date Nov 25, 2015 6:01:08 PM
 */
public interface StoreAccountMapper {


    /**
     * 新增门店账户
    * @author 张进军
    * @date Nov 25, 2015 6:01:19 PM
    * @param record 门店账户信息
    * @return   0:失败，1:成功
     */
    int insert(StoreAccount record);


    /**
     * 根据门店标识查询门店账户信息
    * @author 张进军
    * @date Nov 25, 2015 6:01:35 PM
    * @param storeId    门店标识
    * @return   门店账户信息
     */
    StoreAccount selectByPrimaryKey(Integer storeId);


    /**
     * 修改门店账户信息
    * @author 张进军
    * @date Nov 25, 2015 6:01:51 PM
    * @param record 门店账户信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(StoreAccount record);

    /**
     * 根据多个门店id查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return 多个门店账号
     */
    List<StoreAccount> selectByStoreIds(List<Integer> storeIds);

    /**
     * 根据多个门店id和门店账号状态查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param params storeIds 多个门店id, status 门店状态
     * @return 多个门店账号
     */
    List<StoreAccount> selectByStoreIdsAndStatus(Map<String, Object> params);

    /**
     * 根据多个门店id查询正常使用的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中正常使用门店账号
     */
    List<StoreAccount> selectNormalAccountByStoreIds(List<Integer> storeIds);

    /**
     * 根据多个门店id查询需要续费的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param params storeIds 多个门店id,storeIds中需要续费的门店账号
     * @return 集合
     */
    List<StoreAccount> selectRenewAccountByStoreIds(Map<String, Object> params);

    /**
     * 根据多个门店id查询已停用(剩余使用时间为0)的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中已停用的门店账号
     */
    List<StoreAccount> selectOverAccountByStoreIds(List<Integer> storeIds);
}
