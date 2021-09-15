package com.flightapp.usermode.DAO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookingDetails")
public class BookingDetails {

	@Id
	private String pnr;
	private String name;
	private String emaildId;
	private String numberOfSeats;
	private String mealOption;
	private String seatNos;
	private String dateOfTravel;
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
	public String getEmaildId() {
		return emaildId;
	}
	public void setEmaildId(String emaildId) {
		this.emaildId = emaildId;
	}
	public String getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public String getMealOption() {
		return mealOption;
	}
	public void setMealOption(String mealOption) {
		this.mealOption = mealOption;
	}
	public String getSeatnos() {
		return seatNos;
	}
	public void setSeatnos(String seatNos) {
		this.seatNos = seatNos;
	}
	public String getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(String dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public String getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(String ticketCost) {
		this.ticketCost = ticketCost;
	}
	
}
