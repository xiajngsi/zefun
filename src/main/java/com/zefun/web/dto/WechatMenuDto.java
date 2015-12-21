package com.zefun.web.dto;

import java.util.List;

/**
 * 菜单Dto，替换了以前的Menu
* @author 高国藩
* @date 2015年8月10日 下午11:51:07
 */
public class WechatMenuDto {
    /** 菜单ID*/
    private Integer menuId;
    /** 菜单名称*/
    private String menuName;
    /** 菜单类型*/
    private String menuType;
    /** 菜单链接*/
    private String menuUrl;
    /** 上级菜单*/
    private Integer menuRefId;
    /** 门店ID*/
    private Integer storeId;
    /** 子菜单*/
    private List<WechatMenuDto> menus;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuRefId() {
        return menuRefId;
    }

    public void setMenuRefId(Integer menuRefId) {
        this.menuRefId = menuRefId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public List<WechatMenuDto> getMenus() {
        return menus;
    }

    public void setMenus(List<WechatMenuDto> menus) {
        this.menus = menus;
    }
    
    /**
     * 无惨
    * @author 高国藩
    * @date 2015年9月20日 下午4:50:38
     */
    public WechatMenuDto() {
        // TODO Auto-generated constructor stub
    }
    
}
