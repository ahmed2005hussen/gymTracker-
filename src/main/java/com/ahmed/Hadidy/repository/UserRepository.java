package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    public Optional<User> findByUsername(String username);

}
