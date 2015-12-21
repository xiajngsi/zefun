package com.zefun.wechat.dto;

/**
 * 分组范松
* @author 高国藩
* @date 2015年8月11日 下午5:18:47
 */
public class GroupInfoDto {
	private String name;
	private String id;
	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroupInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public GroupInfoDto(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "GroupInfo [name=" + name + ", id=" + id + ", count=" + count
				+ "]";
	}

}
