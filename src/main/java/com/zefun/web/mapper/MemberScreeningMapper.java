package com.zefun.web.mapper;

import java.util.List;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.MemberScreening;

/**
 * 会员筛选器
* @author 高国藩
* @date 2015年9月8日 下午7:56:18
 */
public interface MemberScreeningMapper {
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月8日 下午7:56:32
    * @param screeningId 主键
    * @return 删除
     */
    int deleteByPrimaryKey(Integer screeningId);

    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月8日 下午7:57:09
    * @param record s
    * @return s
     */
    int insert(MemberScreening record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月8日 下午7:57:09
    * @param record s
    * @return s
     */
    int insertSelective(MemberScreening record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月8日 下午7:57:09
    * @param screeningId s
    * @return s
     */
    MemberScreening selectByPrimaryKey(Integer screeningId);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月8日 下午7:57:09
    * @param record s
    * @return s
     */
    int updateByPrimaryKeySelective(MemberScreening record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年9月8日 下午7:57:09
    * @param record s
    * @return s
     */
    int updateByPrimaryKey(MemberScreening record);

    /**
     * 查询筛选器根据门店id
    * @author 高国藩
    * @date 2015年9月8日 下午8:36:57
    * @param storeId 门店信息
    * @return 集合
     */
    List<MemberScreening> selectByStoreId(Integer storeId);

    /**
     * 发送人员的本月微信次数减去1
    * @author 高国藩
    * @date 2015年10月7日 下午3:04:35
    * @param ls 主键集合
     */
    void updateWechatCountByMemberId(List<Integer> ls);

    /**
     * 将本月次数为0的用户去除
    * @author 高国藩
    * @date 2015年10月7日 下午3:55:35
    * @param ls 满足分组条件的人员id集合
    * @return 去除0次以后的人员id集合
     */
    List<Integer> selectSendItemsWechatCountNotZero(List<Integer> ls);

    /**
     * 查询一个条件Dto出来
    * @author 高国藩
    * @date 2015年12月10日 下午2:13:06
    * @param groupId     主键
    * @return ScreeningDto
     */
    ScreeningDto selectByDto(Integer groupId);
    
    /**
     * 查询给定id的分组
    * @author 高国藩
    * @date 2015年12月10日 下午8:40:23
    * @param asList  分组id的集合
    * @return        返回分组信息的结合
     */
    List<ScreeningDto> selectByDtos(List<String> asList);

    /**
     * 通过门店ID查询出所有的分组dtos
    * @author 高国藩
    * @date 2015年12月10日 下午3:09:05
    * @param storeId    门店
    * @return           分组
     */
    List<ScreeningDto> selectDtosByStoreId(Integer storeId);

}