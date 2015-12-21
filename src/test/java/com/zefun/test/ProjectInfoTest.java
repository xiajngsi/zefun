package com.zefun.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.controller.ProjectInfoController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ProjectAppointDto;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.service.ProjectService;

/**
 * 项目设置
* @author 洪秋霞
* @date 2015年8月10日 下午4:53:01 
*
 */
public class ProjectInfoTest extends BaseTest {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(ProjectInfoTest.class);

    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();

    /**
     * 项目信息Controller
     */
    @Autowired private ProjectInfoController projectInfoController;
    /**
     * 项目信息Controller
     */
    @Autowired private ProjectService projectService;

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:45:52
     */
    @Test
    public void queryProjectInfoById() {
        Integer projectId = 1;
        BaseDto dto = projectInfoController.queryProjectInfoById(request, response, projectId);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 保存
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:45:48
     */
    @Test
    public void saveProject() {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setStoreId(1);
        projectInfo.setCategoryId(1);
        projectInfo.setProjectName("项目名称test");
        projectInfo.setProjectDesc("desc");
        projectInfo.setProjectPrice(new BigDecimal(100));
        projectInfo.setCostPrice(new BigDecimal(50));
        projectInfo.setIsWechatSell(0);
        projectInfo.setIsDisable(0);
        projectInfo.setIsDeleted(0);
        projectInfo.setCreateTime(DateUtil.getCurTime());
        
        request.addParameter("levelId", new String[] { "1", "2" });
        request.addParameter("discountProportion", new String[] { "1", "2" });
        request.addParameter("discountAmount", new String[] { "1", "2" });
        request.addParameter("empLevelId", new String[] { "1", "2" });
        request.addParameter("assignType", new String[] { "1", "2" });
        request.addParameter("assignAmount", new String[] { "1", "2" });
        request.addParameter("nonAssignType", new String[] { "1", "2" });
        request.addParameter("nonAssignAmount", new String[] { "1", "2" });
        BaseDto dto = projectInfoController.saveProject(request, response, projectInfo);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 编辑
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:45:44
     */
    @Test
    public void editProjectInfo() {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectId(1);
        projectInfo.setStoreId(1);
        projectInfo.setCategoryId(1);
        projectInfo.setProjectName("项目名称test");
        projectInfo.setProjectDesc("desc");
        projectInfo.setProjectPrice(new BigDecimal(100));
        projectInfo.setCostPrice(new BigDecimal(50));
        projectInfo.setIsWechatSell(0);
        projectInfo.setIsDisable(0);
        projectInfo.setIsDeleted(0);
        projectInfo.setCreateTime(DateUtil.getCurTime());

        request.addParameter("levelId", new String[] { "1", "2" });
        request.addParameter("discountProportion", new String[] { "1", "2" });
        request.addParameter("discountAmount", new String[] { "1", "2" });
        request.addParameter("empLevelId", new String[] { "1", "2" });
        request.addParameter("assignType", new String[] { "1", "2" });
        request.addParameter("assignAmount", new String[] { "1", "2" });
        request.addParameter("nonAssignType", new String[] { "1", "2" });
        request.addParameter("nonAssignAmount", new String[] { "1", "2" });
        BaseDto dto = projectInfoController.saveProject(request, response, projectInfo);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }
    
    /**
     * 测试手机端项目预览
    * @author 高国藩
    * @date 2015年12月5日 上午10:21:44
     */
    @Test
    public void debugProject() {
        ProjectAppointDto projectDetail = projectService.selectProjectAppointByProjectId(567);
        logger.info(JSONObject.fromObject(projectDetail).toString());
        List<String> imgList = new ArrayList<String>();
        imgList.add(projectDetail.getProjectImage());
    }
    
}
