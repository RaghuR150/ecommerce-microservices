package com.ecommerce.payment.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ecommerce.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {

	private final PaymentService paymentService;

	@KafkaListener(topics = "ORDER_CREATED", groupId = "payment-group")
	public void consumeOrderCreated(String message) {

		log.info("Received ORDER_CREATED event: {}", message);

		// Example message: "Order created with ID: 1"
		Long orderId = Long.parseLong(message.replaceAll("\\D+", ""));
		log.info("Processing payment for Order ID: {}", orderId);
		paymentService.processPayment(orderId);
	}
}
