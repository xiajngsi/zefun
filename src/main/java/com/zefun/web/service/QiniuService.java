package com.zefun.web.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.zefun.common.consts.App;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.web.controller.QiniuController;
import com.zefun.web.dto.BaseDto;

/**
 * 七牛api接口服务类
* @author 张进军
* @date Aug 21, 2015 5:49:35 PM 
*/
@Service
public class QiniuService {
    /** 七牛应用id */
    private static final String QINIU_AK = "y6orG-wW5CzUSJ_E0kk_jfCkEW16qZAu7ihg2g_n";
    /** 七牛应用密钥 */
    private static final String QINIU_SK = "czEgmRjAh_MFLEmLB6Qien41FxgbAZV5u8Bwvm7j";
    /** 空间名称 */
    private static final String QINIU_SCOPE = "zefun";
    /**域名*/
    public static final String DO_MAIN = "http://7xkv8r.com1.z0.glb.clouddn.com/";
    /**jpg*/
    private static final String JPG = "data:image/jpg;base64,";
    /**png*/
    private static final String PNG = "data:image/png;base64,";
    /**gif*/
    private static final String GIF = "data:image/gif;base64,";
    
    /**日志对象*/
    private Logger logger = Logger.getLogger(QiniuController.class);
    
    /**七牛授权对象*/
    private final Auth auth = Auth.create(QINIU_AK, QINIU_SK);
    
    /**七牛上传管理者*/
    private final UploadManager uploadManager = new UploadManager();
    
    /**七牛空间管理者*/
    private final BucketManager bucketManager = new BucketManager(auth);

    /** redis api服务对象 */
    @Autowired
    private RedisService redisService;
    
    /**
     * 获取默认上传策略的token
    * @author 张进军
    * @date Nov 23, 2015 10:02:10 AM
    * @return   token
     */
    public Map<String, String> qiniuUptoken() {
        String uptoken = getUptoken();
        Map<String, String> r = new HashMap<String, String>();
        r.put("uptoken", uptoken);
        return r;
    }
    
   
    /**
     * 抓取网络资源上传到七牛
    * @author 张进军
    * @date Aug 22, 2015 11:36:03 AM
    * @param fromUrl       资源文件地址
    * @param key           七牛目标地址
    * @return              成功返回码0,返回值为七牛上传的key
     */
    public BaseDto fetch(String fromUrl, String key) {
        try {
            bucketManager.fetch(fromUrl, QINIU_SCOPE, key);
        }
        catch (QiniuException e) {
            logger.error("fetch error : " , e);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, key);
    }
    
    
    /**
     * 文字转语音
    * @author 张进军
    * @date Nov 23, 2015 10:59:06 AM
    * @param text   需要转换的文字
    * @param per    发音人，1:男性，0:女性
    * @return   语音地址
     */
    public BaseDto textToVioce(String text, int per){
        Map<String, String> params = new HashMap<String, String>();
        params.put("tex", text);
        params.put("lan", "zh");
        params.put("cuid", "zefun");
        params.put("ctp", "1");
        params.put("per", "" + per);
        params.put("tok", redisService.get(App.Redis.BAIDU_TEXT_TO_VOICE_ACCESS_TOKEN_KEY));
        byte[] data = HttpClientUtil.downloadPost("http://tsn.baidu.com/text2audio", params);
        String key = "zefun/voice/" + System.nanoTime() + ".mp3";
        try {
            putByte(data, key);
        }
        catch (QiniuException e) {
            logger.error("textToVioce error : ", e);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, key);
    }
    
    
    /**
     * 上传文件到七牛
    * @author 张进军
    * @date Nov 23, 2015 11:33:39 AM
    * @param file   需上传的文件   
    * @param key    七牛目标地址
    * @return   成功返回hash、key
    * @throws QiniuException    七牛上传时抛出的异常
     */
    public StringMap putFile(File file, String key) throws QiniuException {
        return uploadManager.put(file, key, getUptoken()).jsonToMap();
    }
    
    
    /**
     * 上传文件到七牛
    * @author 张进军
    * @date Nov 23, 2015 11:33:39 AM
    * @param data   字节数组  
    * @param key    七牛目标地址
    * @return   成功返回hash、key
    * @throws QiniuException    七牛上传时抛出的异常
     */
    public StringMap putByte(byte[] data, String key) throws QiniuException {
        return uploadManager.put(data, key, getUptoken()).jsonToMap();
    }
    
    
    /**
     * 上传文件到七牛
    * @author 张进军
    * @date Nov 23, 2015 11:33:39 AM
    * @param filepath   需上传的文件路径   
    * @param key    七牛目标地址
    * @return   成功返回hash、key
    * @throws QiniuException    七牛上传时抛出的异常
     */
    public StringMap putFilepath(String filepath, String key) throws QiniuException {
        return uploadManager.put(filepath, key, getUptoken()).jsonToMap();
    }

    
    /**
     * 将base64上传至七牛图片库
    * @author 高国藩
    * @date 2015年11月9日 下午8:13:37
    * @param stringBase64 js生成的base64数据
    * @param key 自定义key
    * @return 七牛地址
     */
    @SuppressWarnings("restriction")
    public BaseDto put64(String stringBase64, String key) {
        try {
            if (stringBase64.startsWith(PNG)){
                stringBase64 = stringBase64.substring(PNG.length(), stringBase64.length());
            }
            else if (stringBase64.startsWith(JPG)){
                stringBase64 = stringBase64.substring(JPG.length(), stringBase64.length());
            }
            else if (stringBase64.startsWith(GIF)){
                stringBase64 = stringBase64.substring(GIF.length(), stringBase64.length());
            }
            key = new sun.misc.BASE64Encoder().encode(key.getBytes());
            if (key.indexOf("+") > 0) {
                key.replaceAll("+", "-");
            }
            if (key.indexOf("/") > 0) {
                key.replaceAll("/", "_");
            }
            String url = "http://up.qiniu.com/putb64/-1/key/" + key;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/octet-stream");
            header.put("Authorization", "UpToken " + getUptoken());
            JSONObject o = JSONObject.fromObject(httpRequest(url, "POST", stringBase64, header));
            String imageUrl = DO_MAIN + o.get("key");
            Map<String, String> map = new HashMap<String, String>();
            map.put("imageUrl", imageUrl);
            map.put("key", o.get("key").toString());
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
        } 
        catch (Exception e) {
            logger.error("put64 error : ", e);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
    }
    
    
    /**
     * HTTPS 请求方式
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:59
    * @param requestUrl 请求链接
    * @param requestMethod 请求方式
    * @param outputStr 请求数据
    * @param header     头部信息
    * @return 返回json
     */
    public static String httpRequest(String requestUrl,
            String requestMethod, String outputStr, Map<String, String> header) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url
                    .openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }
            if (header!=null){
                Set<String> set = header.keySet();
                for (String str : set) {
                    httpUrlConn.setRequestProperty(str, header.get(str));
                }
            }
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            return buffer.toString();
        } 
        catch (Exception ce) {
            ce.printStackTrace();
        }
        return "error";
    }
    
    
    /**
     * 获取上传默认策略的token
    * @author 张进军
    * @date Nov 22, 2015 10:33:30 PM
    * @return   token
     */
    private String getUptoken(){
        return auth.uploadToken(QINIU_SCOPE);
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Nov 23, 2015 5:37:54 PM
    * @param args       参数
    * @throws QiniuException    异常
     */
//    public static void main(String[] args) throws QiniuException {
//        QiniuService qiniu = new QiniuService();
//        String path = "/Users/smallpang/Downloads/zefun图库";
//        File file = new File(path);
//        File[] tempList = file.listFiles();
//        StringBuffer sb = new StringBuffer("INSERT INTO `code_library` (`type_no`, `type_name`, `code_no`, `code_name`, `sort_no`) VALUES ");
//        for (int i = 0; i < tempList.length; i++) {
//            File dir = tempList[i];
//            if (!dir.isDirectory()) {
//                continue;
//            }
//            String str = "(1100" + (i + 1) + ", '" + dir.getName() + "', %s, '%s', %s),";
//            File[] fileList = dir.listFiles();
//            for (int j = 0; j < fileList.length; j++) {
//                File f = fileList[j];
//                if (f.getName().equals(".DS_Store")) {
//                    continue;
//                }
//                
//                String key = "zefun/project/storage/" + System.nanoTime();
//                qiniu.putFile(f, key);
//
//                String c = str;
//                c = String.format(c, String.valueOf(j + 1), key, String.valueOf(j + 1));
//                sb.append(c);
//            }
//        }
//    }
}
