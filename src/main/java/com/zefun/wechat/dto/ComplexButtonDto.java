package com.zefun.wechat.dto;

import java.util.List;


/**
 * 一级菜单按钮
* @author 高国藩
* @date 2015年8月6日 下午4:14:00 
*
 */
public class ComplexButtonDto extends ButtonDto {
    /**
     * 二级菜单
     */
	private List<ButtonDto> sub_button;

	public List<ButtonDto> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<ButtonDto> sub_button) {
		this.sub_button = sub_button;
	}

	
}