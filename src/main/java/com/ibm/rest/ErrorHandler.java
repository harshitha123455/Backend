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

/**
 * REST controller advice to handle exceptions and return appropriate responses.
 */
@RestControllerAdvice
public class ErrorHandler {

	/**
	 * Exception handler for MovieNotFoundException.
	 *
	 * @param mnfe the MovieNotFoundException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<Response> handleMovieNotFoundException(MovieNotFoundException mnfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(new Response(mnfe.getMessage()));
	}

	/**
	 * Exception handler for MovieAlreadyExistException.
	 *
	 * @param maee the MovieAlreadyExistException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(MovieAlreadyExistException.class)
	public ResponseEntity<Response> handleMovieAlreadyExistsException(MovieAlreadyExistException maee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(maee.getMessage()));
	}

	/**
	 * Exception handler for ScreenNotFoundException.
	 *
	 * @param snfe the ScreenNotFoundException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(ScreenNotFoundException.class)
	public ResponseEntity<Response> handleScreenNotFoundException(ScreenNotFoundException snfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Response from", "ErrorHandler")
				.body(new Response(snfe.getMessage()));
	}

	/**
	 * Exception handler for ScreenAlreadyExistException.
	 *
	 * @param saee the ScreenAlreadyExistException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(ScreenAlreadyExistException.class)
	public ResponseEntity<Response> handleScreenAlreadyExistsException(ScreenAlreadyExistException saee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(saee.getMessage()));
	}

	/**
	 * Exception handler for TimeTableAlreadyExistException.
	 *
	 * @param ttaee the TimeTableAlreadyExistException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(TimeTableAlreadyExistException.class)
	public ResponseEntity<Response> handleTimeTableAlreadyExistsException(TimeTableAlreadyExistException ttaee) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(ttaee.getMessage()));
	}

	/**
	 * Exception handler for TimeTableNotFoundException.
	 *
	 * @param ttnfe the TimeTableNotFoundException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(TimeTableNotFoundException.class)
	public ResponseEntity<Response> handleTimeTableNotFoundException(TimeTableNotFoundException ttnfe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(ttnfe.getMessage()));
	}

	/**
	 * Exception handler for BookingNotFoundException.
	 *
	 * @param bnfe the BookingNotFoundException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Response> handleBookingNotFoundException(BookingNotFoundException bnfe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(bnfe.getMessage()));
	}

	/**
	 * Exception handler for JsonMappingException.
	 *
	 * @param jme the JsonMappingException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<Response> handleJsonMappingException(JsonMappingException jme) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(jme.getMessage()));
	}

	/**
	 * Exception handler for JsonProcessingException.
	 *
	 * @param jpe the JsonProcessingException object
	 * @return ResponseEntity containing the error response and HTTP status
	 */
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<Response> handleJsonProcessingException(JsonProcessingException jpe) {
		return ResponseEntity.status(HttpStatus.CONFLICT).header("Response from", "ErrorHandler")
				.body(new Response(jpe.getMessage()));
	}

}
