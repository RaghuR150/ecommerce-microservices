package com.ecommerce.order.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.order.client.InventoryClient;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.producer.OrderEventProducer;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;
	private final OrderEventProducer orderEventProducer;

	@Override
	@Transactional
	public Order createOrder(Order order) {

		log.info("Creating order: {}", order);
		// Reduce stock using Feign
		for (OrderItem item : order.getOrderItems()) {
			inventoryClient.reduceStock(item.getProductId(), item.getQuantity());
		}

		order.setStatus("CREATED");

		Order savedOrder = orderRepository.save(order);

		// Publish Kafka event
		orderEventProducer.sendOrderCreatedEvent(savedOrder.getId());

		log.info("Order created with ID: {}", savedOrder.getId());
		return savedOrder;
	}

	@Override
	public Order getOrderById(Long id) {
		log.info("Fetching order with id: {}", id);
		return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}
}
