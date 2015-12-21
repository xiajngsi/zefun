package com.zefun.web.mapper;

import com.zefun.web.entity.MemberPresentRecord;

/**
 * 会员赠送记录操作类
* @author 张进军
* @date Dec 10, 2015 7:56:43 PM
 */
public interface MemberPresentRecordMapper {
    
    /**
     * 新增赠送记录
    * @author 张进军
    * @date Dec 10, 2015 7:56:51 PM
    * @param record 赠送信息
    * @return   0:失败，1:成功
     */
    int insert(MemberPresentRecord record);
}