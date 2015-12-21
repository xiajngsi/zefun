package com.zefun.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 验证工具列
* @author 高国藩
* @date 2015年8月4日 下午5:07:42 
*
 */
public class SignUtil {
    /**
     * 与接口配置信息中的Token要一致
     */
    private static String token = "weixinCourse";

    /**
     * 验证签名
     * @param signature s
     * @param timestamp t
     * @param nonce b
     * @return boolean b
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }
    
    /**
     * 微信js api签名
    * @author 张进军
    * @date Aug 17, 2015 4:30:08 PM
    * @param jsapiTicket    js api票据
    * @param url            需要使用js api的地址路径
    * @param appId          微信应用id
    * @return               签名后的数据
     */
    public static Map<String, String> jsSign(String jsapiTicket, String url, String appId) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr
                + "&timestamp=" + timestamp + "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("appId", appId);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
    

    /**
     * 微信 地址api签名
    * @author 张进军
    * @date Aug 17, 2015 4:31:27 PM
    * @param accessToken        口令
    * @param url            需要使用地址api的地址路径
    * @param appId          微信应用id
    * @return               签名后的数据
     */
    public static Map<String, String> addrSign(String accessToken, String url, String appId) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        String string1 = "accesstoken=" + accessToken + "&appid=" + appId 
                + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("addr_appId", appId);
        ret.put("addr_scope", "jsapi_address");
        ret.put("addr_signType", "SHA1");
        ret.put("addr_addrSign", signature);
        ret.put("addr_nonceStr", nonceStr);
        ret.put("addr_timestamp", timestamp);

        return ret;
    }
    
    /**
     * 生成支付签名
    * @author 张进军
    * @date Sep 23, 2015 8:35:46 PM
    * @param params     支付参数
    * @param payKey     微信商户支付密钥
    * @return           签名字符串
     */
    public static String paySign(Map<String, String> params, String payKey) {
        //签名步骤一：按字典序排序参数
        String str = toUrlParams(params);
        //签名步骤二：在string后加入KEY
        str = str + "&key=" + payKey;
        //签名步骤三：MD5加密
        str = DigestUtils.md5Hex(str.getBytes());
        //签名步骤四：所有字符转为大写
        str = str.toUpperCase();
        return str;
    }
    
    
    /**
     * 将map参数转为url参数并排序
    * @author 张进军
    * @date Sep 23, 2015 8:36:18 PM
    * @param params     map参数
    * @return           排序后的参数字符串
     */
    public static String toUrlParams(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            
            if (value == null || value.equals("")
                    || key.equalsIgnoreCase("key")
                    || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("paySign")) {
                continue;
            }

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /**
     * 获取uuid随机字符串
    * @author 张进军
    * @date Aug 17, 2015 4:23:52 PM
    * @return   uuid随机字符串
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取时间戳
    * @author 张进军
    * @date Aug 17, 2015 4:24:12 PM
    * @return   当前秒数
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
     * byte数组转16进制字符串
    * @author 张进军
    * @date Aug 17, 2015 4:24:48 PM
    * @param hash   byte数组
    * @return       字符串
     */
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param byteArray 数组
     * @return String
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * @param mByte b
     * @return String
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
