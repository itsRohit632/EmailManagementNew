// src/main/java/com/projectmanagement/service/Stage3Service.java
package com.projectmanagement.service;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.Stage3MockTest;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.Stage3Repository;
import com.projectmanagement.repo.UserRepository;
import com.projectmanagement.utility.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Stage3Service {

    private final Stage3Repository repository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public Stage3Service(Stage3Repository repository,
                         UserRepository userRepository,
                         EmailService emailService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    /**
     * Adds a mock test for the given user email.
     * If no testDate is provided, uses today's date.
     */
    public String addMockTest(Stage3MockTest test, String userEmail) {
        if (test.getTestDate() == null) {
            test.setTestDate(LocalDate.now());
        }

        // Load the User entity from the email in the JWT
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + userEmail));
        test.setUser(user);

        repository.save(test);
        return "Mock test added successfully for " + userEmail + "!";
    }

    /**
     * Retrieves all mock tests for the given user email.
     */
    public List<Stage3MockTest> getMockTests(String email) {
        return repository.findByUserEmail(email);
    }

    /**
     * Builds a DTO of the tests plus their average score.
     */
    public Stage3MockDTO getMockSummary(String email) {
        List<Stage3MockTest> tests = repository.findByUserEmail(email);
        double average = tests.stream()
                              .mapToInt(Stage3MockTest::getScore)
                              .average()
                              .orElse(0.0);
        return new Stage3MockDTO(tests, average);
    }

    /**
     * Runs every Monday at 08:00 Eastern to remind the admin to add weekly tests.
     */
    @Scheduled(cron = "0 0 8 * * MON", zone = "America/New_York")
    public void remindAdminWeekly() {
        String subject = "Weekly Mock Test Reminder";
        String body = "Please add this week's mock tests for all users.";
        emailService.sendEmail("admin@example.com", subject, body);
    }
}
