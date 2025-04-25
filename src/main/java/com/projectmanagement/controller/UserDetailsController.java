package com.projectmanagement.controller;

import com.projectmanagement.service.UserExtraDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user-details")
@CrossOrigin(origins = "*")
public class UserDetailsController {

    @Autowired
    private UserExtraDetailsService service;

    @PostMapping("/submit")
    public ResponseEntity<String> submitUserDetails(
            @RequestParam String email,
            @RequestParam String referredBy,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
            @RequestParam String eadType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eadStartDate,
            @RequestParam Boolean hasPriorExperience,
            @RequestParam(required = false) String priorExperienceDetails,
            @RequestParam String programmingLanguage,
            @RequestParam String fileType, // Add fileType parameter
            @RequestParam MultipartFile uploadedFile // Handle a single file upload
    ) {
        try {
            // Process the uploaded file based on fileType
            service.saveExtraDetails(
                    email, referredBy, firstName, lastName, dob, eadType,
                    eadStartDate, hasPriorExperience, priorExperienceDetails,
                    programmingLanguage, uploadedFile, null, null
            );
            return ResponseEntity.ok("Your details have been submitted successfully. One of our executives will contact you shortly.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
