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
import com.ibm.pojo.AdminLogin;
import com.ibm.service.AdminService;

/**
 * REST controller for handling Admin-related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	/**
	 * Endpoint for adding a new admin.
	 *
	 * Example URL: http://localhost:8880/admin/add
	 * 
	 * @param admin the admin object to be added
	 * @return ResponseEntity containing the response message and HTTP status
	 */
	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<String> saveAdmin(@RequestBody Admin admin) {
		int id = service.save(admin);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body("Admin added with id: " + id);
	}

	/**
	 * Endpoint for retrieving all admins.
	 *
	 * Example URL: http://localhost:8880/admin/all
	 * 
	 * @return ResponseEntity containing the list of all admins and HTTP status
	 */
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(service.list());
	}

	/**
	 * Endpoint for retrieving an admin by ID.
	 *
	 * Example URL: http://localhost:8880/admin/id/{id}
	 * 
	 * @param id the ID of the admin
	 * @return ResponseEntity containing the admin object and HTTP status
	 */
	@GetMapping(path = "/id/{id}", produces = "application/json")
	public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(service.searchById(id));
	}

	/**
	 * Endpoint for retrieving an admin by email.
	 *
	 * Example URL: http://localhost:8880/admin/email/{email}
	 * 
	 * @param email the email of the admin
	 * @return ResponseEntity containing the admin object and HTTP status
	 */
	@GetMapping(path = "/email/{email}", produces = "application/json")
	public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body(service.searchByEmail(email));
	}

	/**
	 * Endpoint for deleting an admin by ID.
	 *
	 * Example URL: http://localhost:8880/admin/remove/id/{id}
	 * 
	 * @param id the ID of the admin to be deleted
	 * @return ResponseEntity containing the response message and HTTP status
	 */
	@DeleteMapping(path = "/remove/id/{id}", produces = "application/json")
	public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController")
				.body("Admin with id: " + id + " deleted successfully");
	}

	/**
	 * Endpoint for authenticating an admin.
	 *
	 * Example URL: http://localhost:8880/admin/login
	 * 
	 * @param a the AdminLogin object containing the email and password
	 * @return ResponseEntity containing the authentication token or unauthorized
	 *         status
	 */
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<Map<String, String>> authenticateAdmin(@RequestBody AdminLogin a) {
		String token = service.authenticate(a.getEmail(), a.getPassword());
		if (token == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "AdminController").body(response);
	}

}
