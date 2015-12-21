package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.entity.RechargeSetting;
import com.zefun.web.mapper.RechargeSettingMapper;

/**
 * 充值配置服务类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月28日
 */
@Service
public class RechargeSettingService {

    /**
     * 充值配置操作
     */
    @Autowired
    private RechargeSettingMapper rechargeSettingMapper;

    /**
     * 根据type字段查询充值配置
     * @author gebing
     * @date 2015年12月4日
     * @param type 系统续费, 短信充值等
     * @return 充值配置
     */
    public List<RechargeSetting> getByType(int type) {
        return rechargeSettingMapper.selectByType(type);
    }

    /**
     * 根据配置id查询充值配置
     * @author gebing
     * @date 2015年12月4日
     * @param id 配置id
     * @return 充值配置
     */
    public RechargeSetting getById(Integer id) {
        return rechargeSettingMapper.selectByPrimaryKey(id);
    }

}
