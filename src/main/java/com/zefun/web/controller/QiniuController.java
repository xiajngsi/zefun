package com.zefun.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.QiniuService;

/**
 * 七牛云存储相关api
 * @author 张进军
 * @date Jul 10, 2015 8:44:50 PM 
 */
@Controller
public class QiniuController {
    /** 七牛云存储api操作服务类 */
    @Autowired
    private QiniuService qiniuService;

    
    /**
     * 获取七牛上传默认策略的token
    * @author 张进军
    * @date Nov 23, 2015 11:50:32 AM
    * @return   上传token
     */
    @RequestMapping(value = Url.Qiniu.UPTOKEN)
    @ResponseBody
    public Map<String, String> qiniuUptoken() {
        return qiniuService.qiniuUptoken();
    }

    
    /**
     * 抓取网络资源上传到七牛
    * @author 张进军
    * @date Aug 22, 2015 11:36:03 AM
    * @param fromUrl       资源文件地址
    * @param key           七牛目标地址
    * @return              成功返回码0,失败返回其他错误码
     */
    @RequestMapping(value = Url.Qiniu.FETCH, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto fetch(String fromUrl, String key) {
        return qiniuService.fetch(fromUrl, key);
    }
    
    
    /**
     * 文字转语音
    * @author 张进军
    * @date Nov 23, 2015 10:59:06 AM
    * @param text   需要转换的文字
    * @param per    发音人，1:男性，0:女性
    * @return   语音地址
     */
    @RequestMapping(value = Url.Qiniu.TEXT_TO_VOICE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto textToVoice(String text, int per) {
        return qiniuService.textToVioce(text, per);
    }
    
    
    /**
     * 将base64上传至七牛图片库
    * @author 高国藩
    * @date 2015年11月9日 下午8:13:37
    * @param stringBase64 js生成的base64数据
    * @param key 自定义key
    * @return 七牛地址
     */
    @RequestMapping(value = Url.Qiniu.BASE64, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto base64(String stringBase64, String key) {
        return qiniuService.put64(stringBase64, key);
    } 
}
