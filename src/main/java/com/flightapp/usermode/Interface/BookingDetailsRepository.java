package com.flightapp.usermode.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.usermode.DAO.BookingDetails;
import java.lang.String;
import java.util.List;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, String>{

	BookingDetails findByPnr(String pnr);
	
	List<BookingDetails> findByEmaildId(String emaildId);
	
	void deleteByPnr(String pnr);
}
