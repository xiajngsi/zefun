package com.zefun.web.dto;

/**
 * 权限控制
* @author 高国藩
* @date 2015年12月7日 下午5:51:51
 */
public class AuthorityDto {

    /**权限ID*/
    private Integer authorityId;
    /**权限名称*/
    private String authorityName;
    /**请求链接*/
    private String authorityUrl;
    /**原链接*/
    private String oldUrl;
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
        this.authorityName = authorityName;
    }

    public String getAuthorityUrl() {
        return authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl;
    }

    public String getOldUrl() {
        return oldUrl;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
