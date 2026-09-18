package com.ahmed.Hadidy.user.service.impl;

import com.ahmed.Hadidy.exception.CompromisedPasswordException;
import com.ahmed.Hadidy.exception.IncorrectPasswordException;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.exception.UsernameAlreadyExistsException;
import com.ahmed.Hadidy.profile.entity.Profile;
import com.ahmed.Hadidy.user.dto.EditPasswordRequest;
import com.ahmed.Hadidy.user.dto.UserRequest;
import com.ahmed.Hadidy.user.entity.HadidyUser;
import com.ahmed.Hadidy.user.repository.UserRepository;
import com.ahmed.Hadidy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompromisedPasswordChecker compromisedPasswordChecker;
    @Override
    public Optional<HadidyUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public HadidyUser registerUser(UserRequest userRequest) {
        HadidyUser u = userRepository.findByUsername(userRequest.getUsername())
                .orElse(null);
        if (u != null) throw new UsernameAlreadyExistsException(userRequest.getUsername());

        CompromisedPasswordDecision decision =
                compromisedPasswordChecker.check(userRequest.getPassword());
        if(decision.isCompromised()){
            throw new CompromisedPasswordException("Choose a strong password") ;
        }
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());

        HadidyUser user = new HadidyUser(userRequest.getUsername(), hashedPassword);
        Profile p = new Profile();
        p.setUser(user);
        user.setProfile(p);
        return userRepository.save(user);
    }

    @Override
    public void changePassword(EditPasswordRequest request, String username) {

        HadidyUser user = userRepository.findByUsername(username)
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
