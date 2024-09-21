package com.futbol.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3381402997645181757L;

	public EquipoNotFoundException(String mensaje) {
		super(mensaje);
	}
	
	public EquipoNotFoundException() {
		super();
	}
	
}
