package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/add", consumes = "application/json")
    public int saveAdmin(@RequestBody AdminDetails adminDetails) {
        return service.save(adminDetails);
    }

    @GetMapping (path="/list", produces = "application/json")
    public List<Admin> getAllAdmins() {
        return service.list();
    }

    @GetMapping(path = "/id/{id}", produces = "application/json")
    public Admin getAdminById(@PathVariable int id) {
        return service.searchById(id);
    }

    @GetMapping(path = "/email/{email}", produces = "application/json")
    public Admin getAdminByEmail(@PathVariable String email) {
        return service.searchByEmail(email);
    }

    @DeleteMapping(path = "id/{id}", produces = "application/json")
    public Boolean deleteAdmin(@PathVariable int id) {
        return service.remove(id);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public String authenticateAdmin(@RequestBody AdminLogin a) {
        return service.authenticate(a.getEmail(), a.getPassword());
    }
}
