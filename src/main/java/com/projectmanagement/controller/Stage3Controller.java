package com.projectmanagement.controller;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.Stage3MockTest;
import com.projectmanagement.service.Stage3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stage3")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class Stage3Controller {

    private final Stage3Service stage3Service;

    public Stage3Controller(Stage3Service stage3Service) {
        this.stage3Service = stage3Service;
    }

    /**
     * Submit a mock test record for the given email.
     * Call:
     *   POST /api/stage3/add?email=you@example.com
     * Body JSON:
     *   {
     *     "testName": "Java Mock 3",
     *     "score": 79,
     *     "status": "PASSED"
     *   }
     */
    @PostMapping({"/add", "/submit-score"})
    public ResponseEntity<String> addMockTest(
            @RequestParam("email") String email,
            @RequestBody Stage3MockTest test) {

        String msg = stage3Service.addMockTest(test, email);
        return ResponseEntity.ok(msg);
    }

    /** Get all tests for a user by email (query param) */
    @GetMapping("/tests")
    public ResponseEntity<List<Stage3MockTest>> getTestsByParam(@RequestParam String email) {
        return ResponseEntity.ok(stage3Service.getMockTests(email));
    }

    /** Get all tests for a user by email (path variable) */
    @GetMapping("/tests/{email}")
    public ResponseEntity<List<Stage3MockTest>> getTestsByPath(@PathVariable String email) {
        return ResponseEntity.ok(stage3Service.getMockTests(email));
    }

    /** Get summary for a user by email (query param) */
    @GetMapping("/summary")
    public ResponseEntity<Stage3MockDTO> getSummaryByParam(@RequestParam String email) {
        return ResponseEntity.ok(stage3Service.getMockSummary(email));
    }

    /** Get summary for a user by email (path variable) */
    @GetMapping("/summary/{email}")
    public ResponseEntity<Stage3MockDTO> getSummaryByPath(@PathVariable String email) {
        return ResponseEntity.ok(stage3Service.getMockSummary(email));
    }
}
