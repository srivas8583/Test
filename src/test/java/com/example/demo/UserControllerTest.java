package com.example.demo;


import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

class UserControllerTest {

    @Test
    void testLogin_Success() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController();
        userController.setUserService(userService);

        User user = new User("username", "password", "regular");

        when(userService.login(user)).thenReturn("mockedToken");

        ResponseEntity<String> response = userController.login(user);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("mockedToken");
    }

    @Test
    void testLogin_Failure() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController();
        userController.setUserService(userService);

        User user = new User("invalidUsername", "invalidPassword", "regular");

        when(userService.login(user)).thenThrow(new IllegalArgumentException("Invalid credentials"));

        ResponseEntity<String> response = userController.login(user);

        assert response.getStatusCode() == HttpStatus.UNAUTHORIZED;
        assert response.getBody().equals("Invalid credentials");
    }
}

