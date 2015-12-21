package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.PictureLibrary;

/**
 * 图片库
* @author 高国藩
* @date 2015年9月2日 上午10:51:31
 */
public interface PictureLibraryMapper {
    /**
     * 删除-自动生成
    * @author 高国藩
    * @date 2015年9月2日 上午10:52:13
    * @param pictureId id
    * @return 影响行数
     */
    int deleteByPrimaryKey(Integer pictureId);

    /**
     * 删除-自动生成
    * @author 高国藩
    * @date 2015年9月2日 上午10:52:13
    * @param record id
    * @return 影响行数
     */
    int insert(PictureLibrary record);

    /**
     * 删除-自动生成
    * @author 高国藩
    * @date 2015年9月2日 上午10:52:13
    * @param record id
    * @return 影响行数
     */
    int insertSelective(PictureLibrary record);

    /**
     * 删除-自动生成
    * @author 高国藩
    * @date 2015年9月2日 上午10:52:13
    * @param pictureId id
    * @return 影响行数
     */
    PictureLibrary selectByPrimaryKey(Integer pictureId);

    /**
     * 删除-自动生成
    * @author 高国藩
    * @date 2015年9月2日 上午10:52:13
    * @param record record
    * @return 影响行数
     */
    int updateByPrimaryKeySelective(PictureLibrary record);

    /**
     * 删除-自动生成
    * @author 高国藩
    * @date 2015年9月2日 上午10:52:13
    * @param record record
    * @return 影响行数
     */
    int updateByPrimaryKey(PictureLibrary record);

    /**
     * 展示图片信息-自定义方法
    * @author 高国藩
    * @date 2015年9月2日 上午11:25:47
    * @param storeId 门店
    * @return 图片集合
     */
    List<PictureLibrary> selectPicturesByStoreId(int storeId);
}