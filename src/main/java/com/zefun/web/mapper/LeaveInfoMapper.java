package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.LeaveInfo;

/**
 * 请假信息
* @author chendb
* @date 2015年9月21日 下午2:03:18
 */
public interface LeaveInfoMapper {
    /**
     * 请假申请
    * @author chendb
    * @date 2015年9月21日 下午2:03:53
    * @param record 参数
    * @return int
     */
    int insert(LeaveInfo record);
    /**
     * 审批请假
    * @author chendb
    * @date 2015年9月21日 下午2:06:28
    * @param record 参数
    * @return int
     */
    int updateApproval(LeaveInfo record);
    /**
     * 查询请假记录
    * @author chendb
    * @date 2015年9月21日 下午5:05:02
    * @param record 参数
    * @return List<LeaveInfo>
     */
    List<LeaveInfo> queryLeaveInfo(LeaveInfo record);
    /**
     * 根据人员和签到时间判断是否在请假的日期中
    * @author chendb
    * @date 2015年9月23日 上午10:08:00
    * @param map 参数
    * @return int
     */
    int getCountLeave(Map<String, Object>map);
    /**
     * 获取请假信息
    * @author chendb
    * @date 2015年9月23日 下午2:33:25
    * @param map 参数
    * @return Map<String,Object>
     */
    Map<String, Object> getLeaveInfo(Map<String, Object>map);

}