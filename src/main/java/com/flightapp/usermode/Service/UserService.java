package com.flightapp.usermode.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.UserLoginCredentials;
import com.flightapp.usermode.Exception.UserAlreadyExistException;
import com.flightapp.usermode.Exception.UserNotFoundException;
import com.flightapp.usermode.DAO.UserDetails;
import com.flightapp.usermode.Interface.UserDetailsRepository;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);

	private UserDetailsRepository detailsRepository;

	public UserService(UserDetailsRepository detailsRepository) {
		this.detailsRepository = detailsRepository;
	}

	public String registerUser(UserDetails details) throws UserAlreadyExistException {
		if(detailsRepository.findByEmailId(details.getEmailId()) != null) {
			logger.warn("User Already found");
			throw new UserAlreadyExistException("User Already found!");
		}
		detailsRepository.save(details);
		return "User registered successfully!";
	}

	public String loginUser(UserLoginCredentials credentials) throws UserNotFoundException{
		UserDetails user = detailsRepository.findByEmailId(credentials.getEmailId());

		if (user != null && user.getPassword().equals(credentials.getPassword())) {
			return "Success";
		}
		else {
			logger.warn("Invalid user credentials!");
			throw new UserNotFoundException("Invalid user credentials!");
		}
	}
}
