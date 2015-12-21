package com.zefun.web.entity;

/**
 * 接口权限信息表
* @author 高国藩
* @date 2015年9月19日 上午10:51:15
 */
public class AuthorityRequest {
    
    /**权限ID*/
    private Integer authorityId;
    /**权限名称*/
    private String authorityName;
    /**请求链接*/
    private String authorityUrl;
    /**角色引用*/
    private Integer roleId;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getAuthorityUrl() {
        return authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl == null ? null : authorityUrl.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}