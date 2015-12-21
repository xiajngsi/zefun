package com.zefun.web.entity;

/**
 * 
* @author 高国藩
* @date 2015年12月3日 下午8:19:50
 */
public class RechargeSetting {
    
    /***/
    private Integer id;
    /***/
    private Integer type;
    /***/
    private Double amount;
    /***/
    private Integer quantity;
    /***/
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
