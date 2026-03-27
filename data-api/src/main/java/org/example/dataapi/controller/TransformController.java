package org.example.dataapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dataapi.dto.TransformRequest;
import org.example.dataapi.dto.TransformResponse;
import org.example.dataapi.exception.InvalidInternalTokenException;
import org.example.dataapi.service.TransformService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transform")
@RequiredArgsConstructor
public class TransformController {
    private final TransformService transformService;

    @Value("${app.internal-token}")
    private String internalToken;

    @PostMapping
    public TransformResponse transform(
            @RequestHeader("X-Internal-Token") String token,
            @Valid @RequestBody TransformRequest request
    ) {
        if (!internalToken.equals(token)) {
            throw new InvalidInternalTokenException("Invalid internal token");
        }
        return transformService.transform(request);
    }
}
