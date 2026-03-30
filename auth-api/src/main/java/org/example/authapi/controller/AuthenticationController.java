package org.example.authapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.UserLoginRequest;
import org.example.authapi.dto.UserLoginResponse;
import org.example.authapi.dto.UserRegistrationRequest;
import org.example.authapi.dto.UserRegistrationResponse;
import org.example.authapi.exception.RegistrationException;
import org.example.authapi.security.AuthenticationService;
import org.example.authapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody @Valid UserLoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponse register(
            @RequestBody @Valid UserRegistrationRequest request) throws RegistrationException {
        return userService.register(request);
    }
}
