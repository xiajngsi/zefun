package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorHt;
import com.zefun.web.entity.Page;

/**
 * 错误数据录入-云浩企汇通
* @author 高国藩
* @date 2015年12月6日 下午7:46:39
 */
public interface MemberErrorHtMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月6日 下午7:46:55
    * @param id  主键
    * @return    行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月6日 下午7:46:55
    * @param record  主键
    * @return    行数
     */
    int insert(MemberErrorHt record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月6日 下午7:46:55
    * @param record  主键
    * @return    行数
     */
    int insertSelective(MemberErrorHt record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月6日 下午7:46:55
    * @param id  主键
    * @return    行数
     */
    MemberErrorHt selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月6日 下午7:46:55
    * @param record  主键
    * @return    行数
     */
    int updateByPrimaryKeySelective(MemberErrorHt record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月6日 下午7:46:55
    * @param record  主键
    * @return    行数
     */
    int updateByPrimaryKey(MemberErrorHt record);

    /**
     * 批量插入错误会员数据
    * @author 高国藩
    * @date 2015年12月6日 下午7:31:25
    * @param errorHts   数据集合
     */
    void insertList(List<MemberErrorHt> errorHts);
    
    /**
     * 云浩企汇通
    * @author 高国藩
    * @date 2015年12月7日 上午11:29:37
    * @param page   page
    * @return       结合 
     */
    List<MemberErrorHt> selectByPage(Page<MemberErrorHt> page);
    /**
     * 获取所有
    * @author 高国藩
    * @date 2015年12月17日 下午5:12:52
    * @param storeId  门店
    * @return         集合
     */
    List<MemberErrorHt> selectAll(Integer storeId);
    
    /**
     * 门店异常会员统计
    * @author 高国藩
    * @date 2015年12月17日 下午2:18:00
    * @param storeId  门店信息
    * @return         统计信息
     */
    MemberErrorHt selectStoreMemberAmount(Integer storeId);
}