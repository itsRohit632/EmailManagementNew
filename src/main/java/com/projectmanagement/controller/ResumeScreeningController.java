package com.projectmanagement.controller;

import com.projectmanagement.dto.ResumeScreeningDTO;
import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.service.ResumeScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage4")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ResumeScreeningController {

    private final ResumeScreeningService service;

    public ResumeScreeningController(ResumeScreeningService service) {
        this.service = service;
    }

    // ✅ Submit screening scores
    @PostMapping("/submit")
    public ResponseEntity<String> submitScreening(@RequestBody ResumeScreeningDTO dto) {
        return ResponseEntity.ok(service.submitScreening(dto));
    }

    // ✅ Get screening result by email
    @GetMapping("/result")
    public ResponseEntity<?> getResult(@RequestParam String email) {
        ResumeScreening screening = service.getResult(email);
        if (screening == null) {
            return ResponseEntity.status(404).body("No screening record found for: " + email);
        }
        return ResponseEntity.ok(screening);
    }

    // ✅ Check if a screening exists for the given email (for Stage 6 trigger)
    @GetMapping("/check")
    public ResponseEntity<?> checkResumeScreening(@RequestParam String email) {
        ResumeScreening screening = service.getResult(email);
        if (screening == null) {
            return ResponseEntity.status(404).body("No resume screening record found for: " + email);
        }
        return ResponseEntity.ok(screening);
    }
}
