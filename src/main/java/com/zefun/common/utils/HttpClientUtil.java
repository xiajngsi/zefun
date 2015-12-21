package com.zefun.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * http请求工具类
* @author 张进军
* @date Nov 23, 2015 9:44:49 AM
 */
public class HttpClientUtil {

    /**https*/
    private static final String HTTPS_PROTOCOL = "https://";
    
    /**https 端口*/
    private static final int HTTPS_PROTOCOL_DEFAULT_PORT = 443;

    /**默认编码格式*/
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**日志记录对象*/
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);


    /**
     * 发送GET请求
     * @param url 请求地址
     * @param charset 返回数据编码
     * @return 返回数据
     */
    public static String sendGetReq(final String url, String charset) {
        if (null == charset) {
            charset = DEFAULT_CHARSET;
        }
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
            initSSL(httpClient, getPort(url));
        }
        try {
            // 提交请求并以指定编码获取返回数据
            HttpResponse httpResponse = httpClient.execute(get);
            int statuscode = httpResponse.getStatusLine().getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header header = httpResponse.getFirstHeader("location");
                if (header != null) {
                    String newuri = header.getValue();
                    if ((newuri == null) || (newuri.equals(""))){
                        newuri = "/";
                    }
                    try {
                        httpClient.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        httpClient = null;
                    }
                    logger.info("重定向地址：" + newuri);
                    return sendGetReq(newuri, null);
                }
            }
            logger.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, charset);
        }
        catch (ClientProtocolException e) {
            logger.error("协议异常,堆栈信息如下", e);
        }
        catch (IOException e) {
            logger.error("网络异常,堆栈信息如下", e);
        }
        finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                httpClient = null;
            }
        }
        return null;
    }


    /**
     * 发送POST请求，支持HTTP与HTTPS
     * @param url 请求地址
     * @param params 请求参数
     * @param charset 返回数据编码
     * @return 返回数据
     */
    public static String sendPostReq(String url, Map<String, ?> params, String charset) {
        if (null == charset){
            charset = DEFAULT_CHARSET;
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig reqConf = RequestConfig.DEFAULT;
        HttpPost httpPost = new HttpPost(url);
        // 封装请求参数
        List<NameValuePair> pairs = geneNameValPairs(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            // 对HTTPS请求进行处理
            if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
                initSSL(httpClient, getPort(url));
            }
            // 提交请求并以指定编码获取返回数据
            httpPost.setConfig(reqConf);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statuscode = httpResponse.getStatusLine().getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header header = httpResponse.getFirstHeader("location");
                if (header != null) {
                    String newuri = header.getValue();
                    if ((newuri == null) || (newuri.equals(""))){
                        newuri = "/";
                    }
                    try {
                        httpClient.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        httpClient = null;
                    }
                    return sendGetReq(newuri, null);
                }
            }

            logger.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, charset);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("不支持当前参数编码格式[" + charset + "],堆栈信息如下", e);
        }
        catch (ClientProtocolException e) {
            logger.error("协议异常,堆栈信息如下", e);
        }
        catch (IOException e) {
            logger.error("网络异常,堆栈信息如下", e);
        }
        finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                httpClient = null;
            }
        }
        return null;
    }
    
    
    /**
     * 以post的方式下载文件，返回文件的字节数组
    * @author 张进军
    * @date Nov 23, 2015 9:59:03 AM
    * @param url    下载地址
    * @param params 请求参数
    * @return   文件字节数组
     */
    public static byte[] downloadPost(String url, Map<String, String> params) {
        String charset = DEFAULT_CHARSET;
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig reqConf = RequestConfig.DEFAULT;
        HttpPost httpPost = new HttpPost(url);
        // 封装请求参数
        List<NameValuePair> pairs = geneNameValPairs(params);
        
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            // 对HTTPS请求进行处理
            if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
                initSSL(httpClient, getPort(url));
            }
            // 提交请求并以指定编码获取返回数据
            httpPost.setConfig(reqConf);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            logger.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            
            HttpEntity entity = httpResponse.getEntity();
            if (entity.getContentType().getValue().equals("application/json")) {
                return null;
            }
            return EntityUtils.toByteArray(entity);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("不支持当前参数编码格式[" + charset + "],堆栈信息如下", e);
        }
        catch (ClientProtocolException e) {
            logger.error("协议异常,堆栈信息如下", e);
        }
        catch (IOException e) {
            logger.error("网络异常,堆栈信息如下", e);
        }
        finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                httpClient = null;
            }
        }
        return null;
    }
    
    /**
     * 将map参数转换为http参数
    * @author 张进军
    * @date Nov 23, 2015 9:49:53 AM
    * @param params 请求参数对象
    * @return   http请求参数对象
     */
    private static List<NameValuePair> geneNameValPairs(Map<String, ?> params) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (params == null) {
            return pairs;
        }
        for (String name : params.keySet()) {
            if (params.get(name) == null) {
                continue;
            }
            pairs.add(new BasicNameValuePair(name, params.get(name).toString()));
        }
        return pairs;
    }
    
    
    /**
     * 根据请求地址获取端口
    * @author 张进军
    * @date Nov 23, 2015 9:46:57 AM
    * @param url    请求地址
    * @return   端口
     */
    private static int getPort(String url) {
        int startIndex = url.indexOf("://") + "://".length();
        String host = url.substring(startIndex);
        if (host.indexOf("/") != -1) {
            host = host.substring(0, host.indexOf("/"));
        }
        int port = HTTPS_PROTOCOL_DEFAULT_PORT;
        if (host.contains(":")) {
            int i = host.indexOf(":");
            port = new Integer(host.substring(i + 1));
        }
        return port;
    }

    /**
     * 初始化HTTPS请求服务
     * @param httpClient HTTP客户端
     * @param port 端口
     */
    public static void initSSL(CloseableHttpClient httpClient, int port) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            final X509TrustManager trustManager = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            // 使用TrustManager来初始化该上下文,TrustManager只是被SSL的Socket所使用
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            ConnectionSocketFactory ssf = new SSLConnectionSocketFactory(sslContext);
            Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory> create().register("https", ssf).build();
            BasicHttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(r);
            HttpClients.custom().setConnectionManager(ccm).build();
        }
        catch (KeyManagementException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
}
