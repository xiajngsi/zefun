package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.PaymentOffDto;
import com.zefun.web.entity.MemberCoupon;

/**
 * 优惠券用户关联
* @author 高国藩
* @date 2015年9月15日 上午11:16:58
 */
public interface MemberCouponMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年9月15日 上午11:17:13
    * @param relevanceId 主键
    * @return 影响行数
     */
    int deleteByPrimaryKey(Integer relevanceId);

    /**
     * 插入
    * @author 高国藩
    * @date 2015年9月15日 上午11:18:49
    * @param record 实体
    * @return 影响行数
     */
    int insert(MemberCoupon record);

    /**
     * 查询
    * @author 高国藩
    * @date 2015年9月15日 上午11:19:28
    * @param relevanceId 主键
    * @return 单个实体
     */
    MemberCoupon selectByPrimaryKey(Integer relevanceId);

    /**
     * 所有字段更新
    * @author 高国藩
    * @date 2015年9月15日 上午11:19:28
    * @param record 主键
    * @return 单个实体
     */
    int updateByPrimaryKey(MemberCoupon record);

    /**
     * 插入一个集合
    * @author 高国藩
    * @date 2015年9月15日 下午2:19:14
    * @param coupons 集合
    * @return 插入条数
     */
    int insertList(List<MemberCoupon> coupons);
    
    /**
     * 使用优惠券后更新优惠券状态
     * @param relevanceIds 集合
     * @return 更新条数
     */
    int updateUsedCouponByRelevanceIds(List<Integer> relevanceIds);
    
    /**
     * 查询会员对应项目可使用的优惠券
    * @author 张进军
    * @date Nov 10, 2015 8:46:02 PM
    * @param map    会员标识、项目/商品标识、优惠券类型(1：项目，2：商品)
    * @return   优惠券列表
     */
    List<PaymentOffDto> selectPaymentCouponListByMemberIdAndProjectId(Map<String, Integer> map);
    
    /**
     * 根据会员标识、优惠券标识查询会员可用的优惠券关联标识
    * @author 张进军
    * @date Nov 11, 2015 9:43:59 PM
    * @param map    会员标识、优惠券标识
    * @return   会员可用的优惠券关联标识
     */
    int selectLeftCouponByMemberIdAndCouponId(Map<String, Integer> map);
}