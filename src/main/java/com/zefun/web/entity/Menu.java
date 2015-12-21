package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class Menu {
	/** 菜单ID */
	private Integer menuId;

	/** 菜单名称 */
	private String menuName;

	/** 菜单类型 */
	private String menuType;

	/** 菜单链接 */
	private String menuUrl;

	/** 菜单父级ID */
	private Integer menuRefId;

	/** 门店ID */
	private Integer storeId;

	/** @param menuId	菜单ID */
	public void setMenuId(Integer menuId){
		this.menuId = menuId;
	}

	/** @return	菜单ID */
	public Integer getMenuId(){
		return menuId;
	}

	/** @param menuName	菜单名称 */
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}

	/** @return	菜单名称 */
	public String getMenuName(){
		return menuName;
	}

	/** @param menuType	菜单类型 */
	public void setMenuType(String menuType){
		this.menuType = menuType;
	}

	/** @return	菜单类型 */
	public String getMenuType(){
		return menuType;
	}

	/** @param menuUrl	菜单链接 */
	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}

	/** @return	菜单链接 */
	public String getMenuUrl(){
		return menuUrl;
	}

	/** @param menuRefId	菜单父级ID */
	public void setMenuRefId(Integer menuRefId){
		this.menuRefId = menuRefId;
	}

	/** @return	菜单父级ID */
	public Integer getMenuRefId(){
		return menuRefId;
	}

	/** @param storeId	门店ID */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店ID */
	public Integer getStoreId(){
		return storeId;
	}
	
	/**
	 * 
	* @author 洪秋霞
	* @date 2015年8月11日 上午11:57:52
	 */
	public Menu() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	* @author 洪秋霞
	* @date 2015年8月11日 上午11:57:55
	* @param menuName 菜单名称
	* @param menuType 菜单类型
	* @param menuUrl 菜单路径
	* @param menuRefId 菜单refid
	* @param storeId 门店id
	 */
    public Menu(String menuName, String menuType, String menuUrl,
            Integer menuRefId, Integer storeId) {
        super();
        this.menuName = menuName;
        this.menuType = menuType;
        this.menuUrl = menuUrl;
        this.menuRefId = menuRefId;
        this.storeId = storeId;
    }
    
}