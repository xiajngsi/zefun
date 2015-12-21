package com.zefun.wechat.dto;

import java.util.List;

/**
 * 菜单
* @author 高国藩
* @date 2015年8月11日 上午12:10:00
 */
public class MenuDto {
	
	/** 可存放一级菜单 ComplexButtonDto、普通菜单按钮 CommonButtonDto 或者 ViewButtonDto */
	private List<ButtonDto> button;

	public List<ButtonDto> getButton() {
		return button;
	}

	public void setButton(List<ButtonDto> button) {
		this.button = button;
	}

}