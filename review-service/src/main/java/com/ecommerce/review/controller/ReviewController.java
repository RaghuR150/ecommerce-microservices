package com.ecommerce.review.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.review.entity.Review;
import com.ecommerce.review.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	public Review addReview(@RequestBody Review review) {
		log.info("Adding review: {}", review);
		return reviewService.addReview(review);
	}

	@GetMapping("/product/{productId}")
	public List<Review> getReviews(@PathVariable Long productId) {
		log.info("Fetching reviews for productId: {}", productId);
		return reviewService.getReviewsByProduct(productId);
	}
}
