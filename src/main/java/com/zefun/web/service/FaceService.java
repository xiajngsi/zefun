package com.zefun.web.service;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.common.exception.ServiceException;
import com.zefun.common.utils.FaceUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.FaceInfo;
import com.zefun.web.entity.FacesetStore;
import com.zefun.web.mapper.FaceInfoMapper;
import com.zefun.web.mapper.FaceSearchRecordMapper;
import com.zefun.web.mapper.FacesetStoreMapper;

/**
 * 人脸识别
 * @author 张进军
 * @date Jun 30, 2015 2:50:01 PM 
 */
@Service
public class FaceService {
    /**
     * 
     */
    @Autowired private FaceInfoMapper faceInfoMapper;

    /**
     * 
     */
    @Autowired private FacesetStoreMapper facesetStoreMapper;

    /**
     * 
     */
    @Autowired private FaceSearchRecordMapper faceSearchRecordMapper;

    /** 每个用户在faceset中的最大数量值 */
    private final int userFaseInSetCount = 10;
    /** 每个faceset存储的face数量值 */
    private final int faseSetCount = 10000;

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午3:13:05
    * @param storeId 门店id
    * @param userId 用户id
    * @param faceId id
    * @return BaseDto
    * @throws ServiceException 异常
     */
    public BaseDto addFace(Integer storeId, Integer userId, String faceId)
            throws ServiceException {
        // 当前用户face数量在faceset中达到上限
        if (isMaxForUser(userId)) {
            return new BaseDto(-1, "个人人脸数量已上限");
        }

        // 如果查询不到店铺对应的faceset
        String setId = facesetStoreMapper.selectSetIdByStoreId(storeId);
        if (StringUtils.isBlank(setId)) {
            return new BaseDto(-1, "当前店铺未获得脸部识别功能");
        }

        // 当前faceset下face数量已达到上限，创建新的faceset
        if (isMaxForSet(setId)) {
            JSONObject json = FaceUtil.createFaceset(faceId);
            setId = json.getString("faceset_id");
            FacesetStore faceset = new FacesetStore();
            faceset.setSetId(setId);
            faceset.setStoreId(storeId);
            facesetStoreMapper.insert(faceset);
        }

        // 将face添加到faceset
        FaceUtil.addFaceByFacesetId(setId, faceId);

        // 保存数据
        FaceInfo faceInfo = new FaceInfo();
        faceInfo.setFaceId(faceId);
        faceInfo.setSetId(setId);
        faceInfo.setUserId(userId);
        faceInfoMapper.insert(faceInfo);

        // 刷新faceset，应换成异步方式
        FaceUtil.trainFacesetById(setId);

        return new BaseDto(0, "ok");
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午3:13:26
    * @param storeId 门店id
    * @param faceId id
    * @return BaseDto
    * @throws ServiceException 异常
     */
    public BaseDto search(Integer storeId, String faceId) throws ServiceException {
        String facesetId = facesetStoreMapper.selectSetIdByStoreId(storeId);
        JSONObject json = FaceUtil.searchByFacesetId(faceId, facesetId, false);
        JSONObject matchObj = json.getJSONArray("candidate").getJSONObject(0);
        String matchFaceId = matchObj.getString("face_id");
        FaceInfo faceInfo = faceInfoMapper.selectByPrimaryKey(matchFaceId);
        matchObj.put("userId", faceInfo.getUserId());
        return new BaseDto(0, matchObj);
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午3:13:42
    * @param userId 用户id
    * @return boolean
     */
    public boolean isMaxForUser(Integer userId) {
        int count = faceInfoMapper.selectCountByUserId(userId);
        if (count < userFaseInSetCount) {
            return false;
        }
        return true;
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午3:14:09
    * @param setId id
    * @return boolean
     */
    public boolean isMaxForSet(String setId) {
        int count = faceInfoMapper.selectCountBySetId(setId);
        if (count < faseSetCount) {
            return false;
        }
        return true;
    }
}
