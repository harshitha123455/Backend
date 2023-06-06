package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ibm.entity.Admin;
import com.ibm.entity.Movie;
import com.ibm.pojo.AdminDetails;
import com.ibm.pojo.AdminLogin;
import com.ibm.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> saveAdmin(@RequestBody AdminDetails adminDetails) {
    	int id = service.save(adminDetails);
    	return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Admin added with id: " + id);
    }

    @GetMapping (path="/all", produces = "application/json")
    public ResponseEntity<List<Admin>> getAllAdmins() {
    	return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(service.list());
    }

    @GetMapping(path = "/id/{id}", produces = "application/json")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
    	return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(service.searchById(id));
    }

    @GetMapping(path = "/email/{email}", produces = "application/json")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
    	return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(service.searchByEmail(email));
    }

    @DeleteMapping(path = "id/{id}", produces = "application/json")
    public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
    	return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body("Admin with id: " + id + " deleted successfully");
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> authenticateAdmin(@RequestBody AdminLogin a) {
    	return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "MovieController")
				.body(service.authenticate(a.getEmail(), a.getPassword()));
    }
}
