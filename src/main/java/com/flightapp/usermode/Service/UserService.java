package com.flightapp.usermode.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.User;
import com.flightapp.usermode.Exception.UserNotFoundException;
import com.flightapp.usermode.Interface.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);

	private UserRepository detailsRepository;

	public UserService(UserRepository detailsRepository) {
		this.detailsRepository = detailsRepository;
	}

//	public String registerUser(UserDetails details) throws UserAlreadyExistException {
//		if(detailsRepository.findByEmailId(details.getEmailId()) != null) {
//			logger.warn("User Already found");
//			throw new UserAlreadyExistException("User Already found!");
//		}
//		detailsRepository.save(details);
//		return "User registered successfully!";
//	}
//
//	public String loginUser(UserLoginCredentials credentials) throws UserNotFoundException{
//		UserDetails user = detailsRepository.findByEmailId(credentials.getEmailId());
//
//		if (user != null && user.getPassword().equals(credentials.getPassword())) {
//			return "Success";
//		}
//		else {
//			logger.warn("Invalid user credentials!");
//			throw new UserNotFoundException("Invalid user credentials!");
//		}
//	}
	
	public String getUserDetails(String emailId) throws UserNotFoundException{
		User user = detailsRepository.findByEmail(emailId).orElse(null);

		if (user != null) {
			return user.getUsername();
		}
		else {
			logger.warn("User credentials not found!");
			throw new UserNotFoundException("User credentials not found!");
		}
	}
}

