package com.zefun.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AuthorityRequest;
import com.zefun.web.entity.RoleInfo;
import com.zefun.web.mapper.AuthorityRequestMapper;
import com.zefun.web.mapper.RoleInfoMapper;
import com.zefun.web.service.AuthorityService;

/**
 * 角色管理权限配置
* @author 高国藩
* @date 2015年11月25日 上午11:47:18
 */
@Controller
public class AuthorityController extends BaseController{
    /**角色*/
    @Autowired
    private RoleInfoMapper roleInfoMapper;
    /**权限*/
    @Autowired
    private AuthorityRequestMapper authorityRequestMapper;
    /**权限*/
    @Autowired
    private AuthorityService authorityService;
    
    /**
     * 进入角色权限配置页面
    * @author 高国藩
    * @date 2015年11月25日 下午2:29:31
    * @param request 请求
    * @return        页面 
     */
    @RequestMapping(value = Url.Authority.AUTHORITY_VIEW, method = RequestMethod.GET)
    public ModelAndView authorityView(HttpServletRequest request){
        List<AuthorityRequest> authorityRequests = authorityRequestMapper.selectAllRequest(100);
        List<RoleInfo> infos = roleInfoMapper.selectAllRolesConfigure();
        ModelAndView view = new ModelAndView(View.Authority.VIEW);
        view.addObject("infos", infos);
        view.addObject("authorityRequests", authorityRequests);
        return view;
    }
    
    /**
     * 动态查询角色下列的已拥有的权限
    * @author 高国藩
    * @date 2015年11月25日 下午3:57:44
    * @param request     请求
    * @param roleId      角色
    * @return            集合
     */
    @RequestMapping(value = Url.Authority.AUTHORITY_SELECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto authoritySelect(HttpServletRequest request, Integer roleId){
        List<AuthorityRequest> authorityRequests = authorityRequestMapper.selectAllRequest(roleId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, authorityRequests);
    }
    
    /**
     * 新增权限   
    * @author 高国藩
    * @date 2015年12月7日 下午5:02:20
    * @param request    请求
    * @param url        链接
    * @param name       名称
    * @param id         主键
    * @param type       类型
    * @param oldUrl     原链接
    * @return           状态
     */
    @RequestMapping(value = Url.Authority.SAVE_AUTHORITY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveAuthority(HttpServletRequest request, String url, String name, Integer id, Integer type, String oldUrl){
        return authorityService.initAuthority(url, name, id, type, oldUrl);
    }
    
    /**
     * 角色权限绑定
    * @author 高国藩
    * @date 2015年11月25日 下午6:01:29
    * @param request  请求
    * @param roleId   角色
    * @return         状态
     */
    @RequestMapping(value = Url.Authority.AUTHORITY_SAVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto authoritySave(HttpServletRequest request, Integer roleId){
        String[] authorityNames = request.getParameterValues("authorityName");
        String[] authorityUrls = request.getParameterValues("authorityUrl");
        return authorityService.authoritySave(roleId, authorityNames, authorityUrls);
    }

}
