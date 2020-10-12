package com.marcelsauter.AYC.service;

import com.marcelsauter.AYC.model.Company;
import com.marcelsauter.AYC.model.User;
import com.marcelsauter.AYC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public User insertNewUser() {
        Company company = new Company();
        User user = new User();

        company.setCompanyName("proQrent");
        company.setCompanyId((long) 1);
        user.setCompany(company);

        user.setEmail("msauter@proqrent.de");
        user.setFirstName("Marcel");
        user.setLastName("Sauter");
        user.setJob("Junior Full-Stack-Developer");

        return this.userRepository.save(user);
    }
}
