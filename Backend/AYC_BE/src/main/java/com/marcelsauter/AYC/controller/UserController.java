package com.marcelsauter.AYC.controller;

import com.marcelsauter.AYC.model.User;
import com.marcelsauter.AYC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
public class UserController {

    HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserByUsername() {
        return new ResponseEntity<List<User>>((List<User>) this.userService.getAllUser(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public User insertNewUser() {
        return this.userService.insertNewUser();
    }
}
