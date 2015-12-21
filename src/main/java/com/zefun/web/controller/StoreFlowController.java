package com.zefun.web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.StoreFlow;
import com.zefun.web.service.StoreFlowService;

/**
 * 收银记账
 * @author laowang
 * @date Jun 30, 2015 4:42:19 PM 
 */
@Controller
public class StoreFlowController extends BaseController{
	
	/**
	 * 开支记账Service
	 */
	@Autowired
	private StoreFlowService storeFlowService;
	
	/**
	 * 新增开支记账
	* @author 王大爷
	* @date Jul 2, 2015 2:58:04 PM
	* @param response 请求
	* @param request 返回
	* @param storeFlow 开支记账
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.ADD_STOREFLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addStoreFlow(HttpServletRequest request, HttpServletResponse response, StoreFlow storeFlow){
	    
		storeFlow.setStoreId(getStoreId(request));
        storeFlow.setFlowTime(DateUtil.getCurDate());
        storeFlow.setOperatorId(85);
        storeFlow.setIsDeleted(0);
        return storeFlowService.addStoreFlow(storeFlow, getUserId(request));
	}
	
	/**
	 * 修改开支记账
	* @author 王大爷
	* @date 2015年8月11日 上午10:50:38
	* @param request 返回
	* @param response 请求
	* @param storeFlow 开支记账
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.UPDATE_STOREFLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto updateStoreFlow(HttpServletRequest request, HttpServletResponse response, StoreFlow storeFlow){
		return storeFlowService.updateStoreFlow(storeFlow);
	}
	
	/**
	 * 初始化开卡记账界面
     * 默认返回该门店最前面10条数据
	* @author 王大爷
	* @date 2015年8月11日 上午10:51:46
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZESTOREFLOW, method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView initializeStoreFlow(HttpServletRequest request, HttpServletResponse response){
	    
        return storeFlowService.initializeStoreFlow(getStoreId(request), Integer.parseInt((DateUtil.getMinMonthDateStr()).replace("-", "")), 
                Integer.parseInt((DateUtil.getMaxMonthDateStr()).replace("-", "")));
	}
	
	/**
	 * 动态生成项目类别
	* @author 王大爷
	* @date 2015年8月11日 上午10:52:59
	* @param request 返回
	* @param response 请求
	* @param codeName 数据字典名称
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.TREND_CODELIBRARY, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto trendCodeLibrary(HttpServletRequest request, HttpServletResponse response, String codeName){
		return storeFlowService.trendCodeLibrary(codeName);
	}
	
	/**
	 * 数据导入
	* @author 王大爷
	* @date 2015年8月6日 下午1:52:41
	* @param file fileupload对象
	* @param request 返回
	* @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.READEXCLE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto readExcle(@RequestParam("filevalue") MultipartFile file,
		      HttpServletRequest request, HttpServletResponse response){
		String temp = request.getSession().getServletContext()
		        .getRealPath(File.separator)
		        + "temp"; // 临时目录
		return storeFlowService.insertStoreFlowList(file, temp, getStoreId(request));
	}
	
	/**
	 * 导出excle
	* @author 王大爷
	* @date 2015年8月11日 上午11:00:33
	* @param request 返回
	* @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.DOWNLOAD_EXCLE, method = RequestMethod.GET)
	@ResponseBody
	public BaseDto downloadExcle(HttpServletRequest request, HttpServletResponse response){
		
		return storeFlowService.downloadExcle(response);
	}
	
	/**
	 * 分页查询某个门店的开卡记账界面
	* @author laowang
	* @date Aug 5, 2015 7:58:53 PM
	* @param pageNo		页码
	* @param pageSize	每页显示数
	* @param beginTime 开始时间
	* @param endTime 结束时间
	* @param request 返回
    * @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.STOREFLOW_LIST, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto storeFlowList(int pageNo, int pageSize, Integer beginTime, Integer endTime, HttpServletRequest request, 
	        HttpServletResponse response){
		return storeFlowService.storeFlowList(getStoreId(request), pageNo, pageSize, beginTime, endTime);
	}
	
	/**
	 * 删除开支记账
	* @author laowang
	* @date 2015年8月6日 下午8:45:22
	* @param flowId 开支记账ID
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.DELETE_STOREFLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteStoreFlow(Integer flowId){
		return storeFlowService.deleteStoreFlow(flowId);
	}
	
}
