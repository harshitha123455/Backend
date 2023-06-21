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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.entity.MovieHighlight;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.service.MovieHighlightService;

/**
 * REST controller for handling Movie-Highlight-related operations.
 */
@RestController
@CrossOrigin
public class MovieHighlightController {

	@Autowired
	private MovieHighlightService service;

	/**
	 * End point for adding the movie with the given ID as a highlight with the provided image.
	 * 
	 * Example URL: http://localhost:8880/admin/highlight/add
	 *
	 * @param image the image file of the movie highlight
	 * @param id    the ID of the movie to set as a highlight
	 * @return ResponseEntity with the success message or error
	 * @throws MovieNotFoundException if the movie is not found
	 */
	@PostMapping(path = "/admin/highlight/add", consumes = "multipart/form-data")
	public ResponseEntity<String> setHighlight(@RequestParam("image") MultipartFile image, @RequestParam("id") int id)
			throws MovieNotFoundException {
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
		int mid = service.save(id, image);
		if (mid > 0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieHighlightController")
					.body("Movie with id: " + id + " added as highlight");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the image file.");
		}
	}

	/**
	 * End point for retrieving the current movie highlight.
	 * 
	 * Example URL: http://localhost:8880/movie/highlight/get
	 *
	 * @return ResponseEntity containing the highlighted movie
	 */
	@GetMapping(path = "/movie/highlight/get", produces = "application/json")
	public ResponseEntity<List<MovieHighlight>> getHighlight() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getHighlight());
	}
	
	@DeleteMapping(path = "/admin/movie/highlight/remove/id/{id}")
	public ResponseEntity<String> removeMovieById(@PathVariable int id) {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "HighlightMovieController")
				.body("Highlight Movie with id: " + id + " deleted successfully");
	}
}
