package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberErrorZy;
import com.zefun.web.entity.Page;

/**
 * 
* @author 高国藩
* @date 2015年12月4日 下午3:04:18
 */
public interface MemberErrorZyMapper {
    
    /**
     * ɾ��
    * @author �߹���
    * @date 2015��12��4�� ����2:57:37
    * @param id  ����
    * @return    ����
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * ɾ��
    * @author �߹���
    * @date 2015��12��4�� ����2:57:37
    * @param record  ʵ��
    * @return    ����
     */
    int insert(MemberErrorZy record);
    /**
     * ɾ��
    * @author �߹���
    * @date 2015��12��4�� ����2:57:37
    * @param record  ʵ��
    * @return    ����
     */
    int insertSelective(MemberErrorZy record);
    /**
     * ��ѯ
    * @author �߹���
    * @date 2015��12��4�� ����2:57:37
    * @param id  ʵ��
    * @return    ����
     */
    MemberErrorZy selectByPrimaryKey(Integer id);
    /**
     * ��ѯ
    * @author �߹���
    * @date 2015��12��4�� ����2:57:37
    * @param record  ʵ��
    * @return    ����
     */
    int updateByPrimaryKeySelective(MemberErrorZy record);
    /**
     * ��ѯ
    * @author �߹���
    * @date 2015��12��4�� ����2:57:37
    * @param record  ʵ��
    * @return    ����
     */
    int updateByPrimaryKey(MemberErrorZy record);
    
    /**
     * 批量插入
    * @author 高国藩
    * @date 2015年12月4日 下午3:03:57
    * @param errorZies 结合
     */
    void insertList(List<MemberErrorZy> errorZies);
    
    /**
     * 分页查询左右导入会员数据-异常数据
    * @author 高国藩
    * @date 2015年12月7日 上午11:27:32
    * @param page     page
    * @return         集合
     */
    List<MemberErrorZy> selectByPage(Page<MemberErrorZy> page);
    /**
     * 获取所有
    * @author 高国藩
    * @date 2015年12月17日 下午5:15:21
    * @param storeId  门店
    * @return         集合
     */
    List<MemberErrorZy> selectAll(Integer storeId);
    
    /**
     * 门店异常会员统计
    * @author 高国藩
    * @date 2015年12月17日 下午2:18:00
    * @param storeId  门店信息
    * @return         统计信息
     */
    MemberErrorZy selectStoreMemberAmount(Integer storeId);
}