package com.example.boursedemo.model;

import com.example.boursedemo.model.DTO.OrderDTO;
import com.example.boursedemo.model.DTO.TradeDTO;

import java.util.Comparator;

public class Order {
    public static enum SIDE {
        buy, sell;
    }
    private Integer id;
    private SIDE orderSide;
    private Double orderPrice;
    private Integer orderQuantity;
    private Integer tradedShareQuantity;
    private boolean fullyTraded;


    public Order(Integer id, SIDE orderSide, Double orderPrice, Integer orderQuantity) {
        this.id = id;
        this.orderSide = orderSide;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.tradedShareQuantity = 0;
        this.fullyTraded = false;
    }
    public boolean isFullyTraded() {
        return fullyTraded;
    }

    public Integer tryTrade(Order other) {
        if (this.orderPrice != other.orderPrice) return 0;
        var quantity = Math.min(this.orderQuantity - this.tradedShareQuantity, other.orderQuantity - other.tradedShareQuantity);

        this.tradedShareQuantity += quantity;
        other.tradedShareQuantity += quantity;

        this.fullyTraded = this.orderQuantity - this.tradedShareQuantity == 0;
        other.fullyTraded = other.orderQuantity - other.tradedShareQuantity == 0;

        return quantity;
    }

    public TradeDTO getTradeDTO() {
        var newDTO = new TradeDTO();
        newDTO.setPrice(this.orderPrice);
        newDTO.setTradeQuantity(this.tradedShareQuantity);
        return newDTO;
    }
    public OrderDTO getOrderDTO() {
        var newDTO = new OrderDTO();
        newDTO.setId(this.id);
        newDTO.setSide(this.orderSide.toString());
        newDTO.setPrice(this.orderPrice);
        newDTO.setQuantity(this.orderQuantity - this.tradedShareQuantity);
        return newDTO;
    }
    public static Comparator<OrderDTO> getDescendingPriceComparator() {
        return (OrderDTO o1, OrderDTO o2) -> (int) Math.round((o2.getPrice() - o1.getPrice())*10);

    }
    public static Comparator<OrderDTO> getAscendingPriceComparator() {
        return (OrderDTO o1, OrderDTO o2) -> (int) Math.round((o1.getPrice() - o2.getPrice())*10);
    }
}
