package com.futbol.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class JugadorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 888618408530421586L;

	public JugadorNotFoundException(String mensaje) {
		super(mensaje);
	}
	
	public JugadorNotFoundException() {
		super();
	}
	
	

}
