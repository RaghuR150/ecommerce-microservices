package com.ecommerce.order.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "ORDER_CREATED";

    public void sendOrderCreatedEvent(Long orderId) {
    	log.info("Sending order created event for order ID: {}", orderId);
        kafkaTemplate.send(TOPIC, "Order created with ID: " + orderId);
    }
}

