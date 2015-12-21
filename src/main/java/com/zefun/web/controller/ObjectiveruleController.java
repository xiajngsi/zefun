package com.zefun.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ObjectiveRuleDto;
import com.zefun.web.service.ObjectiveruleService;

/**
 * 关于目标考核奖惩相关信息
* @author chendb
* @date 2015年9月2日 下午2:38:13
 */
@Controller
public class ObjectiveruleController extends BaseController{
    /**关于目标考核*/
    
    @Autowired
    private ObjectiveruleService objectiveruleService;
    /**
     * 
    * @author chendb
    * @date 2015年10月14日 上午11:34:05
    * @param request request
    * @param response response
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Objectiverule.VIEW_QUERY)
    public ModelAndView employeelevelView(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        return objectiveruleService.queryObjectiverule(params);
    }
    /**
     * 翻页功能
    * @author chendb
    * @date 2015年9月2日 下午3:50:17
    * @param request 页码
    * @param response 页码
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @param search 每页数量
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objectiverule.ACTION_LIST)
    @ResponseBody
    public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, String search){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        params.put("search", search);
        return objectiveruleService.listAction(params, pageNo, pageSize);
    }
    /**
     * 新增目标考核
    * @author chendb
    * @date 2015年9月6日 上午11:38:48
    * @param request 参数
    * @param response 参数
    * @param addData 参数
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objectiverule.ADD_OBJECTIVERULE)
    @ResponseBody
    public BaseDto addObjectiverule(HttpServletRequest request, HttpServletResponse response, String addData){
        JSONObject  jasonObject = JSONObject.fromObject(addData);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        map.put("createTime", DateUtil.getCurTime());
        map.put("lastOperatorId", getUserId(request));
        int results= objectiveruleService.addObjectiverule(map);
        if (results==1){
            return new BaseDto(-1, "该目标考核项已经有相关指标要求");  
        }
        return new BaseDto(0, "新增成功！");
    }
    /**
     * 根据标识id来获取详情
    * @author chendb
    * @date 2015年9月6日 下午2:36:44
    * @param ruleId 标识id
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objectiverule.GET_RULEDETAIL)
    @ResponseBody
    public BaseDto getruledetail(int ruleId){
        ObjectiveRuleDto info= objectiveruleService.getruledetail(ruleId);
        return new BaseDto(0, info);
    }
    /**
     * 修改目标考核信息
    * @author chendb
    * @date 2015年9月6日 下午3:25:36
    * @param request 参数
    * @param response 参数
    * @param addData 参数
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objectiverule.UPDATE_OBJECTIVERULE)
    @ResponseBody
    public BaseDto updateObjectiverule(HttpServletRequest request, HttpServletResponse response, String addData){
        JSONObject  jasonObject = JSONObject.fromObject(addData);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        map.put("createTime", DateUtil.getCurTime());
        map.put("lastOperatorId", getUserId(request));
        int results= objectiveruleService.updateObjectiverule(map);
        if (results==1){
            return new BaseDto(-1, "该目标考核项已经有相关指标要求");  
        }
        return new BaseDto(0, "修改成功！");
    }
    /**
     * 删除
    * @author chendb
    * @date 2015年9月6日 下午4:08:37
    * @param ruleId 参数
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objectiverule.DELETE_OBJECTIVERULE)
    @ResponseBody
    public BaseDto deleteObjectiverule(Integer ruleId){
        objectiveruleService.deleteObjectiverule(ruleId);
        return new BaseDto(0, "删除成功！");
    }
}
