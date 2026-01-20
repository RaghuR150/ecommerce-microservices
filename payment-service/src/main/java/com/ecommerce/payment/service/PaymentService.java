package com.ecommerce.payment.service;

import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    void processPayment(Long orderId);
}

