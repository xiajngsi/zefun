package com.zefun.common.log;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
* @author 洪秋霞
* @date 2015年8月11日 下午2:35:01
 */
public class AccessLogFilter extends Filter{

	@Override
	public int decide(LoggingEvent event) {
		int inputLevel = event.getLevel().toInt();
		if (inputLevel == AccessLog.LOG_LEVEL) {
			return 0;
		}
		return -1;
	}

}
