package com.tyss.shopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.shopapp.dto.ResponseStructure;
import com.tyss.shopapp.entity.UserInfo;
import com.tyss.shopapp.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Operation(summary = "Api to save user as merchant or customer")
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<UserInfo>> saveUserInfo(@RequestBody UserInfo userInfo) {
		//save user as merchant or customer
		return userInfoService.saveUserInfo(userInfo);
	}
}
