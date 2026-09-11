package com.ahmed.Hadidy.user.repository;

import com.ahmed.Hadidy.user.entity.HadidyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<HadidyUser,Long> {

    public Optional<HadidyUser> findByUsername(String username);

}
