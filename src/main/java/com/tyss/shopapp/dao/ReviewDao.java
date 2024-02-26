package com.tyss.shopapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.shopapp.entity.Review;
import com.tyss.shopapp.repository.ReviewRepository;

@Repository
public class ReviewDao {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}
	
}
