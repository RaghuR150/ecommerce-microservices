package com.ecommerce.order.service;

import org.springframework.stereotype.Service;

import com.ecommerce.order.entity.Order;
@Service
public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long id);
}
