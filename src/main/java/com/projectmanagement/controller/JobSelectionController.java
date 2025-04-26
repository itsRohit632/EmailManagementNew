package com.projectmanagement.controller;

import com.projectmanagement.dto.JobSelectionDTO;
import com.projectmanagement.model.JobSelection;
import com.projectmanagement.service.JobSelectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage8")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class JobSelectionController {

    private final JobSelectionService service;

    public JobSelectionController(JobSelectionService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitSelection(@RequestBody JobSelectionDTO dto) {
        try {
            String msg = service.submitSelection(dto);
            return ResponseEntity.ok(msg);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Validation error: " + ex.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getSelection(@RequestParam String email) {
        JobSelection sel = service.getSelection(email);
        if (sel == null) {
            return ResponseEntity.status(404)
                    .body("No job selection found for: " + email);
        }
        return ResponseEntity.ok(sel);
    }
}
