package com.ecommerce.payment.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendPaymentSuccess(Long orderId) {
    	log.info("Sending PAYMENT_SUCCESS event for Order ID: {}", orderId);
        kafkaTemplate.send("PAYMENT_SUCCESS", "Payment success for order " + orderId);
    }

    public void sendPaymentFailed(Long orderId) {
    	log.info("Sending PAYMENT_FAILED event for Order ID: {}", orderId);
        kafkaTemplate.send("PAYMENT_FAILED", "Payment failed for order " + orderId);
    }
}
