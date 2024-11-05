package com.proyectos.authservice.commons.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class StackTraceError extends AbstractClass{

    private String className;

    private String method;

    private Integer lineNumber;
}
