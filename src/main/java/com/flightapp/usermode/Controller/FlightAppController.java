package com.flightapp.usermode.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsDisplay;
import com.flightapp.usermode.Service.FlightAppService;

@RestController
@RequestMapping("/user/api/v1.0/flight/")
public class FlightAppController {

	@Autowired
	FlightAppService service;

	@GetMapping("/ticket/{pnr}")
	public BookingDetailsDisplay getBookingDetails(@PathVariable("pnr") String pnr) {
		return service.getBookingDetails(pnr);
	}

	@PostMapping("/booking/{flightId}")
	public BookingDetails bookAFlight(@PathVariable("flightId") String flightId,
			@RequestBody BookingDetailsDisplay bookingDetailsDisplay) {
		return service.bookAFlight(flightId, bookingDetailsDisplay);
	}
	
	@GetMapping("/booking/history/{emailId}")
	public List<BookingDetails> bookedTicketHistory(@PathVariable("emailId") String emailId) {
		return service.bookedTicketHistory(emailId);
	}
	
	@DeleteMapping("/booking/cancel/{pnr}")
	public String cancelBooking(@PathVariable("pnr") String pnr) {
		return service.cancelBooking(pnr);
	}
}
