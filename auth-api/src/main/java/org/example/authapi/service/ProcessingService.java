package org.example.authapi.service;

import org.example.authapi.dto.ProcessRequest;
import org.example.authapi.dto.ProcessResponse;

public interface ProcessingService {
    ProcessResponse process(String email, ProcessRequest request);
}
