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
import com.flightapp.usermode.DAO.PassengerDetails;
import com.flightapp.usermode.Interface.BookingDetailsRepository;
import com.flightapp.usermode.Interface.PassengerDetailsRepository;

@RestController
@RequestMapping("/api/v1.0/flight/")
public class FlightAppController {

	@Autowired
	BookingDetailsRepository bookingDetailsRepo;

	@Autowired
	PassengerDetailsRepository passengerDetailsRepo;

	@GetMapping("/ticket/{pnr}")
	public BookingDetailsDisplay getBookingDetails(@PathVariable("pnr") String pnr) {
		if(pnr == null)
			return null;
		BookingDetails bookingDetails = bookingDetailsRepo.findByPnr(pnr);
		
		if(bookingDetails != null) {
			BookingDetailsDisplay bookingDetailsDisplay = new BookingDetailsDisplay();
			bookingDetailsDisplay.setPnr(pnr);
			bookingDetailsDisplay.setEmailId(bookingDetails.getEmaildId());
			bookingDetailsDisplay.setMealOption(bookingDetails.getMealOption());
			bookingDetailsDisplay.setName(bookingDetails.getName());
			bookingDetailsDisplay.setNumberOfSeats(bookingDetails.getNumberOfSeats());
			bookingDetailsDisplay.setSeatnos(null);
			bookingDetailsDisplay.setDateofTravel(bookingDetails.getDateOfTravel());
			bookingDetailsDisplay.setTicketCost(bookingDetails.getTicketCost());
			bookingDetailsDisplay.setPassengerDetails(passengerDetailsRepo.findByBookingId(pnr));
			return bookingDetailsDisplay;
		}
		return null;
	}

	@PostMapping("/booking/{flightId}")
	public BookingDetails bookAFlight(@PathVariable("flightId") String flightId,
			@RequestBody BookingDetailsDisplay bookingDetailsDisplay) {

		if(bookingDetailsDisplay == null) {
			return null;
		}
		BookingDetails bookingDetails = new BookingDetails();
		String pnr = flightId + Math.floor((int) Math.random() * 1000000);
		bookingDetails.setPnr(pnr);
		bookingDetails.setMealOption(bookingDetailsDisplay.getMealOption());
		bookingDetails.setName(bookingDetailsDisplay.getName());
		bookingDetails.setEmaildId(bookingDetailsDisplay.getEmailId());
		bookingDetails.setNumberOfSeats(bookingDetailsDisplay.getNumberOfSeats());
		bookingDetails.setSeatnos(bookingDetailsDisplay.getSeatnos().toString());
		bookingDetails.setDateOfTravel(bookingDetailsDisplay.getDateofTravel());
		bookingDetails.setTicketCost(bookingDetailsDisplay.getTicketCost());
		
		for (PassengerDetails details : bookingDetailsDisplay.getPassengerDetails()) {
			PassengerDetails passengerDetails = new PassengerDetails();
			passengerDetails.setName(details.getName());
			passengerDetails.setBookingId(pnr);
			passengerDetails.setGender(details.getGender());
			passengerDetails.setAge(details.getAge());
			passengerDetailsRepo.save(passengerDetails);
		}

		return bookingDetailsRepo.save(bookingDetails);
	}
	
	@GetMapping("/booking/history/{emailId}")
	public List<BookingDetails> bookedTicketHistory(@PathVariable("emailId") String emailId) {
		return bookingDetailsRepo.findByEmaildId(emailId);
	}
	
	@DeleteMapping("/booking/cancel/{pnr}")
	public String cancelBooking(@PathVariable("pnr") String pnr) {
		List<PassengerDetails> details = passengerDetailsRepo.findByBookingId(pnr);
		details.stream().forEach(d -> {
			passengerDetailsRepo.deleteById(d.getId());
		});
		bookingDetailsRepo.deleteById(pnr);
		
		return "Ticket has been cancelled successfully";
	}
}
