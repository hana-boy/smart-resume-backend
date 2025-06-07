package jp.smart_resume.resume.service;

import jp.smart_resume.resume.client.OpenAiClient;
import jp.smart_resume.resume.dto.ResumeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final OpenAiClient openAiClient;

    public String generateResumeText(ResumeRequest request) {
        String prompt = buildPrompt(request);
        return openAiClient.callOpenAi(prompt);
    }

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
