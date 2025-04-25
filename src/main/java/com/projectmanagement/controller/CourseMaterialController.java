package com.projectmanagement.controller;

import com.projectmanagement.model.CourseMaterial;
import com.projectmanagement.service.CourseMaterialService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin("*")
public class CourseMaterialController {

    @Autowired
    private CourseMaterialService courseMaterialService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMaterial(
            @RequestParam("file") MultipartFile file,
            @RequestParam("courseName") String courseName,
            @RequestParam("title") String title) {
        try {
            String result = courseMaterialService.uploadMaterial(courseName, title, file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<CourseMaterial>> getMaterialsByUser(@RequestParam String email) {
        return ResponseEntity.ok(courseMaterialService.getMaterialsForUser(email));
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        try {
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
