package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 订单明细支付信息
* @author 张进军
* @date Nov 11, 2015 8:33:17 PM 
*/
public class OrderDetaiSubmitDto {
    /** 明细标识 */
    private Integer detailId;

    /** 优惠标识 */
    private Integer offId;
    
    /** 优惠类型(1:套餐，2:礼金，3:优惠券) */
    private Integer offType;
    
    /** 优惠金额 */
    private BigDecimal offAmount;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getOffId() {
        return offId;
    }

    public void setOffId(Integer offId) {
        this.offId = offId;
    }

    public Integer getOffType() {
        return offType;
    }

    public void setOffType(Integer offType) {
        this.offType = offType;
    }

    public BigDecimal getOffAmount() {
        return offAmount;
    }

    public void setOffAmount(BigDecimal offAmount) {
        this.offAmount = offAmount;
    }
    
}
