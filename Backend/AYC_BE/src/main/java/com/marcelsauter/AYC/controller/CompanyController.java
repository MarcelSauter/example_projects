package com.marcelsauter.AYC.controller;

import com.marcelsauter.AYC.model.Company;
import com.marcelsauter.AYC.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company")
    public Iterable<Company> getAllCompany() {
        return this.companyService.getAllCompany();
    }

    @PostMapping("/company/create")
    public Company insertNewCompany() {
        return this.companyService.insertNewCompany();
    }
}
