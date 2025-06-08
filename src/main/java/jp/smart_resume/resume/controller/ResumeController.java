package jp.smart_resume.resume.controller;

import jp.smart_resume.resume.dto.ResumeRequest;
import jp.smart_resume.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling resume generation requests.
 */
@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    /**
     * Service responsible for generating resume text based on user input.
     * Injected via constructor (enabled by Lombok's {@code @RequiredArgsConstructor}).
     */
    private final ResumeService resumeService;

    /**
     * POST endpoint for generating a resume.
     * @param request the user-provided information required to generate the resume
     * @return HTTP 200 OK with the generated resume text
     */
    @PostMapping
    public ResponseEntity<String> generateResume(@RequestBody ResumeRequest request) {
        String generatedText = resumeService.generateResumeText(request);
        return ResponseEntity.ok(generatedText);
    }
}
