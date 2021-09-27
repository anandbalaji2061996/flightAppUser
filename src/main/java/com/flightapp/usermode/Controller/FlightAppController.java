package com.flightapp.usermode.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.flightapp.usermode.Exception.BadRequestException;
import com.flightapp.usermode.Exception.TicketNotFoundException;
import com.flightapp.usermode.Exception.UserAlreadyExistException;
import com.flightapp.usermode.Exception.UserNotFoundException;
import com.flightapp.usermode.Service.FlightAppService;
import com.flightapp.usermode.Service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api1/v1.0/user/flight")
public class FlightAppController {
	
	private static final Logger logger = LogManager.getLogger(FlightAppController.class);

	@Autowired
	FlightAppService service;
	
	@Autowired
	UserService userService;

	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<List<BookingDetailsDisplay>> getBookingDetails(@PathVariable("pnr") String pnr) throws TicketNotFoundException {
		logger.info("Get booking Details " + pnr);
		List<BookingDetailsDisplay> list = new ArrayList<>();
		list.add(service.getBookingDetails(pnr));
		return new ResponseEntity<List<BookingDetailsDisplay>>(list, HttpStatus.OK);
	}

	@PostMapping("/booking/{flightId}")
	public ResponseEntity<BookingDetails> bookAFlight(@PathVariable("flightId") String flightId,
			@RequestBody BookingDetailsFromUI bookingDetailsDisplay) throws BadRequestException {
		logger.info("Book the tickets");
		return new ResponseEntity<>(service.bookAFlight(flightId, bookingDetailsDisplay), HttpStatus.CREATED);
	}
	
	@GetMapping("/booking/history/{emailId}")
	public ResponseEntity<List<BookingDetails>> bookedTicketHistory(@PathVariable("emailId") String emailId) {
		logger.info("Fetching ticket history for " + emailId);
		return new ResponseEntity<>(service.bookedTicketHistory(emailId), HttpStatus.OK);
	}
	
	@DeleteMapping("/booking/cancel/{pnr}")
	public ResponseEntity<String> cancelBooking(@PathVariable("pnr") String pnr) throws TicketNotFoundException {
		logger.warn("Cancelling ticket of " + pnr);
		return new ResponseEntity<>(service.cancelBooking(pnr), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDetails details) throws UserAlreadyExistException {
		logger.info("Register user details");
		return new ResponseEntity<>(userService.registerUser(details), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginCredentials credentials) throws UserNotFoundException {
		logger.info("Login User!");
		return new ResponseEntity<>(userService.loginUser(credentials), HttpStatus.OK);
	}
	
	@GetMapping("/userDetails/{emailId}")
	public ResponseEntity<String> getUserDetails(@PathVariable("emailId") String emailId) throws UserNotFoundException {
		logger.info("Get User Details!");
		return new ResponseEntity<>(userService.getUserDetails(emailId), HttpStatus.OK);
	}
}
