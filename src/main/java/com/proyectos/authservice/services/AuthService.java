package com.proyectos.authservice.services;

import com.proyectos.authservice.commons.dto.TokenResponse;
import com.proyectos.authservice.commons.dto.UserRequest;

public interface AuthService {

    TokenResponse createUser(UserRequest userRequest);

    TokenResponse loginUser(UserRequest userRequest);
}
