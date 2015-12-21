package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.Page;

/**
 * 会员等级操作类
* @author 张进军
* @date Aug 11, 2015 3:41:19 PM
 */
public interface MemberLevelMapper {
    
    /**
     * 根据等级标识删除会员等级信息
    * @author 张进军
    * @date Aug 11, 2015 3:41:40 PM
    * @param levelId    等级标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer levelId);

    /**
     * 插入会员等级信息
    * @author 张进军
    * @date Aug 11, 2015 3:42:20 PM
    * @param record    等级信息
    * @return   0:失败，1:成功
     */
    int insert(MemberLevel record);

    /***
     * 根据会员等级标识查询等级信息
    * @author 张进军
    * @date Aug 11, 2015 3:42:50 PM
    * @param levelId    等级标识
    * @return   会员等级信息
     */
    MemberLevel selectByPrimaryKey(Integer levelId);

    /**
     * 根据等级标识修改会员等级信息
    * @author 张进军
    * @date Aug 11, 2015 3:43:09 PM
    * @param record 会员等级信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(MemberLevel record);
    
    /**
     * 分页查询会员等级信息
    * @author 张进军
    * @date Aug 11, 2015 3:43:34 PM
    * @param page   分页信息(页码、每页显示数量、查询条件)
    * @return   对应页码的查询结果列表
     */
    List<MemberLevel> selectByPage(Page<MemberLevel> page);
    
    /**
     * 根据门店标识查询会员等级信息
    * @author 张进军
    * @date Aug 11, 2015 3:44:19 PM
    * @param storeId    门店标识
    * @return   对应门店的查询结果列表
     */
    List<MemberLevel> selectByStoreId(Integer storeId);
    
    /**
     * 根据门店标识查询默认的会员等级
    * @author 张进军
    * @date Oct 10, 2015 3:39:44 PM
    * @param storeId    门店标识
    * @return   对应门店的默认会员等级           
     */
    int selectDefaultLevelIdByStoreId(Integer storeId);
    
    /**
     * 将门店下所有等级设为非默认等级
    * @author 张进军
    * @date Oct 12, 2015 8:50:41 AM
    * @param storeId    门店标识
    * @return   0:失败，1:成功
     */
    int updateNonDefaultByStoreId(int storeId);
    
    /**
     * 将指定会员等级设为默认等级
    * @author 张进军
    * @date Oct 12, 2015 8:50:41 AM
    * @param levelId    等级标识
    * @return   0:失败，1:成功
     */
    int updateDefaultByLevelId(int levelId);
    
    /**
     * 删除/恢复会员等级
    * @author 张进军
    * @date Dec 2, 2015 10:51:01 AM
    * @param memberLevel    等级信息
    * @return   0:失败，1:成功
     */
    int updateDeleteByLevelId(MemberLevel memberLevel);
}