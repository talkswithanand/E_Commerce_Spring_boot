package com.tyss.shopapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.shopapp.dao.UserInfoDao;
import com.tyss.shopapp.dto.ResponseStructure;
import com.tyss.shopapp.entity.UserInfo;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	public ResponseEntity<ResponseStructure<UserInfo>> saveUserInfo(UserInfo userInfo){
		UserInfo receivedUser = userInfoDao.saveUserInfo(userInfo);
		
		ResponseStructure<UserInfo> responseStructure = new ResponseStructure<UserInfo>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(receivedUser);
		
		return new ResponseEntity<ResponseStructure<UserInfo>>(responseStructure, HttpStatus.CREATED);
		
	}
}
