package com.zefun.wechat.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ListUtil;
import com.zefun.common.utils.MyX509TrustManagerUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AutomaticReply;
import com.zefun.web.entity.ItemCensus;
import com.zefun.web.entity.PictureLibrary;
import com.zefun.web.mapper.AutomaticReplyMapper;
import com.zefun.web.mapper.ItemCensusMapper;
import com.zefun.web.mapper.MsgReplyMapper;
import com.zefun.web.mapper.PictureLibraryMapper;
import com.zefun.web.service.RedisService;
import com.zefun.wechat.dto.ArticlesDto;
import com.zefun.wechat.dto.ItemDto;
import com.zefun.wechat.dto.ItemStatusDto;
import com.zefun.wechat.dto.ItmesDto;
import com.zefun.wechat.dto.NewsItemsDto;
import com.zefun.wechat.dto.ThumbDto;
import com.zefun.wechat.dto.UpdateArticleDto;
import com.zefun.wechat.dto.UpdateChangeThumb;
import com.zefun.wechat.dto.UpdateItemsDto;

/**
 * 微信交互信息serivce
* @author 高国藩
* @date 2015年8月11日 上午11:48:24
 */
@Service
public class WeixinUploadService {
    /** 菜单回复信息 */
    @Autowired
    private AutomaticReplyMapper automaticReplyMapper;
    /** 图片库管理 */
    @Autowired
    private PictureLibraryMapper pictureLibraryMapper;
    /**redis*/
    @Autowired
    private RedisService redisService;
    /**发送记录表*/
    @Autowired
    private ItemCensusMapper itemCensusMapper;
    /**自动回复*/
    @Autowired
    private MsgReplyMapper msgReplyMapper;
    /** 日志 */
    private static Logger logger = Logger.getLogger(WeixinUploadService.class);

    /**
     * 上传图文素材,获得永久mediaId
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:33
    * @param article 图文消息
    * @param ACCESS_TOKEN 微信认证
    * @return 返回medaiId
     */
    public static String getMediaId(ArticlesDto article, String ACCESS_TOKEN) {
        String url = Url.Wechat.GETMEDIAID;
        url = url.replace("ACCESS_TOKEN", ACCESS_TOKEN);
        JSONObject json = httpRequest(url, "POST", JSONObject.fromObject(article).toString());
        return json.getString("media_id");
    }

    /**
     * 上传图片根据url的不同,分别可获得ThumbMediaId或PicUrl
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:25
    * @param url 七牛图片地址
    * @param ACCESS_TOKEN 微信认证
    * @param imgUrl 上传至微信的图片地址
    * @return 返回微信图片地址
     */
    public static String getThumbMediaUrl(String url, String ACCESS_TOKEN,
            String imgUrl) {
        InputStream in = sendGETRequest(imgUrl);
        Map<String, InputStream> fileMap = new HashMap<String, InputStream>();
        fileMap.put("media", in);
        String result = formUpload(url, null, fileMap);
        logger.info(result);
        return JSONObject.fromObject(result).getString("url").toString();

    }

    /**
     * 获得微信上传的永久素材ThumbMediaId
    * @author 高国藩
    * @date 2015年8月11日 上午11:55:01
    * @param url 微信上传永久素材url
    * @param ACCESS_TOKEN 微信认证
    * @param imgUrl 七牛图片地址
    * @return 返回id
     */
    public static String getThumbMediaId(String url, String ACCESS_TOKEN,
            String imgUrl) {
        InputStream in = sendGETRequest(imgUrl);
        Map<String, InputStream> fileMap = new HashMap<String, InputStream>();
        Map<String, String> textMap = new HashMap<String, String>();
        textMap.put("type", "thumb");
        fileMap.put("media", in);
        String result = formUpload(url, null, fileMap);
        logger.info(result);
        return JSONObject.fromObject(result).getString("media_id").toString();

    }

    /**
     * 获取素材列表 ,offset 从第几个开始 一共取出count个数据
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:17
    * @param offset 偏移量
    * @param count 个数
    * @param accessToken 微信认证
    * @return 返回图文消息的json数据
     */
    public ItemDto getNews(String offset, String count, String accessToken) {
        String url = Url.Wechat.GET_THUMB;
        url = url.replace("ACCESS_TOKEN", accessToken);
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "news");
        map.put("offset", offset);
        map.put("count", count);
        JSONObject json = httpRequest(url, "POST", JSONObject.fromObject(map)
                .toString());
        ItemDto item = (ItemDto) JSONObject.toBean(json, ItemDto.class);
        return item;
    }

    /**
     * 根据media_id，获得图文素材的信息
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:08
    * @param media_id 图文id
    * @param accessToken 微信认证
    * @return 返回图文消息类
     */
    public ItmesDto getNewsByMediaId(String media_id, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", accessToken);
        Map<String, String> map = new HashMap<String, String>();
        map.put("media_id", media_id);
        JSONObject json = httpRequest(url, "POST", JSONObject.fromObject(map)
                .toString());
        ItmesDto itmes = (ItmesDto) JSONObject.toBean(json, ItmesDto.class);
        return itmes;
    }

    /**
     * 正式上传图文消息
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:50
    * @param ACCESS_TOKEN 微信认证
    * @param thumb 图文接受form表单请求
    * @param itemNum 图文消息数量
    * @param storeId 门店id
    * @return 返回状态
     */
    public BaseDto uploadNews(String ACCESS_TOKEN, ThumbDto thumb,
            Integer storeId, Integer itemNum) {
        /** 更新地址,换区ACCESS_TOKEN */
        String getPicUrl = Url.Wechat.GETPICURL;
        String getThumbMediaIdUrl = Url.Wechat.GETTHUMBID;
        getPicUrl = getPicUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
        getThumbMediaIdUrl = getThumbMediaIdUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
        /** 映射字段，form表单提交 */
        String[] imgUrl = thumb.getImgUrl().split("@!,");
        String[] author = thumb.getAuthor().split("@!,");
        String[] title = thumb.getTitle().split("@!,");
        String[] contentSourceUrl = thumb.getContent_source_url().split("@!,");
        String[] content = thumb.getContent().split("@!,");
        /**将描述和摘要绑定在一起，作为一样的内容即可*/
        String[] description = thumb.getDescription().split("@!,");
        /** 保存上传的微信永久图片*/
        List<String> autoImgUrl = new ArrayList<String>();
        /** 生成微信图文消息对应字段 */
        ArticlesDto article = new ArticlesDto();
        List<ThumbDto> articles = new ArrayList<ThumbDto>();
        for (int i = 0; i < itemNum; i++) {
            /** 微信上传的imgUrl-永久 */
            String picUrl = WeixinUploadService.getThumbMediaUrl(getPicUrl,
                    ACCESS_TOKEN, imgUrl[i]);
            /** 微信上传的thumb_medai_id-永久 */
            String thumbMediaId = WeixinUploadService.getThumbMediaId(
                    getThumbMediaIdUrl, ACCESS_TOKEN, picUrl);
            //进行异常抓取,如果没有填写阅读原文
            try {
                ThumbDto thumbDto = new ThumbDto(thumbMediaId, author[i], title[i], contentSourceUrl[i], content[i], description[i], "0");
                articles.add(thumbDto);
                autoImgUrl.add(picUrl);
            } 
            catch (Exception e) {
                ThumbDto thumbDto = new ThumbDto(thumbMediaId, author[i], title[i], "", content[i], description[i], "0");
                articles.add(thumbDto);
                autoImgUrl.add(picUrl);   
            }
            
        }
        article.setArticles(articles);
        /** 生成素材mediaId */
        String mediaId = WeixinUploadService.getMediaId(article, ACCESS_TOKEN);
        logger.info("图文消息素材上传ID:" + mediaId);
        /** 得到MedaiId后,说明上传成功,根据这个MedaiId获得图文消息,然后拉取下来,将其封装成自动回复图文消息的素材保存下来即可 */
        int status = -1;
        List<AutomaticReply> automaticReplies = new ArrayList<AutomaticReply>();
        if (mediaId != null && !mediaId.equals("")) {
            ItmesDto itmesDto = this.getNewsByMediaId(mediaId, ACCESS_TOKEN);
            List<NewsItemsDto> newsItem = itmesDto.getNews_item();
            for (int i = 0; i < newsItem.size(); i++) {
                /**保存图文素材,用于自动回复图文消息,也可用于选择链接地址*/
                NewsItemsDto newsItemsDto = (NewsItemsDto) JSONObject.toBean(
                        JSONObject.fromObject(newsItem.get(i)),
                        NewsItemsDto.class);
                try {
                    AutomaticReply automaticReply = new AutomaticReply(mediaId,
                            newsItemsDto.getTitle(), author[i], newsItemsDto.getUrl(),
                            description[i], autoImgUrl.get(i), imgUrl[i], articles.get(i).getThumb_media_id(),
                            String.valueOf(new Date().getTime()), content[i], contentSourceUrl[i], storeId, storeId.toString());
                    automaticReplies.add(automaticReply);
                } 
                catch (Exception e) {
                    AutomaticReply automaticReply = new AutomaticReply(mediaId,
                            newsItemsDto.getTitle(), author[i], newsItemsDto.getUrl(),
                            description[i], autoImgUrl.get(i), imgUrl[i], articles.get(i).getThumb_media_id(),
                            String.valueOf(new Date().getTime()), content[i], "", storeId, storeId.toString());
                    automaticReplies.add(automaticReply);
                }
            }
            /** 将得到的图文消息保存入数据库中 */
            status = automaticReplyMapper.insertByList(automaticReplies);
        }
        if (status > 0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, JSONArray.fromObject(automaticReplies));
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "新增失败");
        }
    }
    
    /**
     * 下拉开发商的图文消息
    * @author 高国藩
    * @date 2015年9月17日 上午11:20:12
    * @param storeId 门店
    * @return 状态
     */
    public BaseDto actionUploadItems(Integer storeId) {
        //获得该门店所有的图文消息的媒体id,注意查询到的是father_media_id
        List<String> medias = automaticReplyMapper.selectItemsByStoreIdHasItems(storeId);
        //如果是空,说明没有下载过任何媒体
        if (medias==null || medias.size()<=0){
            List<String> mediaIds = automaticReplyMapper.selectItmesByStoreIdIsZero(0);
            for (int i = 0; i < mediaIds.size(); i++) {
                List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByMediaId(mediaIds.get(i));
                try {
                    String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
                    initItmesEveryStore(automaticReplies, storeId, accessToken);
                } 
                catch (Exception e) {
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
                }
                
            }
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_FAIL);
        }
      //过略下载媒体
        else {
            List<String> mediaIds = automaticReplyMapper.selectItemsByStoreIdNotSelf(medias);
            if (mediaIds==null || mediaIds.size()<=0){
                //如果此处是空,那么说明官方没有发布任何的图文消息
            }
            else {
                for (int i = 0; i < mediaIds.size(); i++) {
                    List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByMediaId(mediaIds.get(i));
                    try {
                        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
                        initItmesEveryStore(automaticReplies, storeId, accessToken);
                    } 
                    catch (Exception e) {
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
                    }
                }
            }
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
    /**
     * 门店开始复制图文消息
    * @author 高国藩
    * @date 2015年9月9日 下午3:03:42
    * @param automaticReplies 开发商图文消息
    * @param storeId 门店信息
    * @param accessToken 微信认证
     */
    public void initItmesEveryStore(List<AutomaticReply> automaticReplies, Integer storeId, String accessToken){
        /**以上代表了从数据库中查询信息*/
        String getPicUrl = Url.Wechat.GETPICURL;
        String getThumbMediaIdUrl = Url.Wechat.GETTHUMBID;
        getPicUrl = getPicUrl.replace("ACCESS_TOKEN", accessToken);
        getThumbMediaIdUrl = getThumbMediaIdUrl.replace("ACCESS_TOKEN", accessToken);
        /** 保存上传的微信永久图片*/
        ArticlesDto article = new ArticlesDto();
        List<ThumbDto> articles = new ArrayList<ThumbDto>();
        for (int j = 0; j < automaticReplies.size(); j++) {
            AutomaticReply automaticReply = automaticReplies.get(j);
            String imgUrl = automaticReply.getQiniuImg();
            /** 微信上传的imgUrl-永久 */
            String picUrl = WeixinUploadService.getThumbMediaUrl(getPicUrl,
                    accessToken, imgUrl);
            /** 微信上传的thumb_medai_id-永久 */
            String thumbMediaId = WeixinUploadService.getThumbMediaId(
                    getThumbMediaIdUrl, accessToken, picUrl);
            ThumbDto thumbDto = new ThumbDto(thumbMediaId, automaticReply.getAuthor(), automaticReply.getTitle(), 
                    automaticReply.getContentSourceUrl(), automaticReply.getContent(), automaticReply.getDescription(), "0");
            articles.add(thumbDto);
        }
        article.setArticles(articles);
        String mediaId = WeixinUploadService.getMediaId(article, accessToken);
        /**存入数据库中*/
        List<AutomaticReply> autoReply = new ArrayList<AutomaticReply>();
        if (mediaId != null && !mediaId.equals("")) {
            ItmesDto itmesDto = this.getNewsByMediaId(mediaId, accessToken);
            List<NewsItemsDto> newsItem = itmesDto.getNews_item();
            for (int k = 0; k < newsItem.size(); k++) {
                AutomaticReply autoItems = automaticReplies.get(k);
                NewsItemsDto newsItemsDto = (NewsItemsDto) JSONObject.toBean(
                        JSONObject.fromObject(newsItem.get(k)),
                        NewsItemsDto.class);
                AutomaticReply automaticReply = new AutomaticReply(mediaId,
                        newsItemsDto.getTitle(), autoItems.getAuthor(), newsItemsDto.getUrl(),
                        autoItems.getDescription(), autoItems.getPicUrl(), autoItems.getQiniuImg(), articles.get(k).getThumb_media_id(),
                        String.valueOf(new Date().getTime()), autoItems.getContent(), autoItems.getContentSourceUrl(), storeId, 
                        automaticReplies.get(0).getMediaId());
                autoReply.add(automaticReply);
            } 
            /** 将得到的图文消息保存入数据库中 */
            automaticReplyMapper.insertByList(autoReply);
        }
    }

    /**
     * HTTPS 请求方式
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:59
    * @param requestUrl 请求链接
    * @param requestMethod 请求方式
    * @param outputStr 请求数据
    * @return 返回json
     */
    public static JSONObject httpRequest(String requestUrl,
            String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            TrustManager[] tm = { new MyX509TrustManagerUtil() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
                    .openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
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
            jsonObject = JSONObject.fromObject(buffer.toString());
        } 
        catch (Exception ce) {
            ce.printStackTrace();
        }
        logger.info(jsonObject.toString());
        return jsonObject;
    }

    /**
     * 上传文件
    * @author 高国藩
    * @date 2015年8月11日 下午12:54:20
    * @param urlStr 请求链接
    * @param textMap 请求文本数据
    * @param fileMap 请求文件数据
    * @return 返回String
     */
    @SuppressWarnings("unused")
    public static String formUpload(String urlStr, Map<String, String> textMap,
            Map<String, InputStream> fileMap) {
        String res = "";
        HttpURLConnection conn = null;
        String boundary = "------WebKitFormBoundaryZVwkbA47qbv8sxAs";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                @SuppressWarnings("rawtypes")
                Iterator iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    @SuppressWarnings("rawtypes")
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(boundary)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                logger.info(strBuf.toString());
                out.write(strBuf.toString().getBytes());
            }
            if (fileMap != null) {
                @SuppressWarnings("rawtypes")
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    @SuppressWarnings("rawtypes")
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    InputStream inputValue = (InputStream) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(boundary)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + "media" + "\"; filename=\"" + new Date() + ".jpg"
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + "image/png" + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    DataInputStream in = new DataInputStream(inputValue);
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }
            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    /**
     * 请求链接转换位流
    * @author 高国藩
    * @date 2015年8月11日 下午12:56:33
    * @param path 请求链接
    * @return 返回流
     */
    public static InputStream sendGETRequest(String path) {
        try {
            StringBuilder url = new StringBuilder(path);
            HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
                    .openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                return conn.getInputStream();
            }
        } 
        catch (Exception e) {
        }
        return null;
    }

    /**
     * 上传图片值微信，获得微信的图片地址
    * @author 高国藩
    * @date 2015年8月27日 下午7:27:50
    * @param imgUrl 图片链接
    * @param accessToken 微信认证
    * @return 返回信息
     */
    public BaseDto uploadImg(String imgUrl, String accessToken) {
        String getPicUrl = Url.Wechat.GETPICURL;
        getPicUrl = getPicUrl.replace("ACCESS_TOKEN", accessToken);
        String weichatImgUrl = WeixinUploadService.getThumbMediaUrl(getPicUrl,
                accessToken, imgUrl);
        if (weichatImgUrl != null && !weichatImgUrl.equals("")) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, weichatImgUrl);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, weichatImgUrl);
        }
    }

    /**
     * 更新图文消息
    * @author 高国藩
    * @date 2015年8月31日 下午5:12:38
    * @param updateItemsDto 一组图文消息
    * @param updateArticleDto 详情
    * @param updateChangeThumb 更新数据库
    * @param ACCESS_TOKEN 微信认证
    * @param itemNum 个数判断
    * @return 跳转页面
     */
    public BaseDto updateItems(UpdateItemsDto updateItemsDto, UpdateArticleDto updateArticleDto, 
            UpdateChangeThumb updateChangeThumb, String ACCESS_TOKEN, Integer itemNum) {
        String updateItemsUrl = Url.Wechat.UPDATE_WECHAT;
        updateItemsUrl = updateItemsUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
        String[] replyId = null;
        String[] mediaId = null;
        String[] index= null;
        String[] title = null;
        String[] thumbMediaId = null;
        String[] author = null;
        String[] digest = null;
        String[] content = null;
        String[] contentSourceUrl = null;
        String[] picUrl = null;
        String[] qiniuImg = null;
     /*   if (itemNum == 1){
            replyId = updateItemsDto.getReplyId().split("@!");
            mediaId = updateItemsDto.getMedia_id().split("@!");
            index= updateItemsDto.getIndex().split(",");
            title = updateArticleDto.getTitle().split("@!");
            thumbMediaId = updateArticleDto.getThumb_media_id().split("@!");
            author = updateArticleDto.getAuthor().split("@!");
            digest = updateArticleDto.getDigest().split("@!");
            content = updateArticleDto.getContent().split("@!");
            contentSourceUrl = updateArticleDto.getContent_source_url().split("@!");
            picUrl = updateChangeThumb.getPicUrl().split("@!");
            qiniuImg = updateChangeThumb.getQiniuImg().split("@!");
        }
        else {*/
        replyId = updateItemsDto.getReplyId().split("@!,");
        mediaId = updateItemsDto.getMedia_id().split("@!,");
        index= updateItemsDto.getIndex().split(",");
        title = updateArticleDto.getTitle().split("@!,");
        thumbMediaId = updateArticleDto.getThumb_media_id().split("@!,");
        author = updateArticleDto.getAuthor().split("@!,");
        digest = updateArticleDto.getDigest().split("@!,");
        content = updateArticleDto.getContent().split("@!,");
        contentSourceUrl = updateArticleDto.getContent_source_url().split("@!,");
        picUrl = updateChangeThumb.getPicUrl().split("@!,");
        qiniuImg = updateChangeThumb.getQiniuImg().split("@!,");
            /*}
        String[] replyId = updateItemsDto.getReplyId().split(",");
        String[] mediaId = updateItemsDto.getMedia_id().split(",");
        String[] index= updateItemsDto.getIndex().split(",");
        String[] title = updateArticleDto.getTitle().split(",");
        String[] thumbMediaId = updateArticleDto.getThumb_media_id().split(",");
        String[] author = updateArticleDto.getAuthor().split(",");
        String[] digest = updateArticleDto.getDigest().split(",");
        String[] content = updateArticleDto.getContent().split(",");
        String[] contentSourceUrl = updateArticleDto.getContent_source_url().split(",");
        String[] picUrl = updateChangeThumb.getPicUrl().split(",");
        String[] qiniuImg = updateChangeThumb.getQiniuImg().split(",");*/
        int ok = 1;
        for (int i = 0; i < replyId.length; i++) {
            try {
                UpdateItemsDto itemsDto = new UpdateItemsDto(mediaId[i], index[i], new UpdateArticleDto(title[i], thumbMediaId[i],
                        author[i], digest[i], content[i], contentSourceUrl[i]));
                JSONObject json = httpRequest(updateItemsUrl, "POST", JSONObject.fromObject(itemsDto).toString());
                ok = Integer.valueOf(json.get("errcode").toString());
            } 
            catch (Exception e) {
                UpdateItemsDto itemsDto = new UpdateItemsDto(mediaId[i], index[i], new UpdateArticleDto(title[i], thumbMediaId[i],
                        author[i], digest[i], content[i], ""));
                JSONObject json = httpRequest(updateItemsUrl, "POST", JSONObject.fromObject(itemsDto).toString());
                ok = Integer.valueOf(json.get("errcode").toString());
            }
        }
        if (ok == 0){
            for (int i = 0; i < replyId.length; i++) {
                AutomaticReply reply = new AutomaticReply(Integer.valueOf(replyId[i]), mediaId[i], title[i], author[i], digest[i], picUrl[i], 
                        qiniuImg[i], thumbMediaId[i], content[i], 1);
                automaticReplyMapper.updateById(reply);
            }
        }
        return new BaseDto();
        //return new ModelAndView("redirect:/"+Url.Wechat.ITEMS_MANAGE);
    }

    /**
     * 更新thumbMediaId
    * @author 高国藩
    * @date 2015年9月1日 上午10:52:37
    * @param qiniuImg 七牛图片地址
    * @param accessToken 微信认证
    * @return 返回thumbMediaId和微信图片地址
     */
    public BaseDto updateThumbMediaId(String qiniuImg, String accessToken) {
        String getPicUrl = Url.Wechat.GETPICURL;
        String getThumbMediaIdUrl = Url.Wechat.GETTHUMBID;
        getPicUrl = getPicUrl.replace("ACCESS_TOKEN", accessToken);
        getThumbMediaIdUrl = getThumbMediaIdUrl.replace("ACCESS_TOKEN", accessToken);
        String picUrl = WeixinUploadService.getThumbMediaUrl(getPicUrl, accessToken, qiniuImg);
        String thumbMediaId = WeixinUploadService.getThumbMediaId(getThumbMediaIdUrl, accessToken, picUrl);
        Map<String, String> map = new HashMap<String, String>();
        map.put("picUrl", picUrl);
        map.put("thumbMediaId", thumbMediaId);
        return new BaseDto(0, map);
    }

    /**
     * 上传图片
    * @author 高国藩
    * @date 2015年9月2日 下午12:10:31
    * @param imgUrl 七牛图片地址
    * @param accessToken 微信认证
    * @param storeId 门店信息
    * @return 状态，附加图片信息
     */
    public BaseDto initImg(String imgUrl, String accessToken, Integer storeId) {
        String getPicUrl = Url.Wechat.GETPICURL;
        String getThumbMediaIdUrl = Url.Wechat.GETTHUMBID;
        getPicUrl = getPicUrl.replace("ACCESS_TOKEN", accessToken);
        getThumbMediaIdUrl = getThumbMediaIdUrl.replace("ACCESS_TOKEN", accessToken);
        String picUrl = WeixinUploadService.getThumbMediaUrl(getPicUrl, accessToken, imgUrl);
        String thumbMediaId = WeixinUploadService.getThumbMediaId(getThumbMediaIdUrl, accessToken, picUrl);
        PictureLibrary pictureLibrary = new PictureLibrary(imgUrl, picUrl, thumbMediaId, storeId);
        int ok = pictureLibraryMapper.insert(pictureLibrary);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, pictureLibrary);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除图片
    * @author 高国藩
    * @date 2015年9月2日 下午4:42:02
    * @param pictureId 主键标示
    * @return 状态
     */
    public BaseDto deleteImg(Integer pictureId) {
        int ok = pictureLibraryMapper.deleteByPrimaryKey(pictureId);
        if (ok>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 新增图文消息,将图片库的信息带入
    * @author 高国藩
    * @date 2015年9月6日 上午9:37:03
    * @param storeId 门店ID
    * @return 返回页面
     */
    public ModelAndView aritcManager(Integer storeId) {
        List<PictureLibrary> pictureLibraries = pictureLibraryMapper.selectPicturesByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.Wechat.ARTIC_MANAGER);
        view.addObject("pictureLibraries", pictureLibraries);
        return view;
    }

    /**
     * 删除图文消息
    * @author 高国藩
    * @date 2015年9月6日 下午4:09:18
    * @param mediaId 媒体id
    * @param accessToken 微信认证
    * @return 删除状态
     */
    public BaseDto deleteItems(String mediaId, String accessToken) {
        String deleteUrl = Url.Wechat.DELETE_WECHAT;
        deleteUrl = deleteUrl.replace("ACCESS_TOKEN", accessToken);
        Map<String, String> map = new HashMap<String, String>();
        map.put("media_id", mediaId);
        JSONObject status = httpRequest(deleteUrl, "POST", JSONObject.fromObject(map).toString());
        if (Integer.valueOf(status.getString("errcode")) == 0){
            int ok = automaticReplyMapper.deleteItemsByMedaidId(mediaId);
            if (ok>0){
                //同样要将自动回复中的信息删除
                msgReplyMapper.deleteByMediaId(mediaId);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES); 
            }
            else {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
            }
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 进入图文消息状态统计
    * @author 高国藩
    * @date 2015年9月8日 上午9:57:57
    * @param storeId 门店
    * @return 跳转页面
     */
    public ModelAndView viewItemsStatus(Integer storeId) {
        List<ItemStatusDto> itemStatusDtos = new ArrayList<ItemStatusDto>();
        //首先获取开发商新建的图文消息 记得storeId是0
        List<AutomaticReply> automaticReplies  = automaticReplyMapper.selectItemsByStoreId(0);
        //从redis中读取商家次数
        for (int i = 0; i < automaticReplies.size(); i++) {
            AutomaticReply automaticReply = automaticReplies.get(i);
            String fatherMediaId = automaticReply.getMediaId();
            Long count = redisService.zcount(fatherMediaId, 0l, 1l);
            if (count == 0l){
                //如果在redis中没有值,那么说明没有店铺发送,去掉该统计
                automaticReplies.remove(i);
            }
            else {
                //查询出已经发送的对象
                List<ItemCensus> census = itemCensusMapper.selectHasSendObject(fatherMediaId);
                StringBuffer sql = new StringBuffer();
                for (int j = 0; j < census.size(); j++) {
                    sql.append(census.get(j).getHasGroup());
                }
                String hasOjb = ListUtil.getBigCountFormList(Arrays.asList(sql.toString().split(",")));
                ItemStatusDto itemStatusDto = new ItemStatusDto();
                itemStatusDto.setMediaId(fatherMediaId);
                itemStatusDto.setTitle(automaticReply.getTitle());
                itemStatusDto.setCreateTime(DateUtil.tranStrToDateStr(automaticReply.getCreateTime()));
                itemStatusDto.setHotCount(Integer.valueOf(count.toString()));
                itemStatusDto.setHasRead(hasOjb);
                itemStatusDtos.add(itemStatusDto);
            }
        }
        ModelAndView view = new ModelAndView(View.Wechat.ITEMS_STATUS);
        view.addObject("itemStatusDtos", itemStatusDtos);
        return view;
    }

    /**
     * 复制单个智放库中的图文消息
    * @author 高国藩
    * @date 2015年9月23日 下午5:06:32
    * @param mediaId 要进行复制的图文消息的媒体id
    * @param accessToken 微信认证
    * @param storeId 门店
    * @return 状态
     */
    public BaseDto actionCopyItemsFromZhifang(String mediaId,
            String accessToken, Integer storeId) {
        List<String> hasItems = automaticReplyMapper.selectItemsByStoreIdHasItems(storeId);
        /*1.判断是否已经复制,该查询到的是father_media_id*/
        if (hasItems.contains(mediaId)){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您已下载过该图文消息");
        }
        else {
            List<AutomaticReply> automaticReplies = automaticReplyMapper.selectByMediaId(mediaId);
            //下载量
            AutomaticReply automaticReplie = automaticReplyMapper.selectByOneItem(mediaId);
            if (automaticReplie.getDownloads() == null){
                automaticReplie.setDownloads(1);
            }
            else {
                automaticReplie.setDownloads(automaticReplie.getDownloads()+1);
            }
            automaticReplyMapper.updateByPrimaryKeySelective(automaticReplie);
            try {
                //正式下载
                initItmesEveryStore(automaticReplies, storeId, accessToken);
            } 
            catch (Exception e) {
                //失败之后将下载量-1
                automaticReplie.setDownloads(automaticReplie.getDownloads()-1);
                automaticReplyMapper.updateByPrimaryKeySelective(automaticReplie);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更新失败,请稍后重新下载");
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "更新成功");
    }
    
    /**
     * 发送统计图文的活跃度
    * @author 高国藩
    * @date 2015年11月16日 下午5:20:47
    * @param storeId 门店
    * @param mediaId 智放媒体ID
     */
    public void mediaCountItems(Integer storeId, String mediaId) {
        AutomaticReply automaticReplie = automaticReplyMapper.selectByOneItem(mediaId);
        if (automaticReplie == null){
            return;
        }
        String hasStoreIds = automaticReplie.getHasSendStore();
        if (hasStoreIds==null||hasStoreIds.equals("")){
            automaticReplie.setHasSendStore(storeId.toString()+",");
            automaticReplie.setMediaCount(1);
            automaticReplyMapper.updateByPrimaryKeySelective(automaticReplie);
        }
        else {
            if (Arrays.asList(hasStoreIds.split(",")).contains(storeId.toString())){
                logger.info("该门店已经对该图文进行了活动热度的统计");
                //对于已经发送统计过的门店将不再进行统计;
            }
            else {
                automaticReplie.setHasSendStore(automaticReplie.getHasSendStore()+storeId.toString()+",");
                automaticReplie.setMediaCount(automaticReplie.getMediaCount()+1);
                automaticReplyMapper.updateByPrimaryKeySelective(automaticReplie);
            }
        }
    }

    /**
     * 点赞操作
    * @author 高国藩
    * @date 2015年11月16日 下午5:20:47
    * @param storeId 门店
    * @param mediaId 媒体ID
    * @return 状态码
     */
    public BaseDto praiseItems(Integer storeId, String mediaId) {
        AutomaticReply automaticReplie = automaticReplyMapper.selectByOneItem(mediaId);
        String hasStoreIds = automaticReplie.getHasPraiseStore();
        //没有进行过点赞的行为
        if (hasStoreIds==null||hasStoreIds.equals("")){
            automaticReplie.setHasPraiseStore(storeId.toString()+",");
            automaticReplie.setPraise(1);
            automaticReplyMapper.updateByPrimaryKeySelective(automaticReplie);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        else {
            if (Arrays.asList(hasStoreIds.split(",")).contains(storeId.toString())){
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您已经赞过了!"); 
            }
            else {
                automaticReplie.setHasPraiseStore(automaticReplie.getHasPraiseStore()+storeId.toString()+",");
                automaticReplie.setPraise(automaticReplie.getPraise()+1);
                automaticReplyMapper.updateByPrimaryKeySelective(automaticReplie);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
            }
        }
    }

}
