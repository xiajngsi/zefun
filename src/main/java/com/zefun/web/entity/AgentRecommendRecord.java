package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月25日 PM 17:47:43
 */
public class AgentRecommendRecord {
    /** 代理标识 */
    private Integer agentId;

    /** 门店标识 */
    private Integer recommendedId;

    /** 推荐代理标识 */
    private Integer recommendId;

    /** 1为门店, 2为渠道商 */
    private Integer recommendType;

    /** @param agentId	代理标识 */
    public void setAgentId(Integer agentId){
        this.agentId = agentId;
    }

    /** @return	代理标识 */
    public Integer getAgentId(){
        return agentId;
    }

    /**
     *
     * @author gebing
     * @date 2015年12月15日
     * @return 被推荐者id
     */
    public Integer getRecommendedId() {
        return recommendedId;
    }

    /**
     * @author gebing
     * @date 2015年12月15日
     * @param recommendedId 被推荐者id
     */
    public void setRecommendedId(Integer recommendedId) {
        this.recommendedId = recommendedId;
    }

    /** @param recommendId	推荐代理标识 */
    public void setRecommendId(Integer recommendId){
        this.recommendId = recommendId;
    }

    /** @return	推荐代理标识 */
    public Integer getRecommendId(){
        return recommendId;
    }

    /** @param recommendType	1为门店, 2为渠道商 */
    public void setRecommendType(Integer recommendType){
        this.recommendType = recommendType;
    }

    /** @return	1为门店, 2为渠道商 */
    public Integer getRecommendType(){
        return recommendType;
    }

}
