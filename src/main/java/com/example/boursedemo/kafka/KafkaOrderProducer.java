package com.example.boursedemo.kafka;

import com.example.boursedemo.model.DTO.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderProducer {
    @Value("${ORDER_TOPIC}")
    private String orderTopic;
    @Autowired
    KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void SendToTopic(OrderDTO orderLog) {
        kafkaTemplate.send(orderTopic, orderLog);
    }
}
