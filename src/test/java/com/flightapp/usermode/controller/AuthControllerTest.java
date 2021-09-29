package com.flightapp.usermode.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.flightapp.usermode.Interface.UserRepository;


@WebAppConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class AuthControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired UserRepository details;
	
	@BeforeEach
	public void setup() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}
	
	@Test
	public void userRegistrationAndLogin() throws Exception {
 
		String s1 = "{\"email\":\"testEmailId1@gmail\",\"username\":\"testName\",\"password\":\"testPassword\"}";
		mockMvc.perform(post("/api1/auth/user/register").contentType(MediaType.APPLICATION_JSON).content(s1))
		.andExpect(status().isOk()).andReturn();
		String s2 = "{\"emailId\":\"testEmailId1@gmail\",\"password\":\"testPassword\"}";
		mockMvc.perform(post("/api1/auth/user/login").contentType(MediaType.APPLICATION_JSON).content(s2))
		.andExpect(status().isOk()).andReturn();
		details.deleteById(details.findByEmail("testEmailId1@gmail").get().getId());
	}
}
