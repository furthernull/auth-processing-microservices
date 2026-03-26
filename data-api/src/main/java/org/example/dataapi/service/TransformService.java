package org.example.dataapi.service;

import org.example.dataapi.dto.TransformRequest;
import org.example.dataapi.dto.TransformResponse;

public interface TransformService {

    TransformResponse transform(TransformRequest request);
}
