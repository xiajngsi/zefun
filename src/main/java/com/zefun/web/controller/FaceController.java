package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.exception.ServiceException;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.FaceService;

/**
 * 人脸识别模块
 * @author 张进军
 * @date Jun 30, 2015 4:42:19 PM 
 */
@Controller
public class FaceController {
    /**
     * 
     */
    @Autowired private FaceService faceService;

    /**
     * 为用户添加人脸
    * @author 张进军
    * @date Jul 2, 2015 2:58:04 PM
    * @param faceId id
    * @param request request
    * @return BaseDto
     */
    @RequestMapping(value = Url.Face.ACTION_ADD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addFace(String faceId, HttpServletRequest request) {
        Integer storeId = 1;
        Integer userId = 1001;
        try {
            return faceService.addFace(storeId, userId, faceId);
        }
        catch (ServiceException e) {
            return new BaseDto(e.getCode(), e.getMsg());
        }
    }

    /**
     * 根据人脸搜索到相应的用户
    * @author 张进军
    * @date Jul 2, 2015 2:58:17 PM
    * @param faceId id
    * @param request request
    * @return BaseDto
     */
    @RequestMapping(value = Url.Face.ACTION_SEARCH, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto search(String faceId, HttpServletRequest request) {
        Integer storeId = 1;
        try {
            return faceService.search(storeId, faceId);
        }
        catch (ServiceException e) {
            return new BaseDto(e.getCode(), e.getMsg());
        }
    }

    /**
     * 显示添加人脸的页面
    * @author 张进军
    * @date Jul 2, 2015 2:58:54 PM
    * @return String
     */
    @RequestMapping(value = Url.Face.VIEW_ADD, method = RequestMethod.GET)
    public String addFaceView() {
        return View.Face.ADD;
    }

    /**
     * 显示搜索人脸的页面
    * @author 张进军
    * @date Jul 2, 2015 2:59:05 PM
    * @return String
     */
    @RequestMapping(value = Url.Face.VIEW_SEARCH, method = RequestMethod.GET)
    public String searchFaceView() {
        return View.Face.SEARCH;
    }
}
