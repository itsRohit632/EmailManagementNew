// src/main/java/com/projectmanagement/controller/Stage3Controller.java
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

    // 1) Submit a mock test record
    @PostMapping({"/add", "/submit-score"})
    public ResponseEntity<String> addMockTest(@RequestBody Stage3MockTest test) {
        return ResponseEntity.ok(stage3Service.addMockTest(test));
    }

    // 2a) Get all tests by query parameter
    //    GET /api/stage3/tests?email=you@example.com
    @GetMapping("/tests")
    public ResponseEntity<List<Stage3MockTest>> getTestsByParam(@RequestParam String email) {
        return ResponseEntity.ok(stage3Service.getMockTests(email));
    }

    // 2b) Get all tests by path variable
    //    GET /api/stage3/tests/{email}
    @GetMapping("/tests/{email}")
    public ResponseEntity<List<Stage3MockTest>> getTestsByPath(@PathVariable String email) {
        return ResponseEntity.ok(stage3Service.getMockTests(email));
    }

    // 3a) Get summary by query parameter
    //    GET /api/stage3/summary?email=you@example.com
    @GetMapping("/summary")
    public ResponseEntity<Stage3MockDTO> getSummaryByParam(@RequestParam String email) {
        return ResponseEntity.ok(stage3Service.getMockSummary(email));
    }

    // 3b) Get summary by path variable
    //    GET /api/stage3/summary/{email}
    @GetMapping("/summary/{email}")
    public ResponseEntity<Stage3MockDTO> getSummaryByPath(@PathVariable String email) {
        return ResponseEntity.ok(stage3Service.getMockSummary(email));
    }
}
