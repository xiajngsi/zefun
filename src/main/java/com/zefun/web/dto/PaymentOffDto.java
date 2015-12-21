package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 支付优惠传输对象
* @author 张进军
* @date Nov 10, 2015 3:50:57 PM 
*/
public class PaymentOffDto {
    /** 优惠标识 */
    private String id;
    
    /** 优惠类型(1:项目套餐，2:商品套餐，3:优惠券，4:礼金) */
    private int type;
    
    /** 优惠名称 */
    private String name;
    
    /** 可使用数量 */
    private int count;
    
    /** 剩余使用数量,套餐/优惠券为数量，礼金为总余额 */
    private BigDecimal balance;
    
    /** 优惠金额 */
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object other){       //重写equals方法，后面最好重写hashCode方法
        if (this == other) {
            return true;
        }
        
        if (other == null) {
            return false;
        }
        
        if (!(other instanceof PaymentOffDto)) {
            return false;
        }
        
        final PaymentOffDto paymentOff = (PaymentOffDto) other;
        if (getId().equals(paymentOff.getId())) {
            return true;
        }
        
        return false;
    }
}
