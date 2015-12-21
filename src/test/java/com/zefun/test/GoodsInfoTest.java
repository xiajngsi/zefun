package com.zefun.test;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zefun.web.controller.GoodsInfoController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.GoodsBrand;
import com.zefun.web.entity.GoodsInfo;

/**
 * 商品信息测试
* @author 洪秋霞
* @date 2015年8月11日 下午2:42:22
 */
public class GoodsInfoTest extends BaseTest {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(GoodsInfoTest.class);

    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();

    /**
     * 商品信息Controller
     */
    @Autowired private GoodsInfoController goodsInfoController;

    /**
     * 保存品牌
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:43:00
     */
    @Test
    public void saveGoodsBrand() {
        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setStoreId(1);
        goodsBrand.setBrandName("品牌名称test");
        BaseDto dto = goodsInfoController.saveGoodsBrand(request, response, goodsBrand);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 保存商品
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:43:08
     */
    @Test
    public void saveGoodsInfo() {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setStoreId(1);
        goodsInfo.setCategoryId(1);
        goodsInfo.setGoodsName("商品名称test");
        goodsInfo.setGoodsPrice(new BigDecimal(11));
        goodsInfo.setCostPrice(new BigDecimal(12));
        goodsInfo.setGoodsDesc("dsf");
        goodsInfo.setGoodsStock(11);
        goodsInfo.setWarnStock(2);
        goodsInfo.setCommissionType(1);
        goodsInfo.setCommissionAmount(23);

        String[] levelId = new String[] { "1", "2" };
        String[] discountAmount = new String[] { "5.2", "3.5" };
        BaseDto dto = goodsInfoController.saveGoodsInfo(request, response, goodsInfo, levelId, discountAmount, discountAmount, discountAmount);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:43:21
     */
    @Test
    public void queryGoodsInfoById() {
        BaseDto dto = goodsInfoController.queryGoodsInfoById(request, response, 1);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 编辑
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:43:25
     */
    @Test
    public void editGoodsInfo() {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsId(1);
        goodsInfo.setStoreId(1);
        goodsInfo.setCategoryId(1);
        goodsInfo.setGoodsName("商品名称test");
        goodsInfo.setGoodsPrice(new BigDecimal(11));
        goodsInfo.setCostPrice(new BigDecimal(12));
        goodsInfo.setGoodsDesc("dsf");
        goodsInfo.setGoodsStock(11);
        goodsInfo.setWarnStock(2);
        goodsInfo.setCommissionType(1);
        goodsInfo.setCommissionAmount(23);

        request.addParameter("levelId", new String[] { "1", "2" });
        request.addParameter("discountAmount", new String[] { "2.3", "5.4" });

//        BaseDto dto = goodsInfoController.editGoodsInfo(request, response, goodsInfo);
//        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:43:32
     */
    @Test
    public void deleteGoodsInfo() {
        /*BaseDto dto = goodsInfoController.deleteGoodsInfo(request, response, 1);*/
        /*logger.info("result : " + JSONObject.fromObject(dto).toString());*/
    }

}
