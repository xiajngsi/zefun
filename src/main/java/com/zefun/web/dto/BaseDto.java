package com.zefun.web.dto;

/**
 * 数据传输基础对象，所有返回至前端的数据格式
* @author 张进军
* @date Aug 4, 2015 9:33:10 AM
 */
public class BaseDto {
	/**	返回码  */
	public Integer code;
	/**	返回值  */
	public Object msg;
	/**
	 * 午餐构造
	* @author 高国藩
	* @date 2015年8月11日 下午1:59:18
	 */
	public BaseDto(){
		
	}
	/**
	 * 有参构造
	* @author 高国藩
	* @date 2015年8月11日 下午1:59:44
	* @param code 返回值
	* @param msg 返回信息
	 */
	public BaseDto(Integer code, Object msg) {
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
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BaseDto [code=" + code + ", msg=" + msg + "]";
	}
}
