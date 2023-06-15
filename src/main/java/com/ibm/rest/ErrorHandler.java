package com.ibm.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibm.exception.MovieAlreadyExistException;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.exception.ScreenAlreadyExistException;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;

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
	public ResponseEntity<String> handleScreenNotFoundException(ScreenNotFoundException snfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(snfe.getMessage());
	}

	@ExceptionHandler(ScreenAlreadyExistException.class)
	public ResponseEntity<String> handleScreenAlreadyExistsException(ScreenAlreadyExistException saee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(saee.getMessage());
	}
	
	@ExceptionHandler(TimeTableAlreadyExistException.class)
	public ResponseEntity<String> handleTimeTableAlreadyExistsException(TimeTableAlreadyExistException ttaee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(ttaee.getMessage());
	}
	
	@ExceptionHandler(TimeTableNotFoundException.class)
	public ResponseEntity<String> handleTimeTableNotFoundException(TimeTableNotFoundException ttnfe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(ttnfe.getMessage());
	}

}
