package com.flightapp.usermode.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsDisplay;
import com.flightapp.usermode.DAO.BookingDetailsFromUI;
import com.flightapp.usermode.DAO.UserDetails;
import com.flightapp.usermode.DAO.UserLoginCredentials;
import com.flightapp.usermode.Service.FlightAppService;
import com.flightapp.usermode.Service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/api/v1.0/flight")
public class FlightAppController {

	@Autowired
	FlightAppService service;
	
	@Autowired
	UserService userService;

	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<BookingDetailsDisplay> getBookingDetails(@PathVariable("pnr") String pnr) {
		return new ResponseEntity<>(service.getBookingDetails(pnr), HttpStatus.OK);
	}

	@PostMapping("/booking/{flightId}")
	public ResponseEntity<BookingDetails> bookAFlight(@PathVariable("flightId") String flightId,
			@RequestBody BookingDetailsFromUI bookingDetailsDisplay) {
		return new ResponseEntity<>(service.bookAFlight(flightId, bookingDetailsDisplay), HttpStatus.CREATED);
	}
	
	@GetMapping("/booking/history/{emailId}")
	public ResponseEntity<List<BookingDetails>> bookedTicketHistory(@PathVariable("emailId") String emailId) {
		return new ResponseEntity<>(service.bookedTicketHistory(emailId), HttpStatus.OK);
	}
	
	@DeleteMapping("/booking/cancel/{pnr}")
	public ResponseEntity<String> cancelBooking(@PathVariable("pnr") String pnr) {
		return new ResponseEntity<>(service.cancelBooking(pnr), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDetails details) {
		return new ResponseEntity<>(userService.registerUser(details), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginCredentials credentials) {
		return new ResponseEntity<>(userService.loginUser(credentials), HttpStatus.OK);
	}
}
