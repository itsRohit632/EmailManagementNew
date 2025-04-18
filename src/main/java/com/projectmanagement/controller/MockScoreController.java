package com.projectmanagement.controller;

import com.projectmanagement.model.MockScore;
import com.projectmanagement.service.MockScoreService;
import com.projectmanagement.dto.MockScoreSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stage3")
@CrossOrigin(origins = "*")
public class MockScoreController {

    @Autowired
    private MockScoreService service;

    // ✅ Submit a single mock score
    @PostMapping("/score")
    public ResponseEntity<String> submitScore(@RequestBody MockScore score) {
        return ResponseEntity.ok(service.saveScore(score));
    }

    // ✅ Get all scores for a student (unique mapping)
    @GetMapping("/scores")
    public ResponseEntity<List<MockScore>> getAllScores(@RequestParam String email) {
        return ResponseEntity.ok(service.getAllScores(email));
    }

    // ✅ Get average + list of scores (another unique mapping)
    @GetMapping("/scores/summary")
    public ResponseEntity<MockScoreSummary> getAllScoresWithAvg(@RequestParam String email) {
        return ResponseEntity.ok(service.getScoreSummary(email));
    }
}
