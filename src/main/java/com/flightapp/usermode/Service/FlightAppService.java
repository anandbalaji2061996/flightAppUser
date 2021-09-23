package com.flightapp.usermode.Service;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsDisplay;
import com.flightapp.usermode.DAO.BookingDetailsFromUI;
import com.flightapp.usermode.DAO.PassengerDetails;
import com.flightapp.usermode.Exception.BadRequestException;
import com.flightapp.usermode.Exception.TicketNotFoundException;
import com.flightapp.usermode.Interface.BookingDetailsRepository;
import com.flightapp.usermode.Interface.PassengerDetailsRepository;

@Service
public class FlightAppService {
	
	private static final Logger logger = LogManager.getLogger(FlightAppService.class);

	private BookingDetailsRepository bookingDetailsRepository;

	private PassengerDetailsRepository passengerDetailsRepository;

	public FlightAppService(BookingDetailsRepository bookingDetailsRepository,
			PassengerDetailsRepository passengerDetailsRepository) {
		this.bookingDetailsRepository = bookingDetailsRepository;
		this.passengerDetailsRepository = passengerDetailsRepository;
	}

	public BookingDetailsDisplay getBookingDetails(String pnr) throws TicketNotFoundException{
		BookingDetails bookingDetails = bookingDetailsRepository.findByPnr(pnr);

		if (bookingDetails != null) {
			BookingDetailsDisplay bookingDetailsDisplay = new BookingDetailsDisplay();
			bookingDetailsDisplay.setPnr(pnr);
			bookingDetailsDisplay.setEmailId(bookingDetails.getEmailId());
			bookingDetailsDisplay.setMealOption(bookingDetails.getMealOption());
			bookingDetailsDisplay.setName(bookingDetails.getName());
			bookingDetailsDisplay.setNumberOfSeats(bookingDetails.getNumberOfSeats());
			bookingDetailsDisplay.setSeatnos(bookingDetails.getSeatNos());
			bookingDetailsDisplay.setSeatType(bookingDetails.getSeatType());
			bookingDetailsDisplay.setDateofTravel(bookingDetails.getDateOfTravel());
			bookingDetailsDisplay.setTicketCost(bookingDetails.getTicketCost());
			bookingDetailsDisplay.setFlightNumber(bookingDetails.getFlightNumber());
			bookingDetailsDisplay.setPassengerDetails(passengerDetailsRepository.findByBookingId(pnr));
			return bookingDetailsDisplay;
		}
		logger.warn("Ticket not found");
		throw new TicketNotFoundException("Ticket not found!");
	}

	public BookingDetails bookAFlight(String flightId, BookingDetailsFromUI bookingDetailsDisplay) throws BadRequestException {
		if (bookingDetailsDisplay == null) {
			logger.warn("Invalid Details");
			throw new BadRequestException("Invalid details!");
		}
		BookingDetails bookingDetails = new BookingDetails();
		String pnr = flightId +"-"+ new Random().nextLong();
		bookingDetails.setPnr(pnr);
		bookingDetails.setMealOption(bookingDetailsDisplay.getMealOption());
		bookingDetails.setName(bookingDetailsDisplay.getName());
		bookingDetails.setEmailId(bookingDetailsDisplay.getEmailId());
		bookingDetails.setSeatType(bookingDetailsDisplay.getSeatType());
		bookingDetails.setNumberOfSeats(bookingDetailsDisplay.getNumberOfSeats());
		bookingDetails.setSeatNos(bookingDetailsDisplay.getSeatnos());
		bookingDetails.setDateOfTravel(bookingDetailsDisplay.getDateofTravel());
		bookingDetails.setTicketCost(bookingDetailsDisplay.getTicketCost());
		bookingDetails.setFlightNumber(bookingDetailsDisplay.getFlightNumber());
		String[] listOfPassenger = bookingDetailsDisplay.getPassengerDetails().split(",");

		for (String details : listOfPassenger) {
			String[] passenger = details.split("-");
			PassengerDetails passengerDetails = new PassengerDetails();
			passengerDetails.setName(passenger[0]);
			passengerDetails.setBookingId(pnr);
			passengerDetails.setGender(passenger[1]);
			passengerDetails.setAge(passenger[2]);
			passengerDetailsRepository.save(passengerDetails);
		}

		return bookingDetailsRepository.save(bookingDetails);
	}
	
	public List<BookingDetails> bookedTicketHistory(String emailId) {
		return bookingDetailsRepository.findByEmailId(emailId);
	}
	
	public String cancelBooking(String pnr) throws TicketNotFoundException {
		List<PassengerDetails> details = passengerDetailsRepository.findByBookingId(pnr);
		
		if(details.size()==0 || bookingDetailsRepository.findByPnr(pnr) == null) {
			logger.warn("Ticket not found");
			throw new TicketNotFoundException("Ticket Not Found!");
		}
		
		details.stream().forEach(d -> {
			passengerDetailsRepository.deleteById(d.getId());
		});
		bookingDetailsRepository.deleteById(pnr);
		
		return "Ticket has been cancelled successfully";
	}

}
