package com.codecool.travelish.controller;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/companies")
@CrossOrigin("*")
@PreAuthorize("hasRole('USER') or hasRole('COMPANY')")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/change-password/{companyId}/{password}")
    public ResponseEntity<String> changePassword(@PathVariable Long companyId, @PathVariable String password) {
        companyService.changePassword(companyId, password);
        return ResponseEntity.ok("Password has been changed.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

}
