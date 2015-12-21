package com.zefun.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ObjectiveRuleDto;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.ObjectiveRuleMapper;

/**
 * 关于目标考核
* @author chendb
* @date 2015年9月2日 下午2:39:14
 */
@Service
public class ObjectiveruleService {
    /**关于目标考核*/
    @Autowired
    private ObjectiveRuleMapper objectiveRuleMapper;
    /**
     * 查询某个店铺的职位信息
     * 默认返回该门店最前面10条数据
    * @author 陈端斌
    * @date 2015年9月2日 下午1:59:44
    * @param params 参数
    * @return ModelAndView
     */
    public ModelAndView queryObjectiverule(Map<String, Object> params){
        Page<ObjectiveRuleDto> page=selectPageForInfo(params, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView("employee/objectiverule/objectiverule");
        mav.addObject("page", page);
        return mav;
    }
    
    /**
     * 翻页功能
    * @author 陈端斌
    * @date 2015年8月17日 上午10:55:59
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @return BaseDto
     */
    public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize){
        Page<ObjectiveRuleDto> page = selectPageForInfo(params, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月17日 上午11:20:47
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @return Page<EmployeeDto>
     */
    private Page<ObjectiveRuleDto> selectPageForInfo(Map<String, Object> params, int pageNo, int pageSize){
        Page<ObjectiveRuleDto> page = new Page<ObjectiveRuleDto>();
        page.setParams(params);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<ObjectiveRuleDto> list = objectiveRuleMapper.getObjectiverule(page);
        page.setResults(list);
        return page;
    }
    /**
     * 新增
    * @author chendb
    * @date 2015年9月6日 上午11:42:03
    * @param map 参数
    * @return int
     */
    public int addObjectiverule(Map<String, Object> map){
        //判断之前是不是有数据了  根据项目+区间类别唯一
        /*int count=objectiveRuleMapper.isSoleInfo(map);
        if (count>0){
           //说明该目标考核项已经有相关指标要求 
            return 1;
        }*/
        objectiveRuleMapper.insert(map);
        return 0;
    }
    /**
     * 根据标识获取考核项目详情
    * @author chendb
    * @date 2015年9月6日 下午2:42:34
    * @param ruleId 标识
    * @return ObjectiveRuleDto
     */
    public ObjectiveRuleDto getruledetail(int ruleId){
       
        ObjectiveRuleDto info=objectiveRuleMapper.getruledetail(ruleId);
        return info;
    }
    /**
     * 修改
    * @author chendb
    * @date 2015年9月6日 下午3:48:43
    * @param map 参数
    * @return int
     */
    public int updateObjectiverule(Map<String, Object> map){
        //判断之前是不是有数据了  根据项目+区间类别唯一
        /*int count=objectiveRuleMapper.isSoleInfo(map);
        if (count>0){
           //说明该目标考核项已经有相关指标要求 
            return 1;
        }*/
        objectiveRuleMapper.updateByPrimaryKeySelective(map);
        return 0;
    }
    /**
     * 删除
    * @author chendb
    * @date 2015年9月6日 下午4:10:28
    * @param ruleId 标识
    * @return int
     */
    public int deleteObjectiverule(Integer ruleId){
        
        objectiveRuleMapper.deleteByPrimaryKey(ruleId);
        return 0;
    }
}
