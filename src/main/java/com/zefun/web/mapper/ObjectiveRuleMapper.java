package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.ObjectiveRuleDto;
import com.zefun.web.entity.Page;
/**
 * 关于目标考核
* @author chendb
* @date 2015年9月2日 下午3:27:29
 */
public interface ObjectiveRuleMapper {
    /**
     * 删除
    * @author chendb
    * @date 2015年9月2日 下午3:26:44
    * @param ruleId 参数
    * @return int
     */
    int deleteByPrimaryKey(Integer ruleId);
    /**
     * 新增
    * @author chendb
    * @date 2015年9月2日 下午3:28:14
    * @param map 参数
    * @return insert
     */
    int insert(Map<String, Object> map);
    /**
     * 修改
    * @author chendb
    * @date 2015年9月2日 下午3:29:25
    * @param map 参数
    * @return int
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);
    /**
     * 获取列表信息
    * @author chendb
    * @date 2015年9月2日 下午5:37:08
    * @param page 参数
    * @return List<ObjectiveRuleDto>
     */
    List<ObjectiveRuleDto>getObjectiverule(Page<ObjectiveRuleDto> page);
    /**
     * 判断是否唯一   考核目标+区间类型
    * @author chendb
    * @date 2015年9月6日 下午2:04:48
    * @param map 参数
    * @return int
     */
    int isSoleInfo(Map<String, Object> map);
    
    /**
     * 获取考核信息
    * @author chendb
    * @date 2015年9月6日 下午2:40:41
    * @param ruleId 考核标识
    * @return ObjectiveRuleDto
     */
    ObjectiveRuleDto getruledetail(Integer ruleId);

}