package com.zefun.test;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.controller.PositioninfoController;
import com.zefun.web.dto.BaseDto;

/**
 * 岗位信息测试类
* @author chendb
* @date 2015年8月11日 下午5:44:15
 */
public class PositionInfoTest {
    /**
     * 岗位
     */
    @Autowired
    private PositioninfoController positioninfoController;
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(GoodsInfoTest.class);
    
    /**
     * 新增岗位
    * @author chendb
    * @date 2015年8月11日 上午11:30:49
     */
   /* @Test
    public void add(){
        Integer positionCode=2; 
        String positionName="gangwei";
        BaseDto dto =positioninfoController.addPositioninfo(null, null, positionCode, positionName, positionCode);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }*/
    
    /**
     *修改岗位
    * @author chendb
    * @date 2015年8月11日 上午11:30:49
     */
   /* @Test
    public void update(){
        Integer positionId=1;
        Integer positionCode=2; 
        String positionName="gangwei1";
        BaseDto dto =positioninfoController.updatePositioninfo(null, null, positionCode, positionName, positionId, positionCode);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }*/
    
    /**
     *删除岗位
    * @author chendb
    * @date 2015年8月11日 上午11:30:49
     */
   /* @Test
    public void delete(){
        Integer positionId=1;
        BaseDto dto =positioninfoController.deleteposition(null, null, positionId);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }*/
    
}
