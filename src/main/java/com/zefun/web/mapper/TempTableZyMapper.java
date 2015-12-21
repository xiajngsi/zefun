package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.entity.TempTableZy;

/**
 * 左右临时数据
* @author 高国藩
* @date 2015年12月4日 下午3:25:36
 */
public interface TempTableZyMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param id 主键
    * @return   行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param record 主键
    * @return   行数
     */
    int insert(TempTableZy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param record 主键
    * @return   行数
     */
    int insertSelective(TempTableZy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param id 主键
    * @return   行数
     */
    TempTableZy selectByPrimaryKey(Integer id);

    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param record 主键
    * @return   行数
     */
    int updateByPrimaryKeySelective(TempTableZy record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param record 主键
    * @return   行数
     */
    int updateByPrimaryKey(TempTableZy record);

    /**
     * 插入
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param tableXies 集合
     */
    void insertList(List<TempTableZy> tableXies);

    /**
     * 查询
    * @author 高国藩
    * @date 2015年12月4日 下午3:25:52
    * @param storeId 条件
    * @return        集合
     */
    List<MemberInfoDto> selectAllbyStoreId(Integer storeId);


}