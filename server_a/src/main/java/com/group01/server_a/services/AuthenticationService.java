package com.group01.server_a.services;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group01.server_a.dto.SignInRequest;
import com.group01.server_a.dto.SignUpRequest;
import com.group01.server_a.dto.JwtAuthenticationResponse;
import com.group01.server_a.dto.RefreshTokenRequest;
import com.group01.server_a.models.User;
import com.group01.server_a.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            return null;
        }

        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        var user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByUsername(username).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }

        return null;
    }
}
