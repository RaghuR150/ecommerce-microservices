package com.ecommerce.product.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.CategoryRepository;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(Product product) {
    	log.info("Saving product: {}", product);
        Category category = categoryRepository.save(product.getCategory());
        product.setCategory(category);
        log.info("Product category saved: {}", category);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
    	log.info("Retrieving product with id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
