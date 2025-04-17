package com.projectmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referredBy;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String eadType;
    private LocalDate eadStartDate;
    private Boolean hasPriorExperience;
    private String priorExperienceDetails;

    private String programmingLanguage;

    private String resumeFileName;
    private String eadFileName;
    private String idProofFileName;

    @OneToOne
    private User user;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final UserDetails instance;

        public Builder() {
            instance = new UserDetails();
        }

        public Builder referredBy(String referredBy) { instance.referredBy = referredBy; return this; }
        public Builder firstName(String firstName) { instance.firstName = firstName; return this; }
        public Builder lastName(String lastName) { instance.lastName = lastName; return this; }
        public Builder dob(LocalDate dob) { instance.dob = dob; return this; }
        public Builder email(String email) { instance.email = email; return this; }
        public Builder eadType(String eadType) { instance.eadType = eadType; return this; }
        public Builder eadStartDate(LocalDate eadStartDate) { instance.eadStartDate = eadStartDate; return this; }
        public Builder hasPriorExperience(Boolean hasPriorExperience) { instance.hasPriorExperience = hasPriorExperience; return this; }
        public Builder priorExperienceDetails(String details) { instance.priorExperienceDetails = details; return this; }
        public Builder programmingLanguage(String language) { instance.programmingLanguage = language; return this; }
        public Builder resumeFileName(String fileName) { instance.resumeFileName = fileName; return this; }
        public Builder eadFileName(String fileName) { instance.eadFileName = fileName; return this; }
        public Builder idProofFileName(String fileName) { instance.idProofFileName = fileName; return this; }
        public Builder user(User user) { instance.user = user; return this; }

        public UserDetails build() { return instance; }
    }
}
