package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ibm.entity.Movie;
import com.ibm.exception.MovieAlreadyExistException;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.service.MovieService;

@CrossOrigin
@RestController
public class MovieController {

	@Autowired
	private MovieService service;

	private static final String uploadDir = "images";

//	http://localhost:8880/admin/movie/add
	@PostMapping(path = "/admin/movie/add", consumes = "multipart/form-data")
	public ResponseEntity<String> addMovie(@RequestParam("image") MultipartFile image, @RequestParam("movie") String ms)
			throws MovieAlreadyExistException, JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
		Movie m = objectMapper.readValue(ms, Movie.class);
		// Validate the image file
		if (image.isEmpty()) {
			// Handle empty file error
			return ResponseEntity.badRequest().body("Image file is required.");
		}

		// Check the file format
		String fileExtension = StringUtils.getFilenameExtension(image.getOriginalFilename());
		if (!"jpg".equalsIgnoreCase(fileExtension) && !"png".equalsIgnoreCase(fileExtension)) {
			// Handle invalid file format error
			return ResponseEntity.badRequest().body("Only JPG and PNG file formats are supported.");
		}
		int id;
		if ((id = service.save(m, image)) > 0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
					.body("Movie added with id: " + id);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the image file.");
		}
	}

//	http://localhost:8880/admin/movie/update
	@PutMapping(path = "/admin/movie/update", consumes = "application/json")
	public ResponseEntity<String> updateMovie(@RequestBody Movie m) throws MovieNotFoundException {
		int id = service.update(m);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Movie with id: " + id + " updated");
	}

//	http://localhost:8880/movie/all	
	@GetMapping(path = "/movie/all", produces = "application/json")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = service.list();
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController").body(movies);
	}

//	http://localhost:8880/movie/search/id/{id}
	@GetMapping(path = "/movie/search/id/{id}", produces = "application/json")
	public ResponseEntity<Movie> getMovieById(@PathVariable int id) throws MovieNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(service.searchById(id));
	}

//	http://localhost:8880/movie/search/name/{name}
	@GetMapping(path = "/movie/search/name/{name}", produces = "application/json")
	public ResponseEntity<Movie> getMovieByName(@PathVariable String name) throws MovieNotFoundException {
		Movie m = service.searchByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController").body(m);
	}

//	http://localhost:8880/admin/movie/remove/id/{id}
	@DeleteMapping(path = "/admin/movie/remove/id/{id}")
	public ResponseEntity<String> removeMovieById(@PathVariable int id) throws MovieNotFoundException {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Movie with id: " + id + " deleted successfully");
	}

//	http://localhost:8880/admin/movie/remove/name/{name}
	@DeleteMapping(path = "/admin/movie/remove/name/{name}")
	public ResponseEntity<String> removeMovieByName(@PathVariable String name) throws MovieNotFoundException {
		service.removeByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Movie with name: " + name + " deleted successfully");
	}
}
