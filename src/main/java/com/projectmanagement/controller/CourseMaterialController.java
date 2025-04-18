package com.projectmanagement.controller;

import com.projectmanagement.model.CourseMaterial;
import com.projectmanagement.service.CourseMaterialService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin("*")
public class CourseMaterialController {

    @Autowired
    private CourseMaterialService courseMaterialService;

    // ✅ Already implemented
    @GetMapping("/user")
    public ResponseEntity<?> getMaterialsByUser(@RequestParam String email) {
        return ResponseEntity.ok(courseMaterialService.getMaterialsForUser(email));
    }

    // ✅ New download endpoint
    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        try {
            // Absolute file path from saved location
            String basePath = System.getProperty("user.dir") + "/uploads/materials/";
            File file = new File(basePath + fileName);

            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.");
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to download file.");
        }
    }
}
