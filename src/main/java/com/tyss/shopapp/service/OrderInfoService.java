package com.tyss.shopapp.service;

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
import com.tyss.shopapp.entity.UserInfo;
import com.tyss.shopapp.exception.NotAuthorisedException;

@Service
public class OrderInfoService {

	@Autowired
	private OrderInfoDao orderInfoDao;

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<OrderInfo>> createOrderInfo(String username, String password,
			OrderInfo orderInfo, List<Integer> productIds) {

		UserInfo receivedUser = userInfoDao.findUserInfo(username, password);

		if (receivedUser.getRole().equals("customer")) {

			orderInfo.setProducts(productDao.findAllByProductIds(productIds));
			orderInfo = orderInfoDao.saveOrderInfo(orderInfo);

			ResponseStructure<OrderInfo> responseStructure = new ResponseStructure<OrderInfo>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(orderInfo);

			return new ResponseEntity<ResponseStructure<OrderInfo>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new NotAuthorisedException();
		}
	}
}
