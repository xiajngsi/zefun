package com.zefun.wechat.dto;

/**
 * 子按钮(点击事件按钮)
 */
public class CommonButtonDto extends ButtonDto {
    /**
     * 类型 view代表跳转链接，key为事件识别码
     */
    private String type;
    /**
     * 按钮key值，代表数据库中的菜单ID
     */
    private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	   /**
     * 午餐构造
    * @author 高国藩
    * @date 2015年8月11日 下午2:04:53
     */
    public CommonButtonDto() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 有参构造
    * @author 高国藩
    * @date 2015年8月11日 下午2:05:01
    * @param type 类型
    * @param key  key
     */
    public CommonButtonDto(String type, String key) {
        super();
        this.type = type;
        this.key = key;
    }
	
}