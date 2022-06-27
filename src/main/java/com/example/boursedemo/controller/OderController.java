package com.example.boursedemo.controller;

import com.example.boursedemo.domain.OrderDomainManager;
import com.example.boursedemo.model.DTO.OrderDTO;
import com.example.boursedemo.model.DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@ComponentScan(basePackages ={"com.example.boursedemo"})
public class OderController {
    @Autowired
    private OrderDomainManager orderDomainManager;

    @RequestMapping(value = "/orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response postOrder(@RequestBody OrderDTO newOrderDTO) {
        if(newOrderDTO.checkNullability()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BadPostPayload");
        }
        var tradeResponse = orderDomainManager.addOrder(newOrderDTO);
        var responseMessage = tradeResponse == null ? "Order Added" : "Oder was Traded";
        return new Response(true, responseMessage, tradeResponse);
    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getOrders() {
        var ordersDTO = orderDomainManager.getOrderBook();
        return new Response(true, "successful", ordersDTO);
    }
}

