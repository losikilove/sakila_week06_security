package com.group01.server_a.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
