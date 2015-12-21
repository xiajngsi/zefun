package com.zefun.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CodeLibraryDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.GoodsBrandDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.GoodsPurchaseRecordDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.ShipmentRecordDto;
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.ComboGoods;
import com.zefun.web.entity.GoodsBrand;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.GoodsPurchaseRecord;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShipmentRecord;
import com.zefun.web.entity.SupplierInfo;
import com.zefun.web.mapper.CodeLibraryMapper;
import com.zefun.web.mapper.ComboGoodsMapper;
import com.zefun.web.mapper.GoodsBrandMapper;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.GoodsPurchaseRecordService;
import com.zefun.web.service.MemberLevelService;
import com.zefun.web.service.SupplierInfoService;

/**
 * 商品
* @author 洪秋霞
* @date 2015年8月7日 下午5:03:49 
*
 */
@Controller
public class GoodsInfoController extends BaseController{
    /**商品*/
    @Autowired private GoodsInfoService goodsInfoService;
    /**会员等级*/
    @Autowired private MemberLevelService memberLevelService;
    /**进货记录*/
    @Autowired private GoodsPurchaseRecordService goodsPurchaseRecordService;
    /**供应商信息*/
    @Autowired private SupplierInfoService supplierInfoService;
    /**自身的品牌库*/
    @Autowired private GoodsBrandMapper goodsBrandMapper;
    /**查询图库*/
    @Autowired private CodeLibraryMapper codeLibraryMapper;
    /**套餐商品关联*/
    @Autowired private ComboGoodsMapper comboGoodsMapper;

    /**
     * 进入商品设置页面
    * @author 洪秋霞
    * @date 2015年8月10日 上午10:12:29
    * @param request request
    * @param response response
    * @param model 视图模型
    * @return ModelAndView
     */
    @RequestMapping(value = Url.GoodsInfo.GOODSINFO_LIST)
    public ModelAndView toGoodsInfoPage(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        Integer storeId = getStoreId(request);
        
        List<DeptGoodsBaseDto> deptGoodsBaseDto = goodsInfoService.getDeptGoodsByStoreId(storeId);
        model.addObject("deptGoodsBaseDto", deptGoodsBaseDto);
        model.addObject("js_deptGoodsBaseDto", JSONArray.fromObject(deptGoodsBaseDto));

        /**自己的品牌库*/
        List<GoodsBrand> brands =  goodsBrandMapper.selectByStoreId(storeId);
        model.addObject("brands", brands);
        /**智放品牌列表*/
        List<CodeLibrary> goodsBrands = goodsInfoService.selectGoodsBrandList();
        /*List<GoodsBrand> goodsBrandList = goodsInfoService.queryGoodsBrandList(storeId);*/
        model.addObject("goodsBrandList", goodsBrands);

        /** 会员等级列表 */
        List<MemberLevel> memberLevelList = memberLevelService.queryByStoreId(storeId);
        model.addObject("memberLevels", memberLevelList);
        model.addObject("memberLevelList", JSONArray.fromObject(memberLevelList));
        List<CodeLibraryDto> images = codeLibraryMapper.selectProjectImage();
        model.addObject("images", images);
        
        model.setViewName(View.GoodsInfo.GOODSINFO);
        return model;
    }

    /**
     * 保存商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:36:45
    * @param request request
    * @param response response
    * @param goodsCategory 商品类别
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_CATEGORY)
    @ResponseBody
    public BaseDto saveGoodsCategory(HttpServletRequest request, HttpServletResponse response, GoodsCategory goodsCategory) {
        try {
            goodsCategory.setStoreId(getStoreId(request));
            goodsCategory.setCreateTime(DateUtil.getCurTime());
            goodsCategory.setIsDeleted(0);
            Integer categoryId = goodsInfoService.saveGoodsCategory(goodsCategory);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, categoryId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
    /**
     * 保存商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:36:45
    * @param request request
    * @param response response
    * @param deptId 部门ID
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_CATEGORY_LIST)
    @ResponseBody
    public BaseDto saveGoodsCategoryList(HttpServletRequest request, HttpServletResponse response, Integer deptId) {
        String[] categoryName = request.getParameterValues("categoryName");
        List<GoodsCategory> ls = new ArrayList<GoodsCategory>();
        for (int i = 0; i < categoryName.length; i++) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setStoreId(getStoreId(request));
            goodsCategory.setCategoryName(categoryName[i]);
            goodsCategory.setCreateTime(DateUtil.getCurTime());
            goodsCategory.setDeptId(deptId);
            goodsCategory.setIsDeleted(0);
            Integer categoryId = goodsInfoService.saveGoodsCategory(goodsCategory);
            goodsCategory.setCategoryId(categoryId);
            ls.add(goodsCategory);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }

    /**
     * 编辑商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:37:48
    * @param request request
    * @param response response
    * @param goodsCategory 商品类别
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.EDIT_GOODS_CATEGORY)
    @ResponseBody
    public BaseDto editGoodsCategory(HttpServletRequest request, HttpServletResponse response, GoodsCategory goodsCategory) {
        try {
            goodsCategory.setStoreId(getStoreId(request));
            goodsCategory.setCreateTime(DateUtil.getCurTime());
            goodsInfoService.editGoodsCategory(goodsCategory);
            goodsInfoService.deleteGoodsRedis(goodsCategory.getDeptId().toString());
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:38:44
    * @param request request
    * @param response response
    * @param categoryId 类别Id
    * @param deptId 部门ID
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.DELETE_GOODS_CATEGORY)
    @ResponseBody
    public BaseDto deleteGoodsCategory(HttpServletRequest request, HttpServletResponse response, Integer categoryId, Integer deptId) {
        String[] goodsIds = request.getParameterValues("goodsId");
        if (goodsIds!=null&&goodsIds.length>0){
            for (int j = 0; j < goodsIds.length; j++) {
                ComboGoods comboGoods = new ComboGoods();
                comboGoods.setGoodsId(Integer.parseInt(goodsIds[j]));
                List<ComboGoods> comboGoodss = comboGoodsMapper.selectByPrimaryKey(comboGoods);
                if (comboGoodss!=null&&comboGoodss.size()>0){
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该系列下商品在套餐中引用不可删除");
                }
                goodsInfoService.deleteGoodsInfo(Integer.parseInt(goodsIds[j]));
            }
        }
        goodsInfoService.deleteGoodsCategory(categoryId);
        goodsInfoService.deleteGoodsRedis(deptId.toString());
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 保存品牌
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:21:53
    * @param request request
    * @param response response
    * @param goodsBrand 品牌
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_BRAND)
    @ResponseBody
    public BaseDto saveGoodsBrand(HttpServletRequest request, HttpServletResponse response, GoodsBrand goodsBrand) {
        try {
            goodsBrand.setStoreId(getStoreId(request));
            goodsBrand.setCreateTime(DateUtil.getCurTime());
            goodsInfoService.saveGoodsBrand(goodsBrand);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 编辑品牌
    * @author 洪秋霞
    * @date 2015年9月1日 下午2:55:42
    * @param request request
    * @param response response
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.EDIT_GOODS_BRAND)
    @ResponseBody
    public BaseDto editGoodsBrand(HttpServletRequest request, HttpServletResponse response) {
        try {
            String brandId = request.getParameter("brandId");
            String brandName = request.getParameter("brandName");
            GoodsBrand goodsBrand = new GoodsBrand();
            goodsBrand.setBrandId(Integer.parseInt(brandId));
            goodsBrand.setBrandName(brandName);
            goodsInfoService.editGoodsBrand(goodsBrand);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除品牌
    * @author 洪秋霞
    * @date 2015年9月1日 下午2:59:21
    * @param request request
    * @param response response
    * @param brandId 品牌id
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.DELETE_GOODS_BRAND)
    @ResponseBody
    public BaseDto deleteGoodsBrand(HttpServletRequest request, HttpServletResponse response, Integer brandId) {
        try {
            goodsInfoService.deleteGoodsBrand(brandId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 保存商品
    * @author 洪秋霞
    * @date 2015年8月10日 上午10:13:22
    * @param request request
    * @param response response
    * @param goodsInfo 商品信息
    * @param levelId 等级id
    * @param discountProportion 折扣比例
    * @param discountAmount 会员门店价
    * @param onlineAppointmentPrice 在线预约价
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_INFO)
    @ResponseBody
    public BaseDto saveGoodsInfo(HttpServletRequest request, HttpServletResponse response, GoodsInfo goodsInfo, String[] levelId,
            String[] discountProportion, String[] discountAmount, String[] onlineAppointmentPrice) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        goodsInfo.setStoreId(getStoreId(request));
        try {
            if (goodsInfo.getGoodsId() == null) {
                // 新增
                Integer goodsId = goodsInfoService.saveGoodsInfo(goodsInfo, levelId, discountProportion, discountAmount, onlineAppointmentPrice);
                baseDto.setMsg(goodsId);
            }
            else {
                // 编辑
                goodsInfoService.editGoodsInfo(goodsInfo, levelId, discountProportion, discountAmount, onlineAppointmentPrice);
            }
            return baseDto;
        }
        
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
        finally {
            goodsInfoService.deleteGoodsRedis(goodsInfo.getDeptId().toString());
        }
    }

    /**
     * 保存商品
    * @author 洪秋霞
    * @date 2015年8月10日 上午10:13:22
    * @param request request
    * @param response response
    * @param deptId 门店信息
    * @param categoryId 系列ID
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_INFO_LIST, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveGoodsInfoList(HttpServletRequest request, HttpServletResponse response, Integer deptId, Integer categoryId) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        String[] goodsInfoName = request.getParameterValues("goodsInfoName");
        List<GoodsInfo> ls = new ArrayList<GoodsInfo>();
        for (int i = 0; i < goodsInfoName.length; i++) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setStoreId(getStoreId(request));
            goodsInfo.setDeptId(deptId);
            goodsInfo.setCategoryId(categoryId);
            goodsInfo.setGoodsName(goodsInfoName[i]);
            Integer goodsId =  goodsInfoService.saveGoodsInfos(goodsInfo);
            goodsInfo.setGoodsId(goodsId);
            ls.add(goodsInfo);
        }
        goodsInfoService.deleteGoodsRedis(deptId.toString());
        baseDto.setMsg(ls);
        return baseDto;
    }
    
    /**
     * 根据商品id查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:24:37
    * @param request request
    * @param response response
    * @param goodsId 商品id
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.QUERY_GOODSINFO_BYID)
    @ResponseBody
    public BaseDto queryGoodsInfoById(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        try {
            GoodsInfoDto goodsInfo = goodsInfoService.queryGoodsInfoById(goodsId);
            GoodsDiscount goodsDiscount = new GoodsDiscount();
            goodsDiscount.setGoodsId(goodsId);
            List<GoodsDiscount> goodsDiscountList = goodsInfoService.queryGoodsDiscountList(goodsDiscount);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("goodsInfo", goodsInfo);
            map.put("goodsDiscountList", goodsDiscountList);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除商品信息
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:33:21
    * @param request request
    * @param response response
    * @param goodsId 商品id
    * @param deptId 部门ID
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.DELETE_GOODSINFO)
    @ResponseBody
    public BaseDto deleteGoodsInfo(HttpServletRequest request, HttpServletResponse response, Integer goodsId, Integer deptId) {
        ComboGoods comboGoods = new ComboGoods();
        comboGoods.setGoodsId(goodsId);
        List<ComboGoods> goods = comboGoodsMapper.selectByPrimaryKey(comboGoods);
        if (goods!=null&&goods.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该商品在套餐中引用,不可删除"); 
        }
        goodsInfoService.deleteGoodsInfo(goodsId);
        goodsInfoService.deleteGoodsRedis(deptId.toString());
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 进入商品库存页面
    * @author 洪秋霞
    * @date 2015年9月10日 上午10:22:31
    * @param request request
    * @param response response
    * @param model model
    * @return ModelAndView
     */
    @RequestMapping(value = Url.GoodsInfo.GOODSSTOCK_LIST)
    public ModelAndView togoodsStockPage(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        try {
            Integer storeId = getStoreId(request);
            String goodsName = request.getParameter("goodsName");
            String isSellProduct = request.getParameter("isSellProduct");
            String goodsStock = request.getParameter("goodsStock");
            String warnStock = request.getParameter("warnStock");

            GoodsInfoDto goodsInfoDto = new GoodsInfoDto();
            goodsInfoDto.setStoreId(storeId);
            goodsInfoDto.setGoodsName(goodsName);
            if (isSellProduct != null && !"".equals(isSellProduct)) {
                goodsInfoDto.setIsSellProduct(Integer.parseInt(isSellProduct));
            }
            if (goodsStock != null && !"".equals(goodsStock) && !"undefined".equals(goodsStock)) {
                goodsInfoDto.setGoodsStock(Integer.parseInt(goodsStock));
            }
            if (warnStock != null && !"".equals(warnStock) && !"undefined".equals(warnStock)) {
                goodsInfoDto.setWarnStock(Integer.parseInt(warnStock));
            }

            /** 进货记录 */
            Page<GoodsInfoDto> page = goodsInfoService.queryGoodsInfoPageList(goodsInfoDto, 1, 5);
            model.addObject("goodsStockPage", page);

            GoodsPurchaseRecordDto goodsPurchaseRecordDto = new GoodsPurchaseRecordDto();
            goodsPurchaseRecordDto.setStoreId(storeId);
            Page<GoodsPurchaseRecordDto> goodsPurchasePage = goodsPurchaseRecordService.queryGoodsPurchaseRecordDtoPage(goodsPurchaseRecordDto, 1,
                    App.System.API_DEFAULT_PAGE_SIZE);
            model.addObject("purchaseRecordsPage", goodsPurchasePage);

            // 供应商列表
            List<SupplierInfo> supplierInfoList = supplierInfoService.querySupplierInfoByStoreId(storeId);
            model.addObject("supplierInfoList", supplierInfoList);
            // 商品列表
            List<GoodsInfo> goodsInfoList = goodsInfoService.queryGoodsInfos(storeId);
            model.addObject("goodsInfoList", goodsInfoList);
            //商品类别
            List<GoodsCategory> goodsCategoryList = goodsInfoService.queryGoodsCategoryList(storeId);
            model.addObject("goodsCategoryList", goodsCategoryList);
//            //品牌列表
//            List<GoodsBrand> goodsBrandList = goodsInfoService.queryGoodsBrandList(storeId);
//            model.addObject("goodsBrandList", goodsBrandList);
            
            model.setViewName(View.GoodsInfo.GOODSSTOCK);
            return model;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 商品库存分页 change
    * @author 洪秋霞
    * @date 2015年8月11日 下午6:11:33
    * @param request request
    * @param response response
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.ACTION_LIST)
    @ResponseBody
    public BaseDto listActionGoodsStock(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize) {
        try {
            String goodsName = request.getParameter("goodsName");
//            String isSellProduct = request.getParameter("isSellProduct");
//            String goodsStock = request.getParameter("goodsStock");
//            String warnStock = request.getParameter("warnStock");
//            String brandId = request.getParameter("brandId");
//            String categoryId = request.getParameter("categoryId");
            GoodsInfoDto goodsInfoDto = new GoodsInfoDto();
            goodsInfoDto.setStoreId(getStoreId(request));
            if (!"".equals(goodsName)) {
                goodsInfoDto.setGoodsName(goodsName);
            }
//            if (isSellProduct != null && !"".equals(isSellProduct)) {
//                goodsInfoDto.setIsSellProduct(Integer.parseInt(isSellProduct));
//            }
//            if (goodsStock != null && !"".equals(goodsStock) && !"undefined".equals(goodsStock)) {
//                goodsInfoDto.setGoodsStock(Integer.parseInt(goodsStock));
//            }
//            if (warnStock != null && !"".equals(warnStock) && !"undefined".equals(warnStock)) {
//                goodsInfoDto.setWarnStock(Integer.parseInt(warnStock));
//            }
//            if (brandId != null && !"".equals(brandId) && !"undefined".equals(brandId)) {
//                goodsInfoDto.setBrandId(Integer.parseInt(brandId));
//            }
//            if (categoryId != null && !"".equals(categoryId) && !"undefined".equals(categoryId)) {
//                goodsInfoDto.setCategoryId(Integer.parseInt(categoryId));
//            }
            
            return goodsInfoService.listAction(goodsInfoDto, pageNo, pageSize);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询商品30天销售量
    * @author 洪秋霞
    * @date 2015年9月18日 上午11:05:35
    * @param request request
    * @param response response
    * @param goodsId 商品id
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.QUERY_BYGOODSSALE)
    @ResponseBody
    public BaseDto queryByGoodsSale(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        try {
            // 30天销售量
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderType(3);
            orderDetail.setProjectId(goodsId);
            List<OrderDetailDto> orderDetailDtoList = goodsInfoService.queryByGoodsSale(orderDetail);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderDetailDtoList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 进货记录分页 change
    * @author 洪秋霞
    * @date 2015年9月6日 下午5:48:33
    * @param request request
    * @param response response
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsPurchaseRecord.ACTION_LIST)
    @ResponseBody
    public BaseDto listActionGoodsPurchase(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize) {
        try {
            String goodsName = request.getParameter("goodsName");
            GoodsPurchaseRecordDto goodsPurchaseRecordDto = new GoodsPurchaseRecordDto();
            goodsPurchaseRecordDto.setStoreId(getStoreId(request));
            if (!"".equals(goodsName)) {
                goodsPurchaseRecordDto.setGoodsName(goodsName);
            }
            return goodsPurchaseRecordService.listAction(goodsPurchaseRecordDto, pageNo, pageSize);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 保存进货记录
    * @author 洪秋霞
    * @date 2015年9月7日 上午11:14:30
    * @param request request
    * @param response response
    * @param goodsPurchaseRecord 进货记录
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsPurchaseRecord.SAVE_PURCHASERECORDS)
    @ResponseBody
    public BaseDto savePurchaseRecords(HttpServletRequest request, HttpServletResponse response, GoodsPurchaseRecord goodsPurchaseRecord) {
        try {
            goodsPurchaseRecord.setStoreId(getStoreId(request));
            goodsPurchaseRecord.setOperatorId(Integer.parseInt(request.getSession().getAttribute(App.Session.USER_ID).toString()));
            goodsPurchaseRecordService.savePurchaseRecords(goodsPurchaseRecord);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
    /**
     * 根据部门查询商品类别
    * @author 高国藩
    * @date 2015年10月15日 下午1:53:27
    * @param deptId 部门Id标识 
    * @param request 请求
    * @return 查询结果
     */
    @RequestMapping(value = Url.GoodsInfo.SELECT_CATEGORY_BY_DEPT, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto listSelectCategoryByDept(Integer deptId, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return goodsInfoService.listSelectCategoryByDept(deptId, storeId);
    }
    
    /**
     * 出货管理页面
    * @author 高国藩
    * @date 2015年11月14日 上午11:11:25
    * @param request 请求
    * @return 跳转页面
     */
    @RequestMapping(value = Url.GoodsInfo.VIEW_SHIPMENT_RECORD)
    public ModelAndView viewShipmentRecord(HttpServletRequest request){
        return goodsInfoService.viewShipmentRecord(getStoreId(request));
    }
    
    /**
     * 保存出货记录
    * @author 高国藩
    * @date 2015年11月14日 上午11:12:07
    * @param request 请求
    * @param shipmentRecord 实体
    * @return 数据
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_SHIPMENT_RECORD, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto saveShipmentRecord(HttpServletRequest request, ShipmentRecord shipmentRecord){
        Integer userId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        shipmentRecord.setOperatorId(userId);
        shipmentRecord.setStoreId(getStoreId(request));
        shipmentRecord.setShipmentTime(DateUtil.getCurDate());
        return goodsInfoService.saveShipmentRecord(shipmentRecord);
    }
    
    /**
     * 分页查询出货记录
    * @author 高国藩
    * @date 2015年11月14日 上午11:12:28
    * @param request 请求
    * @param pageNo 源码
    * @param pageSize 大小
    * @param shipmentRecord 实体
    * @return 数据
     */
    @RequestMapping(value = Url.GoodsInfo.SERCH_SHIPMENT_RECORD, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto serchShipmentRecord(HttpServletRequest request, int pageNo, int pageSize, ShipmentRecordDto shipmentRecord){
        shipmentRecord.setStoreId(getStoreId(request));
        return goodsInfoService.serchShipmentRecord(shipmentRecord, pageNo, pageSize);
    }
    
    /**
     * 品牌记录页面
    * @author 高国藩
    * @date 2015年11月14日 上午11:11:25
    * @param request 请求
    * @return 跳转页面
     */
    @RequestMapping(value = Url.GoodsInfo.VIEW_BRAND)
    public ModelAndView viewBrand(HttpServletRequest request){
        return goodsInfoService.viewBrand(getStoreId(request));
    }
    
    /**
     * 新增品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:11:25
    * @param request    请求
    * @param goodsBrand 实体
    * @return           跳转页面
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_BRAND)
    @ResponseBody
    public BaseDto saveBrand(HttpServletRequest request, GoodsBrand goodsBrand){
        goodsBrand.setStoreId(getStoreId(request));
        goodsBrand.setCreateTime(DateUtil.getCurDate());
        goodsBrand.setLastOperatorId((Integer)request.getSession().getAttribute(App.Session.USER_ID));
        return goodsInfoService.saveBrand(goodsBrand);
    }
    
    /**
     * 分页查询品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:12:28
    * @param request 请求
    * @param pageNo 源码
    * @param pageSize 大小
    * @param goodsBrandDto 实体
    * @return 数据
     */
    @RequestMapping(value = Url.GoodsInfo.SERCH_BRAND, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto serchBrand(HttpServletRequest request, int pageNo, int pageSize, GoodsBrandDto goodsBrandDto){
        goodsBrandDto.setStoreId(getStoreId(request));
        return goodsInfoService.serchBrand(goodsBrandDto, pageNo, pageSize);
    }
    
    /**
     * 进入商品进货记录页面
    * @author 洪秋霞
    * @date 2015年9月10日 上午10:22:31
    * @param request  request
    * @param response response
    * @param model    model
    * @return         ModelAndView
     */
    @RequestMapping(value = Url.GoodsInfo.GOODS_PURCHASE_RECORDS)
    public ModelAndView goodsPurchaseRecords(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        try {
            Integer storeId = getStoreId(request);
            String goodsName = request.getParameter("goodsName");
            String isSellProduct = request.getParameter("isSellProduct");
            String goodsStock = request.getParameter("goodsStock");
            String warnStock = request.getParameter("warnStock");

            GoodsInfoDto goodsInfoDto = new GoodsInfoDto();
            goodsInfoDto.setStoreId(storeId);
            goodsInfoDto.setGoodsName(goodsName);
            if (isSellProduct != null && !"".equals(isSellProduct)) {
                goodsInfoDto.setIsSellProduct(Integer.parseInt(isSellProduct));
            }
            if (goodsStock != null && !"".equals(goodsStock) && !"undefined".equals(goodsStock)) {
                goodsInfoDto.setGoodsStock(Integer.parseInt(goodsStock));
            }
            if (warnStock != null && !"".equals(warnStock) && !"undefined".equals(warnStock)) {
                goodsInfoDto.setWarnStock(Integer.parseInt(warnStock));
            }

            /** 进货记录 */
            Page<GoodsInfoDto> page = goodsInfoService.queryGoodsInfoPageList(goodsInfoDto, 1, 5);
            model.addObject("goodsStockPage", page);

            GoodsPurchaseRecordDto goodsPurchaseRecordDto = new GoodsPurchaseRecordDto();
            goodsPurchaseRecordDto.setStoreId(storeId);
            Page<GoodsPurchaseRecordDto> goodsPurchasePage = goodsPurchaseRecordService.queryGoodsPurchaseRecordDtoPage(goodsPurchaseRecordDto, 1,
                    App.System.API_DEFAULT_PAGE_SIZE);
            model.addObject("purchaseRecordsPage", goodsPurchasePage);

            // 供应商列表
            List<SupplierInfo> supplierInfoList = supplierInfoService.querySupplierInfoByStoreId(storeId);
            model.addObject("supplierInfoList", supplierInfoList);
            // 商品列表
            List<GoodsInfo> goodsInfoList = goodsInfoService.queryGoodsInfos(storeId);
            model.addObject("goodsInfoList", goodsInfoList);
            //商品类别
            List<GoodsCategory> goodsCategoryList = goodsInfoService.queryGoodsCategoryList(storeId);
            model.addObject("goodsCategoryList", goodsCategoryList);
            
            model.setViewName(View.GoodsInfo.GOODS_PURCHASE_RECORDS);
            return model;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
