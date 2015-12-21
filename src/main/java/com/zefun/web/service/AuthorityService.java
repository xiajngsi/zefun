package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.web.dto.AuthorityDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AuthorityRequest;
import com.zefun.web.mapper.AuthorityRequestMapper;
import com.zefun.web.mapper.MemberMenuMapper;

/**
 * 权限管理
* @author 高国藩
* @date 2015年11月25日 下午5:53:13
 */
@Service
@Transactional
public class AuthorityService {
    
    /**权限*/
    @Autowired
    private AuthorityRequestMapper authorityRequestMapper;
    
    /**菜单*/
    @Autowired
    private MemberMenuMapper memberMenuMapper;
    
    /**
     * 保存角色权限管理
    * @author 高国藩
    * @date 2015年11月25日 下午5:55:01
    * @param roleId         角色
    * @param authorityNames 名称
    * @param authorityUrls 链接地址
    * @return 状态
     */
    public BaseDto authoritySave(Integer roleId, String[] authorityNames,
            String[] authorityUrls) {
        authorityRequestMapper.deleteByRoleId(roleId);
        List<AuthorityRequest> authorityRequests = new ArrayList<>();
        if (authorityNames==null||authorityNames.length<=0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "成功绑定角色:"+authorityRequests.size());
        }
        for (int i = 0; i < authorityNames.length; i++) {
            AuthorityRequest authorityRequest = new AuthorityRequest();
            authorityRequest.setAuthorityName(authorityNames[i]);
            authorityRequest.setAuthorityUrl(authorityUrls[i]);
            authorityRequest.setRoleId(roleId);
            authorityRequests.add(authorityRequest);
        }
        for (int i = 0; i < authorityRequests.size(); i++) {
            authorityRequestMapper.insertSelective(authorityRequests.get(i));
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "成功绑定角色:"+authorityRequests.size());
    }

    /**
     * 新增权限
    * @author 高国藩
    * @date 2015年12月7日 下午4:59:59
    * @param url        权限链接地址
    * @param name       名称
    * @param id         主键
    * @param type       类型
    * @param oldUrl     原链接
    * @return           状态
     */
    public BaseDto initAuthority(String url, String name, Integer id, Integer type, String oldUrl) {
        if (type == 1){
            AuthorityRequest record = new AuthorityRequest();
            record.setAuthorityName(name);
            record.setAuthorityUrl(url);
            record.setRoleId(100);
            int ok = authorityRequestMapper.insert(record);
            if (ok>0){
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "新增成功");
            }
            else {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "新增失败");
            }
        }
        else if (type == 2){
            AuthorityDto record = new AuthorityDto();
            record.setAuthorityName(name);
            record.setAuthorityUrl(url);
            record.setOldUrl(oldUrl);
            //查询所有菜单中的该接口,将其修改
            Map<String, String> params = new HashMap<>();
            params.put("nUrl", url.substring(1, url.length()));
            params.put("oUrl", oldUrl.substring(1, oldUrl.length()));
            memberMenuMapper.updateFirstMenu(params);
            memberMenuMapper.updateSecontMenu(params);
            int ok = authorityRequestMapper.updateByPrimaryKeySelective(record);
            if (ok>0){
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "修改成功");
            }
            else {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "修改失败");
            }
        }
        else {
            AuthorityRequest authorityRequest = authorityRequestMapper.selectByPrimaryKey(id);
            int ok = authorityRequestMapper.deleteByAuthorityUrl(authorityRequest.getAuthorityUrl());
            if (ok>0){
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
            }
            else {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "删除失败");
            }
        }
    } 
}
