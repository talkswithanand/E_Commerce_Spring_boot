package com.tyss.shopapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.shopapp.entity.UserInfo;
import com.tyss.shopapp.repository.UserRepository;

@Repository
public class UserInfoDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserInfo saveUserInfo(UserInfo user) {
		return userRepository.save(user);
	}
	
	public UserInfo findUserInfo(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}
	
}
