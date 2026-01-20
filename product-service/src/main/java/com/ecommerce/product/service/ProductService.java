package com.ecommerce.product.service;

import org.springframework.stereotype.Service;

import com.ecommerce.product.entity.Product;

@Service
public interface ProductService {

    Product createProduct(Product product);

    Product getProductById(Long id);
}
