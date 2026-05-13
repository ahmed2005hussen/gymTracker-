package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByGmail(String gmail);

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(Long id );

    User update(Long id, User user);


}
