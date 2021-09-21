package com.flightapp.usermode.Exception;

public class TicketNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketNotFoundException() {
		super();
	}

	public TicketNotFoundException(final String message) {
		super(message);
	}

}
