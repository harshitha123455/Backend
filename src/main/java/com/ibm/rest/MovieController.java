package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Movie;
import com.ibm.service.MovieAlreadyExistException;
import com.ibm.service.MovieNotFoundException;
import com.ibm.service.MovieService;

@CrossOrigin
@RestController
@RequestMapping(path = "/movie")
public class MovieController {

	@Autowired
	private MovieService service;

//	http://localhost:8880/movie/add
	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<String> addMovie(@RequestBody Movie m) throws MovieAlreadyExistException {
		int id = service.save(m);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Movie added with id: " + id);
	}

//	http://localhost:8880/movie/all	
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Movie>> getAllMovies() {
		 return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
					.body(service.list());
	}

//	http://localhost:8880/movie/search/id/{id}
	@GetMapping(path = "/search/id/{id}", produces = "application/json")
	public ResponseEntity<Movie> getMovieById(@PathVariable int id) throws MovieNotFoundException {
		Movie m = service.searchById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(m);
	}

//	http://localhost:8880/movie/search/name/{name}
	@GetMapping(path = "/search/name/{name}", produces = "application/json")
	public ResponseEntity<Movie> getMovieByName(@PathVariable String name) throws MovieNotFoundException {
		Movie m= service.searchByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(m);
	}

//	http://localhost:8880/movie/remove/id/{id}
	@DeleteMapping(path = "/remove/id/{id}")
	public ResponseEntity<String> removeMovieById(@PathVariable int id) throws MovieNotFoundException {
		service.removeById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Movie with id: " + id + " deleted successfully");
	}

//	http://localhost:8880/movie/remove/name/{name}
	@DeleteMapping(path = "/remove/name/{name}")
	public ResponseEntity<String> removeMovieByName(@PathVariable String name) throws MovieNotFoundException {
		service.removeByName(name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Movie with name: " + name + " deleted successfully");
	}
}
