package com.example.boursedemo.domain;

import com.example.boursedemo.exception.InvalidPayloadException;
import com.example.boursedemo.model.DTO.OrderDTO;
import com.example.boursedemo.model.DTO.TradeDTO;
import com.example.boursedemo.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDomainManager {
    private static Integer lastId = 0;
    private ArrayList<Order> sellOrders = new ArrayList<>();
    private ArrayList<Order> buyOrders = new ArrayList<>();

    public TradeDTO addOrder(OrderDTO orderInfo) throws InvalidPayloadException {
        if(!validateOrderDTO(orderInfo))
            throw new InvalidPayloadException("InvalidPriceJSON");
        var orderSide = Order.SIDE.valueOf(orderInfo.getSide());
        var newOrder = new Order(lastId++, orderSide, orderInfo.getPrice(), orderInfo.getQuantity());

        if (Order.SIDE.buy.equals(orderSide)) {
            buyOrders.add(newOrder);
            return tryTrade(buyOrders, sellOrders, buyOrders.size() - 1);
        } else {
            sellOrders.add(newOrder);
            return tryTrade(sellOrders, buyOrders, sellOrders.size() - 1);
        }
    }
    private TradeDTO tryTrade(ArrayList<Order> fromSideOrders, ArrayList<Order> toSideOrders, int fromSideIndex) {
        var fromOder = fromSideOrders.get(fromSideIndex);

        for(int toSideIndex = 0; toSideIndex < toSideOrders.size(); toSideIndex++) {
            toSideOrders.get(toSideIndex).tryTrade(fromOder);
            if(toSideOrders.get(toSideIndex).isFullyTraded()) {
                toSideOrders.remove(toSideIndex); toSideIndex--;
            }
            if(fromOder.isFullyTraded()) {
                fromSideOrders.remove(fromOder); break;
            }
        }
        return fromOder.getTradeDTO();
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
    private boolean validateOrderDTO(OrderDTO orderDTO) {
        boolean isValid = true;
        isValid &= !orderDTO.checkNullability();
        isValid &= orderDTO.getSide().equals(Order.SIDE.buy.toString()) | orderDTO.getSide().equals(Order.SIDE.sell.toString());
        isValid &= orderDTO.getPrice() > 0;
        isValid &= orderDTO.getQuantity() > 0;
        return isValid;
    }
}
