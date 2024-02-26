package com.tyss.shopapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.shopapp.dao.ProductDao;
import com.tyss.shopapp.dao.ReviewDao;
import com.tyss.shopapp.dao.UserInfoDao;
import com.tyss.shopapp.dto.ResponseStructure;
import com.tyss.shopapp.entity.Product;
import com.tyss.shopapp.entity.Review;
import com.tyss.shopapp.entity.UserInfo;
import com.tyss.shopapp.exception.NotAuthorisedException;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Review>> saveReview(String username, String password, int pid,
			Review review) {

		UserInfo receivedUser = userInfoDao.findUserInfo(username, password);

		if (receivedUser != null && receivedUser.getRole().equals("customer")) {

			Product product = productDao.findProduct(pid);

			if (product != null) {
				
				List<Review> reviews = product.getReviews(); 
				reviews.add(review);
				product = productDao.saveProduct(product);
				reviews = product.getReviews();
				
				ResponseStructure<Review> responseStructure = new ResponseStructure<Review>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Success");
				responseStructure.setData(reviews.get(reviews.size()-1));

				return new ResponseEntity<ResponseStructure<Review>>(responseStructure, HttpStatus.CREATED);

			} else {
				throw new NotAuthorisedException("Product does not exist!!");
			}
		} else {
			throw new NotAuthorisedException();
		}

	}

}
