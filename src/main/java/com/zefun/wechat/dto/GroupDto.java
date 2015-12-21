package com.zefun.wechat.dto;

import java.util.List;

public class GroupDto {
    /**
     * 
     */
	private GroupInfoDto group;
	   /**
     * 
     */
	private List<GroupInfoDto> groups;
	   /**
     * 
     */
	private String openid;
	   /**
     * 
     */
	private String to_groupid;
	/**下面两个为分组发送消息时候的json字符串*/
	private boolean is_to_all;
	private String group_id;

	public boolean isIs_to_all() {
		return is_to_all;
	}

	public void setIs_to_all(boolean is_to_all) {
		this.is_to_all = is_to_all;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(String to_groupid) {
		this.to_groupid = to_groupid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public GroupInfoDto getGroup() {
		return group;
	}

	public void setGroup(GroupInfoDto group) {
		this.group = group;
	}

	public List<GroupInfoDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupInfoDto> groups) {
		this.groups = groups;
	}

	public GroupDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Group [group=" + group + ", groups=" + groups + "]";
	}

}
