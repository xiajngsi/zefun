package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.GoodsPurchaseRecordDto;
import com.zefun.web.entity.GoodsPurchaseRecord;
import com.zefun.web.entity.Page;

/**
 * 商品进货记录
* @author 洪秋霞
* @date 2015年8月11日 上午11:18:08
 */
public interface GoodsPurchaseRecordMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:18:11
    * @param purchaseId 记录id
    * @return int
     */
    int deleteByPrimaryKey(Integer purchaseId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:18:54
    * @param goodsPurchaseRecord 商品进货记录
    * @return int
     */
    int insertSelective(GoodsPurchaseRecord goodsPurchaseRecord);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:19:01
    * @param purchaseId 记录id
    * @return GoodsPurchaseRecord
     */
    GoodsPurchaseRecord selectByPrimaryKey(Integer purchaseId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:19:28
    * @param goodsPurchaseRecord 商品进货记录
    * @return int
     */
    int updateByPrimaryKeySelective(GoodsPurchaseRecord goodsPurchaseRecord);
    
    /**
     * 分页查询
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:20:04
    * @param page 分页对象
    * @return List<SupplierInfo>
     */
    List<GoodsPurchaseRecordDto> selectByPropertyPage(Page<GoodsPurchaseRecordDto> page);
    
    /**
     * 根据门店id查询进货记录
    * @author 洪秋霞
    * @date 2015年9月6日 下午4:24:35
    * @param storeId 门店id
    * @return List<GoodsPurchaseRecordDto>
     */
    List<GoodsPurchaseRecordDto> selectByStoreId(Integer storeId);

}