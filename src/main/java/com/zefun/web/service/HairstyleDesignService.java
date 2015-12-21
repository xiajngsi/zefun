package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.dto.HairstyleCategoryDto;
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.HairstyleCategory;
import com.zefun.web.entity.HairstyleDesign;
import com.zefun.web.mapper.CodeLibraryMapper;
import com.zefun.web.mapper.HairstyleCategoryMapper;
import com.zefun.web.mapper.HairstyleDesignMapper;

/**
 * 发型设置
* @author 洪秋霞
* @date 2015年9月8日 下午2:14:00
 */
@Service
public class HairstyleDesignService {

    /** 发型类别 */
    @Autowired private HairstyleCategoryMapper hairstyleCategoryMapper;
    /** 发型设置 */
    @Autowired private HairstyleDesignMapper hairstyleDesignMapper;
    /** 数据字典 */
    @Autowired private CodeLibraryMapper codeLibraryMapper;
    
    /**
     * 查找发型类别和发型列表
    * @author 洪秋霞
    * @date 2015年9月8日 下午3:15:07
    * @param storeId 门店id
    * @return List<HairstyleCategoryDto>
     */
    public List<HairstyleCategoryDto> queryHairstyleDesignCategory(Integer storeId){
        return hairstyleCategoryMapper.getHairstyleCategoryInfo(storeId);
    }
    
    /**
     * 查询发型类别列表
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:21:40
    * @param storeId 门店id
    * @return List<HairstyleCategory>
     */
    public List<HairstyleCategory> queryHairstyleCategory(Integer storeId){
        return hairstyleCategoryMapper.selectByStoreId(storeId);
    }
    
    
    /**
     * 保存发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:35:18
    * @param hairstyleCategory 发型类别
    * @return int 
     */
    public int saveHairstyleCategory(HairstyleCategory hairstyleCategory){
        return hairstyleCategoryMapper.insertSelective(hairstyleCategory);
    }
    
    
    /**
     * 修改发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:36:07
    * @param hairstyleCategory 发型类别
    * @return int
     */
    public int updateHairstyleCategory(HairstyleCategory hairstyleCategory){
        return hairstyleCategoryMapper.updateByPrimaryKeySelective(hairstyleCategory);
    }
    
    /**
     * 删除发型类别
    * @author 洪秋霞
    * @date 2015年9月8日 下午4:03:09
    * @param hairstyleCategoryId 类别id
    * @return int
     */
    public int deleteHairstyleCategory(Integer hairstyleCategoryId){
        return hairstyleCategoryMapper.deleteByPrimaryKey(hairstyleCategoryId);
    }
    
    /**
     * 查询发型列表
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:30:33
    * @param storeId 门店id
    * @return List<HairstyleDesign>
     */
    public List<HairstyleDesign> queryHairstyleDesign(Integer storeId){
        return hairstyleDesignMapper.selectByStoreId(storeId);
    }
    
    /**
     * 根据id查询发型
    * @author 洪秋霞
    * @date 2015年9月8日 下午4:21:56
    * @param hairstyleId 发型id
    * @return HairstyleDesign
     */
    public HairstyleDesign queryHairstyleDesignById(Integer hairstyleId){
        return hairstyleDesignMapper.selectByPrimaryKey(hairstyleId);
    }
    
    /**
     * 保存发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 下午2:25:04
    * @param hairstyleDesign 发型设置
    * @return int
     */
    public int saveHairstyleDesign(HairstyleDesign hairstyleDesign){
        return hairstyleDesignMapper.insertSelective(hairstyleDesign);
    }
    
    /**
     * 编辑发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 下午4:03:59
    * @param hairstyleDesign 发型设置
    * @return int
     */
    public int editHairstyleDesign(HairstyleDesign hairstyleDesign){
        return hairstyleDesignMapper.updateByPrimaryKeySelective(hairstyleDesign);
    }
    
    /**
     * 删除发型设置
    * @author 洪秋霞
    * @date 2015年9月8日 下午4:04:40
    * @param hairstyleId 发型设置id
    * @return int
     */
    public int deleteHairstyleDesign(Integer hairstyleId){
        return hairstyleDesignMapper.deleteByPrimaryKey(hairstyleId);
    }
    
    /**
     * 根据多个tyepNo查询数据
    * @author 洪秋霞
    * @date 2015年9月30日 上午10:38:18
    * @param typeNos 类型编号
    * @return List<CodeLibrary>
     */
    public List<CodeLibrary> queryByTypeNos(List<Integer> typeNos){
        return codeLibraryMapper.selectByTypeNos(typeNos);
    }
    
    /**
     * 根据tyepNo查询数据
    * @author 洪秋霞
    * @date 2015年9月30日 上午10:48:12
    * @param typeNo 类型编号
    * @return List<CodeLibrary>
     */
    public List<CodeLibrary> queryByTypeNo(Integer typeNo){
        return codeLibraryMapper.selectByTypeNo(typeNo);
    }
}
