package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.GiftmoneyDetail;

/**
 * 礼金明细
* @author 王大爷
* @date 2015年9月10日 下午4:49:40
 */
public interface GiftmoneyDetailMapper {
    
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月10日 下午4:49:53
    * @param detail 礼金明细标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer detail);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:49:53
    * @param record 礼金明细
    * @return 是否成功
     */
    int insert(GiftmoneyDetail record);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:49:53
    * @param record 礼金明细
    * @return 是否成功
     */
    int insertSelective(GiftmoneyDetail record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月10日 下午4:49:53
    * @param detail 礼金明细标识
    * @return 是否成功
     */
    GiftmoneyDetail selectByPrimaryKey(Integer detail);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:49:53
    * @param record 礼金明细
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(GiftmoneyDetail record);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:49:53
    * @param record 礼金明细
    * @return 是否成功
     */
    int updateByPrimaryKey(GiftmoneyDetail record);
    
    /**
     * 根据会员账户标识查询礼金明细列表
    * @author 王大爷
    * @date 2015年12月14日 下午12:24:47
    * @param accountId 礼金账户
    * @return List<GiftmoneyDetail>
     */
    List<GiftmoneyDetail> selectByAccountId(Integer accountId);
    
    /**
     * 根据明细标识查询礼金明细
    * @author 王大爷
    * @date 2015年12月14日 下午2:36:30
    * @param detailId 明细标识
    * @return List<GiftmoneyDetail>
     */
    List<GiftmoneyDetail> selectByDetailId(Integer detailId);
}