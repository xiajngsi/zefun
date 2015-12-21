package com.zefun.web.mapper;

import com.zefun.web.entity.StoreSetting;

/**
 * 门店设置操作类
* @author 张进军
* @date Nov 24, 2015 11:50:01 AM
 */
public interface StoreSettingMapper {
    /**
     * 新增门店设置
    * @author 张进军
    * @date Nov 24, 2015 11:50:35 AM
    * @param record 门店信息
    * @return   0:失败，1:成功
     */
    int insert(StoreSetting record);

    /**
     * 根据门店标识查询门店设置信息
    * @author 张进军
    * @date Nov 24, 2015 11:50:39 AM
    * @param storeId    门店标识
    * @return           门店设置信息
     */
    StoreSetting selectByPrimaryKey(Integer storeId);

    /**
     * 根据门店标识修改门店信息
    * @author 张进军
    * @date Nov 24, 2015 11:50:43 AM
    * @param record 门店信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(StoreSetting record);
}