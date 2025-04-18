package com.projectmanagement.controller;

import com.projectmanagement.dto.ResumeScreeningDTO;
import com.projectmanagement.model.ResumeScreening;
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

    @PostMapping("/submit")
    public ResponseEntity<String> submitScreening(@RequestBody ResumeScreeningDTO dto) {
        return ResponseEntity.ok(service.submitScreening(dto));
    }

    @GetMapping("/result")
    public ResponseEntity<?> getResult(@RequestParam String email) {
        ResumeScreening screening = service.getResult(email);
        if (screening == null) {
            return ResponseEntity.status(404).body("No screening record found for: " + email);
        }
        return ResponseEntity.ok(screening);
    }
}
