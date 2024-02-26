package com.tyss.shopapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.shopapp.entity.Product;
import com.tyss.shopapp.repository.ProductRepository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product findProduct(int id) {
		return productRepository.findById(id);
	}
	
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}
	
	public List<Product> findAllByProductIds(List<Integer> products){
		return productRepository.findAllById(products);
	}
	
}
