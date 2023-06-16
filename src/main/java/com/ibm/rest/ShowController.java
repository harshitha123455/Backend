package com.ibm.rest;

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
import com.ibm.service.ShowService;

@CrossOrigin
@RestController
public class ShowController {

	@Autowired
	private ShowService showService;
	
//	http://localhost:8880/show/all
	@GetMapping(path = "/show/all", produces="application/json")
	public ResponseEntity<List<Shows>> getAllShows(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(showService.list());
	}
	
//	http://localhost:8880/show/search/movie/id/{id}
	@GetMapping(path = "/show/search/movie/id/{id}", produces="application/json")
	public ResponseEntity<List<Shows>> getAllShowsByMovieId(@PathVariable int id) throws MovieNotFoundException{
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(showService.searchByMovieId(id));
	}
	
//	http://localhost:8880/show/search/movie/name/{name}
	@GetMapping(path = "/show/search/movie/name/{name}", produces="application/json")
	public ResponseEntity<List<Shows>> getAllShowsByMovieName(@PathVariable String name) throws MovieNotFoundException{
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ShowController")
				.body(showService.searchByMovieName(name));
	}
	
	// fetch timetable of a show
		
}