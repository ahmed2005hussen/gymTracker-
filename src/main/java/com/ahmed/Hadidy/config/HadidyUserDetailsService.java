package com.ahmed.Hadidy.config;

import org.springframework.security.core.userdetails.User;
import com.ahmed.Hadidy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HadidyUserDetailsService implements UserDetailsService {

    final private UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.ahmed.Hadidy.entity.User
                user = userRepository.findByUsername(username).orElseThrow(
                ()-> new RuntimeException("not found user with username: " + username)
        );

        List<GrantedAuthority> g = List.of(new SimpleGrantedAuthority(user.getRole().toString()));

        return new User(user.getUsername() , user.getPassword() , g);

    }
}
