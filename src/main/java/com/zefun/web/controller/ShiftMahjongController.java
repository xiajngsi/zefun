package com.zefun.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.ShiftMahjong;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.service.ShiftMahjongService;

/**
 * 收银记账
 * @author 王大爷
 * @date Jun 30, 2015 4:42:19 PM 
 */
@Controller
public class ShiftMahjongController extends BaseController{
	
	/**
	 * 轮牌信息Service
	 */
	@Autowired
	private ShiftMahjongService shiftMahjongService;
	
	/**
	 * 初始化轮职排班界面
	* @author 王大爷
	* @date 2015年8月11日 上午11:10:16
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZE_SHIFT_MAHJONG, method = RequestMethod.GET)
	public ModelAndView initializeShiftMahjong(HttpServletRequest request, HttpServletResponse response){
		return shiftMahjongService.initializeShiftMahjong(getStoreId(request));
	}
	
	/**
	 * 新增或修改轮牌信息
	* @author 王大爷
	* @date 2015年9月8日 下午3:44:11
	* @param request 返回
	* @param response 请求
	* @param shiftMahjong 轮牌信息
	* @param positionIdListStr 岗位标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.ADDUPDATE_SHIFTMAHJONG, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addUpdateShiftMahjong(HttpServletRequest request, HttpServletResponse response, 
	        ShiftMahjong shiftMahjong, String positionIdListStr){
	    
	    List<Integer> positionIds = new ArrayList<Integer>();
        List<Integer> isPunchCards = new ArrayList<Integer>();
        if (positionIdListStr != "") {
            String[] positionIdList = positionIdListStr.split(",");
            for (int i = 0; i < positionIdList.length; i++) {
                String positionIdStr = positionIdList[i];
                positionIds.add(Integer.parseInt(positionIdStr.split(":")[0]));
                isPunchCards.add(Integer.parseInt(positionIdStr.split(":")[1]));
            }
        }
	    return shiftMahjongService.addUpdateShiftMahjong(shiftMahjong, positionIds, isPunchCards, getStoreId(request), getUserId(request));
	}
	
	/**
     * 根据部门查询轮牌信息
    * @author 王大爷
    * @date 2015年9月16日 下午4:15:28
    * @param request 返回
    * @param response 请求
    * @param deptId 部门标识
    * @return BaseDto
     */
	@RequestMapping(value = Url.KeepAccounts.SELECT_SHIFTMAHJONG, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto selectshiftMahjong(HttpServletRequest request, HttpServletResponse response, Integer deptId){
	    return shiftMahjongService.selectshiftMahjong(deptId);
	}
	
	/**
	 * 删除轮牌信息
	* @author 王大爷
	* @date 2015年9月8日 下午4:24:29
	* @param shiftMahjongId 轮牌信息标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.DELETE_SHIFTMAHJONG, method = RequestMethod.POST)
    @ResponseBody
	public BaseDto deleteShiftMahjong(Integer shiftMahjongId){
	    return shiftMahjongService.deleteShiftMahjong(shiftMahjongId);
	}
	
	/**
     * 修改员工状态
    * @author 王大爷
    * @date 2015年9月16日 下午8:25:29
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployeeId 轮牌员工信息标识
    * @param state 状态
    * @return BaseDto
     */
	@RequestMapping(value = Url.KeepAccounts.UPDATE_STATE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto updateState(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongEmployeeId, Integer state){
	    Integer storeId = getStoreId(request);
	    return shiftMahjongService.updateState(shiftMahjongEmployeeId, state, storeId);
	}
	
/*	*//**
	 * 设置轮牌
	* @author 王大爷
	* @date 2015年8月11日 上午11:11:14
	* @param request 返回
	* @param response 请求
	* @param shiftMahjongId 轮牌信息标识
	* @param shiftMahjongUp 上牌规则（1：考勤轮牌、2：持续轮牌）
	* @param shiftMahjongRule 轮牌规则（1：指定不轮牌、2：指定某只后轮牌）
	* @param appointNumber 轮牌指定人数
	* @param levelId 员工级别
	* @param nature 轮牌性质
	* @return BaseDto
	 *//*
	@RequestMapping(value = Url.KeepAccounts.SET_SHIFTMAHJONG, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto setShiftMahjong(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongId, 
	        Integer shiftMahjongUp, Integer shiftMahjongRule, Integer appointNumber, String levelId, Integer nature){
		return shiftMahjongService.setShiftMahjong(shiftMahjongId, shiftMahjongUp, shiftMahjongRule, appointNumber, 
		        levelId, getStoreId(request), nature);
	}*/
	
	/**
	 * 根据轮牌信息标识查询该轮牌对应的岗位信息
	* @author laowang
	* @date 2015年8月10日 下午5:18:09
	* @param request 返回
	* @param response 请求
	* @param shiftMahjongId 轮牌信息ID
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZE_MODEL, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto initializeModel(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongId){
		return shiftMahjongService.initializeModel(shiftMahjongId);
	}
	
	/**
	 * 轮牌上移
	* @author 王大爷
	* @date 2015年8月13日 上午11:48:27
	* @param request 返回
	* @param response 请求
	* @param shiftMahjongEmployee 轮牌员工信息
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.UPWARD_IMG, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto upwardIMG(HttpServletRequest request, HttpServletResponse response, ShiftMahjongEmployee shiftMahjongEmployee){
	    return shiftMahjongService.upwardIMG(shiftMahjongEmployee);
	}
	
	/**
     * 轮牌下移
    * @author 王大爷
    * @date 2015年8月13日 上午11:48:27
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongEmployee 轮牌员工信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.NEXT_IMG, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto nextIMG(HttpServletRequest request, HttpServletResponse response, ShiftMahjongEmployee shiftMahjongEmployee){
        return shiftMahjongService.nextIMG(shiftMahjongEmployee);
    }
}
