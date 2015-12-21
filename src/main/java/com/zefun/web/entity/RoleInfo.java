package com.zefun.web.entity;

/**
 * 角色信息表
* @author 高国藩
* @date 2015年9月19日 上午10:50:41
 */
public class RoleInfo {
    
    /**角色ID*/
    private Integer roleId;
    /**角色名称*/
    private String roleName;
    /**排序*/
    private Integer roleSort;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }
}