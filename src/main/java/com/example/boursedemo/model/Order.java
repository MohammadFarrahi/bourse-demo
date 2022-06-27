package com.example.boursedemo.model;

import com.example.boursedemo.model.DTO.OrderDTO;
import com.example.boursedemo.model.DTO.TradeDTO;

public class Order {
    public static enum SIDE {
        BUY, SELL;
    }
    private Integer id;
    private SIDE orderSide;
    private Double orderPrice;

    public Order(Integer id, SIDE orderSide, Double orderPrice) {
        this.id = id;
        this.orderSide = orderSide;
        this.orderPrice = orderPrice;
    }

    public TradeDTO getTradeDTO() {
        var newDTO = new TradeDTO();
        newDTO.setOrderPrice(this.orderPrice);
        return newDTO;
    }
}
