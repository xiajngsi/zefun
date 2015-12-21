package com.zefun.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.service.EmployeeService;

/**
 * 人员信息
* @author 陈端斌
* @date 2015年8月7日 下午5:11:43 
*
 */
@Controller
public class EmployeeController extends BaseController{
   /**
    * 人员信息接口
    */
	@Autowired
	private EmployeeService employeeService;
	/**
	 * 
	* @author chendb
	* @date 2015年10月14日 上午11:10:23
	* @param request request
	* @param response response
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.Employee.VIEW_QUERY)
	public ModelAndView employeeLevelView(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("storeId", getStoreId(request));
		return employeeService.queryEmployeeInfo(params);
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午10:16:18
	* @param request request
	* @param response response
	* @param pageNo 页码
	* @param pageSize 每页显示数量
	* @param search 查询条件
	* @param gangwei 查询条件
	* @return BaseDto
	 */
	@RequestMapping(value = Url.Employee.ACTION_LIST)
	@ResponseBody
	public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, String search
	        , String gangwei){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("storeId", getStoreId(request));
		params.put("search", search);
		params.put("positionId", gangwei);
		return employeeService.listAction(params, pageNo, pageSize);
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午10:17:32
	* @param request request
	* @param response response
	* @param addData 新增的数据参数对象
	* @return BaseDto
	 */
    @RequestMapping(value = Url.Employee.ADD)
    @ResponseBody
	public BaseDto addEmployee(HttpServletRequest request, HttpServletResponse response, String addData){
		JSONObject  jasonObject = JSONObject.fromObject(addData);
		
		EmployeeDto employeeDto = (EmployeeDto)JSONObject.toBean(jasonObject, EmployeeDto.class);
		employeeDto.setStoreId(getStoreId(request));
		employeeDto.setCreateTime(DateUtil.getCurTime());
		employeeDto.setLastOperatorId(getUserId(request));
		/*Map<String, Object> map = (Map<String, Object>)jasonObject;
		map.put("storeId", getStoreId(request));
		map.put("createTime", DateUtil.getCurTime());
		map.put("lastOperatorId", getUserId(request));*/
		
		int result=employeeService.addEmployee(employeeDto);
		if (result==1){
			return new BaseDto(-2, "员工编码已经被人引用过了！");
		}
		else if (result==2){
		    return new BaseDto(-3, "员工账号已经被人引用过了！");
		}
		Integer employeeId=employeeDto.getEmployeeId();
		
		return new BaseDto(0, employeeId);
	}
    /**
     * 删除功能
    * @author chendb
    * @date 2015年10月8日 下午5:20:37
    * @param employeeId 标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.DELETE)
    @ResponseBody
    public BaseDto deleteEmployee(Integer employeeId){
        
        employeeService.deleteEmployee(employeeId);
        return new BaseDto(0, "删除成功!");
    }
    /**
     * 新增个人简历
    * @author chendb
    * @date 2015年11月7日 上午10:49:04
    * @param employeeId employeeId
    * @param desc desc
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.SAVEDESC)
    @ResponseBody
    public BaseDto savedesc(Integer employeeId, String desc){
        
        employeeService.savedesc(employeeId, desc);
        return new BaseDto(0, "删除成功!");
    }
    /**
     * 修改人员信息
    * @author chendb
    * @date 2015年8月14日 下午3:52:37
    * @param request request
    * @param response response
    * @param addData 参数
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.UPDATE)
    @ResponseBody
    public BaseDto updateEmployee(HttpServletRequest request, HttpServletResponse response, String addData){
        JSONObject jasonObject = JSONObject.fromObject(addData);
        EmployeeDto employeeDto = (EmployeeDto)JSONObject.toBean(jasonObject, EmployeeDto.class); 
        employeeDto.setStoreId(getStoreId(request));
        employeeDto.setCreateTime(DateUtil.getCurTime());
        employeeDto.setLastOperatorId(getUserId(request));
       /* @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        map.put("createTime", DateUtil.getCurTime());
        map.put("lastOperatorId", getUserId(request));*/
        
        int result=employeeService.updateEmployee(employeeDto);
        if (result==1){
            return new BaseDto(-2, "员工编码已经被人引用过了！");
        }
        
        return new BaseDto(0, "修改成功");
    }
    /**
     * 根据员工标识获取详情
    * @author chendb
    * @date 2015年8月14日 上午10:57:32
    * @param employeeId 员工标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.GET_DETAIL)
    @ResponseBody
    public BaseDto getDetail(Integer employeeId){
       
        Map<String, Object> info=employeeService.getDetail(employeeId);
        
        return new BaseDto(0, info);
    }

    /**
     * 新增培训信息
    * @author chendb
    * @date 2015年8月12日 下午5:55:46
    * @param request request
    * @param response response
    * @param employeeId 员工标识
    * @param startDateList 开始时间集合
    * @param endDateList 结束时间
    * @param educationInstitutionList 培训机构
    * @param educationContentList 培训内容
    * @param educationPerformanceList 培训成绩
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Employee.ADD_PX)
    @ResponseBody
    public BaseDto addpx(HttpServletRequest request, HttpServletResponse response, Integer employeeId, String startDateList, String endDateList,
            String educationInstitutionList,
            String educationContentList, String educationPerformanceList){
        List<Map<String, Object>> maplist=new ArrayList<Map<String, Object>>();
        String[] startDateList1=startDateList.split(",");
        String[] endDateList1=endDateList.split(",");
        String[] educationInstitutionList1=educationInstitutionList.split(",");
        String[] educationContentList1=educationContentList.split(",");
        String[] educationPerformanceList1=educationPerformanceList.split(",");
        //循环遍历出所有参赛 然后封装
        for (int i = 0; i < startDateList1.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            String startDate=startDateList1[i];
            String endDate=endDateList1[i];
            String educationInstitution=educationInstitutionList1[i];
            String educationContent=educationContentList1[i];
            String educationPerformance=educationPerformanceList1[i];
            
            map.put("lastOperatorId", getUserId(request));
            map.put("createTime", DateUtil.getCurTime());
            map.put("employeeId", employeeId);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("educationInstitution", educationInstitution);
            map.put("educationContent", educationContent);
            map.put("educationPerformance", educationPerformance);
            maplist.add(map);
        }
        employeeService.addpx(employeeId, maplist);
        return new BaseDto(0, "新增成功");
    }*/
    /**
     * 查询人员培训信息
    * @author chendb
    * @date 2015年8月17日 上午10:12:16
    * @param employeeId 员工标识
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Employee.QUERY_PX)
    @ResponseBody
    public BaseDto querypx(Integer employeeId){
        List<Map<String, Object>> list=employeeService.querypx(employeeId);
        return new BaseDto(0, list);
    }*/
    /**
     * 新增工作经历
    * @author chendb
    * @date 2015年8月13日 上午9:35:53
    * @param request 员工标识
    * @param response 员工标识
    * @param employeeId 员工标识
    * @param startDateList 开始时间
    * @param endDateList 结束时间
    * @param companyNameList 公司名称
    * @param positionNameList 职位
    * @param experienceDescList 描述
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Employee.ADD_GZJY)
    @ResponseBody
    public BaseDto addgz(HttpServletRequest request, HttpServletResponse response, Integer employeeId, 
            String startDateList, String endDateList, String companyNameList,
            String positionNameList, String experienceDescList){
        List<Map<String, Object>> maplist=new ArrayList<Map<String, Object>>();
        String[] startDateList1=startDateList.split(",");
        String[] endDateList1=endDateList.split(",");
        String[] companyNameList1=companyNameList.split(",");
        String[] positionNameList1=positionNameList.split(",");
        String[] experienceDescList1=experienceDescList.split(",");
        //循环遍历出所有参赛 然后封装
        for (int i = 0; i < startDateList1.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            String startDate=startDateList1[i];
            String endDate=endDateList1[i];
            String companyName=companyNameList1[i];
            String positionName=positionNameList1[i];
            String experienceDesc=experienceDescList1[i];
            
            map.put("lastOperatorId", getUserId(request));
            map.put("createTime", DateUtil.getCurTime());
            map.put("employeeId", employeeId);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("companyName", companyName);
            map.put("positionName", positionName);
            map.put("experienceDesc", experienceDesc);
            maplist.add(map);
        }
        employeeService.addgz(employeeId, maplist);
        return new BaseDto(0, "新增成功");
    }*/
    /**
     * 工作经验信息查询
    * @author chendb
    * @date 2015年8月17日 上午11:46:23
    * @param employeeId 员工标识
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Employee.QUERT_GZJY)
    @ResponseBody
    public BaseDto querygz(Integer employeeId){
        List<Map<String, Object>> list=employeeService.querygz(employeeId);
        return new BaseDto(0, list);
    }*/
    /**
     * 新增员工擅长技能信息
    * @author chendb
    * @date 2015年8月13日 下午2:06:48
    * @param request request
    * @param response response
    * @param employeeId 员工标识
    * @param categoryNameList 类别名称
    * @param skillNameList 擅长项目
    * @param skillDescList 技能描述
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Employee.ADD_SC)
    @ResponseBody
    public BaseDto addgz(HttpServletRequest request, HttpServletResponse response, Integer employeeId,
            String categoryNameList, String skillNameList, String skillDescList){
        List<Map<String, Object>> maplist=new ArrayList<Map<String, Object>>();
        String[] categoryNameList1=categoryNameList.split(",");
        String[] skillNameList1=skillNameList.split(",");
        String[] skillDescList1=skillDescList.split(",");
        //循环遍历出所有参赛 然后封装
        for (int i = 0; i < categoryNameList1.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            String categoryName=categoryNameList1[i];
            String skillName=skillNameList1[i];
            String skillDesc=skillDescList1[i];
            
            map.put("lastOperatorId", getUserId(request));
            map.put("createTime", DateUtil.getCurTime());
            map.put("employeeId", employeeId);
            map.put("categoryName", categoryName);
            map.put("skillName", skillName);
            map.put("skillDesc", skillDesc);
            maplist.add(map);
        }
        employeeService.addsc(employeeId, maplist);
        return new BaseDto(0, "新增成功");
    }*/
    /**
     * 擅长信息获取
    * @author chendb
    * @date 2015年8月17日 下午2:32:16
    * @param employeeId 员工信息
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Employee.QUERT_SC)
    @ResponseBody
    public BaseDto querysc(Integer employeeId){
        List<Map<String, Object>> list=employeeService.querysc(employeeId);
        return new BaseDto(0, list);
    }*/
    /**
     * 获取人员关系
    * @author chendb
    * @date 2015年8月19日 上午11:34:44
    * @param employeeId 员工标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.QUERT_REGX)
    @ResponseBody
    public BaseDto queryrygx(Integer employeeId){
        Map<Object, Object> info=employeeService.queryrygx(employeeId);
        return new BaseDto(0, info);
    }
    /**
     * 根据部门标识获取人员信息下拉框
    * @author chendb
    * @date 2015年9月11日 下午3:25:24
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.GETDEPTEMPLOYEE)
    @ResponseBody
    public BaseDto getDeptEmployee(Integer deptId){
        List<EmployeeDto> list=employeeService.getDeptEmployee(deptId);
        return new BaseDto(0, list);
    }
    /**
     * 公共展示详情
    * @author chendb
    * @date 2015年9月16日 上午10:43:17
    * @param employeeId 员工标识
    * @return BaseDto
     */
   /* @RequestMapping(value = Url.Employee.EMPLOYEEDETAILSHOW)
    @ResponseBody
    public BaseDto employeedetailshow(Integer employeeId){
        Map<Object, Object> info=employeeService.employeedetailshow(employeeId);
        return new BaseDto(0, info);
    }*/
    /**
     * 
    * @author chendb
    * @date 2015年9月24日 下午3:58:05
    * @param request 派遣门店
    * @param response 派遣门店
    * @param pStoreId 派遣门店
    * @param dispatchTime 派遣时间
    * @param employeeId 员工标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.ADDDISPATCH)
    @ResponseBody
    public BaseDto adddispatch(HttpServletRequest request, HttpServletResponse response, Integer pStoreId, String dispatchTime, Integer employeeId){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("pStoreId", pStoreId);
        map.put("dispatchTime", dispatchTime);
        map.put("storeId", getStoreId(request));
        map.put("employeeId", employeeId);
        map.put("operatorId", getUserId(request));
        map.put("createTime", DateUtil.getCurTime());
        employeeService.adddispatch(map);
        return new BaseDto(0, "操作成功");
    }
    /**
     * 获取员工的派遣
    * @author chendb
    * @date 2015年9月24日 下午5:36:08
    * @param employeeId 员工标示
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.GETPQLIST)
    @ResponseBody
    public BaseDto getpqlist(Integer employeeId){
       
        List<Map<String, Object>> list=employeeService.getpqlist(employeeId);
        return new BaseDto(0, list);
    }
    /**
     * 导出功能
    * @author chendb
    * @date 2015年10月8日 下午5:01:44
    * @param request 返回
    * @param response 请求
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.DOWNLOADEXCLE, method = RequestMethod.GET)
    @ResponseBody
    public BaseDto downloadExcle(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("storeId", getStoreId(request));
        return employeeService.downloadExcle(response, map);
    }
    
    /**
     * 导入
    * @author chendb
    * @date 2015年10月13日 下午5:04:43
    * @param file 对象
    * @param request 请求
    * @param response 返回
    * @return BaseDto
     */
    @RequestMapping(value = Url.Employee.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("filevalue") MultipartFile file,
              HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        String temp = request.getSession().getServletContext()
                .getRealPath(File.separator)
                + "temp"; // 临时目录
        return employeeService.importExcle(file, temp, storeId);
    }
    /**
     * 
    * @author chendb
    * @date 2015年11月10日 上午10:26:28
    * @param os os
    * @param response response
    * @throws WriteException WriteException
    * @throws  IOException IOException
     */
    @RequestMapping(value = Url.Employee.DOWNLOADIMPORT)
    @ResponseBody
    public void createExcel(OutputStream os, HttpServletResponse response) throws WriteException, IOException{
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
     // 设置response的编码方式 
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("aaaa" + ".xls").getBytes(), "iso-8859-1"));
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label xuexiao = new Label(0, 0, "学校");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1, 0, "专业");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2, 0, "专业竞争力");
        sheet.addCell(jingzhengli);
        
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
    
}
