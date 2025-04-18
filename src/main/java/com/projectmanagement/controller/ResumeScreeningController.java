package com.projectmanagement.controller;

import com.projectmanagement.dto.ResumeScreeningDTO;
import com.projectmanagement.service.ResumeScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage4")
@CrossOrigin(origins = "*")
public class ResumeScreeningController {

    private final ResumeScreeningService service;

    public ResumeScreeningController(ResumeScreeningService service) {
        this.service = service;
    }

    // ✅ Submit resume screening decision
    @PostMapping("/submit")
    public ResponseEntity<String> submitResumeScreening(@RequestBody ResumeScreeningDTO dto) {
        try {
            String result = service.submitScreening(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // ✅ Check if the user has passed resume screening
    @GetMapping("/check")
    public ResponseEntity<String> checkScreening(@RequestParam String email) {
        try {
            String result = service.checkScreening(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
