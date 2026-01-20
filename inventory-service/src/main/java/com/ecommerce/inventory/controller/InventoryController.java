package com.ecommerce.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.service.InventoryService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

	private final InventoryService inventoryService;

	@GetMapping("/{productId}")
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
	public Inventory getInventory(@PathVariable Long productId) {

		log.info("Fetching inventory for productId: {}", productId);

		return inventoryService.getInventoryByProductId(productId);
	}

	@PutMapping("/reduce")
	public Inventory reduceStock(@RequestParam Long productId, @RequestParam Integer quantity) {

		log.info("Reducing stock for productId: {} by quantity: {}", productId, quantity);

		return inventoryService.reduceStock(productId, quantity);
	}

	// Fallback
	public Inventory inventoryFallback(Long productId) {
		log.warn("Inventory service is down. Executing fallback for productId: {}", productId);
		Inventory inventory = new Inventory();
		inventory.setProductId(productId);
		inventory.setQuantity(0);
		log.info("Returning fallback inventory with quantity 0 for productId: {}", productId);
		return inventory;
	}
}
