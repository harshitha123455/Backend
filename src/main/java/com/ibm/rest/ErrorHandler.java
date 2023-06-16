package com.ibm.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.exception.MovieAlreadyExistException;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.exception.ScreenAlreadyExistException;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;
import com.ibm.pojo.Response;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<Response> handleMovieNotFoundException(MovieNotFoundException mnfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(new Response(mnfe.getMessage()));
	}

	@ExceptionHandler(MovieAlreadyExistException.class)
	public ResponseEntity<Response> handleMovieAlreadyExistsException(MovieAlreadyExistException maee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(maee.getMessage()));
	}

	@ExceptionHandler(ScreenNotFoundException.class)
	public ResponseEntity<Response> handleScreenNotFoundException(ScreenNotFoundException snfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(new Response(snfe.getMessage()));
	}

	@ExceptionHandler(ScreenAlreadyExistException.class)
	public ResponseEntity<Response> handleScreenAlreadyExistsException(ScreenAlreadyExistException saee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(saee.getMessage()));
	}
	
	@ExceptionHandler(TimeTableAlreadyExistException.class)
	public ResponseEntity<Response> handleTimeTableAlreadyExistsException(TimeTableAlreadyExistException ttaee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(ttaee.getMessage()));
	}
	
	@ExceptionHandler(TimeTableNotFoundException.class)
	public ResponseEntity<Response> handleTimeTableNotFoundException(TimeTableNotFoundException ttnfe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(ttnfe.getMessage()));
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Response> handleBookingNotFoundException(BookingNotFoundException bnfe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(bnfe.getMessage()));
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<Response> handleJsonMappingException(JsonMappingException jme) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(jme.getMessage()));
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<Response> handleJsonProcessingException(JsonProcessingException jpe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(jpe.getMessage()));
	}

}
