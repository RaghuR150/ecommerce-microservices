package com.ecommerce.inventory.service;

import org.springframework.stereotype.Service;

import com.ecommerce.inventory.entity.Inventory;
@Service
public interface InventoryService {

    Inventory getInventoryByProductId(Long productId);

    Inventory reduceStock(Long productId, Integer quantity);
}
