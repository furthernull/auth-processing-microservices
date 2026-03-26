package org.example.dataapi.service;

import org.example.dataapi.dto.TransformRequest;
import org.example.dataapi.dto.TransformResponse;
import org.springframework.stereotype.Service;

@Service
public class TransformServiceImpl implements TransformService {
    @Override
    public TransformResponse transform(TransformRequest request) {
        String transformed = new StringBuilder(request.text())
                .reverse()
                .toString()
                .toUpperCase();
        return new TransformResponse(transformed);
    }
}
