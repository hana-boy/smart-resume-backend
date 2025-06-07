package jp.smart_resume.resume.controller;

import jp.smart_resume.resume.dto.ResumeRequest;
import jp.smart_resume.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<String> generateResume(@RequestBody ResumeRequest request) {
        String generatedText = resumeService.generateResumeText(request);
        return ResponseEntity.ok(generatedText);
    }
}
