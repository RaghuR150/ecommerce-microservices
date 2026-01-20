package com.ecommerce.inventory.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;
import com.ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	@Override
	public Inventory getInventoryByProductId(Long productId) {
		log.info("Retrieving inventory for productId: {}", productId);
		return inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));
	}

	@Override
	public Inventory reduceStock(Long productId, Integer quantity) {
		log.info("Reducing stock for productId: {} by quantity: {}", productId, quantity);
		Inventory inventory = getInventoryByProductId(productId);

		if (inventory.getQuantity() < quantity) {
			throw new RuntimeException("Insufficient stock");
		}

		inventory.setQuantity(inventory.getQuantity() - quantity);
		log.info("New stock for productId: {} is {}", productId, inventory.getQuantity());
		return inventoryRepository.save(inventory);
	}
}
