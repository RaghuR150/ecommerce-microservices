package com.ecommerce.cart.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart addToCart(Long userId, CartItem item) {
    	
    	log.info("Service layer: Adding item to cart for userId={}, item={}", userId, item);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(userId);
                    c.setItems(new ArrayList<>());
                    return c;
                });

        cart.getItems().add(item);
        log.info("Updated cart items: {}", cart.getItems());
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeFromCart(Long userId, Long productId) {
    	
    	log.info("Service layer: Removing item from cart for userId={}, productId={}", userId, productId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems()
            .removeIf(i -> i.getProductId().equals(productId));
        
        log.info("Updated cart items after removal: {}", cart.getItems());
        return cartRepository.save(cart);
    }
}

