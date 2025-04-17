package com.projectmanagement.controller;

import com.projectmanagement.dto.Stage2DTO;
import com.projectmanagement.model.Stage2Details;
import com.projectmanagement.service.Stage2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage2")
@CrossOrigin(origins = "*")
public class Stage2Controller {

    private final Stage2Service stage2Service;

    public Stage2Controller(Stage2Service stage2Service) {
        this.stage2Service = stage2Service;
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignCourse(@RequestBody Stage2DTO dto) {
        return ResponseEntity.ok(stage2Service.assignCourse(dto));
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<Stage2Details> getDetails(@PathVariable String email) {
        return ResponseEntity.ok(stage2Service.getStage2DetailsByEmail(email));
    }
}
