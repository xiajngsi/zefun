package com.zefun.web.mapper;

import com.zefun.web.entity.GoodsEvaluate;

/**
 * 商品评价信息操作类
* @author 张进军
* @date Nov 13, 2015 3:30:34 PM
 */
public interface GoodsEvaluateMapper {
    /**
     * 新增商品评价
    * @author 张进军
    * @date Nov 13, 2015 3:27:55 PM
    * @param record     商品评价信息
    * @return   0:失败，1:成功
     */
    int insert(GoodsEvaluate record);

    /**
     * 根据评价标识查询评价信息
    * @author 张进军
    * @date Nov 13, 2015 3:28:25 PM
    * @param id     评价标识
    * @return   评价信息
     */
    GoodsEvaluate selectByPrimaryKey(Integer id);

    /**
     * 根据评价标识修改评价信息
    * @author 张进军
    * @date Nov 13, 2015 3:28:53 PM
    * @param record     评价信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(GoodsEvaluate record);
}