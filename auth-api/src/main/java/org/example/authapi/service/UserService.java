package org.example.authapi.service;

import org.example.authapi.dto.UserRegistrationRequest;
import org.example.authapi.dto.UserRegistrationResponse;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request);
}
