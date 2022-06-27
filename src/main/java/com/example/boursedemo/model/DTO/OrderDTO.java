package com.example.boursedemo.model.DTO;

public class OrderDTO {
    private Integer id;
    private String orderSide;
    private Double orderPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSide() {
        return orderSide;
    }

    public void setOrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
    public boolean checkNullability() {
        if(this.orderPrice == null || this.orderSide == null)
            return true;
        return false;
    }
}
