package com.zefun.common.exception;

/**
 * 
* @author 洪秋霞
* @date 2015年8月11日 下午2:49:25
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:49:32
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:49:34
    * @param message msg
    * @param cause c
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:49:37
    * @param message msg
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:49:39
    * @param cause c
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
