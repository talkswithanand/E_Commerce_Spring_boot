package com.tyss.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.shopapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findById(int id);
	
}
