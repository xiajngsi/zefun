package com.zefun.common.exception;
/**
 * 
* @author 洪秋霞
* @date 2015年8月11日 下午2:50:03
 */
public class ServiceException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:50:21
    * @param code 编码
    * @param msg 信息
     */
    public ServiceException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "service exception [code=" + code + ", msg=" + msg + "]";
    }
}
