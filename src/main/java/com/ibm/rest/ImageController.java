package com.ibm.rest;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling image requests.
 */
@RestController
@CrossOrigin
public class ImageController {

	/**
	 * End point for retrieving an image by its filename.
	 *
	 * @param filename the filename of the image
	 * @return ResponseEntity containing the image resource and appropriate headers
	 * @throws MalformedURLException if the file URL is malformed
	 */
	@GetMapping("/images/{filename:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
		// Construct the file path based on the filename
		Path filePath = Paths.get("images/" + filename);

		// Load the file as a resource
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			// Set the appropriate content type
			String contentType = "image/jpg";

			// Return the image with proper headers
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(resource);
		} else {
			// Handle the case when the image does not exist
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * End point for retrieving a highlight image by its filename.
	 *
	 * @param filename the filename of the highlight image
	 * @return ResponseEntity containing the highlight image resource and
	 *         appropriate headers
	 * @throws MalformedURLException if the file URL is malformed
	 */
	@GetMapping("/highlight/{filename:.+}")
	public ResponseEntity<Resource> getHightlight(@PathVariable String filename) throws MalformedURLException {
		// Construct the file path based on the filename
		Path filePath = Paths.get("highlight/" + filename);

		// Load the file as a resource
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			// Set the appropriate content type
			String contentType = "image/jpg";

			// Return the highlight image with proper headers
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(resource);
		} else {
			// Handle the case when the highlight image does not exist
			return ResponseEntity.notFound().build();
		}
	}
}
