package com.flightapp.usermode.DAO;

public class BookingDetailsFromUI {

	private String name;
	private String emailId;
	private int numberOfSeats;
	private String passengerDetails;
	private String seatType;
	private String mealOption;
//	private String seatnos;
	private String dateofTravel;
	private String flightNumber;
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
	public String getPassengerDetails() {
		return passengerDetails;
	}
	public void setPassengerDetails(String passengerDetails) {
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
