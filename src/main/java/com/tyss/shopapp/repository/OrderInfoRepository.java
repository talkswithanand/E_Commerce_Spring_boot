package com.tyss.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.shopapp.entity.OrderInfo;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer>{

}
