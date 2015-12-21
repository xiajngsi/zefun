package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorBk;
import com.zefun.web.entity.Page;

/**
 * 博卡处理异常会员数据
* @author 高国藩
* @date 2015年12月17日 下午5:26:20
 */
public interface MemberErrorBkMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param id   主键
    * @return     状态行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param record   主键
    * @return     状态行数
     */
    int insert(MemberErrorBk record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param record   主键
    * @return     状态行数
     */
    int insertSelective(MemberErrorBk record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param id   主键
    * @return     状态行数
     */
    MemberErrorBk selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param record   主键
    * @return     状态行数
     */
    int updateByPrimaryKeySelective(MemberErrorBk record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param record   主键
    * @return     状态行数
     */
    int updateByPrimaryKey(MemberErrorBk record);
    /**
     * 批量新增
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param list 数据
     */
    void insertList(List<MemberErrorBk> list);
    /**
     * 分页查询
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param page 分页
    * @return     状态行数
     */
    List<MemberErrorBk> selectByPage(Page<MemberErrorBk> page);
    /**
     * 查询所有
    * @author 高国藩
    * @date 2015年12月16日 下午4:36:46
    * @param storeId   门店
    * @return          集合数据
     */
    List<MemberErrorBk> selectAll(Integer storeId);
    
    /**
     * 异常会员数据统计
    * @author 高国藩
    * @date 2015年12月21日 上午11:30:06
    * @param storeId    门店信息
    * @return           异常数据
     */
    MemberErrorBk selectStoreMemberAmount(Integer storeId);
}