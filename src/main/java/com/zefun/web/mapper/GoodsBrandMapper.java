package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.GoodsBrandDto;
import com.zefun.web.entity.GoodsBrand;
import com.zefun.web.entity.Page;
/**
 * 商品品牌
* @author 洪秋霞
* @date 2015年8月11日 上午10:52:05
 */
public interface GoodsBrandMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:52:15
    * @param brandId 品牌id
    * @return int
     */
    int deleteByPrimaryKey(Integer brandId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:52:52
    * @param goodsBrand 商品品牌
    * @return int
     */
    int insertSelective(GoodsBrand goodsBrand);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:53:58
    * @param brandId 品牌id
    * @return GoodsBrand
     */
    GoodsBrand selectByPrimaryKey(Integer brandId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:54:57
    * @param goodsBrand 商品品牌
    * @return int
     */
    int updateByPrimaryKeySelective(GoodsBrand goodsBrand);
    
    /**
     * 根据门店id查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:55:10
    * @param storeId 门店id
    * @return List<GoodsBrand>
     */
    List<GoodsBrand> selectByStoreId(Integer storeId);
    
    /**
     * 获取品牌列表和商品列表
    * @author 洪秋霞
    * @date 2015年8月31日 下午5:07:52
    * @param storeId 门店id
    * @return List<GoodsBrand>
     */
    List<GoodsBrand> getGoodsBrandInfo(Integer storeId);

    /**
     * 分页查询品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:44:31
    * @param page    分页对象
    * @return        数据结果街
     */
    List<GoodsBrandDto> selectByPage(Page<GoodsBrandDto> page);
}