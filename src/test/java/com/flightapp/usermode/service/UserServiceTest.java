package com.flightapp.usermode.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.flightapp.usermode.DAO.User;
import com.flightapp.usermode.Exception.UserNotFoundException;
import com.flightapp.usermode.Interface.RoleRepository;
import com.flightapp.usermode.Interface.UserRepository;
import com.flightapp.usermode.Security.jwt.JwtUtils;
import com.flightapp.usermode.Service.UserService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTest {

	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder encoder;

	private JwtUtils jwtUtils;

	private UserService service;

	@BeforeEach
	public void setup() {
		service = new UserService(userRepository, jwtUtils, authenticationManager, roleRepository, encoder);
	}

	@AfterEach
	public void setup2() {
		userRepository.deleteAll();
	}

//	@Test
//	public void registerUser() throws UserAlreadyExistException {
//
//		UserDetails d = new UserDetails();
//		d.setEmailId("testEmailId");
//		d.setName("testName");
//		d.setPassword("testPassword");
//
//		String response = service.registerUser(d);
//
//		assertEquals(response, "User registered successfully!");
//
//		Throwable thrown = catchThrowable(() -> service.registerUser(d));
//
//		assertThat(thrown).isInstanceOf(UserAlreadyExistException.class);
//	}
//
//	@Test
//	public void loginUser() throws UserNotFoundException {
//		UserDetails d = new UserDetails();
//		d.setEmailId("testEmailId");
//		d.setName("testName");
//		d.setPassword("testPassword");
//
//		detailsRepository.save(d);
//
//		UserLoginCredentials loginCredentials = new UserLoginCredentials();
//		loginCredentials.setEmailId("testEmailId");
//		loginCredentials.setPassword("testPassword");
//
//		assertEquals(service.loginUser(loginCredentials), "Success");
//
//		loginCredentials.setEmailId("wrongEmailId");
//
//		Throwable thrown = catchThrowable(() -> service.loginUser(loginCredentials));
//
//		assertThat(thrown).isInstanceOf(UserNotFoundException.class);
//	}
	
	@Test
	public void getUserDetails() throws UserNotFoundException {
		User d = new User();
		d.setId(1L);
		d.setEmail("testEmailId@gmail");
		d.setUsername("testName");
		d.setPassword("testPassword");
		
		userRepository.save(d);

		assertEquals(service.getUserDetails("testEmailId@gmail"), "testName");

		Throwable thrown = catchThrowable(() -> service.getUserDetails("testEmailId1"));

		assertThat(thrown).isInstanceOf(UserNotFoundException.class);
	}
}
