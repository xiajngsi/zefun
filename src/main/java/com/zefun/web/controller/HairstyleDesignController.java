package com.zefun.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.HairstyleCategoryDto;
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.HairstyleCategory;
import com.zefun.web.entity.HairstyleDesign;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.HairstyleDesignService;
import com.zefun.web.service.ProjectService;

/**
 * 发型设置
* @author 洪秋霞
* @date 2015年9月8日 上午11:16:41
 */
@Controller
public class HairstyleDesignController {

    /**发型设置*/
    @Autowired private HairstyleDesignService hairstyleDesignService;
    /** 商品 */
    @Autowired private GoodsInfoService goodsInfoService;
    /** 项目 */
    @Autowired private ProjectService projectService;

    /**门店id*/
    private Integer storeId = 1002;

    /**
     * 进入发型设置页面
    * @author 洪秋霞
    * @date 2015年9月8日 上午11:20:49
    * @param request request
    * @param response response
    * @param model model
    * @return ModelAndView
     */
    @RequestMapping(value = Url.HairstyleDesign.TO_HAIRSTYLEDESIGN)
    public ModelAndView toHairstyleDesign(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        try {
            // 发型类别和发型列表
            List<HairstyleCategoryDto> categoryList = hairstyleDesignService.queryHairstyleDesignCategory(storeId);
            model.addObject("categoryList", categoryList);

            // 发型类别列表
            List<HairstyleCategory> hairstyleCategoryList = hairstyleDesignService.queryHairstyleCategory(storeId);
            model.addObject("hairstyleCategoryList", hairstyleCategoryList);

            // 发型列表
            List<HairstyleDesign> hairstyleDesignList = hairstyleDesignService.queryHairstyleDesign(storeId);
            model.addObject("hairstyleDesignList", hairstyleDesignList);

            // 发型属性列表
            List<Integer> typeNos = new ArrayList<Integer>();
            typeNos.add(201); // 头发
            typeNos.add(202); // 发质
            typeNos.add(203); // 样式
            typeNos.add(204); // 场景
            List<CodeLibrary> codeLibraries = hairstyleDesignService.queryByTypeNos(typeNos);
            Set<String> set = new HashSet<String>();
            for (int i = 0; i < codeLibraries.size(); i++) {
                String typeNo = codeLibraries.get(i).getTypeName();
                set.add(typeNo);
            }
            Iterator<String> it = set.iterator();
            Map<String, List<CodeLibrary>> map = new HashMap<String, List<CodeLibrary>>();
            while (it.hasNext()) {
                List<CodeLibrary> lsCodeLibraries = new ArrayList<CodeLibrary>();
                String typeName = it.next();
                for (int i = 0; i < codeLibraries.size(); i++) {
                    String typeName2 = codeLibraries.get(i).getTypeName();
                    if (typeName.equals(typeName2)) {
                        lsCodeLibraries.add(codeLibraries.get(i));
                    }
                }
                map.put(typeName, lsCodeLibraries);
            }
//            model.addObject("set", set);
            model.addObject("codeLibraries", map);

//            List<CodeLibrary> codeLibraryHairList = hairstyleDesignService.queryByTypeNo(201);
//            List<CodeLibrary> codeLibraryHairQualityList = hairstyleDesignService.queryByTypeNo(202);
//            List<CodeLibrary> codeLibraryStyleList = hairstyleDesignService.queryByTypeNo(203);
//            List<CodeLibrary> codeLibraryScenarioList = hairstyleDesignService.queryByTypeNo(204);
//            model.addObject("codeLibraryHairList", codeLibraryHairList);
//            model.addObject("codeLibraryHairQualityList", codeLibraryHairQualityList);
//            model.addObject("codeLibraryStyleList", codeLibraryStyleList);
//            model.addObject("codeLibraryScenarioList", codeLibraryScenarioList);

            // 产品列表
            List<GoodsInfo> goodsInfoList = goodsInfoService.queryGoodsInfos(storeId);
            model.addObject("goodsInfoList", goodsInfoList);

            // 项目列表
            List<ProjectInfo> projectInfoList = projectService.queryByStoreId(storeId);
            model.addObject("projectInfoList", projectInfoList);

            model.setViewName(View.HairstyleDesign.HAIRSTYLEDESIGN);
            return model;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:37:58
    * @param request request
    * @param response response
    * @param hairstyleCategory 发型类别
    * @return BaseDto
     */
    @RequestMapping(value = Url.HairstyleDesign.SAVE_HAIRSTYLECATEGORY)
    @ResponseBody
    public BaseDto saveHairstyleCategory(HttpServletRequest request, HttpServletResponse response, HairstyleCategory hairstyleCategory) {
        try {
            hairstyleCategory.setStoreId(storeId);
            hairstyleCategory.setCreateTime(DateUtil.getCurTime());
            hairstyleDesignService.saveHairstyleCategory(hairstyleCategory);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 修改发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:41:09
    * @param request request
    * @param response response
    * @param hairstyleCategory 发型类别
    * @return BaseDto
     */
    @RequestMapping(value = Url.HairstyleDesign.EDIT_HAIRSTYLECATEGORY)
    @ResponseBody
    public BaseDto editHairstyleCategory(HttpServletRequest request, HttpServletResponse response, HairstyleCategory hairstyleCategory) {
        try {
            hairstyleDesignService.updateHairstyleCategory(hairstyleCategory);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 下午4:05:09
    * @param request request
    * @param response response
    * @param hairstyleCategoryId 发型类别id
    * @return BaseDto
     */
    @RequestMapping(value = Url.HairstyleDesign.DELETE_HAIRSTYLECATEGORY)
    @ResponseBody
    public BaseDto deleteHairstyleCategory(HttpServletRequest request, HttpServletResponse response, Integer hairstyleCategoryId) {
        try {
            hairstyleDesignService.deleteHairstyleCategory(hairstyleCategoryId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 根据id查询发型
    * @author 洪秋霞
    * @date 2015年9月8日 下午4:24:26
    * @param request request
    * @param response response
    * @param hairstyleId 发型id
    * @return BaseDto
     */
    @RequestMapping(value = Url.HairstyleDesign.QUERY_HAIRSTYLEDESIGNBYID)
    @ResponseBody
    public BaseDto queryHairstyleDesignById(HttpServletRequest request, HttpServletResponse response, Integer hairstyleId) {
        try {
            HairstyleDesign hairstyleDesign = hairstyleDesignService.queryHairstyleDesignById(hairstyleId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("hairstyleDesign", hairstyleDesign);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 保存发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:26:44
    * @param request request
    * @param response response
    * @return BaseDto
     */
    @RequestMapping(value = Url.HairstyleDesign.SAVE_HAIRSTYLEDESIGN)
    @ResponseBody
    public BaseDto saveHairstyleDesign(HttpServletRequest request, HttpServletResponse response) {
        try {
            String hairstyleCategoryId = request.getParameter("hairstyleCategoryId");
            String hairstyleTitle = request.getParameter("hairstyleTitle");
            String hairstyleContent = request.getParameter("hairstyleContent");
            String[] affiliatedImg = request.getParameterValues("affiliatedImg");
            
            String[] style = request.getParameterValues("样式");
            String[] hair = request.getParameterValues("头发");
            String[] hairQuality = request.getParameterValues("发质");
            String[] scenario = request.getParameterValues("场景");
            
            String goodsId = request.getParameter("goodsId");
            String projectId = request.getParameter("projectId");
            
            String affiliatedImgStr = "";
            for (int i = 0; i < affiliatedImg.length; i++) {
                affiliatedImgStr += affiliatedImg[i] + ",";
            }
            
            String styleStr = "";
            for (int i = 0; i < style.length; i++) {
                styleStr += style[i] + ",";
            }
            
            String hairStr = "";
            for (int i = 0; i < hair.length; i++) {
                hairStr += hair[i] + ",";
            }
            
            String hairQualityStr = "";
            for (int i = 0; i < hairQuality.length; i++) {
                hairQualityStr += hairQuality[i] + ",";
            }
            
            String scenarioStr = "";
            for (int i = 0; i < scenario.length; i++) {
                scenarioStr += scenario[i] + ",";
            }
            
            HairstyleDesign hairstyleDesign = new HairstyleDesign();
            hairstyleDesign.setHairstyleCategoryId(Integer.parseInt(hairstyleCategoryId));
            hairstyleDesign.setHairstyleTitle(hairstyleTitle);
            hairstyleDesign.setHairstyleContent(hairstyleContent);
            hairstyleDesign.setAffiliatedImg(affiliatedImgStr);
            hairstyleDesign.setStyle(styleStr);
            hairstyleDesign.setHair(hairStr);
            hairstyleDesign.setHairQuality(hairQualityStr);
            hairstyleDesign.setScenario(scenarioStr);
            hairstyleDesign.setRelatedProduct(goodsId);
            hairstyleDesign.setRelatedProject(projectId);
            hairstyleDesign.setStoreId(storeId);
            hairstyleDesign.setCreateTime(DateUtil.getCurTime());
            hairstyleDesignService.saveHairstyleDesign(hairstyleDesign);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

}
