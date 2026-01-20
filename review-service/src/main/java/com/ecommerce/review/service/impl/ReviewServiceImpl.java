package com.ecommerce.review.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.review.entity.Review;
import com.ecommerce.review.repository.ReviewRepository;
import com.ecommerce.review.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {
    	log.info("Saving review: {}", review);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
    	log.info("Retrieving reviews for productId: {}", productId);
        return reviewRepository.findByProductId(productId);
    }
}
