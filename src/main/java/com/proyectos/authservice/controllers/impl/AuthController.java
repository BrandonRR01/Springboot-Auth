package com.proyectos.authservice.controllers.impl;

import com.proyectos.authservice.commons.dto.TokenResponse;
import com.proyectos.authservice.commons.dto.UserRequest;
import com.proyectos.authservice.controllers.AuthApi;
import com.proyectos.authservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> login(UserRequest userRequest) {
        return ResponseEntity.ok(authService.loginUser(userRequest));
    }

    @Override
    public ResponseEntity<String> getUser(String userId) {
        return ResponseEntity.ok(userId);
    }


}
