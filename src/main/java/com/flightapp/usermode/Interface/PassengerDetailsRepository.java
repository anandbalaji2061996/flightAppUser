package com.flightapp.usermode.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.usermode.DAO.PassengerDetails;

@Repository
public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long> {
	
	List<PassengerDetails> findByBookingId(String bookingId);
	
	void deleteByBookingId(String bookingId);
	
}
