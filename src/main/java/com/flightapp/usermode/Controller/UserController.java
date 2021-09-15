package com.flightapp.usermode.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.usermode.DAO.LoginCredentials;
import com.flightapp.usermode.DAO.UserDetails;
import com.flightapp.usermode.Service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public String registerUser(@RequestBody UserDetails details) {
		return service.registerUser(details);
	}

	@PostMapping("/user/login")
	public String loginUser(@RequestBody LoginCredentials credentials) {
		return service.loginUser(credentials);
	}
}
