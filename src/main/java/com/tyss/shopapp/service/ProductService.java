package com.tyss.shopapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.shopapp.dao.OrderInfoDao;
import com.tyss.shopapp.dao.ProductDao;
import com.tyss.shopapp.dao.UserInfoDao;
import com.tyss.shopapp.dto.ResponseStructure;
import com.tyss.shopapp.entity.OrderInfo;
import com.tyss.shopapp.entity.Product;
import com.tyss.shopapp.entity.Review;
import com.tyss.shopapp.entity.UserInfo;
import com.tyss.shopapp.exception.NotAuthorisedException;
import com.tyss.shopapp.repository.ReviewRepository;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private OrderInfoDao orderInfoDao;

	@Autowired
	private ReviewRepository reviewRepository;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(String userName, String password, Product product) {
		UserInfo receivedUser = userInfoDao.findUserInfo(userName, password);

		if (receivedUser != null && receivedUser.getRole().equals("merchant")) {

			List<Product> products = receivedUser.getProducts();
			products.add(product);
			productDao.saveProduct(product);
			receivedUser.setProducts(products);
			//updating list of products in the user_info_product table
			userInfoDao.saveUserInfo(receivedUser);

			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(product);

			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new NotAuthorisedException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Review>>> findReview(String userName, String password, int id) {
		UserInfo receivedUser = userInfoDao.findUserInfo(userName, password);

		if (receivedUser != null && receivedUser.getRole().equals("merchant")) {

			Product product = productDao.findProduct(id);
			if (product != null) {

				List<Review> reviews = product.getReviews();

				ResponseStructure<List<Review>> responseStructure = new ResponseStructure<List<Review>>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Success");
				responseStructure.setData(reviews);

				return new ResponseEntity<ResponseStructure<List<Review>>>(responseStructure, HttpStatus.FOUND);

			} else {
				throw new NotAuthorisedException("Product does not exist!!");
			}
		} else {
			throw new NotAuthorisedException();
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(String userName, String password, int pid) {
		UserInfo receivedUser = userInfoDao.findUserInfo(userName, password);

		if (receivedUser != null && receivedUser.getRole().equals("merchant")) {

			Product product = productDao.findProduct(pid);
			
			if (product != null) {
				
				List<OrderInfo> orders = product.getOrders();
				List<Product> userProduct = receivedUser.getProducts();
				for(OrderInfo order: orders) {
					List<Product> products = order.getProducts();
					products.removeAll(Arrays.asList(product));
					order.setProducts(products);
					orderInfoDao.saveOrderInfo(order);
				}
				userProduct.removeAll(Arrays.asList(product));
				receivedUser.setProducts(userProduct);
				userInfoDao.saveUserInfo(receivedUser);
				productDao.deleteProduct(product);
				
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Success");
				responseStructure.setData("Product deleted successfully!!");

				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

			} else {
				throw new NotAuthorisedException("Product does not exist!!!");
			}
		} else {
			throw new NotAuthorisedException();
		}
	}

}
