package com.projectmanagement.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDetailsDTO {
    private String referredBy;
    private String firstName;
    private String lastName;
    // dob is kept as String in ISO format for simplicity; you can convert later
    private String dob;
    private String email;
    private String eadType;
    private String eadStartDate;
    private Boolean hasPriorExperience;
    private String priorExperienceDetails;
    // Updated to single select field
    private String programmingLanguage;

    // For file uploads
    private MultipartFile resumeFile;
    private MultipartFile eadFile;
    private MultipartFile idProofFile;
}
