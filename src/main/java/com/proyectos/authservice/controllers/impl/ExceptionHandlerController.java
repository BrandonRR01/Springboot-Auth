package com.proyectos.authservice.controllers.impl;

import com.proyectos.authservice.commons.dto.ErrorResponse;
import com.proyectos.authservice.commons.exceptions.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = {AuthException.class})
    ResponseEntity<ErrorResponse> handleGameError(AuthException exception){

        log.error("StackTraceError: {}, ", exception.getStackTraceError(), exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .codeStatus(exception.getHttpStatus().value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }
}
