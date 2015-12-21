package com.zefun.web.mapper;

import com.zefun.web.entity.CopyMenu;

/**
 * 复制菜单信息
* @author 高国藩
* @date 2015年9月23日 下午6:32:53
 */
public interface CopyMenuMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param copyId 主键
    * @return 状态
     */
    int deleteByPrimaryKey(Integer copyId);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param record 实体
    * @return 状态
     */
    int insert(CopyMenu record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param record 实体
    * @return 状态
     */
    int insertSelective(CopyMenu record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param copyId 主键
    * @return 状态
     */
    CopyMenu selectByPrimaryKey(Integer copyId);
    /**
     * 跟新
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(CopyMenu record);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(CopyMenu record);
    /**
     * 查询根据门店
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param storeId 门店
    * @return 状态是否开启
     */
    Integer selectByStoreId(Integer storeId);
    /**
     * 删除根据门店
    * @author 高国藩
    * @date 2015年9月23日 下午6:31:04
    * @param storeId 门店
    * @return 状态
     */
    int deleteByStoreId(Integer storeId);
}