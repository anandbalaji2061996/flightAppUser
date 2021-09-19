package com.flightapp.usermode.Service;

import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.UserLoginCredentials;
import com.flightapp.usermode.DAO.UserDetails;
import com.flightapp.usermode.Interface.UserDetailsRepository;

@Service
public class UserService {

	private UserDetailsRepository detailsRepository;

	public UserService(UserDetailsRepository detailsRepository) {
		this.detailsRepository = detailsRepository;
	}

	public String registerUser(UserDetails details) {
		detailsRepository.save(details);
		return "User registered successfully!";
	}

	public String loginUser(UserLoginCredentials credentials) {
		UserDetails user = detailsRepository.findByEmailId(credentials.getEmailId());

		if (user != null && user.getPassword().equals(credentials.getPassword())) {
			return "Success";
		}

		else
			return "Invalid Login";
	}
}
