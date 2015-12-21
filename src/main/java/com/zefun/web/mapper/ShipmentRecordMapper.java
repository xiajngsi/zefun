package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ShipmentRecordDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShipmentRecord;

/**
 * 出货记录
* @author 高国藩
* @date 2015年11月14日 上午11:04:47
 */
public interface ShipmentRecordMapper {

    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param recordId id
    * @return 影响行数
     */
    int deleteByPrimaryKey(Integer recordId);

    /**
     * 插入
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param record 实体
    * @return 影响行数
     */
    int insert(ShipmentRecord record);

    /**
     * 悟空新增
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param record 实体
    * @return 返回影响行数
     */
    int insertSelective(ShipmentRecord record);

    /**
     * 查询
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param recordId id
    * @return 实体
     */
    ShipmentRecord selectByPrimaryKey(Integer recordId);

    /**
     * 修改
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKeySelective(ShipmentRecord record);

    /**
     * 修改
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKey(ShipmentRecord record);

    /**
     * 根据实体查询
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param shipmentRecord 实体
    * @return 返回集合
     */
    List<ShipmentRecord> selectByEntity(ShipmentRecord shipmentRecord);

    /**
     * 分页查询
    * @author 高国藩
    * @date 2015年11月14日 上午11:04:57
    * @param page 分页
    * @return 集合
     */
    List<ShipmentRecordDto> selectByPage(Page<ShipmentRecordDto> page);
}