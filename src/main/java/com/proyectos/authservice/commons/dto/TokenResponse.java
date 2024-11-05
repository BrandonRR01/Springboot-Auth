package com.proyectos.authservice.commons.dto;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TokenResponse {

    private String accessToken;
}
