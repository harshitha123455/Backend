package com.ibm.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ibm.entity.Admin;
import com.ibm.pojo.AdminDetails;
import com.ibm.pojo.AdminLogin;
import com.ibm.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

//	http://localhost:8880/admin/add
	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<String> saveAdmin(@RequestBody AdminDetails adminDetails) {
		int id = service.save(adminDetails);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body("Admin added with id: " + id);
	}

//	http://localhost:8880/admin/all
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(service.list());
	}

//	http://localhost:8880/admin/id/{id}
	@GetMapping(path = "/id/{id}", produces = "application/json")
	public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(service.searchById(id));
	}

//	http://localhost:8880/admin/email/{email}
	@GetMapping(path = "/email/{email}", produces = "application/json")
	public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(service.searchByEmail(email));
	}

//	http://localhost:8880/admin/remove/id/{id}
	@DeleteMapping(path = "/remove/id/{id}", produces = "application/json")
	public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body("Admin with id: " + id + " deleted successfully");
	}

//	http://localhost:8880/admin/login
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<Map<String, String>> authenticateAdmin(@RequestBody AdminLogin a) {
		String token = service.authenticate(a.getEmail(), a.getPassword());
		if (token == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(response);
	}

}
