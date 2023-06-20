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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Screen;
import com.ibm.exception.ScreenAlreadyExistException;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.service.ScreenService;

/**
 * REST controller for handling Screen-related operations.
 */
@RestController
@CrossOrigin
public class ScreenController {

	@Autowired
	private ScreenService service;

	/**
	 * End point for adding a new screen.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/add
	 *
	 * @param s the Screen object to add
	 * @return ResponseEntity with the status and response message
	 * @throws ScreenAlreadyExistException if the screen already exists
	 */
	@PostMapping(path = "/admin/screen/add", consumes = "application/json")
	public ResponseEntity<String> addScreen(@RequestBody Screen s) throws ScreenAlreadyExistException {
		int id = service.save(s);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Screen added with id: " + id);
	}

	/**
	 * End point for updating an existing screen.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/update
	 *
	 * @param s the updated Screen object
	 * @return ResponseEntity with the status and response message
	 * @throws ScreenNotFoundException     if the screen is not found
	 * @throws ScreenAlreadyExistException if the updated screen already exists
	 */
	@PutMapping(path = "/admin/screen/update", consumes = "application/json")
	public ResponseEntity<String> updateScreen(@RequestBody Screen s)
			throws ScreenNotFoundException, ScreenAlreadyExistException {
		int id = service.update(s);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Screen with id: " + id + " updated");
	}

	/**
	 * End point for retrieving all screens.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/all
	 *
	 * @return ResponseEntity containing the list of screens
	 */
	@GetMapping(path = "/admin/screen/all", produces = "application/json")
	public ResponseEntity<List<Screen>> getAllScreens() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body(service.list());
	}

	/**
	 * End point for retrieving a screen by its ID.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/search/id/{id}
	 *
	 * @param id the ID of the screen to retrieve
	 * @return ResponseEntity containing the retrieved screen
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@GetMapping(path = "/admin/screen/search/id/{id}", produces = "application/json")
	public ResponseEntity<Screen> getScreenById(@PathVariable int id) throws ScreenNotFoundException {
		Screen s = service.searchById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController").body(s);
	}

	/**
	 * End point for retrieving a screen by its name.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/search/name/{name}
	 *
	 * @param name the name of the screen to retrieve
	 * @return ResponseEntity containing the retrieved screen
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@GetMapping(path = "/admin/screen/search/name/{name}", produces = "application/json")
	public ResponseEntity<Screen> getScreenByName(@PathVariable String name) throws ScreenNotFoundException {
		Screen s = service.searchByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController").body(s);
	}

	/**
	 * End point for removing a screen by its ID.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/remove/id/{id}
	 *
	 * @param id the ID of the screen to remove
	 * @return ResponseEntity with the status and response message
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@DeleteMapping(path = "/admin/screen/remove/id/{id}")
	public ResponseEntity<String> removeScreenById(@PathVariable int id) throws ScreenNotFoundException {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Movie with id: " + id + " deleted successfully");
	}

	/**
	 * End point for removing a screen by its name.
	 * 
	 * Example URL: http://localhost:8880/admin/screen/remove/name/{name}
	 *
	 * @param name the name of the screen to remove
	 * @return ResponseEntity with the status and response message
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@DeleteMapping(path = "/admin/screen/remove/name/{name}")
	public ResponseEntity<String> removeScreenByName(@PathVariable String name) throws ScreenNotFoundException {
		service.removeByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Movie with name: " + name + " deleted successfully");
	}
}
