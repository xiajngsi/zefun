package com.zefun.wechat.service;


import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zefun.common.consts.Url;

/**
 * 微信处理工具service
* @author 高国藩
* @date 2015年8月11日 上午11:47:56
 */
@Service
public class WeixinConfigService {
	/** 日志 */
	private static Logger logger = Logger.getLogger(WeixinConfigService.class);
	/**微信全局返回信息*/
    private static Map<String, String> errorMessage;
	
	static {
	    logger.info("微信全局返回码说明Map生成中...");
	    errorMessage = new HashMap<>();
	    errorMessage.put("-1", "系统繁忙，此时请开发者稍候再试");
	    errorMessage.put("0", "请求成功");
	    errorMessage.put("40001", "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口");
	    errorMessage.put("40002", "不合法的凭证类型");
	    errorMessage.put("40003", "请开确认该用户是否已关注公众号");
	    errorMessage.put("40004", "不合法的媒体文件类型");
	    errorMessage.put("40005", "不合法的文件类型");
	    errorMessage.put("40006", "不合法的文件大小");
	    errorMessage.put("40007", "不合法的媒体文件id");
	    errorMessage.put("40008", "不合法的消息类型");
	    errorMessage.put("40009", "不合法的图片文件大小");
	    errorMessage.put("40010", "不合法的语音文件大小");
	    errorMessage.put("40011", "不合法的视频文件大小");
	    errorMessage.put("40012", "不合法的缩略图文件大小");
	    errorMessage.put("40013", "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写");
	    errorMessage.put("40014", "access_token 失效");
	    errorMessage.put("40015", "不合法的菜单类型");
	    errorMessage.put("40016", "不合法的按钮个数");
	    errorMessage.put("40017", "不合法的按钮个数");
	    errorMessage.put("40130", "该分组条件内不满足微信推送消息最少人员,请确保至少两人以上!");
	}
	
	
	public static Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(Map<String, String> errorMessage) {
        WeixinConfigService.errorMessage = errorMessage;
    }

    /**
	 * 获得微信accseeToken
	* @author 高国藩
	* @date 2015年8月28日 上午10:45:14
	* @param appID 微信认证
	* @param appsecret 微信认证
	* @return 返回accessToken
	 */
	public String getAccessToken(String appID, String appsecret){
	    String url = Url.Wechat.GET_ACCESSTOKEN;
        url = url.replace("APPID", appID);
        url = url.replace("APPSECRET", appsecret);
        JSONObject json = WeixinUploadService.httpRequest(url, "GET", null);
        String accessToken = json.getString("access_token");
        logger.info(accessToken);
	    return accessToken;
	}
	
}
