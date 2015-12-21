package com.zefun.web.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.zefun.web.dto.ObjectiveDto;
import com.zefun.web.service.ObjectiveService;

/**
 * 人员目标（月）
* @author chendb
* @date 2015年8月17日 下午3:25:02
 */
@Controller
public class ObjectiveController extends BaseController{
    /**
     * 人员信息
     */
    @Autowired
    private ObjectiveService objectiveService;
    /**
     * 查询列表
    * @author chendb
    * @date 2015年10月14日 上午11:29:01
    * @param request request
    * @param response response
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Objective.VIEW_QUERY)
    public ModelAndView employeelevelView(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        String date=DateUtil.getCurDate();
        date=date.substring(0, 7);
        params.put("objectiveMonth", date);
        params.put("search", "");
        return objectiveService.queryObjective(params);
    }
    /**
     * 翻页功能
    * @author chendb
    * @date 2015年8月17日 下午3:50:17
    * @param request 页码
    * @param response 页码
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @param objectiveMonth 年月
    * @param search 每页数量
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.ACTION_LIST)
    @ResponseBody
    public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo,
            int pageSize, String objectiveMonth, String search){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        params.put("objectiveMonth", objectiveMonth);
        params.put("search", search);
        return objectiveService.listAction(params, pageNo, pageSize);
    }
    /**
     * 新增人员目标
    * @author chendb
    * @date 2015年8月17日 下午4:55:16
    * @param request 参数
    * @param response 参数
    * @param addData 参数
    * @return BaseDto
     */ 
    @RequestMapping(value = Url.Objective.ADD)
    @ResponseBody
    public BaseDto addObjective(HttpServletRequest request, HttpServletResponse response, String addData){
        JSONObject  jasonObject = JSONObject.fromObject(addData);
        ObjectiveDto objectiveDto = (ObjectiveDto)JSONObject.toBean(jasonObject, ObjectiveDto.class); 
        objectiveDto.setStoreId(getStoreId(request));
        objectiveService.addObjective(objectiveDto);
        return new BaseDto(0, "保存成功！");
    }
    /**
     * 修改
    * @author chendb
    * @date 2015年8月17日 下午7:51:24
    * @param request 参数
    * @param response 参数
    * @param addData 参数
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.UPDATE)
    @ResponseBody
    public BaseDto updateObjective(HttpServletRequest request, HttpServletResponse response, String addData){
        JSONObject  jasonObject = JSONObject.fromObject(addData);
        ObjectiveDto objectiveDto = (ObjectiveDto)JSONObject.toBean(jasonObject, ObjectiveDto.class); 
        objectiveDto.setStoreId(getStoreId(request));
        int result=objectiveService.updateObjective(objectiveDto);
        if (result==1){
            return new BaseDto(-2, "新增失败！该员工该月已经有目标了");
        }
        return new BaseDto(0, "新增成功！");
    }
    /**
     * 删除目标
    * @author chendb
    * @date 2015年8月18日 上午10:08:00
    * @param objectiveId 目标标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.DELETE)
    @ResponseBody
    public BaseDto deleteObjective(Integer objectiveId){
        objectiveService.deleteObjective(objectiveId);
        return new BaseDto(0, "删除成功！");
    }
    /**
     * 详情
    * @author chendb
    * @date 2015年8月17日 下午6:43:18
    * @param objectiveId 目标标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.QUERY_DETAIL)
    @ResponseBody
    public BaseDto queryDetail(Integer objectiveId){
        
        ObjectiveDto result=objectiveService.queryDetail(objectiveId);
        
        return new BaseDto(0, result);
    }
    /**
     * 统计月份相关目标信息
    * @author chendb
    * @date 2015年8月20日 上午11:17:17
    * @param request 月份
    * @param response 月份
    * @param objectiveMonth 月份
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.QUERYSUM)
    @ResponseBody
    public BaseDto querysum(HttpServletRequest request, HttpServletResponse response, String objectiveMonth){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("objectiveMonth", objectiveMonth);
        map.put("storeId", getStoreId(request));
        Map<String, Object> result=objectiveService.getsum(map);
        
        return new BaseDto(0, result);
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月12日 上午11:07:45
    * @param request 返回
    * @param response 参数
    * @param objectiveMonth 年月
    * @param search 查询
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.DOWNLOADEXCLE, method = RequestMethod.GET)
    @ResponseBody
    public BaseDto downloadExcle(HttpServletRequest request, HttpServletResponse response, String objectiveMonth, String search){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("storeId", getStoreId(request));
        map.put("objectiveMonth", objectiveMonth);
        if (search!=null &&search!=""){
        	try {
				search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
			} 
        	catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        map.put("search", search);
        return objectiveService.downloadExcle(response, map);
    }
    /**
     * 导入模板下载
    * @author chendb
    * @date 2015年10月12日 下午3:13:59
    * @param request 返回
    * @param response 参数 
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.DOWNLOADIMPORT, method = RequestMethod.GET)
    @ResponseBody
    public BaseDto downloadImport(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("storeId", getStoreId(request));
        //map.put("objectiveMonth", DateUtil.getCurDate().substring(0, 7));
        return objectiveService.downloadImport(response, map);
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月12日 上午11:53:59
    * @param file 对象
    * @param request 返回
    * @param response 请求
    * @return BaseDto
     */
    @RequestMapping(value = Url.Objective.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("filevalue") MultipartFile file,
              HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        String temp = request.getSession().getServletContext()
                .getRealPath(File.separator)
                + "temp"; // 临时目录
        return objectiveService.importExcle(file, temp, storeId);
    }
}
