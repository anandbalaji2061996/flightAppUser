package com.flightapp.usermode.DAO;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
 
public class SignupRequest {

	@NotEmpty(message = "Username should not be empty")
    private String username;

	@NotEmpty(message = "Email should not be empty")
    private String email;
    
    private Set<String> role;
    
    @NotEmpty(message = "Password should not be empty")
    private String password;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
