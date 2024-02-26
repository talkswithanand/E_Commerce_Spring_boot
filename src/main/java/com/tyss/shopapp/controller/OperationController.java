package com.tyss.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.tyss.shopapp.dao.ReviewDao;
import com.tyss.shopapp.dto.ResponseStructure;
import com.tyss.shopapp.entity.OrderInfo;
import com.tyss.shopapp.entity.Product;
import com.tyss.shopapp.entity.Review;
import com.tyss.shopapp.repository.ReviewRepository;
import com.tyss.shopapp.service.OrderInfoService;
import com.tyss.shopapp.service.ProductService;
import com.tyss.shopapp.service.ReviewService;
import com.tyss.shopapp.util.OrderBooking;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/{username}/{password}")
public class OperationController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Operation(summary = "Api to save Products by merchant")
	@PostMapping("/saveProduct")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@PathVariable String username, @PathVariable String password, @RequestBody Product product) {
		//save product from product service
		return productService.saveProduct(username, password, product);
	}
	
	@Operation(summary = "Api to add orders by customer")
	@PostMapping("/addOrder")
	public ResponseEntity<ResponseStructure<OrderInfo>> saveOrder(@PathVariable String username, @PathVariable String password, @RequestBody OrderBooking orderBooking){
		return orderInfoService.createOrderInfo(username, password, orderBooking.getOrderInfo(), orderBooking.getProductIds());
	}
	
	@Operation(summary = "Api to save Reviews by customer")
	@PostMapping("/{pid}/saveReview")
	public ResponseEntity<ResponseStructure<Review>> saveReview(@PathVariable String username, @PathVariable String password, @PathVariable int pid,  @RequestBody Review review){
		return reviewService.saveReview(username, password, pid, review);
	}
	
	@Operation(summary = "Api to get review of each product by merchant")
	@GetMapping("/{pid}/getReviews")
	public ResponseEntity<ResponseStructure<List<Review>>> getReviews(@PathVariable String username, @PathVariable String password, @PathVariable int pid){
		return productService.findReview(username, password, pid);
	}
	
	@Operation(summary = "Api to delete products by merchant")
	@DeleteMapping("/{pid}/deleteProduct")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable String username, @PathVariable String password, @PathVariable int pid){
		return productService.deleteProduct(username, password, pid); 
	}
	
	
	
}
