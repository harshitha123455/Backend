package com.ibm.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibm.service.MovieAlreadyExistException;
import com.ibm.service.MovieNotFoundException;
import com.ibm.service.ScreenAlreadyExistException;
import com.ibm.service.ScreenNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException mnfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(mnfe.getMessage());
	}

	@ExceptionHandler(MovieAlreadyExistException.class)
	public ResponseEntity<String> handleMovieAlreadyExistsException(MovieAlreadyExistException maee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(maee.getMessage());
	}

	@ExceptionHandler(ScreenNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleScreenNotFoundException(ScreenNotFoundException snfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(snfe.getMessage());
	}

	@ExceptionHandler(ScreenAlreadyExistException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleScreenAlreadyExistsException(ScreenAlreadyExistException saee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(saee.getMessage());
	}

}
