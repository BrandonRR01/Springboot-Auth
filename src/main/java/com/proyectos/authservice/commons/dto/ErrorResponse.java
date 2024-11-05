package com.proyectos.authservice.commons.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ErrorResponse {

    private Integer codeStatus;

    private String message;
}
