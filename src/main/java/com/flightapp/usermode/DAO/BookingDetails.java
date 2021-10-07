package com.flightapp.usermode.DAO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bookingDetails")
public class BookingDetails {

	@Id
	@NotEmpty(message = "Pnr number should not be empty")
	private String pnr;
	@NotEmpty(message = "Flight number should not be empty")
	private String flightNumber;
	@NotEmpty(message = "Name should not be empty")
	private String name;
	@NotEmpty(message = "Email Id should not be empty")
	private String emailId;
	@NotEmpty(message = "Number od seats should not be empty")
	private int numberOfSeats;
	@NotEmpty(message = "Meal Option should not be empty")
	private String mealOption;
	@NotEmpty(message = "Seat type should not be empty")
	private String seatType;
//	private String seatNos;
	@NotEmpty(message = "Date of Travel should not be empty")
	private String dateOfTravel;
	@NotEmpty(message = "From Place should not be empty")
	private String fromPlace;
	@NotEmpty(message = "To Place should not be empty")
	private String toPlace;
	@NotEmpty(message = "Departure Time should not be empty")
	private String departureTime;
	@NotEmpty(message = "Arrival Time should not be empty")
	private String arrivalTime;
	@NotEmpty(message = "Discount code should not be empty")
	private String discountCode;
	@NotEmpty(message = "Ticket Cost should not be empty")
	private int ticketCost;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PNR_NO")
    private List<PassengerDetails> passengerDetails;
	
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
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
	public String getMealOption() {
		return mealOption;
	}
	public void setMealOption(String mealOption) {
		this.mealOption = mealOption;
	}
	public String getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(String dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
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
	public List<PassengerDetails> getPassengerDetails() {
		return passengerDetails;
	}
	public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
		this.passengerDetails = passengerDetails;
	}
	
}
