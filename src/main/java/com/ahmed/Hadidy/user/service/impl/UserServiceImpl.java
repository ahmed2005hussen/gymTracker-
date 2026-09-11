package com.ahmed.Hadidy.user.service.impl;

import com.ahmed.Hadidy.user.dto.EditPasswordRequest;
import com.ahmed.Hadidy.user.dto.UserRequest;
import com.ahmed.Hadidy.profile.entity.Profile;
import com.ahmed.Hadidy.user.entity.User;
import com.ahmed.Hadidy.exception.IncorrectPasswordException;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.exception.UsernameAlreadyExistsException;
import com.ahmed.Hadidy.user.repository.UserRepository;
import com.ahmed.Hadidy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User registerUser(UserRequest userRequest) {
        User u = userRepository.findByUsername(userRequest.getUsername())
                .orElse(null);
        if (u != null) throw new UsernameAlreadyExistsException(userRequest.getUsername());
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());

        User user = new User(userRequest.getUsername(), hashedPassword);
        Profile p = new Profile();
        p.setUser(user);
        user.setProfile(p);

        return userRepository.save(user);

    }

    @Override
    public void changePassword(EditPasswordRequest request, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(username)
                );
        if (passwordEncoder.matches(request.getOldPass(), user.getPassword())) {
            String newEncodedPassword = passwordEncoder.encode(request.getNewPass());
            user.setPassword(newEncodedPassword);
            userRepository.save(user);
            return;
        }
        throw new IncorrectPasswordException();

    }

}
