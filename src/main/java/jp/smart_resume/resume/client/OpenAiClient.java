package jp.smart_resume.resume.client;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenAiClient {

    private final OpenAIClient client;

    /**
     * Constructor: initializes OpenAIClient with API key configured in application.properties/yml.
     *
     * @param apiKey the OpenAI API key
     */
    public OpenAiClient(@Value("${openai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)  // ここで認証キーを正しくセット
                .build();
    }

    /**
     * Sends the provided prompt to the GPT‑4.1‑nano model and returns the response content.
     *
     * @param prompt the Japanese prompt for generating a resume
     * @return the generated resume content, or a fallback error message
     */
    public String callOpenAi(String prompt) {
        try {
            var params = ChatCompletionCreateParams.builder()
                    .model(ChatModel.GPT_4_1_NANO)
                    .addSystemMessage("あなたは日本語の職務経歴書を作るエキスパートです。")
                    .addUserMessage(prompt)
                    .temperature(0.7)
                    .build();

            ChatCompletion result = client.chat().completions().create(params);

            return result.choices()
                    .get(0)
                    .message()
                    .content()
                    .orElse("OpenAI API returned no content.");
        } catch (Exception e) {
            log.error("OpenAI API call failed", e);
            return "OpenAI APIとの通信に失敗しました。";
        }
    }
}
