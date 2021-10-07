package com.flightapp.usermode.DAO;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class BookingDetailsFromUI {

	@NotEmpty(message = "Name should not be empty")
	private String name;
	@NotEmpty(message = "EmailId should not be empty")
	private String emailId;
	private int numberOfSeats;
	@NotEmpty(message = "Passenger details should not be empty")
	private List<PassengerDetails> passengerDetails;
	@NotEmpty(message = "Seat Type should not be empty")
	private String seatType;
	@NotEmpty(message = "Meal Option should not be empty")
	private String mealOption;
	@NotEmpty(message = "Date of Travel should not be empty")
	private String dateofTravel;
	@NotEmpty(message = "From Place should not be empty")
	private String fromPlace;
	@NotEmpty(message = "To Place should not be empty")
	private String toPlace;
	@NotEmpty(message = "Departure time should not be empty")
	private String departureTime;
	@NotEmpty(message = "Arrival time should not be empty")
	private String arrivalTime;
	@NotEmpty(message = "Flight number should not be empty")
	private String flightNumber;
	@NotEmpty(message = "Discount Code should not be empty")
	private String discountCode;
	private int ticketCost;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public List<PassengerDetails> getPassengerDetails() {
		return passengerDetails;
	}
	public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
		this.passengerDetails = passengerDetails;
	}
	public String getMealOption() {
		return mealOption;
	}
	public void setMealOption(String mealOption) {
		this.mealOption = mealOption;
	}
//	public String getSeatnos() {
//		return seatnos;
//	}
//	public void setSeatnos(String seatnos) {
//		this.seatnos = seatnos;
//	}
	public String getDateofTravel() {
		return dateofTravel;
	}
	public void setDateofTravel(String dateofTravel) {
		this.dateofTravel = dateofTravel;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public int getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(int ticketCost) {
		this.ticketCost = ticketCost;
	}
}
