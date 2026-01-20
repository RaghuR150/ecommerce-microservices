package com.ecommerce.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "ORDER_CREATED", groupId = "notification-group")
    public void consumeOrderCreated(String message) {
        log.info("📧 EMAIL: Order notification -> {}" , message);
    }

    @KafkaListener(topics = "PAYMENT_SUCCESS", groupId = "notification-group")
    public void consumePaymentSuccess(String message) {
        log.info("📲 SMS: Payment success -> {}" , message);
    }
}
