package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderProjectPageDto;
import com.zefun.web.dto.SumPriceDto;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.Page;

/**
 * 订单明细
* @author 王大爷
* @date 2015年8月19日 下午3:03:57
 */
public interface OrderDetailMapper {
    
    /**
     * 删除订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午2:59:23
    * @param detailId 订单明细id
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer detailId);

    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午3:00:19
    * @param record 订单明细
    * @return 是否成功
     */
    int insert(OrderDetail record);

    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午3:01:02
    * @param record 订单明细
    * @return 是否成功
     */
    int insertSelective(OrderDetail record);

    /**
     * 根据订单明细id查询订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午3:01:06
    * @param detailId 订单明细id
    * @return 订单明细
     */
    OrderDetail selectByPrimaryKey(Integer detailId);

    /**
     * 修改订单明细信息
    * @author 王大爷
    * @date 2015年8月19日 下午3:01:14
    * @param record 订单明细
    * @return 是否成功
     */
    int updateByPrimaryKey(OrderDetail record);
    
    /**
     * 业绩总排行
    * @author 王大爷
    * @date 2015年8月19日 下午4:28:32
    * @param map 员工list
    * @return 业绩总排行
     */
    List<SumPriceDto> selectBySumPriceEmployees(Map<String, Object> map);
    
    /**
     * 算平均（除商品）
    * @author 王大爷
    * @date 2015年8月19日 下午8:46:55
    * @param map 开始时间--结束时间
    * @return 算平均（除商品）
     */
    SumPriceDto selectByMEmployees(Map<String, Object> map);
    
    /**
     * 算平均商品
    * @author 王大爷
    * @date 2015年8月19日 下午8:46:55
    * @param map 开始时间--结束时间
    * @return 算平均商品
     */
    SumPriceDto selectByCEmployees(Map<String, Object> map);
    /**
     * 单个员工的劳动业绩、客单价、指定客比率
    * @author 王大爷
    * @date 2015年8月19日 下午8:24:15
    * @param map 员工id
    * @return 单个员工的劳动业绩、客单价、指定客比率
     */
    SumPriceDto selectByPPriceEmployees(Map<String, Object> map);
    
    /**
     * 单个员工的商品
    * @author 王大爷
    * @date 2015年8月19日 下午8:24:15
    * @param map 员工id
    * @return 单个员工的商品
     */
    SumPriceDto selectByCPriceEmployees(Map<String, Object> map);
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月22日 下午7:50:46
    * @param orderId 订单标识
    * @return List<OrderDetail>
     */
    List<OrderDetail> selectOrderDetail(Integer orderId);
    
    /**
     * 查询商品30天销售量
    * @author 洪秋霞
    * @date 2015年9月18日 上午10:50:46
    * @param orderDetail 订单详情
    * @return List<OrderDetailDto>
     */
    List<OrderDetailDto> selectByGoodsSale(OrderDetail orderDetail);
    
    /**
     * 修改订单明细服务时长
    * @author 王大爷
    * @date 2015年10月20日 下午4:56:16
    * @param shiftMahjongStepId 轮牌步骤标识
    * @return 是否成功
     */
    Integer updateServiceLength(Integer shiftMahjongStepId);
    
    /**
     * 根据明细标识查询明细及步骤
    * @author 王大爷
    * @date 2015年10月24日 上午11:28:48
    * @param detailId 明细标识
    * @return OrderDetailDto
     */
    OrderDetailDto selectByDetailBaseDto(Integer detailId);
    
    /**
     * 查询该明细对应的订单中存在的未完成的项目明细
    * @author 王大爷
    * @date 2015年10月26日 下午3:56:27
    * @param detailId 明细标识
    * @return List<OrderDetail>
     */
    List<OrderDetail> selectByNotOverOrderDetail(Integer detailId);
    
    /**
     * 查询订单中存在的未完成的项目明细
    * @author 王大爷
    * @date 2015年11月11日 下午9:19:59
    * @param orderId 订单明细标识
    * @return List<OrderDetail>
     */
    List<OrderDetail> selectByNotOverByOrderId(Integer orderId);
    
    /**
     * 
    * @author 王大爷
    * @date 2015年11月9日 下午6:40:46
    * @param orderId 订单标识
    * @return List<OrderDetail
     */
    List<OrderDetail> selectOrderId(Integer orderId);
    
    /**
     * 会员对应的项目消费
    * @author 王大爷
    * @date 2015年11月30日 下午3:43:15
    * @param page 参数
    * @return List<OrderProjectPageDto>
     */
    List<OrderProjectPageDto> selectByOrderProjectPage(Page<OrderProjectPageDto> page);
    
    /**
     * 会员对应的商品消费
    * @author 王大爷
    * @date 2015年11月30日 下午6:14:53
    * @param page 参数
    * @return List<OrderProjectPageDto>
     */
    List<OrderProjectPageDto> selectByOrderGoodsPage(Page<OrderProjectPageDto> page);
    
    /**
     * 根据明细标识查询套餐名称
    * @author 王大爷
    * @date 2015年11月30日 下午4:26:08
    * @param detailId 明细标识
    * @return 套餐名称
     */
    String selectComboNameByDetailId(Integer detailId);
    
    /**
     * 根据明细标识查询优惠卷名称
    * @author 王大爷
    * @date 2015年11月30日 下午4:42:30
    * @param detailId 明细标识
    * @return 优惠卷名称
     */
    Map<String, Object> selectCouponNameByDetailId(Integer detailId);
    
    /**
     * 根据项目查询出状态值为1,2的订单信息
    * @author 高国藩
    * @date 2015年12月9日 下午4:54:53
    * @param projectId     项目Id
    * @return              订单列表
     */
    List<OrderDetail> selectHasProjectAndStatus(Integer projectId);
    
    
}