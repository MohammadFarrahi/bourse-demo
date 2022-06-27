package com.example.boursedemo.model.DTO;

public class TradeDTO {
    Double price;
    Integer tradeQuantity = 0;

    public Integer getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(Integer tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
