package com.zefun.web.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.json.JSONObject;

/**
 * 流水查询查询条件对象
* @author luhw
*/
public class DayBookQueryDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -2072513887328303917L;
	
	/**  */
	public DayBookQueryDto() {
		
	}
	
	/**
	 * 
	 * @param storeId 门店标识
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 */
	public DayBookQueryDto(Integer storeId, String beginTime, String endTime) {
		this.storeId = storeId;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	
	/** 门店标识 */
	private Integer storeId;

	/** 流水号或会员手机号 */
	private String queryCode;
	
	/** 以逗号分隔的部门标识 */
	private String deptIdStr;
	
	/** 开始时间 */
	private String beginTime;
	
	/** 结束时间 */
	private String endTime;
	
	/** 时间排序方向 */
	private Integer direct = 1;
	
	/** pageNo */
	private Integer pageNo = 1;
	
	/** pageSize */
	private Integer pageSize = 10;
	
	/**  */
	private List<Integer> deptIds;
	
	/** 支付类型：1.微信支付 2.支付宝支付  */
	private Integer payType;
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public List<Integer> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<Integer> deptIds) {
		this.deptIds = deptIds;
	}

	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getDirect() {
		return direct;
	}
	
	public void setDirect(Integer direct) {
		this.direct = direct;
	}
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}
	
	public String getDeptIdStr() {
		return deptIdStr;
	}

	public void setDeptIdStr(String deptIdStr) {
		this.deptIdStr = deptIdStr;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
