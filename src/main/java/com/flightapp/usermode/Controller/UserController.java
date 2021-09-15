package com.flightapp.usermode.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.usermode.DAO.UserDetails;
import com.flightapp.usermode.Interface.UserDetailsRepository;

@RestController
public class UserController {

	@Autowired
	private UserDetailsRepository detailsRepository;

	@PostMapping("/register")
	public String registerUser(@RequestBody UserDetails details) {
		detailsRepository.save(details);
		return "User registered successfully!";
	}

	@GetMapping("/login/emailId/{emailId}/password/{password}")
	public String loginUser(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {
		UserDetails user = detailsRepository.findByEmailId(emailId);

		if (user != null && user.getPassword().equals(password)) {
			return "Success";
		}

		else
			return "Invalid Login";
	}
}
