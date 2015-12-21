package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.AuthorityDto;
import com.zefun.web.entity.AuthorityRequest;

/**
 * 接口权限crud
* @author 高国藩
* @date 2015年9月19日 上午10:52:47
 */
public interface AuthorityRequestMapper {
    
    /**
     * 根据Id删除
    * @author 高国藩
    * @date 2015年9月19日 上午10:52:16
    * @param authorityId 权限id
    * @return 状态
     */
    int deleteByPrimaryKey(Integer authorityId);

    /**
     * 新增权限
    * @author 高国藩
    * @date 2015年9月19日 上午10:53:06
    * @param record 实体
    * @return 状态
     */
    int insert(AuthorityRequest record);

    /**
     * 新增非空字段
    * @author 高国藩
    * @date 2015年9月19日 上午10:53:21
    * @param record 实体
    * @return 状态
     */
    int insertSelective(AuthorityRequest record);

    /**
     * 根据主键查询
    * @author 高国藩
    * @date 2015年9月19日 上午10:53:42
    * @param authorityId 查询
    * @return 实体
     */
    AuthorityRequest selectByPrimaryKey(Integer authorityId);

    /**
     * 更新角色
    * @author 高国藩
    * @date 2015年9月19日 上午10:54:12
    * @param record 实体
    * @return 装填
     */
    int updateByPrimaryKeySelective(AuthorityDto record);

    /**
     * 更新
    * @author 高国藩
    * @date 2015年9月19日 上午10:54:25
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(AuthorityRequest record);

    /**
     * 根据角色id查询接口权限链接
    * @author 高国藩
    * @date 2015年9月19日 下午12:55:47
    * @param roleId 角色id
    * @return url
     */
    List<String> selectByUserRoleId(Integer roleId);

    /**
     *  查询角色下的所有权限,作为系统的全部权限
    * @author 高国藩
    * @date 2015年11月25日 下午3:54:08
    * @param roleId 角色ID
    * @return       集合
     */
    List<AuthorityRequest> selectAllRequest(Integer roleId);

    /**
     * 删除角色权限的绑定
    * @author 高国藩
    * @date 2015年11月25日 下午5:59:56
    * @param roleId 角色
    * @return 影响行数
     */
    int deleteByRoleId(Integer roleId);

    /**
     * 删除权限
    * @author 高国藩
    * @date 2015年12月7日 下午5:49:48
    * @param authorityUrl   权限链接
    * @return               影响航速
     */
    int deleteByAuthorityUrl(String authorityUrl);
}