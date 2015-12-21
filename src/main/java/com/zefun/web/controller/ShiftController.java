package com.zefun.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
import com.zefun.web.dto.ShiftDto;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.service.ShiftService;

/**
 * 关于班次相关
* @author chendb
* @date 2015年8月27日 下午6:07:36
 */
@Controller
public class ShiftController extends BaseController{
    /**班次*/
    @Autowired
    private ShiftService shiftService;
    /**
     * 
    * @author chendb
    * @date 2015年10月14日 上午11:52:24
    * @param request request
    * @param response response
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Shift.VIEW_QUERY)
    public ModelAndView attendanceView(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        return shiftService.queryShift(params);
    }
     /**
      * 翻页
     * @author chendb
     * @date 2015年8月27日 下午2:39:02
     * @param request 页码
     * @param response 页码
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @param deptId 部门标识
     * @param search 查询条件
     * @return BaseDto
      */
    @RequestMapping(value = Url.Shift.ACTION_LIST)
    @ResponseBody
    public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, Integer deptId, String search){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("storeId", getStoreId(request));
        params.put("deptId", deptId);
        params.put("search", search);
        return shiftService.listAction(params, pageNo, pageSize);
    }
    /**
     * 人员班次信息
    * @author chendb
    * @date 2015年8月28日 下午3:50:39
    * @param request 参数
    * @param response 参数
    * @param addData 参数
    * @return BaseDto 
     */
    @RequestMapping(value = Url.Shift.ADDEMPLOYEESHIFT)
    @ResponseBody
    public BaseDto employeeShift(String addData, HttpServletRequest request, HttpServletResponse response){
        JSONObject  jasonObject = JSONObject.fromObject(addData);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        int result=shiftService.employeeShift(map);
        if (result==1){
            return new BaseDto(-1, "该员工已经设置过班次信息了！"); 
        }
        return new BaseDto(0, "操作成功");
        
    }
    /**
     * 修改人员班次
    * @author chendb
    * @date 2015年8月31日 下午4:56:35
    * @param request 参数
    * @param response 参数
    * @param addData 参数
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.UPDATEEMPLOYEESHIFT)
    @ResponseBody
    public BaseDto updateemployeeShift(String addData, HttpServletRequest request, HttpServletResponse response){
        JSONObject  jasonObject = JSONObject.fromObject(addData);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)jasonObject;
        map.put("storeId", getStoreId(request));
        shiftService.updateemployeeShift(map);
        
        return new BaseDto(0, "操作成功");
        
    }
    /**
     * 获取人员班次信息
    * @author chendb
    * @date 2015年8月31日 下午3:54:09
    * @param employeeId 员工标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.GETEMPLOYEESHIFT)
    @ResponseBody
    public BaseDto queryEmployeeShift(Integer employeeId){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("employeeId", employeeId);
        ShiftDto info=shiftService.queryEmployeeShift(map);
        return new BaseDto(0, info);
    }
    /**
     * 删除人员班次信息
    * @author chendb
    * @date 2015年8月31日 下午6:40:34
    * @param employeeId 员工标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.DELETEEMPLOYEESHIFT)
    @ResponseBody
    public BaseDto deleteEmployeeShift(Integer employeeId){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("employeeId", employeeId);
        shiftService.deleteEmployeeShift(map);
        return new BaseDto(0, "操作成功");
    }
    /**
     * 修改班次
    * @author chendb
    * @date 2015年9月10日 下午4:33:14
    * @param deptId 部门标识
    * @param zaostart 开始时间
    * @param zaoend 结束时间
    * @param zhongstart 开始时间
    * @param zhongend 结束时间
    * @param wanstart 开始时间
    * @param wanend 结束时间
    * @param quanstart 开始时间
    * @param quanend 结束时间
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.UPDATESHIFT)
    @ResponseBody
    public BaseDto updateShift(Integer deptId, String zaostart, String zaoend, String zhongstart, String zhongend,
            String wanstart, String wanend, String quanstart, String quanend){
        ShiftInfo zao=new ShiftInfo();
        zao.setDeptId(deptId);
        zao.setStartTime(zaostart);
        zao.setEndTime(zaoend);
        zao.setShifName("早班");
        ShiftInfo zhong=new ShiftInfo();
        zhong.setDeptId(deptId);
        zhong.setStartTime(zhongstart);
        zhong.setEndTime(zhongend);
        zhong.setShifName("中班");
        ShiftInfo wan=new ShiftInfo();
        wan.setDeptId(deptId);
        wan.setStartTime(wanstart);
        wan.setEndTime(wanend);
        wan.setShifName("晚班");
        ShiftInfo quan=new ShiftInfo();
        quan.setDeptId(deptId);
        quan.setStartTime(quanstart);
        quan.setEndTime(quanend);
        quan.setShifName("全班");
        shiftService.updateShift(zao, zhong, wan, quan);
        return new BaseDto(0, "操作成功");
    }
    /**
     * 
    * @author chendb
    * @date 2015年9月10日 下午5:29:16
    * @param request 部门标识
    * @param response 部门标识
    * @param deptId 部门标识
    * @param zaostart 开始时间
    * @param zaoend 结束时间
    * @param zhongstart 开始时间
    * @param zhongend 结束时间
    * @param wanstart 开始时间
    * @param wanend 结束时间
    * @param quanstart 开始时间
    * @param quanend 结束时间
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.ADDSHIFT)
    @ResponseBody
    public BaseDto addShift(HttpServletRequest request, HttpServletResponse response, 
            Integer deptId, String zaostart, String zaoend, String zhongstart, String zhongend,
            String wanstart, String wanend, String quanstart, String quanend){
        ShiftInfo zao=new ShiftInfo();
        zao.setStoreId(getStoreId(request));
        zao.setDeptId(deptId);
        zao.setStartTime(zaostart);
        zao.setEndTime(zaoend);
        zao.setShifName("早班");
        zao.setCreateTime(DateUtil.getCurTime());
        ShiftInfo zhong=new ShiftInfo();
        zhong.setStoreId(getStoreId(request));
        zhong.setDeptId(deptId);
        zhong.setStartTime(zhongstart);
        zhong.setEndTime(zhongend);
        zhong.setShifName("中班");
        zhong.setCreateTime(DateUtil.getCurTime());
        ShiftInfo wan=new ShiftInfo();
        wan.setStoreId(getStoreId(request));
        wan.setDeptId(deptId);
        wan.setStartTime(wanstart);
        wan.setEndTime(wanend);
        wan.setShifName("晚班");
        wan.setCreateTime(DateUtil.getCurTime());
        ShiftInfo quan=new ShiftInfo();
        quan.setStoreId(getStoreId(request));
        quan.setDeptId(deptId);
        quan.setStartTime(quanstart);
        quan.setEndTime(quanend);
        quan.setShifName("全班");
        quan.setCreateTime(DateUtil.getCurTime());
        shiftService.addShift(zao, zhong, wan, quan);
        return new BaseDto(0, "操作成功");
    }
    /**
     * 根据部门标识获取班次信息
    * @author chendb
    * @date 2015年9月9日 下午5:02:40
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.GETSHIFTINFO)
    @ResponseBody
    public BaseDto getShiftinfo(Integer deptId){
        List<ShiftInfo> list=shiftService.getShiftinfo(deptId);
        return new BaseDto(0, list);
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月20日 上午10:51:04
    * @param file file
    * @param request request
    * @param  response response
    * @return BaseDto
     */
    @RequestMapping(value = Url.Shift.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("filevalue") MultipartFile file,
              HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        String temp = request.getSession().getServletContext()
                .getRealPath(File.separator)
                + "temp"; // 临时目录
        return shiftService.importExcle(file, temp, storeId);
    }
}
