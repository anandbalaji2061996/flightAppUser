package com.flightapp.usermode.DAO;

import javax.validation.constraints.NotEmpty;

public class UserLoginCredentials {

	@NotEmpty(message = "EmailId should not be empty")
	private String emailId;
	@NotEmpty(message = "Password should not be empty")
	private String password;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
