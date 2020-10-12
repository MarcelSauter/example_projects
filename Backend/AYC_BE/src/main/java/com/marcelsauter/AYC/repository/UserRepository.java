package com.marcelsauter.AYC.repository;

import com.marcelsauter.AYC.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
