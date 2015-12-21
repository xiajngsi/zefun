package com.zefun.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.controller.EmployeelevelController;
import com.zefun.web.dto.BaseDto;

/**
 * 职位信息
* @author chendb
* @date 2015年8月11日 下午5:14:09
 */
public class EmployeeLevelTest {

    /**
     * 员工职位Controller
     */
    @Autowired
    private EmployeelevelController employeelevelController;
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(GoodsInfoTest.class);
    
    /**
     * 新增职位
    * @author chendb
    * @date 2015年8月11日 上午11:30:49
     */
  //  @Test
  /*  public void add(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("positionId", 1);
        map.put("levelName", 1);
        map.put("assignType", 1);
        map.put("assignCommission", 11.11);
        map.put("nonAssignType", 1);
        map.put("nonAssignCommission", 1);
        map.put("nonCustomercost", 12312);
        map.put("customercost", 3123);
        map.put("performancecount", 12);
        map.put("isUpgrade", 1);
        
        JSONObject jsonObj = JSONObject.fromObject(map);
        String b=jsonObj.toString();
       
        BaseDto dto = employeelevelController.addEmployeelevel(null, null, b);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }*/
    
    /**
     * 修改职位
    * @author chendb
    * @date 2015年8月11日 上午11:30:49
     */
   /* @Test
    public void update(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("levelId", 1);
        map.put("positionId", 1);
        map.put("levelName", 1);
        map.put("assignType", 1);
        map.put("assignCommission", 11.11);
        map.put("nonAssignType", 1);
        map.put("nonAssignCommission", 1);
        map.put("nonCustomercost", 12312);
        map.put("customercost", 3123);
        map.put("performancecount", 12);
        map.put("isUpgrade", 1);
        
        JSONObject jsonObj = JSONObject.fromObject(map);
        String b=jsonObj.toString();
       
        BaseDto dto = employeelevelController.addEmployeelevel(null, null, b);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }*/
    
    
    /**
     * 删除职位
    * @author chendb
    * @date 2015年8月11日 上午11:30:49
     */
   /* @Test
    public void delete(){
        Integer levelId=1;
        BaseDto dto = employeelevelController.deleteEmployeelevel(null, null, levelId);
        logger.info("list result : " + JSONObject.fromObject(dto).toString());
    }*/

}
