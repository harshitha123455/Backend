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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Screen;
import com.ibm.service.ScreenAlreadyExistException;
import com.ibm.service.ScreenNotFoundException;
import com.ibm.service.ScreenService;

@CrossOrigin
@RestController
@RequestMapping(path = "/screen")
public class ScreenController {

	@Autowired
	private ScreenService service;

//	http://localhost:8880/screen/add
	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<String> addScreen(@RequestBody Screen s) throws ScreenAlreadyExistException {
		int id = service.save(s);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Response from", "ScreenController");
		return ResponseEntity.accepted().headers(headers).body("Screen added with id: " + id);
	}

//	http://localhost:8880/screen/all
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Screen>> getAllScreens() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body(service.list());
	}

//	http://localhost:8880/screen/search/id/{id}
	@GetMapping(path = "/search/id/{id}", produces = "application/json")
	public ResponseEntity<Screen> getScreenById(@PathVariable int id) throws ScreenNotFoundException {
		Screen s = service.searchById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController").body(s);
	}

//	http://localhost:8880/screen/search/name/{name}
	@GetMapping(path = "/search/name/{name}", produces = "application/json")
	public ResponseEntity<Screen> getScreenByName(@PathVariable String name) throws ScreenNotFoundException {
		Screen s = service.searchByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body(s);
	}

//	http://localhost:8880/screen/remove/id/{id}
	@DeleteMapping(path = "/remove/id/{id}")
	public ResponseEntity<String> removeScreenById(@PathVariable int id) throws ScreenNotFoundException {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Movie with id: " + id + " deleted successfully");
	}

//	http://localhost:8880/screen/remove/name/{name}
	@DeleteMapping(path = "/remove/name/{name}")
	public ResponseEntity<String> removeScreenByName(@PathVariable String name) throws ScreenNotFoundException {
		service.removeByName(name);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "ScreenController")
				.body("Movie with name: " + name + " deleted successfully");
	}
}
