// src/main/java/com/projectmanagement/service/Stage3Service.java
package com.projectmanagement.service;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.Stage3MockTest;
import com.projectmanagement.repo.Stage3Repository;
import com.projectmanagement.utility.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Stage3Service {

    private final Stage3Repository repository;
    private final EmailService emailService;

    @Autowired
    public Stage3Service(Stage3Repository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    /**
     * Adds a mock test. If no testDate is provided, uses today's date.
     */
    public String addMockTest(Stage3MockTest test) {
        if (test.getTestDate() == null) {
            test.setTestDate(LocalDate.now());
        }
        repository.save(test);
        return "Mock test added successfully!";
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
     * Runs every Monday at 08:00 to remind the admin to add weekly tests.
     */
    @Scheduled(cron = "0 0 8 * * MON")
    public void remindAdminWeekly() {
        String subject = "Weekly Mock Test Reminder";
        String body = "Please add this week's mock tests for all users.";
        // Replace with your real admin email
        emailService.sendEmail("admin@example.com", subject, body);
    }
}
