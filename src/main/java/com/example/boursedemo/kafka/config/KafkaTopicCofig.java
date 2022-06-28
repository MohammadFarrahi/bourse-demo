package com.example.boursedemo.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicCofig {
    @Value("${ORDER_TOPIC}")
    private String orderTopic;
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(orderTopic).build();
    }
}
