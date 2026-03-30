package org.example.authapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.ProcessRequest;
import org.example.authapi.dto.ProcessResponse;
import org.example.authapi.model.User;
import org.example.authapi.service.ProcessingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/process")
public class ProcessingController {
    private final ProcessingService processingService;

    @PostMapping
    public ProcessResponse process(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ProcessRequest request
    ){
        return processingService.process(user.getEmail(), request);
    }
}
