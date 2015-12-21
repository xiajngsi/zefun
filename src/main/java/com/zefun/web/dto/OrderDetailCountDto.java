package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 订单明细统计数据传输对象
* @author 张进军
* @date Nov 30, 2015 2:06:07 AM 
*/
public class OrderDetailCountDto {
    /**订单类型(1:项目,2:商品,3:套餐,4、开卡充值升级)*/
    private Integer type;
    
    /**订单类型(1:项目,2:商品,3:套餐,4、开卡充值升级)*/
    private String typeName;
    
    /**应收统计*/
    private BigDecimal receivableAmount;
    
    /**实收统计*/
    private BigDecimal realAmount;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }
    
}
