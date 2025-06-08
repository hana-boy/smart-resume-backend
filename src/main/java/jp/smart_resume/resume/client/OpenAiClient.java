package jp.smart_resume.resume.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Client component responsible for communicating with the OpenAI API.
 */
@Slf4j
@Component
public class OpenAiClient {

    /**
     * The API key used to authenticate with the OpenAI API.
     * It is injected from the application properties.
     */
    @Value("${openai.api.key}")
    private String apiKey;

    /**
     * HTTP client used to send requests to the OpenAI API.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Calls the OpenAI Chat Completion API using the provided prompt and returns the generated text.
     *
     * @param prompt the text prompt to send to the OpenAI model
     * @return the generated text from the API response, or an error message if the request fails
     */
    public String callOpenAi(String prompt) {
        String uri = "https://api.openai.com/v1/chat/completions";

        // Construct the request body
        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4.1-nano-2025-04-14");
        body.put("messages", List.of(
                Map.of("role", "system", "content", "あなたは日本語の職務経歴書を作るエキスパートです。"),
                Map.of("role", "user", "content", prompt)
        ));
        body.put("temperature", 0.7);

        // Set up HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // Combine headers and body into an HTTP entity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            // Send the POST request to the OpenAI API
            ResponseEntity<Map> response = restTemplate.postForEntity(uri, entity, Map.class);

            // Parse the response to extract the generated content
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            return (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");

        } catch (Exception e) {
            // Log the error and return a fallback message
            log.error("OpenAI API call failed", e);
            return "OpenAI APIとの通信に失敗しました。";
        }
    }
}
