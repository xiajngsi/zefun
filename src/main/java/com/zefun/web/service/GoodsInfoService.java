package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EntityJsonConverter;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.GoodsBrandDto;
import com.zefun.web.dto.GoodsCategoryDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.ShipmentRecordDto;
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.GoodsBrand;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShipmentRecord;
import com.zefun.web.mapper.CodeLibraryMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsBrandMapper;
import com.zefun.web.mapper.GoodsCategoryMapper;
import com.zefun.web.mapper.GoodsDiscountMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.ShipmentRecordMapper;

import net.sf.json.JSONArray;

/**
 * 商品
* @author 洪秋霞
* @date 2015年8月7日 下午5:04:10 
*
 */
@Service
public class GoodsInfoService {
    /**
     * 商品品牌
     */
    @Autowired private GoodsBrandMapper goodsBrandMapper;
    /**
     * 商品信息
     */
    @Autowired private GoodsInfoMapper goodsInfoMapper;
    /**
     * 商品会员折扣
     */
    @Autowired private GoodsDiscountMapper goodsDiscountMapper;
    /**
     * 商品类别
     */
    @Autowired private GoodsCategoryMapper goodsCategoryMapper;
    
    /**部门信息*/
    @Autowired private DeptInfoMapper deptInfoMapper;
    
    /**订单详情*//*
    @Autowired private OrderDetailMapper orderDetailMapper;*/
    
    /** redis api 操作服务对象 */
    @Autowired private RedisService redisService;
    
    /** 数据字典 */
    @Autowired private CodeLibraryMapper codeLibraryMapper;
    /**出货记录*/
    @Autowired
    private ShipmentRecordMapper shipmentRecordMapper;
    /**员工信息操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    /**
     * 获取品牌列表和商品列表
    * @author 洪秋霞
    * @date 2015年8月31日 下午5:07:52
    * @param storeId 门店id
    * @return List<GoodsBrand>
     */
    public List<GoodsBrand> getGoodsBrandInfo(Integer storeId) {
        return goodsBrandMapper.getGoodsBrandInfo(storeId);
    }
    
    /**
     * 获取部门，商品类别和商品列表
    * @author 洪秋霞
    * @date 2015年9月16日 下午2:58:47
    * @param storeId 门店id
    * @return List<DeptInfoDto>
     */
    public List<DeptInfoDto> getDetpInfoByGoods(Integer storeId){
        return goodsCategoryMapper.getDetpInfoByGoods(storeId);
    }

    /**
     * 查询部门列表
    * @author 洪秋霞
    * @date 2015年9月11日 下午3:47:00
    * @param storeId 门店id
    * @return List<DeptInfo>
     */
    public List<DeptInfo> queryDeptInfoList(Integer storeId){
        return deptInfoMapper.getResultsDeptInfo(storeId);
    }
    
    /**
     * 查询商品列表
    * @author 洪秋霞
    * @date 2015年8月7日 下午5:22:59
    * @param storeId 门店Id
    * @return List<GoodsInfo>
     */
    public List<GoodsInfo> queryGoodsInfos(Integer storeId) {
        return goodsInfoMapper.selectByStoreId(storeId);
    }

    /**
     * 商品列表 change 分页
    * @author 洪秋霞
    * @date 2015年8月7日 下午5:46:26
    * @param goodsInfo 商品信息
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return BaseDto
     */
    public BaseDto listAction(GoodsInfoDto goodsInfo, int pageNo, int pageSize) {
        Page<GoodsInfoDto> page = queryGoodsInfoPageList(goodsInfo, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 查询商品列表 分页
    * @author 洪秋霞
    * @date 2015年8月7日 下午5:45:13
    * @param goodsInfo 商品信息
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return Page<GoodsInfoDto>
     */
    public Page<GoodsInfoDto> queryGoodsInfoPageList(GoodsInfoDto goodsInfo, int pageNo, int pageSize) {
        Page<GoodsInfoDto> page = new Page<GoodsInfoDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("goodsInfo", goodsInfo);
        page.setParams(params);
        List<GoodsInfoDto> list = goodsInfoMapper.selectGoodsInfoPage(page);
        page.setResults(list);
        return page;
    }

    /**
     * 查询商品类别
    * @author 洪秋霞
    * @date 2015年8月10日 上午11:09:07
    * @param storeId 门店id
    * @return List<GoodsCategory>
     */
    public List<GoodsCategory> queryGoodsCategoryList(Integer storeId) {
        return goodsCategoryMapper.selectByStoreId(storeId);
    }

    /**
     * 查询品牌列表
    * @author 洪秋霞
    * @date 2015年8月10日 上午11:00:16
    * @param storeId 门店id
    * @return List<GoodsBrand>
     */
    public List<CodeLibrary> queryGoodsBrandList(Integer storeId) {
        return codeLibraryMapper.selectByTypeNo(App.CodeLibary.GOODS_TYPE_NO);
//        return goodsBrandMapper.selectByStoreId(storeId);
    }

    /**
     * 保存商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:33:15
    * @param goodsCategory 商品类别
    * @return Integer
     */
    public Integer saveGoodsCategory(GoodsCategory goodsCategory){
        goodsCategoryMapper.insertSelective(goodsCategory);
        redisService.hdel(App.Redis.DEPT_GOODS_BASE_INFO_KEY_HASH, goodsCategory.getDeptId().toString());
        return goodsCategory.getCategoryId();
    }
    
    /**
     * 编辑商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:33:56
    * @param goodsCategory 商品类别
     */
    public void editGoodsCategory(GoodsCategory goodsCategory){
        goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
    }
    
    /**
     * 删除商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:34:32
    * @param categoryId 商品类别Id
     */
    public void deleteGoodsCategory(Integer categoryId){
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setCategoryId(categoryId);
        List<GoodsInfo> ls = goodsInfoMapper.selectByProperty(goodsInfo);
        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setIsDeleted(1);
            goodsInfoMapper.updateByPrimaryKeySelective(ls.get(i));
        }
        //goodsInfoMapper.deleteByCategoryId(categoryId);
        GoodsCategory category = new GoodsCategory();
        category.setCategoryId(categoryId);
        category.setIsDeleted(1);
        goodsCategoryMapper.updateByPrimaryKeySelective(category);
        //goodsCategoryMapper.deleteByPrimaryKey(categoryId);
    }
    
    /**
     * 保存品牌
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:47:58
    * @param goodsBrand 商品品牌
     */
    public void saveGoodsBrand(GoodsBrand goodsBrand) {
        goodsBrandMapper.insertSelective(goodsBrand);
    }

    /**
     * 编辑商品品牌
    * @author 洪秋霞
    * @date 2015年9月1日 下午2:53:23
    * @param goodsBrand 商品品牌
     */
    public void editGoodsBrand(GoodsBrand goodsBrand) {
        goodsBrandMapper.updateByPrimaryKeySelective(goodsBrand);
    }

    /**
     * 删除商品品牌
    * @author 洪秋霞
    * @date 2015年9月1日 下午2:54:12
    * @param brandId 品牌id
     */
    public void deleteGoodsBrand(Integer brandId) {
        goodsBrandMapper.deleteByPrimaryKey(brandId);
    }

    /**
     * 保存商品
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:48:17
    * @param goodsInfo 商品信息
    * @param levelId 会员等级id
    * @param discountProportion 折扣比例
    * @param discountAmount 会员门店价
    * @param onlineAppointmentPrice 在线预约价
    * @return Integer
     */
    @Transactional
    public Integer saveGoodsInfo(GoodsInfo goodsInfo, String[] levelId, String[] discountProportion, String[] discountAmount,
            String[] onlineAppointmentPrice) {
        goodsInfo.setCreateTime(DateUtil.getCurTime());
        goodsInfoMapper.insertSelective(goodsInfo);
        Integer goodsId = goodsInfo.getGoodsId();
        List<GoodsDiscount> discounts = new ArrayList<GoodsDiscount>();
        if (levelId != null && !levelId.equals("")){
            for (int i = 0; i < levelId.length; i++) {
                GoodsDiscount goodsDiscount = new GoodsDiscount();
                // 保存商品会员折扣
                goodsDiscount.setGoodsId(goodsId);
                goodsDiscount.setLevelId(Integer.parseInt(levelId[i]));
                // 页面去掉了折扣比例和在线预约价
                goodsDiscount.setDiscountProportion(Integer.parseInt(discountAmount[i].trim()));
                goodsDiscount.setDiscountAmount(new BigDecimal(discountAmount[i].trim()));
                goodsDiscount.setOnlineAppointmentPrice(new BigDecimal(discountAmount[i].trim()));
                goodsDiscount.setCreateTime(DateUtil.getCurTime());
                discounts.add(goodsDiscount);
            }
            goodsDiscountMapper.insertGoodsDiscountList(discounts);
        }
        return goodsId;
    }
    
    /**
     * 批量保存商品
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:48:17
    * @param goodsInfo 商品信息
    * @return Integer
     */
    @Transactional
    public Integer saveGoodsInfos(GoodsInfo goodsInfo) {
        goodsInfo.setCreateTime(DateUtil.getCurTime());
        goodsInfoMapper.insertSelective(goodsInfo);
        Integer goodsId = goodsInfo.getGoodsId();
        return goodsId;
    }
    
    /**
     * 编辑商品
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:49:12
    * @param goodsInfo 商品信息
    * @param levelId 会员等级id
    * @param discountProportion 折扣比例
    * @param discountAmount 会员门店价
    * @param onlineAppointmentPrice 在线预约价
     */
    @Transactional
    public void editGoodsInfo(GoodsInfo goodsInfo, String[] levelId, String[] discountProportion, String[] discountAmount,
            String[] onlineAppointmentPrice) {
        //更新商品
        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);

        GoodsDiscount goodsDiscount = new GoodsDiscount();
        goodsDiscount.setGoodsId(goodsInfo.getGoodsId());
        List<GoodsDiscount> goodsDiscountList = goodsDiscountMapper.selectByProperty(goodsDiscount);
        for (int i = 0; i < goodsDiscountList.size(); i++) {
            goodsDiscountMapper.deleteByPrimaryKey(goodsDiscountList.get(i).getDiscountId());
        }
        
        if (levelId != null && !levelId.equals("")){
            List<GoodsDiscount> discountList = new ArrayList<GoodsDiscount>();
            for (int i = 0; i < levelId.length; i++) {
                GoodsDiscount discount = new GoodsDiscount();
                discount.setGoodsId(goodsInfo.getGoodsId());
                discount.setLevelId(Integer.parseInt(levelId[i]));
                //折扣比例和在线预约去掉了,此处先代替一下了
                discount.setDiscountProportion(Integer.parseInt(discountAmount[i].trim()));
                discount.setDiscountAmount(new BigDecimal(discountAmount[i].trim()));
                discount.setOnlineAppointmentPrice(new BigDecimal(discountAmount[i].trim()));
                discount.setCreateTime(DateUtil.getCurTime());
                discountList.add(discount);
            }
            goodsDiscountMapper.insertGoodsDiscountList(discountList);
        }
    }

    /**
     * 查询商品会员折扣列表
    * @author 洪秋霞
    * @date 2015年8月10日 下午2:46:46
    * @param goodsDiscount 商品会员折扣
    * @return List<GoodsDiscount>
     */
    public List<GoodsDiscount> queryGoodsDiscountList(GoodsDiscount goodsDiscount) {
        return goodsDiscountMapper.selectByProperty(goodsDiscount);
    }

    /**
     * 根据id查询商品
    * @author 洪秋霞
    * @date 2015年8月10日 下午2:33:52
    * @param goodsId 商品id
    * @return GoodsInfo
     */
    public GoodsInfoDto queryGoodsInfoById(Integer goodsId) {
        return goodsInfoMapper.selectGoodsAllByPrimaryKey(goodsId);
    }

    /**
     * 删除商品信息
    * @author 洪秋霞
    * @date 2015年8月10日 下午2:40:52
    * @param goodsId 商品id
     */
    public void deleteGoodsInfo(Integer goodsId) {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsId(goodsId);
        goodsInfo.setIsDeleted(1);
        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }
    
    /**
     * 查询商品30天销售量
    * @author 洪秋霞
    * @date 2015年9月18日 上午10:53:46
    * @param orderDetail 订单详情
    * @return List<OrderDetailDto>
     */
    public List<OrderDetailDto> queryByGoodsSale(OrderDetail orderDetail){
//        return orderDetailMapper.selectByGoodsSale(orderDetail);
        return null;
    }

    /**
     * 根据部门查询商品累呗
    * @author 高国藩
    * @date 2015年10月15日 下午1:56:05
    * @param deptId 部门标识ID
    * @param storeId 请求
    * @return 返回查询结果
     */
    public BaseDto listSelectCategoryByDept(Integer deptId, Integer storeId) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setStoreId(storeId);
        goodsCategory.setDeptId(deptId);
        List<GoodsCategoryDto> ls = goodsCategoryMapper.selectByGoodsCategory(goodsCategory);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }

    /**
     * 查询门店下所有部门的所有商品信息
    * @author 高国藩
    * @date Oct 14, 2015 11:41:56 PM
    * @param storeId    门店标识
    * @return   所有部门下的商品商品类别及其下的商品列表
     */
    public List<DeptGoodsBaseDto> getDeptGoodsByStoreId(Integer storeId) {
        List<Integer> deptIdList = deptInfoMapper.selectDeptIdByStoreIdIsResults(storeId);
        if (deptIdList.isEmpty()) {
            return null;
        }
        
        List<DeptGoodsBaseDto> deptGoodsList = new ArrayList<DeptGoodsBaseDto>(deptIdList.size());
        for (Integer deptId : deptIdList) {
            DeptGoodsBaseDto deptGoods = getDeptGoodsByDeptId(deptId);
            deptGoodsList.add(deptGoods);
        }
        return deptGoodsList;
    }

    /**
     * 根据部门编号查询部门下所有商品信息，包含商品类别
    * @author 高国藩
    * @date Oct 15, 2015 12:13:00 AM
    * @param deptId 部门编号
    * @return   商品列表
     */
    public DeptGoodsBaseDto getDeptGoodsByDeptId(Integer deptId) {
        String deptGoodsBaseInfoJson = redisService.hget(App.Redis.DEPT_GOODS_BASE_INFO_KEY_HASH, deptId);
        DeptGoodsBaseDto deptGoods = null;
        //首先从缓存中获取，如果缓存中不存在，则从数据库查出并缓存
        if (StringUtils.isBlank(deptGoodsBaseInfoJson)) {
            deptGoods = goodsInfoMapper.selectDeptGoodsByDeptId(deptId);
            if (deptGoods == null) {
                return null;
            }
            redisService.hset(App.Redis.DEPT_GOODS_BASE_INFO_KEY_HASH, deptId, EntityJsonConverter.entity2Json(deptGoods));
        }
        //缓存中存在则直接转换为对象
        else {
            deptGoods = EntityJsonConverter.json2Entity(deptGoodsBaseInfoJson, DeptGoodsBaseDto.class);
        }
        return deptGoods;
    }
    
    /**
     * 删除商品redis缓存
    * @author 高国藩
    * @date 2015年10月16日 下午5:43:01
    * @param deptId 部门ID
     */
    public void deleteGoodsRedis(String deptId){
        redisService.hdel(App.Redis.DEPT_GOODS_BASE_INFO_KEY_HASH, deptId);
    }

    /**
     * 查询商品品牌列表
    * @author 高国藩
    * @date 2015年10月19日 上午9:48:43
    * @return 返回数据字典结果集
     */
    public List<CodeLibrary> selectGoodsBrandList() {
        return codeLibraryMapper.selectByTypeNo(205);
    }

    /**
     * 查询
    * @author 高国藩
    * @date 2015年11月10日 下午4:11:56
    * @param goodsInfo 实体属性
    * @return 集合
     */
    public List<GoodsInfo> selectGoodsInfos(GoodsInfo goodsInfo) {
        return goodsInfoMapper.selectByProperty(goodsInfo);
    }

    /**
     * 查询出货记录表
     * @param storeId   门店标识
     * @return  出货记录页面
     */
    public ModelAndView viewShipmentRecord(Integer storeId) {
        ShipmentRecord shipmentRecord = new ShipmentRecord();
        shipmentRecord.setStoreId(storeId);
        Page<ShipmentRecordDto> page = new Page<ShipmentRecordDto>();
        page.setPageNo(1);
        page.setPageSize(5);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<ShipmentRecordDto> records = shipmentRecordMapper.selectByPage(page);
        page.setResults(records);
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectByStoreId(storeId);
        List<EmployeeInfo> employeeInfos = employeeInfoMapper.getRecommendlist(storeId);
        ModelAndView view = new ModelAndView(View.GoodsInfo.SHIP_MENT_RECORD);
        view.addObject("goodsInfoList", goodsInfoList);
        view.addObject("employeeInfos", employeeInfos);
        view.addObject("employee", JSONArray.fromObject(employeeInfos));
        view.addObject("page", page);
        return view;
    }

    /**
     * 新增出货记录
     * @param shipmentRecord    出货信息
     * @return  成功返回码为0，失败为其他返回码
     */
    public BaseDto saveShipmentRecord(ShipmentRecord shipmentRecord) {
      //修改商品库存数量
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(shipmentRecord.getGoodsId());
        Integer goodsStock = goodsInfo.getGoodsStock();
        Integer shipmentCount = shipmentRecord.getShipmentCount();
        if (goodsStock<shipmentCount){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "库存不足");
        }
        goodsInfo.setGoodsStock(goodsStock - shipmentCount);
        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
        int ok = shipmentRecordMapper.insertSelective(shipmentRecord);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shipmentRecord);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    
    /**
     * 查询出货记录
    * @author 高国藩
    * @date Nov 13, 2015 9:19:34 PM
    * @param shipmentRecord     出货信息
    * @param pageNo             页码
    * @param pageSize           每页显示数
    * @return   成功返回码为0，msg为查询结果数据
     */
    public BaseDto serchShipmentRecord(ShipmentRecordDto shipmentRecord, int pageNo, int pageSize) {
        Page<ShipmentRecordDto> page = new Page<ShipmentRecordDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", shipmentRecord.getStoreId());
        params.put("goodsName", shipmentRecord.getGoodsName());
        page.setParams(params);
        List<ShipmentRecordDto> records = shipmentRecordMapper.selectByPage(page);
        page.setResults(records);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 商品品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:25:22
    * @param storeId 门店ID
    * @return 返回页面
     */
    public ModelAndView viewBrand(Integer storeId) {
        Page<GoodsBrandDto> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(5);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<GoodsBrandDto> goodsBrands = goodsBrandMapper.selectByPage(page);
        page.setResults(goodsBrands);
        ModelAndView view = new ModelAndView(View.GoodsInfo.BRAND);
        view.addObject("page", page);
        return view;
    }

    /**
     * 保存商品品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:36:13
    * @param goodsBrand 实体
    * @return 返回新增实体
     */
    public BaseDto saveBrand(GoodsBrand goodsBrand) {
        int ok = goodsBrandMapper.insertSelective(goodsBrand);
        Page<GoodsBrandDto> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(5);
        Map<String, Object> params = new HashMap<>();
        params.put("brandId", goodsBrand.getBrandId());
        page.setParams(params);
        List<GoodsBrandDto> goodsBrands = goodsBrandMapper.selectByPage(page);
        page.setResults(goodsBrands);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 分页查询品牌
    * @author 高国藩
    * @date 2015年11月14日 下午12:15:28
    * @param goodsBrandDto      实体内容
    * @param pageNo             页数
    * @param pageSize           每页大小
    * @return                   结果数据
     */
    public BaseDto serchBrand(GoodsBrandDto goodsBrandDto, int pageNo,
            int pageSize) {
        Page<GoodsBrandDto> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", goodsBrandDto.getStoreId());
        params.put("brandName", goodsBrandDto.getBrandName());
        page.setParams(params);
        List<GoodsBrandDto> goodsBrands = goodsBrandMapper.selectByPage(page);
        page.setResults(goodsBrands);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
}
