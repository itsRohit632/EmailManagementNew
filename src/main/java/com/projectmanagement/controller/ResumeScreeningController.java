package com.projectmanagement.controller;

import com.projectmanagement.dto.ResumeScreeningDTO;
import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.service.ResumeScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage4")
@CrossOrigin(origins = "*")  // Allow requests from frontend
public class ResumeScreeningController {

    private final ResumeScreeningService service;

    public ResumeScreeningController(ResumeScreeningService service) {
        this.service = service;
    }

    // ✅ POST endpoint to submit resume screening details
    @PostMapping("/submit")
    public ResponseEntity<String> submitScreening(@RequestBody ResumeScreeningDTO dto) {
        try {
            String result = service.submitScreening(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Error during submission: " + e.getMessage());
        }
    }

    // ✅ GET endpoint to fetch screening result by email
    @GetMapping("/result")
    public ResponseEntity<?> getResult(@RequestParam String email) {
        try {
            ResumeScreening screening = service.getResult(email);
            if (screening == null) {
                return ResponseEntity.status(404).body("No screening record found for: " + email);
            }
            return ResponseEntity.ok(screening);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Error retrieving result: " + e.getMessage());
        }
    }
}
