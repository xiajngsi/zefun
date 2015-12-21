package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.Page;
import com.zefun.web.entity.SupplierInfo;

/**
 * 供应商信息
* @author 洪秋霞
* @date 2015年8月11日 上午11:21:55
 */
public interface SupplierInfoMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:21:58
    * @param supplierId 供应商id
    * @return int
     */
    int deleteByPrimaryKey(Integer supplierId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:22:28
    * @param supplierInfo 供应商信息
    * @return int
     */
    int insertSelective(SupplierInfo supplierInfo);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:22:41
    * @param supplierId 供应商id
    * @return SupplierInfo
     */
    SupplierInfo selectByPrimaryKey(Integer supplierId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:28:20
    * @param supplierInfo 供应商信息
    * @return int
     */
    int updateByPrimaryKeySelective(SupplierInfo supplierInfo);

    /**
     * 供应商列表
    * @author 洪秋霞
    * @date 2015年9月6日 上午10:28:13
    * @param storeId 门店id
    * @return List<SupplierInfo>
     */
    List<SupplierInfo> selectByStoreId(Integer storeId);

    /**
     * 分页查询
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:20:04
    * @param page 分页对象
    * @return List<SupplierInfo>
     */
    List<SupplierInfo> selectByPropertyPage(Page<SupplierInfo> page);

}