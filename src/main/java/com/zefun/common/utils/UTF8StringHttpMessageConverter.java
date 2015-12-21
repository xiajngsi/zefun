package com.zefun.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

/**
 * 
* @author 洪秋霞
* @date 2015年8月11日 下午2:31:25
 */
public class UTF8StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {

    /**
     * 默认编码
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 可用数据集
     */
    private final List<Charset> availableCharsets;

    /**
     * writeAccept字符集
     */
    private boolean writeAcceptCharset = true;

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:32:11
     */
    public UTF8StringHttpMessageConverter() {
        super(new MediaType("text", "plain", DEFAULT_CHARSET), MediaType.ALL);
        this.availableCharsets = new ArrayList<Charset>(Charset.availableCharsets().values());
    }

    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }

    /**
     * @param clazz c
     * @return boolean
     */
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    /**
     * @param clazz c
     * @param inputMessage i
     * @throws IOException 异常
     * @return String
     */
    protected String readInternal(@SuppressWarnings("rawtypes") Class clazz, HttpInputMessage inputMessage) throws IOException {
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));
    }

    /**
     * @param s s
     * @param contentType 内容
     * @return Long
     */
    protected Long getContentLength(String s, MediaType contentType) {
        Charset charset = getContentTypeCharset(contentType);
        try {
            return (long) s.getBytes(charset.name()).length;
        }
        catch (UnsupportedEncodingException ex) {
            throw new InternalError(ex.getMessage());
        }
    }

    /**
     * @param s s
     * @param outputMessage o
     * @throws IOException 异常
     */
    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        if (writeAcceptCharset) {
            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
        }
        Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), charset));
    }

    protected List<Charset> getAcceptedCharsets() {
        return this.availableCharsets;
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:34:34
    * @param contentType 内容
    * @return Charset
     */
    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharSet() != null) {
            return contentType.getCharSet();
        }
        else {
            return DEFAULT_CHARSET;
        }
    }

}
