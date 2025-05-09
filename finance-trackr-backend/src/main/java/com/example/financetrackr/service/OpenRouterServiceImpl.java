package com.example.financetrackr.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenRouterServiceImpl implements OpenRouterService{

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.api.url}")
    private String apiUrl;

    @Value("${openrouter.api.model}")
    private String apiModel;

    @Value("${openrouter.system.prompt}")
    private String systemPrompt;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getChatResponse(String userPrompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Title", "FinanceTrackr");

        String body = """
        {
            "model": "%s",
            "messages": [
                {"role": "system", "content": "%s"},
                {"role": "user", "content": "%s"}
            ]
        }
        """.formatted(apiModel, systemPrompt, userPrompt);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String messageContent = rootNode.path("choices").path(0).path("message").path("content").asText();

            return messageContent;
        } catch (Exception e) {
            return "Sorry, something went wrong: " + e.getMessage();
        }
    }
}
