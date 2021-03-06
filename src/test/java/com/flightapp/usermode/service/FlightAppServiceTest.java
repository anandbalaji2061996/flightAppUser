package com.flightapp.usermode.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.flightapp.usermode.DAO.BookingDetails;
import com.flightapp.usermode.DAO.BookingDetailsFromUI;
import com.flightapp.usermode.DAO.PassengerDetails;
import com.flightapp.usermode.Exception.BadRequestException;
import com.flightapp.usermode.Exception.TicketNotFoundException;
import com.flightapp.usermode.Interface.BookingDetailsRepository;
import com.flightapp.usermode.Interface.PassengerDetailsRepository;
import com.flightapp.usermode.Service.FlightAppService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FlightAppServiceTest {

	@Autowired
	BookingDetailsRepository detailsRepository;

	@Autowired
	PassengerDetailsRepository passengerDetailsRepository;

	private FlightAppService service;

	private BookingDetailsFromUI fromUI;

	@BeforeEach
	public void setup() {
		service = new FlightAppService(detailsRepository, passengerDetailsRepository);
		fromUI = new BookingDetailsFromUI();
		fromUI.setDateofTravel("today");
		fromUI.setDepartureTime("13:00");
		fromUI.setArrivalTime("15:00");
		fromUI.setFromPlace("place1");
		fromUI.setToPlace("place2");
		fromUI.setEmailId("testEmailId");
		fromUI.setFlightNumber("123");
		fromUI.setMealOption("Veg");
		fromUI.setName("myName");
		fromUI.setNumberOfSeats(2);
		List<PassengerDetails> pd = new ArrayList<>();
		PassengerDetails p = new PassengerDetails();
		p.setAge(23);
		p.setName("passengerName");
		p.setGender("Male");
		pd.add(p);		
		fromUI.setPassengerDetails(pd);
//		fromUI.setSeatnos("21,22");
		fromUI.setSeatType("Business Class");
		fromUI.setDiscountCode("TESTDISCOUNT10");
		fromUI.setTicketCost(11000);
				
	}

	@AfterEach
	public void setup2() {
		detailsRepository.deleteAll();
		passengerDetailsRepository.deleteAll();
	}

	@Test
	public void bookAFlightTest() throws BadRequestException {
		Throwable thrown = catchThrowable(() -> service.bookAFlight("123", null));

		assertThat(thrown).isInstanceOf(BadRequestException.class);

		BookingDetails bd = service.bookAFlight("123", fromUI);
		assertNotNull(bd);
		assertEquals("today", bd.getDateOfTravel());
		assertEquals("13:00", bd.getDepartureTime());
		assertEquals("15:00", bd.getArrivalTime());
		assertEquals("place1", bd.getFromPlace());
		assertEquals("place2", bd.getToPlace());
		assertEquals("testEmailId", bd.getEmailId());
		assertEquals("123", bd.getFlightNumber());
		assertEquals("Veg", bd.getMealOption());
		assertEquals(2, bd.getNumberOfSeats());
//		assertEquals("21,22", bd.getSeatNos());
		assertEquals("Business Class", bd.getSeatType());
		assertEquals(11000, bd.getTicketCost());
		assertEquals("myName", bd.getName());
		assertEquals("TESTDISCOUNT10",bd.getDiscountCode());
		assertEquals(1, bd.getPassengerDetails().size());
	}

	@Test
	public void bookedTicketHistoryTest() throws BadRequestException {

		service.bookAFlight("123", fromUI);

		List<BookingDetails> list = service.bookedTicketHistory("testEmailId");
		assertEquals(1, list.size());
	}

	@Test
	public void getBookingDetailsTest() throws BadRequestException, TicketNotFoundException {
		BookingDetails bd = service.bookAFlight("123", fromUI);

		Throwable thrown = catchThrowable(() -> service.getBookingDetails("123"));

		assertThat(thrown).isInstanceOf(TicketNotFoundException.class);

		assertNotNull(service.getBookingDetails(bd.getPnr()));
		assertNotNull(service.getBookingDetails(bd.getPnr()));

	}

	@Test
	public void cancelTicketTest() throws BadRequestException, TicketNotFoundException {
		BookingDetails bd = service.bookAFlight("123", fromUI);

		Throwable thrown = catchThrowable(() -> service.cancelBooking("123"));

		assertThat(thrown).isInstanceOf(TicketNotFoundException.class);

		assertNotNull(service.getBookingDetails(bd.getPnr()));

		assertEquals("Ticket has been cancelled successfully", service.cancelBooking(bd.getPnr()));

		thrown = catchThrowable(() -> service.getBookingDetails(bd.getPnr()));

		assertThat(thrown).isInstanceOf(TicketNotFoundException.class);
	}
}
