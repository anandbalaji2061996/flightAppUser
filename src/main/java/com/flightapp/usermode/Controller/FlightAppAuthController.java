package com.flightapp.usermode.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.usermode.DAO.SignupRequest;
import com.flightapp.usermode.DAO.UserLoginCredentials;
import com.flightapp.usermode.Exception.UserAlreadyExistException;
import com.flightapp.usermode.Service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api1/auth/user")
public class FlightAppAuthController {
	private static final Logger logger = LoggerFactory.getLogger(FlightAppAuthController.class);

	@Autowired
	private UserService service;

	@PostMapping(path = "/register", produces = {"application/json"})
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws UserAlreadyExistException {
		logger.info("User Registration");
		return ResponseEntity.ok(service.registerUser(signUpRequest));
	}

	@PostMapping(path = "/login", produces = {"application/json"})
	public ResponseEntity<?> authenticateUser(@RequestBody UserLoginCredentials loginRequest) {
		logger.info("User Login");
		return ResponseEntity.ok(service.loginUser(loginRequest));
	}

}
