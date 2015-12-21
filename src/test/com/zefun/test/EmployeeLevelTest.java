package com.zefun.test;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.common.utils.HttpClientUtil;
import com.zefun.web.controller.EmployeelevelController;
import com.zefun.web.dto.BaseDto;

/**
 * 职位测试
* @author chendb
* @date 2015年8月11日 下午3:01:25
 */
public class EmployeeLevelTest extends BaseTest{
    /**
     * 职位信息
     */
    @Autowired
    private EmployeelevelController employeelevelController;
    
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
    /**
     * 新增功能
    * @author chendb
    * @date 2015年8月11日 下午3:07:14
     */
    @Test
    public void add(){
        double   f   =   111231.5585; 
        Integer positionId=1; 
        String levelName="1";
        String assignType="11";
        Integer assignCommission=1; 
        String nonAssignType="1";
        Integer nonAssignCommission=1;
        BigDecimal nonCustomercost=new BigDecimal(f);
        BigDecimal customercost=new BigDecimal(f);
        BigDecimal performancecount=new BigDecimal(f);
        Integer isUpgrade=1;
        BaseDto dto =employeelevelController.addEmployeelevel(positionId, levelName, assignType, assignCommission, nonAssignType, 
                nonAssignCommission, nonCustomercost, customercost, performancecount, isUpgrade);
        logger.info(dto);
    }
    /**
     * 修改功能
    * @author chendb
    * @date 2015年8月11日 下午3:07:14
     */
    @Test
    public void update(){
        double   f   = 31.5585; 
        Integer positionId=1; 
        String levelName="1";
        String assignType="11";
        Integer assignCommission=1; 
        String nonAssignType="1";
        Integer nonAssignCommission=1;
        BigDecimal nonCustomercost=new BigDecimal(f);
        BigDecimal customercost=new BigDecimal(f);
        BigDecimal performancecount=new BigDecimal(f);
        Integer isUpgrade=1;
        Integer levelId=1;
        BaseDto dto =employeelevelController.updateEmployeelevel(positionId, levelName, assignType, assignCommission, nonAssignType,
                nonAssignCommission, nonCustomercost, customercost, performancecount, levelId, isUpgrade);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

}
