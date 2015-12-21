package com.zefun.test;

import java.math.BigDecimal;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zefun.web.controller.ComboInfoController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.ComboInfo;

/**
 * 套餐信息测试
* @author 洪秋霞
* @date 2015年8月11日 下午2:38:37
 */
public class ComboInfoTest extends BaseTest {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(ComboInfoTest.class);
    
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();

    /**
     * 套餐Controller
     */
    @Autowired private ComboInfoController comboInfoController;

    /**
     * 保存
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:14
     */
    @Test
    public void saveComboInfo() {
        ComboInfo comboInfo = new ComboInfo();
        comboInfo.setStoreId(1);
        comboInfo.setComboName("套餐名称test");
        comboInfo.setComboDesc("套餐desc");
        comboInfo.setProjectAmount(new BigDecimal(12));
        request.addParameter("projectIds", new String[] { "1", "2" });
        request.addParameter("projectNames", new String[] { "t1", "t2" });
        request.addParameter("projectCounts", new String[] { "2", "3" });
        BaseDto dto = comboInfoController.saveComboInfo(request, response, comboInfo);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:39
     */
    @Test
    public void queryComboInfoById() {
        BaseDto dto = comboInfoController.queryComboInfoById(request, response, 1);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 编辑
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:43
     */
    @Test
    public void editComboInfo() {
        ComboInfo comboInfo = new ComboInfo();
        comboInfo.setComboId(1);
        comboInfo.setStoreId(1);
        comboInfo.setComboName("套餐名称test");
        comboInfo.setComboDesc("套餐desc");
        comboInfo.setProjectAmount(new BigDecimal(12));
        request.addParameter("projectIds", new String[] { "1", "2" });
        request.addParameter("projectNames", new String[] { "t1", "t2" });
        request.addParameter("projectCounts", new String[] { "2", "3" });
//        BaseDto dto = comboInfoController.editComboInfo(request, response, comboInfo);
//        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:49
     */
    @Test
    public void deleteComboInfo() {
        BaseDto dto = comboInfoController.deleteComboInfo(1);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

}
