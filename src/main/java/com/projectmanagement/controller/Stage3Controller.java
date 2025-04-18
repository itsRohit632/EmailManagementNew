package com.projectmanagement.controller;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.Stage3MockTest;
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

    @PostMapping("/add")
    public ResponseEntity<String> addMockTest(@RequestBody Stage3MockDTO dto) {
        return ResponseEntity.ok(stage3Service.addMockTest(dto));
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<List<Stage3MockTest>> getMocks(@PathVariable String email) {
        return ResponseEntity.ok(stage3Service.getMocksByEmail(email));
    }
}
