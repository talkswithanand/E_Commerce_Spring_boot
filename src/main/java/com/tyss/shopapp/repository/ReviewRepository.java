package com.tyss.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.shopapp.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	List<Review> findAll();
	
}
