package com.projectmanagement.controller;

import com.projectmanagement.dto.ApplicationsAndInterviewUpdatesDTO;
import com.projectmanagement.model.ApplicationsAndInterviewUpdates;
import com.projectmanagement.service.ApplicationsAndInterviewUpdatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage7")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ApplicationsAndInterviewUpdatesController {

    private final ApplicationsAndInterviewUpdatesService service;

    public ApplicationsAndInterviewUpdatesController(
            ApplicationsAndInterviewUpdatesService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitUpdates(
            @RequestBody ApplicationsAndInterviewUpdatesDTO dto) {
        try {
            String msg = service.submitUpdates(dto);
            return ResponseEntity.ok(msg);
        } catch (IllegalArgumentException e) {
            // validation failure → 400 Bad Request
            return ResponseEntity
                    .badRequest()
                    .body("Validation error: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUpdates(@RequestParam String email) {
        ApplicationsAndInterviewUpdates updates = service.getUpdates(email);
        if (updates == null) {
            return ResponseEntity
                    .status(404)
                    .body("No updates found for: " + email);
        }
        return ResponseEntity.ok(updates);
    }
}
