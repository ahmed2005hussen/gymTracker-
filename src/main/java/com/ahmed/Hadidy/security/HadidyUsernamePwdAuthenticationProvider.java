package com.ahmed.Hadidy.security;

import com.ahmed.Hadidy.user.entity.HadidyUser;
import com.ahmed.Hadidy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HadidyUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pass = authentication.getCredentials().toString();

        HadidyUser user = userRepository.findByUsername(username).orElseThrow(

                () -> new UsernameNotFoundException("User details not found for the user: " + username)
        );

        if (passwordEncoder.matches(pass, user.getPassword())) {

            List<SimpleGrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority(user.getRole().toString()));

            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        } else {
            throw new BadCredentialsException("Invalid password");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
