package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.common.consts.App;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.GoodsPurchaseRecordDto;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.GoodsPurchaseRecord;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.GoodsPurchaseRecordMapper;

/**
 * 进货记录
* @author 洪秋霞
* @date 2015年9月6日 下午4:27:08
 */
@Service
public class GoodsPurchaseRecordService {

    /** 进货记录 */
    @Autowired private GoodsPurchaseRecordMapper goodsPurchaseRecordMapper;
    
    /** 商品信息 */
    @Autowired private GoodsInfoMapper goodsInfoMapper;

    /**
     * 分页查询进货记录 change
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:30:33
    * @param goodsPurchaseRecordDto 进货记录
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return BaseDto
     */
    public BaseDto listAction(GoodsPurchaseRecordDto goodsPurchaseRecordDto, int pageNo, int pageSize) {
        Page<GoodsPurchaseRecordDto> page = queryGoodsPurchaseRecordDtoPage(goodsPurchaseRecordDto, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 分页查询进货记录
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:21:16
    * @param goodsPurchaseRecordDto 进货记录
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return Page<SupplierInfo>
     */
    public Page<GoodsPurchaseRecordDto> queryGoodsPurchaseRecordDtoPage(GoodsPurchaseRecordDto goodsPurchaseRecordDto, int pageNo, int pageSize) {
        Page<GoodsPurchaseRecordDto> page = new Page<GoodsPurchaseRecordDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("goodsPurchaseRecordDto", goodsPurchaseRecordDto);
        page.setParams(params);
        List<GoodsPurchaseRecordDto> goodsPurchaseRecordDtoList = goodsPurchaseRecordMapper.selectByPropertyPage(page);
        page.setResults(goodsPurchaseRecordDtoList);
        return page;
    }

    /**
     * 根据门店id查询进货记录
    * @author 洪秋霞
    * @date 2015年9月6日 下午4:31:03
    * @param storeId 门店id
    * @return List<GoodsPurchaseRecordDto>
     */
    public List<GoodsPurchaseRecordDto> queryGoodsPurchaseRecordDtoByStoreId(Integer storeId) {
        return goodsPurchaseRecordMapper.selectByStoreId(storeId);
    }

    /**
     * 保存进货记录
    * @author 洪秋霞
    * @date 2015年9月7日 上午11:10:35
    * @param goodsPurchaseRecord 进货记录
    * @return int
     */
    public int savePurchaseRecords(GoodsPurchaseRecord goodsPurchaseRecord){
        //修改商品库存数量
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsPurchaseRecord.getGoodsId());
        Integer goodsStock = goodsInfo.getGoodsStock();
        Integer purchaseCount = goodsPurchaseRecord.getPurchaseCount();
        
        goodsInfo.setGoodsStock(goodsStock + purchaseCount);
        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
        
        return goodsPurchaseRecordMapper.insertSelective(goodsPurchaseRecord);
    }
    
    
}
