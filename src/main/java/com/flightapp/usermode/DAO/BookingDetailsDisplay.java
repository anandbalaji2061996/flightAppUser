package com.flightapp.usermode.DAO;

import java.util.List;

import javax.persistence.Id;

public class BookingDetailsDisplay {

	@Id
	private String pnr;
	private String name;
	private String emailId;
	private String numberOfSeats;
	private List<PassengerDetails> passengerDetails;
	private String mealOption;
	private List<String> seatnos;
	private String dateofTravel;
	private String ticketCost;
	
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(String numberOfSeats) {
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
	public List<String> getSeatnos() {
		return seatnos;
	}
	public void setSeatnos(List<String> seatnos) {
		this.seatnos = seatnos;
	}
	public String getDateofTravel() {
		return dateofTravel;
	}
	public void setDateofTravel(String dateofTravel) {
		this.dateofTravel = dateofTravel;
	}
	public String getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(String ticketCost) {
		this.ticketCost = ticketCost;
	}
	
}
