package com.zefun.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.service.DeptService;

/**
 * 关于部门
* @author chendb
* @date 2015年9月8日 上午11:09:30
 */
@Controller
public class DeptController extends BaseController{
    /**
     * 关于部门
     */
    @Autowired
    private DeptService deptService;
    
    /**
     * 新增部门
    * @author chendb
    * @date 2015年9月8日 上午11:47:11
    * @param request request
    * @param response response
    * @param deptCode 部门编码 1-9
    * @param deptName 部门名称
    * @param isResults isResults
    * @return BaseDto
     */
    @RequestMapping(value = Url.Dept.ADD_DEPT)
    @ResponseBody
    public BaseDto adddDept(HttpServletRequest request, HttpServletResponse response, Integer deptCode, String deptName, Integer isResults){
        DeptInfo deptInfo=new DeptInfo();
        deptInfo.setDeptCode(deptCode);
        deptInfo.setDeptName(deptName);
        deptInfo.setStoreId(getStoreId(request));
        deptInfo.setIsResults(isResults);
        deptInfo.setOperateTime(DateUtil.getCurTime());
        deptInfo.setOperateId(getUserId(request));
        int result=deptService.adddDept(deptInfo);
        if (result==1){
            return new BaseDto(-1, "部门编码已经存在！");
        }
        if (result==2){
            return new BaseDto(-2, "部门名称已经存在！");
        }
        return new BaseDto(0, "新增成功");
    }
    /**
     * 修改部门信息
    * @author chendb
    * @date 2015年9月8日 下午3:49:51
    * @param request request
    * @param response response
    * @param deptId 部门标识
    * @param deptCode 部门编码
    * @param deptName 部门名称
    * @param isResults isResults
    * @return BaseDto
     */
    @RequestMapping(value = Url.Dept.UPDATE_DEPT)
    @ResponseBody
    public BaseDto updatedDept(HttpServletRequest request, HttpServletResponse response, Integer deptId,
            Integer deptCode, String deptName, Integer isResults){
        DeptInfo deptInfo=new DeptInfo();
        deptInfo.setDeptCode(deptCode);
        deptInfo.setDeptName(deptName);
        deptInfo.setDeptId(deptId);
        deptInfo.setIsResults(isResults);
        deptInfo.setStoreId(getStoreId(request));
        deptInfo.setOperateTime(DateUtil.getCurTime());
        deptInfo.setOperateId(getUserId(request));
        int result=deptService.updatedDept(deptInfo);
        if (result==1){
            return new BaseDto(-1, "部门编码已经存在！");
        }
        if (result==2){
            return new BaseDto(-2, "部门名称已经存在！");
        }
        return new BaseDto(0, "修改成功");
    }
    /**
     * 删除部门信息
    * @author chendb
    * @date 2015年9月8日 下午3:54:08
    * @param deptId 部门标识
    * @param request request
    * @param response response
    * @return BaseDto
     */
    @RequestMapping(value = Url.Dept.DELETE_DEPT)
    @ResponseBody
    public BaseDto deleteDept(Integer deptId, HttpServletRequest request, HttpServletResponse response){
        int result=deptService.deleteDept(deptId, getStoreId(request));
        if (result==1){
            return new BaseDto(-1, "部门已经被岗位引用，请先删除岗位！");
        }
        if (result==2){
            return new BaseDto(-1, "部门已经被项目引用，请先删除项目！");
        }
        if (result==3){
            return new BaseDto(-1, "部门已经被商品引用，请先删除商品！");
        }
        if (result==4){
            return new BaseDto(-1, "部门已经被套餐引用，请先删除套餐！");
        }
        if (result==5){
            return new BaseDto(-1, "部门已经被商品类别引用，请先删除商品类别！");
        }
        return new BaseDto(0, "删除成功！");
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月14日 上午11:08:31
    * @param request request
    * @param response response
    * @return BaseDto
     */
    @RequestMapping(value = Url.Dept.GETDEPTINFO)
    @ResponseBody
    public BaseDto getDeptInfo(HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        List<DeptInfo> list=deptService.getDeptInfo(storeId);
        return new BaseDto(0, list);
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月19日 上午9:39:43
    * @param file 文件
    * @param request 请求
    * @param response 返回
    * @return BaseDto
     * @throws IOException 
     */ 
    @RequestMapping(value = Url.Dept.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("filevalue") MultipartFile file,
              HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer storeId=getStoreId(request);
        String temp = request.getSession().getServletContext()
                .getRealPath(File.separator)
                + "temp"; // 临时目录
        return deptService.importExcle(file, temp, storeId);
    }
}
