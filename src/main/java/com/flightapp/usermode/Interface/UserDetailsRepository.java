package com.flightapp.usermode.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.usermode.DAO.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

	UserDetails findByEmailId(String emailid);
}
