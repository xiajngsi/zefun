package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.mapper.AgentRecommendRecordMapper;

/**
 * 渠道推荐记录服务类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 */
@Service
public class AgentRecommendRecordService {

    /**
     * 渠道推荐记录操作
     */
    @Autowired
    private AgentRecommendRecordMapper agentRecommendRecordMapper;

    /**
     * 根据多个门店或渠道id查询推荐记录
     * @author gebing
     * @date 2015年12月4日
     * @param recommendedIds 多个被推荐者id
     * @return 推荐记录
     */
    public List<AgentRecommendRecord> getByRecommendedIds(List<Integer> recommendedIds) {

        return agentRecommendRecordMapper.selectByRecommendedIds(recommendedIds);

    }

}
