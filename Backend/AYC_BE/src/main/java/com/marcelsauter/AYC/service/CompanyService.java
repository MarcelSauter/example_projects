package com.marcelsauter.AYC.service;

import com.marcelsauter.AYC.model.Company;
import com.marcelsauter.AYC.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> getAllCompany() {
        return this.companyRepository.findAll();
    }

    public Company insertNewCompany() {
        Company company = new Company();
        company.setCompanyName("proQrent");
        return this.companyRepository.save(company);
    }
}
