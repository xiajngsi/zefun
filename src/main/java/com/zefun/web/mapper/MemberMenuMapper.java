package com.zefun.web.mapper;

import java.util.Map;

import com.zefun.web.entity.MemberMenu;

/**
 * 菜单权限表
* @author 高国藩
* @date 2015年10月12日 上午11:16:19
 */
public interface MemberMenuMapper {
    
    /**
     * 根据角色查询权限
    * @author 高国藩
    * @date 2015年10月12日 上午11:16:44
    * @param roleId 角色ID
    * @return 菜单信息
     */
    MemberMenu selectMenuByRoleId(Integer roleId);

    /**
     * 修改一级菜单
    * @author 高国藩
    * @date 2015年12月8日 下午8:50:22
    * @param params   接口地址 (nUrl:现接口地址,oUrl:原接口地址)
     */
    void updateFirstMenu(Map<String, String> params);

    /**
     * 修改2级菜单
    * @author 高国藩
    * @date 2015年12月8日 下午8:50:22
    * @param params   接口地址
     */
    void updateSecontMenu(Map<String, String> params);

}