package com.zefun.web.entity;

/**
 * 菜单权限表
* @author 高国藩
* @date 2015年10月12日 上午11:15:17
 */
public class MemberMenu {
    
    /**主键ID*/
    private Integer menuId;
    /**角色ID*/
    private Integer roleId;
    /**一级菜单*/
    private String firstMenu;
    /**二级菜单*/
    private String secontMenu;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFirstMenu() {
        return firstMenu;
    }

    public void setFirstMenu(String firstMenu) {
        this.firstMenu = firstMenu;
    }

    public String getSecontMenu() {
        return secontMenu;
    }

    public void setSecontMenu(String secontMenu) {
        this.secontMenu = secontMenu;
    }
    
    
}
