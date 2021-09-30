package com.flightapp.usermode.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.ERole;
import com.flightapp.usermode.DAO.JwtResponse;
import com.flightapp.usermode.DAO.Role;
import com.flightapp.usermode.DAO.SignupRequest;
import com.flightapp.usermode.DAO.User;
import com.flightapp.usermode.DAO.UserLoginCredentials;
import com.flightapp.usermode.Exception.UserAlreadyExistException;
import com.flightapp.usermode.Exception.UserNotFoundException;
import com.flightapp.usermode.Interface.RoleRepository;
import com.flightapp.usermode.Interface.UserRepository;
import com.flightapp.usermode.Security.jwt.JwtUtils;
import com.flightapp.usermode.Security.service.UserDetailsImpl;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
		
	private AuthenticationManager authenticationManager;

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder encoder;

	private JwtUtils jwtUtils;

	public UserService(UserRepository userRepository, JwtUtils jwtUtils, AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}

	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) throws UserAlreadyExistException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			logger.error("Username is already taken!");
			throw new UserAlreadyExistException("Username is already taken");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			logger.error("Email is already taken!");
			throw new UserAlreadyExistException("Email is already taken");

		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			logger.error("Checking for role!");
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		logger.info("User Registered!");
		return ResponseEntity.ok("User registered successfully!");
	}

	public JwtResponse loginUser(UserLoginCredentials loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmailId(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		logger.info("Token {}",jwt);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		logger.info("User login success!");

		return new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles);
	}
	
	public String getUserDetails(String emailId) throws UserNotFoundException{
		User user = userRepository.findByEmail(emailId).orElse(null);

		if (user != null) {
			return user.getUsername();
		}
		else {
			logger.warn("User credentials not found!");
			throw new UserNotFoundException("User credentials not found!");
		}
	}
}

