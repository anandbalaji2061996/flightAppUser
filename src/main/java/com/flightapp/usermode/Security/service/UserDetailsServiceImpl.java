package com.flightapp.usermode.Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightapp.usermode.DAO.User;
import com.flightapp.usermode.Interface.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with Email Id: " + emailId));

		return UserDetailsImpl.build(user);
	}

}
