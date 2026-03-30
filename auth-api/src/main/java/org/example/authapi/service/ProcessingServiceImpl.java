package org.example.authapi.service;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.DataApiResponse;
import org.example.authapi.dto.ProcessRequest;
import org.example.authapi.dto.ProcessResponse;
import org.example.authapi.exception.UserNotFoundException;
import org.example.authapi.model.ProcessingLog;
import org.example.authapi.model.User;
import org.example.authapi.repository.ProcessingLogRepository;
import org.example.authapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ProcessingServiceImpl implements ProcessingService {
    private final UserRepository userRepository;
    private final ProcessingLogRepository processingLogRepository;
    private final RestTemplate restTemplate;

    @Value("${app.data-api-url}")
    private String dataApiUrl;

    @Value("${app.internal-token}")
    private String internalToken;

    @Override
    public ProcessResponse process(String email, ProcessRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Can't find user: " + email));

        DataApiResponse dataApiResponse = callDataApi(request.text());
        saveLog(user, request.text(), dataApiResponse.result());

        return new ProcessResponse(dataApiResponse.result());
    }

    private DataApiResponse callDataApi(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Internal-Token", internalToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<Map<String, String>> entity  =
                new HttpEntity<>(Map.of("text", text), headers);

        return restTemplate.postForObject(
                dataApiUrl + "/api/transform",
                entity,
                DataApiResponse.class
        );
    }

    private void saveLog(User user, String input, String output) {
        ProcessingLog log = new ProcessingLog();
        log.setUser(user);
        log.setInputText(input);
        log.setOutputText(output);
        log.setCreatedAt(LocalDateTime.now());
        processingLogRepository.save(log);
    }
}
