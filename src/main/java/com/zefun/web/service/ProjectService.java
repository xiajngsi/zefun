package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EntityJsonConverter;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.DeptMahjongDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.ProjectAppointDto;
import com.zefun.web.dto.ProjectCommissionDto;
import com.zefun.web.dto.ProjectInfoDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.ProjectCategory;
import com.zefun.web.entity.ProjectCommission;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;
import com.zefun.web.mapper.ProjectCommissionMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;

/**
 * 项目
* @author 洪秋霞
* @date 2015年8月6日 上午10:08:18 
*
 */
@Service
public class ProjectService {
    /** 项目类别 */
    @Autowired private ProjectCategoryMapper projectCategoryMapper;
    /** 项目信息 */
    @Autowired private ProjectInfoMapper projectInfoMapper;
    /** 项目会员折扣 */
    @Autowired private ProjectDiscountMapper projectDiscountMapper;
    /** 项目职位提成 */
    @Autowired private ProjectCommissionMapper projectCommissionMapper;
    /**项目步骤*/
    @Autowired private ProjectStepMapper projectStepMapper;
    /**轮牌信息*/
    @Autowired private ShiftMahjongMapper shiftMahjongMapper;
    /**轮牌员工*/
    @Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
    /**部门信息*/
    @Autowired private DeptInfoMapper deptInfoMapper;
    /** redis api 操作服务对象 */
    @Autowired private RedisService redisService;
    
    
    /**
     * 查询门店下所有部门的所有轮牌信息
    * @author 张进军
    * @date Oct 14, 2015 11:41:56 PM
    * @param storeId    门店标识
    * @return   所有部门下的轮牌信息
     */
    public List<DeptMahjongDto> getDeptMahjongByStoreId(int storeId) {
        List<Integer> deptIdList = deptInfoMapper.selectDeptIdByStoreIdIsResults(storeId);
        if (deptIdList.isEmpty()) {
            return null;
        }
        
        List<DeptMahjongDto> deptMahjongList = new ArrayList<DeptMahjongDto>(deptIdList.size());
        for (Integer deptId : deptIdList) {
            DeptMahjongDto deptMahjong = getDeptMahjongByDeptId(deptId);
            deptMahjongList.add(deptMahjong);
        }
        return deptMahjongList;
    }
    
    
    /**
     * 根据部门编号查询部门下所有轮牌信息，包含职位列表
    * @author 张进军
    * @date Oct 15, 2015 12:13:00 AM
    * @param deptId 部门编号
    * @return   轮牌信息
     */
    public DeptMahjongDto getDeptMahjongByDeptId(int deptId){
        String deptProjectBaseInfoJson = redisService.hget(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, deptId);
        DeptMahjongDto deptMahjong = null;
        //首先从缓存中获取，如果缓存中不存在，则从数据库查出并缓存
        if (StringUtils.isBlank(deptProjectBaseInfoJson)) {
            deptMahjong = projectInfoMapper.selectDeptMahjongByDeptId(deptId);
            if (deptMahjong == null) {
                return null;
            }
            redisService.hset(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, deptId, EntityJsonConverter.entity2Json(deptMahjong));
        }
        //缓存中存在则直接转换为对象
        else {
            deptMahjong = EntityJsonConverter.json2Entity(deptProjectBaseInfoJson, DeptMahjongDto.class);
        }
        return deptMahjong;
    }
    
    
    /**
     * 查询门店下所有部门的所有项目信息
    * @author 张进军
    * @date Oct 14, 2015 11:41:56 PM
    * @param storeId    门店标识
    * @return   所有部门下的项目项目类别及其下的项目列表
     */
    public List<DeptProjectBaseDto> getDeptProjectByStoreId(int storeId) {
        List<Integer> deptIdList = deptInfoMapper.selectDeptIdByStoreIdIsResults(storeId);
        if (deptIdList.isEmpty()) {
            return null;
        }
        
        List<DeptProjectBaseDto> deptProjectList = new ArrayList<DeptProjectBaseDto>(deptIdList.size());
        for (Integer deptId : deptIdList) {
            DeptProjectBaseDto deptPorject = getDeptProjectByDeptId(deptId);
            deptProjectList.add(deptPorject);
        }
        return deptProjectList;
    }
    
    
    /**
     * 根据部门编号查询部门下所有项目信息，包含项目类别
    * @author 张进军
    * @date Oct 15, 2015 12:13:00 AM
    * @param deptId 部门编号
    * @return   项目列表
     */
    public DeptProjectBaseDto getDeptProjectByDeptId(int deptId){
        String deptProjectBaseInfoJson = redisService.hget(App.Redis.DEPT_PROJECT_BASE_INFO_KEY_HASH, deptId);
        DeptProjectBaseDto deptProject = null;
        //首先从缓存中获取，如果缓存中不存在，则从数据库查出并缓存
        if (StringUtils.isBlank(deptProjectBaseInfoJson)) {
            deptProject = projectInfoMapper.selectDeptProjectByDeptId(deptId);
            if (deptProject == null) {
                return null;
            }
            redisService.hset(App.Redis.DEPT_PROJECT_BASE_INFO_KEY_HASH, deptId, EntityJsonConverter.entity2Json(deptProject));
        }
        //缓存中存在则直接转换为对象
        else {
            deptProject = EntityJsonConverter.json2Entity(deptProjectBaseInfoJson, DeptProjectBaseDto.class);
        }
        return deptProject;
    }
    

    /**
     * 获取部门，项目类别和项目列表
    * @author 洪秋霞
    * @date 2015年9月16日 上午10:58:45
    * @param storeId 门店Id
    * @return List<DeptInfoDto>
     */
    public List<DeptInfoDto> getDetpInfoByProject(Integer storeId) {
        return projectCategoryMapper.getDetpInfoByProject(storeId);
    }

    /**
     * 查询项目部门列表
    * @author 洪秋霞
    * @date 2015年9月11日 下午3:47:00
    * @param storeId 门店id
    * @return List<DeptInfo>
     */
    public List<DeptInfo> queryDeptInfoList(Integer storeId) {
        return deptInfoMapper.getResultsDeptInfo(storeId);
    }

    /**
     * 查询轮牌列表
     * @param deptId 部门id
    * @author 洪秋霞
    * @date 2015年9月11日 下午2:47:08
    * @return List<ShiftMahjongDto>
     */
    public List<ShiftMahjongDto> queryShiftMahjongList(Integer deptId) {
        return shiftMahjongMapper.selectByDeptId(deptId);
    }

    /**
     * 根据轮牌标识查询员工
    * @author 洪秋霞
    * @date 2015年9月21日 下午2:13:37
    * @param shiftMahjongIds 轮牌标识
    * @return List<Integer>
     */
    public List<Integer> queryShiftMahjongByLevelId(List<Integer> shiftMahjongIds) {
        return shiftMahjongEmployeeMapper.selectShiftMahjongByLevelId(shiftMahjongIds);
    }

    /**
     * 保存项目类别
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:30:38
    * @param projectCategory 项目类别
    * @return int
     */
    public int saveProjectCategory(ProjectCategory projectCategory) {
        projectCategory.setIsDeleted(0);
        projectCategoryMapper.insertSelective(projectCategory);
        return projectCategory.getCategoryId();
    }

    /**
     * 删除项目类别
    * @author 洪秋霞
    * @date 2015年8月27日 下午2:47:32
    * @param categoryId 类别id
    * @return int
     */
    public int deleteProjectCategory(Integer categoryId) {
        //projectInfoMapper.deleteByCategory(categoryId);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setCategoryId(categoryId);
        List<ProjectInfo> ls =  projectInfoMapper.selectByProperty(projectInfo);
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setIsDeleted(1);
            projectInfoMapper.updateByPrimaryKeySelective(ls.get(i));
        }
        ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setIsDeleted(1);
        projectCategory.setCategoryId(categoryId);
        return projectCategoryMapper.updateByPrimaryKeySelective(projectCategory);
    }

    /**
     * 编辑项目类别
    * @author 洪秋霞
    * @date 2015年8月28日 上午9:43:05
    * @param categoryId 类别Id
    * @param categoryName 类别名称
    * @return int
     */
    public int editProjectCategory(Integer categoryId, String categoryName) {
        ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setCategoryId(categoryId);
        projectCategory.setCategoryName(categoryName);
        return projectCategoryMapper.updateByPrimaryKeySelective(projectCategory);
    }

    /**
     * 查询项目类别
    * @author 洪秋霞
    * @date 2015年8月4日 下午8:33:48
    * @param projectCategory 项目类别
    * @return List<ProjectCategory>
     */
    public List<ProjectCategory> queryProjectCategoryList(ProjectCategory projectCategory) {
        return projectCategoryMapper.selectByProperty(projectCategory);
    }

    /**
     * 查询项目职位提成列表
    * @author 洪秋霞
    * @date 2015年8月13日 上午11:46:11
    * @param projectCommission 项目职位提成
    * @return List<ProjectDiscount>
     */
    public List<ProjectCommission> queryProjectCommissionList(ProjectCommission projectCommission) {
        return projectCommissionMapper.selectByProperty(projectCommission);
    }

    /**
     * 查询项目会员折扣信息
    * @author 洪秋霞
    * @date 2015年8月5日 上午11:22:31
    * @param projectDiscount 项目折扣
    * @return List<ProjectDiscount>
     */
    public List<ProjectDiscount> queryProjectDiscountList(ProjectDiscount projectDiscount) {
        return projectDiscountMapper.selectByProperty(projectDiscount);
    }

    /**
     * 保存项目
     * 保存项目-指定项目会员的折扣
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:31:48
    * @param userId 用户id
    * @param projectInfo 项目信息
    * @param levelId 会员等级id
    * @param discountProportion 折扣比例
    * @param discountAmount 折扣金额
    * @param onlineAppointmentPrice 在线预约价格
    * @param empLevelId 职位id
    * @param assignType 提成方式:1:按业绩比例,2:按固定金额
    * @param assignCash 指定客现金
    * @param assignCard 指定客刷卡
    * @param nonAssignCash 非指定客现金
    * @param nonAssignCard 非指定客刷卡
    * @param appointmentReward 预约奖励
    * @param shiftMahjongId 轮牌信息标识
    * @param shiftStepNameArr 步骤名称
    * @param stepPerformanceArr 步骤业绩计算
    * @param isDisableArr 是否为预约步骤
    * @param zhiweinum 每一步骤中设置的岗位的数量
    * @param stepPerformanceType 项目步骤中的业绩计算方式
    * @param stepPerformance 目步骤中的业绩值
    * @return Integer
     */
    @Transactional
    public Integer saveProject(Integer userId, ProjectInfo projectInfo, String[] levelId, String[] discountProportion, String[] discountAmount,
            String[] onlineAppointmentPrice, String[] empLevelId, String[] assignType, String[] assignCash, String[] assignCard,
            String[] nonAssignCash, String[] nonAssignCard, String[] appointmentReward, String[] shiftMahjongId, String[] shiftStepNameArr, 
            String[] stepPerformanceArr, String[] isDisableArr, String[] zhiweinum, String[] stepPerformanceType, String[] stepPerformance) {
        /*List<StoreUser> storeUserList = storeInfoService.queryStoreUserList(userId);
        if (storeUserList.size() > 1) {
            // 总店项目新增
            projectInfo.setParentId(0);
            projectInfoMapper.insertSelective(projectInfo);
            Integer totalStoreProjectId = projectInfo.getProjectId();
            saveCommissionAndDiscount(userId, totalStoreProjectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
                    assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
                    shiftMahjongId, shiftStepNameArr, stepPerformanceArr, isDisableArr, zhiweinum);

            // 分店项目新增
            Integer parentId = projectInfo.getProjectId();
            for (int i = 0; i < storeUserList.size(); i++) {
                projectInfo.setStoreId(storeUserList.get(i).getStoreId());
                projectInfo.setParentId(parentId);
                projectInfo.setIsUpdate(0);
                projectInfoMapper.insertSelective(projectInfo);
                Integer projectId = projectInfo.getProjectId();
                saveCommissionAndDiscount(userId, projectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
                        assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
                        shiftMahjongId, shiftStepNameArr, stepPerformanceArr, isDisableArr, zhiweinum);
            }
            return totalStoreProjectId;
        }
        else {
            // 单店
//*/            
//        projectInfoMapper.insertSelective(projectInfo);
//            Integer projectId = projectInfo.getProjectId();
//            saveCommissionAndDiscount(userId, projectId, levelId, discountProportion, discountAmount, 
        //onlineAppointmentPrice, empLevelId, assignType,
//                    assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, shiftMahjongId, shiftStepNameArr, stepPerformanceArr, 
//                    isDisableArr, zhiweinum);
//            return projectId;
////        }
//            String[] shiftStepNameArr, String[] stepPerformanceArr, String[] isDisableArr) {
//        List<StoreUser> storeUserList = storeInfoService.queryStoreUserList(userId);
//        if (storeUserList.size() > 1) {
//            // 总店项目新增
//            projectInfo.setParentId(0);
//            projectInfoMapper.insertSelective(projectInfo);
//            Integer totalStoreProjectId = projectInfo.getProjectId();
//            saveCommissionAndDiscount(userId, totalStoreProjectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
//                    assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
//                    shiftMahjongId, shiftStepNameArr, stepPerformanceArr, isDisableArr);
//
//            // 分店项目新增
//            Integer parentId = projectInfo.getProjectId();
//            for (int i = 0; i < storeUserList.size(); i++) {
//                projectInfo.setStoreId(storeUserList.get(i).getStoreId());
//                projectInfo.setParentId(parentId);
//                projectInfo.setIsUpdate(0);
//                projectInfoMapper.insertSelective(projectInfo);
//                Integer projectId = projectInfo.getProjectId();
//                saveCommissionAndDiscount(userId, projectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
//                        assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
//                        shiftMahjongId, shiftStepNameArr, stepPerformanceArr, isDisableArr);
//            }
//            return totalStoreProjectId;
//        }
//        else {
            // 单店
        projectInfoMapper.insertSelective(projectInfo);
        Integer projectId = projectInfo.getProjectId();
        saveCommissionAndDiscount(userId, projectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId, assignType,
                assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, shiftMahjongId, shiftStepNameArr, stepPerformanceArr, 
                isDisableArr, zhiweinum, stepPerformanceType, stepPerformance);
        return projectId;
//        }
    }

    /**
     * 批量保存项目-只有项目id和name两个属性
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:31:48
    * @param userId 用户id
    * @param projectInfo 项目信息
    * @return Integer
     */
    @Transactional
    public Integer saveProjects(Integer userId, ProjectInfo projectInfo) {
//        List<StoreUser> storeUserList = storeInfoService.queryStoreUserList(userId);
//        if (storeUserList.size() > 1) {
//            // 总店项目新增
//            projectInfo.setParentId(0);
//            projectInfoMapper.insertSelective(projectInfo);
//            Integer totalStoreProjectId = projectInfo.getProjectId();
//
//            // 分店项目新增
//            Integer parentId = projectInfo.getProjectId();
//            for (int i = 0; i < storeUserList.size(); i++) {
//                projectInfo.setStoreId(storeUserList.get(i).getStoreId());
//                projectInfo.setParentId(parentId);
//                projectInfo.setIsUpdate(0);
//                projectInfoMapper.insertSelective(projectInfo);
//                @SuppressWarnings("unused")
//                Integer projectId = projectInfo.getProjectId();
//            }
//            return totalStoreProjectId;
//        }
//        else {
            // 单店
        projectInfoMapper.insertSelective(projectInfo);
        Integer projectId = projectInfo.getProjectId();
        return projectId;
//        }
    }
    
    /**
     * 修改项目
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:32:41
    * @param userId 用户id
    * @param projectInfo 项目信息
    * @param levelId 会员等级id
    * @param discountProportion 折扣比例
    * @param discountAmount 折扣金额
    * @param onlineAppointmentPrice 在线预约价格
    * @param empLevelId 职位id
    * @param assignType 提成方式:1:按业绩比例,2:按固定金额
    * @param assignCash 指定客现金
    * @param assignCard 指定客刷卡
    * @param nonAssignCash 非指定客现金
    * @param nonAssignCard 非指定客刷卡
    * @param appointmentReward 预约奖励
    * @param shiftMahjongId 轮牌信息标识
    * @param shiftStepNameArr 步骤名称
    * @param stepPerformanceArr 步骤业绩计算
    * @param isDisableArr 是否为预约步骤
    * @param zhiweinum 每一步骤中设置的岗位的数量
    * @param stepPerformanceType 业绩提成方式
    * @param stepPerformance 业绩
    * @return int
     */
    @Transactional
    public int updateProject(Integer userId, ProjectInfo projectInfo, String[] levelId, String[] discountProportion, String[] discountAmount,
            String[] onlineAppointmentPrice, String[] empLevelId, String[] assignType, String[] assignCash, String[] assignCard,
            String[] nonAssignCash, String[] nonAssignCard, String[] appointmentReward, String[] shiftMahjongId, String[] shiftStepNameArr, 
            String[] stepPerformanceArr, String[] isDisableArr, String[] zhiweinum, String[] stepPerformanceType, String[] stepPerformance) {
        
        // 总店项目修改
        projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
        // 1.先删除
        deleteCommissionAndDiscount(projectInfo.getProjectId());
        // 2.再新增
        saveCommissionAndDiscount(userId, projectInfo.getProjectId(), levelId, discountProportion, discountAmount, onlineAppointmentPrice,
                empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, shiftMahjongId, shiftStepNameArr, 
                stepPerformanceArr, isDisableArr, zhiweinum, stepPerformanceType, stepPerformance);

        // 分店项目修改
        ProjectInfo queryProjectInfo = new ProjectInfo();
        queryProjectInfo.setParentId(projectInfo.getProjectId());
        List<ProjectInfo> queryProjectInfoList = projectInfoMapper.selectByProperty(queryProjectInfo);
        for (int i = 0; i < queryProjectInfoList.size(); i++) {
            ProjectInfo resultProjectInfo = queryProjectInfoList.get(i);
            Integer resultProjectId = resultProjectInfo.getProjectId();
            projectInfo.setProjectId(resultProjectId);
            projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
            // 1.先删除
            deleteCommissionAndDiscount(resultProjectId);
            // 2.再新增
            saveCommissionAndDiscount(userId, resultProjectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
                    assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
                    shiftMahjongId, shiftStepNameArr, stepPerformanceArr, isDisableArr, zhiweinum, stepPerformanceType, stepPerformance);
        }
        return 1;
        
        //下面是连锁店的判断,暂时去掉
        /*List<StoreUser> storeUserList = storeInfoService.queryStoreUserList(userId);
        if (storeUserList.size() > 0) {
            // 总店项目修改
            projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
            // 1.先删除
            deleteCommissionAndDiscount(projectInfo.getProjectId());
            // 2.再新增
            saveCommissionAndDiscount(userId, projectInfo.getProjectId(), levelId, discountProportion, discountAmount, onlineAppointmentPrice,
                    empLevelId, assignType, assignCash, assignCard, nonAssignCash, 
                    nonAssignCard, appointmentReward, shiftMahjongId, shiftStepNameArr, isDisableArr);

            // 分店项目修改
            ProjectInfo queryProjectInfo = new ProjectInfo();
            queryProjectInfo.setParentId(projectInfo.getProjectId());
            List<ProjectInfo> queryProjectInfoList = projectInfoMapper.selectByProperty(queryProjectInfo);
            for (int i = 0; i < queryProjectInfoList.size(); i++) {
                ProjectInfo resultProjectInfo = queryProjectInfoList.get(i);
                Integer resultProjectId = resultProjectInfo.getProjectId();
                projectInfo.setProjectId(resultProjectId);
                projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
                // 1.先删除
                deleteCommissionAndDiscount(resultProjectId);
                // 2.再新增
                saveCommissionAndDiscount(userId, resultProjectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
                        assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
                        shiftMahjongId, shiftStepNameArr, isDisableArr);
            }
            return 1;
        }
        else {
            // 0：不可修改，1：可修改
            if (projectInfo.getIsUpdate() == 1) {
                // 单店
                projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
                Integer projectId = projectInfo.getProjectId();
                // 1.先删除
                deleteCommissionAndDiscount(projectId);
                // 2.再新增
                saveCommissionAndDiscount(userId, projectId, levelId, discountProportion, discountAmount, onlineAppointmentPrice, empLevelId,
                        assignType, assignCash, assignCard, nonAssignCash, nonAssignCard, appointmentReward, 
                        shiftMahjongId, shiftStepNameArr, isDisableArr);
                return 1;
            }
            else {
                return 0;
            }
        }*/
    }

    /**
     * 职位提成 & 会员折扣
    * @author 洪秋霞
    * @date 2015年8月13日 下午8:01:40
    * @param userId 用户id
    * @param projectId 项目id
    * @param levelId 会员等级id
    * @param discountProportion 折扣比例
    * @param discountAmount 会员门店价
    * @param onlineAppointmentPrice 在线预约价
    * @param empLevelId 职位id
    * @param assignType 提成方式:1:按业绩比例,2:按固定金额
    * @param assignCash 指定客现金
    * @param assignCard 指定客刷卡
    * @param nonAssignCash 非指定客现金
    * @param nonAssignCard 非指定客刷卡
    * @param appointmentReward 预约奖励
    * @param shiftMahjongId 轮牌信息标识
    * @param shiftStepNameArr 步骤名称
    * @param stepPerformanceArr 步骤业绩计算
    * @param isDisableArr 是否为预约步骤
    * @param zhiweinum 每一步骤中设置的岗位的数量
    * @param stepPerformanceType 业绩计算方式
    * @param stepPerformance 业绩值
     */
    private void saveCommissionAndDiscount(Integer userId, Integer projectId, String[] levelId, String[] discountProportion, String[] discountAmount,
            String[] onlineAppointmentPrice, String[] empLevelId, String[] assignType, String[] assignCash, String[] assignCard,
            String[] nonAssignCash, String[] nonAssignCard, String[] appointmentReward, String[] shiftMahjongId, String[] shiftStepNameArr, 
            String[] stepPerformanceArr, String[] isDisableArr, String[] zhiweinum, String[] stepPerformanceType, String[] stepPerformance) {
        if (levelId != null && levelId.length > 0) {
            // 会员折扣
            List<ProjectDiscount> projectDiscountList = new ArrayList<ProjectDiscount>();
            for (int i = 0; i < levelId.length; i++) {
                ProjectDiscount projectDiscount = new ProjectDiscount();
                projectDiscount.setProjectId(projectId);
                projectDiscount.setLevelId(Integer.parseInt(levelId[i].trim()));
                //页面上已经去掉了这个了
                projectDiscount.setDiscountProportion(new BigDecimal(discountAmount[i].trim()));
                projectDiscount.setDiscountAmount(new BigDecimal(discountAmount[i].trim()));
                projectDiscount.setOnlineAppointmentPrice(new BigDecimal(discountAmount[i].trim()));
                projectDiscount.setIsDeleted(0);
                projectDiscount.setCreateTime(DateUtil.getCurTime());
                projectDiscount.setLastOperatorId(userId);
                projectDiscountList.add(projectDiscount);
            }
            projectDiscountMapper.insertProjectDiscountList(projectDiscountList);
        }

        // 员工提成
        List<ProjectCommission> projectCommissionList = new ArrayList<ProjectCommission>();
        Integer num = 0;
        for (int i = 0; i < zhiweinum.length; i++) {
            for (int j = 0; j < Integer.parseInt(zhiweinum[i]); j++) {
                ProjectCommission projectCommission = new ProjectCommission();
                projectCommission.setProjectId(projectId);
                projectCommission.setShiftMahjongId(Integer.parseInt(shiftMahjongId[i]));
                projectCommission.setLevelId(Integer.parseInt(empLevelId[num].trim()));
                
                projectCommission.setAssignCashType(Integer.parseInt(assignType[num].trim()));
                projectCommission.setAssignCash(Integer.parseInt(assignCash[num].trim()));

                projectCommission.setAssignCardType(Integer.parseInt(assignType[num].trim()));
                projectCommission.setAssignCard(Integer.parseInt(assignCard[num].trim()));

                projectCommission.setNonAssignCashType(Integer.parseInt(assignType[num].trim()));
                projectCommission.setNonAssignCash(Integer.parseInt(nonAssignCash[num].trim()));

                projectCommission.setNonAssignCardType(Integer.parseInt(assignType[num].trim()));
                projectCommission.setNonAssignCard(Integer.parseInt(nonAssignCard[num].trim()));

                projectCommission.setAppointmentRewardType(Integer.parseInt(assignType[num].trim()));
                projectCommission.setAppointmentReward(Integer.parseInt(appointmentReward[num].trim()));

                projectCommission.setIsDeleted(0);
                projectCommission.setCreateTime(DateUtil.getCurTime());
                projectCommission.setLastOperatorId(userId);
                projectCommissionList.add(projectCommission);
                num = num + 1;
            }
        }
        /*for (int i = 0; i < empLevelId.length; i++) {
            ProjectCommission projectCommission = new ProjectCommission();
            projectCommission.setProjectId(projectId);
            projectCommission.setLevelId(Integer.parseInt(empLevelId[i].trim()));

            projectCommission.setAssignCashType(Integer.parseInt(assignType[i].trim()));
            projectCommission.setAssignCash(Integer.parseInt(assignCash[i].trim()));

            projectCommission.setAssignCardType(Integer.parseInt(assignType[i].trim()));
            projectCommission.setAssignCard(Integer.parseInt(assignCard[i].trim()));

            projectCommission.setNonAssignCashType(Integer.parseInt(assignType[i].trim()));
            projectCommission.setNonAssignCash(Integer.parseInt(nonAssignCash[i].trim()));

            projectCommission.setNonAssignCardType(Integer.parseInt(assignType[i].trim()));
            projectCommission.setNonAssignCard(Integer.parseInt(nonAssignCard[i].trim()));

            projectCommission.setAppointmentRewardType(Integer.parseInt(assignType[i].trim()));
            projectCommission.setAppointmentReward(Integer.parseInt(appointmentReward[i].trim()));

            projectCommission.setIsDeleted(0);
            projectCommission.setCreateTime(DateUtil.getCurTime());
            projectCommission.setLastOperatorId(userId);
            projectCommissionList.add(projectCommission);
        }*/
        projectCommissionMapper.insertProjectCommissionList(projectCommissionList);

        // 按项目流程设置轮牌
        // 先删除原有步骤-此处在xml中改为了修改,不是物理删除了
        projectStepMapper.deleteByProjectId(projectId);
        for (int i = 0; i < shiftMahjongId.length; i++) {
            ProjectStep projectStep = new ProjectStep();
            projectStep.setProjectId(projectId);
            projectStep.setShiftMahjongId(Integer.parseInt(shiftMahjongId[i]));
            projectStep.setProjectStepName(shiftStepNameArr[i]);
            projectStep.setStepPerformance(new BigDecimal(stepPerformance[i]));
            projectStep.setStepPerformanceType(Integer.parseInt(stepPerformanceType[i]));
            projectStep.setIsDisable(Integer.parseInt(isDisableArr[i]));
            projectStep.setProjectStepOrder(i + 1);
            projectStep.setCreateTime(DateUtil.getCurTime());
            projectStep.setIsDeleted(0);
            projectStepMapper.insert(projectStep);
        }

    }

    /**
     * 删除职位提成和会员折扣
    * @author 洪秋霞
    * @date 2015年8月14日 下午4:14:10
    * @param projectId 项目id
     */
    public void deleteCommissionAndDiscount(Integer projectId) {
        // 职位提成 1.先删除
        ProjectCommission projectCommission = new ProjectCommission();
        projectCommission.setProjectId(projectId);
        projectCommissionMapper.deleteByProjectId(projectId);
        /*List<ProjectCommission> projectCommissionList = projectCommissionMapper.selectByProperty(projectCommission);
        for (int i = 0; i < projectCommissionList.size(); i++) {
            projectCommissionMapper.deleteByPrimaryKey(projectCommissionList.get(i).getCommissionId());
        }*/
        // 会员折扣 1.先删除
        ProjectDiscount projectDiscount = new ProjectDiscount();
        projectDiscount.setProjectId(projectId);
        List<ProjectDiscount> projectDiscountList = projectDiscountMapper.selectByProperty(projectDiscount);
        for (int i = 0; i < projectDiscountList.size(); i++) {
            projectDiscountMapper.deleteByPrimaryKey(projectDiscountList.get(i).getDiscountId());
        }
    }

    /**
     * 删除项目
    * @author 洪秋霞
    * @date 2015年8月4日 下午5:15:06
    * @param projectId 项目id
     */
    public void deleteProject(Integer projectId) {
        // 总店项目删除
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectId(projectId);
        projectInfo.setIsDeleted(1);
        projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
        updateCommissionAndDiscount(projectId);

        // 分店项目删除
        ProjectInfo queryProjectInfo = new ProjectInfo();
        queryProjectInfo.setParentId(projectId);
        List<ProjectInfo> resultProjectInfoList = projectInfoMapper.selectByProperty(queryProjectInfo);
        if (resultProjectInfoList.size() > 0) {
            for (int i = 0; i < resultProjectInfoList.size(); i++) {
                ProjectInfo resultProjectInfo = resultProjectInfoList.get(i);
                resultProjectInfo.setIsDeleted(1);
                projectInfoMapper.updateByPrimaryKeySelective(resultProjectInfo);
                updateCommissionAndDiscount(resultProjectInfo.getProjectId());
            }
        }
        // 删除轮牌
        List<ProjectStep> ps = projectStepMapper.queryProjectStepByPIdList(projectId);
        for (int i = 0; i < ps.size(); i++) {
            ProjectStep projectStep = ps.get(i);
            projectStep.setIsDeleted(1);
            projectStepMapper.updateByPrimaryKey(projectStep);
        }
    }

    /**
     * 更新职位提成和会员折扣为删除：1
    * @author 洪秋霞
    * @date 2015年8月14日 下午5:12:14
    * @param projectId 项目id
     */
    private void updateCommissionAndDiscount(Integer projectId) {
        // 职位提成
        ProjectCommission projectCommission = new ProjectCommission();
        projectCommission.setProjectId(projectId);
        List<ProjectCommission> projectCommissionList = projectCommissionMapper.selectByProperty(projectCommission);
        for (int i = 0; i < projectCommissionList.size(); i++) {
            ProjectCommission commission = projectCommissionList.get(i);
            commission.setIsDeleted(1);
            projectCommissionMapper.updateByPrimaryKeySelective(commission);
        }
        // 会员折扣
        ProjectDiscount projectDiscount = new ProjectDiscount();
        projectDiscount.setProjectId(projectId);
        List<ProjectDiscount> projectDiscountList = projectDiscountMapper.selectByProperty(projectDiscount);
        for (int i = 0; i < projectDiscountList.size(); i++) {
            ProjectDiscount discount = projectDiscountList.get(i);
            discount.setIsDeleted(1); // 1：删除
            projectDiscountMapper.updateByPrimaryKeySelective(discount);
        }
    }

    /**
     * 查询项目，根据项目id查询
    * @author 洪秋霞
    * @date 2015年8月4日 下午5:16:36
    * @param projectId 项目id
    * @return ProjectInfo
     */
    public ProjectInfo queryProjectInfoById(Integer projectId) {
        return projectInfoMapper.selectByPrimaryKey(projectId);
    }

    /**
     * 查询项目列表
    * @author 洪秋霞
    * @date 2015年8月7日 下午4:10:15
    * @param projectInfoDto 项目信息dto
    * @return List<ProjectInfoDto>
     */
    public List<ProjectInfoDto> queryProjectInfoList(ProjectInfoDto projectInfoDto) {
        return projectInfoMapper.selectProjectInfoDto(projectInfoDto);
    }

    /**
     * 根据项目id查询轮牌步骤
    * @author 洪秋霞
    * @date 2015年9月14日 下午2:27:40
    * @param projectId 项目id
    * @return List<ProjectStep>
     */
    public List<ProjectStep> queryProjectStepByPIdList(Integer projectId) {
        return projectStepMapper.queryProjectStepByPIdList(projectId);
    }

    /**
     * 根据项目id查询提成职位列表
    * @author 洪秋霞
    * @date 2015年9月23日 下午8:10:07
    * @param projectId 项目id
    * @return List<ProjectCommission>
     */
    public List<ProjectCommissionDto> queryProjectCommissionByProjectId(Integer projectId) {
        return projectCommissionMapper.selectByProjectId(projectId);
    }

    /**
     * 根据门店id查询项目列表
    * @author 洪秋霞
    * @date 2015年9月29日 下午5:28:49
    * @param stroesId 门店id
    * @return List<ProjectInfo>
     */
    public List<ProjectInfo> queryByStoreId(Integer stroesId) {
        return projectInfoMapper.selectByStoreId(stroesId);
    }
    
    /**
     * 清除部门下的项目缓存
    * @author 张进军
    * @date Oct 16, 2015 4:59:29 PM
    * @param deptId 部门标识
     */
    public void cleanRedisCacheByDeptId(int deptId){
        redisService.hdel(App.Redis.DEPT_PROJECT_BASE_INFO_KEY_HASH, deptId);
    }
    
    /**
     * 根据项目标识查询项目信息，包括可预约的员工
    * @author 张进军
    * @date Oct 18, 2015 7:11:52 PM
    * @param projectId  项目标识
    * @return   项目详情
     */
    public ProjectAppointDto selectProjectAppointByProjectId(int projectId){
        return projectInfoMapper.selectProjectAppointByProjectId(projectId);
    }
}
