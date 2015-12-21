package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.RoleInfo;

/**
 * 角色crud
* @author 高国藩
* @date 2015年9月19日 上午10:54:53
 */
public interface RoleInfoMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年9月19日 上午10:55:06
    * @param roleId 主键
    * @return 状态
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月19日 上午10:55:17
    * @param record 实体
    * @return 状态
     */
    int insert(RoleInfo record);

    /**
     * 新增非空字段
    * @author 高国藩
    * @date 2015年9月19日 上午10:55:31
    * @param record 实体
    * @return 状态
     */
    int insertSelective(RoleInfo record);

    /**
     * 查询
    * @author 高国藩
    * @date 2015年9月19日 上午10:55:47
    * @param roleId 主键
    * @return 实体
     */
    RoleInfo selectByPrimaryKey(Integer roleId);

    /**
     * 更新
    * @author 高国藩
    * @date 2015年9月19日 上午10:56:10
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(RoleInfo record);

    /**
     * 更新
    * @author 高国藩
    * @date 2015年9月19日 上午10:56:29
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(RoleInfo record);
    /**
     * 根据门店查询
    * @author 高国藩
    * @date 2015年10月9日 下午2:22:27
    * @return 角色的集合
     */
    List<RoleInfo> selectAllRoles();
    /**
     * 根据角色名称查询角色标识
    * @author chendb
    * @date 2015年10月19日 下午7:41:36
    * @param roleName 角色名称
    * @return int
     */
    int getRoleInfo(String roleName);
    /**
     * 获取角色标识顺便判断存在不存在
    * @author chendb
    * @date 2015年11月5日 上午9:46:58
    * @param roleName 名称
    * @return RoleInfo
     */
    RoleInfo queryRoleInfo(String roleName);

    /**
     * 开发者为除老板角色外的其他角色赋予权限
    * @author 高国藩
    * @date 2015年12月6日 下午3:19:50
    * @return  角色列表
     */
    List<RoleInfo> selectAllRolesConfigure();
}