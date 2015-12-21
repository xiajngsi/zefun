package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.RechargeSetting;

/**
 * 充值配置操作类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年12月4日
 */
public interface RechargeSettingMapper {

    /**
     * 根据id删除配置
     * @author gebing
     * @date 2015年12月4日
     * @param id 配置id
     * @return 1成功, 0没有对应记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入配置
     * @author gebing
     * @date 2015年12月4日
     * @param record 配置信息
     * @return 1成功, 0失败
     */
    int insert(RechargeSetting record);

    /**
     * 插入配置
     * @author gebing
     * @date 2015年12月4日
     * @param record 配置信息
     * @return 1成功, 0失败
     */
    int insertSelective(RechargeSetting record);

    /**
     * 根据配置id查询配置
     * @author gebing
     * @date 2015年12月4日
     * @param id 配置id
     * @return 充值配置
     */
    RechargeSetting selectByPrimaryKey(Integer id);

    /**
     * 根据配置id更新配置信息
     * @author gebing
     * @date 2015年12月4日
     * @param record 待更新配置信息
     * @return 1成功, 0无影响的记录
     */
    int updateByPrimaryKeySelective(RechargeSetting record);

    /**
     * 根据配置id更新配置信息
     * @author gebing
     * @date 2015年12月4日
     * @param record 待更新配置信息
     * @return 1成功, 0无影响的记录
     */
    int updateByPrimaryKeyWithBLOBs(RechargeSetting record);

    /**
     * 根据配置id更新配置信息
     * @author gebing
     * @date 2015年12月4日
     * @param record 待更新配置信息
     * @return 1成功, 0无影响的记录
     */
    int updateByPrimaryKey(RechargeSetting record);

    /**
     * 根据type字段查询充值配置
     * @author gebing
     * @date 2015年12月4日
     * @param type 系统续费, 短信充值等
     * @return 充值配置
     */
    List<RechargeSetting> selectByType(int type);
}
