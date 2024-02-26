package com.tyss.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.shopapp.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer>{
	
	UserInfo findByUserNameAndPassword(String userName, String password);
}
