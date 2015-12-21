package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ComboInfoProjectCommissionDto;
import com.zefun.web.dto.ComboProjectCommissionDto;
import com.zefun.web.entity.ComboProjectCommission;
/**
 * 套餐项目提成
* @author 洪秋霞
* @date 2015年9月23日 下午4:44:18
 */
public interface ComboProjectCommissionMapper {
    /**
     * 删除套餐项目提成
    * @author 洪秋霞
    * @date 2015年9月23日 下午4:44:36
    * @param commissionId 套餐项目提成Id
    * @return int
     */
    int deleteByPrimaryKey(Integer commissionId);

    /**
     * 新增套餐项目提成
    * @author 洪秋霞
    * @date 2015年9月23日 下午4:45:05
    * @param comboProjectCommission 套餐项目提成
    * @return int
     */
    int insertSelective(ComboProjectCommission comboProjectCommission);

    /**
     * 根据Id查询套餐项目提成
    * @author 洪秋霞
    * @date 2015年9月23日 下午4:45:20
    * @param commissionId 套餐项目提成Id
    * @return ComboProjectCommission
     */
    ComboProjectCommission selectByPrimaryKey(Integer commissionId);

    /**
     * 更新套餐项目提成
    * @author 洪秋霞
    * @date 2015年9月23日 下午4:45:40
    * @param comboProjectCommission 套餐项目提成
    * @return int
     */
    int updateByPrimaryKeySelective(ComboProjectCommission comboProjectCommission);

    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年9月24日 上午10:29:14
    * @param comboProjectCommission 套餐项目提成
    * @return List<ComboProjectCommission>
     */
    List<ComboProjectCommissionDto> selectByProperty(ComboProjectCommission comboProjectCommission);
    
    /**
     * 批量添加
    * @author 洪秋霞
    * @date 2015年9月28日 下午2:01:29
    * @param comboProjectCommissionList 套餐项目提成
    * @return int
     */
    int insertBatch(List<ComboProjectCommission> comboProjectCommissionList);
    
    /**
     * 批量更新
    * @author 洪秋霞
    * @date 2015年9月28日 下午2:01:29
    * @param comboProjectCommissionList 套餐项目提成
    * @return int
     */
    int updateBatch(List<ComboProjectCommissionDto> comboProjectCommissionList);

    /**
     * 根据套餐将其内容删除
    * @author 高国藩
    * @date 2015年10月20日 下午5:42:02
    * @param comboId 套餐id
    * @return int
     */
    int deleteByComboId(Integer comboId);

    /**
     * 查询单个套餐
    * @author 高国藩
    * @date 2015年10月20日 下午8:32:21
    * @param comboId 套餐ID
    * @return 集合
     */
    List<ComboInfoProjectCommissionDto> selectByCommissionId(Integer comboId);
}