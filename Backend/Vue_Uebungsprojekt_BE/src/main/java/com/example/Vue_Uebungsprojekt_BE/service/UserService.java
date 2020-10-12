package com.example.Vue_Uebungsprojekt_BE.service;

import com.example.Vue_Uebungsprojekt_BE.model.Task;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import com.example.Vue_Uebungsprojekt_BE.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUser() {
        return this.userRepository.findAll();
    }

    public User findUserByKeycloakId(String username, String userIdKeycloak) {
        Optional<User> user = this.userRepository.findByUserIdKeycloak(userIdKeycloak);
        return user.orElseGet(() -> this.userRepository.save(new User(userIdKeycloak, username)));
    }

    public User uploadUserProfilePicture(String userIdKeycloak, MultipartFile profilePicture) throws SQLException, IOException {
        Optional<User> user = this.userRepository.findByUserIdKeycloak(userIdKeycloak);
        user.get().setProfilePicture(IOUtils.toByteArray(profilePicture.getInputStream()));
        return this.userRepository.save(user.get());
    }

    public Set<Task> findAssignedTasks(String userIdKeycloak) {
        Optional<User> user = this.userRepository.findByUserIdKeycloak(userIdKeycloak);
        return user.get().getTasks();
    }
}
