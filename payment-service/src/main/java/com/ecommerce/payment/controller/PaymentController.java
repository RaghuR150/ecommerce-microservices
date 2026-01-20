package com.ecommerce.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping("/{orderId}")
	public ResponseEntity<String> pay(@PathVariable Long orderId) {
		log.info("Processing payment for Order ID: {}", orderId);
		paymentService.processPayment(orderId);
		log.info("Payment processed for Order ID: {}", orderId);
		return ResponseEntity.ok("Payment processed");
	}
}
