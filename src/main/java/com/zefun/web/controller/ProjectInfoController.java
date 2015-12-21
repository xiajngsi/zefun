package com.zefun.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CodeLibraryDto;
import com.zefun.web.dto.DeptMahjongDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.entity.ComboProject;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.ProjectCategory;
import com.zefun.web.entity.ProjectCommission;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.mapper.CodeLibraryMapper;
import com.zefun.web.mapper.ComboProjectMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.service.EmployeelevelService;
import com.zefun.web.service.MemberLevelService;
import com.zefun.web.service.ProjectService;

/**
 * 项目
* @author 洪秋霞
* @date 2015年8月6日 上午10:07:47 
*
 */
@Controller
public class ProjectInfoController extends BaseController {

    /**项目*/
    @Autowired private ProjectService projectService;
    /**会员等级*/
    @Autowired private MemberLevelService memberLevelService;
    /**职位信息*/
    @Autowired private EmployeelevelService employeelevelService;
    /**查询图库*/
    @Autowired private CodeLibraryMapper codeLibraryMapper;
    /**订单列表*/
    @Autowired private OrderDetailMapper orderDetailMapper;
    /**套餐项目关联*/
    @Autowired
    private ComboProjectMapper comboProjectMapper;

    /**
     * 进入项目价格设置页面
    * @author 洪秋霞
    * @date 2015年8月4日 下午5:38:56
    * @param request request
    * @param response response
    * @param model 视图模型
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Project.PROJECT_LIST)
    public ModelAndView toProjectSetting(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        int storeId = getStoreId(request);
        
        List<DeptProjectBaseDto> deptProjectList = projectService.getDeptProjectByStoreId(storeId);
        model.addObject("deptProjectList", deptProjectList);
        model.addObject("js_deptProjectList", JSONArray.fromObject(deptProjectList));
        
        List<DeptMahjongDto> deptMahjongList = projectService.getDeptMahjongByStoreId(storeId);
        model.addObject("deptMahjongList", deptMahjongList);
        model.addObject("mahjongList", JSONArray.fromObject(deptMahjongList).toString());

        // 会员等级列表
        List<MemberLevel> memberLevelList = memberLevelService.queryByStoreId(storeId);
        model.addObject("memberLevels", memberLevelList);
        model.addObject("memberLevelList", JSONArray.fromObject(memberLevelList).toString());
        List<CodeLibraryDto> images = codeLibraryMapper.selectProjectImage();
        model.addObject("images", images);
        model.setViewName(View.Project.PROJECTSETTING);
        return model;
    }

    /**
     * 根据部门查询轮牌列表
    * @author 洪秋霞
    * @date 2015年9月21日 上午11:11:40
    * @param request request
    * @param response response
    * @param deptId 部门Id
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.QUERY_SHIFTMAHBY_DEPID)
    @ResponseBody
    public BaseDto queryShiftMahByDepId(HttpServletRequest request, HttpServletResponse response, Integer deptId) {
        try {
            List<ShiftMahjongDto> shiftMahjongDtoList = projectService.queryShiftMahjongList(deptId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shiftMahjongDtoList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 根据选择的轮牌标识查询对应的会员等级
    * @author 洪秋霞
    * @date 2015年9月21日 下午2:16:30
    * @param request request
    * @param response response
    * @param shiftMahjongId 多个轮牌标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.QUERY_SHIFTMAH_BYLEVELID)
    @ResponseBody
    public BaseDto queryShiftMahByLevelId(HttpServletRequest request, HttpServletResponse response, String shiftMahjongId) {
        try {
            String[] shiftMahjongIdArr = shiftMahjongId.split(",");
            List<Integer> shiftMahjongIds = new ArrayList<Integer>();
            if (shiftMahjongIdArr != null && !"null".equals(shiftMahjongIdArr)) {
                for (int i = 0; i < shiftMahjongIdArr.length; i++) {
                    shiftMahjongIds.add(Integer.parseInt(shiftMahjongIdArr[i]));
                }
                List<Integer> levelIds = projectService.queryShiftMahjongByLevelId(shiftMahjongIds);
                if (levelIds.size() > 0) {
                    List<EmployeeLevel> employeeLevelList = employeelevelService.queryByLevelIds(levelIds);
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, employeeLevelList);
                }
                else {
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_FAIL);
                }
            }
            else {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_FAIL);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 根据项目id查询项目
    * @author 洪秋霞
    * @date 2015年8月5日 上午11:07:22
    * @param request request
    * @param response response
    * @param projectId 项目id
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.QUERY_PROJECTINFO_BYID)
    @ResponseBody
    public BaseDto queryProjectInfoById(HttpServletRequest request, HttpServletResponse response, Integer projectId) {
        try {
            ProjectInfo projectInfo = projectService.queryProjectInfoById(projectId);
            // 员工项目提成
            ProjectCommission projectCommission = new ProjectCommission();
            projectCommission.setProjectId(projectId);
            List<ProjectCommission> projectCommissionList = projectService.queryProjectCommissionList(projectCommission);

            // 会员折扣
            ProjectDiscount projectDiscount = new ProjectDiscount();
            projectDiscount.setProjectId(projectId);
            List<ProjectDiscount> projectDiscountList = projectService.queryProjectDiscountList(projectDiscount);

            // 项目轮牌步骤
            List<ProjectStep> projectStepList = projectService.queryProjectStepByPIdList(projectId);
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("projectInfo", projectInfo);
            map.put("projectCommissionList", projectCommissionList);
            map.put("projectDiscountList", projectDiscountList);
            map.put("projectStepList", projectStepList);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 保存项目类别
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:35:27
    * @param request request
    * @param response response
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.SAVE_PROJECT_CATEGORY)
    @ResponseBody
    public BaseDto saveProjectCategory(HttpServletRequest request, HttpServletResponse response, Integer deptId) {
        String categoryName = request.getParameter("categoryName");
        ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setCategoryName(categoryName);
        projectCategory.setDeptId(deptId);
        projectCategory.setStoreId(getStoreId(request));
        projectCategory.setCreateTime(DateUtil.getCurTime());
        Integer categoryId = projectService.saveProjectCategory(projectCategory);
        projectService.cleanRedisCacheByDeptId(deptId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, categoryId);
    }
    
    /**
     * 保存项目类别集合
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:35:27
    * @param request request
    * @param response response
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.SAVE_PROJECT_CATEGORY_LIST, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto saveProjectCategoryList(HttpServletRequest request, HttpServletResponse response, Integer deptId) {
        String[] categoryName = request.getParameterValues("categoryName");
        List<ProjectCategory> ls = new ArrayList<ProjectCategory>();
        for (int i = 0; i < categoryName.length; i++) {
            ProjectCategory projectCategory = new ProjectCategory();
            projectCategory.setCategoryName(categoryName[i]);
            projectCategory.setDeptId(deptId);
            projectCategory.setStoreId(getStoreId(request));
            projectCategory.setCreateTime(DateUtil.getCurTime());
            Integer categoryId = projectService.saveProjectCategory(projectCategory);
            projectCategory.setCategoryId(categoryId);
            ls.add(projectCategory);
        }
        projectService.cleanRedisCacheByDeptId(deptId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }

    /**
     * 删除项目类别
    * @author 洪秋霞
    * @date 2015年8月27日 下午2:50:20
    * @param request request
    * @param response response
    * @param categoryId 类别id
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.DELETE_PROJECT_CATEGORY)
    @ResponseBody
    public BaseDto deleteProjectCategory(HttpServletRequest request, HttpServletResponse response, Integer categoryId, Integer deptId) {
        String[] projectIds = request.getParameterValues("projectId");
        if (projectIds!=null&&projectIds.length>0){
            for (int j = 0; j < projectIds.length; j++) {
                ComboProject comboProject = new ComboProject();
                comboProject.setProjectId(Integer.parseInt(projectIds[j]));
                List<ComboProject> comboProjects = comboProjectMapper.selectByProperty(comboProject);
                if (comboProjects!=null&&comboProjects.size()>0){
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该系列下项目在套餐中引用不可删除");
                }
                projectService.deleteProject(Integer.parseInt(projectIds[j]));
            }
        }
        projectService.deleteProjectCategory(categoryId);
        projectService.cleanRedisCacheByDeptId(deptId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 编辑项目类别
    * @author 洪秋霞
    * @date 2015年8月28日 上午9:42:17
    * @param request request
    * @param response response
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.EDIT_PROJECT_CATEGORY)
    @ResponseBody
    public BaseDto editProjectCategory(HttpServletRequest request, HttpServletResponse response, Integer deptId) {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        projectService.editProjectCategory(Integer.parseInt(categoryId), categoryName);
        projectService.cleanRedisCacheByDeptId(deptId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 保存项目
    * @author 洪秋霞
    * @date 2015年8月13日 上午10:17:42
    * @param request request
    * @param response response
    * @param projectInfo 项目信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.SAVE_PROJECT)
    @ResponseBody
    public BaseDto saveProject(HttpServletRequest request, HttpServletResponse response, ProjectInfo projectInfo) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        
        String[] levelId = request.getParameterValues("levelId");
        String[] discountProportion = request.getParameterValues("discountProportion");
        String[] discountAmount = request.getParameterValues("discountAmount");
        String[] onlineAppointmentPrice = request.getParameterValues("onlineAppointmentPrice");

        String[] empLevelId = request.getParameterValues("empLevelId");
        String[] assignType = request.getParameterValues("assignType");
        String[] assignCash = request.getParameterValues("assignCash");
        String[] assignCard = request.getParameterValues("assignCard");
        String[] nonAssignCash = request.getParameterValues("assignCash");
        String[] nonAssignCard = request.getParameterValues("assignCard");
        String[] appointmentReward = request.getParameterValues("appointmentReward");

        String[] shiftMahjongIdArr = request.getParameterValues("shiftMahjongId");
        String[] shiftStepNameArr = request.getParameterValues("shiftStepName");
        String[] stepPerformanceArr = request.getParameterValues("stepPerformanceDate");
        String[] isDisableArr = request.getParameterValues("isDisableApp");
        
        //新增加的设置步骤的业绩方式
        String[] stepPerformanceType = request.getParameterValues("stepPerformanceType");
        String[] stepPerformance = request.getParameterValues("stepPerformance");
        
        //每一步骤中设置的岗位的数量
        String[] zhiweinum = request.getParameterValues("zhiweinum");

        if (projectInfo.getProjectId() == null) {
            // 新增
            projectInfo.setStoreId(getStoreId(request));
            projectInfo.setCreateTime(DateUtil.getCurTime());
            Integer projectId = projectService.saveProject(getUserId(request), projectInfo, levelId, discountProportion, discountAmount,
                    onlineAppointmentPrice, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward,
                    shiftMahjongIdArr, shiftStepNameArr, stepPerformanceArr, isDisableArr, zhiweinum, stepPerformanceType, stepPerformance);
            baseDto.setMsg(projectId);
        }
        else {
            List<OrderDetail> details = orderDetailMapper.selectHasProjectAndStatus(projectInfo.getProjectId());
            if (details!=null&&details.size()>0){
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "项目在订单列表中,不可修改");
            }
            // 修改
            projectService.updateProject(getUserId(request), projectInfo, levelId, discountProportion, discountAmount,
                    onlineAppointmentPrice, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward,
                    shiftMahjongIdArr, shiftStepNameArr, stepPerformanceArr, isDisableArr, zhiweinum, stepPerformanceType, stepPerformance);
        }
        projectService.cleanRedisCacheByDeptId(projectInfo.getDeptId());
        return baseDto;
    }

    /**
     * 批量保存项目
    * @author 洪秋霞
    * @date 2015年8月13日 上午10:17:42
    * @param request request
    * @param response response
    * @param deptId 部门
    * @param categoryId 系列
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.SAVE_PROJECT_LIST)
    @ResponseBody
    public BaseDto saveProjectList(HttpServletRequest request, HttpServletResponse response, Integer deptId, Integer categoryId) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        String[] projectName = request.getParameterValues("projectName");
        List<ProjectInfo> ls = new ArrayList<ProjectInfo>();
        for (int i = 0; i < projectName.length; i++) {
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setCategoryId(categoryId);
            projectInfo.setDeptId(deptId);
            projectInfo.setProjectName(projectName[i]);
            projectInfo.setStoreId(getStoreId(request));
            projectInfo.setCreateTime(DateUtil.getCurTime());
            projectInfo.setIsDeleted(0);
            Integer projectId = projectService.saveProjects(getUserId(request), projectInfo);
            projectInfo.setProjectId(projectId);
            ls.add(projectInfo);
        }
        baseDto.setMsg(ls);
        projectService.cleanRedisCacheByDeptId(deptId);
        return baseDto;
    }
    
    /**
     * 删除项目
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:37:10
    * @param request request
    * @param response response
    * @param projectId 项目id
    * @param deptId 部门标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Project.DELETE_PROJECT)
    @ResponseBody
    public BaseDto deleteProject(HttpServletRequest request, HttpServletResponse response, Integer projectId, Integer deptId) {
        ComboProject comboProject = new ComboProject();
        comboProject.setProjectId(projectId);
        List<ComboProject> comboProjects = comboProjectMapper.selectByProperty(comboProject);
        if (comboProjects!=null&&comboProjects.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该项目在套餐中引用不可删除");
        }
        List<OrderDetail> details = orderDetailMapper.selectHasProjectAndStatus(projectId);
        if (details!=null&&details.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "项目在订单列表中,不可删除");
        }
        projectService.deleteProject(projectId);
        projectService.cleanRedisCacheByDeptId(deptId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 文件上传
    * @author 高国藩
    * @date 2015年11月9日 下午2:46:14
    * @param file 文件
    * @param request 请求
    * @return 返回标示
     */
    @RequestMapping(value = Url.Project.UPLOAD_PROJECT)
    public BaseDto uploadTest(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload"); 
        String fileName = file.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if (!targetFile.exists()){  
            targetFile.mkdir();
        }  
        try {  
            file.transferTo(targetFile);  
        } 
        catch (Exception e) {  
            e.printStackTrace();  
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

}
