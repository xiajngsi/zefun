package com.zefun.web.mapper;

import java.util.List;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.entity.ComboInfo;
/**
 * 套餐信息Mapper
* @author 洪秋霞
* @date 2015年8月11日 上午10:46:43
 */
public interface ComboInfoMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:47:01
    * @param comboId 套餐id
    * @return int
     */
    int deleteByPrimaryKey(Integer comboId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:47:10
    * @param comboInfo 套餐
    * @return int
     */
    int insertSelective(ComboInfo comboInfo);
    
    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:47:45
    * @param comboId 套餐id
    * @return ComboInfo
     */
    ComboInfo selectByPrimaryKey(Integer comboId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:48:03
    * @param comboInfo 套餐
    * @return int
     */
    int updateByPrimaryKeySelective(ComboInfo comboInfo);

    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:48:21
    * @param comboInfo 套餐
    * @return List<ComboInfoDto>
     */
    List<ComboInfo> selectByProperty(ComboInfo comboInfo);
    
    /**
     * 查询部门，套餐列表
    * @author 洪秋霞
    * @date 2015年9月16日 下午2:26:56
    * @param storeId 门店id
    * @return List<DeptInfoDto>
     */
    List<DeptInfoDto> getDetpInfoByCombo(Integer storeId);
    
    /**
     * 根据门店标识、部门标识查询套餐
    * @author 王大爷
    * @date 2015年10月14日 上午9:43:35
    * @param deptId 部门标识
    * @return List<ComboInfo>
     */
    List<ComboInfo> getComboInfo(Integer deptId);
}