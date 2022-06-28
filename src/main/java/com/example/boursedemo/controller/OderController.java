package com.example.boursedemo.controller;

import com.example.boursedemo.domain.OrderDomainManager;
import com.example.boursedemo.exception.InvalidPayloadException;
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
        try {
            var tradeResponse = orderDomainManager.addOrder(newOrderDTO);
            return new Response(true, "successful", tradeResponse);
        }
        catch(InvalidPayloadException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BadPostPayload");
        }

    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getOrders(@RequestParam(defaultValue = "all") String type) {
//        var ordersDTO = orderDomainManager.getOrderBook(type);
        var ordersDTO = orderDomainManager.getOrderBook();
        return new Response(true, "successful", ordersDTO);
    }
}

