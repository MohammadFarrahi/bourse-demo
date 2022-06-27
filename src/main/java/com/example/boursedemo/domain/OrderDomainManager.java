package com.example.boursedemo.domain;

import com.example.boursedemo.model.DTO.OrderDTO;
import com.example.boursedemo.model.DTO.TradeDTO;
import com.example.boursedemo.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDomainManager {
    private static Integer lastId = 0;
    private ArrayList<Order> sellOrders;
    private ArrayList<Order> buyOrders;

    public TradeDTO addOrder(OrderDTO orderInfo) {
        //TODO : handle if it is non of these.
        var orderSide = Order.SIDE.valueOf(orderInfo.getOrderSide());
        var newOrder = new Order(lastId++, orderSide, orderInfo.getOrderPrice());


        if (Order.SIDE.BUY.equals(orderSide)) {
            buyOrders.add(newOrder);
            return tryTrade(buyOrders, sellOrders, buyOrders.size() - 1);
        } else {
            sellOrders.add(newOrder);
            return tryTrade(sellOrders, buyOrders, sellOrders.size() - 1);
        }
    }
    private TradeDTO tryTrade(ArrayList<Order> fromSideOrders, ArrayList<Order> toSideOrders, int fromSideIndex) {
        var fromOder = fromSideOrders.get(fromSideIndex);
        for(var order : toSideOrders) {
            if(order.isPriceEquals(fromOder)) {
                toSideOrders.remove(order);
                fromSideOrders.remove(fromOder);
                return fromOder.getTradeDTO();
            }
        }
        return null;
    }
    public List<OrderDTO> getOrderBook() {
        ArrayList<OrderDTO> response = new ArrayList<>();
        for(var order : this.buyOrders) {
            response.add(order.getOrderDTO());
        }
        for(var order : this.sellOrders) {
            response.add(order.getOrderDTO());
        }
        return response;
    }
}
