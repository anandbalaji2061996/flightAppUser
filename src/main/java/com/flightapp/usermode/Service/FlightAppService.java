package com.flightapp.usermode.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsFromUI;
import com.flightapp.usermode.DAO.PassengerDetails;
import com.flightapp.usermode.Exception.BadRequestException;
import com.flightapp.usermode.Exception.TicketNotFoundException;
import com.flightapp.usermode.Interface.BookingDetailsRepository;
import com.flightapp.usermode.Interface.PassengerDetailsRepository;

@Service
public class FlightAppService {

	private static final Logger logger = LoggerFactory.getLogger(FlightAppService.class);

	private BookingDetailsRepository bookingDetailsRepository;

	private PassengerDetailsRepository passengerDetailsRepository;

	public FlightAppService(BookingDetailsRepository bookingDetailsRepository,
			PassengerDetailsRepository passengerDetailsRepository) {
		this.bookingDetailsRepository = bookingDetailsRepository;
		this.passengerDetailsRepository = passengerDetailsRepository;
	}

	public BookingDetails getBookingDetails(String pnr) throws TicketNotFoundException {
		BookingDetails bookingDetails = bookingDetailsRepository.findByPnr(pnr);

		if (bookingDetails != null) {
			return bookingDetails;
		}
		logger.warn("Tickets not found");
		throw new TicketNotFoundException("Tickets not found!");
	}

	public BookingDetails bookAFlight(String flightId, BookingDetailsFromUI bookingDetailsDisplay)
			throws BadRequestException {
		if (bookingDetailsDisplay == null) {
			logger.warn("Invalid Details");
			throw new BadRequestException("Invalid details!");
		}
		BookingDetails bookingDetails = new BookingDetails();
		String pnr = flightId + "-" + Math.abs(new Random().nextLong());
		bookingDetails.setPnr(pnr);
		bookingDetails.setMealOption(bookingDetailsDisplay.getMealOption());
		bookingDetails.setName(bookingDetailsDisplay.getName());
		bookingDetails.setEmailId(bookingDetailsDisplay.getEmailId());
		bookingDetails.setSeatType(bookingDetailsDisplay.getSeatType());
		bookingDetails.setNumberOfSeats(bookingDetailsDisplay.getNumberOfSeats());
//		bookingDetails.setSeatNos(bookingDetailsDisplay.getSeatnos());
		bookingDetails.setDateOfTravel(bookingDetailsDisplay.getDateofTravel());
		bookingDetails.setFromPlace(bookingDetailsDisplay.getFromPlace());
		bookingDetails.setToPlace(bookingDetailsDisplay.getToPlace());
		bookingDetails.setDepartureTime(bookingDetailsDisplay.getDepartureTime());
		bookingDetails.setArrivalTime(bookingDetailsDisplay.getArrivalTime());
		bookingDetails.setTicketCost(bookingDetailsDisplay.getTicketCost());
		bookingDetails.setFlightNumber(bookingDetailsDisplay.getFlightNumber());
		bookingDetails.setDiscountCode(bookingDetailsDisplay.getDiscountCode());

		List<PassengerDetails> passengerList = new ArrayList<>();

		for (PassengerDetails details : bookingDetailsDisplay.getPassengerDetails()) {
			PassengerDetails passengerDetails = new PassengerDetails();
			passengerDetails.setName(details.getName());
			passengerDetails.setGender(details.getGender());
			passengerDetails.setAge(details.getAge());
			passengerList.add(passengerDetails);
			passengerDetailsRepository.save(passengerDetails);
		}
		bookingDetails.setPassengerDetails(passengerList);
		return bookingDetailsRepository.save(bookingDetails);
	}

	public List<BookingDetails> bookedTicketHistory(String emailId) {
		return bookingDetailsRepository.findByEmailId(emailId);
	}

	public String cancelBooking(String pnr) throws TicketNotFoundException {

		if (bookingDetailsRepository.findByPnr(pnr) == null) {
			logger.warn("Ticket not found");
			throw new TicketNotFoundException("Ticket Not Found!");
		}

		bookingDetailsRepository.deleteById(pnr);

		return "Ticket has been cancelled successfully";
	}

}
