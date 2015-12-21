package com.zefun.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EmployeeLevelDto;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.service.EmployeelevelService;

/**
 * 职位信息
* @author 陈端斌
* @date 2015年8月5日 下午1:34:20 
*
 */
@Controller
public class EmployeelevelController extends BaseController{
    /**
     * 职位信息
     */
	@Autowired
	private EmployeelevelService employeelevelService;


	/**
	 * 新增职位
	* @author chendb
	* @date 2015年8月11日 上午10:27:21
	* @param request 参数
	* @param response 参数
	* @param addData 参数
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.ADD)
	@ResponseBody
	public BaseDto addEmployeelevel(HttpServletRequest request, HttpServletResponse response, String addData){
	    JSONObject  jasonObject = JSONObject.fromObject(addData);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        map.put("createTime", DateUtil.getCurTime());
        map.put("lastOperatorId", getUserId(request));
		int result=employeelevelService.addEmployeelevel(map);
		if (result==1){
			return new BaseDto(-1, "职位名称已经存在");
		}

	    return new BaseDto(0, "新增成功！");
	}
	/**
	 * 修改职位
	* @author chendb
	* @date 2015年8月11日 上午10:33:20
	* @param request 参数
	* @param response 参数
	* @param addData 参数
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.UPDATE)
	@ResponseBody
	public BaseDto updateEmployeelevel(HttpServletRequest request, HttpServletResponse response, String addData){
	    JSONObject  jasonObject = JSONObject.fromObject(addData);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        map.put("createTime", DateUtil.getCurTime());
        map.put("lastOperatorId", getUserId(request));
	   
		
		int result=employeelevelService.updateEmployeelevel(map);
		if (result==1){
			return new BaseDto(-1, "职位名称已经存在");
		}
		return new BaseDto(0, "修改成功！");
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午10:37:59
	* @param request  request
	* @param response response
	* @param levelId 职位标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.DELETE)
	@ResponseBody
	public BaseDto deleteEmployeelevel(HttpServletRequest request, HttpServletResponse response, Integer levelId){
		
		EmployeeLevel employeeLevel=new EmployeeLevel();
		employeeLevel.setStoreId(getStoreId(request));
		employeeLevel.setLevelId(levelId);
		int result =employeelevelService.deleteEmployeelevel(employeeLevel);
		if (result==1){
			return new BaseDto(-1, "职位已被人员引用，请先删除人员！");
		}
		return new BaseDto(0, "删除成功！");
	}
	/**
	 * 下拉框职位获取
	* @author chendb
	* @date 2015年10月14日 上午11:23:17
	* @param request request
	* @param response response
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.QUERTLEVEL)
	@ResponseBody
	public BaseDto queryEmployeelevel(HttpServletRequest request, HttpServletResponse response){
		List<EmployeeLevel> result=new ArrayList<EmployeeLevel>();
		EmployeeLevel employeeLevel=new EmployeeLevel();
		employeeLevel.setStoreId(getStoreId(request));
		result =employeelevelService.queryEmployeelevel(employeeLevel);
		return new BaseDto(0, result);
	}
	/**
	 * 根据岗位获取下的职位
	* @author chendb
	* @date 2015年8月11日 上午10:39:43
	* @param request 岗位标识
	* @param response 岗位标识
	* @param positionId 岗位标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.QUERTLEVELINFO)
    @ResponseBody
	public BaseDto queryEmployeelevel(HttpServletRequest request, HttpServletResponse response, Integer positionId){
		List<EmployeeLevel> result=new ArrayList<EmployeeLevel>();
		EmployeeLevel employeelevel=new EmployeeLevel();
		//根据岗位id去获取以下的相关职位
		employeelevel.setPositionId(positionId);
		employeelevel.setStoreId(getStoreId(request));
		result =employeelevelService.queryEmployeelevel(employeelevel);
		return new BaseDto(0, result);
	}
	/**
	 * 获取职位详情
	* @author chendb
	* @date 2015年8月11日 上午10:40:56
	* @param levelId 职位标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.LEVELDETAIL)
	@ResponseBody
	public BaseDto leveldetail(Integer levelId){
		EmployeeLevelDto result=new EmployeeLevelDto();
	    result =employeelevelService.leveldetail(levelId);
		return new BaseDto(0, result);
	}
	
	/**
	 * 职位列表信息页面
	* @author chendb
	* @date 2015年10月14日 上午11:24:49
	* @param request request
	* @param response response
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.EmployeeLevel.VIEW_QUERY)
	public ModelAndView employeelevelView(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("storeId", getStoreId(request));
		return employeelevelService.queryEmployeelevelinfo(params);
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午10:41:55
	* @param request 页码
	* @param response 页码
	* @param pageNo 页码
	* @param pageSize 每页数量
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.ACTION_LIST)
	@ResponseBody
	public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("storeId", getStoreId(request));
		return employeelevelService.listAction(params, pageNo, pageSize);
	}
	/**
	 * 根据职员标识获取底下员工
	* @author chendb
	* @date 2015年9月9日 上午10:09:03
	* @param levelId 职位标识
	* @param request request
	* @param response response
	* @return BaseDto
	 */
	@RequestMapping(value = Url.EmployeeLevel.GETLEVELEMPLOYEE)
    @ResponseBody
    public BaseDto getlevelemployee(HttpServletRequest request, HttpServletResponse response, Integer levelId){
	    
	    List<EmployeeDto> list =employeelevelService.getlevelemployee(levelId, getStoreId(request));
        return new BaseDto(0, list);
    }
	
	
}
