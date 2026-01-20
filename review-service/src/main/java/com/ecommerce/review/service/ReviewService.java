package com.ecommerce.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.review.entity.Review;
@Service
public interface ReviewService {

    Review addReview(Review review);

    List<Review> getReviewsByProduct(Long productId);
}
