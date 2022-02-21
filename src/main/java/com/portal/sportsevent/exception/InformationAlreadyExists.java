package com.portal.sportsevent.exception;

public class InformationAlreadyExists extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InformationAlreadyExists(String message) {
		super(message);
	}
}
