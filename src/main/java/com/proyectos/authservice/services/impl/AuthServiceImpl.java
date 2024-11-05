package com.proyectos.authservice.services.impl;

import com.proyectos.authservice.commons.dto.TokenResponse;
import com.proyectos.authservice.commons.dto.UserRequest;
import com.proyectos.authservice.commons.enums.Role;
import com.proyectos.authservice.commons.exceptions.AuthException;
import com.proyectos.authservice.entities.User;
import com.proyectos.authservice.repositories.UserRepository;
import com.proyectos.authservice.services.AuthService;
import com.proyectos.authservice.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse createUser(UserRequest userRequest) {

        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new AuthException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user"));
    }

    @Override
    public TokenResponse loginUser(UserRequest userRequest) {
        return userRepository.findByEmail(userRequest.getEmail())
                .filter(userFiltered -> passwordEncoder.matches(userRequest.getPassword(), userFiltered.getPassword()))
                .map(user -> jwtService.generateToken(user.getId()))
                .orElseThrow(() -> new AuthException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
    }

    private User mapToEntity(UserRequest userRequest){
        return User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.USER.name())
                .build();
    }

}
