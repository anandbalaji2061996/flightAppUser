package com.flightapp.usermode.Service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsDisplay;
import com.flightapp.usermode.DAO.PassengerDetails;
import com.flightapp.usermode.Interface.BookingDetailsRepository;
import com.flightapp.usermode.Interface.PassengerDetailsRepository;

@Service
public class FlightAppService {

	private BookingDetailsRepository bookingDetailsRepository;

	private PassengerDetailsRepository passengerDetailsRepository;

	public FlightAppService(BookingDetailsRepository bookingDetailsRepository,
			PassengerDetailsRepository passengerDetailsRepository) {
		this.bookingDetailsRepository = bookingDetailsRepository;
		this.passengerDetailsRepository = passengerDetailsRepository;
	}

	public BookingDetailsDisplay getBookingDetails(String pnr) {
		if (pnr == null)
			return null;
		BookingDetails bookingDetails = bookingDetailsRepository.findByPnr(pnr);

		if (bookingDetails != null) {
			BookingDetailsDisplay bookingDetailsDisplay = new BookingDetailsDisplay();
			bookingDetailsDisplay.setPnr(pnr);
			bookingDetailsDisplay.setEmailId(bookingDetails.getEmaildId());
			bookingDetailsDisplay.setMealOption(bookingDetails.getMealOption());
			bookingDetailsDisplay.setName(bookingDetails.getName());
			bookingDetailsDisplay.setNumberOfSeats(bookingDetails.getNumberOfSeats());
			bookingDetailsDisplay.setSeatnos(null);
			bookingDetailsDisplay.setDateofTravel(bookingDetails.getDateOfTravel());
			bookingDetailsDisplay.setTicketCost(bookingDetails.getTicketCost());
			bookingDetailsDisplay.setPassengerDetails(passengerDetailsRepository.findByBookingId(pnr));
			return bookingDetailsDisplay;
		}
		return null;
	}

	public BookingDetails bookAFlight(String flightId, BookingDetailsDisplay bookingDetailsDisplay) {
		if (bookingDetailsDisplay == null) {
			return null;
		}
		BookingDetails bookingDetails = new BookingDetails();
		String pnr = flightId + "-"+ new Random().nextLong();
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
			passengerDetailsRepository.save(passengerDetails);
		}

		return bookingDetailsRepository.save(bookingDetails);
	}
	
	public List<BookingDetails> bookedTicketHistory(String emailId) {
		return bookingDetailsRepository.findByEmaildId(emailId);
	}
	
	public String cancelBooking(String pnr) {
		List<PassengerDetails> details = passengerDetailsRepository.findByBookingId(pnr);
		details.stream().forEach(d -> {
			passengerDetailsRepository.deleteById(d.getId());
		});
		bookingDetailsRepository.deleteById(pnr);
		
		return "Ticket has been cancelled successfully";
	}

}
