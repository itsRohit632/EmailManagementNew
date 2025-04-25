package com.projectmanagement.controller;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.MockScore;
import com.projectmanagement.service.Stage3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stage3")
@CrossOrigin(origins = "*")
public class Stage3Controller {

    private final Stage3Service stage3Service;

    public Stage3Controller(Stage3Service stage3Service) {
        this.stage3Service = stage3Service;
    }

    @PostMapping("/submit-score")
    public ResponseEntity<String> addMockScore(@RequestBody MockScore score) {
        return ResponseEntity.ok(stage3Service.addMockScore(score));
    }

    @GetMapping("/mock-scores")  // 🔧 changed to avoid conflict
    public ResponseEntity<List<MockScore>> getScores(@RequestParam String email) {
        return ResponseEntity.ok(stage3Service.getMockScores(email));
    }

    @GetMapping("/summary")
    public ResponseEntity<Stage3MockDTO> getSummary(@RequestParam String email) {
        return ResponseEntity.ok(stage3Service.getMockSummary(email));
    }
}
