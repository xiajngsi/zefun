package com.zefun.test;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zefun.web.controller.SupplierInfoController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.SupplierInfo;

/**
 * 供应商信息测试
* @author 洪秋霞
* @date 2015年8月12日 下午3:57:03
 */
public class SupplierInfoTest extends BaseTest {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(SupplierInfoTest.class);
    
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();
    
    /**
     * 供应商信息
     */
    @Autowired private SupplierInfoController supplierInfoController;
    
    /**
     * 列表分页
    * @author 洪秋霞
    * @date 2015年8月12日 下午4:16:01
     */
    @Test
    public void listAction(){
        BaseDto dto = supplierInfoController.listAction(request, response, 1, 10);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }
    
    /**
     * 保存
    * @author 洪秋霞
    * @date 2015年8月12日 下午5:20:31
     */
    @Test
    public void saveSupplierInfo(){
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setSupplierName("友谊");
        supplierInfo.setSupplyBrand("班尼路");
        supplierInfo.setSupplyCategory("服装");
        supplierInfo.setLinkName("李老板");
        supplierInfo.setLinkPhone("13586543954");
        BaseDto dto = supplierInfoController.saveSupplierInfo(request, response, supplierInfo);
        logger.info("saveSupplierInfo result : " + JSONObject.fromObject(dto).toString());
    }
    
    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月12日 下午5:23:02
     */
    @Test
    public void querySupplierInfoById(){
        BaseDto dto = supplierInfoController.querySupplierInfoById(request, response, 1);
        logger.info("querySupplierInfoById result : " + JSONObject.fromObject(dto).toString());
    }
    
    /**
     * 修改
    * @author 洪秋霞
    * @date 2015年8月12日 下午5:23:48
     */
    @Test
    public void editSupplierInfo(){
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setSupplierId(1);
        supplierInfo.setSupplierName("友谊");
        supplierInfo.setSupplyBrand("班尼路");
        supplierInfo.setSupplyCategory("服装");
        supplierInfo.setLinkName("李老板");
        supplierInfo.setLinkPhone("13586543954");
        BaseDto dto = supplierInfoController.saveSupplierInfo(request, response, supplierInfo);
        logger.info("editSupplierInfo result : " + JSONObject.fromObject(dto).toString());
    }
    
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月12日 下午5:24:28
     */
    @Test
    public void deleteSupplierInfo(){
        BaseDto dto = supplierInfoController.deleteSupplierInfo(request, response, 1);
        logger.info("deleteSupplierInfo result : " + JSONObject.fromObject(dto).toString());
    }
}
