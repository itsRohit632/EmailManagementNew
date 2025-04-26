package com.projectmanagement.controller;

import com.projectmanagement.service.UserExtraDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User; // Spring Security User class
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/user-details")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserDetailsController {

  private final UserExtraDetailsService service;

  public UserDetailsController(UserExtraDetailsService service) {
    this.service = service;
  }

  @PostMapping(
    path     = "/submit",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public ResponseEntity<String> submitUserDetails(
    @AuthenticationPrincipal UserDetails userDetails,
    @RequestParam String referredBy,
    @RequestParam String firstName,
    @RequestParam String lastName,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
    @RequestParam String eadType,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eadStartDate,
    @RequestParam Boolean hasPriorExperience,
    @RequestParam(required = false) String priorExperienceDetails,
    @RequestParam String programmingLanguage,
    @RequestParam String fileType,
    @RequestParam MultipartFile uploadedFile
  ) {
    String email = userDetails.getUsername();

    // map to the right file slot
    MultipartFile resumeFile  = null, eadFile = null, idProofFile = null;
    switch (fileType) {
      case "resume"  -> resumeFile  = uploadedFile;
      case "ead"     -> eadFile     = uploadedFile;
      case "idProof" -> idProofFile = uploadedFile;
      default        -> {
        return ResponseEntity
          .badRequest()
          .body("Unknown fileType: " + fileType);
      }
    }

    try {
      service.saveExtraDetails(
        email, referredBy, firstName, lastName, dob,
        eadType, eadStartDate, hasPriorExperience,
        priorExperienceDetails, programmingLanguage,
        resumeFile, eadFile, idProofFile
      );
      return ResponseEntity.ok(
        "✅ Your details have been submitted successfully. Our team will contact you soon."
      );
    } catch (IOException e) {
      // log if you have a logger
      e.printStackTrace();
      return ResponseEntity
        .status(500)
        .body("❌ Failed to save files: " + e.getMessage());
    }
  }
}