package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByGmail(String gmail) {
        return Optional.of(userRepository.findByGmail(gmail)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, User user) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        u.setUsername(user.getUsername());
        u.setGmail(user.getGmail());
        u.setRole(user.getRole());
        u.setActive(user.isActive());

        return userRepository.save(u);
    }
}