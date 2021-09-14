package com.codecool.travelish.controller;


import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin()
@RequestMapping("/dashboard")
public class DashboardController {

    private final CompanyService companyService;

    @Autowired
    public DashboardController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/applicants/{companyId}")
    public ResponseEntity<List<AppUser>> getApplicants(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.findAllUsersByCompany(companyId));
    }

}
