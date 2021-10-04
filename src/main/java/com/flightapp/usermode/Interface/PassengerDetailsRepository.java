package com.flightapp.usermode.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.usermode.DAO.PassengerDetails;

@Repository
public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long> {
	
}
