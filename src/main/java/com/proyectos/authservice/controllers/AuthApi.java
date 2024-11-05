package com.proyectos.authservice.controllers;


import com.proyectos.authservice.commons.constants.ApiPathVariables;
import com.proyectos.authservice.commons.dto.TokenResponse;
import com.proyectos.authservice.commons.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathVariables.V1_ROUTE + ApiPathVariables.AUTH_ROUTE)
public interface AuthApi {

    @PostMapping("/register")
    ResponseEntity<TokenResponse> createUser(@RequestBody @Valid UserRequest userRequest);

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@RequestBody @Valid UserRequest userRequest);


    @GetMapping
    ResponseEntity<String> getUser(@RequestAttribute(name = "X-User-Id") String userId);


}
