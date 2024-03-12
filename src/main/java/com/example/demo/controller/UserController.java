package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
	
	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody User user){
		try {
			String token=userService.login(user);
			return ResponseEntity.ok(token);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}
	}
	
}
