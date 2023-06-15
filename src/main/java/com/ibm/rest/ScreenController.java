package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

@CrossOrigin
@RestController
public class ScreenController {

	@Autowired
	private ScreenService service;

//	http://localhost:8880/admin/screen/add
	@PostMapping(path = "/admin/screen/add", consumes = "application/json")
	public ResponseEntity<String> addScreen(@RequestBody Screen s) throws ScreenAlreadyExistException {
		int id = service.save(s);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Screen added with id: " + id);
	}
	
//	http://localhost:8880/admin/screen/update
	@PutMapping(path = "/admin/screen/update", consumes = "application/json")
	public ResponseEntity<String> updateScreen(@RequestBody Screen s) throws ScreenNotFoundException, ScreenAlreadyExistException {
		int id = service.update(s);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Screen with id: " + id + " updated");
	}

//	http://localhost:8880/admin/screen/all
	@GetMapping(path = "/admin/screen/all", produces = "application/json")
	public ResponseEntity<List<Screen>> getAllScreens() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body(service.list());
	}

//	http://localhost:8880/admin/screen/search/id/{id}
	@GetMapping(path = "/admin/screen/search/id/{id}", produces = "application/json")
	public ResponseEntity<Screen> getScreenById(@PathVariable int id) throws ScreenNotFoundException {
		Screen s = service.searchById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController").body(s);
	}

//	http://localhost:8880/admin/screen/search/name/{name}
	@GetMapping(path = "/admin/screen/search/name/{name}", produces = "application/json")
	public ResponseEntity<Screen> getScreenByName(@PathVariable String name) throws ScreenNotFoundException {
		Screen s = service.searchByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body(s);
	}

//	http://localhost:8880/admin/screen/remove/id/{id}
	@DeleteMapping(path = "/admin/screen/remove/id/{id}")
	public ResponseEntity<String> removeScreenById(@PathVariable int id) throws ScreenNotFoundException {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Movie with id: " + id + " deleted successfully");
	}

//	http://localhost:8880/admin/screen/remove/name/{name}
	@DeleteMapping(path = "/admin/screen/remove/name/{name}")
	public ResponseEntity<String> removeScreenByName(@PathVariable String name) throws ScreenNotFoundException {
		service.removeByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Movie with name: " + name + " deleted successfully");
	}
}
