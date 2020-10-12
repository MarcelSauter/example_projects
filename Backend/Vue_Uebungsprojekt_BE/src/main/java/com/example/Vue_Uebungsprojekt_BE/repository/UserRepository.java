package com.example.Vue_Uebungsprojekt_BE.repository;

import com.example.Vue_Uebungsprojekt_BE.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserIdKeycloak(String userIdKeycloak);
}
