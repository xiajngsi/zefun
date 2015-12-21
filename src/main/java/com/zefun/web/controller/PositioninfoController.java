package com.zefun.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zefun.common.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.service.PositioninfoService;


/***
 * 岗位信息
* @author 陈端斌
* @date 2015年8月4日 下午4:05:07 
*
 */
@Controller
public class PositioninfoController extends BaseController{
    /**
     * 岗位接口
     */
	@Autowired
	private PositioninfoService positioninfoService;

	/**
	 * 
	* @author chendb
	* @date 2015年10月14日 上午11:38:48
	* @param request request
	* @param response response
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.Position.VIEW_QUERY)
	public ModelAndView positionView(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("storeId", getStoreId(request));
		return positioninfoService.queryPositioninfo(params);
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午10:43:52
	* @param request 页码
	* @param response 页码
	* @param pageNo 页码
	* @param pageSize 每页数量
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Position.ACTION_LIST)
	@ResponseBody
	public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("storeId", getStoreId(request));
		return positioninfoService.listAction(params, pageNo, pageSize);
	}
	
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午10:44:24
	* @param request 岗位编码
	* @param response 岗位编码
	* @param positionCode 岗位编码
	* @param positionName 岗位名称
	* @param deptId 部门标识
	* @param isDept 是否允许夸部门   
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Position.ADD)
	@ResponseBody
	public BaseDto addPositioninfo(HttpServletRequest request, HttpServletResponse response, 
	        Integer positionCode, String positionName, Integer deptId , Integer isDept){
		//相关参数进行封装
		PositionInfo positioninfo=new PositionInfo();
		positioninfo.setStoreId(getStoreId(request));
		positioninfo.setLastOperatorId(getUserId(request));
		positioninfo.setDeptId(deptId);
		positioninfo.setCreateTime(DateUtil.getCurTime());
		positioninfo.setPositionCode(positionCode);
		positioninfo.setPositionName(positionName);
		positioninfo.setIsDept(isDept);
		
		int result=positioninfoService.addPositioninfo(positioninfo);
		if (result==1){
			return new BaseDto(-3, "岗位编码已经存在");
		}
		if (result==2){
			return new BaseDto(-2, "岗位名称已经存在");
		}
		return new BaseDto(0, "新增成功！");
	}
	/**
	 * 修改
	* @author chendb
	* @date 2015年8月11日 上午10:45:51
	* @param positioninfo 岗位信息
	* @param request 岗位编码
	* @param response 岗位编码
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Position.UPDATE)
	@ResponseBody
	public BaseDto updatePositioninfo(HttpServletRequest request, HttpServletResponse response, 
	        PositionInfo positioninfo){

		int result=positioninfoService.updatePositioninfo(positioninfo);
		if (result==1){
			return new BaseDto(-3, "岗位编码已经存在");
		}
		if (result==2){
			return new BaseDto(-2, "岗位名称已经存在");
		}
		if (result == 3) {
		    return new BaseDto(-4, "岗位已被其他部门轮牌引用");
		}
		return new BaseDto(0, "修改成功！");
	}
	/**
	 * 删除岗位信息
	* @author chendb
	* @date 2015年8月11日 上午10:47:12
	* @param request 岗位标识
	* @param response 岗位标识
	* @param positionId 岗位标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Position.DELETE)
	@ResponseBody
	public BaseDto deleteposition(HttpServletRequest request, HttpServletResponse response, Integer positionId){
		
		PositionInfo positioninfo=new PositionInfo();
		positioninfo.setStoreId(getStoreId(request));
		positioninfo.setPositionId(positionId);
		int result=positioninfoService.deleteposition(positioninfo);
		if (result==1){
			return new BaseDto(-1, "该岗位已被职位引用，请先删除职位");
		}
		return new BaseDto(0, "删除成功！");
	}
	/**
	 * 获取下拉框岗位信息
	* @author chendb
	* @date 2015年8月11日 上午10:47:53
	* @param request bumen标识
	* @param response bumen标识
	* @param deptId bumen标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Position.QUERYPOSITION)
	@ResponseBody
	public BaseDto queryPosition(HttpServletRequest request, HttpServletResponse response, Integer deptId){
		PositionInfo positioninfo=new PositionInfo();
		positioninfo.setStoreId(getStoreId(request));
		positioninfo.setDeptId(deptId);
		List<PositionInfo>list=positioninfoService.queryPosition(positioninfo);
	    return new BaseDto(0, list);
	}
	/**
	 * 岗位详情
	* @author chendb
	* @date 2015年8月11日 上午10:48:12
	* @param request 岗位标识
	* @param response 岗位标识
	* @param positionId 岗位标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Position.POSITIONDETAIL)
	@ResponseBody
	public BaseDto positiondetail(HttpServletRequest request, HttpServletResponse response, Integer positionId){
		PositionInfo positioninfo=new PositionInfo();
		positioninfo.setStoreId(getStoreId(request));
		positioninfo.setPositionId(positionId);
		PositionInfo info=positioninfoService.positiondetail(positioninfo);
	    return new BaseDto(0, info);
	}
	
}
