package com.example.Vue_Uebungsprojekt_BE.controller;

import com.example.Vue_Uebungsprojekt_BE.model.Task;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import com.example.Vue_Uebungsprojekt_BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public Iterable<User> findAllUser() {
        return this.userService.findAllUser();
    }

    @GetMapping("/user-active/{username}/{userIdKeycloak}")
    public ResponseEntity<User> findUserById(@PathVariable("username") String username, @PathVariable("userIdKeycloak") String userIdKeycloak) {
        return ResponseEntity
                .ok()
                .body(this.userService.findUserByKeycloakId(username, userIdKeycloak));
    }

    @GetMapping("/user-assigned-tasks/{userIdKeycloak}")
    public ResponseEntity<Set<Task>> findAssignedTasks(@PathVariable("userIdKeycloak") String userIdKeycloak) {
        return ResponseEntity
                .ok()
                .body(this.userService.findAssignedTasks(userIdKeycloak));
    }

    @PostMapping("/user/upload-profile-picture/{userIdKeycloak}")
    public ResponseEntity<?> uploadUserProfilePicture(@PathVariable("userIdKeycloak") String userIdKeycloak, @RequestParam(value = "image", required = true) MultipartFile profilePicture) throws SQLException, IOException {
        System.out.println("UploadController was called");
        return ResponseEntity
                .ok()
                .body(this.userService.uploadUserProfilePicture(userIdKeycloak, profilePicture));
                // .body(profilePicture);
    }
}
