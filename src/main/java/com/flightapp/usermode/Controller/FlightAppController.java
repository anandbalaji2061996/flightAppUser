package com.flightapp.usermode.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsFromUI;
import com.flightapp.usermode.Exception.BadRequestException;
import com.flightapp.usermode.Exception.TicketNotFoundException;
import com.flightapp.usermode.Exception.UserNotFoundException;
import com.flightapp.usermode.Service.FlightAppService;
import com.flightapp.usermode.Service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api1/v1.0/user/flight")
public class FlightAppController {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightAppController.class);

	@Autowired
	FlightAppService service;
	
	@Autowired
	UserService userService;

	@GetMapping(path = "/ticket/{pnr}", produces = {"application/json"})
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<BookingDetails>> getBookingDetails(@Valid @PathVariable("pnr") String pnr) throws TicketNotFoundException {
		logger.info("Get booking Details " + pnr);
		List<BookingDetails> list = new ArrayList<>();
		list.add(service.getBookingDetails(pnr));
		return new ResponseEntity<List<BookingDetails>>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/booking/{flightId}", produces = {"application/json"})
	public ResponseEntity<BookingDetails> bookAFlight(@Valid @PathVariable("flightId") String flightId,
			@RequestBody BookingDetailsFromUI bookingDetailsDisplay) throws BadRequestException {
		logger.info("Book the tickets");
		return new ResponseEntity<>(service.bookAFlight(flightId, bookingDetailsDisplay), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/booking/history/{emailId}", produces = {"application/json"})
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<BookingDetails>> bookedTicketHistory(@Valid @PathVariable("emailId") String emailId) {
		logger.info("Fetching ticket history for " + emailId);
		return new ResponseEntity<>(service.bookedTicketHistory(emailId), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/booking/cancel/{pnr}", produces = {"application/text"})
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> cancelBooking(@Valid @PathVariable("pnr") String pnr) throws TicketNotFoundException {
		logger.warn("Cancelling ticket of " + pnr);
		return new ResponseEntity<>(service.cancelBooking(pnr), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/userDetails/{emailId}", produces = {"application/text"})
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> getUserDetails(@Valid @PathVariable("emailId") String emailId) throws UserNotFoundException {
		logger.info("Get User Details!");
		return new ResponseEntity<>(userService.getUserDetails(emailId), HttpStatus.OK);
	}
}
