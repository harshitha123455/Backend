package com.ibm.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Shows;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.pojo.Response;
import com.ibm.service.ShowService;

/**
 * REST controller for handling Show-related operations.
 */
@RestController
@CrossOrigin
public class ShowController {

	@Autowired
	private ShowService showService;

	/**
	 * End point for retrieving all shows.
	 * 
	 * Example URL: http://localhost:8880/show/all
	 *
	 * @return ResponseEntity containing the list of shows
	 */
	@GetMapping(path = "/show/all", produces = "application/json")
	public ResponseEntity<List<Shows>> getAllShows() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(showService.list());
	}

	/**
	 * End point for retrieving all shows by movie ID.
	 * 
	 * Example URL: http://localhost:8880/show/search/movie/id/{id}
	 *
	 * @param id the ID of the movie
	 * @return ResponseEntity containing the list of shows for the movie
	 * @throws MovieNotFoundException if the movie is not found
	 */
	@GetMapping(path = "/show/search/movie/id/{id}", produces = "application/json")
	public ResponseEntity<List<Shows>> getAllShowsByMovieId(@PathVariable int id) throws MovieNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(showService.searchByMovieId(id));
	}

	/**
	 * End point for retrieving the reserved seats for a show by its ID.
	 * 
	 * Example URL: http://localhost:8880/show/reserved/id/{id}
	 *
	 * @param id the ID of the show
	 * @return ResponseEntity containing the response object with the list of
	 *         reserved seat positions
	 */
	@GetMapping(path = "/show/reserved/id/{id}", produces = "application/json")
	public ResponseEntity<Response> getReserved(@PathVariable int id) {
		List<Boolean> reserved = showService.searchById(id).getSeatingArrangement().getReserved();
		List<Integer> pos = new ArrayList<>();
		for (int i = 0; i < reserved.size(); i++) {
			if (reserved.get(i) != null && reserved.get(i))
				pos.add(i + 1);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(new Response(pos));
	}

	/**
	 * End point for retrieving all shows by movie name.
	 * 
	 * Example URL: http://localhost:8880/show/search/movie/name/{name}
	 *
	 * @param name the name of the movie
	 * @return ResponseEntity containing the list of shows for the movie
	 * @throws MovieNotFoundException if the movie is not found
	 */
	@GetMapping(path = "/show/search/movie/name/{name}", produces = "application/json")
	public ResponseEntity<List<Shows>> getAllShowsByMovieName(@PathVariable String name) throws MovieNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(showService.searchByMovieName(name));
	}

}
