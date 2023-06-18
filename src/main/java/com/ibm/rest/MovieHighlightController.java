package com.ibm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ibm.entity.Movie;
import com.ibm.entity.MovieHighlight;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.service.MovieHighlightService;

@RestController
@CrossOrigin
public class MovieHighlightController {

	@Autowired
	private MovieHighlightService service;


//	http://localhost:8880/admin/highlight/set
	@PostMapping(path = "/admin/highlight/set", consumes = "multipart/form-data")
	public ResponseEntity<String> setHighlight(@RequestParam("image") MultipartFile image,
			@RequestParam("id") int id) throws MovieNotFoundException {
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
		int mid;
		if ((mid = service.save(id, image)) > 0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieHighlightController")
					.body("Movie with id: " + mid + " set as highlight");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the image file.");
		}
	}
	
//	http://localhost:8880/movie/highlight/get
	@GetMapping(path= "/movie/highlight/get", produces = "application/json")
	public ResponseEntity<Movie> getHighlight(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getHighlight());
	}
}

