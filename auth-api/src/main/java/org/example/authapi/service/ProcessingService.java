package org.example.authapi.service;

import org.example.authapi.dto.ProcessRequest;
import org.example.authapi.dto.ProcessResponse;
import org.example.authapi.model.User;

public interface ProcessingService {
    ProcessResponse process(User user, ProcessRequest request);
}
