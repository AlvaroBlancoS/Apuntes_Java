package com.futbol.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	MiLogger log = new MiLogger(CustomExceptionHandler.class.toString(), true);
	
	// ----------- EXCEPTIONES DE JUGADOR -----------
	@ExceptionHandler(JugadorNotFoundException.class)
	public ResponseEntity<String> handleJugadorNotFoundException(JugadorNotFoundException ex) {
		log.logError("No se encuentra el jugador " + HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(JugadorBadRequest.class)
	public ResponseEntity<String> handleJugadorBadResquestException(JugadorBadRequest ex) {
		log.logError(ex.getMessage() + " " + HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// ----------- OTROS -----------
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		log.logError("Error interno del servidor:" + ex.getMessage() + " (" + HttpStatus.INTERNAL_SERVER_ERROR + ")");
		return new ResponseEntity<>(
				"Error interno del servidor: " + ex.getMessage() + " (" + HttpStatus.INTERNAL_SERVER_ERROR + ")",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
