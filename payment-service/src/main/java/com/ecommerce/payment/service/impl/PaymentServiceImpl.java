package com.ecommerce.payment.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.producer.PaymentEventProducer;
import com.ecommerce.payment.repository.PaymentRepository;
import com.ecommerce.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final PaymentEventProducer paymentEventProducer;

	@Override
	public void processPayment(Long orderId) {
		log.info("Processing payment for Order ID: {}", orderId);
		// Simulate payment success
		Payment payment = new Payment();
		payment.setOrderId(orderId);
		payment.setAmount(1000.0);
		payment.setStatus("SUCCESS");
		log.info("Saving payment record for Order ID: {}", orderId);
		paymentRepository.save(payment);
		log.info("Payment processed successfully for Order ID: {}", orderId);
		paymentEventProducer.sendPaymentSuccess(orderId);
	}
}
