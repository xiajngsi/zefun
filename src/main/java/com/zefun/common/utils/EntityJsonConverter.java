package com.zefun.common.utils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 实体与json字符串转换工具类
* @author 张进军
* @date Aug 19, 2015 5:33:27 PM
 */
public class EntityJsonConverter {

    /** 映射对象 */
	public static ObjectMapper objMapper = new ObjectMapper();
	
	static {
		objMapper.getDeserializationConfig().without(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	/**
	 * json字符串转为实体对象
	* @author 张进军
	* @date Aug 19, 2015 5:36:12 PM
	* @param json  json字符串
	* @param clz   目标对象类
	* @param <E>   泛型
	* @return      目标类对象
	 */
	public static <E> E json2Entity(String json, Class<E> clz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return objMapper.readValue(json, clz);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 实体对象转换为json字符串
	* @author 张进军
	* @date Aug 19, 2015 5:36:58 PM
	* @param e     实体对象
	* @param <E>   泛型
	* @return      json字符串
	 */
	public static <E> String entity2Json(E e) {
		if (e == null) {
			return null;
		}
		try {
			return objMapper.writeValueAsString(e);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}