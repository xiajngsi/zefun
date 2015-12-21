package com.zefun.web.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.IncomeDto;
import com.zefun.web.entity.AgentAccount;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.entity.WechatAgent;
import com.zefun.web.mapper.AgentAccountFlowMapper;
import com.zefun.web.mapper.AgentAccountMapper;
import com.zefun.web.mapper.AgentInfoMapper;
import com.zefun.web.mapper.AgentRecommendRecordMapper;
import com.zefun.web.mapper.WechatAgentMapper;

/**
 * 渠道相关信息操作类
 *
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 *
 */
@Service
public class AgentInfoService {

    /**
     * 渠道与微信openid的对应关系操作
     */
    @Autowired
    private WechatAgentMapper wechatAgentMapper;

    /**
     * 渠道信息操作
     */
    @Autowired
    private AgentInfoMapper agentInfoMapper;

    /**
     * 渠道的推荐记录操作
     */
    @Autowired
    private AgentRecommendRecordMapper agentRecommendRecordMapper;

    /**
     * 渠道账号操作
     */
    @Autowired
    private AgentAccountMapper agentAccountMapper;

    /**
     * 渠道流水操作
     */
    @Autowired
    private AgentAccountFlowMapper agentAccountFlowMapper;

    /**
     * 根据openid获取渠道信息
     * @author gebing
     * @date 2015年12月4日
     * @param openId 微信openid
     * @return 渠道信息
     */
    public AgentInfo getByOpenId(String openId) {
        WechatAgent wechatAgent = wechatAgentMapper.selectByPrimaryKey(openId);
        if (wechatAgent == null) {
            return null;
        }
        return agentInfoMapper.selectByPrimaryKey(wechatAgent.getAgentId());
    }

    /**
     * 根据身份与城市获取渠道信息
     * @author gebing
     * @date 2015年12月4日
     * @param province 省份
     * @param city 城市
     * @return 渠道信息
     */
    public AgentInfo getByRegion(String province, String city) {
        Map<String, String> region = new HashMap<String, String>();
        region.put("province", province);
        region.put("city", city);
        return agentInfoMapper.selectByRegion(region);
    }

    /**
     * 记录渠道推荐信息
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道标识 (如新增渠道，该值传null)
     * @param recommendedId 被推荐者标识
     * @param recomendId 推荐人id
     * @param type  推荐的类型, 1为门店, 2为渠道商
     */
    public void recommend(Integer agentId, Integer recommendedId,
            Integer recomendId, int type) {

        AgentRecommendRecord record = new AgentRecommendRecord();
        record.setAgentId(agentId);
        record.setRecommendedId(recommendedId);
        record.setRecommendId(recomendId);
        record.setRecommendType(type);

        agentRecommendRecordMapper.insert(record);
    }

    /**
     * 根据多个渠道的id查询多个渠道的信息
     *
     * @author gebing
     * @date 2015年12月4日
     * @param agentIds
     *            多个渠道的id
     * @return 多个渠道的信息
     */
    public List<AgentInfo> getByAgentIds(Integer... agentIds) {
        return agentInfoMapper.selectByAgentIds(Arrays.asList(agentIds));
    }

    /**
     * 根据渠道的id查询渠道的信息
     *
     * @author gebing
     * @date 2015年12月4日
     * @param agentId
     *            渠道的id
     * @return 渠道的信息
     */
    public AgentInfo getByAgentId(Integer agentId) {
        return agentInfoMapper.selectByPrimaryKey(agentId);
    }

    /**
     * 添加渠道申请信息, 如果申请人申请的身份与城市已存在渠道商, 则申请失败
     * @author gebing
     * @date 2015年12月4日
     * @param code 推荐人openid
     * @param name 申请人姓名
     * @param phone 申请人手机号
     * @param agentType 申请的渠道类型
     * @param company 公司/商号名称
     * @param province 申请的省份
     * @param city 申请的城市
     * @param openId 申请人的openid
     * @return 申请结果
     */
    @Transactional
    public BaseDto addAgentApplyInfo(String code, String name, String phone,
            Integer agentType, String company, String province, String city,
            String openId) {

        if (this.getByRegion(province, city) != null) {
            return new BaseDto(1, "该城市已存在代理商");
        }

        AgentInfo agentInfo = new AgentInfo();
        agentInfo.setCity(city);
        agentInfo.setCompanyName(company);
        agentInfo.setCompanyType(agentType);
        agentInfo.setCreateTime(DateUtil.getCurTime());
        agentInfo.setUpdateTime(DateUtil.getCurTime());
        agentInfo.setName(name);
        agentInfo.setPhone(phone);
        agentInfo.setProvince(province);

        agentInfoMapper.insert(agentInfo);
        Integer agentId = agentInfo.getAgentId();

        WechatAgent wechatAgent = new WechatAgent();
        wechatAgent.setAgentId(agentId);
        wechatAgent.setOpenId(openId);

        wechatAgentMapper.insert(wechatAgent);

        AgentAccount agentAccount = new AgentAccount();
        agentAccount.setAgentId(agentId);
        agentAccountMapper.insert(agentAccount);

        Integer recomendId = null;
        if (StringUtils.isNotBlank(code)) { // 推荐, 根据openid查询渠道商, 有则归到该渠道商
            AgentInfo recommendAgentInfo = this.getByOpenId(code);
            if (recommendAgentInfo != null) {
                recomendId = recommendAgentInfo.getAgentId();
                // 查询选择的城市有没有渠道商, 有则归到该渠道商
//        AgentInfo regionAgentInfo = this.getByRegion(province, city);
//        if (regionAgentInfo != null && regionAgentInfo.getAgentId() == agentId) {
//            regionAgentInfo = null;
//        }
//        int regionAgentId = App.System.DEFAULT_RECOMMEND_AGENT_ID;
//        if (regionAgentInfo != null) {
//            regionAgentId = regionAgentInfo.getAgentId();
//        }

                this.recommend(null, agentId, recomendId, 2);
            }
        }

        return new BaseDto(0, "申请成功");
    }

    /**
     * 根据渠道商id查询渠道商账号
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道商id
     * @return 渠道商账号信息
     */
    public AgentAccount getAccountByAgentId(Integer agentId) {
        return agentAccountMapper.selectByPrimaryKey(agentId);

    }

    /**
     * 根据渠道商id获取他自己发展的门店或渠道
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道商id
     * @param type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    public List<AgentRecommendRecord> getSelfRecommendsByType(Integer agentId,
            int type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("agentId", agentId);
        params.put("type", type);
        return agentRecommendRecordMapper.selectSelfRecommendsByType(params);
    }

    /**
     * 根据渠道商id查询其他渠道推荐给自己的门店活渠道
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道商id
     * @param type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    public List<AgentRecommendRecord> getOtherRecommendsByType(Integer agentId,
            int type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("agentId", agentId);
        params.put("type", type);
        return agentRecommendRecordMapper.selectOtherRecommendsByType(params);
    }

    /**
     * 根据渠道商id获取属于他的推荐记录, 不论是自己发展的还是别人推荐的
     * @author gebing
     * @date 2015年12月4日
     * @param agentId  渠道商id
     * @param type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    public List<AgentRecommendRecord> getRecommendsByType(Integer agentId,
            int type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("agentId", agentId);
        params.put("type", type);
        return agentRecommendRecordMapper.selectRecommendsByType(params);
    }

    /**
     * 根据渠道商id查询自己推荐给别人的推荐记录
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道商id
     * @param type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    public List<AgentRecommendRecord> getMyRecommendsByType(Integer agentId,
            int type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("agentId", agentId);
        params.put("type", type);
        return agentRecommendRecordMapper.selectMyRecommendsByType(params);
    }

    /**
     * 根据多个渠道商的id查询渠道商账号
     * @author gebing
     * @date 2015年12月4日
     * @param agentIds 多个渠道商id
     * @return 多个渠道商账号
     */
    public List<AgentAccount> getAccountByAgentIds(List<Integer> agentIds) {
        return agentAccountMapper.selectAccountByAgentIds(agentIds);
    }

    /**
     * 根据渠道商id查询其他渠道推荐给自己的门店活渠道
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道商id
     * @param type 推荐的类型, 门店或渠道
     * @return 推荐结果
     */
    public List<AgentRecommendRecord> getRecommendsToMeByType(Integer agentId,
            int type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("agentId", agentId);
        params.put("type", type);
        return agentRecommendRecordMapper.selectRecommendsToMeByType(params);
    }

    /**
     * 根据渠道商的id查询指定时间内的收益记录
     * @author gebing
     * @date 2015年12月4日
     * @param agentId 渠道商id
     * @param begin 开始时间
     * @param end 结束时间
     * @return 收益记录
     */
    public List<IncomeDto> getMonthIncomeByTime(Integer agentId, String begin,
            String end) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("agentId", agentId);
        params.put("begin", begin);
        params.put("end", end);
        return agentAccountFlowMapper.selectMonthIncomeByTime(params);
    }

    /**
     * 根据agentId查询出其推荐的渠道
     * @author gebing
     * @date 2015年12月15日
     * @param agentId 渠道推荐者的id
     * @return 推荐结果
     */
    public List<AgentRecommendRecord> getMyRecommendAgents(Integer agentId) {
        return agentRecommendRecordMapper.selectMyRecommendAgents(agentId);
    }

}
