package com.ecommerce.cart.service;

import org.springframework.stereotype.Service;

import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
@Service
public interface CartService {

    Cart addToCart(Long userId, CartItem item);

    Cart removeFromCart(Long userId, Long productId);
}

