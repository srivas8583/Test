package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.Utility.JwtUtil;
import com.example.demo.model.User;

@Service
public class UserService {

	private static final String ADMIN_USERNAME="Shubham";
	private static final String ADMIN_PASSWORD="123456";
	private static final String REGULAR_USERNAME="Subhu";
	private static final String REGULAR_PASSWORD="234956";
	
	
	public String login(User user) {
		if(user.getUsername().equals(ADMIN_USERNAME)&& user.equals(ADMIN_PASSWORD)) {
			user.setType("admin");
		}
		else if(user.getUsername().equals(REGULAR_USERNAME)&& user.getPassword().equals(REGULAR_PASSWORD)) {
			user.setType("regular");
		}else {
			throw new IllegalArgumentException("Invalid credential");
			
		}
		return JwtUtil.generateToken(user);
	}
}
