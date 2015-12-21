package com.zefun.wechat.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.common.utils.SignUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.QiniuService;
import com.zefun.web.service.RedisService;


/**
 * 微信相关api操作业务逻辑类
* @author 张进军
* @date Aug 13, 2015 2:30:27 PM 
*/
@Service
public class WechatCallService {
    /** 日志记录对象 */
    private final Logger logger = Logger.getLogger(WechatCallService.class);
    
    /** redis操作对象 */
    @Autowired
    private RedisService redisService;
    
    /** 会员信息业务逻辑处理对象 */
    @Autowired
    private MemberInfoService memberInfoService;
    
    /** 七牛api服务类 */
    @Autowired
    private QiniuService qiniuService;
    
    /** 用户账户操作对象 */
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    /**
     *  微信授权回调处理
    * @author 张进军
    * @date Aug 17, 2015 3:54:23 PM
    * @param redirect       重定向地址
    * @param code           微信返回，用于获取授权的access token
    * @param state          随机字符，用作校验
    * @param scope          应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
    *                       snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
    *                           并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
    * @param storeId        门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param appId          微信公众号id
    * @param appSecret      微信公众号密钥
    * @param request        请求对象
    * @param response       返回对象
     * @throws IOException  重定向失败时抛出的异常 
     * @throws ServletException 
     */
    public void callback(String redirect, String code, String state, String scope, 
            int storeId, int businessType, String appId, String appSecret,
            HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        redirect = redirect.replace("__", "&");
        logger.info("weixin callback redirect -->" + redirect);
        
        if (StringUtils.isEmpty(code)) {
            response.sendRedirect(redirect);
        }
        
        String accessTokenRes = HttpClientUtil.sendGetReq(String.format(App.Wechat.AUTH_ACCESS_TOKEN_URL, 
                new Object[] { appId, appSecret, code }), "utf-8");
        JSONObject accessTokenJson = JSONObject.fromObject(accessTokenRes);
        
        //如果授权失败，跳转到重定向页面
        if (accessTokenJson.containsKey("errcode")) {
            String errcode = accessTokenJson.get("errcode").toString();
            String errmsg = accessTokenJson.get("errmsg").toString();
            logger.error("use weixin login err, code " + code + ", errcode " + errcode + ", errmsg " + errmsg);
            response.sendRedirect(redirect);
            return;
        }
        
        String openId = accessTokenJson.getString("openid");
        logger.info("wechat auth callback, openid is : " + openId);
        
        request.getSession().setAttribute(App.Session.WECHAT_OPEN_ID, openId);
        
        if (businessType == 1 || businessType == 2) {
            //根据openid查询userid，如果不存在userid，那么跳转到验证页面
            String userId = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
            
            //检查是否已注册
            if (StringUtils.isBlank(userId)) {
                //如果是操作会员相关api
                if (businessType == 1) {
                    redirect = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_STORE_LIST + "?url=" + Url.MemberCenter.VIEW_REGISTER;
                } 
                //如果是操作员工相关api
                else {
                    redirect = App.System.SERVER_BASE_URL + Url.Staff.VIEW_REGISTER;
                }
                response.sendRedirect(redirect);
                return;
            } 
            //存储员工角色信息
            else if (businessType == 2) {
                int employeeId = Integer.parseInt(userId);
                UserAccount userAccount = userAccountMapper.selectByPrimaryKey(employeeId);
                request.getSession().setAttribute(App.Session.ROLE_ID, userAccount.getRoleId());
            }
            request.getSession().setAttribute(App.Session.USER_ID, userId);
        }
        
        response.sendRedirect(redirect);
    }
    
    
    /**
     * 微信支付回调处理
    * @author 张进军
    * @date Sep 23, 2015 5:23:58 PM
    * @param data   支付结果
    * @return       业务处理成功返回SUCCESS
     */
    @RequestMapping(value = Url.Wechat.CREATE_PAY, method = RequestMethod.POST)
    @ResponseBody
    public String payCallback(String data){
        data = data.replace("<![CDATA[", "").replace("]]>", "");
        String returnCode = data.substring(data.indexOf("<return_code>") + 13, data.indexOf("</return_code>"));
        String resultCode = data.substring(data.indexOf("<result_code>") + 13, data.indexOf("</result_code>"));

        logger.info("payCallback return_code : " + returnCode + ", result_code : " + resultCode);
        String totalFee = data.substring(data.indexOf("<total_fee>") + 11, data.indexOf("</total_fee>"));
        String tradeNo = data.substring(data.indexOf("<out_trade_no>") + 14, data.indexOf("</out_trade_no>"));
        String endTime = data.substring(data.indexOf("<time_end>") + 10, data.indexOf("</time_end>"));
        String outTradeNo = data.substring(data.indexOf("<transaction_id>") + 16, data.indexOf("</transaction_id>"));

        String transactionId = tradeNo.substring(0, 11);
        String sequenceNo = tradeNo.substring(11);
        BigDecimal amount = (new BigDecimal(totalFee)).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        totalFee = amount.toString();
        
        logger.info("total_fee : " + totalFee + ", trade_no : " + tradeNo 
                + ", end_time : " + endTime + ", out_trade_no : " + outTradeNo 
                + ", transactionId : " + transactionId + ", sequenceNo : " + sequenceNo);

        return "SUCCESS";
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Sep 23, 2015 5:45:28 PM
    * @param appId          店铺对应的微信应用标识
    * @param mchId          店铺对应的微信商户标识
    * @param deviceInfo     设备标识
    * @param goodsName      商品名称
    * @param totalFee       支付金额
    * @param openId         支付用户标识
    * @param outTradeNo     支付订单号
    * @param request        请求对象
    * @param response       响应对象
    * @return               支付申请结果
     */
    public BaseDto pay(String appId, String mchId, String deviceInfo, String goodsName, String totalFee, 
            String openId, String outTradeNo, HttpServletRequest request, HttpServletResponse response){
        String spbillCreateIp = StringUtil.getIpAddr(request);
        String uuid = UUID.randomUUID().toString().replace("-", "");

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("appid", appId);
        sParaTemp.put("mch_id", mchId);
        sParaTemp.put("device_info", deviceInfo);
        sParaTemp.put("nonce_str", uuid);
        sParaTemp.put("body", goodsName);
        sParaTemp.put("out_trade_no", outTradeNo);
        sParaTemp.put("total_fee", (int)(Double.parseDouble(totalFee) * 100) + "");
        sParaTemp.put("spbill_create_ip", spbillCreateIp);
        sParaTemp.put("notify_url", App.System.SERVER_BASE_URL + Url.Wechat.CALL_BACK_PAY);
        sParaTemp.put("trade_type", "JSAPI");
        sParaTemp.put("openid", openId);
        
        String sign = SignUtil.paySign(sParaTemp, App.Wechat.MCH_PAY_KEY);
        sParaTemp.put("sign", sign);
        logger.info("sign result : " + sParaTemp);
        String preXml = "";
        try {
            preXml = wxUnifiedorderPost(sParaTemp);
        }
        catch (IOException e) {
            logger.error("wx_unifiedorder_post error : ", e);
        }
        logger.info("weixin pay request result : " + preXml);

        preXml = preXml.replace("<![CDATA[", "").replace("]]>", "");
        String returnCode = preXml.substring(preXml.indexOf("<return_code>") + 13, preXml.indexOf("</return_code>"));
        String resultCode = preXml.substring(preXml.indexOf("<result_code>") + 13, preXml.indexOf("</result_code>"));

        Map<String, String> payMap = new HashMap<String, String>();
        if (preXml.contains("<result_code>")  && returnCode.equals("SUCCESS") && resultCode.equals("SUCCESS")) {
            String prepayId = preXml.substring(preXml.indexOf("<prepay_id>") + 11, preXml.indexOf("</prepay_id>"));
            String xpackage = "prepay_id="+ prepayId;

            String ts = Long.toString(System.currentTimeMillis());
            
            Map<String, String> singMap2 = new HashMap<String, String>();
            singMap2.put("appId", appId);
            singMap2.put("timeStamp", ts);
            singMap2.put("nonceStr", uuid);
            singMap2.put("signType", App.Wechat.SIGN_TYPE);
            singMap2.put("package", xpackage);
            String paySign = SignUtil.paySign(singMap2, App.Wechat.MCH_PAY_KEY);

            payMap.put("resultCode", "0");
            payMap.put("appId", appId);
            payMap.put("timeStamp", ts);
            payMap.put("nonceStr", uuid);
            payMap.put("signType", App.Wechat.SIGN_TYPE);
            payMap.put("paySign", paySign);
            payMap.put("package", xpackage);
            payMap.put("transactionId", outTradeNo.substring(0, 11));
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, payMap);
    }
    
    /**
     *微信统一下单api，获取预付单信息
    * @author 张进军
    * @date Sep 23, 2015 7:31:07 PM
    * @param xo                         订单信息
    * @return                           预付单信息
    * @throws ClientProtocolException   客户端协议异常
    * @throws IOException               返回结果读取异常
     */
    private static String wxUnifiedorderPost(Map<String, String> xo) throws ClientProtocolException, IOException {
        StringBuffer xml = new StringBuffer();
        xml.append("<xml>")
            .append("<appid>").append(xo.get("appid")).append("</appid>")
            .append("<body><![CDATA[").append(xo.get("body")).append("]]></body>")
            .append("<device_info>").append(xo.get("device_info")).append("</device_info>")
            .append("<mch_id>").append(xo.get("mch_id")).append("</mch_id>")
            .append("<nonce_str>").append(xo.get("nonce_str")).append("</nonce_str>")
            .append("<notify_url>").append(xo.get("notify_url")).append("</notify_url>")
            .append("<out_trade_no>").append(xo.get("out_trade_no")).append("</out_trade_no>")
            .append("<spbill_create_ip>").append(xo.get("spbill_create_ip")).append("</spbill_create_ip>")
            .append("<total_fee>").append(xo.get("total_fee")).append("</total_fee>")
            .append("<trade_type>").append(xo.get("trade_type")).append("</trade_type>")
            .append("<openid>").append(xo.get("openid")).append("</openid>")
            .append("<sign><![CDATA[").append(xo.get("sign")).append("]]></sign>")
            .append("</xml>");

        StringBuffer resultXml =  new StringBuffer();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
            StringEntity myEntity = new StringEntity(xml.toString(), "utf-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(myEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    InputStreamReader reader = new InputStreamReader(resEntity.getContent());
                    char[] buff = new char[1024];
                    while ((reader.read(buff)) != -1) {
                        resultXml.append(buff);
                    }
                }
            } 
            finally {
                response.close();
            }
        }
        finally {
            httpclient.close();
        }
        return resultXml.toString();
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Aug 22, 2015 11:56:02 AM
    * @param mediaid        微信的资源id
    * @param key            七牛目标地址
    * @param accessToken    微信api访问口令
    * @return               成功返回码0,失败返回其他错误码
     */
    public BaseDto uploadMediaToQiniu(String mediaid, String key, String accessToken){
        String mediaUrl = String.format(App.Wechat.FETCH_MEDIA_URL, new Object[] {accessToken, mediaid});
        return qiniuService.fetch(mediaUrl, key);
    }
}