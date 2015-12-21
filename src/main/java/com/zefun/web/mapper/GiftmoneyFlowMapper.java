package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.GiftmoneyFlow;

/**
 * 礼金流水
* @author 王大爷
* @date 2015年9月10日 下午4:51:18
 */
public interface GiftmoneyFlowMapper {
    
    /**
     * 删除
    * @author 王大爷
    * @date 2015年9月10日 下午4:51:27
    * @param flowId 礼金流水标识
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer flowId);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:51:27
    * @param record 礼金流水
    * @return 是否成功
     */
    int insert(GiftmoneyFlow record);

    /**
     * 新增
    * @author 王大爷
    * @date 2015年9月10日 下午4:51:27
    * @param record 礼金流水
    * @return 是否成功
     */
    int insertSelective(GiftmoneyFlow record);

    /**
     * 查询
    * @author 王大爷
    * @date 2015年9月10日 下午4:51:27
    * @param flowId 礼金流水标识
    * @return 是否成功
     */
    GiftmoneyFlow selectByPrimaryKey(Integer flowId);
    
    /**
     * 根据订单明细标识查询
    * @author 王大爷
    * @date 2015年12月15日 下午5:11:55
    * @param detailId 订单明细标识
    * @return GiftmoneyFlow
     */
    GiftmoneyFlow selectByDetailId(Integer detailId);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:51:27
    * @param record 礼金流水
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(GiftmoneyFlow record);

    /**
     * 修改
    * @author 王大爷
    * @date 2015年9月10日 下午4:51:27
    * @param record 礼金流水
    * @return 是否成功
     */
    int updateByPrimaryKey(GiftmoneyFlow record);
    
    /***
     * 根据会员标识查询礼金流水
    * @author 张进军
    * @date Nov 21, 2015 6:37:17 PM
    * @param accountId  会员标识
    * @return   流水记录
     */
    List<GiftmoneyFlow> selectFlowlistByAccountId(int accountId);
}