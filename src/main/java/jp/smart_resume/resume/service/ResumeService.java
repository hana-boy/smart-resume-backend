package jp.smart_resume.resume.service;

import jp.smart_resume.resume.client.OpenAiClient;
import jp.smart_resume.resume.dto.ResumeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for generating resume text based on user input.
 */
@Service
@RequiredArgsConstructor
public class ResumeService {

    /**
     * Client component that communicates with the OpenAI API.
     */
    private final OpenAiClient openAiClient;

    /**
     * Generates a Japanese resume text based on the given request data.
     *
     * @param request the user-provided resume information
     * @return the generated resume content as a plain text string
     */
    public String generateResumeText(ResumeRequest request) {
        String prompt = buildPrompt(request);
        return openAiClient.callOpenAi(prompt);
    }

    /**
     * Builds a prompt string in Japanese using the input resume data.
     * This prompt is used to instruct the AI how to compose the resume.
     *
     * @param request the input data used to construct the resume prompt
     * @return the complete prompt string to send to OpenAI
     */
    private String buildPrompt(ResumeRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("以下の情報をもとに日本語で職務経歴書を作成してください。\n");
        sb.append("氏名: ").append(request.getBasicInfo().getName()).append("\n");
        sb.append("職務要約: ").append(request.getSummary().getOverview()).append("\n");
        for (String point : request.getSummary().getHighlights()) {
            sb.append("・").append(point).append("\n");
        }
        return sb.toString();
    }
}
