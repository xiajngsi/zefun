package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.PaymentOffDto;
import com.zefun.web.entity.MemberComboProject;

/**
 * 会员套餐项目数据操作对象
* @author 张进军
* @date Oct 23, 2015 11:11:27 AM
 */
public interface MemberComboProjectMapper {
    /**
     * 新增会员套餐项目记录
    * @author 张进军
    * @date Oct 23, 2015 11:11:50 AM
    * @param record 会员套餐项目记录
    * @return   0:失败，1:成功
     */
    int insert(MemberComboProject record);

    
    /**
     * 修改会员套餐项目记录
    * @author 张进军
    * @date Oct 23, 2015 11:11:59 AM
    * @param record 会员套餐项目记录
    * @return       0:失败，1:成功
     */
    int updateByPrimaryKey(MemberComboProject record);
    
    /**
     * 插入套餐详情
     * @param map map
     * @return 新增数据条数
     */
    int insertSelfCashierComboDetail(Map<String, Integer> map);
    
    /**
     * 更新会员套餐信息
     * @param map map
     * @return 更新记录数
     */
    int updateSelfCashierCombo(Map<String, Object> map);
    
    /**
     * 增加会员套餐信息
    * @author 王大爷
    * @date 2015年12月15日 下午3:27:51
    * @param map 参数
    * @return 更新记录数
     */
    int updateAddComboNum(Map<String, Object> map);
    
    /**
     * 根据套餐项目明细标识查询套餐标识
    * @author 张进军
    * @date Dec 4, 2015 11:16:39 AM
    * @param detailId  detailId
    * @return   套餐标识
     */
    Integer selectComboIdByDetailId(Integer detailId);
    
    /**
     * 根据会员标识跟项目标识查询套餐优惠列表
    * @author 张进军
    * @date Nov 10, 2015 5:32:07 PM
    * @param map    会员标识、项目标识
    * @return   套餐优惠列表
     */
    List<PaymentOffDto> selectPaymentOffListByMemberIdAndProjectId(Map<String, Integer> map);
}