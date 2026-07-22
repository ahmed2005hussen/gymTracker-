package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.dto.request.EditPasswordRequest;
import com.ahmed.Hadidy.dto.request.UserRequest;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.exceptions.IncorrectPasswordException;
import com.ahmed.Hadidy.exceptions.UserNotFoundException;
import com.ahmed.Hadidy.exceptions.UsernameAlreadyExistsException;
import com.ahmed.Hadidy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_encodesPasswordAndSavesNewUser() {
        UserRequest request = new UserRequest();
        request.setUsername("ahmed");
        request.setPassword("password123");
        User savedUser = new User("ahmed", "encoded-password");

        when(userRepository.findByUsername("ahmed")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(request);

        assertSame(savedUser, result);
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_throwsWhenUsernameAlreadyExists() {
        UserRequest request = new UserRequest();
        request.setUsername("ahmed");
        request.setPassword("password123");
        when(userRepository.findByUsername("ahmed"))
                .thenReturn(Optional.of(new User("ahmed", "existing-password")));

        assertThrows(UsernameAlreadyExistsException.class,
                () -> userService.registerUser(request));

        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void changePassword_encodesAndSavesWhenOldPasswordMatches() {
        User user = new User("ahmed", "old-encoded-password");
        EditPasswordRequest request = new EditPasswordRequest("old-password", "new-password");
        when(userRepository.findByUsername("ahmed")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("old-password", "old-encoded-password")).thenReturn(true);
        when(passwordEncoder.encode("new-password")).thenReturn("new-encoded-password");

        userService.changePassword(request, "ahmed");

        assertEquals("new-encoded-password", user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void changePassword_throwsWhenOldPasswordDoesNotMatch() {
        User user = new User("ahmed", "old-encoded-password");
        EditPasswordRequest request = new EditPasswordRequest("wrong-password", "new-password");
        when(userRepository.findByUsername("ahmed")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "old-encoded-password")).thenReturn(false);

        assertThrows(IncorrectPasswordException.class,
                () -> userService.changePassword(request, "ahmed"));

        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void changePassword_throwsWhenUserDoesNotExist() {
        EditPasswordRequest request = new EditPasswordRequest("old-password", "new-password");
        when(userRepository.findByUsername("missing")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.changePassword(request, "missing"));

        verify(passwordEncoder, never()).matches(any(), any());
        verify(userRepository, never()).save(any(User.class));
    }
}
