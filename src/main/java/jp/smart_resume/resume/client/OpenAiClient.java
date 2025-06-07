package jp.smart_resume.resume.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Component
public class OpenAiClient {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String callOpenAi(String prompt) {
        String uri = "https://api.openai.com/v1/chat/completions";

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o");
        body.put("messages", List.of(
                Map.of("role", "system", "content", "あなたは日本語の職務経歴書を作るエキスパートです。"),
                Map.of("role", "user", "content", prompt)
        ));
        body.put("temperature", 0.7);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(uri, entity, Map.class);
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            return (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");
        } catch (Exception e) {
            log.error("OpenAI API call failed", e);
            return "OpenAI APIとの通信に失敗しました。";
        }
    }
}
