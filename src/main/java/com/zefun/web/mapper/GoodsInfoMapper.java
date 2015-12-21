package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.Page;

/**
 * 商品信息
* @author 洪秋霞
* @date 2015年8月11日 上午10:59:55
 */
public interface GoodsInfoMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:08
    * @param goodsId 商品id
    * @return int
     */
    int deleteByPrimaryKey(Integer goodsId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:34
    * @param goodsInfo 商品信息
    * @return int
     */
    int insertSelective(GoodsInfo goodsInfo);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:45
    * @param goodsId 商品id
    * @return GoodsInfo
     */
    GoodsInfo selectByPrimaryKey(Integer goodsId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:58
    * @param goodsInfo 商品信息
    * @return int
     */
    int updateByPrimaryKeySelective(GoodsInfo goodsInfo);
    
    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:01:09
    * @param goodsInfo 商品信息
    * @return List<GoodsInfo>
     */
    List<GoodsInfo> selectByProperty(GoodsInfo goodsInfo);
    
    /**
     * 根据门店id查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:01:26
    * @param storeId 门店id
    * @return List<GoodsInfo>
     */
    List<GoodsInfo> selectByStoreId(Integer storeId);

    /**
     * 查询商品库存分页
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:01:43
    * @param page 分页对象
    * @return List<GoodsInfoDto>
     */
    List<GoodsInfoDto> selectGoodsInfoPage(Page<GoodsInfoDto> page);

    /**
     * 根据部门标识查询商品信息
    * @author 高国藩
    * @date Oct 15, 2015 12:02:09 AM
    * @param deptId    部门标识
    * @return 部门下的商品信息
     */
    DeptGoodsBaseDto selectDeptGoodsByDeptId(Integer deptId);
    
    /**
     * 根据商品id查询该商品所有数据
    * @author 高国藩
    * @date 2015年10月17日 上午11:50:51
    * @param goodsId 商品id
    * @return 商品
     */
    GoodsInfoDto selectGoodsAllByPrimaryKey(Integer goodsId);

    /**
     * 将类别下面的商品删除
    * @author 高国藩
    * @date 2015年10月27日 下午6:30:05
    * @param categoryId 类别ID
    * @return 返回影响行数
     */
    int deleteByCategoryId(Integer categoryId);
    
    /**
     * 增加对应商品库存
    * @author 王大爷
    * @date 2015年12月2日 下午2:35:16
    * @param goodsInfo 参数
    * @return 是否成功
     */
    Integer updateGoodsStock(GoodsInfo goodsInfo);
    
}