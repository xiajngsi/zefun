package com.zefun.web.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.json.JSONObject;

/**
 * 流水查询门店信息对象
* @author luhw
*/
public class DayBookDeptDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3163690691402529484L;

	/** 门店标识 */
	private Integer deptId;
	
	/** 门店名称 */
	private String deptName;
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
    	return JSONObject.fromObject(this).toString();
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
}
