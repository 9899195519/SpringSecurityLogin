package com.dhiraj.login.service;

import com.dhiraj.login.dto.UserRegistrationDto;
import com.dhiraj.login.entity.User;
import com.dhiraj.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;


public User registerNewUser(UserRegistrationDto dto) throws Exception {
    if (userRepository.findUserByUsername(dto.getUsername()).isPresent()) {
        throw new IllegalArgumentException("Username already exists");
    }

    if (!dto.getPassword().equals(dto.getConfirmPassword())) {
        throw new IllegalArgumentException("Passwords do not match");
    }

    User user = new User();
    user.setUsername(dto.getUsername());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setRole("ROLE_USER");
    return userRepository.save(user);
}


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole().replace("ROLE_",""))
            .build();
}
}
