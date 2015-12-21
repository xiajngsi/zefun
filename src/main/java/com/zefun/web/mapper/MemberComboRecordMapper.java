package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.entity.Page;

/**
 *  会员套餐记录表
* @author 张进军
* @date Oct 23, 2015 11:14:27 AM
 */
public interface MemberComboRecordMapper {
    /**
     * 根据记录标识删除会员套餐记录
    * @author 张进军
    * @date Oct 23, 2015 11:14:37 AM
    * @param recordId   会员套餐记录
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer recordId);

    /**
     * 新增会员套餐记录
    * @author 张进军
    * @date Oct 23, 2015 11:14:51 AM
    * @param record     会员套餐记录
    * @return   0:失败，1:成功
     */
    int insert(MemberComboRecord record);

    /**
     * 根据记录标识查询会员套餐记录
    * @author 张进军
    * @date Oct 23, 2015 11:15:01 AM
    * @param recordId   会员套餐标识
    * @return   会员套餐记录
     */
    MemberComboRecord selectByPrimaryKey(Integer recordId);

    /**
     * 修改会员套餐记录
    * @author 张进军
    * @date Oct 23, 2015 11:15:16 AM
    * @param record     会员套餐记录
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(MemberComboRecord record);

    /**
     * 根据会员标识查询套餐记录
    * @author 张进军
    * @date Oct 24, 2015 11:02:21 AM
    * @param memberId   会员标识
    * @return   套餐记录
     */
    List<MemberComboDto> selectComboListByMemberId(int memberId);

    /**
     * 根据订单明细标识查询
    * @author 王大爷
    * @date 2015年12月15日 下午4:37:47
    * @param detailId 订单明细标识
    * @return MemberComboDto
     */
    MemberComboDto selectComboListByDetailId(int detailId);
    
    
    /**
     * 新增会员套餐
     * @param map map
     * @return 新增记录数
     */
    int insertSelfCashierComboByComboId(Map<String, Integer> map);

    /**
     * 更新会员套餐信息
     * @param map map
     * @return 更新记录数
     */
    int updateOrderComboAmount(Map<String, Integer> map);

    /**
     * 根据套餐ID查询关联信息
    * @author 高国藩
    * @date 2015年11月21日 下午3:08:09
    * @param comboId 套餐ID
    * @return 实体
     */
    List<MemberComboRecord> selectByComboId(Integer comboId);
    
    /**
     * 会员购买的套餐分页查询
    * @author 王大爷
    * @date 2015年12月1日 上午10:29:02
    * @param page 参数
    * @return List<MemberComboDto>
     */
    List<MemberComboDto> selectComboListByMemberIdPage(Page<MemberComboDto> page);

}