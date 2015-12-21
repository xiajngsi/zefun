package com.zefun.web.dto;

/**
* @author 张进军
* @date Nov 28, 2015 11:38:23 AM 
*/
public class ProjectEvaluateDto {
    /** 评价标识 */
    private Integer id;

    /** 评价人标识 */
    private Integer memberId;

    /** 项目标识 */
    private Integer projectId;

    /** 评价分数 */
    private Integer evaluateRate;

    /** 评价内容 */
    private String comment;

    /** 评价时间 */
    private String time;
    
    /**会员基本信息*/
    private MemberBaseDto memberInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEvaluateRate() {
        return evaluateRate;
    }

    public void setEvaluateRate(Integer evaluateRate) {
        this.evaluateRate = evaluateRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MemberBaseDto getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberBaseDto memberInfo) {
        this.memberInfo = memberInfo;
    }
}
