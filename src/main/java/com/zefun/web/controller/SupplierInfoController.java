package com.zefun.web.controller;

import java.util.List;

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
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.SupplierInfo;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.SupplierInfoService;

/**
 * 供应商信息
* @author 洪秋霞
* @date 2015年8月11日 下午5:59:59
 */
@Controller
public class SupplierInfoController extends BaseController {

    /**门店id*/
//    private Integer storeId = 1;
    /** 供应商信息 */
    @Autowired private SupplierInfoService supplierInfoService;
    /** 商品 */
    @Autowired private GoodsInfoService goodsInfoService;

    /**
     * 进入供应商页面 
    * @author 洪秋霞
    * @date 2015年8月11日 下午6:11:19
    * @param request request
    * @param response response
    * @param model 视图模型
    * @return ModelAndView
     */
    @RequestMapping(value = Url.SupplierInfo.SUPPLIERINFO_LIST)
    public ModelAndView toSupplierInfo(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        try {
            Integer storeId = getStoreId(request);
            SupplierInfo supplierInfo = new SupplierInfo();
            supplierInfo.setStoreId(storeId);
            Page<SupplierInfo> page = supplierInfoService.querySupplierInfoPage(supplierInfo, 1, App.System.API_DEFAULT_PAGE_SIZE);
            model.addObject("page", page);

            // 供应商列表
//            List<SupplierInfo> supplierInfoList = supplierInfoService.querySupplierInfoByStoreId(storeId);
//            model.addObject("supplierInfoList", supplierInfoList);
            // 品牌列表
            List<CodeLibrary> goodsBrandList = goodsInfoService.queryGoodsBrandList(storeId);
            model.addObject("goodsBrandList", goodsBrandList);
            // 商品类别
            List<GoodsCategory> goodsCategoryList = goodsInfoService.queryGoodsCategoryList(storeId);
            model.addObject("goodsCategoryList", goodsCategoryList);

            model.setViewName(View.SupplierInfo.SUPPLIERINFO);
            return model;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 供应商分页 change
    * @author 洪秋霞
    * @date 2015年8月11日 下午6:11:33
    * @param request request
    * @param response response
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return BaseDto
     */
    @RequestMapping(value = Url.SupplierInfo.ACTION_LIST)
    @ResponseBody
    public BaseDto listAction(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize) {
        try {
            String supplierName = request.getParameter("supplierName");
            SupplierInfo supplierInfo = new SupplierInfo();
            if (null != supplierName && !"undefined".equals(supplierName)) {
                supplierInfo.setSupplierName(supplierName);
            }
            supplierInfo.setStoreId(getStoreId(request));
            return supplierInfoService.listAction(supplierInfo, pageNo, pageSize);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存供应商信息
    * @author 洪秋霞
    * @date 2015年8月12日 上午9:51:04
    * @param request request
    * @param response response
    * @param supplierInfo 供应商信息
    * @return BaseDto
     */
    @RequestMapping(value = Url.SupplierInfo.SAVE_SUPPLIERINFO)
    @ResponseBody
    public BaseDto saveSupplierInfo(HttpServletRequest request, HttpServletResponse response, SupplierInfo supplierInfo) {
        try {
            if (supplierInfo.getSupplierId() == null) {
                // 保存
                supplierInfo.setStoreId(getStoreId(request));
                supplierInfo.setThirtySales(0);
                supplierInfo.setCreateTime(DateUtil.getCurTime());
                supplierInfoService.saveSupplierInfo(supplierInfo);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
            }
            else {
                // 编辑
                supplierInfoService.editSupplierInfo(supplierInfo);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 根据id查询供应商信息
    * @author 洪秋霞
    * @date 2015年8月12日 上午10:10:10
    * @param request request
    * @param response response
    * @param supplierId 供应商id
    * @return BaseDto
     */
    @RequestMapping(value = Url.SupplierInfo.QUERY_SUPPLIERINFO_BYID)
    @ResponseBody
    public BaseDto querySupplierInfoById(HttpServletRequest request, HttpServletResponse response, Integer supplierId) {
        try {
            SupplierInfo supplierInfo = supplierInfoService.querySupplierInfoById(supplierId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, supplierInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除供应商信息
    * @author 洪秋霞
    * @date 2015年8月12日 下午2:00:19
    * @param request request
    * @param response response
    * @param supplierId 供应商id
    * @return BaseDto
     */
    @RequestMapping(value = Url.SupplierInfo.DELETE_SUPPLIERINFO)
    @ResponseBody
    public BaseDto deleteSupplierInfo(HttpServletRequest request, HttpServletResponse response, Integer supplierId) {
        try {
            supplierInfoService.deleteSupplierInfo(supplierId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
}
