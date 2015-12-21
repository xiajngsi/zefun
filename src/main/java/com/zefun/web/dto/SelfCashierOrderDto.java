package com.zefun.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 自助收银套餐订单相关
* @author luhw
* @date 2015年10月21日 下午4:20:00
 */
public class SelfCashierOrderDto implements Serializable {
    
	/** serialVersionUID */
    private static final long serialVersionUID = 2313074965116650601L;
    
    /** 订单ID */
    private Integer orderId;
    
    /** 部门标识 */
    private Integer deptId;
    
    /** 订单号 */
    private String orderCode;
    
    /** 消费者性别 */
    private String sex;
    
    /** 会员标识 */
    private Integer memberId;
    
    /** 会员名称 */
    private String memberName;
    
    /** 订单创建时间 */
    private String createTime;
    
    /** 项目总价 */
    private BigDecimal totalPrice;
    
    /** 实际价格 */
    private BigDecimal realPrice;
    
    /** 折扣金额 */
    private BigDecimal discountAmount;
    
    /** 应收价格 */
    private BigDecimal receivableAmount;
    
    /** 签单金额 */
    private String freeAmount;

    /** 挂账金额 */
    private BigDecimal debtAmount;
    
    /** 订单状态 */
    private Integer orderStatus;
    
    /** 会员信息 */
    private MemberBaseDto memberInfo;
    
    /** 操作人信息 */
    private EmployeeBaseDto operateEmployee;
    
    /** 服务人员信息 */
    private EmployeeBaseDto serverEmployee;
    
    /** 订单详情 */
    private List<SelfCashierDetailDto> orderDetails;
    
    /** 组装所有优惠项的可用次数/余额 */
    private Map<String, Object> allOffMap;

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public MemberBaseDto getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberBaseDto memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<SelfCashierDetailDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<SelfCashierDetailDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
    

    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public EmployeeBaseDto getOperateEmployee() {
        return operateEmployee;
    }

    public void setOperateEmployee(EmployeeBaseDto operateEmployee) {
        this.operateEmployee = operateEmployee;
    }

    public EmployeeBaseDto getServerEmployee() {
        return serverEmployee;
    }

    public void setServerEmployee(EmployeeBaseDto serverEmployee) {
        this.serverEmployee = serverEmployee;
    }

    public Map<String, Object> getAllOffMap() {
        return allOffMap;
    }

    public void setAllOffMap(Map<String, Object> allOffMap) {
        this.allOffMap = allOffMap;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
}
