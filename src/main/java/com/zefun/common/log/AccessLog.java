package com.zefun.common.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

/**
 * 
* @author 洪秋霞
* @date 2015年8月11日 下午2:35:04
 */
public class AccessLog {
    /**
     * 
     */
    public static final int LOG_LEVEL = 20050;

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:35:12
     */
    private static class CustomerLogLevel extends Level {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /**
         * 
        * @author 洪秋霞
        * @date 2015年8月11日 下午2:35:25
        * @param level 等级
        * @param levelStr 等级str 
        * @param syslogEquivalent s
         */
        CustomerLogLevel(int level, String levelStr, int syslogEquivalent) {
            super(level, levelStr, syslogEquivalent);
        }
    }

    /**
     * 
     */
    private static final Level CUSTOMERLEVEL = new CustomerLogLevel(LOG_LEVEL, "ACCESS ", SyslogAppender.LOG_LOCAL0);

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:36:21
    * @param logger logger
    * @param info info
     */
    public static void log(Logger logger, Object info) {
        logger.log(CUSTOMERLEVEL, info);
    }
}
