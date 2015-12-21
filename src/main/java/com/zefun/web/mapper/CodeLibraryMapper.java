package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.CodeLibraryDto;
import com.zefun.web.entity.CodeLibrary;

/**
 * 数据字典Mapper
* @author 王大爷
* @date 2015年8月11日 下午2:28:34
 */
public interface CodeLibraryMapper {
    
    /**
     * 删除数据字典
    * @author 王大爷
    * @date 2015年8月11日 上午10:43:48
    * @param key 数据字典
    * @return 是否成功
     */
    int deleteByPrimaryKey(CodeLibrary key);

    /**
     * 插入数据字典
    * @author 王大爷
    * @date 2015年8月11日 上午10:43:04
    * @param record 数据字典
    * @return 是否成功
     */
    int insert(CodeLibrary record);

    /**
     * 插入存在属性数据字典
    * @author 王大爷
    * @date 2015年8月11日 上午10:42:25
    * @param record 数据字典
    * @return 是否成功
     */
    int insertSelective(CodeLibrary record);

    /**
     * 通过数据字典no查询数据字典
    * @author 王大爷
    * @date 2015年8月11日 上午10:41:42
    * @param key 数据字典
    * @return 轮牌信息
     */
    CodeLibrary selectByPrimaryKey(CodeLibrary key);

    /**
     * 修改已存在的数据字典
    * @author 王大爷
    * @date 2015年8月11日 上午10:40:56
    * @param record 数据字典
    * @return 是否成功
     */
    int updateByPrimaryKeySelective(CodeLibrary record);

    /**
     * 整体修改数据字典
    * @author 王大爷
    * @date 2015年8月11日 上午10:40:16
    * @param record 数据字典
    * @return 是否成功
     */
    int updateByPrimaryKey(CodeLibrary record);
    
    /**
     * 通过类型编号查询数据字典集合
    * @author 王大爷
    * @date 2015年8月11日 下午2:34:01
    * @param typeNo 类型编号
    * @return 数据字典集合
     */
    List<CodeLibrary> selectByTypeNo(Integer typeNo);
    
    /**
     * 通过父 代码编号查询数据字典集合
    * @author 王大爷
    * @date 2015年8月11日 下午2:34:54
    * @param codeLibraryDto 数据字典DTO
    * @return 数据字典集合
     */
    List<CodeLibrary> selectBySunCodeName(CodeLibraryDto codeLibraryDto);
    
    /**
     * 通过代码名称查询数据字典
    * @author 王大爷
    * @date 2015年8月11日 下午2:36:30
    * @param codeLibraryDto 数据字典DTO
    * @return 数据字典
     */
    CodeLibrary selectByCodeName(CodeLibraryDto codeLibraryDto);
    
    /**
     * 根据多个tyepNo查询数据
    * @author 洪秋霞
    * @date 2015年9月30日 上午10:36:09
    * @param typeNos 类型编号
    * @return List<CodeLibrary>
     */
    List<CodeLibrary> selectByTypeNos(List<Integer> typeNos);
    
    /**
     * 项目图库
    * @author 高国藩
    * @date 2015年11月24日 下午7:43:37
    * @return 集合
     */
    List<CodeLibraryDto> selectProjectImage();
}