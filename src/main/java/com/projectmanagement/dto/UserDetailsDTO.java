package com.projectmanagement.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDetailsDTO {
    private String referredBy;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String eadType;
    private String eadStartDate;
    private Boolean hasPriorExperience;
    private String priorExperienceDetails;
    private String programmingLanguages;

    private MultipartFile resumeFile;
    private MultipartFile eadFile;
    private MultipartFile idProofFile;
}
