package com.group01.server_a.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;

    private String refreshToken;
}
