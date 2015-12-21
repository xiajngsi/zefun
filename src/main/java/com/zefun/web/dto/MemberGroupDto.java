package com.zefun.web.dto;

import java.util.List;

/**
 * 会员分组详情
* @author 高国藩
* @date 2015年9月11日 上午10:23:33
 */
public class MemberGroupDto {
    
    /**筛选器Id*/
    private Integer groupId;
    /**分组名称*/
    private String groupName;
    /**本组人数*/
    private String memberCount;
    /**分组条件*/
    private String groupCondition;
    /**本月新增人数*/
    private String newMember;
    /**创建时间*/
    private String createTime;
    /**会员id集合*/
    private List<Integer> memberIds;
    
    public List<Integer> getMemberIds() {
        return memberIds;
    }
    public void setMemberIds(List<Integer> memberIds) {
        this.memberIds = memberIds;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getMemberCount() {
        return memberCount;
    }
    public void setMemberCount(String memberCount) {
        this.memberCount = memberCount;
    }
    public String getGroupCondition() {
        return groupCondition;
    }
    public void setGroupCondition(String groupCondition) {
        this.groupCondition = groupCondition;
    }
    public String getNewMember() {
        return newMember;
    }
    public void setNewMember(String newMember) {
        this.newMember = newMember;
    }
    
}
