package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.entity.MemberErrorSc;
import com.zefun.web.entity.Page;

/**
 * 
* @author 高国藩
* @date 2015年12月4日 下午5:05:05
 */
public interface MemberErrorScMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月5日 下午12:23:08
    * @param id  标示
    * @return    行数
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月5日 下午12:23:08
    * @param record  标示
    * @return    行数
     */
    int insert(MemberErrorSc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月5日 下午12:23:08
    * @param record  标示
    * @return    行数
     */
    int insertSelective(MemberErrorSc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月5日 下午12:23:08
    * @param id  标示
    * @return    行数
     */
    MemberErrorSc selectByPrimaryKey(Integer id);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月5日 下午12:23:08
    * @param record  标示
    * @return    行数
     */
    int updateByPrimaryKeySelective(MemberErrorSc record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月5日 下午12:23:08
    * @param record  标示
    * @return    行数
     */
    int updateByPrimaryKey(MemberErrorSc record);

    /**
     * 批量新增
    * @author 高国藩
    * @date 2015年12月4日 下午4:57:12
    * @param errorScs 集合
     */
    void insertList(List<MemberErrorSc> errorScs);
    
    /**
     * 分页查询盛传导入错误会员
    * @author 高国藩
    * @date 2015年12月7日 上午10:58:15
    * @param page      参数
    * @return          集合
     */
    List<MemberErrorSc> selectByPage(Page<MemberErrorSc> page);
    /**
     * 获取所有
    * @author 高国藩
    * @date 2015年12月17日 下午5:09:11
    * @param storeId 门店
    * @return        集合
     */
    List<MemberErrorSc> selectAll(Integer storeId);
    
    /**
     * 门店异常会员统计
    * @author 高国藩
    * @date 2015年12月17日 下午2:18:00
    * @param storeId  门店信息
    * @return         统计信息
     */
    MemberErrorSc selectStoreMemberAmount(Integer storeId);
}