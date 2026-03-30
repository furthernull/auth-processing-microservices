package org.example.authapi.service;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.UserRegistrationRequest;
import org.example.authapi.dto.UserRegistrationResponse;
import org.example.authapi.exception.RegistrationException;
import org.example.authapi.model.User;
import org.example.authapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RegistrationException("User already exists");
        }
        User user = new User();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return new UserRegistrationResponse(user.getId().toString(), user.getEmail());
    }
}
