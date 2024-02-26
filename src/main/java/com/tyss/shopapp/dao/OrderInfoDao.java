package com.tyss.shopapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.shopapp.entity.OrderInfo;
import com.tyss.shopapp.repository.OrderInfoRepository;

@Repository
public class OrderInfoDao {
	
	@Autowired
	private OrderInfoRepository orderInfoRepository;
	
	public OrderInfo saveOrderInfo(OrderInfo orderInfo) {
		return orderInfoRepository.save(orderInfo);
	}
}
