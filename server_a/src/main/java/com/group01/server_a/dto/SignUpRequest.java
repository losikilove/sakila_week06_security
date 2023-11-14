package com.group01.server_a.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String confirmPassword;
}
